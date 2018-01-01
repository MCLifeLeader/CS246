// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/ClientModel.java,v 1.56 2006/06/27 00:45:59 manxman Exp $
package sudoku.cs;

import sudoku.al.*;

import sudoku.cs.event.*;

import sudoku.si.*;

import java.net.*;

import java.rmi.*;
import java.rmi.server.*;

import java.util.*;

/**
 * Implements the Client interface and models a game of Sudoku.
 * <p>
 * <a href="../../../docs/newPuzzleSequenceDiagram.jpg">newPuzzle Sequence Diagram</a>
 *
 * @author $Randall Booth, James Comish, Rick Neff, Michael Ricks$
 * @version $Revision: 1.56 $
 */
public class ClientModel
   extends UnicastRemoteObject
   implements Client
{
   /**
    * The default host address prefix.
    */
   public static final String HOST_ADDRESS_PREFIX = "157.201.194.";

   /**
    * The default host address suffix.
    */
   public static final String HOST_ADDRESS_SUFFIX = "254";

   /**
    * The Server instance that the Client can communicate with.
    */
   private Server mService;

   /**
    * The game's current state.
    */
   private State mCurrState;

   /**
    * The game's previous state.
    */
   private State mPrevState;

   /**
    * The Player this Client represents
    */
   private Player mPlayer;

   /**
    * The client's IP address.
    */
   private String mIPAddress;

   /**
    * The current list of Players in the game.
    */
   private Collection<Player> mCurrPlayers;

   /**
    * The collection of listeners to the game state changes
    */
   private Collection<GameStatusChangedListener> mGameStatusChangedListeners;

   /**
    * Contains all of the listeners associated with puzzle changes
    */
   private Collection<PuzzleChangedListener> mPuzzleChangedListeners;

   /**
    * Contains all of the listeners associated with puzzle changes
    */
   private Collection<PlayerChangedListener> mPlayerChangedListeners;

   /**
    * Constructs a ClientModel instance.
    *
    * @param playerType The type of player - Computer or Human
    * @throws RemoteException Caused by problems between remote objects.
    */
   public ClientModel(String playerType) throws RemoteException
   {
      // initialize all required members
      try
      {
         mPlayer = (Player) Class.forName(playerType + "Player").newInstance();
         mPlayer.setName(Config.get("user.name", "No Name"));
      }
      catch (Exception e)
      {
         mPlayer = new ComputerPlayer(Config.get("user.name", "No Name"));
         Logger.debug("Error: Unable to create a " + playerType + " player. " +
            "Creating a Computer player.");
      }

      Logger.debug(mPlayer);
      // initialize event listener collections
      mGameStatusChangedListeners = new ArrayList<GameStatusChangedListener>();
      mPuzzleChangedListeners = new ArrayList<PuzzleChangedListener>();
      mPlayerChangedListeners = new ArrayList<PlayerChangedListener>();
      mCurrPlayers = new ArrayList<Player>();

      // Game state will be LOOKING_FOR_SERVER until both server
      // connection and registration are successful.
      mCurrState = State.LOOKING_FOR_SERVER;
   }

   /**
    * Creates a new ClientModel object.
    *
    * @throws RemoteException Cause by problems between remote objects
    */
   public ClientModel() throws RemoteException
   {
      mPlayer = new HumanPlayer(Config.get("user.name", "No Name"));
      Logger.debug(mPlayer);
      // initialize event listener collections
      mGameStatusChangedListeners = new ArrayList<GameStatusChangedListener>();
      mPuzzleChangedListeners = new ArrayList<PuzzleChangedListener>();
      mPlayerChangedListeners = new ArrayList<PlayerChangedListener>();
      // Game state will be LOOKING_FOR_SERVER until both server
      // connection and registration are successful.
      mCurrState = State.LOOKING_FOR_SERVER;

      try
      {
         mIPAddress = InetAddress.getLocalHost().getHostAddress();
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }
   }

   /**
    * Looks for an already registered Server stored in the RMI registry.
    *
    * @param name the name of the Server to look up.
    *
    * @return a reference to the remote Server.
    */
   public static Remote lookup(String name, String prefix, String suffix)
   {
      String host;
      String hostAddressPrefix = Config.get("RMIRegistryHostAddressPrefix",
            prefix);
      String hostAddressSuffix = Config.get("RMIRegistryHostAddressSuffix",
            suffix);

      if ((prefix != null) && (prefix != "") && (suffix != null) &&
             (suffix != ""))
      {
         host = prefix + suffix;
      }
      else
      {
         host = hostAddressPrefix + hostAddressSuffix;
      }

      String hostKey = "//" + host + "/";

      try
      {
         return (Remote) Naming.lookup(hostKey + name);
      }
      catch (Exception e)
      {
         Logger.debug("ERROR: No server found on host " + host);
      }

      return null;
   }

   /**
    * Attempts to connect to a remote server.
    *
    * @return true if connection successful; false otherwise
    */
   public boolean connectRemote()
   {
      return connectRemote(HOST_ADDRESS_PREFIX, HOST_ADDRESS_SUFFIX);
   }

   /**
    * Attempts to connect to a remote server.
    *
    * @param suffix Enters the desired suffix.
    *
    * @return true if connection successful; false otherwise
    */
   public boolean connectRemote(String suffix)
   {
      if (suffix.length() > 3)
      {
         String prefix = suffix.substring(0, 11);
         suffix = suffix.substring(11);

         return connectRemote(prefix, suffix);
      }

      return connectRemote(HOST_ADDRESS_PREFIX, suffix);
   }

   /**
    * Attempts to connect to a remote server.
    *
    * @param prefix Enters the desired prefix.
    * @param suffix Enters the desired suffix.
    *
    * @return true if connection successful; false otherwise
    */
   public boolean connectRemote(String prefix, String suffix)
   {
      try
      {
         mService = null;
         // look for an already RMI-registered Server
         mService = (Server) lookup(Server.NAME, prefix, suffix);

         if (mService == null)
         {
            Logger.debug("ERROR: RMI lookup for server failed");
         }
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }

      return ((mService == null) ? false : registerWithServer());
   }

   /**
    * Attempts to connect to a locally running server.
    *
    * @return true if connection successful; false otherwise
    */
   public boolean connectLocal()
   {
      try
      {
         mService = null;
         // ServerModel will register itself in the RMI registry
         Logger.debug("NOTICE: Creating local machine server");
         mService = new ServerModel();
      }
      catch (Exception ex)
      {
         // If we fail creating a local server, something is WRONG!
         Logger.debug(ex);
      }

      return registerWithServer();
   }

   /**
    * Attempts to register the Client with the Server (local or network).
    *
    * @return true if registration successful; false otherwise
    */
   public boolean registerWithServer()
   {
      try
      {
         if (! mService.addPlayer(this))
         {
            Logger.debug("ERROR: Could not register with server!");

            return false; // Game state is still LOOKING_FOR_SERVER
         }

         Logger.debug("NOTICE: Server registration successful!");
         // since registration successful, change state
         mPrevState = mCurrState; // PrevState is now LOOKING_FOR_SERVER
         mCurrState = State.WAITING_FOR_PLAYERS;

         fireGameStatusChangedEvent(new GameStatusChangedEvent(mPrevState,
               mCurrState));
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }

      return true;
   }

   /**
    * Gets the Client's IP address.
    *
    * @return The Client's IP address.
    */
   public String getIPAddress() throws RemoteException
   {
      return mIPAddress;
   }

   /**
    * Tells the Client that a player has been changed.
    *
    * @param inPlayers A Collection of the current Players.
    */
   public void playerChanged(Collection<Player> inPlayers)
      throws RemoteException
   {
      firePlayerChangedEvent(new PlayerChangedEvent(mCurrPlayers, inPlayers));
      mCurrPlayers = inPlayers;
   }

   /**
    * Returns an instance of the Player object
    *
    * @return an instance of the Player object
    */
   public Player getPlayer() throws RemoteException
   {
      return mPlayer;
   }

   /**
    * Called by Server to get this Client's user name.
    *
    * @return the Client's user name
    */
   public String getName() throws RemoteException
   {
      return mPlayer.getName();
   }

   /**
    * Sets the Player's current user name.
    *
    * @param inName the new name of the player
    */
   public void setName(String inName)
   {
      mPlayer.setName(inName);
   }

   /**
    * Accesses mPuzzle in mPlayer.
    *
    * @return The players puzzle.
    */
   public Puzzle getPuzzle()
   {
      return mPlayer.getPuzzle();
   }

   /**
    * Accesses PlayerStats in mPlayer
    *
    * @return The players stats
    */
   public Stats getPlayerStats()
   {
      return mPlayer.getPlayerStats();
   }

   /**
    * Tells the Server to generate a new puzzle.
    *
    * @param difficulty The difficulty of the desired puzzle.
    */
   public void newPuzzle(String difficulty)
   {
      try
      {
         mService.newPuzzle(difficulty);
      }
      catch (Exception ex)
      {
         Logger.debug("ERROR: There is no server.");
      }
   }

   /**
    * Sets the board to the given Grid.
    *
    * @param newBoard the Grid for players to solve.
    */
   public void setNewPuzzle(Grid newBoard) throws RemoteException
   {
      getPuzzle().newPuzzle(newBoard);

      for (int y = 0; y < 9; y++)
      {
         for (int x = 0; x < 9; x++)
         {
            String s = Byte.toString(newBoard.getCell(x, y));
            firePuzzleChangedEvent(new PuzzleChangedEvent(x, y, null, s));
         }
      }

      try
      {
         mService.playerStatsChanged();
      }
      catch (Exception ex)
      {
         Logger.debug("ERROR: There is no server.");
      }
   }

   /**
    * Clears the guesses in the puzzle.
    */
   public void clear()
   {
      try
      {
         setNewPuzzle(getPuzzle().getSolution());
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }
   }

   /**
    * Adds a guess to the Grid.
    *
    * @param guess The guess.
    * @param x The x coordinate.
    * @param y The y coordinate.
    */
   public void addGuess(String guess, String prev, int x, int y)
   {
      guess = prev + guess;

      if (prev.length() == 0)
      {
         getPuzzle().setSquare(guess, x, y);
         getPlayerStats().updateNumberOfGuesses();
         getPlayerStats().updateSquaresLeft(getBoard());
      }
      else
      {
         getPuzzle().clearSquare(x, y);
      }

      firePuzzleChangedEvent(new PuzzleChangedEvent(x, y, prev, guess));

      try
      {
         mService.playerStatsChanged();
      }
      catch (Exception ex)
      {
         Logger.debug("ERROR: There is no server.");
      }
   }

   /**
    * Removes a guess from the Grid.
    *
    * @param x The x coordinate.
    * @param y The y coordinate.
    */
   public void removeGuess(String prev, int x, int y)
   {
      if (prev.length() > 0)
      {
         int prevBackSpace = prev.length() - 1;
         String prevAfterBackSpace = prev.substring(0, prevBackSpace);

         if (prev.length() == 1)
         {
            getPuzzle().clearSquare(x, y);
         }
         else if (prevAfterBackSpace.length() == 1)
         {
            getPuzzle().setSquare(prevAfterBackSpace, x, y);
         }

         firePuzzleChangedEvent(new PuzzleChangedEvent(x, y, prev,
               prevAfterBackSpace));

         try
         {
            mService.playerStatsChanged();
         }
         catch (Exception ex)
         {
            Logger.debug("ERROR: There is no server.");
         }
      }
   }

   /**
    * Returns what is in the specified square.
    *
    * @param x X coordinate.
    * @param y Y coordinate.
    *
    * @return What is in the specified sqaure.
    */
   public String getSquare(int x, int y)
   {
      return getPuzzle().getSquare(x, y);
   }

   /**
    * Checks to see if the puzzle is correct.
    *
    * @return Whether the puzzle is correct.
    */
   public Collection checkPuzzle()
   {
      try
      {
         if (getPuzzle().isComplete())
         {
            mService.endGame();
         }
      }
      catch (Exception ex)
      {
         Logger.debug("ERROR: There is no server.");
      }

      return getPuzzle().checkPuzzle();
   }

   /**
    * Sets the puzzle to another desired state.
    *
    * @param puzzle The Grid to set the puzzle to.
    */
   public void setBoard(Grid puzzle)
   {
      try
      {
         setNewPuzzle(puzzle);
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }
   }

   /**
    * Accesses mBoard in mPlayer.
    *
    * @return The players puzzle board.
    */
   public Grid getBoard()
   {
      return getPuzzle().getBoard();
   }

   /**
    * Gets the games timer
    *
    * @return the SudokuTimer
    */
   public SudokuTimer getTimer()
   {
      try
      {
         return mService.getTimer();
      }
      catch (Exception ex)
      {
         Logger.debug("ERROR: There is no server.");
      }

      return null;
   }

   /**
    * Starts a new game and creates a new puzzle.
    *
    * @param difficulty The difficulty of the desired puzzle.
    */
   public void startGame(String difficulty)
   {
      startGame();
      newPuzzle(difficulty);
   }

   /**
    * Host tells the server to start the game.
    */
   public void startGame()
   {
      try
      {
         if (getBoard().empty())
         {
            newPuzzle("");
         }

         mService.startGame();
      }
      catch (Exception ex)
      {
         Logger.debug("ERROR: There is no server.");
      }
   }

   /**
    * Called by Server when all clients have joined the game.
    */
   public void gameReady() throws RemoteException
   {
      clear();
      getPlayerStats().resetStats();

      mPrevState = mCurrState;
      mCurrState = State.GAME_IN_PROGRESS;

      fireGameStatusChangedEvent(new GameStatusChangedEvent(mPrevState,
            mCurrState));
   }

   /**
    * Tells the server to pause the game.
    */
   public void pause()
   {
      try
      {
         mService.pause();
      }
      catch (Exception ex)
      {
         Logger.debug("ERROR: There is no server.");
      }
   }

   /**
    * Tells the server to unpause the game.
    */
   public void unpause()
   {
      try
      {
         if (mCurrState == State.GAME_PAUSED)
         {
            mService.unpause();
         }
      }
      catch (Exception ex)
      {
         Logger.debug("ERROR: There is no server.");
      }
   }

   /**
    * Changes the game state to GAME_PAUSED.
    */
   public void gamePaused() throws RemoteException
   {
      if (mCurrState == State.GAME_IN_PROGRESS)
      {
         mPrevState = mCurrState;
         mCurrState = State.GAME_PAUSED;

         fireGameStatusChangedEvent(new GameStatusChangedEvent(mPrevState,
               mCurrState));
      }
   }

   /**
    * Ends the game.
    */
   public void endGame() throws RemoteException
   {
      mPrevState = mCurrState;
      mCurrState = State.GAME_OVER;

      fireGameStatusChangedEvent(new GameStatusChangedEvent(mPrevState,
            mCurrState));
   }

   /**
    * Gets the current state of the game.
    *
    * @return the current state of the game.
    */
   public State getCurrState()
   {
      return mCurrState;
   }

   /**
    * Gets the previous state of the game.
    *
    * @return the previous state of the game.
    */
   public State getPrevState()
   {
      return mPrevState;
   }

   /**
    * Exits the game on behalf of a Player who leaves early.
    * This method will inform the Server that this Player
    * should be removed from the Server's Client list.
    */
   public void exitGame()
   {
      try
      {
         mService.removePlayer(this, mIPAddress);
      }
      catch (Exception ex)
      {
         Logger.debug("ERROR: There is no server.");
      }

      // Save the player's state information
      saveState();
   }

   /**
    * Saves the Player's current state in the game.
    */
   public void saveState()
   {
      //Logger.debug("NOTICE: Saving Player information");
      Config.set(mPlayer.getName(), mPlayer);

      //Config.save();
   }

   /**
    * Add a GameStatusChangedListener model to the Client.
    *
    * @param listener The GameStatusChangedListener object to add
    */
   public void addGameStatusChangedListener(GameStatusChangedListener listener)
   {
      mGameStatusChangedListeners.add(listener);
   }

   /**
    * Calls the gameStatusChangedEvent method on all
    * GameStatusChangedListener implementers.
    *
    * @param gameStatusChangedEvent contains the event that has happened.
    */
   private void fireGameStatusChangedEvent(
      GameStatusChangedEvent gameStatusChangedEvent)
   {
      Logger.debug(gameStatusChangedEvent);

      for (GameStatusChangedListener listener : mGameStatusChangedListeners)
      {
         listener.gameStatusChanged(gameStatusChangedEvent);
      }
   }

   /**
    * Add a PuzzleChangedListener model to the Client.
    *
    * @param listener The listener to be added to the list
    */
   public void addPuzzleChangedListener(PuzzleChangedListener listener)
   {
      mPuzzleChangedListeners.add(listener);
   }

   /**
    * Calls the puzzleChangedEvent method on all
    * PuzzleChangedListener implementers.
    * @param puzzleChangedEvent The event created by the change
    */
   private void firePuzzleChangedEvent(PuzzleChangedEvent puzzleChangedEvent)
   {
      for (PuzzleChangedListener listener : mPuzzleChangedListeners)
      {
         listener.puzzleChanged(puzzleChangedEvent);
      }
   }

   /**
    * Add a PlayerChangedListener model to the Client.
    *
    * @param listener The listener to be added to the list
    */
   public void addPlayerChangedListener(PlayerChangedListener listener)
   {
      mPlayerChangedListeners.add(listener);
   }

   /**
    * Calls the PlayerChangedEvent method on all
    * PlayerChangedListener implementers.
    *
    * @param playerChangedEvent The event created by the change
    */
   private void firePlayerChangedEvent(PlayerChangedEvent playerChangedEvent)
   {
      for (PlayerChangedListener listener : mPlayerChangedListeners)
      {
         listener.playerChanged(playerChangedEvent);
      }
   }

   /**
    * Returns whether the word is in the Server's dictionary.
    *
    * @param word the word to lookup
    * @return whether the word is in the Server's dictionary.
    */
   public boolean wordLookup(String word) throws RemoteException
   {
      return mService.wordLookup(word);
   }
}

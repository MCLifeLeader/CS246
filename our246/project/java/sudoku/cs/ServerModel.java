// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/ServerModel.java,v 1.32 2006/06/26 23:32:44 manxman Exp $
package sudoku.cs;

import sudoku.al.*;

import sudoku.cs.event.*;

import sudoku.si.*;

import java.io.*;

import java.net.*;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

import java.util.*;

/**
 * Implements (models) the Server interface.
 * Hosts the clients of the Sudoku game.
 *
 * @author $Randall Booth, James Comish, Rick Neff, Michael Ricks$
 * @version $Revision: 1.32 $
 */
public class ServerModel
   extends UnicastRemoteObject
   implements Server
{
   /**
    * The dictionary.
    */
   private Dictionary mDictionary;

   /**
    * Whether or not the Server is bound.
    */
   private boolean mIsBound;

   /**
    * The current state of the game.
    */
   private State mCurrState;

   /**
    * Holds all of the Clients.
    */
   private Map<String, Client> mClients;

   /**
    * The puzzle collection.
    */
   private PuzzleCollection mPuzzleCollection;

   /**
    * The solution to the puzzle.
    */
   private Grid mSolution;

   /**
    * The timer for the Game.
    */
   private SudokuTimer mTimer;

   /**
    * Constructs a ServerModel instance.
    */
   public ServerModel() throws Exception
   {
      mDictionary = new Dictionary();
      mClients = new HashMap<String, Client>();
      mPuzzleCollection = new PuzzleCollection();
      mCurrState = State.WAITING_FOR_PLAYERS;
      mTimer = new SudokuTimer();
      mIsBound = false;
   }

   /**
    * (Re)Binds the Server to its name in the RMI registry.
    */
   public void rebind()
   {
      String name = "///" + NAME;

      try
      {
         Logger.info("Rebinding \"" + NAME + "\"");
         Naming.rebind(name, this);
         mIsBound = true;
      }
      catch (Exception e)
      {
         Logger.debug(e);
      }
   }

   /**
    * Unbinds the Server from its name in the RMI registry.
    */
   public void unbind()
   {
      String name = "///" + NAME;

      try
      {
         Logger.info("Unbinding \"" + NAME + "\"");
         Naming.unbind(name);
         mIsBound = false;
      }
      catch (Exception e)
      {
         Logger.debug(e);
      }
   }

   /**
    * Adds a client's player to the game.
    * A Player is distinguished from another player by name.
    *
    * @param inClient the client whose player just joined the game.
    *
    * @return whether or not the player was added.
    */
   public boolean addPlayer(Client inClient) throws RemoteException
   {
      if ((! mClients.containsKey(inClient.getName())) &&
          ((mCurrState == State.WAITING_FOR_PLAYERS) ||
           ( mCurrState == State.GAME_OVER)))
      {
         Logger.debug("Player added: " + inClient.getName());
         mClients.put(inClient.getName(), inClient);

         new PlayerChangedTellAll(mClients.values(), getPlayers()).start();

         return true;
      }

      return false;
   }

   /**
    * Removes a client's player from the game.
    *
    * @param inClient the client whose player just exited the game.
    */
   public void removePlayer(Client inClient, String clientAddress)
      throws RemoteException
   {
      try
      {
         String serverAddress = InetAddress.getLocalHost().getHostAddress();

         if (serverAddress.equals(clientAddress))
         {
            mCurrState = State.GAME_OVER;
            endGame();
         }
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }

      Logger.debug("Player removed: " + inClient.getName());
      mClients.remove(inClient.getName());

      new PlayerChangedTellAll(mClients.values(), getPlayers()).start();
   }

   /**
    * Lets all players know that the stats have changed.
    */
   public void playerStatsChanged() throws RemoteException
   {
      new PlayerChangedTellAll(mClients.values(), getPlayers()).start();
   }

   /**
    * Puts the Players of each Client into an ArrayList.
    *
    * @return A Collection of the Players
    */
   public Collection<Player> getPlayers()
   {
      Collection<Player> players = new ArrayList<Player>();

      try
      {
         for (Client client : mClients.values())
         {
            players.add(client.getPlayer());
         }
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }

      return players;
   }

   /**
    * Looks up a word in the Server's dictionary.
    *
    * @param word the word to be looked up.
    *
    * @return whether or not the word was found in the dictionary.
    */
   public boolean wordLookup(String word) throws RemoteException
   {
      return mDictionary.lookup(word);
   }

   /**
    * Generates a new puzzle, and gives it to every player.
    *
    * @param difficulty The desired difficulty level.
    */
   public void newPuzzle(String difficulty) throws RemoteException
   {
      mSolution = mPuzzleCollection.getNewPuzzle(difficulty);

      new SetNewPuzzleAll(mClients.values(), mSolution).start();
   }

   /**
    * Gets the solution to the Puzzle.
    *
    * @return The solution.
    */
   public Grid getSolution() throws RemoteException
   {
      return mSolution;
   }

   /**
    * Gets the timer for the game
    *
    * @return the SudokuTimer
    */
   public SudokuTimer getTimer() throws RemoteException
   {
      return mTimer;
   }

   /**
    * Starts the game.
    */
   public void startGame() throws RemoteException
   {
      mCurrState = State.GAME_IN_PROGRESS;

      new StartGameAll(mClients.values()).start();

      mTimer.reset();
      mTimer.start();
   }

   /**
    * Pauses the game.
    */
   public void pause() throws RemoteException
   {
      mTimer.stop();

      mCurrState = State.GAME_PAUSED;

      new PauseAll(mClients.values()).start();
   }

   /**
    * Unpauses the game.
    */
   public void unpause() throws RemoteException
   {
      mCurrState = State.GAME_IN_PROGRESS;

      new StartGameAll(mClients.values()).start();

      mTimer.start();
   }

   /**
    * Ends the game.
    */
   public void endGame() throws RemoteException
   {
      mTimer.stop();

      mCurrState = State.GAME_OVER;

      new EndGameAll(mClients.values()).start();
   }

   /**
    * Mainly for starting a separate server (not running in a client jvm).
    */
   public static void main(String[] args) throws Exception
   {
      new ServerModel().handleCommands(System.in);
   }

   /**
    * Handles commands from an input stream (e.g., standard in).
    * <p>
    * Commands are names of methods taking no parameters and
    * "returning void" that are looked up via reflection and invoked.
    *
    * <ul>
    *   <li>help  - shows help message
    *   <li>show  - shows Server status
    *   <li>start - starts (binds) the Server
    *   <li>stop  - stops (unbinds) the Server
    *   <li>exit  - exits program (same as quit)
    *   <li>quit  - quits program (same as exit)
    * </ul>
    *
    * @param inputStream the input stream for commands.
    */
   public void handleCommands(InputStream inputStream)
      throws Exception
   {
      boolean done = false;
      StringTokenizer st;
      String command;
      String line;

      BufferedReader reader = new BufferedReader(new InputStreamReader(
               inputStream));

      while (true) // break out of by "exit" or "quit"
      {
         System.out.print("\n> ");

         line = reader.readLine();

         //reader.reset();
         st = new StringTokenizer((line != null) ? line : "quit");

         command = (st.hasMoreTokens() ? st.nextToken() : "help");

         try
         {
            getClass().getMethod(command).invoke(this);
         }
         catch (Exception e)
         {
            System.out.println("Unknown command: " + command);
         }
      }
   }

   /**
    * Displays help for the user.
    */
   public void help()
   {
      System.out.println("help  - shows this message");
      System.out.println("show  - shows the server status.");
      System.out.println("start - starts (binds) the server.");
      System.out.println("stop  - stops (unbinds) the server.");
      System.out.println("exit  - exits program (same as quit)");
      System.out.println("quit  - quits program (same as exit)");
   }

   /**
    * Shows the Server status.
    */
   public void show() throws RemoteException
   {
      System.out.println("Server is " + (mIsBound ? "bound." : "unbound."));
      System.out.println("Player");
      System.out.println("-----------------------");

      for (Client client : mClients.values())
      {
         System.out.println(client.getName());
      }
   }

   /**
    * Starts (binds) the Server.
    */
   public void start()
   {
      rebind();
   }

   /**
    * Stops (unbinds) the Server.
    */
   public void stop()
   {
      unbind();
   }

   /**
    * Exits (quits) this program.
    */
   public void exit()
   {
      System.exit(0);
   }

   /**
    * Quits (exits) this program.
    */
   public void quit()
   {
      exit();
   }
}

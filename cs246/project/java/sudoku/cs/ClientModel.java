// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/ClientModel.java,v 1.1 2006/06/13 16:13:03 neffr Exp $
package sudoku.cs;

import sudoku.cs.event.*;

import sudoku.al.*;

import sudoku.si.*;

import java.rmi.*;
import java.rmi.server.*;

import java.util.*;

/**
 * Implements the Client interface and models a game of Sudoku.
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
   private Server mService = null;

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
    * The collection of listeners to the game state changes
    */
   private Collection<GameStatusChangedListener> mGameStatusChangedListeners;

   /**
    * Constructs a ClientModel instance.
    */
   public ClientModel()
      throws RemoteException
   {
      // initialize all required members
      mPlayer = new Player(Config.get("user.name", "No Name"));
      Logger.debug(mPlayer);
      // initialize event listener collection
      mGameStatusChangedListeners =
         new ArrayList<GameStatusChangedListener>();
      // Game state will be LOOKING_FOR_SERVER until both server
      // connection and registration are successful.
      mCurrState = State.LOOKING_FOR_SERVER;
   }

   /**
    * Looks for an already registered Server stored in the RMI registry.
    *
    * @param name the name of the Server to look up.
    *
    * @return a reference to the remote Server.
    */
   public static Remote lookup(String name)
   {
      String hostAddressPrefix = Config.get("RMIRegistryHostAddressPrefix",
            HOST_ADDRESS_PREFIX);
      String hostAddressSuffix = Config.get("RMIRegistryHostAddressSuffix",
            HOST_ADDRESS_SUFFIX);
      String host = hostAddressPrefix + hostAddressSuffix;
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
      try
      {
         mService = null;
         // look for an already RMI-registered Server
         mService = (Server) lookup(Server.NAME);

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

         fireGameStatusChangedEvent(
            new GameStatusChangedEvent(mPrevState, mCurrState));
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }

      return true;
   }

   /**
    * Returns an instance of the Player object
    *
    * @return an instance of the Player object
    */
   public Player getPlayer()
   {
      return mPlayer;
   }

   /**
    * Called by Server to get this Client's user name.
    *
    * @return the Client's user name
    */
   public String getName()
      throws RemoteException
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
    * Called by Server when all clients have joined the game.
    */
   public void gameReady()
      throws RemoteException
   {
      mPrevState = mCurrState;
      mCurrState = State.GAME_IN_PROGRESS;
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
         mService.removePlayer(this);
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }

      // Save the player's state information
      saveState();
   }

   /**
    * Saves the Player's current state in the game.
    */
   public void saveState()
   {
      Logger.debug("NOTICE: Saving Player information");
      Config.set(mPlayer.getName(), mPlayer);
      Config.save();
   }

   /**
    * Add a GameStatusChangedListener model to the Client.
    *
    * @param l The GameStatusChangedListener object to add
    */
   public void addGameStatusChangedListener(GameStatusChangedListener l)
   {
      mGameStatusChangedListeners.add(l);
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
    * Returns whether the word is in the Server's dictionary.
    *
    * @param word the word to lookup
    * @return whether the word is in the Server's dictionary.
    */
   public boolean wordLookup(String word)
      throws RemoteException
   {
      return mService.wordLookup(word);
   }
}

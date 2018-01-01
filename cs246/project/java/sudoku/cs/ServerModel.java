// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/ServerModel.java,v 1.10 2006/06/17 00:15:44 manxman Exp $
package sudoku.cs;

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
    * Holds all of the Players
    */
   private Map<String, Client> mPlayers;

   /**
    * Constructs a ServerModel instance.
    */
   public ServerModel() throws Exception
   {
      mDictionary = new Dictionary();
      mPlayers = new HashMap<String, Client>();
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
   public boolean addPlayer(Client inClient)
      throws RemoteException
   {
      if (! mPlayers.containsKey(inClient.getName()))
      {
         Logger.debug("Player added: " + inClient.getName());
         mPlayers.put(inClient.getName(), inClient);

         return true;
      }

      return false;
   }

   /**
    * Removes a client's player from the game.
    *
    * @param inClient the client whose player just exited the game.
    */
   public void removePlayer(Client inClient)
      throws RemoteException
   {
      Logger.debug("Player removed: " + inClient.getName());
      mPlayers.remove(inClient);
   }

   /**
    * Looks up a word in the Server's dictionary.
    *
    * @param word the word to be looked up.
    *
    * @return whether or not the word was found in the dictionary.
    */
   public boolean wordLookup(String word)
      throws RemoteException
   {
      return mDictionary.lookup(word);
   }

   /**
    * Mainly for starting a separate server (not running in a client jvm).
    */
   public static void main(String[] args)
      throws Exception
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

//         reader.reset();

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

      for (Client player : mPlayers.values())
      {
         System.out.println(player.getName());
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

// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/Server.java,v 1.1 2006/06/13 16:13:03 neffr Exp $
package sudoku.cs;

import java.rmi.*;

/**
 * Remote interface for the Sudoku Server.
 */
public interface Server
   extends Remote
{
   /**
    * The name by which the server is registered in the RMI registry.
    */
   public static final String NAME = "SudokuServer";

   /**
    * Adds a client's player to the game.
    *
    * @param inClient the client whose player just joined the game.
    *
    * @return whether or not the player was added.
    */
   public boolean addPlayer(Client inClient)
      throws RemoteException;

   /**
    * Removes a client's player from the game.
    *
    * @param inClient the client whose player just exited the game.
    */
   public void removePlayer(Client inClient)
      throws RemoteException;

   /**
    * Looks up a word in the Server's dictionary.
    *
    * @param word the word to be looked up.
    *
    * @return whether or not the word was found in the dictionary.
    */
   public boolean wordLookup(String word)
      throws RemoteException;
}

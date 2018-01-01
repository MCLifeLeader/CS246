// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/Server.java,v 1.15 2006/06/26 23:32:44 manxman Exp $
package sudoku.cs;

import sudoku.al.*;

import java.rmi.*;

/**
 * Remote interface for the Sudoku Server.
 *
 * @author $James Comish, Rick Neff, Michael Ricks$
 * @version $Revision: 1.15 $
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
   public boolean addPlayer(Client inClient) throws RemoteException;

   /**
    * Removes a client's player from the game.
    *
    * @param inClient the client whose player just exited the game.
    */
   public void removePlayer(Client inClient, String clientAddress)
      throws RemoteException;

   /**
    * Lets all players know that the stats have changed.
    */
   public void playerStatsChanged() throws RemoteException;

   /**
    * Gets solution
    *
    * @return getSolution
    *
    */
   public Grid getSolution() throws RemoteException;

   /**
    * Gets the games timer
    *
    * @return the SudokuTimer
    */
   public SudokuTimer getTimer() throws RemoteException;

   /**
    * Generates a new puzzle.
    *
    * @param difficulty The desired difficulty level.
    */
   public void newPuzzle(String difficulty) throws RemoteException;

   /**
    * Start the game.
    */
   public void startGame() throws RemoteException;

   /**
    * Pauses the game.
    */
   public void pause() throws RemoteException;

   /**
    * Unpauses the game.
    */
   public void unpause() throws RemoteException;

   /**
    * Ends the game.
    */
   public void endGame() throws RemoteException;

   /**
    * Looks up a word in the Server's dictionary.
    *
    * @param word the word to be looked up.
    *
    * @return whether or not the word was found in the dictionary.
    */
   public boolean wordLookup(String word) throws RemoteException;
}

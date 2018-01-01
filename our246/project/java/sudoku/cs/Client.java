// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/Client.java,v 1.13 2006/06/26 23:32:44 manxman Exp $
package sudoku.cs;

import sudoku.al.*;

import java.rmi.*;

import java.util.*;

/**
 * Remote interface for the Sudoku Client.
 *
 * @author $James Comish, Rick Neff$
 * @version $Revision: 1.13 $
 */
public interface Client
   extends Remote
{
   /**
    * Gets the Clients IP address.
    *
    * @return The Client's IP address.
    */
   public String getIPAddress() throws RemoteException;

   /**
    * Tells the Client that a Player has been changed.
    *
    * @param inPlayers A Collection of the current Players.
    */
   public void playerChanged(Collection<Player> inPlayers)
      throws RemoteException;

   /**
    * Called by Server to get this client's player's name.
    */
   public String getName() throws RemoteException;

   /**
    * Sets the board to the given Grid.
    *
    * @param newBoard The Grid for players to solve.
    */
   public void setNewPuzzle(Grid newBoard) throws RemoteException;

   /**
    * Called by Server when all clients have joined the game.
    */
   public void gameReady() throws RemoteException;

   /**
    * Pauses the game.
    */
   public void gamePaused() throws RemoteException;

   /**
    * Ends the game.
    */
   public void endGame() throws RemoteException;

   /**
    * Gets a Player object.
    *
    * @return an instance of the Player object
    */
   public Player getPlayer() throws RemoteException;
}

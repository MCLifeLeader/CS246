// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/Client.java,v 1.1 2006/06/13 16:13:03 neffr Exp $
package sudoku.cs;

import java.rmi.*;

import java.util.*;

/**
 * Remote interface for the Sudoku Client.
 */
public interface Client
    extends Remote
{
   /**
    * Called by Server to get this client's player's name.
    */
   public String getName()
      throws RemoteException;
   
   /**
    * Called by Server when all clients have joined the game.
    */
   public void gameReady()
      throws RemoteException;

   /**
    * Gets a Player object.
    *
    * @return an instance of the Player object
    */
   public Player getPlayer()
      throws RemoteException;
}

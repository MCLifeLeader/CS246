// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/SetNewPuzzleAll.java,v 1.2 2006/06/24 17:56:12 manxman Exp $
package sudoku.cs;

import sudoku.al.*;

import sudoku.si.*;

import java.util.*;

/**
 * Sets a new puzzle in each of the clients.
 *
 * @author $James Comish$
 * @version $Revision: 1.2 $
 */
public class SetNewPuzzleAll
   extends ClientCallback
{
   /**
    * The new puzzle.
    */
   private Grid mGrid;

   /**
    * Creates a new SetNewPuzzleAll object.
    *
    * @param inClients A Collection of the Clients.
    * @param inGrid The new puzzle.
    */
   public SetNewPuzzleAll(Collection<Client> inClients, Grid inGrid)
   {
      super(inClients);
      mGrid = inGrid;
   }

   /**
    * Send the action to the Client.
    *
    * @param client The client to send the message to.
    */
   public void clientCallback(Client client)
   {
      try
      {
         client.setNewPuzzle(mGrid);
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }
   }
}

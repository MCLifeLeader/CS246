// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/PauseAll.java,v 1.2 2006/06/24 17:56:12 manxman Exp $
package sudoku.cs;

import sudoku.si.*;

import java.util.*;

/**
 * Sends a message to the Clients to pause the game.
 *
 * @author $James Comish$
 * @version $Revision: 1.2 $
 */
public class PauseAll
   extends ClientCallback
{
   /**
    * Creates a new PauseAll object.
    *
    * @param inClients A Collection of the Clients.
    */
   public PauseAll(Collection<Client> inClients)
   {
      super(inClients);
   }

   /**
    * Tells the Client the game is paused.
    *
    * @param client The client to send the message to.
    */
   public void clientCallback(Client client)
   {
      try
      {
         client.gamePaused();
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }
   }
}

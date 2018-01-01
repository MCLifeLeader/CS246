// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/StartGameAll.java,v 1.2 2006/06/24 17:56:12 manxman Exp $
package sudoku.cs;

import sudoku.si.*;

import java.util.*;

/**
 * Sends a message to the Clients
 *
 * @author $James Comish$
 * @version $Revision: 1.2 $
 */
public class StartGameAll
   extends ClientCallback
{
   /**
    * Creates a new StartGameAll object.
    *
    * @param inClients A Collection of the Clients.
    */
   public StartGameAll(Collection<Client> inClients)
   {
      super(inClients);
   }

   /**
    * Tells the Client the game has started.
    *
    * @param client The client to send the message to.
    */
   public void clientCallback(Client client)
   {
      try
      {
         client.gameReady();
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }
   }
}

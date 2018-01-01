// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/EndGameAll.java,v 1.2 2006/06/24 17:56:12 manxman Exp $
package sudoku.cs;

import sudoku.si.*;

import java.util.*;

/**
 * Sends a message to the Clients to end the game.
 *
 * @author $James Comish$
 * @version $Revision: 1.2 $
 */
public class EndGameAll
   extends ClientCallback
{
   /**
    * Creates a new EndGameAll object.
    *
    * @param inClients A Collection of the Clients.
    */
   public EndGameAll(Collection<Client> inClients)
   {
      super(inClients);
   }

   /**
    * Tells the Client the game has ended.
    *
    * @param client The client to send the message to.
    */
   public void clientCallback(Client client)
   {
      try
      {
         client.endGame();
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }
   }
}

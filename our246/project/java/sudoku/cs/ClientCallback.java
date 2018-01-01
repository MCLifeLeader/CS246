// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/ClientCallback.java,v 1.2 2006/06/24 17:56:12 manxman Exp $
package sudoku.cs;

import java.util.*;

/**
 * Starts a thread that will send messages to the clients.
 *
 * @author $James Comish$
 * @version $Revision: 1.2 $
 */
public abstract class ClientCallback
   extends Thread
{
   /**
    * Holds all the given Clients.
    */
   private Collection<Client> mClients;

   /**
    * Creates a new ClientCallback object.
    *
    * @param inClients A Collection of the Clients.
    */
   public ClientCallback(Collection<Client> inClients)
   {
      mClients = inClients;
   }

   /**
    * Runs the thread.
    */
   public void run()
   {
      for (Client client : mClients)
      {
         clientCallback(client);
      }
   }

   /**
    * Send the action to the Client.
    *
    * @param client The client to send the message to.
    */
   public abstract void clientCallback(Client client);
}

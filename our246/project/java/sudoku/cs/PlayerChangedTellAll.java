// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/PlayerChangedTellAll.java,v 1.1 2006/06/24 23:31:39 manxman Exp $
package sudoku.cs;

import sudoku.si.*;

import java.util.*;

/**
 * Sends a message to the Clients that a player has been changed.
 *
 * @author $James Comish$
 * @version $Revision: 1.1 $
 */
public class PlayerChangedTellAll
   extends ClientCallback
{
   /**
    * A Collection of the current players.
    */
   Collection<Player> mPlayers;

   /**
    * Creates a new PlayerChangedTellAll object.
    *
    * @param inClients A Collection of the Clients.
    * @param inPlayers A Collection of the Players.
    */
   public PlayerChangedTellAll(Collection<Client> inClients,
      Collection<Player> inPlayers)
   {
      super(inClients);
      mPlayers = inPlayers;
   }

   /**
    * Tells the Client that a player has been changed.
    *
    * @param client The client to send the message to.
    */
   public void clientCallback(Client client)
   {
      try
      {
         client.playerChanged(mPlayers);
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }
   }
}

// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/event/PlayerChangedEvent.java,v 1.2 2006/06/24 17:56:40 manxman Exp $
package sudoku.cs.event;

import sudoku.cs.*;

import java.util.*;

/**
 * Tells the lister that the Player list has changed.
 *
 * @author $James Comish$
 * @version $Revision: 1.2 $
 */
public class PlayerChangedEvent
{
   /**
    * The previous list.
    */
   private Collection<Player> mPrevList;

   /**
    * The new list.
    */
   private Collection<Player> mCurrList;

   /**
    * Creates a new PlayerChangedEvent object.
    *
    * @param prevList The previous list.
    * @param currList The current list.
    */
   public PlayerChangedEvent(Collection<Player> prevList,
      Collection<Player> currList)
   {
      mPrevList = prevList;
      mCurrList = currList;
   }

   /**
    * Gets the previous list.
    *
    * @return The previous list.
    */
   public Collection<Player> getPrevList()
   {
      return mPrevList;
   }

   /**
    * Gets the current list.
    *
    * @return The current list.
    */
   public Collection<Player> getCurrList()
   {
      return mCurrList;
   }

   /**
    * Displays the two lists of players.
    *
    * @return The lists as a string.
    */
   public String toString()
   {
      String theList = "[";

      for (Player player : mPrevList)
      {
         theList += player.getName();
      }

      theList += "][";

      for (Player player : mPrevList)
      {
         theList += player.getName();
      }

      theList += "]";

      return theList;
   }
}

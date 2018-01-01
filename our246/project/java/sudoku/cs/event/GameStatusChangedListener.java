// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/event/GameStatusChangedListener.java,v 1.3 2006/06/24 17:56:40 manxman Exp $
package sudoku.cs.event;


/**
 * Listener for game status change events.
 *
 * @author $Rick Neff$
 * @version $Revision: 1.3 $
  */
public interface GameStatusChangedListener
{
   /**
    * Called when the game status changes.
    *
    * @param e the game status changed event.
    */
   public void gameStatusChanged(GameStatusChangedEvent e);
}

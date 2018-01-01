// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/event/GameStatusChangedListener.java,v 1.1 2006/06/13 16:13:03 neffr Exp $

package sudoku.cs.event;

/**
 * Listener for game status change events.
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

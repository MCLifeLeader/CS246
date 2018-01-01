// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/event/PlayerChangedListener.java,v 1.2 2006/06/24 17:56:40 manxman Exp $
package sudoku.cs.event;


/**
 * Listener for player change events.
 *
 * @author $James Comish$
 * @version $Revision: 1.2 $
 */
public interface PlayerChangedListener
{
   /**
    * Called when the player changes.
    *
    * @param e The player changed event.
    */
   public void playerChanged(PlayerChangedEvent e);
}

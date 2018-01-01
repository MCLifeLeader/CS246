// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/event/PuzzleChangedListener.java,v 1.3 2006/06/24 17:56:40 manxman Exp $
package sudoku.cs.event;


/**
 * Listener for puzzle change events.
 *
 * @author $Randall Booth$
 * @version $Revision: 1.3 $
  */
public interface PuzzleChangedListener
{
   /**
    * Called when the puzzle changes.
    *
    * @param e the puzzle changed event.
    */
   public void puzzleChanged(PuzzleChangedEvent e);
}

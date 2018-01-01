// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/ClearCommand.java,v 1.7 2006/06/25 07:11:47 emerrill Exp $
package sudoku.ui;

import sudoku.al.Grid;

import sudoku.cs.*;

/**
 * Will clear the screen
 *
 * @author $Randall King$
 * @version $Revision: 1.7 $
 */
public class ClearCommand
   implements Command
{
   /**
    * To save a copy of the panel before erasing it
    */
   private Grid mGrid;

   /**
    * A reference to the client model class
    */
   private ClientModel mClientModel;

   /**
    * Get the reference to the client model to
    * access the getters and setters
    */
   public ClearCommand(ClientModel reference)
   {
      mClientModel = reference;
      mGrid = mClientModel.getBoard();
   }

   /**
    * Sets the puzzle
    */
   public void undo()
   {
      mClientModel.setBoard(mGrid);
      Sudoku.getPanel().update();
   }

   /**
    * Re-clears the puzzle
    */
   public void redo()
   {
      execute();
   }

   /**
    * Executes the clear function.
    */
   public void execute()
   {
      mClientModel.clear();
      Sudoku.getPanel().update();
   }
}

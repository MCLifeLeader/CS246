// $Header
package sudoku.ui;

import sudoku.al.Grid;

import sudoku.cs.*;

/**
 * When the user removes a number.
 *
 * @author $Randall$
 * @version $Revision: 1.6 $
 */
public class RemoveGuessCommand
   implements Command
{
   /**
    * The row that it is on.
    */
   private int mX;

   /**
    * The column that it is on.
    */
   private int mY;

   /**
    * The number that was removed in String format.
    */
   private String mNumber;

   /**
    * A reference to the puzzle.
    */
   private ClientModel mClientModel;

   /**
    * Creates a new RemoveNumberCommand object.
    *
    * @param row Location of erase.
    * @param column Location of erase.
    */
   public RemoveGuessCommand(String prev, int row, int column)
   {
      mNumber = prev;
      mX = row;
      mY = column;
      mClientModel = Sudoku.getClientModel();
   }

   /**
    * Puts the number back in the model.
    */
   public void undo()
   {
      int length = mNumber.length();
      String thePreviousString = mNumber.substring(0, length - 1);
      String theGuess = mNumber.substring(length - 1, length);
      mClientModel.addGuess(theGuess, thePreviousString, mX, mY);
   }

   /**
    * Erases the number again.
    */
   public void redo()
   {
      mClientModel.removeGuess(mNumber, mX, mY);
   }

   /**
    * Gets the erased value from the model, then erases it from the model.
    */
   public void execute()
   {
      mClientModel.removeGuess(mNumber, mX, mY);
   }
}

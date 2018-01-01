// $Header
package sudoku.ui;

import sudoku.cs.*;

/**
 * Created when the user enters a number
 * runs the execute portion
 * @author $Randall King$
 * @version $Revision: 1.6 $
 */
public class AddGuessCommand
   implements Command
{
   /**
    * The number guessed.
    */
   private String mNumber;

   /**
    * The previous guess
    */

   private String mPrevious;

   /**
    * The x coordinate of the location
    */
   private int mX;

   /**
    * The y coordinate of the location
    */
   private int mY;

   /**
    * Stores a reference to the puzzle
    */
   private ClientModel mClientModel;

   /**
    * Creates a new AddNumberCommand object.
    * Must have this information!!!
    * @param number a string with the info in it
    * @param row the row it's on
    * @param column the column it's on
    */
   public AddGuessCommand(String number, String prev, int row, int column)
   {
      mNumber = number;
      mPrevious = prev;
      mX = row;
      mY = column;
      mClientModel = Sudoku.getClientModel();
   }

   /**
    * Erases the the number they wrote.
    */
   public void undo()
   {
      mClientModel.removeGuess((mPrevious + mNumber), mX, mY);

      if (mPrevious.length() == 1)
      {
         mClientModel.addGuess(mPrevious, "", mX, mY);
      }
   }

   /**
    * Puts the number back
    */
   public void redo()
   {
      execute();
   }

   /**
    * Changes the model when the user does.
    */
   public void execute()
   {
      mClientModel.addGuess(mNumber, mPrevious, mX, mY);
   }
}

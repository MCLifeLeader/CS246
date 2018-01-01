// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/Puzzle.java,v 1.2 2006/06/18 02:44:44 manxman Exp $
package sudoku.cs;

import sudoku.al.*;

/**
 * Controls each players puzzle.
 *
 * @author $author$
 * @version $Revision: 1.2 $
 */
public class Puzzle
{
   /**
    * The uncomplete puzzle used by the player.
    */
   private Grid mPuzzle;

   /**
    * The solution to the current Puzzle; also holds the original configuration
    */
   private Grid mSolution;

   /**
    * Accesses the Player's puzzle.
    *
    * @return The editable puzzle.
    */
   public Grid getPuzzle()
   {
      return mPuzzle;
   }

   /**
    * Sets the value of the square at the given coordinates.
    *
    * @param value The new value of the square.
    * @param x The x coordinate.
    * @param y The y coordinate.
    */
   public void setSquare(String value, int x, int y)
   {
      //set the value in mPuzzle
   }

   /**
    * Gets the value at the given Square.
    *
    * @param x The x coordinate.
    * @param y The y coordinate.
    *
    * @return The value of the given square.
    */
   public String getSquare(int x, int y)
   {
      //get the value from mPuzzle
      return "0";
   }

   /**
    * Creates a new puzzle to solve.
    */
   public void newPuzzle()
   {
      mSolution = GeneratePuzzle.main(new byte[81]);
   }

   /**
    * Resets the puzzle the original configuration.
    */
   public void clear()
   {
//      mPuzzle.resetGrid(mSolution);
   }

   /**
    * Checks to see if the puzzle is finished.
    *
    * @return Whether the puzzle is finished or not.
    */
   public boolean isComplete()
   {
      //checks to see if puzzle is done
      return true;
   }
}

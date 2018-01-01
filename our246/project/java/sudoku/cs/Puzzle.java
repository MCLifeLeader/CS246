// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/Puzzle.java,v 1.23 2006/06/24 17:56:12 manxman Exp $
package sudoku.cs;

import sudoku.al.*;

import sudoku.si.*;

import java.io.*;

import java.util.*;

/**
 * Controls each players puzzle.
 *
 * @author $Randall Booth, James Comish, Michael Ricks$
 * @version $Revision: 1.23 $
 */
public class Puzzle
   implements Serializable
{
   /**
    * The uncomplete puzzle board used by the player.
    */
   private Grid mBoard;

   /**
    * The solution to the current Puzzle; also holds the original configuration
    */
   private Grid mSolution;

   /**
    * Creates a new Puzzle object.
    */
   public Puzzle()
   {
      mBoard = new Grid();
   }

   /**
    * Accesses the Player's puzzle.
    *
    * @return The editable puzzle.
    */
   public Grid getBoard()
   {
      return mBoard;
   }

   /**
    * Gets the puzzle's solution.
    *
    * @return The solution.
    */
   public Grid getSolution()
   {
      return mSolution;
   }

   /**
    * Sets the value of the square at the given coordinates.
    * @param value The new value of the square.
    * @param x The x coordinate.
    * @param y The y coordinate.
    */
   public void setSquare(String value, int x, int y)
   {
      try
      {
         byte number = Byte.parseByte(value);
         mBoard.setCell(x, y, number);
      }
      catch (Exception ex)
      {
         Logger.debug(ex);
      }
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
      return (Byte.toString(mBoard.getCell(x, y)));
   }

   /**
    * Clears a given square back to the original negative number.
    */
   public void clearSquare(int x, int y)
   {
      mBoard.setCell(x, y, mSolution.getCell(x, y));
   }

   /**
    * Creates a new puzzle to solve.
    */
   public void newPuzzle(Grid solution)
   {
      mSolution = solution;
      mBoard = new Grid(solution.getArray());
   }

   /**
    * Resets the puzzle board the original configuration.
    */
   public void clear()
   {
      mBoard.resetGrid(mSolution);
   }

   /**
    * Resets the puzzle board to the given configuration.
    * Used for the undo method.
    */
   public void setBoard(Grid board)
   {
      mBoard = board;
   }

   /**
    * Check for invalid numbers in the puzzle.
    *
    * @return All invalid indexes.
    */
   public Collection checkPuzzle()
   {
      GridChecker test = new GridChecker(mBoard.getArray());
      test.checkGrid();

      return test.getInvalidIndexes();
   }

   /**
    * Checks to see if the puzzle is finished.
    *
    * @return Whether the puzzle is finished or not.
    */
   public boolean isComplete()
   {
      GridChecker test = new GridChecker(mBoard.getArray());

      return (test.checkGrid() && mBoard.full());
   }
}

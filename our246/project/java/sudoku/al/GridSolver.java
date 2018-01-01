// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/al/GridSolver.java,v 1.2 2006/06/24 01:08:53 rec26 Exp $
package sudoku.al;


/**
 * GridSolver.java
 * @author $AlgorithmTeam - Richard Cochran$
 * @version $
 * This class creates a GridSolver object.
 * Its purpose is to find a solution to a sudoku grid.
 */
public class GridSolver
{
   /**
    * Represents a sudoku grid.
    */
   private Grid gridObj;

   /**
    * Each index corresponds with a possible sudoku entry in the grid.
    */
   private final byte[] mData;

   /**
    * Stores data for the current grid being evaluated.
    */
   private byte[] mDataTemp;

   /**
    * Stores the solution to the sudoku grid, if no solution is found
    *    then it is filled with zeros.
    */
   private byte[] mDataSolution;

   /**
    * The number of cells in the grid.
    */
   private final int mTotalSize;

   /**
    * The number of columns, rows, and subgrids in the grid.
    */
   private final int mSize;

   /**
    * The number of clues initially given.
    */
   private final int mNumClues;

   /**
    * Creates a new defualt GridSolver instance.
    */
   public GridSolver()
   {
      mData = new byte[0];
      mTotalSize = 0;

      // Cast to integer should never be problematic because 'mData' should
      //    have n^2 elements (n = some positive integer).
      mSize = 0;
      mNumClues = 0;
   }

   /**
    * Creates a new GridSolver instance.
    *
    * @param inData the value in each cell of the grid.
    */
   public GridSolver(byte[] inData)
   {
      mData = new byte[inData.length];
      System.arraycopy(inData, 0, mData, 0, inData.length);

      mTotalSize = mData.length;

      // Cast to integer should never be problematic because 'mData' should
      //    have n^2 elements (n = some positive integer).
      mSize = (int) Math.sqrt(mTotalSize);
      mNumClues = calcNumClues();
   }

   /**
    * @return the number of rows, columns, and subgrids.
    */
   int getSize()
   {
      return mSize;
   }

   /**
    * @return the total number of cells in the grid.
    */
   int getTotalSize()
   {
      return mTotalSize;
   }

   /**
    * @return the number of clues initially given in the grid.
    */
   int getNumClues()
   {
      return mNumClues;
   }

   /**
    * Calculates the number of initial clues given in the grid.
    *
    * @return the number of clues.
    */
   int calcNumClues()
   {
      int tempNumClues = 0;

      for (int index = 0; index < getTotalSize(); index++)
      {
         if (mData[index] > 0)
         {
            tempNumClues++;
         }
      }

      return tempNumClues;
   }

   /**
    * @return the solution to the grid.
    */
   byte[] getDataSolution()
   {
      return mDataSolution;
   }

   /**
    * Makes an absolute value copy of the solution.
    *
    * This is only called if a solution was found.
    */
   void makeDataSolution()
   {
      for (int index = 0; index < getTotalSize(); index++)
      {
         mDataSolution[index] = (byte) Math.abs(mDataTemp[index]);
      }
   }

   /**
    * Overwrites any value that is not a clue with a 0 and also fills the
    *    solution container with zeros.
    */
   void formatGridData()
   {
      mDataTemp = new byte[mData.length];
      mDataSolution = new byte[mData.length];

      for (int index = 0; index < getTotalSize(); index++)
      {
         mDataSolution[index] = 0;

         if (mData[index] <= 0)
         {
            mDataTemp[index] = 0;
         }
         else
         {
            mDataTemp[index] = mData[index];
         }
      }
   }

   /**
    * Uses brute force and backtracking to find a solution to the grid.
    */
   void solveGrid()
   {
      formatGridData();

      GridChecker gridCheckerObj = new GridChecker(mDataTemp);

      // Attempt to solve the grid ONLY if it is a valid starting sudoku grid.
      if (gridCheckerObj.checkGrid() == true)
      {
         int numLeft = (getTotalSize() - getNumClues());
         byte fillValue;
         int currIndex = 0; // The current index to evaluate/make a guess in.

         // Keep making guesses until the grid is solved or until
         //    the grid is found to be unsolvable.
         while ((numLeft > 0) || (gridCheckerObj.getIsGridValid() == false))
         {
            // The guess was not immediately found to be incorrect...
            if (gridCheckerObj.getIsGridValid() == true)
            {
               // Find next entry to enter a guess in.
               for (; mDataTemp[currIndex] > 0; currIndex++)
               {
               }
            }

            // Put a new guess value in the current cell being evaluated.
            fillValue = (byte) (mDataTemp[currIndex] - 1);

            // Keep evaluating this index until a correct guess is made
            //    or until the guess is outside the valid range.
            for (; Math.abs(fillValue) <= mSize; fillValue--)
            {
               mDataTemp[currIndex] = fillValue;
               // Use parameterized constructor to create a GridChecker object.
               gridCheckerObj = new GridChecker(mDataTemp);
               gridCheckerObj.checkGrid();

               // A correct guess was made.
               if (gridCheckerObj.getIsGridValid() == true)
               {
                  currIndex++;
                  numLeft--;

                  break;
               }
            }

            // No valid guess was found for the index being evaluated.
            if (gridCheckerObj.getIsGridValid() == false)
            {
               // Set the value at 'currIndex' equal to zero to indicate that 
               //    this index needs to reevaluated at a later time.
               mDataTemp[currIndex] = 0;
               numLeft++;

               // Backtrack to the last cell where a guess was made.
               do
               {
                  currIndex--;

                  if (currIndex == -1)
                  {
                     break;
                  }
               }
               while (mDataTemp[currIndex] > 0);

               if (currIndex == -1) // No valid sudoku solution exists.
               {
                  break;
               }
            }
         }

         // Make a permanent copy of the valid solution.
         if (gridCheckerObj.getIsGridValid() == true)
         {
            makeDataSolution();
         }
      }
   }

   /**
    * Intentionally left empty.
    */
   public static void main(String[] args)
   {
   }
}

// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/al/GridChecker.java,v 1.11 2006/06/24 21:51:13 mbcarey Exp $
package sudoku.al;

import java.util.Vector;

/**
 * GridChecker.java
 * @author $AlgorithmTeam - Richard Cochran$
 * @version $Revision: 1.11 $
 * This class creates a GridChecker object.
 * Its main purpose is to determine if a sudoku grid contains a valid solution.
 * If an invalid entry is encountered in a grid the remainder of the grid is
 *    not checked.
 */
public class GridChecker
{
   /**
    * Created in the 'GridChecker' constructor.
    */
   private Grid mGridObj;

   /**
    * Each index corresponds with a sudoku entry in the grid.
    */
   private byte[] mData;

   /**
    * The number of columns, rows, and subgrids in the grid.
    */
   private int mSize;

   /**
    * The number of columns/rows in a subgrid.
    */
   private int mSubgridSize;

   /**
    * Contains array indices of where there are invalid entries in
    * the Sudoku puzzle.
    * Negative values signify indices where a non-visible number is
    * conflicting with a visible guess.
    * If there are no invalid sudoku entries then the vector is empty.
    */
   private Vector<Integer> mInvalidIndex;

   /**
    * Used to save values from an invalid row, column, or subgrid
    * just in case there is more than one conflict per section.
    */
   private Vector<Integer> mInvalidIndexPrelim;

   /**
    * 'true' if 'mData' contains a valid sudoku solution, 'false' otherwise.
    */
   private boolean mIsGridValid;

   /**
    * Default constructor - constructs a new GridChecker instance.
    */
   public GridChecker()
   {
      // Use the 'Grid' default constructor to create a 'Grid' object.
      mGridObj = new Grid();
      mIsGridValid = true; // Assume the grid contains a valid sudoku solution.
      mInvalidIndex = new Vector<Integer>();
      mInvalidIndexPrelim = new Vector<Integer>();
   }

   /**
    * Constructs a new GridChecker instance.
    *
    * @param inData the grid object data.
    *
    * Sets all data members to their default value.
    * The parameter is used as a parameter for the 'Grid' constructor.
    */
   public GridChecker(byte[] inData)
   {
      mData = new byte[inData.length];
      System.arraycopy(inData, 0, mData, 0, inData.length);

      // Cast to integer should never be problematic because 'mData' should
      //    have n^2 elements (n = some positive integer).
      mSize = (int) Math.sqrt(mData.length);

      // Cast to integer should never be problematic because 'mSize' should
      //    equal n^2 (n = some positive integer).
      // An exception to this is if mSize = 2.
      //    In this case 'mSubgridSize' will still equal 1
      //    which is the desired outcome.
      mSubgridSize = (int) Math.sqrt(mSize);

      // Use parameterized constructor to create a 'Grid' object.
      mGridObj = new Grid(mData, mSize);
      mInvalidIndex = new Vector<Integer>();
      mInvalidIndexPrelim = new Vector<Integer>();
      mIsGridValid = true; // Assume the grid contains a valid sudoku solution.
   }

   /**
    * Insures the parameter contains no duplicate entries.
    *
    * @param inData the grid object data.
    */
   public void checkSection(byte[] inData)
   {
      for (int index1 = 0; index1 < inData.length; index1++)
      {
         for (int index2 = (index1 + 1); index2 < inData.length; index2++)
         {
            // Entry not valid.
            if (Math.abs(inData[index1]) == Math.abs(inData[index2]))
            {
               if (inData[index1] != 0)
               {
                  setIsGridValid(false);

                  if (inData[index1] < inData[index2])
                  {
                     int temp = index2;
                     index2 = index1;
                     index1 = temp;
                  }

                  mInvalidIndexPrelim.add(index1);
                  mInvalidIndexPrelim.add(index2);
               }
            }
         }
      }
   }

   /**
    * @return the grid object.
    */
   public Grid getGridObj()
   {
      return mGridObj;
   }

   /**
    * @return the size of the grid.
    */
   public int getSize()
   {
      return mSize;
   }

   /**
    * @return the size of a subgrid.
    */
   public int getSubgridSize()
   {
      return mSubgridSize;
   }

   /**
    * @return 'true' if the grid is valid, 'false'otherwise.
    */
   public boolean getIsGridValid()
   {
      return mIsGridValid;
   }

   /**
    * Checks all rows, columns, and subgrids of the grid object until an
    *    invalid entry is encountered.
    *
    * @return determines if grid contains a valid sudoku entry ('true')
    */
   public boolean checkGrid()
   {
      preliminaryGridCheck();
      checkAllRows();
      checkAllColumns();
      checkAllSubgrids();
      formatIndexes();

      return getIsGridValid();
   }

   /**
    * Makes the indexes negative or positive, relative to whether the
    * box at the specified index should be marked incorrect or not.
    * Negative values will not be marked as incorrect because the player
    * can't see the conflicting value.
    */
   public void formatIndexes()
   {
      int i = 0;

      for (int current : mInvalidIndex)
      {
         if (mData[(current)] < 0)
         {
            mInvalidIndex.set(i, -1);
         }

         i++;
      }
   }

   /**
    * Get Invalid Indexes
    *
    * @return An integer vector with all of the indexes where
    *  there exists a conflict in the grid.
    */
   public Vector<Integer> getInvalidIndexes()
   {
      return (mInvalidIndex);
   }

   /**
    * Set Invalid Index
    *
    * @param inVector Can change the invalid index vector to
    * this paramater vector.
    */
   public void setInvalidIndex(Vector<Integer> inVector)
   {
      mInvalidIndex.clear();

      for (int current : inVector)
      {
         mInvalidIndex.add((current));
      }
   }

   /**
    * Sets a data member equal to parameter.
    *
    * @param setToThis 'true' if the grid contains a valid solution.
    */
   public void setIsGridValid(boolean setToThis)
   {
      mIsGridValid = setToThis;
   }

   /**
    * Insures that the grid is considered valid only if all its grid entries
    *    are within a valid range.
    * The valid range is -'mSize' to +'mSize' (excluding 0).
    */
   public void preliminaryGridCheck()
   {
      if (getSize() > 0) // Check each index in the grid.
      {
         for (int index = 0; index < mData.length; index++)
         {
            // If an entry is outside the valid range...
            if ((Math.abs(mData[index])) > getSize())
            {
               setIsGridValid(false);

               // Only setting 'mInvalidIndex' signifies that this specific
               //    check caused the grid to be considered invalid.
               mInvalidIndex.add(index);
            }
         }
      }
   }

   /**
    * Check all rows of the grid to find a possible invalid sudoku entry.
    */
   public void checkAllRows()
   {
      int rowIndex = 0;
      int rowCount = 0;

      while (true)
      {
         checkSection(getGridObj().getRow(rowCount));
         rowCount++;

         for (int current : mInvalidIndexPrelim)
         {
            int offset = ((rowCount - 1) * getSize());
            mInvalidIndex.add((current) + offset);
         }

         mInvalidIndexPrelim.clear();

         if (rowCount == getSize())
         {
            break; // Only check each row once.
         }
      }
   }

   /**
    * Check all columns of grid to find a possible invalid sudoku entry.
    */
   public void checkAllColumns()
   {
      int columnIndex = 0;

      while (true)
      {
         checkSection(getGridObj().getColumn(columnIndex));
         columnIndex++;

         for (int current : mInvalidIndexPrelim)
         {
            int offset = (columnIndex - 1);
            mInvalidIndex.add((((current) * getSize()) + offset));
         }

         mInvalidIndexPrelim.clear();

         if (columnIndex == getSize())
         {
            break; // Only check each column once.
         }
      }
   }

   /**
    * Check all subgrids of grid to find a possible invalid sudoku entry.
    */
   public void checkAllSubgrids()
   {
      int subgridX = 0;
      int subgridY = 0;
      int subgridCount = 0;

      while (true)
      {
         checkSection(getGridObj().getSubGrid(subgridX, subgridY));
         subgridX += getSubgridSize();
         subgridCount++;

         if ((subgridCount % getSubgridSize()) == 0)
         {
            subgridX = 0;
            subgridY += getSubgridSize();
         }

         for (int current : mInvalidIndexPrelim)
         {
            int offsetX;
            int offsetY;
            int totalOffset;
            int subGridY = subgridY;
            int subGridX = subgridX;

            if ((subgridCount % getSubgridSize()) == 0)
            {
               offsetX = (getSize() - getSubgridSize());
               subGridY = (subGridY - getSubgridSize());
            }
            else
            {
               offsetX = (subGridX - getSubgridSize());
            }

            offsetY = subGridY * getSize();
            totalOffset = offsetX + offsetY;
            offsetX = ((current) % getSubgridSize());
            offsetY = (((current) / getSubgridSize()) * getSize());
            mInvalidIndex.add(offsetX + offsetY + totalOffset);
         }

         mInvalidIndexPrelim.clear();

         if (subgridCount == getSize())
         {
            break; // Only check each subgrid once.
         }
      }
   }
}

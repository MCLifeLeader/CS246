// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/al/GridChecker.java,v 1.1 2006/06/17 20:03:21 rec26 Exp $
package sudoku.al;
import java.lang.Math;

/**
 * GridChecker.java
 * @author $AlgorithmTeam - Richard Cochran$
 * @version $Revision: 1.1 $
 * This class creates a GridChecker object.
 * Its main purpose is to determine if a sudoku grid contains a valid solution.
 * If an invalid entry is encountered in a grid the remainder of the grid is 
 *    not checked.
 */
public class GridChecker
{
   /**
    * DOCUMENT ME!
    */
   protected Grid gridObj;

   /**
    * DOCUMENT ME!
    */
   protected byte[] mData;

   /**
    * The number of columns, rows, and subgrids in the grid.
    */
   protected int mSize;

   /**
    * The number of columns/rows in a subgrid.
    */
   protected int subgridSize;

   /**
    * Contains an array index that is the first of two invalid sudoku entries.
    * 'invalidIndex2' holds the other aray index.
    * If there are no invalid sudoku entries then it contains -1.
    */
   protected int invalidIndex1;

   /**
    * Contains an array index that is the first of two invalid sudoku entries.
    * 'invalidIndex1' holds the other aray index.
    * If there are no invalid sudoku entries then it contains -1.
    */
   protected int invalidIndex2;

   /**
    * 'true' if 'mData' contains a valid sudoku solution, 'false' otherwise.
    */
   protected boolean isGridValid;

   /**
    * Creates a new GridChecker object.
    * Parameter: byte[]
    * Sets all data members to their default value.
    * The parameter is used to create a grid object.
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
      //    In this case 'subgridSize' will still equal 1
      //    which is the desired outcome.
      subgridSize = (int) Math.sqrt(mSize);

      gridObj = new Grid(mData, mSize);

      invalidIndex1 = -1; // Assume no invalid pair exists.
      invalidIndex2 = -1;
      isGridValid = true; // Assume 'mData' contains a valid sudoku solution.
   }

   /**
    * Parameter: byte[]
    * Insures the parameter contains no duplicate entries.
    * This method is the workhorse of the class.
    */
   void checkSection(byte[] inData)
   {
      for (int index1 = 0; index1 < inData.length; index1++)
      {
         for (int index2 = (index1 + 1); index2 < inData.length; index2++)
         {
            if (inData[index1] == inData[index2]) // Entry not valid.
            {
               setIsGridValid(false);
               setInvalidIndex1(index1);
               setInvalidIndex2(index2);

               break; // No need to check the rest of the array.
            }
         }

         if (getIsGridValid() == false) // Exit both loops.
         {
            break;
         }
      }
   }

   /**
    * Returns a data member of type 'Grid'.
    */
   Grid getGridObj()
   {
      return gridObj;
   }

   /**
    * Returns a data member of type 'int'.
    */
   int getSize()
   {
      return mSize;
   }

   /**
    * Returns a data member of type 'int'.
    */
   int getSubgridSize()
   {
      return subgridSize;
   }

   /**
    * Returns a data member of type 'int'.
    */
   int getInvalidIndex1()
   {
      return invalidIndex1;
   }

   /**
    * Returns a data member of type 'int'.
    */
   int getInvalidIndex2()
   {
      return invalidIndex2;
   }

   /**
    * Returns a data member of type 'boolean'.
    */
   boolean getIsGridValid()
   {
      return isGridValid;
   }
   
   /**
    * Returns a 'boolean'.
    * Returns 'true' if the grid contains a valid sudoku solution, 
    *    returns 'false' otherwise.
    * Checks all rows, columns, and subgrids of the grid object until an
    *    invalid entry is encountered.
    * This mehthod is the backbone of the class.
    */
   boolean checkGrid()
   {
      checkAllRows( );

      // Only check columns if check rows did not find an invalid sudoku entry.
      if (getIsGridValid())
      {
         checkAllColumns( );
      }

      // Only check subgrids if check rows did not find an invalid sudoku entry.
      if (getIsGridValid())
      {
         checkAllSubgrids();
      }
      return getIsGridValid();
   }

   /**
    * Sets a data member equal to parameter.
    * Parameter: 'int'
    */
   void setInvalidIndex1(int setToThis)
   {
      invalidIndex1 = setToThis;
   }

   /**
    * Sets a data member equal to parameter.
    * Parameter: 'int'
    */
   void setInvalidIndex2(int setToThis)
   {
      invalidIndex2 = setToThis;
   }

   /**
    * Sets a data member equal to parameter.
    * Parameter: 'boolean'
    */
   void setIsGridValid(boolean setToThis)
   {
      isGridValid = setToThis;
   }


   /**
    * Check all rows of the grid to find a possible invalid sudoku entry.
    */
   void checkAllRows()
   {
      int rowIndex = 0;
      int rowCount = 0;

      do
      {
         checkSection(getGridObj().getRow(rowCount));
         //rowIndex += getSize( );
         rowCount++;

         if (rowCount == getSize())
         {
            break; // Only check each row once.
         }
      }
      while (getIsGridValid() == true);

      // Calculate the indexes of both invalid entries.
      if (getIsGridValid() == false)
      {
         int offset = ((rowCount - 1) * getSize());
         setInvalidIndex1((getInvalidIndex1() + offset));
         setInvalidIndex2((getInvalidIndex2() + offset));
      }
   }

   /**
    * Check all columns of grid to find a possible invalid sudoku entry.
    */
   void checkAllColumns()
   {
      int columnIndex = 0;

      do
      {
         checkSection(getGridObj().getColumn(columnIndex));
         columnIndex++;

         if (columnIndex == getSize())
         {
            break; // Only check each column once.
         }
      }
      while (getIsGridValid() == true);

      // Calculate the indexes of both invalid entries.
      if (getIsGridValid() == false)
      {
         int offset = (columnIndex - 1);
         setInvalidIndex1(((getInvalidIndex1() * getSize()) + offset));
         setInvalidIndex2(((getInvalidIndex2() * getSize()) + offset));
      }
   }

   /**
    * Check all subgrids of grid to find a possible invalid sudoku entry.
    */
   void checkAllSubgrids()
   {
      int subgridX = 0;
      int subgridY = 0;
      int subgridCount = 0;

      do
      {
         checkSection(getGridObj().getSubGrid(subgridX, subgridY));
         subgridX += getSubgridSize();
         subgridCount++;

         if ((subgridCount % getSubgridSize()) == 0)
         {
            subgridX = 0;
            subgridY += getSubgridSize();
         }

         if (subgridCount == getSize())
         {
            break; // Only check each subgrid once.
         }
      }
      while (getIsGridValid() == true);

      // Calculate the indexes of both invalid entries.
      if (getIsGridValid() == false)
      {
         int offsetX;
         int offsetY;
         int totalOffset;

         if ((subgridCount % getSubgridSize()) == 0)
         {
            offsetX = (getSize() - getSubgridSize());
            subgridY = (subgridY - getSubgridSize());
         }
         else
         {
            offsetX = (subgridX - getSubgridSize());
         }

         offsetY = subgridY * getSize();
         totalOffset = offsetX + offsetY;
         offsetX = (getInvalidIndex1() % getSubgridSize());
         offsetY = ((getInvalidIndex1() / getSubgridSize()) * getSize());
         setInvalidIndex1(offsetX + offsetY + totalOffset);
         offsetX = (getInvalidIndex2() % getSubgridSize());
         offsetY = ((getInvalidIndex2() / getSubgridSize()) * getSize());
         setInvalidIndex2(offsetX + offsetY + totalOffset);
      }
   }

   /**
    * 'main' method
    */
   public static void main(String[] args)
   {
      
   }
}


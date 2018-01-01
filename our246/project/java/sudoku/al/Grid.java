// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/al/Grid.java,v 1.26 2006/06/26 19:24:46 mbcarey Exp $
package sudoku.al;

import java.io.*;

/**
 * Grid Class
 *
 * @author $Algorithms Team$
 * @version $Revision: 1.26 $
 */
public class Grid
   implements Serializable
{
   /**
    * Byte Array
    */
   private byte[] mData;

   /**
    * Grid height/length
    */
   private int mSize;

   /**
    * Creates a new Grid object.
    */
   public Grid()
   {
      mData = new byte[81];
      mSize = 9;
   }

   /**
    * Creates a new Grid object.
    *
    * @param inData
    */
   public Grid(byte[] inData)
   {
      this(inData, (int) Math.sqrt(inData.length));
   }

   /**
    * Check to see if the grid is empty
    *
    * @return true if the grid is empty
    */
   public boolean empty()
   {
      if (mData[0] == 0)
      {
         return true;
      }

      return false;
   }

   /**
    * Full Method
    *
    * @return A boolean true if the grid is full, false if it isn't.
    */
   public boolean full()
   {
      for (byte number : mData)
      {
         if (number < 0)
         {
            return (false);
         }
      }

      return (true);
   }

   /**
    * Creates a new Grid object.
    *
    * @param inData
    * @param inSize taks a height size
    */
   public Grid(byte[] inData, int inSize)
   {
      mData = new byte[inData.length];
      System.arraycopy(inData, 0, mData, 0, inData.length);
      mSize = inSize;
   }

   /**
    * Reset Grid Method
    *    Sets the byte array for this grid to the byte array of another grid
    * @param original
    */
   public void resetGrid(Grid original)
   {
      mData = new byte[original.mData.length];
      System.arraycopy(original.mData, 0, mData, 0, original.mData.length);
   }

   /**
    * To Array index
    *    Changes the x, y coordinates to an index of the array
    *
    * @param x left to right coordinate
    * @param y top to bottom coordinate
    *
    * @return array index
    */
   public int toArrayIndex(int x, int y)
   {
      int position = 0;
      position = (y * mSize) + x;

      return position;
   }

   /**
    * Equals Method
    *
    * @param inGrid The Grid that is being tested against
    * the Grid calling this method.
    *
    * @return boolean true if they are equal, false if they are not equal
    */
   public boolean equals(Grid inGrid)
   {
      if (mData.length != inGrid.mData.length)
      {
         return (false);
      }
      else
      {
         for (int i = 0; i < mData.length; i++)
         {
            if (mData[i] != inGrid.mData[i])
            {
               return (false);
            }
         }
      }

      return true;
   }

   /**
    * Get Cell
    *
    * @param x coordinate
    * @param y coordinate
    *
    * @return the value in the array at the specified coordinates
    */
   public byte getCell(int x, int y)
   {
      return (mData[toArrayIndex(x, y)]);
   }

   /**
    * Set Cell
    *
    * @param x coordinate
    * @param y coordinate
    * @param inCell the value to be entered into the cell
    */
   public void setCell(int x, int y, byte inCell)
   {
      mData[toArrayIndex(x, y)] = inCell;
   }

   /**
    * Get Sub Grid
    *    Returns the sub grid in which the x and y coordinates are located.
    *    The coordinates can be located anywhere in the subgrid.
    * @param x coordinate
    * @param y coordinate
    *
    * @return a byte array of the subgrid
    */
   public byte[] getSubGrid(int x, int y)
   {
      byte[] subGrid = new byte[mSize];
      int subSize = ((int) Math.sqrt(mSize));

      while ((x % subSize) != 0)
      {
         x--;
      }

      while ((y % subSize) != 0)
      {
         y--;
      }

      for (int i = 0; i < mSize; i++)
      {
         subGrid[i] = mData[toArrayIndex(x, y)];
         x++;

         if ((x % subSize) == 0)
         {
            x = x - subSize;
            y++;
         }
      }

      return subGrid;
   }

   /**
    * Set Sub Grid
    * The coordinates can be located at any point in the sub grid
    * @param x coordinate
    * @param y coordinate
    * @param inData Takes a byte array and sets the specified subgrid's values
    *               to that sub grid at the specified coordinates
    */
   public void setSubGrid(int x, int y, byte[] inData)
   {
      int subSize = ((int) Math.sqrt(mSize));

      while ((x % subSize) != 0)
      {
         x--;
      }

      while ((y % subSize) != 0)
      {
         y--;
      }

      for (int i = 0; i < mSize; i++)
      {
         mData[toArrayIndex(x, y)] = inData[i];
         x++;

         if ((x % subSize) == 0)
         {
            x = x - subSize;
            y++;
         }
      }
   }

   /**
    * Get Row
    *
    * @param y the rows coordinate
    * @return a byte array of the entire row
    */
   public byte[] getRow(int y)
   {
      byte[] row = new byte[mSize];
      int position = toArrayIndex(0, y);

      for (int i = 0; i < mSize; i++)
      {
         row[i] = mData[position + i];
      }

      return row;
   }

   /**
    * Set Row
    *
    * @param y the rows coordinate
    * @param inRow a byte array which will replace the specified row
    */
   public void setRow(int y, byte[] inRow)
   {
      int position = toArrayIndex(0, y);

      for (int i = 0; i < mSize; i++)
      {
         mData[position + i] = inRow[i];
      }
   }

   /**
    * Get Column
    *
    * @param x coordinate
    *
    * @return a byte array of the values in the column
    */
   public byte[] getColumn(int x)
   {
      byte[] column = new byte[mSize];
      int position = (x % mSize);

      for (int i = 0; i < mSize; i++)
      {
         column[i] = mData[position];
         position = position + mSize;
      }

      return column;
   }

   /**
    * Set Column
    *
    * @param x coordinate
    * @param inColumn a byte array which will replace the specified column
    */
   public void setColumn(int x, byte[] inColumn)
   {
      int position = (x % mSize);

      for (int i = 0; i < mSize; i++)
      {
         mData[position] = inColumn[i];
         position = position + mSize;
      }
   }

   /**
    * Get byte array
    *
    * @return a byte array of entire grid
    */
   public byte[] getArray()
   {
      return (mData);
   }
}

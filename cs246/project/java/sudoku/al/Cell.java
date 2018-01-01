// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/al/Cell.java,v 1.2 2006/06/16 23:09:12 ricjames Exp $
package sudoku.al;
/**
 * Cell Class
 *
 * @author $Algorithm Team$
 * @version $Revision: 1.2 $
 */
public class Cell
{
   /**
    * Cell's x coordinate
    */
   int mXCoord;

   /**
    * Cell's y coordinate
    */
   int mYCoord;

   /**
    * Cell's Value
    */
   String mValue;

   /**
    * Creates a new Cell object.
    */
   Cell()
   {
      mXCoord = 0;
      mYCoord = 0;
      mValue = "0";
   }

   /**
    * Get x method
    *
    * @return Cell's x coordinate
    */
   int getX()
   {
      return mXCoord;
   }

   /**
    * Set x method
    *
    * @param xCoordinate sets the cell's x coordinate
    */
   void setX(int xCoordinate)
   {
      mXCoord = xCoordinate;
   }

   /**
    * Get y method
    *
    * @return Cell's y coordinate
    */
   int getY()
   {
      return mYCoord;
   }

   /**
    * Set y method
    *
    * @param yCoordinate sets cell's y coordinate
    */
   void setY(int yCoordinate)
   {
      mYCoord = yCoordinate;
   }

   /**
    * Gets value method
    *
    * @return Cell's value
    */
   String getValue()
   {
      return mValue;
   }

   /**
    * Set value method
    *
    * @param inValue sets the cell's value
    */
   void setValue(String inValue)
   {
      mValue = inValue;
   }

   /**
    * Set x, y, and value
    *
    * @param xCoordinate sets the cell's x
    * @param yCoordinate sets the cell's y
    * @param inValue sets the cell's value
    */
   void setAll(int xCoordinate, int yCoordinate, String inValue)
   {
      mXCoord = xCoordinate;
      mYCoord = yCoordinate;
      mValue = inValue;
   }
}

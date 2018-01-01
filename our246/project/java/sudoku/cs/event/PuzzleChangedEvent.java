// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/event/PuzzleChangedEvent.java,v 1.5 2006/06/24 17:56:40 manxman Exp $
package sudoku.cs.event;

import sudoku.al.*;

import sudoku.si.*;

import java.rmi.*;
import java.rmi.server.*;

import java.util.*;

/**
 * An event that is shared with the client so that the puzzle can be updated
 * when it is changed.
 *
 * @author $Randall Booth, Rick Neff$
 * @version $Revision: 1.5 $
 */
public class PuzzleChangedEvent
{
   /**
    * The X coordinate of the change.
    */
   private int mX;

   /**
    * The Y coordinate of the change.
    */
   private int mY;

   /**
    * The previous value of the puzzle.
    */
   private String mPrevValue;

   /**
    * The current value of the puzzle.
    */
   private String mCurrValue;

   /**
    * Creates a new PuzzleChangedEvent object.
    */
   public PuzzleChangedEvent(int x, int y, String prev, String curr)
   {
      mX = x;
      mY = y;
      mPrevValue = prev;
      mCurrValue = curr;
   }

   /**
    * Gets the X coordinate of the change.
    *
    * @return the X coordinate of the change.
    */
   public int getX()
   {
      return mX;
   }

   /**
    * Gets the Y coordinate of the change.
    *
    * @return the Y coordinate of the change.
    */
   public int getY()
   {
      return mY;
   }

   /**
    * Gets the previous value of the puzzle.
    *
    * @return the previous value of the puzzle.
    */
   public String getPrevValue()
   {
      return mPrevValue;
   }

   /**
    * Gets the current value of the puzzle.
    *
    * @return the current value of the puzzle.
    */
   public String getCurrValue()
   {
      return mCurrValue;
   }

   /**
    * Returns a readable representation of the event.
    *
    * @return a readable representation of the event.
    */
   public String toString()
   {
      return ("[@(" + mX + "," + mY + ") was '" + mPrevValue + "' now '" +
      mCurrValue + "']");
   }
}

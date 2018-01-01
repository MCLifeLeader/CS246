// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/SudokuTimer.java,v 1.4 2006/06/18 01:17:49 manxman Exp $
package sudoku.cs;

import java.util.Date;

/**
 * Keeps track of the time of the Game.
 *
 * @author $author$
 * @version $Revision: 1.4 $
 */
public class SudokuTimer
{
   /**
    * Used to get the current time.
    */
   Date now = new Date();

   /**
    * Keeps track of the total time of the game.
    */
   private long mTotalTime = 0;

   /**
    * Time when the timer was started.
    */
   private long mStartTime = 0;

   /**
    * Creates a new SudokuTimer object.
    */
   public SudokuTimer()
   {
   }

   /**
    * Gets the total time of the games since the last break in the timer.
    *
    * @return The total time of the game.
    */
   public String getTotalTime()
   {
      mTotalTime = now.getTime() - mStartTime;

      return toString();
   }

   /**
    * Stops the clock adding the time to mTotalTime.
    */
   public void pause()
   {
      mTotalTime += (now.getTime() - mStartTime);
   }

   /**
    * Starts running the clock again.
    */
   public void unpause()
   {
      mStartTime = now.getTime();
   }

   /**
    * Starts running the clock and sets the total time to zero.
    */
   public void start()
   {
      mTotalTime = 0;
      mStartTime = now.getTime();
   }

   /**
    * Stops running the clock and returns the total time.
    *
    * @return Retuns mTotalTime in the form of a string.
    */
   public String stop()
   {
      mTotalTime += (now.getTime() - mStartTime);

      return toString();
   }

   /**
    * Returns a formatted string showing the total time.
    *
    * @return  Formatted time string.
    */
   public String toString()
   {
      long temp = mTotalTime;

      String time = String.format("%03d", (temp % 1000));
      temp /= 1000;

      time = String.format("%02d", (temp % 60)) + "." + time;
      temp /= 60;

      time = String.format("%02d", (temp % 60)) + ":" + time;
      temp /= 60;

      if (temp > 0)
      {
         time = temp + ":" + time;
      }

      return time;
   }
}

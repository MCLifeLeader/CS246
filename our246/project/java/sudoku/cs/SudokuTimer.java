// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/SudokuTimer.java,v 1.17 2006/06/24 17:56:12 manxman Exp $
package sudoku.cs;

import sudoku.si.*;

import java.io.*;

/**
 * Keeps track of the elapsed time.
 *
 * @author $Randall Booth, James Comish, Michael Ricks$
 * @version $Revision: 1.17 $
 */
public class SudokuTimer
   implements Serializable
{
   /**
    * The total elapsed time.
    */
   private long mElapsedTime;

   /**
    * The most recent start time.
    * It is reset to the current time whenever the <code>start</code>
    * or <code>getElapsedTime</code> methods are called.
    */
   private long mStartTime;

   /**
    * Used to keep tabs on what the timer is doing, i.e. whether it is
    * running or not.
    */
   private boolean mIsRunning;

   /**
    * Creates a new SudokuTimer object.
    */
   public SudokuTimer()
   {
      reset();
   }

   /**
    * Starts the timer
    */
   public void start()
   {
      if (! mIsRunning)
      {
         mIsRunning = true;
         mStartTime = System.currentTimeMillis();
      }
   }

   /**
    * Stops the timer
    */
   public void stop()
   {
      if (mIsRunning)
      {
         mIsRunning = false;

         long endTime = System.currentTimeMillis();
         mElapsedTime = (mElapsedTime + endTime) - mStartTime;
      }
   }

   /**
    * Returns the current elapsed time.
    * If the timer is running when called, this method
    * Calculates the elapsed time at the moment
    * it was called. Otherwise it just returns the elapsed
    * time from when the timer was stopped.
    *
    * @return long The total elapsed time
    */
   public long getElapsedTime()
   {
      if (mIsRunning)
      {
         long endTime = System.currentTimeMillis();
         mElapsedTime = (mElapsedTime + endTime) - mStartTime;
         mStartTime = endTime;
      }

      return mElapsedTime;
   }

   /**
    * Accesses isRunning.
    *
    * @return Whether the timer is running.
    */
   public boolean isRunning()
   {
      return mIsRunning;
   }

   /**
    * Clears the elapsed time total and makes sure that the timer is stopped.
    */
   public void reset()
   {
      mElapsedTime = 0;
      mIsRunning = false;
   }

   /**
    * Converts the elapsed time into a string format.
    *
    * @return String The elapsed time in the hh:mm:ss.mili format
    */
   public String toString()
   {
      long temp = getElapsedTime();

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

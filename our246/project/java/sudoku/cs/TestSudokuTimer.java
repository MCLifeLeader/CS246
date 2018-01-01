// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/TestSudokuTimer.java,v 1.3 2006/06/24 17:56:12 manxman Exp $
package sudoku.cs;

import junit.framework.*;

/**
 * Test the SudokuTimer class.
 *
 * @author $James Comish$
 * @version $Revision: 1.3 $
 */
public class TestSudokuTimer
   extends TestCase
{
   /**
    * The timer.
    */
   private SudokuTimer mTimer;

   /**
    * Sets up the test.
    */
   public void setUp()
   {
      mTimer = new SudokuTimer();
   }

   /**
    * Test the commands for the SudokuTimer
    */
   public void testCommands()
   {
      mTimer.stop();
      assertFalse(mTimer.isRunning());

      mTimer.start();
      assertTrue(mTimer.isRunning());

      mTimer.stop();
      assertFalse(mTimer.isRunning());

      mTimer.reset();
      assertFalse(mTimer.isRunning());
      assertEquals(mTimer.getElapsedTime(), 0);

      assertEquals(mTimer.toString(), "00:00.000");
   }

   /**
    * Check to see if the time comes out properly.
    */
   public void testTime()
   {
      SudokuTimer test = new SudokuTimer();

      mTimer.start();
      test.start();

      for (int i = 0; i < 1000000000; i++)
      {
      }

      assertTrue(mTimer.toString().equals(test.toString()));

      mTimer.stop();

      for (int i = 0; i < 1000000000; i++)
      {
      }

      assertFalse(mTimer.toString().equals(test.toString()));

      test.stop();
      mTimer.start();

      for (int i = 0; i < 1000000000; i++)
      {
      }

      mTimer.stop();

      //checks to the nearest second
      assertEquals(mTimer.getElapsedTime() / 1000,
                   test.getElapsedTime() / 1000);
   }
}

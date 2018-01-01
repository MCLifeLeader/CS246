// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/TestStats.java,v 1.2 2006/06/24 17:56:12 manxman Exp $
package sudoku.cs;

import junit.framework.*;

import sudoku.al.*;

/**
 * Test the Stats class
 *
 * @author $Michael Ricks$
 * @version $Revision: 1.2 $
 */
public class TestStats
   extends TestCase
{
   /**
    * The Stats
    */
   private Stats mStats;

   /**
    * The Grid
    */
   private Grid mGrid;

   /**
    * Creates a new TestStats object.
    */
   public TestStats()
   {
      //intentionally empty
   }

   /**
    * Sets up the test case.
    */
   public void setUp()
   {
      mStats = new Stats();
      mGrid = new Grid(new byte[]
            {
               -7, 1, 2, 4, 6, 3, 1, 8, 5,
               4, -5, 8, -2, 1, 1, 6, -3, 7,
               1, 6, 3, 5, 7, -8, 4, 1, 2,
               2, 3, 4, 1, 8, 5, 7, 6, 1,
               5, 8, -7, 6, 1, 4, 3, -2, 1,
               -6, 1, 1, 7, 3, -2, 5, 4, 8,
               8, 4, 5, -1, 2, 6, -1, 7, 3,
               3, -1, 6, 8, 5, 7, 2, -1, 4,
               7, 2, 1, 3, 4, 1, 8, 5, -6,
            });
   }

   /**
    * Test to see if squaresLeft actually returns the squares that are left.
    */
   public void testSquaresLeft()
   {
      mStats.updateSquaresLeft(mGrid);
      assertEquals(mStats.getSquaresLeft(), 14);
   }

   /**
    * Test to see if NumberOfGuesses actually gets total guesses.
    */
   public void testNumberOfGuesses()
   {
      mStats.updateNumberOfGuesses();
      mStats.updateNumberOfGuesses();
      assertEquals(mStats.getNumberOfGuesses(), 2);
   }
}

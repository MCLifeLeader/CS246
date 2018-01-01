// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/TestPuzzle.java,v 1.3 2006/06/24 17:56:12 manxman Exp $
package sudoku.cs;

import junit.framework.*;

import sudoku.al.*;

/**
 * JUnit test class that tests the Puzzle class.
 *
 * @author $Randall Booth$
 * @version $Revision: 1.3 $
 */
public class TestPuzzle
   extends TestCase
{
   /**
    * A grid to hold a test board.
    */
   private Grid mBoard;

   /**
    * Creates a new TestPuzzle object.
    */
   public TestPuzzle()
   {
      //intentionally empty
   }

   /**
    * Sets up the test case.
    */
   public void setUp()
   {
      mBoard = new Grid(new byte[]
            {
               11, 17, 12, 14, 16, 13, 10, 18, 15, 14, 15, 18, 12, 11, 10, 16,
               13, 17, 10, 16, 13, 15, 17, 18, 14, 11, 12, 12, 13, 14, 11, 18,
               15, 17, 16, 10, 15, 18, 17, 16, 10, 14, 13, 12, 11, 16, 10, 11,
               17, 13, 12, 15, 14, 18, 18, 14, 15, 10, 12, 16, 11, 17, 13, 13,
               11, 16, 18, 15, 17, 12, 10, 14, 17, 12, 10, 13, 14, 11, 18, 15,
               16,
            });
   }

   /**
    * Test to see it the constructor and toString work.
    */
   public void testPuzzleConstructor()
   {
      Puzzle aPuzzle = new Puzzle();
      assertTrue(aPuzzle.getBoard() != null);
   }
}

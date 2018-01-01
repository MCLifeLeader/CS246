// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/al/TestGridChecker.java,v 1.4 2006/06/27 04:34:43 rec26 Exp $
package sudoku.al;

import junit.framework.*;

import sudoku.si.*;

import java.util.*;

/**
 * Tests the GridChecker class.
 */
public class TestGridChecker
   extends TestCase
{
   /**
    * Creates a new TestGridChecker object.
    */
   public TestGridChecker()
   {
      // intentionally left empty
   }

   /**
    * Initializes this test.
    */
   public void setUp()
   {
   }

   /**
    * Tears down this test.
    */
   public void tearDown()
   {
   }

   /**
    * Tests the checkGrid method.
    */
   public void testCheckGrid()
   {
      Collection<Puzzle> puzzles = new ArrayList<Puzzle>();

      puzzles.add(new Puzzle());

      puzzles = (Collection<Puzzle>) Config.get("Puzzles", puzzles);

      for (Puzzle puzzle : puzzles)
      {
         assertTrue(new GridChecker(puzzle.getData()).checkGrid());
      }

      assertFalse(new GridChecker(
            new byte[]
            {
               11, 17, 12, 14, 16, 13, 10, 18, 15, 14, 15, 18, 12, 11, 10, 16,
               13, 17, 10, 16, 13, 15, 17, 18, 14, 11, 12, 12, 13, 14, 11, 18,
               15, 17, 16, 10, 15, 18, 17, 16, 10, 14, 13, 12, 11, 16, 10, 11,
               17, 13, 12, 15, 14, 18, 18, 14, 15, 10, 12, 16, 11, 17, 13, 13,
               11, 16, 18, 15, 17, 12, 10, 14, 17, 12, 10, 13, 14, 11, 18, 15,
               16,
            }).checkGrid());
   }
}

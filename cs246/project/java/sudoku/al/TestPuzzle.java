// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/al/TestPuzzle.java,v 1.1 2006/06/19 14:24:10 neffr Exp $

package sudoku.al;

import java.util.*;

import junit.framework.*;

import sudoku.si.*;

/**
 * Tests the Sudoku Puzzle class.
 */
public class TestPuzzle
   extends TestCase
{
   public TestPuzzle()
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
    * Tests the Puzzle constructor and gives output
    * suitable for inclusion in the sudoku.config file.
    */
   public void testPuzzleConstructor()
   {
      Collection<Puzzle> puzzles = new ArrayList<Puzzle>();
      puzzles.add(new Puzzle(new byte[]
         {
            -2,  1,  6, -5,  7,  8, -4,  3,  9,
            -4, -7, -3, -9,  6, -2, -1,  8, -5,
            9, -8, -5, -1,  4,  3, -6, -2, -7,
            5, -6,  4, -2, -8,  1,  7,  9, -3,
            -3,  2,  7, -4, -9, -5,  8,  6, -1,
            -1,  9,  8,  7, -3, -6,  2, -5,  4,
            -8, -3, -9,  6,  1, -7, -5, -4,  2,
            -7,  4, -2, -8,  5, -9, -3, -1, -6,
            6,  5, -1,  3,  2, -4,  9,  7, -8, 
         }, Puzzle.Level.Easy));

      puzzles.add(new Puzzle(new byte[]
         {
            -8, -6, 4, -9, -3, 7, 5, 1, 2,
            7, 2, 3, -1, -5, -4, -9, 6, -8,
            1, 9, -5, 8, -2, -6, -4, -3, 7,
            -3, -4, -8, -2, -1, -5, -7, -9, 6,
            2, 5, -7, 6, 4, 9, -3, 8, 1,
            9, -1, -6, -3, -7, -8, -2, -5, -4,
            6, -3, -1, -4, -9, 2, -8, 7, 5,
            -5, 8, -2, -7, -6, -3, 1, 4, 9,
            4, 7, 9, 5, -8, -1, 6, -2, -3,
         }, Puzzle.Level.Easy));

      puzzles.add(new Puzzle(new byte[]
         {
            -2, -8, -3, 5, 7, 4, 1, 9, -6,
            -5, 6, -9, 3, -2, -1, -7, -4, 8,
            -1, 7, -4, 6, -8, -9, 5, -2, -3,
            3, 4, 5, -2, -9, -6, 8, -7, -1,
            6, -9, 8, 7, -1, 5, 4, -3, 2,
            -7, -1, 2, -8, -4, -3, 6, 5, 9,
            -9, -5, 6, -1, -3, 7, -2, 8, -4,
            4, -2, -7, -9, -6, 8, -3, 1, -5,
            -8, 3, 1, 4, 5, 2, -9, -6, -7, 
         }, Puzzle.Level.Easy));

      assertEquals(puzzles.size(), 3);
      for (Puzzle puzzle : puzzles)
      {
         System.out.println(puzzle);
      }
      Config.set("Puzzles", puzzles);
      Config.show();
   }
}

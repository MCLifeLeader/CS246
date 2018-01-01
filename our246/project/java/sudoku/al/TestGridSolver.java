// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/al/TestGridSolver.java,v 1.2 2006/06/24 21:51:13 mbcarey Exp $
package sudoku.al;

import junit.framework.*;

import sudoku.si.*;

import java.util.*;

/**
 * Tests the ComputerSolve class.
 */
public class TestGridSolver
   extends TestCase
{
   /**
    * Creates a new TestComputerSolve object.
    */
   public TestGridSolver()
   {
   }

   /**
    * test if output is Vaild
    */
   public void testOutputIsVaild()
   {
      Collection<Puzzle> puzzles = new ArrayList<Puzzle>();

      puzzles = (Collection<Puzzle>) Config.get("Puzzles", puzzles);

      for (Puzzle puzzle : puzzles)
      {
         byte[] grid = puzzle.getData();
         GridSolver cs = new GridSolver(grid);
         cs.solveGrid();
         assertTrue(new GridChecker(cs.getDataSolution()).checkGrid());
      }
   }

   /**
    * see if the computer can handle blank boxes
    */
   public void testHandlesBlankBoxes()
   {
      byte[] grid = new byte[]
         {
            5, 4, 0, 0, 2, 0, 9, 6, 8, 0, 0, 2, 9, 8, 0, 0, 4, 0, 6, 9, 0, 0, 5,
            4, 2, 0, 0, 8, 0, 0, 0, 0, 9, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            7, 0, 1, 0, 0, 0, 0, 3, 0, 0, 1, 4, 3, 0, 0, 5, 9, 0, 5, 0, 0, 1, 2,
            3, 0, 0, 7, 3, 4, 0, 9, 0, 0, 8, 2
         };
      final GridSolver cs = new GridSolver(grid);
      Thread test = new Thread("BlankBoxes")
         {
            public void run()
            {
               cs.solveGrid();
            }
         };

      test.start();

      try
      {
         test.join(27000);
      }
      catch (Exception e)
      {
      }

      assertFalse(test.isAlive());
      assertTrue(new GridChecker(cs.getDataSolution()).checkGrid());
   }

   /**
    *
    */
   public void testHandleUnsolveablePuzzle()
   {
      byte[] grid = new byte[]
         {
            1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 6, 7, 8, 9, 8, 7, 6, 2, 3, 7, -1, -2,
            -3, -2, -1, 7, 3, 4, 8, -2, -4, -5, -4, -2, 8, 4, 5, 9, -3, -5, -6,
            -5, -3, 9, 5, 4, 8, -2, -4, -5, -4, -2, 8, 4, 3, 7, -1, -2, -3, -2,
            -1, 7, 3, 2, 6, 7, 8, 9, 8, 7, 6, 2, 1, 2, 3, 4, 5, 4, 3, 2, 1
         };
      final GridSolver cs = new GridSolver(grid);
      Thread test = new Thread("Unsolveable")
         {
            public void run()
            {
               cs.solveGrid();
            }
         };

      test.start();

      try
      {
         test.join(27000);
      }
      catch (Exception e)
      {
      }

      assertFalse(test.isAlive());
   }
}

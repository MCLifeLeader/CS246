// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/al/TestComputerSolve.java,v 1.3 2006/06/24 21:51:13 mbcarey Exp $
package sudoku.al;

import junit.framework.*;

import sudoku.si.*;

import java.util.*;

/**
 * Tests the ComputerSolve class.
 */
public class TestComputerSolve
   extends TestCase
{
   /**
    * Creates a new TestComputerSolve object.
    */
   public TestComputerSolve()
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
         Grid grid = new Grid(puzzle.getData());
         new ComputerSolve(grid);
         assertTrue(new GridChecker(grid.getArray()).checkGrid());
      }
   }

   /**
    * see if the computer can handle blank boxes
    */
   public void testHandlesBlankBoxes()
   {
      Thread test = new Thread("BlankBoxes")
         {
            public void run()
            {
               Grid grid = new Grid(new byte[]
                     {
                        5, 4, 0, 0, 2, 0, 9, 6, 8, 0, 0, 2, 9, 8, 0, 0, 4, 0, 6,
                        9, 0, 0, 5, 4, 2, 0, 0, 8, 0, 0, 0, 0, 9, 0, 1, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 1, 0, 0, 0, 0, 3, 0, 0, 1,
                        4, 3, 0, 0, 5, 9, 0, 5, 0, 0, 1, 2, 3, 0, 0, 7, 3, 4, 0,
                        9, 0, 0, 8, 2
                     });
               new ComputerSolve(grid, "BlankBoxes");
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

      System.out.println(test.isAlive());
      assertFalse(test.isAlive());
   }

   /**
    * test case for unsolveable puzzle
    */
   public void testHandleUnsolveablePuzzle()
   {
      Thread test = new Thread("Unsolveable")
         {
            public void run()
            {
               Grid grid = new Grid(new byte[]
                     {
                        1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 6, 7, 8, 9, 8, 7, 6, 2, 3,
                        7, -1, -2, -3, -2, -1, 7, 3, 4, 8, -2, -4, -5, -4, -2, 8,
                        4, 5, 9, -3, -5, -6, -5, -3, 9, 5, 4, 8, -2, -4, -5, -4,
                        -2, 8, 4, 3, 7, -1, -2, -3, -2, -1, 7, 3, 2, 6, 7, 8, 9,
                        8, 7, 6, 2, 1, 2, 3, 4, 5, 4, 3, 2, 1
                     });
               new ComputerSolve(grid, "Unsolveable");
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

      System.out.println(test.isAlive());
      assertFalse(test.isAlive());
   }
}

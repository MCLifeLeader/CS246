// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/al/TestModule.java,v 1.3 2006/06/26 20:20:07 mbcarey Exp $
package sudoku.al;

import junit.framework.*;

/**
 * Encapsulates all tests created for this module.
 */
public class TestModule
   extends TestSuite
{
   /**
    * Constructs a new TestModule instance.
    */
   public TestModule()
   {
      addTest(new TestSuite(TestComputerSolve.class));
      addTest(new TestSuite(TestGridChecker.class));
      addTest(new TestSuite(TestGridSolver.class));
      addTest(new TestSuite(TestPuzzle.class));
   }

   /**
    * Returns the constructed test suite.
    */
   public static Test suite()
   {
      return new TestModule();
   }
}

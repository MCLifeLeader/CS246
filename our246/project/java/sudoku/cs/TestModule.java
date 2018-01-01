// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/TestModule.java,v 1.7 2006/06/26 20:18:18 mbcarey Exp $
package sudoku.cs;

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
      addTest(new TestSuite(TestClientModel.class));
      addTest(new TestSuite(TestComputerPlayer.class));
      addTest(new TestSuite(TestDictionary.class));
      addTest(new TestSuite(TestHumanPlayer.class));
      addTest(new TestSuite(TestPlayer.class));
      addTest(new TestSuite(TestServerModel.class));
      addTest(new TestSuite(TestStats.class));
      addTest(new TestSuite(TestSudokuTimer.class));
   }

   /**
    * Returns the constructed test suite.
    */
   public static Test suite()
   {
      return new TestModule();
   }
}

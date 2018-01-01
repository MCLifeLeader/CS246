// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/si/TestModule.java,v 1.1 2006/06/13 16:13:03 neffr Exp $
package sudoku.si;

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
      addTest(new TestSuite(TestConfig.class));
      addTest(new TestSuite(TestLogger.class));
   }

   /**
    * Returns the constructed test suite.
    */
   public static Test suite()
   {
      return new TestModule();
   }
}

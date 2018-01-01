// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/TestModule.java,v 1.1 2006/06/13 16:13:03 neffr Exp $
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
      addTest(new TestSuite(TestDictionary.class));
   }

   /**
    * Returns the constructed test suite.
    */
   public static Test suite()
   {
      return new TestModule();
   }
}

// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/si/TestLogger.java,v 1.1 2006/06/13 16:13:03 neffr Exp $
package sudoku.si;

import java.io.*;

import junit.framework.*;

/**
 * A JUnit test for checking that the Logger class works properly.
 */
public class TestLogger
   extends TestCase
{
   /**
    * Local instance of a Logger, which will be used in the test.
    */
   private Logger mLogger;

   /**
    * Constructs a TestLogger instance.
    */
   public TestLogger()
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
    * Tests the warn(String) method of Logger.
    */
   public void testWarn()
   {
      mLogger = new FileLogger();
      mLogger.warnInternal("Warning");
      assertTrue(new File("log.log").exists());
      Config.set("FileLoggerInstance", mLogger);
      Config.show();
   }

   // matching methods should be created for the other levels

}

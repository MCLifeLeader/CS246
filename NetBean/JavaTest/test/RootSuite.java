import junit.framework.*;
/*
 * RootSuite.java
 * JUnit based test
 *
 * Created on June 26, 2006, 1:57 PM
 */

/**
 *
 * @author mbcarey
 */
public class RootSuite extends TestCase
{
   
   public RootSuite(String testName)
   {
      super(testName);
   }

   protected void setUp() throws Exception
   {
   }

   protected void tearDown() throws Exception
   {
   }


   /**
    * suite method automatically generated by JUnit module
    */
   public static Test suite()
   {
      TestSuite suite = new TestSuite("RootSuite");
      suite.addTest(sudoku.ai.AiSuite.suite());
      suite.addTest(sudoku.al.AlSuite.suite());
      suite.addTest(sudoku.cs.CsSuite.suite());
      suite.addTest(sudoku.cs.event.EventSuite.suite());
      suite.addTest(sudoku.si.SiSuite.suite());
      suite.addTest(sudoku.ui.UiSuite.suite());
      suite.addTest(sudoku.ui.action.ActionSuite.suite());
      return suite;
   }
   
}

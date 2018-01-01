// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/TestHumanPlayer.java,v 1.3 2006/06/26 21:43:39 mbcarey Exp $
/*
 * HumanPlayerTest.java
 * JUnit based test
 *
 * Created on June 26, 2006, 2:07 PM
 */
package sudoku.cs;

import junit.framework.*;

import sudoku.si.*;

import java.util.*;

/**
 *
 * @author mbcarey
 */
public class TestHumanPlayer
   extends TestCase
{
   /**
    * Creates a new TestHumanPlayer object.
    *
    * @param testName Name of the test
    */
   public TestHumanPlayer(String testName)
   {
      super(testName);
   }

   /**
    * setup the test
    *
    * @throws Exception If there are problems during setup
    */
   protected void setUp() throws Exception
   {
   }

   /**
    * teardown the test
    *
    * @throws Exception If there are problems during tear down
    */
   protected void tearDown() throws Exception
   {
   }

   /**
    * get the test suite and return it
    *
    * @return the test suite object that is to run
    */
   public static Test suite()
   {
      TestSuite suite = new TestSuite(TestHumanPlayer.class);

      return suite;
   }

   /**
    * Test of getPuzzle method, of class sudoku.cs.HumanPlayer.
    */
   public void testGetPuzzle()
   {
      System.out.println("TestHumanPlayer: getPuzzle");

      HumanPlayer instance = null;

      Puzzle expResult = null;
      Puzzle result = instance.getPuzzle();
      assertEquals(expResult, result);

      fail("The test case is a prototype.");
   }

   /**
    * Test of getPlayerStats method, of class sudoku.cs.HumanPlayer.
    */
   public void testGetPlayerStats()
   {
      System.out.println("TestHumanPlayer: getPlayerStats");

      HumanPlayer instance = null;

      Stats expResult = null;
      Stats result = instance.getPlayerStats();
      assertEquals(expResult, result);

      fail("The test case is a prototype.");
   }

   /**
    * Test of toString method, of class sudoku.cs.HumanPlayer.
    */
   public void testToString()
   {
      System.out.println("TestHumanPlayer: toString");

      HumanPlayer instance = null;

      String expResult = "";
      String result = instance.toString();
      assertEquals(expResult, result);

      fail("The test case is a prototype.");
   }
}

// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/TestServerModel.java,v 1.9 2006/06/27 05:08:03 mbcarey Exp $
/*
 * ServerModelTest.java
 * JUnit based test
 *
 * Created on June 26, 2006, 12:57 PM
 */
package sudoku.cs;

import junit.framework.*;

import sudoku.al.*;

import java.io.*;
import java.rmi.Naming;

import java.util.*;

/**
 *
 * @author mbcarey
 */
public class TestServerModel
   extends TestCase
{
   /**
    * Creates a new TestServerModel object.
    *
    * @param testName Name of the test to perform
    */
   public TestServerModel(String testName)
   {
      super(testName);
   }

   /**
    * setUp code for tests
    *
    * @throws Exception If there was a problem is setup
    */
   protected void setUp() throws Exception
   {
   }

   /**
    * Tear down code
    *
    * @throws Exception If there was a problem during tear down of tests
    */
   protected void tearDown() throws Exception
   {
   }

   /**
    * Get the test suite and return that object
    *
    * @return The test suite object
    */
   public static Test suite()
   {
      TestSuite suite = new TestSuite(TestServerModel.class);

      return suite;
   }

   /**
    * Test of rebind method, of class sudoku.cs.ServerModel.
    */
   public void testRebind()
   {
      System.out.println("TestServerModel: rebind");

      try
      {
         ServerModel instance = new ServerModel();

         instance.rebind();
      }
      catch (Exception ex)
      {
         System.out.println(ex); 
      }
      try
      {
         assertNotNull(Naming.lookup("SudokuServer"));
      }
      catch (Exception ex)
      {
         fail("SudokuServer not bound");
      }
   }

   /**
    * Test of unbind method, of class sudoku.cs.ServerModel.
    */
   public void testUnbind()
   {
      System.out.println("TestServerModel: unbind");

      try
      {
         ServerModel instance = new ServerModel();

         instance.unbind();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      try
      {
         assertNull(Naming.lookup("SudokuServer"));
      }
      catch (Exception ex)
      {
         assertTrue(true);
      }
   }

   /**
    * Test of addPlayer method, of class sudoku.cs.ServerModel.
    */
   public void testAddPlayer() throws Exception
   {
      System.out.println("TestServerModel: addPlayer");

      try
      {
         Client inClient = null;
         ServerModel instance = new ServerModel();

         boolean expResult = true;
         boolean result = instance.addPlayer(inClient);
         assertEquals(expResult, result);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
         fail("add player rised an Exception");
      }
   }

   /**
    * Test of removePlayer method, of class sudoku.cs.ServerModel.
    */
   public void testRemovePlayer() throws Exception
   {
      System.out.println("TestServerModel: removePlayer");

      try
      {
         Client inClient = null;
         ServerModel instance = new ServerModel();

         instance.removePlayer(inClient, inClient.getIPAddress());
         assertTrue(instance.addPlayer(inClient));
      }
      catch (Exception ex)
      {
         System.out.println(ex);
         fail("removePlayer rised an Exception");
      } 
   }

   /**
    * Test of playerStatsChanged method, of class sudoku.cs.ServerModel.
    */
   public void testPlayerStatsChanged() throws Exception
   {
      System.out.println("TestServerModel: playerStatsChanged");

      try
      {
         ServerModel instance = new ServerModel();

         instance.playerStatsChanged();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of getPlayers method, of class sudoku.cs.ServerModel.
    */
   public void testGetPlayers()
   {
      System.out.println("TestServerModel: getPlayers");

      try
      {
         ServerModel instance = new ServerModel();

         Collection<Player> expResult = null;
         Collection<Player> result = instance.getPlayers();
         assertEquals(expResult, result);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
         fail("an Exception was rised");
      }
   }

   /**
    * Test of newPuzzle method, of class sudoku.cs.ServerModel.
    */
   public void testNewPuzzle() throws Exception
   {
      System.out.println("TestServerModel: newPuzzle");

      try
      {
         String difficulty = "easy";
         ServerModel instance = new ServerModel();

         instance.newPuzzle(difficulty);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of getSolution method, of class sudoku.cs.ServerModel.
    */
   public void testGetSolution() throws Exception
   {
      System.out.println("TestServerModel: getSolution");

      try
      {
         ServerModel instance = new ServerModel();

         Grid expResult = null;
         Grid result = instance.getSolution();
         assertEquals(expResult, result);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of getTimer method, of class sudoku.cs.ServerModel.
    */
   public void testGetTimer() throws Exception
   {
      System.out.println("TestServerModel: getTimer");

      try
      {
         ServerModel instance = new ServerModel();

         SudokuTimer expResult = null;
         SudokuTimer result = instance.getTimer();
         assertEquals(expResult, result);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of startGame method, of class sudoku.cs.ServerModel.
    */
   public void testStartGame() throws Exception
   {
      System.out.println("TestServerModel: startGame");

      try
      {
         ServerModel instance = new ServerModel();

         instance.startGame();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of pause method, of class sudoku.cs.ServerModel.
    */
   public void testPause() throws Exception
   {
      System.out.println("TestServerModel: pause");

      try
      {
         ServerModel instance = new ServerModel();

         instance.pause();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of unpause method, of class sudoku.cs.ServerModel.
    */
   public void testUnpause() throws Exception
   {
      System.out.println("TestServerModel: unpause");

      try
      {
         ServerModel instance = new ServerModel();

         instance.unpause();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of endGame method, of class sudoku.cs.ServerModel.
    */
   public void testEndGame() throws Exception
   {
      System.out.println("TestServerModel: endGame");

      try
      {
         ServerModel instance = new ServerModel();

         instance.endGame();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of handleCommands method, of class sudoku.cs.ServerModel.
    */
   public void testHandleCommands() throws Exception
   {
      System.out.println("TestServerModel: handleCommands");

      InputStream inputStream = null;
      ServerModel instance = new ServerModel();

      instance.handleCommands(inputStream);

      fail("The test case is a prototype.");
   }

   /**
    * Test of help method, of class sudoku.cs.ServerModel.
    */
   public void testHelp()
   {
      System.out.println("TestServerModel: help");

      try
      {
         ServerModel instance = new ServerModel();

         instance.help();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of show method, of class sudoku.cs.ServerModel.
    */
   public void testShow() throws Exception
   {
      System.out.println("TestServerModel: show");

      try
      {
         ServerModel instance = new ServerModel();

         instance.show();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of start method, of class sudoku.cs.ServerModel.
    */
   public void testStart()
   {
      System.out.println("TestServerModel: start");

      try
      {
         ServerModel instance = new ServerModel();

         instance.start();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of stop method, of class sudoku.cs.ServerModel.
    */
   public void testStop()
   {
      System.out.println("TestServerModel: stop");

      try
      {
         ServerModel instance = new ServerModel();

         instance.stop();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }
}

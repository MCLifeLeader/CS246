// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/TestClientModel.java,v 1.5 2006/06/26 23:32:44 manxman Exp $
/*
 * ClientModelTest.java
 * JUnit based test
 *
 * Created on June 26, 2006, 1:10 PM
 */
package sudoku.cs;

import junit.framework.*;

import sudoku.al.*;

import sudoku.cs.event.*;

import sudoku.si.*;

import java.rmi.*;
import java.rmi.server.*;

import java.util.*;

/**
 *
 * @author mbcarey
 */
public class TestClientModel
   extends TestCase
{
   /**
    * Creates a new TestClientModel object.
    *
    * @param testName What is the name of the test
    */
   public TestClientModel(String testName)
   {
      super(testName);
   }

   /**
    * Empty setUp function
    *
    * @throws Exception If setup failes
    */
   protected void setUp() throws Exception
   {
   }

   /**
    * Tear Down test code
    *
    * @throws Exception If there is a failure on tear down
    */
   protected void tearDown() throws Exception
   {
   }

   /**
    * The full test suite code
    *
    * @return Return the test suite object
    */
   public static Test suite()
   {
      TestSuite suite = new TestSuite(TestClientModel.class);

      return suite;
   }

   /**
    * Test of lookup method, of class sudoku.cs.ClientModel.
    */
   public void testLookup()
   {
      System.out.println("TestClientModel: lookup");

      String name = "";
      String prefix = "";
      String suffix = "";

      Remote expResult = null;
      Remote result = ClientModel.lookup(name, prefix, suffix);
      assertEquals(expResult, result);

      fail("The test case is a prototype.");
   }

   /**
    * Test of connectRemote method, of class sudoku.cs.ClientModel.
    */
   public void testConnectRemote()
   {
      System.out.println("TestClientModel: connectRemote");

      try
      {
         ClientModel instance = new ClientModel();

         boolean expResult = true;
         boolean result = instance.connectRemote();
         assertEquals(expResult, result);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of connectLocal method, of class sudoku.cs.ClientModel.
    */
   public void testConnectLocal()
   {
      System.out.println("TestClientModel: connectLocal");

      try
      {
         ClientModel instance = new ClientModel();

         boolean expResult = true;
         boolean result = instance.connectLocal();
         assertEquals(expResult, result);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of registerWithServer method, of class sudoku.cs.ClientModel.
    */
   public void testRegisterWithServer()
   {
      System.out.println("TestClientModel: registerWithServer");

      try
      {
         ClientModel instance = new ClientModel();

         boolean expResult = true;
         boolean result = instance.registerWithServer();
         assertEquals(expResult, result);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of playerChanged method, of class sudoku.cs.ClientModel.
    */
   public void testPlayerChanged() throws Exception
   {
      System.out.println("TestClientModel: playerChanged");

      Collection<Player> inPlayers = null;
      ClientModel instance = new ClientModel();

      instance.playerChanged(inPlayers);

      fail("The test case is a prototype.");
   }

   /**
    * Test of getPlayer method, of class sudoku.cs.ClientModel.
    */
   public void testGetPlayer() throws Exception
   {
      System.out.println("TestClientModel: getPlayer");

      ClientModel instance = new ClientModel();

      Player expResult = null;
      Player result = instance.getPlayer();
      assertEquals(expResult, result);

      fail("The test case is a prototype.");
   }

   /**
    * Test of getName method, of class sudoku.cs.ClientModel.
    */
   public void testGetName() throws Exception
   {
      System.out.println("TestClientModel: getName");

      ClientModel instance = new ClientModel();

      String expResult = "";
      String result = instance.getName();
      assertEquals(expResult, result);

      fail("The test case is a prototype.");
   }

   /**
    * Test of setName method, of class sudoku.cs.ClientModel.
    */
   public void testSetName()
   {
      System.out.println("TestClientModel: setName");

      try
      {
         String inName = "";
         ClientModel instance = new ClientModel();

         instance.setName(inName);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of getPuzzle method, of class sudoku.cs.ClientModel.
    */
   public void testGetPuzzle()
   {
      System.out.println("TestClientModel: getPuzzle");

      try
      {
         ClientModel instance = new ClientModel();

         Puzzle expResult = null;
         Puzzle result = instance.getPuzzle();
         assertEquals(expResult, result);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of getPlayerStats method, of class sudoku.cs.ClientModel.
    */
   public void testGetPlayerStats()
   {
      System.out.println("TestClientModel: getPlayerStats");

      try
      {
         ClientModel instance = new ClientModel();

         Stats expResult = null;
         Stats result = instance.getPlayerStats();
         assertEquals(expResult, result);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of newPuzzle method, of class sudoku.cs.ClientModel.
    */
   public void testNewPuzzle()
   {
      System.out.println("TestClientModel: newPuzzle");

      try
      {
         String difficulty = "";
         ClientModel instance = new ClientModel();

         instance.newPuzzle(difficulty);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of setNewPuzzle method, of class sudoku.cs.ClientModel.
    */
   public void testSetNewPuzzle() throws Exception
   {
      System.out.println("TestClientModel: setNewPuzzle");

      Grid newBoard = null;
      ClientModel instance = new ClientModel();

      instance.setNewPuzzle(newBoard);

      fail("The test case is a prototype.");
   }

   /**
    * Test of clear method, of class sudoku.cs.ClientModel.
    */
   public void testClear()
   {
      System.out.println("TestClientModel: clear");

      try
      {
         ClientModel instance = new ClientModel();

         instance.clear();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of addGuess method, of class sudoku.cs.ClientModel.
    */
   public void testAddGuess()
   {
      System.out.println("TestClientModel: addGuess");

      try
      {
         String guess = "";
         String prev = "";
         int x = 0;
         int y = 0;
         ClientModel instance = new ClientModel();

         instance.addGuess(guess, prev, x, y);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of removeGuess method, of class sudoku.cs.ClientModel.
    */
   public void testRemoveGuess()
   {
      System.out.println("TestClientModel: removeGuess");

      try
      {
         String prev = "";
         int x = 0;
         int y = 0;
         ClientModel instance = new ClientModel();

         instance.removeGuess(prev, x, y);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of getSquare method, of class sudoku.cs.ClientModel.
    */
   public void testGetSquare()
   {
      System.out.println("TestClientModel: getSquare");

      try
      {
         int x = 0;
         int y = 0;
         ClientModel instance = new ClientModel();

         String expResult = "";
         String result = instance.getSquare(x, y);
         assertEquals(expResult, result);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of checkPuzzle method, of class sudoku.cs.ClientModel.
    */
   public void testCheckPuzzle()
   {
      System.out.println("TestClientModel: checkPuzzle");

      try
      {
         ClientModel instance = new ClientModel();

         Collection expResult = null;
         Collection result = instance.checkPuzzle();
         assertEquals(expResult, result);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of setBoard method, of class sudoku.cs.ClientModel.
    */
   public void testSetBoard()
   {
      System.out.println("TestClientModel: setBoard");

      try
      {
         Grid puzzle = null;
         ClientModel instance = new ClientModel();

         instance.setBoard(puzzle);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of getBoard method, of class sudoku.cs.ClientModel.
    */
   public void testGetBoard()
   {
      System.out.println("TestClientModel: getBoard");

      try
      {
         ClientModel instance = new ClientModel();

         Grid expResult = null;
         Grid result = instance.getBoard();
         assertEquals(expResult, result);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of getTimer method, of class sudoku.cs.ClientModel.
    */
   public void testGetTimer()
   {
      System.out.println("TestClientModel: getTimer");

      try
      {
         ClientModel instance = new ClientModel();

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
    * Test of startGame method, of class sudoku.cs.ClientModel.
    */
   public void testStartGame()
   {
      System.out.println("TestClientModel: startGame");

      try
      {
         String difficulty = "";
         ClientModel instance = new ClientModel();

         instance.startGame(difficulty);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of gameReady method, of class sudoku.cs.ClientModel.
    */
   public void testGameReady() throws Exception
   {
      System.out.println("TestClientModel: gameReady");

      ClientModel instance = new ClientModel();

      instance.gameReady();

      fail("The test case is a prototype.");
   }

   /**
    * Test of pause method, of class sudoku.cs.ClientModel.
    */
   public void testPause()
   {
      System.out.println("TestClientModel: pause");

      try
      {
         ClientModel instance = new ClientModel();

         instance.pause();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of unpause method, of class sudoku.cs.ClientModel.
    */
   public void testUnpause()
   {
      System.out.println("TestClientModel: unpause");

      try
      {
         ClientModel instance = new ClientModel();

         instance.unpause();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of gamePaused method, of class sudoku.cs.ClientModel.
    */
   public void testGamePaused() throws Exception
   {
      System.out.println("TestClientModel: gamePaused");

      ClientModel instance = new ClientModel();

      instance.gamePaused();

      fail("The test case is a prototype.");
   }

   /**
    * Test of endGame method, of class sudoku.cs.ClientModel.
    */
   public void testEndGame() throws Exception
   {
      System.out.println("TestClientModel: endGame");

      ClientModel instance = new ClientModel();

      instance.endGame();

      fail("The test case is a prototype.");
   }

   /**
    * Test of getCurrState method, of class sudoku.cs.ClientModel.
    */
   public void testGetCurrState()
   {
      System.out.println("TestClientModel: getCurrState");

      try
      {
         ClientModel instance = new ClientModel();

         State expResult = null;
         State result = instance.getCurrState();
         assertEquals(expResult, result);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of getPrevState method, of class sudoku.cs.ClientModel.
    */
   public void testGetPrevState()
   {
      System.out.println("TestClientModel: getPrevState");

      try
      {
         ClientModel instance = new ClientModel();

         State expResult = null;
         State result = instance.getPrevState();
         assertEquals(expResult, result);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of exitGame method, of class sudoku.cs.ClientModel.
    */
   public void testExitGame()
   {
      System.out.println("TestClientModel: exitGame");

      try
      {
         ClientModel instance = new ClientModel();

         instance.exitGame();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of saveState method, of class sudoku.cs.ClientModel.
    */
   public void testSaveState()
   {
      System.out.println("TestClientModel: saveState");

      try
      {
         ClientModel instance = new ClientModel();

         instance.saveState();
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of addGameStatusChangedListener method, of class sudoku.cs.ClientModel.
    */
   public void testAddGameStatusChangedListener()
   {
      System.out.println("TestClientModel: addGameStatusChangedListener");

      try
      {
         GameStatusChangedListener listener = null;
         ClientModel instance = new ClientModel();

         instance.addGameStatusChangedListener(listener);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of addPuzzleChangedListener method, of class sudoku.cs.ClientModel.
    */
   public void testAddPuzzleChangedListener()
   {
      System.out.println("TestClientModel: addPuzzleChangedListener");

      try
      {
         PuzzleChangedListener listener = null;
         ClientModel instance = new ClientModel();

         instance.addPuzzleChangedListener(listener);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of addPlayerChangedListener method, of class sudoku.cs.ClientModel.
    */
   public void testAddPlayerChangedListener()
   {
      System.out.println("TestClientModel: addPlayerChangedListener");

      try
      {
         PlayerChangedListener listener = null;
         ClientModel instance = new ClientModel();

         instance.addPlayerChangedListener(listener);
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }

      fail("The test case is a prototype.");
   }

   /**
    * Test of wordLookup method, of class sudoku.cs.ClientModel.
    */
   public void testWordLookup() throws Exception
   {
      System.out.println("TestClientModel: wordLookup");

      String word = "";
      ClientModel instance = new ClientModel();

      boolean expResult = true;
      boolean result = instance.wordLookup(word);
      assertEquals(expResult, result);

      fail("The test case is a prototype.");
   }
}

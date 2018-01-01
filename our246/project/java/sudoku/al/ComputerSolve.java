// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/al/ComputerSolve.java,v 1.6 2006/06/27 00:10:10 ricjames Exp $
package sudoku.al;

import sudoku.cs.*;

import sudoku.si.*;

import java.lang.Byte;

import java.util.Random;

/**
 * Class Computer Solve
 *
 * @author $Algorithm's Team$
 * @version $1.0$
 */
public class ComputerSolve
   extends ComputerPlayer
{
   /**
    * The max variable time the computer will sleep inbetween guesses
    * to the puzzle
    */
   private static final int MAX = 2200;

   /**
    * The amount to step between difficulty levels
    * range = 200 to 2200
    */
   private static final int STEP = 200;

   /**
    * The grid object used by this algorithm
    */
   protected static Grid mGrid;

   /**
    * Stores a value which is translated by methods between 1 and 10
    * 1 = easy, 10 = insane
    */
   protected static int mDifficulty;

   /**
    * Client for adding guesses
    */

   private static ClientModel mClient;

   /**
    * Creates a new ComputerSolve object.
    *
    * @param inGrid The grid to be solved
    * @param inName The computer's name
    * @param inDifficulty values 1 - 10, 1 is easy, 10 is insane
    */
   public ComputerSolve(Grid inGrid, String inName, int inDifficulty)
   {
      super(inName);
      mGrid = inGrid;
      mDifficulty = inDifficulty;

      try
      {
         mClient = new ClientModel("sudoku.cs.Computer");
         mClient.setName(inName);
      }
      catch (Throwable e)
      {
         e.printStackTrace();
      }

      solveAll();
   }

   /**
    * Creates a new ComputerSolve object.
    *
    * @param inGrid The grid to be solved
    * @param inName The computer's name
    */
   public ComputerSolve(Grid inGrid, String inName)
   {
      super(inName);
      mGrid = inGrid;
      mDifficulty = 1;
      solveAll();

      try
      {
         mClient = new ClientModel("Computer");
         mClient.setName(inName);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Creates a new ComputerSolve object.
    *
    * @param inGrid The grid to be solved
    * Call this only once, otherwise multiple computers
    * with the same name will be created.
    */
   public ComputerSolve(Grid inGrid)
   {
      super("Computer");
      mGrid = inGrid;
      mDifficulty = 1;
      solveAll();

      try
      {
         mClient = new ClientModel("Computer");
         mClient.setName("Computer");
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Get difficutly method
    *
    * @return a difficulty value between 1 and 10
    */
   public int getDifficulty()
   {
      return (MAX - (mDifficulty / STEP));
   }

   /**
    * Set Difficulty
    *
    * @param inDifficulty value between 1 and 10
    * 1 = easy, 10 = insane
    */
   public void setDifficulty(int inDifficulty)
   {
      mDifficulty = (MAX - (inDifficulty * STEP));
   }

   /**
    * Solve All
    * The constructor calls this method when a ComputerSolve object is created
    * Calls the run method as many times as necessary for the computer to
    * complete the puzzle.
    * unfinished - The publisher/observer portion still needs to publish that
    * when a new value is solved by the computer.
    */
   protected void solveAll()
   {
      int mCounter = 0;

      for (byte number : mGrid.getArray())
      {
         if (number < 1)
         {
            mCounter++;
         }
      }

      while (mCounter > 0)
      {
         Grid rtnGrid = new Grid();
         rtnGrid = run(mGrid);
         mCounter--;
      }
   }

   /**
    * Run
    * This method will only solve one cell in the puzzle randomly
    *
    * @param inGrid The grid that needs solved
    *
    * @return The grid with one additional solved cell
    * Caution - outside classes shouldn't call this method, but can if needed
    * Preffered - Construct a ComputerSolve object that will solve itself.
    */
   public static Grid run(Grid inGrid)
   {
      Random generator = new Random();
      int x = generator.nextInt(9);
      int y = generator.nextInt(9);
      byte value = ((byte) (generator.nextInt(9) + 1));

      while (inGrid.getCell(x, y) != -(value))
      {
         try
         {
            Thread.sleep(mDifficulty);
         }
         catch (Exception e)
         {
         }

         x = generator.nextInt(9);
         y = generator.nextInt(9);
         value = ((byte) (generator.nextInt(9) + 1));
      }

      inGrid.setCell(x, y, value);

      if (mClient != null)
      {
         mClient.addGuess(Byte.toString(value),"", x, y);
      }

      return inGrid;
   }
}

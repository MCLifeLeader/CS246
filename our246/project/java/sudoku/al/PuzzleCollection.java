// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/al/PuzzleCollection.java,v 1.7 2006/06/24 21:51:13 mbcarey Exp $
package sudoku.al;

import sudoku.si.*;

import java.util.*;

/**
 * Holds a collection of puzzles.
 */
public class PuzzleCollection
{
   /**
    * The actual collection (list) of puzzles.
    */
   private List<Puzzle> mPuzzles;

   /**
    * Constructs a new PuzzleCollection instance.
    */
   public PuzzleCollection()
   {
      mPuzzles = new ArrayList<Puzzle>();
      new Puzzle(); // forces the Puzzle class to be loaded, unlike the previous line.

      mPuzzles = (List<Puzzle>) Config.get("Puzzles", mPuzzles);
   }

   /**
    * Gets a new puzzle with the given number.
    *
    * @param number the number of the desired puzzle.
    *
    * @return a new puzzle board.
    */
   public Grid getNewPuzzle(int number)
   {
      Puzzle thePuzzle = getRandomPuzzle();

      for (Puzzle puzzle : mPuzzles)
      {
         if (puzzle.getNumber() == number)
         {
            thePuzzle = puzzle;
         }
      }

      return (new Grid(thePuzzle.getData()));
   }

   /**
    * Gets a random puzzle.
    *
    * @return A random puzzle board.
    */
   public Puzzle getRandomPuzzle()
   {
      return getRandomPuzzle(mPuzzles);
   }

   /**
    * Gets a random puzzle.
    *
    * @param inPuzzles A List of Puzzles.
    *
    * @return A random puzzle board.
    */
   public Puzzle getRandomPuzzle(List<Puzzle> inPuzzles)
   {
      int puzzleCount = inPuzzles.size();
      Puzzle retPuzzle = null;

      Random rn = new Random();
      rn.setSeed(new Date().getTime());

      if (puzzleCount <= 0)
      {
         Logger.debug("List of inPuzzles is not greater than 0");
      }
      else
      {
         retPuzzle = inPuzzles.get((int) rn.nextInt(puzzleCount));
      }

      return retPuzzle;
   }

   /**
    * Gets a new puzzle with the given difficulty.
    *
    * @param difficulty the desired difficulty.
    *
    * @return a new puzzle board.
    */
   public Grid getNewPuzzle(String difficulty)
   {
      Puzzle thePuzzle = getRandomPuzzle();

      List<Puzzle> ratedPuzzles = new ArrayList<Puzzle>();

      for (Puzzle puzzle : mPuzzles)
      {
         if (difficulty.equals(puzzle.getLevel().toString()))
         {
            ratedPuzzles.add(puzzle);
         }
      }

      if (ratedPuzzles.size() > 0)
      {
         thePuzzle = getRandomPuzzle(ratedPuzzles);
      }

      return (new Grid(thePuzzle.getData()));
   }
}

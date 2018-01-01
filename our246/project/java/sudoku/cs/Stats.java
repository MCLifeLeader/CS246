// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/Stats.java,v 1.5 2006/06/26 15:58:58 manxman Exp $
package sudoku.cs;

import sudoku.al.*;

import java.io.*;

import java.util.*;

/**
 * Figures out game stats
 *
 * @author $Michael Ricks$
 * @version $Revision: 1.5 $
 */
public class Stats
   implements Serializable
{
   /**
    * Holds the total number of guess that have
    * been made by each player.
    */
   private int mNumberOfGuesses = 0;

   /**
    * Holds the total number of non-correct or empty squares
    * that each player has left.
    */
   private int mSquaresLeft = 0;

   /**
    * Resets the stats.
    */
   public void resetStats()
   {
      mNumberOfGuesses = 0;
      mSquaresLeft = 0;
   }

   /**
    * Updates the total number of squares that are left.
    */
   public void updateSquaresLeft(Grid board)
   {
      mSquaresLeft = 0;

      byte[] array = board.getArray();

      for (byte number : array)
      {
         if (number < 0)
         {
            mSquaresLeft++;
         }
      }
   }

   /**
    * Updates the total number of guesses made.
    */
   public void updateNumberOfGuesses()
   {
      mNumberOfGuesses++;
   }

   /**
    * Gets the NumberOfGuesses.
    */
   public int getNumberOfGuesses()
   {
      return mNumberOfGuesses;
   }

   /**
    * Gets the SquaresLeft.
    */
   public int getSquaresLeft()
   {
      return mSquaresLeft;
   }
}

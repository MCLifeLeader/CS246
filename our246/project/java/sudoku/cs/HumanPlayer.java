// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/HumanPlayer.java,v 1.8 2006/06/24 22:24:01 manxman Exp $
package sudoku.cs;

import sudoku.si.*;

import java.util.*;

/**
 * Represents a "REAL" Sudoku player.
 *
 * @author $Randall Booth$
 * @version $Revision: 1.8 $
  */
public class HumanPlayer
   extends Person
   implements Player
{
   /**
    * Holds the puzzle information.
    */
   private Puzzle mPuzzle = new Puzzle();

   /**
    * Holds the stats information.
    */
   private Stats mPlayerStats = new Stats();

   /**
    * Constructs a Player instance.
    *
    * @param inName the player's name.
    */
   public HumanPlayer(String inName)
   {
      super(inName);
   }

   /**
    * Gets the Puzzle.
    *
    * @return the game Puzzle.
    */
   public Puzzle getPuzzle()
   {
      return mPuzzle;
   }

   /**
    * Gets the stats.
    *
    * @return the game Stats.
    */
   public Stats getPlayerStats()
   {
      return mPlayerStats;
   }

   /**
    * Returns a readable representation of the player.
    *
    * @return a readable representation of the player.
    */
   public String toString()
   {
      return ("Player[" + getName() + "]");
   }
}

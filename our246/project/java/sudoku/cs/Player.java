// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/Player.java,v 1.10 2006/06/24 21:40:03 manxman Exp $
package sudoku.cs;

import sudoku.si.*;

import java.io.*;

import java.util.*;

/**
 * Represents a Sudoku player.
 *
 * @author $Rick Neff$
 * @version $Revision: 1.10 $
  */
public interface Player
   extends Serializable
{
   /**
    * Gets the Puzzle.
    *
    * @return the game Puzzle.
    */
   public Puzzle getPuzzle();

   /**
    * Gets the Stats.
    *
    * @return the game Stats.
    */
   public Stats getPlayerStats();

   /**
    * Returns a readable representation of the player.
    *
    * @return a readable representation of the player.
    */
   public String toString();

   /**
    * Gets the player's name.
    *
    * @return The player's name
    */
   public String getName();

   /**
    * Sets the player's name.
    *
    * @param name The player's name
    */
   public void setName(String name);
}

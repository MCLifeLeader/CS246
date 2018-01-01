// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/Player.java,v 1.1 2006/06/13 16:13:03 neffr Exp $
package sudoku.cs;

import sudoku.si.*;

import java.io.*;

import java.util.*;

/**
 * Represents a Sudoku player.
 */
public class Player
   extends Person
   implements Serializable
{
   /**
    * Constructs a Player instance.
    *
    * @param inName the player's name.
    */
   public Player(String inName)
   {
      super(inName);
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

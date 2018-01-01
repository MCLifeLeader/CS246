// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/al/Puzzle.java,v 1.1 2006/06/19 14:24:10 neffr Exp $

package sudoku.al;

import java.math.BigInteger;

import sudoku.si.*;

/**
 * Represents a Sudoku puzzle.
 */
public class Puzzle
{
   /**
    * Puts a configuration alias of "puzzle" for the Puzzle class.
    */
   static
   {
      Config.putAlias("puzzle", Puzzle.class);
   }

   /**
    * The choices for level are enumerated as four.
    */
   public enum Level
   {
      Easy, Medium, Hard, Evil
   }

    /**
     * A mask used to obtain the value of an int as if it were unsigned.
     */
    private final static long LONG_MASK = 0xffffffffL;

   /**
    * The puzzle data.
    */
   private byte[] data;

   /**
    * The puzzle identifier.
    */
   private transient BigInteger mID;

   /**
    * The puzzle number.
    */
   private int number;

   /**
    * The puzzle difficulty level.
    */
   private Level level;

   /**
    * Constructs a new Puzzle instance.
    */
   public Puzzle()
   {
      data = null;
      mID = null;
      number = 0;
      level = Level.Easy;
   }

   /**
    * Constructs a new Puzzle instance.
    *
    * @param inData the puzzle data.
    *
    * @param inLevel the puzzle difficulty level.
    */
   public Puzzle(byte[] inData, Level inLevel)
   {
      setData(inData);
      level = inLevel;
   }

   /**
    * Gets a guaranteed unique identifier for this puzzle.
    *
    * @return the unique identifier for this puzzle.
    */
   public BigInteger getID()
   {
      if (mID == null)
      {

/*
 *  This way is absolutely guaranteed unique by the Fundamental Theorm of Arithmetic
 *  but is probably major overkill.
 *
 *         mID = BigInteger.ONE;
 *         for (int i = 0; i < data.length; i++)
 *         {
 *            int n = data[i];
 *            if (n < 0)
 *            {
 *               n += 19; // -9 => 10, -8 => 11, ..., -2 => 17, -1 => 18
 *            }
 *            // presupposes a Primes class with static "get" method
 *            // that returns the "i'th" prime number
 *            mID = mID.multiply(Primes.get(i).pow(n));
 *         }
 */
         number = 0;
         for (int i = 0; i < data.length; i++)
         {
            number = (int) (31 * number + (data[i] & LONG_MASK));
         }
         number = Math.abs(number);
         mID = new BigInteger("" + number);
      }
      return mID;
   }

   /**
    * Gets the puzzle data.
    *
    * @return the puzzle data.
    */
   public byte[] getData()
   {
      return data;
   }

   /**
    * Sets the puzzle data.
    *
    * @param inData the puzzle data.
    */
   public void setData(byte[] inData)
   {
      data = new byte[inData.length];
      mID = null;
      System.arraycopy(inData, 0, data, 0, data.length);
      getID();
   }

   /**
    * Gets the puzzle number.
    *
    * @return the puzzle number.
    */
   public int getNumber()
   {
      return number;
   }

   /**
    * Gets the puzzle difficulty level.
    *
    * @return the puzzle difficulty level.
    */
   public Level getLevel()
   {
      return level;
   }

   /**
    * Sets the puzzle difficulty level.
    *
    * @param inLevel the puzzle difficulty level.
    */
   public void setLevel(Level inLevel)
   {
      level = inLevel;
   }

   /**
    * A readable representation of the puzzle.
    *
    * @return a readable representation of the puzzle.
    */
   public String toString()
   {
      String name = getClass().getName();
      name = name.substring(name.lastIndexOf(".") + 1);
      return ("{" + name + " " + getNumber() + " " + getLevel() + "}");
   }
}

// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/TestDictionary.java,v 1.1 2006/06/13 16:13:03 neffr Exp $
package sudoku.cs;

import junit.framework.*;

import sudoku.si.*;

/**
 * Test the dictionary to ensure it is in working order.
 */
public class TestDictionary
   extends TestCase
{
   /**
    * Predetermined word count of the dictionary.
    */
   private static final int WORD_COUNT = 172785;

   /**
    * The dictionary object.
    */
   private Dictionary mDictionary;

   /**
    * Sets up the test case.
    */
   public void setUp()
   {
      mDictionary = new Dictionary();
   }

   /**
    * See if the whole dictionary was read in.
    */
   public void testReadDictionary()
   {
      assertEquals(mDictionary.getWordCount(), WORD_COUNT);
   }

   /**
    * See if various words are in the dictionary, which they should be.
    */
   public void testLookup1()
   {
      assertTrue(mDictionary.lookup("dICTIONARY"));
      assertTrue(mDictionary.lookup("LoOKuP"));
      assertTrue(mDictionary.lookup("Need"));
      assertTrue(mDictionary.lookup("nOt"));
      assertTrue(mDictionary.lookup("bE"));
      assertTrue(mDictionary.lookup("ONEROUS"));
   }

   /**
    * See if other "words" are NOT in the dictionary.
    */
   public void testLookup2()
   {
      assertFalse(mDictionary.lookup("snigglet"));
      assertFalse(mDictionary.lookup("facetiouslee"));
      assertFalse(mDictionary.lookup("acheive"));
      assertFalse(mDictionary.lookup("suCCes"));
      assertFalse(mDictionary.lookup("fellure"));
      assertFalse(mDictionary.lookup("whitch"));
   }
}

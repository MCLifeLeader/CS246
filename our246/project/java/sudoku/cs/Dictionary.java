// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/Dictionary.java,v 1.3 2006/06/24 17:56:12 manxman Exp $
package sudoku.cs;

import sudoku.si.*;

import java.io.*;

import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * A searchable dictionary of words suitable for Sudoku playing.
 *
 * @author $Rick Neff$
 * @version $Revision: 1.3 $
  */
public class Dictionary
{
   /**
    * The set of words in the dictionary.
    */
   private Set<String> mWords;

   /**
    * Constructs a Dictionary instance.
    */
   public Dictionary()
   {
      this("config/dictionary.txt.gz");
   }

   /**
    * Constructs a Dictionary instance.
    *
    * @param inFilename the name of the file containing the word list.
    */
   public Dictionary(String inFilename)
   {
      mWords = new HashSet<String>();

      try
      {
         String line;

         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                  new GZIPInputStream(new FileInputStream(inFilename))));

         while ((line = bufferedReader.readLine()) != null)
         {
            mWords.add(line.toUpperCase());
         }
      }
      catch (Exception e)
      {
         Logger.debug(e);
      }
   }

   /**
    * Gets the word count of the dictionary.
    *
    * @return the word count of the dictionary.
    */
   public int getWordCount()
   {
      return mWords.size();
   }

   /**
    * Looks up a word in the dictionary.
    *
    * @param word the word to look up.
    *
    * @return whether or not the word was in the dictionary.
    */
   public boolean lookup(String word)
   {
      return (mWords.contains(word.toUpperCase()));
   }
}

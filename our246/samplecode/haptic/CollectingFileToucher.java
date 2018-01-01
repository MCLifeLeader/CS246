import java.io.File;

import java.io.FileFilter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collection;

/**
 * Puts the given files into a Collection.
 */
public class CollectingFileToucher
   extends FileToucher
{
   /**
    * Holds the files being touched.
    */
   private Collection<File> mFiles;

   /**
    * Calls the superclass constructor and creates an empty collection.
    */
   public CollectingFileToucher(String[] args)
   {
      super(args);
      mFiles = new HashSet<File>();
      mFileFilter = new SuffixMatchingFileFilter(".java");
   }

   public Collection<File> getFiles()
   {
      return mFiles;
   }

   /**
    * Adds a given file to the list.
    */
   public void touchOne(File inFile)
   {
      mFiles.add(inFile);
   }

   /**
    * Outputs the list.
    */
   public void report(File inFile)
   {
      System.out.println("Collected " + mFiles + " in " + inFile);
   }
}

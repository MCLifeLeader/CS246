// $Header: /usr/local/cvsroot/courses/cs246/samplecode/haptic/FileToucher.java,v 1.1 2006/05/19 16:12:18 neffr Exp $

import java.io.File;
import java.io.FileFilter;

/**
 * Superclass of all "file touching" classes.
 */
public abstract class FileToucher
   implements Runnable
{
   /**
    * String arguments supplied to turn into File objects.
    */
   private String[] mArgs;

   /**
    * Filter for files based on some pattern.
    */
   protected FileFilter mFileFilter = null;

   /**
    * Touches one file
    * (method to be implemented by subclass).
    */
   public abstract void touchOne(File file);

   /**
    * Reports on file being touched
    * (method to be OPTIONALLY implemented by subclass).
    */
   public void report(File file)
   {
   }

   /**
    * Recursively goes through all files or directories.
    * If the file argument is a directory then it will pass that into
    * another call to this method and recurse down into that directory.
    */
   public void touchAll(File inFile)
   {
      if (! inFile.isDirectory())
      {
         if (inFile.exists())
         {
            touchOne(inFile);
         }
      }
      else
      {
         // fill up the array of File objects if there are any
         // (subject to the mFileFilter, if non-null)
         File[] files = inFile.listFiles(mFileFilter);

         if (files != null)
         {
            for (File file : files)
            {
               touchAll(file);
            }
         }
      }
   }

   /**
    * Constructs a new FileToucher instance.
    */
   public FileToucher(String[] args)
   {
      mArgs = args;
   }

   /**
    * Iterates through supplied arguments calling touchAll
    * and report on each file so named.  If none supplied,
    * asks a pertinent question.
    */
   public void run()
   {
      if (mArgs.length == 0)
      {
         System.out.println("What do you want to touch?");
      }
      else
      {
         for (String arg : mArgs)
         {
            File file = new File(arg);
            touchAll(file);
            report(file);
         }
      }
   }
}

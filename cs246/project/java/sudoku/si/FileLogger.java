// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/si/FileLogger.java,v 1.1 2006/06/13 16:13:03 neffr Exp $
package sudoku.si;

import java.io.*;

/**
 * A Logger that outputs messages to a file.
 */
public class FileLogger
   extends Logger
{
   /**
    * The name of the log file.
    */
   private String filename;

   /**
    * Whether or not the log file should be appended to (as opposed to being
    * overwritten each time).
    */
   private boolean append;

   /**
    * An OutputStream which strings can be sent to for
    * eventual writing to the log file.
    */
   private transient FileOutputStream mFileOut;

   /**
    * The wrapper class for the FileOutputStream that provides a println()
    * method for our use.
    */
   private transient PrintWriter mPrinter;

   /**
    * Constructs a new FileLogger instance.
    */
   public FileLogger()
   {
      String name = getClass().getName();
      filename = Config.get(name + ".logger.filename", "log.log");
      append = Config.get(name + ".logger.append", false);
      init();
   }

   /**
    * Prints a given message to a file
    *
    * @param message The String that will get outputed to the file
    */
   protected void logMessage(String message)
   {
      mPrinter.println(getMethodContext() + message);
   }

   /**
    * Uses the Config service to determine a destination file name.
    * If none is found it defaults to "log.log".
    * Creates an appending FileOutputStream with the file name and
    * wraps it in a PrintWriter.
    */
   public void init()
   {
      try
      {
         mFileOut = new FileOutputStream(filename, append);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

      // an automaticallly-flushing PrintWriter
      mPrinter = new PrintWriter(mFileOut, true);
   }
}

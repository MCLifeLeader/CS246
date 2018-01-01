// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/si/ConsoleLogger.java,v 1.1 2006/06/13 16:13:03 neffr Exp $
package sudoku.si;

/**
 * A Logger that outputs to the console.
 */
public class ConsoleLogger
   extends Logger
{
   /**
    * Constructs a new ConsoleLogger instance.
    */
   public ConsoleLogger()
   {
      // intentionally empty
   }

   /**
    * Logs a message to the console
    *
    * @param message String to be printed out to console
    */
   void logMessage(String message)
   {
      System.out.println(getMethodContext() + " " + message);
   }
}

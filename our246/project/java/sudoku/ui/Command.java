// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/Command.java,v 1.2 2006/06/15 01:21:07 rk223 Exp $
package sudoku.ui;


/**
 * Command interface for concrete commands to implement.
 */
public interface Command
{
   /**
    * Undoes this command.
    */
   public void undo();

   /**
    * Redoes this command.
    */
   public void redo();

   /**
    * Executes this command.
    */
   public void execute();
}

// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/CommandInvoker.java,v 1.2 2006/06/15 01:21:07 rk223 Exp $
package sudoku.ui;

import sudoku.cs.*;

import sudoku.si.*;

import sudoku.ui.action.*;

import java.util.*;

/**
 * Invokes a command.
 */
public class CommandInvoker
{
   /**
    * Holds the commands that have been executed.
    */
   private Stack<Command> mCommandHistory;

   /**
    * Holds the history of redone commands.
    * Cleared every time a command is "done".
    */
   public Stack<Command> mRedoHistory;

   /**
    * Constructs a new CommandInvoker instance.
    */
   public CommandInvoker()
   {
      mCommandHistory = new Stack<Command>();
      mRedoHistory = new Stack<Command>();
   }

   /**
    * Removes the most recent command from the history stack.
    * Performs the undo operation on this command and prepares
    * it to be redone.
    */
   public void undo()
   {
      mRedoHistory.add(mCommandHistory.pop());
      mRedoHistory.peek().undo();

      // TODO: Redo.getInstance().setEnabled(true);
      if (mCommandHistory.empty())
      {
         // TODO: Undo.getInstance().setEnabled(false);
      }
   }

   /**
    * Redoes the most recent redo command.
    * Places command back onto the command history stack.
    */
   public void redo()
   {
      mCommandHistory.add(mRedoHistory.pop());
      mCommandHistory.peek().redo();

      // TODO: Undo.getInstance().setEnabled(true);
      if (mRedoHistory.empty())
      {
         // TODO: Redo.getInstance().setEnabled(false);
      }
   }

   /**
    * Dispatches a Command object by calling its
    * execute method, and adds it to the command
    * history stack. Clears the redo history first.
    *
    * @param cmd Command object to dispatch
    */
   public void dispatch(Command cmd)
   {
      cmd.execute();
      mRedoHistory.clear();
      // TODO: Redo.getInstance().setEnabled(false);
      mCommandHistory.push(cmd);

      // TODO: Undo.getInstance().setEnabled(true);
   }
}

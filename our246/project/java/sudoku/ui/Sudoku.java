// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/Sudoku.java,v 1.6 2006/06/25 07:11:47 emerrill Exp $
package sudoku.ui;

import sudoku.cs.*;

import sudoku.cs.event.*;

/**
 * The MAIN Sudoku class, initializes the GUI (SudokuFrame)
 * and allows global access to it.
 */
public class Sudoku
{
   /**
    * Supports a "pseudo-Singleton" pattern.
    */
   private static SudokuFrame cFrameInstance = new SudokuFrame();

   /**
    * Gets the frame.
    *
    * @return the frame.
    */
   public static SudokuFrame getFrame()
   {
      return cFrameInstance;
   }

   /**
    * Games previous state.
    */
   public static State mPrevState;

   /**
    * Games current state.
    */
   public static State mCurrState;

   /**
    * Gets the current state
    *
    * @return the current state.
    */
   public static State getCurrentState()
   {
      return mCurrState;
   }

   /**
    * Gets the previous state.
    *
    * @return the previous state.
    */
   public static State getPreviousState()
   {
      return mPrevState;
   }

   /**
    * Keeps track of both current and previous states.
    *
    * @param e is an event meaning the game state has changed.
    */
   public static void setStates(GameStatusChangedEvent e)
   {
      mPrevState = e.getPrevState();
      mCurrState = e.getCurrState();
   }

   /**
    * Gets the panel.
    *
    * @return the panel.
    */
   public static SudokuPanel getPanel()
   {
      return getFrame().getPanel();
   }

   /**
    * Gets the client model.
    *
    * @return the client model.
    */
   public static ClientModel getClientModel()
   {
      return getFrame().getClientModel();
   }

   /**
    * The main method of Sudoku gets things going.
    *
    * @param args the command-line arguments (none at present).
    */
   public static void main(String[] args)
   {
      getFrame().setVisible(true);
   }
}

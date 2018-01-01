// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/SudokuFrame.java,v 1.33 2006/06/26 21:02:54 emerrill Exp $
package sudoku.ui;

import sudoku.cs.*;

import sudoku.cs.event.*;

import sudoku.si.*;

import sudoku.ui.action.*;

import java.awt.*;
import java.awt.event.*;

import java.rmi.*;

import java.util.*;

import javax.swing.*;

/**
 * The class that instantiates all GUI components.
 */
public class SudokuFrame
   extends JFrame
   implements WindowListener,
      VoidMethodInvokable
{
   /**
    * The Panel for this Frame.
    */
   private SudokuPanel mSudokuPanel;

   /**
    * Gets the panel for this frame.
    *
    * @return the panel for this frame.
    */
   public SudokuPanel getPanel()
   {
      return mSudokuPanel;
   }

   /**
    * Gets the client model.
    *
    * @return the client model.
    */
   public ClientModel getClientModel()
   {
      return mSudokuPanel.getClientModel();
   }

   /**
    * Constructs a new SudokuFrame instance.
    */
   public SudokuFrame()
   {
      super("Sudoku");
      mSudokuPanel = new SudokuPanel();
      init();
   }

   /**
    * Creates Sudoku frame.
    */
   void init()
   {
      setDefaultLookAndFeelDecorated(true);
      setResizable(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      getContentPane().setLayout(new BorderLayout());
      getContentPane().add(mSudokuPanel, BorderLayout.CENTER);
      setJMenuBar(new SudokuMenu());

      setSize(400, 525);
      addWindowListener(this);
      pack();
   }

   /**
    * Displays information about Sudoku.
    */
   public void about()
   {
      getPanel().about();
   }

   /**
    * Displays game play instructions.
    */
   public void howTo()
   {
      getPanel().howTo();
   }

   /**
    * Opens connection window.
    */
   public void connect()
   {
      getPanel().connect();
   }

   /**
    * Pauses the game.
    */
   public void pause()
   {
      getPanel().pause();
   }

   /**
    * Starts the game.
    */
   public void start()
   {
      getPanel().start();
   }

   /**
    * Displays a dialog where the user can choose a puzzle.
    */
   public void choosePuzzle()
   {
      getPanel().choosePuzzle();
   }

   /**
    * Displays a dialog where the user can choose options.
    */
   public void options()
   {
      getPanel().options();
   }

   /**
    * Vindicates something
    */
   public void cozener()
   {
      getPanel().cozener();
   }

   /**
    * Resumes the game from a paused state.
    */
   public void resume()
   {
      getPanel().resume();
   }

   /**
    * Checks to see if user input is correct.
    */
   public void checkProgress()
   {
      getPanel().checkProgress();
   }

   /**
    * Prints Sudoku puzzle.
    */
   public void print()
   {
      getPanel().print();
   }

   /**
    * Undoes action performed.
    */
   public void undo()
   {
      getPanel().undo();
   }

   /**
    * Redoes action performed.
    */
   public void redo()
   {
      getPanel().redo();
   }

   /**
    * Starts a computer opponent.
    */
   public void startComputerPlayer()
   {
      getPanel().startComputerPlayer();
   }
   
   /**
    * Clears all user input.
    */
   public void clear()
   {
      getPanel().clear();
   }

   /**
    * Invoked when the window is first made visible.
    *
    * @param e the window event.
    */
   public void windowOpened(WindowEvent e)
   {
      connect();
   }

   /**
    * Invoked when the user attempts to close the window from the window's
    * system menu.
    *
    * @param e the window event.
    */
   public void windowClosing(WindowEvent e)
   {
      exit();
   }

   /**
    * Asks user to verify exit.
    */
   public void exit()
   {
      boolean exitWithoutConfirmation = true;

      if (exitWithoutConfirmation ||
             (JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?", "Confirm Exit",
                JOptionPane.YES_NO_OPTION) == 0))
      {
         getClientModel().exitGame();
         System.exit(0);
      }
   }

   /**
    * Invoked when a window has been closed as the result of calling
    * dispose on the window (not implemented here).
    *
    * @param e the window event.
    */
   public void windowClosed(WindowEvent e)
   {
   }

   /**
    * For when a window has been closed as the result of calling
    * dispose on the window (not implemented here).
    *
    * @param e the window event.
    */
   public void windowActivated(WindowEvent e)
   {
   }

   /**
    * Invoked when a window is no longer the active window (not
    * implemented here).
    *
    * @param e the window event.
    */
   public void windowDeactivated(WindowEvent e)
   {
   }

   /**
    * Invoked when a window is changed from a normal to a minimized
    * state (not implemented here).
    *
    * @param e the window event.
    */
   public void windowIconified(WindowEvent e)
   {
   }

   /**
    * Invoked when a window is changed from a minimized to a normal
    * state (not implemented here).
    *
    * @param e the window event.
    */
   public void windowDeiconified(WindowEvent e)
   {
   }
}

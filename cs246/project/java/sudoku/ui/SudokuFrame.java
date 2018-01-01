// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/SudokuFrame.java,v 1.8 2006/06/17 15:46:14 benstodd Exp $
package sudoku.ui;

import sudoku.cs.*;

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
   implements WindowListener
{
   /**
    * The Panel for this Frame.
    */
   private SudokuPanel mSudokuPanel = new SudokuPanel();
   ;

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
      getPanel();
      makeFrame();
   }

   /**
    * Creates Sudoku frame.
    */
   void makeFrame()
   {
      setDefaultLookAndFeelDecorated(true);
      setResizable(true);
      //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      getContentPane().setLayout(new BorderLayout());
      getContentPane().add(mSudokuPanel, BorderLayout.CENTER);
      setJMenuBar(new SudokuMenu());
      setSize(400, 500);
      addWindowListener(this);
      pack();
   }

   /**
    * Invoked when the window is first made visible.
    *
    * @param e the window event.
    */
   public void windowOpened(WindowEvent e)
   {
      mSudokuPanel.connect();
   }

   /**
    * Invoked when the user attempts to close the window from the window's
    * system menu.
    *
    * @param e the window event.
    */
   public void windowClosing(WindowEvent e)
   {
      // delegate to Exit action to preserve game state and clean up
      // before really exiting.
      Exit.getInstance().actionPerformed(null);

      // call a server function to remove client and such
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

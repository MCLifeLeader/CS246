// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ai/SudokuApplet.java,v 1.10 2006/06/26 22:52:40 mbcarey Exp $
package sudoku.ai;

import sudoku.cs.*;

import sudoku.cs.event.*;

import sudoku.si.*;

import sudoku.ui.*;

import sudoku.ui.action.*;

import java.awt.*;
import java.awt.event.*;

import java.rmi.*;

import java.util.*;

import javax.swing.*;
import javax.swing.JApplet;

/**
 * web interface for sudoku
 *
 * @author $author$
 * @version $Revision: 1.10 $
 */
public class SudokuApplet
   extends JApplet
   implements VoidMethodInvokable
{
   /**
    * The Panel for this Applet.
    */
   private SudokuPanel mSudokuPanel;

   /**
    * Gets the panel for this Applet.
    *
    * @return the panel for this Applet.
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
    * Constructs a new SudokuApplet instance.
    */
   public SudokuApplet()
   {
      super();
      mSudokuPanel = new SudokuPanel();
      getContentPane().setLayout(new BorderLayout());
      getContentPane().add(mSudokuPanel, BorderLayout.CENTER);
      setJMenuBar(new SudokuMenu());
      setSize(400, 500);
      connect();
   }

   /**
    * shows sudoku
    */
   public void start()
   {
      setVisible(true);
      setSize(400, 500);
      getPanel().setSize(300, 500);
   }

   /**
    * run ClientModel game finalizer
    */
   public void stop()
   {
      exit();
   }

   /**
    * Opens about window.
    */
   public void about()
   {
      getPanel().about();
   }

   /**
    * Displays the howto
    */
   public void howto()
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
    * Pauses the game.
    */
   public void pause()
   {
      if (State.GAME_PAUSED.equals(Sudoku.getCurrentState()))
      {
         getClientModel().unpause();
      }
      else
      {
         getClientModel().pause();
      }
   }

   /**
    * Checks to see if user input is correct.
    */
   public void checkProgress()
   {
      getPanel().checkProgress();
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
         System.exit(0);
      }

      getClientModel().exitGame();
   }

   /**
    * Prints Sudoku puzzle.
    */
   public void print()
   {
      PrintUtility.printComponent(mSudokuPanel.getGameBoard());
   }

   /**
    * Starts a computer opponent.
    */
   public void startComputerPlayer()
   {
      getPanel().startComputerPlayer();
   }

   /**
    * undoes action performed.
    */
   public void undo()
   {
      mSudokuPanel.undo();
   }

   /**
    * redoes action performed.
    */
   public void redo()
   {
      mSudokuPanel.redo();
   }

   /**
    * Clears all user input.
    */
   public void clear()
   {
      getPanel().clear();
   }
}

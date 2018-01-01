// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/SudokuMenu.java,v 1.22 2006/06/26 21:02:54 emerrill Exp $
package sudoku.ui;

import sudoku.cs.*;

import sudoku.si.*;

import sudoku.ui.*;

import sudoku.ui.action.*;

import java.awt.*;
import java.awt.event.*;

import java.rmi.*;

import java.util.*;

import javax.swing.*;

/**
 * Class for all the menu items in the Sudoku frame.
 *
 * @author $Eric Merril$
 */
public class SudokuMenu
   extends JMenuBar
{
   /**
    * Constructs a new SudokuMenu instance.
    */
   public SudokuMenu()
   {
      add(new JMenu("File"));
      add(new JMenu("Edit"));
      add(new JMenu("Settings"));
      add(new JMenu("Help"));

      // add menu items for File menu
      JMenuItem choosePuzzle = new JMenuItem(ChoosePuzzle.getInstance());
      getMenu(0).add(choosePuzzle);

      getMenu(0).addSeparator();

      JMenuItem print = new JMenuItem(Print.getInstance());
      getMenu(0).add(print);

      getMenu(0).add(new Exit());

      Undo undo = Undo.getInstance();
      undo.setEnabled(false);
      getMenu(1).add(undo);

      Redo redo = Redo.getInstance();
      redo.setEnabled(false);
      getMenu(1).add(redo);

      getMenu(1).addSeparator();

      Clear clear = Clear.getInstance();
      getMenu(1).add(clear);

      Cozener cozener = Cozener.getInstance();
      getMenu(1).add(cozener).setVisible(false);

      JMenuItem pause = new JMenuItem(Pause.getInstance());
      getMenu(1).add(pause);

      JMenuItem compPlayer = new JMenuItem(StartComputerPlayer.getInstance());
      getMenu(2).add(compPlayer);      

      JMenuItem connect = new JMenuItem(new Connect());
      getMenu(2).add(connect);

      JMenuItem options = new JMenuItem(new Options());
      getMenu(2).add(options);

      getMenu(3).add(new HowTo());

      getMenu(3).add(new About());
   }
}

// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/SudokuMenu.java,v 1.6 2006/06/17 15:46:14 benstodd Exp $
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
 * Class for all the menu items in the Sudoku frame.
 *
 * @author $Eric Merril$
 */
public class SudokuMenu
   extends JMenuBar
{
   /**
    * Creates a new SudokuMenu object.
    */
   public SudokuMenu()
   {
      makeMenu();
   }

   /**
    * Creates various menus and addes items to them.
    */
   private void makeMenu()
   {
      add(new JMenu("File"));
      add(new JMenu("Edit"));
      add(new JMenu("Player"));
      add(new JMenu("Help"));

      // add menu items for File menu
      JMenuItem newPuzzle = new JMenuItem("New Puzzle");
      newPuzzle.setEnabled(false); //remove me later
      getMenu(0).add(newPuzzle);

      JMenuItem open = new JMenuItem("Open File...");
      open.setEnabled(false); //remove me later
      getMenu(0).add(open);

      getMenu(0).addSeparator();

      JMenuItem print = new JMenuItem(Print.getInstance());
      getMenu(0).add(print);

      getMenu(0).addSeparator();

      JMenuItem save = new JMenuItem("Save");
      save.setEnabled(false); //remove me later
      getMenu(0).add(save);

      JMenuItem saveAs = new JMenuItem("Save As...");
      saveAs.setEnabled(false); //remove me later
      getMenu(0).add(saveAs);

      getMenu(0).addSeparator();

      JMenuItem exitItem = new JMenuItem(Exit.getInstance());
      getMenu(0).add(exitItem);

      // TODO: add menu items for Edit menu
      JMenuItem undo = new JMenuItem("undo");
      undo.setEnabled(false); //remove me later
      getMenu(1).add(undo);

      JMenuItem redo = new JMenuItem("redo");
      redo.setEnabled(false); //remove me later
      getMenu(1).add(redo);

      getMenu(1).addSeparator();

      JMenuItem clear = new JMenuItem("Clear Puzzle");
      clear.addActionListener(new Clear());
      //clear.setEnabled(false); //remove me later
      getMenu(1).add(clear);

      JMenuItem pause = new JMenuItem("Pause");
      pause.setEnabled(false); //remove me later
      getMenu(1).add(pause);

      getMenu(1).addSeparator();

      JMenu optionSubMenu = new JMenu("options");
      ButtonGroup group = new ButtonGroup();
      JRadioButtonMenuItem aButton = new JRadioButtonMenuItem(
            "Competitive Timer - Always display timer.");
      group.add(aButton);
      optionSubMenu.add(aButton);
      aButton = new JRadioButtonMenuItem(
            "Regular Timer - Only show timer when done.");
      aButton.setSelected(true);
      group.add(aButton);
      optionSubMenu.add(aButton);
      aButton = new JRadioButtonMenuItem("Relaxed Timer - Never show timer.");
      group.add(aButton);
      optionSubMenu.add(aButton);
      optionSubMenu.addSeparator();

      ButtonGroup group2 = new ButtonGroup();
      aButton = new JRadioButtonMenuItem(
            "Strict Help - Warn me when a number is wrong");
      group2.add(aButton);
      optionSubMenu.add(aButton);
      aButton = new JRadioButtonMenuItem(
            "Regular Help - Only warn of visible mistakes.");
      aButton.setSelected(true);
      group2.add(aButton);
      optionSubMenu.add(aButton);
      aButton = new JRadioButtonMenuItem(
            "Helpful Help - Show me the position of wrong numbers.");
      group2.add(aButton);
      optionSubMenu.add(aButton);
      getMenu(1).add(optionSubMenu);

      // TODO: add menu items for Player menu

      //add menu items for Help menu
      JMenuItem howTo = new JMenuItem("How to play...");
      howTo.setEnabled(false); //remove me later
      getMenu(3).add(howTo);

      JMenuItem aboutItem = new JMenuItem(About.getInstance());
      getMenu(3).add(aboutItem);
   }
}

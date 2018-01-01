// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/ChoosePuzzleDialog.java,v 1.3 2006/06/26 17:29:55 emerrill Exp $
package sudoku.ui;

import sudoku.al.*;

import sudoku.si.*;

import java.awt.*;
import java.awt.event.*;

import java.util.*;

import javax.swing.*;

/**
 * Allows the user to choose a puzzle.
 */
public class ChoosePuzzleDialog
   extends JDialog
{
   /**
    * The Sudoku Panel.
    */
   private SudokuPanel mSudokuPanel;

   /**
    * The Level label.
    */
   private JLabel mLevelLabel;

   /**
    * The Number label;
    */
   private JLabel mNumberLabel;

   /**
    * The Number field;
    */
   private JTextField mNumberField;

   /**
    * The Level choice box.
    */
   private JComboBox mLevelChoices;

   /**
    * The help label.
    */
   private JLabel mHelpLabel;

   /**
    * The OK button.
    */
   private JButton mOKButton;

   /**
    * Constructs a new ChoosePuzzleDialog instance.
    *
    * @param inSudokuPanel the Sudoku Panel.
    */
   public ChoosePuzzleDialog(SudokuPanel inSudokuPanel)
   {
      super(Sudoku.getFrame(), "Choose Puzzle", true);
      mSudokuPanel = inSudokuPanel;
      mHelpLabel = new JLabel(
            "Leave the number blank to choose a random puzzle.");
      mLevelLabel = new JLabel("Level: ");
      mNumberLabel = new JLabel("Number: ");
      mNumberField = new JTextField("", 12);
      mLevelChoices = new JComboBox();
      mLevelChoices.addItem(Puzzle.Level.Easy.toString());
      mLevelChoices.addItem(Puzzle.Level.Medium.toString());
      mLevelChoices.addItem(Puzzle.Level.Hard.toString());
      mLevelChoices.addItem(Puzzle.Level.Evil.toString());
      mOKButton = new JButton("OK");
      mOKButton.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               mSudokuPanel.getClientModel()
                           .newPuzzle(mLevelChoices.getSelectedItem().toString());
               mSudokuPanel.getStartButton().setEnabled(true);
               setVisible(false);
            }
         });
      setLayout(new GridLayout(3, 2));
      setResizable(true);

      add(mLevelLabel);
      add(mLevelChoices);
      add(mNumberLabel);
      add(mNumberField);
      add(mOKButton);
      add(mHelpLabel);
      pack();
   }
}

// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/OptionsDialog.java,v 1.5 2006/06/27 16:11:42 mbcarey Exp $
package sudoku.ui;

import sudoku.cs.*;

import sudoku.cs.event.*;

import sudoku.si.*;

import java.awt.*;
import java.awt.event.*;

import java.rmi.*;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Displays player options and allows them to be changed.
 *
 * @author Alan Chase
 */
public class OptionsDialog
   extends JDialog
{
   /**
    * The Sudoku Panel.
    */
   private SudokuPanel mSudokuPanel;

   /**
    * Name label
    */
   private JLabel mLabelPuzzleTimer;

   /**
    * IP address label
    */
   private JLabel mLabelHowDoing;

   /**
    * The check box that decides if pencil marks are allowed.
    * A check indicates that they are.
    */
   private JCheckBox mCheckBoxPencil;

   /**
    * The text field that receives the desired username.
    */
   private JTextField mTextName;

   /**
    * The text field for the IP address.
    */
   private JTextField mTextAddress;

   /**
    * The OK button.
    */
   private JButton mOk;

   /**
    * The cancel button.
    */
   private JButton mCancel;

   /**
    * A radio button group for the timer options.
    */
   private ButtonGroup mGroupTimer;

   /**
    * A radio button group for the progress check options.
    */
   private ButtonGroup mGroupHowDoing;

   /**
    * Constructs a new OptionsDialog instance.
    *
    * @param inSudokuPanel the Sudoku Panel.
    */
   public OptionsDialog(SudokuPanel inSudokuPanel)
   {
      super(Sudoku.getFrame(), "Options", true);
      mSudokuPanel = inSudokuPanel;
      mLabelPuzzleTimer = new JLabel("Puzzle Timer:");
      mLabelHowDoing = new JLabel("How am I doing:");
      mCheckBoxPencil = new JCheckBox("Pencil marking allowed");
      mOk = new JButton("OK");
      mCancel = new JButton("Cancel");
      mGroupTimer = new ButtonGroup();
      mGroupHowDoing = new ButtonGroup();
      setSize(450, 320);
      init();
   }

   /**
    * Called by constructor.  Sets up dialog panel.
    */
   private void init()
   {
      SpringLayout layout = new SpringLayout();
      setLayout(layout);
      setResizable(false);
      setLocationRelativeTo(Sudoku.getFrame());

      add(mLabelPuzzleTimer);
      add(mLabelHowDoing);
      add(mCheckBoxPencil);

      JRadioButton radioButtonCompetitive = new JRadioButton(
            "Competitive - Show me the timer while I'm solving.");
      JRadioButton radioButtonRegular = new JRadioButton(
            "Regular - Show me the time when I finish.");
      JRadioButton radioButtonRelax = new JRadioButton(
            "Relaxed - I just don't want to know!");

      JRadioButton radioButtonStrict = new JRadioButton(
            "Only tell me about visible mistakes.");
      JRadioButton radioButtonNormal = new JRadioButton(
            "Warn me when a number is wrong.");
      JRadioButton radioButtonHelpful = new JRadioButton(
            "Show me the positions of wrong numbers.");

      RadioButtonListener radioButtonListener = new RadioButtonListener();
      radioButtonCompetitive.setMnemonic(KeyEvent.VK_M);
      radioButtonCompetitive.setActionCommand("competitiveTimer");
      radioButtonCompetitive.addActionListener(new TimerRadioButtonListener());
      mGroupTimer.add(radioButtonCompetitive);
      add(radioButtonCompetitive);

      radioButtonRegular.setMnemonic(KeyEvent.VK_L);
      radioButtonRegular.setActionCommand("regularTimer");
      radioButtonRegular.setSelected(true);
      radioButtonRegular.addActionListener(new TimerRadioButtonListener());
      mGroupTimer.add(radioButtonRegular);
      add(radioButtonRegular);

      radioButtonRelax.setMnemonic(KeyEvent.VK_L);
      radioButtonRelax.setActionCommand("relaxedTimer");
      radioButtonRelax.addActionListener(new TimerRadioButtonListener());
      mGroupTimer.add(radioButtonRelax);
      add(radioButtonRelax);

      radioButtonStrict.setMnemonic(KeyEvent.VK_L);
      radioButtonStrict.setActionCommand("strictCheck");
      radioButtonStrict.addActionListener(radioButtonListener);
      mGroupHowDoing.add(radioButtonStrict);
      add(radioButtonStrict);

      radioButtonNormal.setMnemonic(KeyEvent.VK_L);
      radioButtonNormal.setActionCommand("normalCheck");
      radioButtonNormal.setSelected(true);
      radioButtonNormal.addActionListener(radioButtonListener);
      mGroupHowDoing.add(radioButtonNormal);
      add(radioButtonNormal);

      radioButtonHelpful.setMnemonic(KeyEvent.VK_L);
      radioButtonHelpful.setActionCommand("helpfulCheck");
      radioButtonHelpful.addActionListener(radioButtonListener);
      mGroupHowDoing.add(radioButtonHelpful);
      add(radioButtonHelpful);

      mOk.addActionListener(new OKButtonActionListener());
      add(mOk);

      mCheckBoxPencil.addActionListener(new CheckBoxListener());

      layout.putConstraint(SpringLayout.WEST, mLabelPuzzleTimer, 15,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, mLabelPuzzleTimer, 15,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, radioButtonCompetitive, 50,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, radioButtonCompetitive, 35,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, radioButtonRegular, 50,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, radioButtonRegular, 55,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, radioButtonRelax, 50,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, radioButtonRelax, 75,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, mLabelHowDoing, 15,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, mLabelHowDoing, 110,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, radioButtonStrict, 50,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, radioButtonStrict, 130,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, radioButtonNormal, 50,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, radioButtonNormal, 150,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, radioButtonHelpful, 50,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, radioButtonHelpful, 170,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, mCheckBoxPencil, 15,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, mCheckBoxPencil, 210,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, mOk, 270, SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, mOk, 260, SpringLayout.NORTH,
         this);
   }

   /**
    * Listens for OK button
    */
   class OKButtonActionListener
      implements ActionListener
   {
      /**
       * Sets the dialog window to be invisible when pressed.
       *
       * @param event is when ok button is pressed.
       */
      public void actionPerformed(ActionEvent event)
      {
         setVisible(false);
      }
   }

   /**
    * Listens for radio buttons to be pushed
    */
   class RadioButtonListener
      implements ActionListener
   {
      /**
       * Determines which radio button is selected.
       *
       * @param event enter key
       */
      public void actionPerformed(ActionEvent event)
      {
         try
         {
            mSudokuPanel.getClass().getMethod(event.getActionCommand())
                        .invoke(mSudokuPanel);
         }
         catch (Exception ex)
         {
            Logger.debug(">*>*>*> " + ex);
         }
      }
   }

   /**
    * Listens for radio buttons to be pushed
    */
   class TimerRadioButtonListener
      implements ActionListener
   {
      /**
       * Determines which radio button is selected.
       *
       */
      public void actionPerformed(ActionEvent ae)
      {
         mSudokuPanel.setTimerMode(ae.getActionCommand());
         Logger.debug("The actionCommand is " + ae.getActionCommand());
      }
   }

   /**
    * Listens for checkbox button to be checked
    */
   class CheckBoxListener
      implements ActionListener
   {
      /**
       * Sets the check box state.
       *
       * @param event the action event.
       */
      public void actionPerformed(ActionEvent event)
      {
         try
         {
            JCheckBox checkbox = (JCheckBox) event.getSource();
            mSudokuPanel.setPencilMarkingAllowed(checkbox.isSelected());
         }
         catch (Exception ex)
         {
            Logger.debug(">*>*>*> " + ex);
         }
      }
   }
}

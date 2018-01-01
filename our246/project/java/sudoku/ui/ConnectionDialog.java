// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/ConnectionDialog.java,v 1.21 2006/06/27 01:33:32 rk223 Exp $
package sudoku.ui;

import sudoku.cs.*;

import sudoku.cs.event.*;

import sudoku.si.*;

import java.awt.*;
import java.awt.event.*;

import java.net.*;

import java.rmi.*;

import java.util.*;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Creates the window for start up options.
 *
 * @author Eric Merrill
 */
public class ConnectionDialog
   extends JDialog
{
   /**
    * The Sudoku Panel.
    */
   private SudokuPanel mSudokuPanel;

   /**
    * Name label
    */
   private JLabel mLabelName;

   /**
    * IP address label
    */
   private JLabel mLabelAddress;

   /**
    * Text field for the name.
    */
   private JTextField mTextName;

   /**
    * Text field for the IP address.
    */
   private JTextField mTextAddress;

   /**
    * OK button.
    */
   private JButton mOk;

   /**
    * Cancel Button
    */
   private JButton mCancel;

   /**
    * Group for the radio buttons.
    */
   private ButtonGroup mGroup;

   /**
    * Constructs a new ConnectionDialog instance.
    *
    * @param inSudokuPanel the Sudoku Panel.
    */
   public ConnectionDialog(SudokuPanel inSudokuPanel)
   {
      super(Sudoku.getFrame(), "Connection Options", true);
      mSudokuPanel = inSudokuPanel;
      mLabelName = new JLabel("Username");
      mLabelAddress = new JLabel("IP Address");
      mTextName = new JTextField(Config.get("user.name", "No Name"), 12);
      mTextAddress = new JTextField(12);
      mOk = new JButton("OK");
      mCancel = new JButton("Cancel");
      mGroup = new ButtonGroup();
      init();
   }

   /**
    * Called by constructor.  Sets up dialog panel.
    */
   private void init()
   {
      SpringLayout layout = new SpringLayout();
      Properties pr = System.getProperties();
      String osName = pr.getProperty("os.name");
      JLabel mode = new JLabel("<html><strong>Select Game Mode</strong></html>");

      try
      {
         InetAddress inet = InetAddress.getLocalHost();
         String localIPAddress = inet.getHostAddress();
         mTextAddress.setText(localIPAddress);
         mSudokuPanel.setIPAddress(localIPAddress);
      }
      catch (Exception e)
      {
      }

      Logger.debug("The OS is : " + osName);
      setLayout(layout);
      setResizable(false);
      setLocationRelativeTo(mSudokuPanel);

      if (! osName.equals("Linux"))
      {
         setSize(260, 275);
      }
      else
      {
         setSize(240, 225);
      }

      add(mLabelName);
      add(mTextName);
      add(mLabelAddress);
      add(mTextAddress);
      add(mode);
      mTextAddress.setEnabled(false);

      JRadioButton radioButtonA = new JRadioButton("Multiplayer");

      radioButtonA.setMnemonic(KeyEvent.VK_M);
      radioButtonA.setActionCommand("false");

      RadioButtonListener radioButtonListener = new RadioButtonListener();
      radioButtonA.addActionListener(radioButtonListener);
      mGroup.add(radioButtonA);
      add(radioButtonA);

      JRadioButton radioButtonB = new JRadioButton("Single Player");

      radioButtonB.setMnemonic(KeyEvent.VK_L);
      radioButtonB.setSelected(mSudokuPanel.isConnectionLocal());
      radioButtonB.setActionCommand("true");
      radioButtonB.addActionListener(radioButtonListener);
      mGroup.add(radioButtonB);
      add(radioButtonB);

      mOk.addActionListener(new OKButtonActionListener());
      add(mOk);

      mCancel.addActionListener(new CancelButtonListener());
      add(mCancel);

      layout.putConstraint(SpringLayout.WEST, mLabelName, 15,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, mLabelName, 15,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, mTextName, 85, SpringLayout.WEST,
         this);
      layout.putConstraint(SpringLayout.NORTH, mTextName, 15,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, mode, 15, SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, mode, 45, SpringLayout.NORTH,
         this);

      layout.putConstraint(SpringLayout.WEST, radioButtonA, 15,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, radioButtonA, 65,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, radioButtonB, 15,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, radioButtonB, 90,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, mLabelAddress, 15,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, mLabelAddress, 130,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, mTextAddress, 85,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, mTextAddress, 130,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, mOk, 50, SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, mOk, 160, SpringLayout.NORTH,
         this);

      layout.putConstraint(SpringLayout.WEST, mCancel, 120, SpringLayout.WEST,
         this);
      layout.putConstraint(SpringLayout.NORTH, mCancel, 160,
         SpringLayout.NORTH, this);
   }

   /**
    * Overides the close operation
    */
   public void processWindowEvent(WindowEvent e)
   {
      mSudokuPanel.setConnectionChange(false);
      super.processWindowEvent(e);
      if (e.getID() == WindowEvent.WINDOW_CLOSING)
      {
         switch (getDefaultCloseOperation())
         {
            case HIDE_ON_CLOSE:
               setVisible(false);

               break;

            case DISPOSE_ON_CLOSE:
               setVisible(false);
               dispose();

               break;

            case DO_NOTHING_ON_CLOSE:default:
               break;
         }
      }
   }

   /**
    * Listens for OK button
    *
    * @author $Eric Merrill$
    * @version $1.1$
    */
   class OKButtonActionListener
      implements ActionListener
   {
      /**
       * Gets text from text fields and starts a server.
       *
       * @param event enter key
       */
      public void actionPerformed(ActionEvent event)
      {
         mSudokuPanel.getClientModel().setName(mTextName.getText());
         mSudokuPanel.setIPAddress(mTextAddress.getText());
         mSudokuPanel.setConnectionChange(true);
         
         if (Sudoku.getCurrentState() == State.GAME_IN_PROGRESS)
         {
            mSudokuPanel.closePanel();
            mSudokuPanel.disableGamePlayButtons();
         }

         setVisible(false);
      }
   }

   /**
    * Listens for Cancel Button
    *
    * @author $Randall King
    * @version $1.0$
    */
   class CancelButtonListener
      implements ActionListener
   {
      /**
       * Should just make the window disapear
       */
      public void actionPerformed(ActionEvent event)
      {
         mSudokuPanel.setConnectionChange(false);
         setVisible(false);
      }
   }

   /**
    * Listens for radio buttons to be pushed
    *
    * @author $Eric Merrill$
    * @version $1.1$
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
         mSudokuPanel.getClientModel().exitGame();
         boolean setLocal = Boolean.parseBoolean(event.getActionCommand());
         mSudokuPanel.setConnectionLocal(setLocal);
         mTextAddress.setEnabled(! setLocal);
      }
   }
}

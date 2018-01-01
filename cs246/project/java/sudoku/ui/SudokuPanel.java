// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/SudokuPanel.java,v 1.14 2006/06/17 15:46:14 benstodd Exp $
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
import javax.swing.text.*;

/**
 * The main panel for Sudoku
 */
public class SudokuPanel
   extends JPanel
   implements GameStatusChangedListener
{
   /**
    * The Matrix array reference to the elements in the panel
    */
   private JFormattedTextField[][] mFields;

   /**
    * The game board panel.
    */
   private JPanel mBoard;

   /**
    * A reference to the ClientModel instance.
    */
   private ClientModel mClientModel;

   /**
    * DOCUMENT ME!
    */
   private NumberButton mBPencil;

   /**
    * Constructs a new SudokuPanel instance.
    */
   public SudokuPanel()
   {
      try
      {
         mClientModel = new ClientModel();
      }
      catch (Exception e)
      {
         // should never happen
      }

      setLayout(new BorderLayout());
      mBoard = new JPanel();
      mFields = new JFormattedTextField[9][9];
      setUp();

      //Set a default puzzle
      setDefault();
      createPanel();
      // TODO: add certain components as
      // ClientModel GameStatusListeners, e.g., this
      mClientModel.addGameStatusChangedListener(this);
   }

   /**
    * Clears all user input
    * won't touch Puzzle values
    */
   public void clear()
   {
      for (int i = 0; i < 9; i++)
      {
         for (int j = 0; j < 9; j++)
         {
            //mBoard.remove(mFields[j][i]);
            mFields[j][i].setText("");
         }
      }

      //setUp();
      setDefault();
      Sudoku.getFrame().setVisible(true);
   }

   /**
    *
    */
   public void createPanel()
   {
      JPanel bottomPanel = new JPanel();
      JPanel rightPanel = new JPanel();
      rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

      mBPencil = new NumberButton("Pencil", false);

      JButton bHowDoing = new JButton("Check Progress");
      JButton bPause = new JButton("Pause");
      JButton bPrint = new JButton("Print...");
      JButton bClear = new JButton("Clear");
      JButton bOptions = new JButton("Options");
      JLabel timeLabel = new JLabel("Time: ");

      bClear.addActionListener(new Clear());
      bottomPanel.add(mBPencil);
      bottomPanel.add(bHowDoing);
      bottomPanel.add(bPause);
      bottomPanel.add(bClear);
      rightPanel.add(timeLabel);
      this.add(bottomPanel);

      //Create a split pane with the two scroll panes in it.
      JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            mBoard, rightPanel);
      splitPane.setOneTouchExpandable(true);
      splitPane.setDividerLocation(400);

      //Provide minimum sizes for the two components in the split pane
      Dimension minimumSize = new Dimension(100, 50);
      setMinimumSize(minimumSize);
      rightPanel.setMinimumSize(minimumSize);
      add(BorderLayout.CENTER, splitPane);
      add(BorderLayout.SOUTH, bottomPanel);

      //splitPane.setResizeWeight(.99);
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Boolean isPencilSelected()
   {
      return mBPencil.isSelected();
   }

   /**
    * Does the background set for the Soduku Panel
    * will
    */
   public void setUp()
   {
      mBoard.setLayout(new GridLayout(9, 9)); //changed from borderlayout
      mBoard.setPreferredSize(new Dimension(500, 500));

      Color aColor = Color.white;
      Color lightBlue = new Color(160, 180, 255);

      for (int i = 0; i < 9; i++)
      {
         //  When we move down three rows change color
         if (((i == 3) || (i == 6)) && (aColor == Color.white))
         {
            aColor = lightBlue;
         }
         else if (((i == 3) || (i == 6)) && (aColor == lightBlue))
         {
            aColor = Color.white;
         }

         for (int j = 0; j < 9; j++)
         {
            // When we move over three columns change color
            if (((j == 3) || (j == 6)) && (aColor == lightBlue))
            {
               aColor = Color.white;
            }
            else if (((j == 3) || (j == 6)) && (aColor == Color.white))
            {
               aColor = lightBlue;
            }

            //            field.setBackground(aColor);
            Field field = new Field(aColor);
            mFields[j][i] = field.getActualNumField();
            mBoard.add(field);
         }
      }
   }

   /**
    * Sets the puzzle to some default values.
    * This more then likely will be removed
    */
   public void setDefault()
   {
      mFields[1][0].setText("1");
      mFields[1][0].setEditable(false);
      mFields[3][0].setText("3");
      mFields[3][0].setEditable(false);
      mFields[6][0].setText("5");
      mFields[6][0].setEditable(false);
      mFields[0][1].setText("3");
      mFields[0][1].setEditable(false);
      mFields[3][1].setText("4");
      mFields[3][1].setEditable(false);
      mFields[7][1].setText("1");
      mFields[7][1].setEditable(false);
      mFields[8][1].setText("7");
      mFields[8][1].setEditable(false);
      mFields[0][2].setText("4");
      mFields[0][2].setEditable(false);
      mFields[1][2].setText("7");
      mFields[1][2].setEditable(false);
      mFields[3][2].setText("1");
      mFields[3][2].setEditable(false);
      mFields[5][2].setText("2");
      mFields[5][2].setEditable(false);
      mFields[0][3].setText("5");
      mFields[0][3].setEditable(false);
      mFields[4][3].setText("3");
      mFields[4][3].setEditable(false);
      mFields[6][3].setText("8");
      mFields[6][3].setEditable(false);
      mFields[8][3].setText("9");
      mFields[8][3].setEditable(false);
      mFields[1][4].setText("9");
      mFields[1][4].setEditable(false);
      mFields[3][4].setText("8");
      mFields[3][4].setEditable(false);
      mFields[4][4].setText("2");
      mFields[4][4].setEditable(false);
      mFields[5][4].setText("6");
      mFields[5][4].setEditable(false);
      mFields[7][4].setText("5");
      mFields[7][4].setEditable(false);
      mFields[0][5].setText("1");
      mFields[0][5].setEditable(false);
      mFields[2][5].setText("3");
      mFields[2][5].setEditable(false);
      mFields[4][5].setText("4");
      mFields[4][5].setEditable(false);
      mFields[8][5].setText("2");
      mFields[8][5].setEditable(false);
      mFields[3][6].setText("6");
      mFields[3][6].setEditable(false);
      mFields[5][6].setText("3");
      mFields[5][6].setEditable(false);
      mFields[7][6].setText("7");
      mFields[7][6].setEditable(false);
      mFields[8][6].setText("5");
      mFields[8][6].setEditable(false);
      mFields[0][7].setText("8");
      mFields[0][7].setEditable(false);
      mFields[1][7].setText("6");
      mFields[1][7].setEditable(false);
      mFields[5][7].setText("4");
      mFields[5][7].setEditable(false);
      mFields[8][7].setText("1");
      mFields[8][7].setEditable(false);
      mFields[2][8].setText("7");
      mFields[2][8].setEditable(false);
      mFields[5][8].setText("9");
      mFields[5][8].setEditable(false);
      mFields[7][8].setText("8");
      mFields[7][8].setEditable(false);
   }

   /**
    * Gets the ClientModel.
    *
    * @return the ClientModel.
    */
   public ClientModel getClientModel()
   {
      return mClientModel;
   }

   /**
    * Connects client to server.
    */
   void connect()
   {
      // TODO: move this code to SudokuPanel and have it
      // display a "player options" dialog, which among
      // other things will allow the user to change his/her
      // name before adding the player to the Server.
      String name = Config.get("user.name", "No Name");
      Object input = JOptionPane.showInputDialog(this,
            "Please enter your name", "Connect to Server",
            JOptionPane.QUESTION_MESSAGE, (Icon) null, (Object[]) null, name);
      String entered = (String) input;
      Logger.debug(entered);

      getClientModel().setName(entered);

      boolean local = (JOptionPane.showConfirmDialog(this, "Connect locally?",
            "Local or Remote", JOptionPane.YES_NO_OPTION) == 0);

      String errorMessage = null;

      boolean connected = (local ? getClientModel().connectLocal()
                                 : getClientModel().connectRemote());

      if (connected)
      {
         try
         {
            boolean isWord = getClientModel().wordLookup(entered);
            JOptionPane.showMessageDialog(this,
               "\"" + entered + (isWord ? "\" is" : "\" is not")
               + " in the dictionary.", "Connection was successful",
               JOptionPane.INFORMATION_MESSAGE);
         }
         catch (RemoteException re)
         {
            connected = false;
            errorMessage = re.toString();
         }
      }

      if (! connected)
      {
         JOptionPane.showMessageDialog(this, errorMessage,
            "Connection was unsuccessful", JOptionPane.ERROR_MESSAGE);
      }
   }

   /**
    * Gets called when the game's status changes.
    *
    * @param e event generated when the game's status changes.
    */
   public void gameStatusChanged(GameStatusChangedEvent e)
   {
      // TODO: figure out when it's necessary to know
      // what the previous state was in order to decide what to do.
      switch (e.getCurrState())
      {
         case LOOKING_FOR_SERVER:
            break;

         case WAITING_FOR_PLAYERS:
            break;

         case GAME_IN_PROGRESS:
            break;

         case GAME_OVER:
            break;

         default:
            break;
      }
   }
}

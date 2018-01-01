// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/SudokuPanel.java,v 1.76 2006/06/27 01:21:45 rk223 Exp $
package sudoku.ui;

import sudoku.cs.*;

import sudoku.cs.event.*;

import sudoku.si.*;

import sudoku.ui.action.*;

import java.awt.*;

import java.net.*;

import java.util.*;

import javax.swing.*;

/**
 * The main panel for Sudoku.
 */
public class SudokuPanel
   extends JPanel
   implements GameStatusChangedListener,
      PlayerChangedListener,
      PuzzleChangedListener,
      VoidMethodInvokable
{
   /**
    * The About object.
    */
   private AboutDialog mAboutDialog;

   /**
    * The connection dialog.
    */
   private ConnectionDialog mConnectionDialog;

   /**
    * The Matrix array reference to the elements in the panel.
    */
   private JTextField[][] mFields;

   /**
    * The CommandInvoker stores the history of commands, and can undo them
    */
   private CommandInvoker mCommandInvoker;

   /**
    * The game board panel.
    */
   private JPanel mBoard;

   /**
    * The split pane between the two panels.
    */
   private JSplitPane mSplitPane;

   /**
    * The game board while paused.
    */
   private JPanel mPausedBoard;

   /**
    * Set in the close operation, true if the user pressed okay
    * false if canceled or exited
    */
   private boolean mConnectionChangeRequested;

   /**
    * The game board when gameover.
    */
   private JPanel mGameoverBoard;

   /**
    * The pause/resume button.
    */
   private JButton mPauseButton;

   /**
    * The timer label.
    */
   private JLabel mTimeLabel;

   /**
    * True if the competitive timer is chosen.
    */
   private boolean mTimerCompetitive;

   /**
    * True if the regular timer is chosen.
    */
   private boolean mTimerRegular;

   /**
    * True if the relaxed timer is chosen.
    */
   private boolean mTimerRelaxed;

   /**
    * A reference to the ClientModel instance.
    */
   private ClientModel mClientModel;

   /**
    * The button to start the game. Only enabled if Host.
    */
   private JButton mStartButton;

   /**
    * The button to select a new puzzle. Only enabled if Host.
    */
   private JButton mPuzzleButton;

   /**
    * The button to check progress.
    */
   private JButton mProgressButton;

   /**
    * The button to clear board.
    */
   private JButton mClearButton;

   /**
    * The Choose Puzzle Dialog.
    */
   private JDialog mChoosePuzzleDialog;

   /**
    * The Options Dialog.
    */
   private JDialog mOptionsDialog;

   /**
    * Whether or not to make a local connection.
    */
   private boolean mIsConnectionLocal;

   /**
    * Where the player data is being held. This is stats, names etc.
    */
   private PlayerDataModel mPlayerDataModel;

   /**
    * The IP Address for making a remote connection.
    */
   private String mIPAddress;

   /**
    * The IP Address on local machine.
    */
   private String mLocalIPAddress;

   /**
    * Whether or not pencil marking is allowed.
    */
   private boolean mIsPencilMarkingAllowed;

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
         Logger.debug("Unable to instantiate new ClientModel.");
      }

      mCommandInvoker = new CommandInvoker();
      setLayout(new BorderLayout());
      mIsConnectionLocal = true;
      mIsPencilMarkingAllowed = false;
      mIPAddress = null;

      mBoard = new JPanel();
      mPausedBoard = new JPanel();
      mGameoverBoard = new JPanel();
      mFields = new JTextField[9][9];
      mAboutDialog = new AboutDialog();
      mPauseButton = new JButton(Pause.getInstance());
      mStartButton = new JButton(Start.getInstance());
      mPuzzleButton = new JButton(ChoosePuzzle.getInstance());
      mProgressButton = new JButton(CheckProgress.getInstance());
      mClearButton = new JButton(Clear.getInstance());
      mConnectionDialog = new ConnectionDialog(this);
      mChoosePuzzleDialog = new ChoosePuzzleDialog(this);
      mOptionsDialog = new OptionsDialog(this);
      mPlayerDataModel = new PlayerDataModel();
      mTimerCompetitive = false;
      mTimerRegular = true;
      mTimerRelaxed = false;

      mConnectionChangeRequested = false;
      setUp();

      //Set a default puzzle
      init();

      mClientModel.addGameStatusChangedListener(this);
      mClientModel.addPuzzleChangedListener(this);
      mClientModel.addPlayerChangedListener(this);
   }

   /**
    * Called by constructor to set up panel.
    */
   public void init()
   {
      JPanel bottomPanel = new JPanel();
      JPanel rightPanel = new JPanel();
      JEditorPane pauseDisplay = new JEditorPane("bold", "CS246 SUDOKU!");
      JEditorPane gameoverDisplay = new JEditorPane("bold", "Game Over");
      pauseDisplay.setFont(new Font("Serif", 0, 36));
      gameoverDisplay.setFont(new Font("Serif", 0, 36));

      rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

      mPausedBoard.setLayout(new BorderLayout());
      mPausedBoard.setPreferredSize(new Dimension(400, 400));
      mGameoverBoard.setLayout(new BorderLayout());
      mGameoverBoard.setPreferredSize(new Dimension(400, 400));
      mBoard.setPreferredSize(new Dimension(400, 400));

      mPausedBoard.add(pauseDisplay, BorderLayout.CENTER);
      mGameoverBoard.add(gameoverDisplay, BorderLayout.CENTER);

      mStartButton.setEnabled(false);
      mPuzzleButton.setEnabled(false);
      mProgressButton.setEnabled(false);
      mPauseButton.setEnabled(false);
      mClearButton.setEnabled(false);

      bottomPanel.add(mStartButton);
      bottomPanel.add(mPuzzleButton);
      bottomPanel.add(mProgressButton);
      bottomPanel.add(mPauseButton);
      bottomPanel.add(mClearButton);

      mTimeLabel = new JLabel("");
      rightPanel.add(mTimeLabel);

      JTable jtbl = new JTable(mPlayerDataModel);
      JScrollPane jsp = new JScrollPane(jtbl);
      rightPanel.add(jsp);

      add(bottomPanel);

      // Create a split pane with the two scroll panes in it.
      mSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mPausedBoard,
            rightPanel);
      mSplitPane.setOneTouchExpandable(true);
      mSplitPane.setDividerLocation(400);

      Dimension minimumSize = new Dimension(100, 50);
      setMinimumSize(minimumSize);
      rightPanel.setMinimumSize(minimumSize);
      add(BorderLayout.CENTER, mSplitPane);
      add(BorderLayout.SOUTH, bottomPanel);
   }

   /**
    * Gets the Command Invoker.
    *
    * @return the Command Invoker.
    */
   public CommandInvoker getCommandInvoker()
   {
      return mCommandInvoker;
   }

   /**
    * Gets the start button.
    *
    * @return the start button.
    */
   public JButton getStartButton()
   {
      return mStartButton;
   }

   /**
    * Gets the IP Address for making a remote connection.
    *
    * @return the IP Address for making a remote connection.
    */
   public String getIPAddress()
   {
      return mIPAddress;
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
    * returns a reference to the game board.
    *
    * @return a reference to the game board.
    */
   public JPanel getGameBoard()
   {
      return mBoard;
   }

   /**
    * Gets whether or not pencil marking is allowed.
    *
    * @return whether or not pencil marking is allowed.
    */
   public boolean isPencilMarkingAllowed()
   {
      return mIsPencilMarkingAllowed;
   }

   /**
    * Sets the timer mode to the given value;
    */
   public void setTimerMode(String mode)
   {
      if (mode.equals("competitiveTimer"))
      {
         mTimerCompetitive = true;
         mTimerRelaxed = false;
      }
      else if (mode.equals("relaxedTimer"))
      {
         mTimerRegular = false;
         mTimerCompetitive = false;
         mTimeLabel.setText("");
      }
      else
      {
         mTimerRegular = true;
         mTimerCompetitive = false;
         mTimeLabel.setText("");
      }
   }

   /**
    * Used to set game board for game over.
    * state or the other way around when going to game in progress.
    */
   public void setPanel(JPanel gamePanel)
   {
      mSplitPane.setLeftComponent(gamePanel);
   }

   /**
    * Sets whether or not to make a local connection.
    *
    * @param local whether or not to make a local connection.
    */
   public void setConnectionLocal(boolean local)
   {
      mIsConnectionLocal = local;
   }

   /**
    * Sets whether or not pencil marking is allowed.
    *
    * @param isAllowed whether or not pencil marking is allowed.
    */
   public void setPencilMarkingAllowed(boolean isAllowed)
   {
      mIsPencilMarkingAllowed = isAllowed;
   }

   /**
    * Sets the IP Address for making a remote connection.
    *
    * @param inIPAddress the IP Address for making a remote connection.
    */
   public void setIPAddress(String inIPAddress)
   {
      mIPAddress = inIPAddress;
   }

   /**
    * Used to swap game board for a paused board during pause
    * state or the other way around when going to game in progress.
    */
   public void swapPanel()
   {
      JPanel temp = (JPanel) mSplitPane.getLeftComponent();

      if ((temp == mPausedBoard) || (temp == mGameoverBoard))
      {
         mSplitPane.setLeftComponent(mBoard);
      }
      else
      {
         mSplitPane.setLeftComponent(mPausedBoard);
      }
   }

   /**
    * Closes the panel
    */
   public void closePanel()
   {
      mSplitPane.setLeftComponent(mPausedBoard);
   }
      
   /**
    * Gets whether or not to make a local connection.
    *
    * @return whether or not to make a local connection.
    */
   public boolean isConnectionLocal()
   {
      return mIsConnectionLocal;
   }

   /**
    * Checks to see if user input is correct and highlights the fields that
    * have incorrect guesses.
    */
   public void checkProgress()
   {
      Integer index = null;
      Integer value = null;
      int indexNum = 0;
      int valueNum = 0;

      Collection invalidGuessCollection = getClientModel().checkPuzzle();
      Iterator it = invalidGuessCollection.iterator();

      while (it.hasNext())
      {
         index = (Integer) it.next();
         value = (Integer) it.next();
         indexNum = index.intValue();
         valueNum = value.intValue();

         Field indexField = (Field) mFields[indexNum % 9][indexNum / 9];

         if (valueNum != -1)
         {
            Field valueField = (Field) mFields[valueNum % 9][valueNum / 9];

            if (valueField.isEditable())
            {
               valueField.setBackground(new Color(255, 0, 0));
            }
         }

         if (indexField.isEditable())
         {
            indexField.setBackground(new Color(255, 0, 0));
         }
      }
   }

   /**
    * Displays information about Sudoku.
    */
   public void about()
   {
      mAboutDialog.setLocationRelativeTo(this);
      mAboutDialog.setVisible(true);
   }

   /**
    * Displays game play instructions.
    */
   public void howTo()
   {
      HowToDialog.getInstance().setLocationRelativeTo(this);
      HowToDialog.getInstance().setVisible(true);
   }

   /**
    * Prints Sudoku puzzle.
    */
   public void print()
   {
      PrintUtility.printComponent(mBoard);
   }

   /**
    * undoes action performed.
    */
   public void undo()
   {
      mCommandInvoker.undo();
   }

   /**
    * redoes action performed.
    */
   public void redo()
   {
      mCommandInvoker.redo();
   }

   /**
    * Starts a computer opponent.
    */
   public void startComputerPlayer()
   {
      //mClientModel.startComputerPlayer();
      Logger.debug("Waiting for CS implementation of this action.");
   }

   /**
    * Clears all user input.
    */
   public void clear()
   {
      mCommandInvoker.dispatch(new ClearCommand(Sudoku.getClientModel()));
   }

   /**
    * Pauses the game.
    */
   public void pause()
   {
      mClientModel.pause();
      mPauseButton.setAction(Resume.getInstance());
      mProgressButton.setEnabled(false);
      mClearButton.setEnabled(false);
   }

   /**
    * Resumes the game from a paused state.
    */
   public void resume()
   {
      mClientModel.unpause();
      mPauseButton.setAction(Pause.getInstance());
   }

   /**
    * Starts the game.
    */
   public void start()
   {
      mClientModel.startGame();
   }

   /**
    * Displays a dialog where the user can choose a puzzle.
    */
   public void choosePuzzle()
   {
      mChoosePuzzleDialog.setLocationRelativeTo(this);
      mChoosePuzzleDialog.setVisible(true);
      update();
   }

   /**
    * Displays a dialog where the user can choose options.
    */
   public void options()
   {
      mOptionsDialog.setLocationRelativeTo(this);
      mOptionsDialog.setVisible(true);
   }

   /**
    * Disables buttons that should only run when
    * the game is running
    */
   public void disableGamePlayButtons()
   {
      mStartButton.setEnabled(false);
      mProgressButton.setEnabled(false);
      mPauseButton.setEnabled(false);
      mClearButton.setEnabled(false);
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

            mFields[j][i] = new Field(this, aColor, j, i);
            mBoard.add(mFields[j][i]);
         }
      }
   }

   /**
    * Setter for connection change requested
    */
   public void setConnectionChange(boolean connectionChangeRequest)
   {
      mConnectionChangeRequested = connectionChangeRequest;
   }

   /**
    * Connects client to server.
    */
   public void connect()
   {
      mConnectionDialog.setVisible(true);

      if (mConnectionChangeRequested)
      {
         String errorMessage = "Server Not Found";

         try
         {
            InetAddress inet = InetAddress.getLocalHost();
            mLocalIPAddress = inet.getHostAddress();
            Logger.debug("local IP is: " + mLocalIPAddress);
            Logger.debug("Entered IP is: " + getIPAddress());

            if (isConnectionLocal() || getIPAddress().equals(mLocalIPAddress))
            {
               mPuzzleButton.setEnabled(true);
            }
            else
            {
               mStartButton.setEnabled(false);
            }
         }
         catch (Exception e)
         {
         }

         boolean connected = 
            (isConnectionLocal() ? mClientModel.connectLocal()
               : mClientModel.connectRemote(getIPAddress()));

         if (connected)
         {
            Logger.debug("Connection Successful!");
            ChoosePuzzle.getInstance().setEnabled(true);
         }
         else
         {
            JOptionPane.showMessageDialog(this, errorMessage,
               "Connection was unsuccessful", JOptionPane.ERROR_MESSAGE);
         }

         try
         {
            Logger.debug("Name input is " + mClientModel.getName());
         }
         catch (Exception e)
         {   
         }

         Logger.debug("IP input is " + getIPAddress());
         if (!connected)
         {
            mPuzzleButton.setEnabled(false);
            ChoosePuzzle.getInstance().setEnabled(false);  
         }
      }
   }

   /**
    * Gets called when the game's status changes.
    *
    * @param e event generated when the game's status changes.
    */
   public void gameStatusChanged(GameStatusChangedEvent e)
   {
      Sudoku.setStates(e);

      switch (e.getCurrState())
      {
         case LOOKING_FOR_SERVER:
            break;

         case WAITING_FOR_PLAYERS:
            new ShowTimer().start();

            break;

         case GAME_PAUSED:
            swapPanel();

            break;

         case GAME_IN_PROGRESS:
            swapPanel();
            update();
            mStartButton.setEnabled(false);
            mPuzzleButton.setEnabled(false);
            ChoosePuzzle.getInstance().setEnabled(false);
            mProgressButton.setEnabled(true);
            mPauseButton.setEnabled(true);
            mClearButton.setEnabled(true);

            break;

         case GAME_OVER:
            setPanel(mGameoverBoard);

            if (mTimerRegular)
            {
               showTime();
            }

            if (getIPAddress().equals(mLocalIPAddress))
            {
               mPuzzleButton.setEnabled(true);
            }

            mProgressButton.setEnabled(false);
            mPauseButton.setEnabled(false);
            mClearButton.setEnabled(false);

            break;

         default:
            break;
      }
   }

   /**
    * Clears the screen
    */
   public void update()
   {
      for (int i = 0; i < 9; i++)
      {
         for (int j = 0; j < 9; j++)
         {
            Field aField = (Field) mFields[j][i];
            aField.revertToOriginalBGColor();

            if (Integer.parseInt(mClientModel.getSquare(j, i)) > 0)
            {
               mFields[j][i].setText(mClientModel.getSquare(j, i));
            }
            else
            {
               mFields[j][i].setText("");
               aField.setEditable(true);
               aField.setFocusable(true);
            }
         }
      }
   }

   /**
    * Updates the screen to another puzzle
    */
   public void cozener()
   {
      for (int i = 0; i < 9; i++)
      {
         for (int j = 0; j < 9; j++)
         {
            Field aField = (Field) mFields[j][i];
            aField.revertToOriginalBGColor();

            if (Integer.parseInt(mClientModel.getSquare(j, i)) > 0)
            {
               mFields[j][i].setText(mClientModel.getSquare(j, i));
            }
            else
            {
               String one = mClientModel.getSquare(j, i);
               int pos = Integer.parseInt(one);
               pos = -pos;
               one = Integer.toString(pos);
               mFields[j][i].setText("");
               mClientModel.getPuzzle().setSquare(one, j, i);
               mFields[j][i].setText(one);
            }
         }
      }
   }

   /**
    * Dispatches a command via the CommandInvoker.
    *
    * @param command the command to dispatch.
    */
   public void dispatch(Command command)
   {
      mCommandInvoker.dispatch(command);
   }

   /**
    * When a player is added or removed, update the list
    */
   public void playerChanged(PlayerChangedEvent e)
   {
      mPlayerDataModel.updatePlayers(e.getCurrList());
   }

   /**
    * Called when the puzzle changes.
    *
    * @param e the puzzle changed event.
    */
   public void puzzleChanged(PuzzleChangedEvent e)
   {
      JTextField field = mFields[e.getX()][e.getY()];
      String value = e.getCurrValue();

      mPlayerDataModel.updateDisplay();

      try
      {
         if (value.equals(""))
         {
            field.setText(value);
         }
         else if (Integer.parseInt(value) > 0)
         {
            field.setText(value);

            if (e.getPrevValue() == null)
            {
               field.setEditable(false);
               field.setFocusable(false);
            }
         }
         else
         {
         }
      }
      catch (NumberFormatException ex)
      {
      }
   }

   /**
    * Adds a guess.
    *
    * @param guess the guess text being added.
    *
    * @param x column where adding guess.
    *
    * @param y row where adding guess.
    */
   public void addGuess(String guess, String prev, int x, int y)
   {
      mCommandInvoker.dispatch(new AddGuessCommand(guess, prev, x, y));
   }

   /**
    * Removes a guess.
    *
    * @param x column where removing guess.
    *
    * @param y row where removing guess.
    */
   public void removeGuess(String number, int x, int y)
   {
      mCommandInvoker.dispatch(new RemoveGuessCommand(number, x, y));
   }

   /**
    * Shows the time in a label.
    */
   public boolean showTime()
   {
      boolean isTimer = false;
      SudokuTimer timer = mClientModel.getTimer();

      if (timer != null)
      {
         mTimeLabel.setText("Time: " + timer);
         isTimer = true;
      }
      else
      {
         mTimeLabel.setText("Time: Timer is Broken.");
      }

      return (isTimer);
   }

   /**
    * Shows the timer updated every second.
    */
   class ShowTimer
      extends Thread
   {
      /**
       * Displays the timer in the label.
       */
      public void run()
      {
         while (true)
         {
            while (mTimerCompetitive)
            {
               showTime();

               try
               {
                  Thread.sleep(1000);
               }
               catch (Exception e)
               {
               }
            }
         }
      }
   }
}

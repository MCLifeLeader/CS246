
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Timer;

public class StopwatchPanel
   extends Panel
   implements ActionListener
{
   //
   Timer mTimer;
   // define the buttons of the stopwatch
   Button mStartButton;
   Button mStopButton;
   Button mResetButton;

   int mCounter;

   // this label displays the time
   Label mCounterLabel;

   public StopwatchPanel()
   {
      init();
      updateCounter(true);
      resize(300, 300);
   }

   public void init()
   {
      mTimer = new Timer(1000, this);
      mStartButton = new Button("Start");
      mStartButton.addActionListener(this);
      mStopButton  = new Button("Stop");
      mStopButton.addActionListener(this);
      mResetButton = new Button("Reset");
      mResetButton.addActionListener(this);
      mCounterLabel = new Label("", Label.CENTER);
      setLayout(new BorderLayout());
      add(mCounterLabel, "Center");
      Panel panel = new Panel();
      panel.setLayout(new FlowLayout());
      panel.add(mStartButton);
      panel.add(mStopButton);
      panel.add(mResetButton);
      add(panel, "South");
   }

   public void actionPerformed(ActionEvent actionevent)
   {
      String command = actionevent.getActionCommand();
      if ("Start".equals(command))
      {
         mTimer.start();
      }
      else if ("Stop".equals(command))
      {
         mTimer.stop();
      }
      else if ("Reset".equals(command))
      {
         updateCounter(true);
      }
      else
      {
	  System.out.println(command);
         updateCounter(false);
      }
   }

   private void updateCounter(boolean reset)
   {
      if (reset)
      {
         mCounter = -1;
      }
      String txt = ((++mCounter == 1) ?
                    " second" : " seconds");
      mCounterLabel.setText(mCounter + txt);
   }
}

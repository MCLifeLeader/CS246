import java.awt.Frame;

public class StopwatchFrame
   extends Frame
{
   // The Panel that knows what to do
   StopwatchPanel mStopwatchPanel;

   public StopwatchFrame()
   {
      super("Stopwatch");
      mStopwatchPanel = new StopwatchPanel();

      // add the StopwatchPanel to our frame
      add("Center", mStopwatchPanel);
      resize(mStopwatchPanel.size());
   }

   // static 'main' is the starting function for applications
   public static void main(String args[])
   {
      // In an application,
      // we don't automagically get a frame from the browser
      new StopwatchFrame().setVisible(true);
   }
}

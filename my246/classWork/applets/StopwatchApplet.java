import java.applet.Applet;

public class StopwatchApplet
   extends Applet
{
   // the init() method is the 'main' for applets
   public void init()
   {
      add(new StopwatchPanel());
   }
}

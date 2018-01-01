import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;

public class FunApplet
    extends java.applet.Applet
{
   int mX = 20;
   int mY = 20;

   public void init()
   {
      resize(200, 200);
   }

   public void paint(Graphics g)
   {
      g.setColor(Color.blue);
      g.fillRect(mX, mY, 10, 10);
   }

   public boolean mouseDown(Event e, int x, int y)
   {
      mX = x - 5;
      mY = y - 5;
      repaint();
      return true;
   }
}

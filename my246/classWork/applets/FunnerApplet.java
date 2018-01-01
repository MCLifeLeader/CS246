import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;

public class FunnerApplet
   extends java.applet.Applet
   implements Runnable
{
   int mX = 20;
   int mY = 20;
   int mDeltaX = 1;

   Thread mThread = null;

   public void init()
   {
      resize(200, 200);
   }

   public void paint(Graphics g)
   {
      g.setColor(Color.white);
      g.fillRect(mX, mY, 10, 10);
      mX += mDeltaX;
      if ((mX < 0) || (mX > 200))
      {
         mDeltaX = - mDeltaX;
      }
      g.setColor(Color.red);
      g.fillRect(mX, mY, 10, 10);
   }

   public void start()
   {
      if (mThread == null)
      {
         mThread = new Thread(this); // <--- what does this do?
         mThread.start();
      }
   }

   public void stop()
   {
      if (mThread != null)
      {
         mThread.stop();
         mThread = null;
      }
   }

   public void run()
   {
      while (true)
      {
         repaint();
         try
         {
            Thread.sleep(10);
         }
         catch (Exception e)
         {
         }
      }
   }
}

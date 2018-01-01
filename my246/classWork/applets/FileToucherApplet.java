// $Header: /usr/local/cvsroot/courses/cs246/samplecode/applets/FileToucherApplet.java,v 1.2 2006/05/15 16:07:49 neffr Exp $

import java.applet.Applet;
import java.io.File;

public class FileToucherApplet
   extends Applet
{
   public void start()
   {
      // get now in milliseconds
      long now = System.currentTimeMillis();

      // get args (where?)
      String arg = getParameter("filename");
      {
         File file = new File(arg);
         if (! file.setLastModified(now))
         {
            System.out.println("Failed to touch " + file);
         }
      }
   }
}

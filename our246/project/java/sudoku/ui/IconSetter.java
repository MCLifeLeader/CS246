// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/IconSetter.java,v 1.1 2006/06/26 14:57:43 emerrill Exp $
package sudoku.ui;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Used to access images from jar file.
 *
 * @author $author$
 * @version $Revision: 1.1 $
  */
public class IconSetter
{
   /**
    * The image path.
    */
   private String gifPath;

   /**
    * Creates a new IconSetter object.
    *
    * @param inGifPath is the path to the image to be used.
    */
   public IconSetter(String inGifPath)
   {
      gifPath = inGifPath;
   }

   /**
    * used to get the image for the jar file.
    *
    * @return an Icon of the image.
    */
   public Icon getIcon()
   {
      Icon icon = null;

      try
      {
         icon = new ImageIcon(getClass().getResource(gifPath));
      }
      catch (Exception e)
      {
         Logger.debug(e);
      }

      return icon;
   }
}

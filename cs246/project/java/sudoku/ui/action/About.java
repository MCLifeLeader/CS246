// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/About.java,v 1.1 2006/06/13 20:44:54 emerrill Exp $
package sudoku.ui.action;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Displays About the game and its authors
 *
 * @version $version 1.1$
 */
public class About
   extends AbstractAction
{
   /**
    * Singleton of About
    */
   private static About cInstance = new About();

   /**
    * gets the instance of About
    *
    * @return the instance of About
    */
   public static About getInstance()
   {
      return cInstance;
   }

   /**
    * Creates a new About object.
    */
   private About()
   {
      super("About");
      putValue(Action.NAME, "About");
      putValue(Action.SMALL_ICON, new ImageIcon("config/images/helpicon.gif"));
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('B',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
   }

   /**
    * Displays information about Sudoku.
    *
    * @param ae the Action Event
    */
   public void actionPerformed(ActionEvent ae)
   {
      new AboutPopup();
   }
}

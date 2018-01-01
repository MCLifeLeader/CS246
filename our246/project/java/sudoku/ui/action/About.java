// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/About.java,v 1.7 2006/06/26 15:20:10 emerrill Exp $
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Displays About the game and its authors
 *
 * @author Eric Merrill
 * @version version 1.1
 */
public class About
   extends InvokeMethodAction
{
   /**
    * Creates a new About object.
    */
   public About()
   {
      super("About");
      putValue(Action.NAME, "About");
      IconSetter iconSetter = new IconSetter("/config/images/helpicon.gif");
      Icon icon = iconSetter.getIcon();
      putValue(Action.SMALL_ICON, icon);
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('B',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
   }
}

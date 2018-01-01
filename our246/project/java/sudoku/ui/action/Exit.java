// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/Exit.java,v 1.5 2006/06/26 15:20:10 emerrill Exp $
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Action;

/**
 * Exits the game
 */
public class Exit
   extends InvokeMethodAction
{
   /**
    * Provides way to exit window.
    */
   public Exit()
   {
      super("Exit");
      putValue(Action.NAME, "Exit");
      IconSetter iconSetter = new IconSetter("/config/images/exiticon.gif");
      Icon icon = iconSetter.getIcon();
      putValue(Action.SMALL_ICON, icon);
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('Q',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_Q));
   }
}

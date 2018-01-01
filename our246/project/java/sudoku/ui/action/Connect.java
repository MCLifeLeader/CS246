// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/Connect.java,v 1.3 2006/06/26 15:20:10 emerrill Exp $
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Connects client to server.
 *
 * @version $version 1.1$
 */
public class Connect
   extends InvokeMethodAction
{
   /**
    * Constructs a new Connect instance.
    */
   public Connect()
   {
      super("Connect");
      putValue(Action.NAME, "Connect");
      IconSetter iconSetter = new IconSetter(
         "/config/images/connectionicon.gif");
      Icon icon = iconSetter.getIcon();
      putValue(Action.SMALL_ICON, icon);
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('I',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_I));
   }
}

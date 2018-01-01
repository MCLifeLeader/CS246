// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/Options.java,v 1.3 2006/06/26 15:20:10 emerrill Exp $
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Display player options and allows them to be modified.
 *
 * @version $version 1.1$
 */
public class Options
   extends InvokeMethodAction
{
   /**
    * Constructs a new Options instance.
    */
   public Options()
   {
      super("Options");
      putValue(Action.NAME, "Options");
      IconSetter iconSetter = new IconSetter("/config/images/optionsicon.gif");
      Icon icon = iconSetter.getIcon();
      putValue(Action.SMALL_ICON, icon);
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('O',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_O));
   }
}

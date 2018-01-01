// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/Print.java,v 1.11 2006/06/26 15:30:53 emerrill Exp $
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;

import javax.swing.*;

/**
 * Listens for the clear event in Menu, the button
 *
 * @author $Randall King$
 * @version $Revision: 1.11 $
 */
public class Print
   extends InvokeMethodAction
{
   /**
    * Creates a new Print object.
    */
   private Print()
   {
      super("Print");
      putValue(Action.NAME, "Print");
      IconSetter iconSetter = new IconSetter("/config/images/printicon.gif");
      Icon icon = iconSetter.getIcon();
      putValue(Action.SMALL_ICON, icon);
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('P',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_P));
   }

   /**
    * Single instance of Print
    */
   private static Print cInstance = new Print();

   /**
    * Get singleton
    *
    * @return A Print Instance
    */
   public static Print getInstance()
   {
      return cInstance;
   }
}

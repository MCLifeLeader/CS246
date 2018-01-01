// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/Clear.java,v 1.9 2006/06/26 15:20:10 emerrill Exp $
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Listens for the clear event in Menu, the button
 *
 * @author $Randall King$
 * @version $Revision: 1.9 $
 */
public class Clear
   extends InvokeMethodAction
{
   /**
    * Constructs a new Clear instance.
    */
   private Clear()
   {
      super("Clear");
      putValue(Action.NAME, "Clear");
      IconSetter iconSetter = new IconSetter("/config/images/clear.gif");
      Icon icon = iconSetter.getIcon();
      putValue(Action.SMALL_ICON, icon);
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('X',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_X));
   }

   /**
    * The singleton instance of Clear.
    */
   private static Clear cInstance = new Clear();

   /**
    * Gets the singleton instance of Clear.
    *
    * @return the singleton instance of Clear.
    */
   public static Clear getInstance()
   {
      return cInstance;
   }
}

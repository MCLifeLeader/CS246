// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/Redo.java,v 1.6 2006/06/26 15:20:10 emerrill Exp $
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Action to redo any previous undone actions.
 *
 * @author $author$
 * @version $Revision: 1.6 $
 */
public class Redo
   extends InvokeMethodAction
{
   /**
    * Constructs a new Redo instance.
    */
   private Redo()
   {
      super("Redo");
      putValue(Action.NAME, "Redo");
      IconSetter iconSetter = new IconSetter("/config/images/redo.gif");
      Icon icon = iconSetter.getIcon();
      putValue(Action.SMALL_ICON, icon);
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('Y',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_Y));
   }

   /**
    * The singleton instance of Redo.
    */
   private static Redo cInstance = new Redo();

   /**
    * Gets the singleton instance of Redo.
    *
    * @return the singleton instance of Redo.
    */
   public static Redo getInstance()
   {
      return cInstance;
   }
}

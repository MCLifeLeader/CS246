// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/Undo.java,v 1.6 2006/06/26 17:02:14 emerrill Exp $
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * The action for undoing actions.
 *
 * @author $author$
 * @version $Revision: 1.6 $
 */
public class Undo
   extends InvokeMethodAction
{
   /**
    * Creates a new Undo object.
    */
   private Undo()
   {
      super("Undo");
      putValue(Action.NAME, "Undo");
      IconSetter iconSetter = new IconSetter("/config/images/undo.gif");
      Icon icon = iconSetter.getIcon();
      putValue(Action.SMALL_ICON, icon);
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('Z',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_Z));
   }

   /**
    * Single instance of Print
    */
   private static Undo cInstance = new Undo();

   /**
    * Get singleton
    *
    * @return A Print Instance
    */
   public static Undo getInstance()
   {
      return cInstance;
   }
}

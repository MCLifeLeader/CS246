// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/Pause.java,v 1.4 2006/06/26 15:20:10 emerrill Exp $
// $header$
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Action that pauses game play.
 *
 * @author $author$
 * @version $Revision: 1.4 $
 */
public class Pause
   extends InvokeMethodAction
{
   /**
    * Creates a new Pause object.
    */
   private Pause()
   {
      super("Pause");
      putValue(Action.NAME, "Pause");
      IconSetter iconSetter = new IconSetter("/config/images/pauseicon.gif");
      Icon icon = iconSetter.getIcon();
      putValue(Action.SMALL_ICON, icon);
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('T',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_T));
   }

   /**
    * Single instance of Pause
    */
   private static Pause cInstance = new Pause();

   /**
    * Get singleton
    *
    * @return A Pause Instance
    */
   public static Pause getInstance()
   {
      return cInstance;
   }
}

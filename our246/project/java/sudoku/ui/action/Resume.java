// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/Resume.java,v 1.4 2006/06/26 15:20:10 emerrill Exp $
// $header$
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Brings the game from a paused state to game in progress.
 *
 * @author $author$
 * @version $Revision: 1.4 $
 */
public class Resume
   extends InvokeMethodAction
{
   /**
    * Creates a new Resume object.
    */
   private Resume()
   {
      super("Resume");
      putValue(Action.NAME, "Resume");
      IconSetter iconSetter = new IconSetter("/config/images/resumeicon.gif");
      Icon icon = iconSetter.getIcon();
      putValue(Action.SMALL_ICON, icon);
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('T',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_T));
   }

   /**
    * Single instance of Resume
    */
   private static Resume cInstance = new Resume();

   /**
    * Get singleton
    *
    * @return A Resume Instance
    */
   public static Resume getInstance()
   {
      return cInstance;
   }
}

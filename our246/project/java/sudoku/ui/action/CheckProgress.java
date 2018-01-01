// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/CheckProgress.java,v 1.5 2006/06/26 15:20:10 emerrill Exp $
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * The purpose of this class is to check to see if
 * a player has made a mistake in the placement of
 * a guess.  Such a mistake will be highlighted.
 *
 * @author $author$
 * @version $Revision: 1.5 $
 */
public class CheckProgress
   extends InvokeMethodAction
{
   /**
    * Creates a new CheckProgress object.
    */
   private CheckProgress()
   {
      super("Check Progress", "checkProgress");
      putValue(Action.NAME, "Check Progress");
      IconSetter iconSetter = new IconSetter("/config/images/checkicon.gif");
      Icon icon = iconSetter.getIcon();
      putValue(Action.SMALL_ICON, icon);
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('D',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_D));
   }

   /**
    * Single instance of CheckProgress
    */
   private static CheckProgress cInstance = new CheckProgress();

   /**
    * Get singleton
    *
    * @return A CheckProgress Instance
    */
   public static CheckProgress getInstance()
   {
      return cInstance;
   }
}

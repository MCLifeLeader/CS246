// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/Start.java,v 1.3 2006/06/26 15:20:10 emerrill Exp $
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Changes the game state from WAITING_FOR_PLAYERS to GAME_IN_PROGRESS.
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
public class Start
   extends InvokeMethodAction
{
   /**
    * Constructs a new Start instance.
    */
   private Start()
   {
      super("Start");
      putValue(Action.NAME, "Start");
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('Y',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_Y));
   }

   /**
    * The singleton instance of Start.
    */
   private static Start cInstance = new Start();

   /**
    * Gets the singleton instance of Start.
    *
    * @return the singleton instance of Start.
    */
   public static Start getInstance()
   {
      return cInstance;
   }
}

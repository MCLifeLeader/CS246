// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/Exit.java,v 1.2 2006/06/15 20:05:10 emerrill Exp $
package sudoku.ui.action;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Action;

/**
 * Exits the game
 * TODO: make this a more configurable AbstractAction subclass.
 */
public class Exit
   extends AbstractAction
{
   /**
    * The singleton instance of Exit.
    */
   private static Exit cInstance = new Exit();

   /**
    * Gets the singleton instance of Exit.
    *
    * @return the singleton instance of Exit.
    */
   public static Exit getInstance()
   {
      return cInstance;
   }

   /**
    * Provides way to exit window.
    */
   private Exit()
   {
      super("Exit");
      putValue(Action.SMALL_ICON, new ImageIcon("config/images/exiticon.gif"));
      putValue(Action.NAME, "Exit");
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('Q',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_Q));
   }

   /**
    * Performs the Exit action.
    *
    * @param e the action event.
    */
   public void actionPerformed(ActionEvent e)
   {
      // TODO: offer to save the player's state before exiting.
      if (JOptionPane.showConfirmDialog(Sudoku.getFrame(),
                "Are you sure you want to exit?", "Confirm Exit",
                JOptionPane.YES_NO_OPTION) == 0)
      {
         System.exit(0);
      }
      
   }
}

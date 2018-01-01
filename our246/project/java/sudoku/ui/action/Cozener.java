// $Header
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Listens for the Cozener action which is ctrl-m
 * gives the user the answers.  Is a "menu item"
 * but it doesn't appear in the menu
 *
 * @author $Randall King$
 * @version $Revision: 1.4 $
 */
public class Cozener
   extends InvokeMethodAction
{
   /**
    * Constructs a new Cozener instance.
    */
   private Cozener()
   {
      super("Cozener");
      putValue(Action.NAME, "Cozener");
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('M',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_M));
   }

   /**
    * The singleton instance of Cozener
    */
   private static Cozener cInstance = new Cozener();

   /**
    * Gets the singleton instance of Cozener.
    *
    * @return the singleton instance of Cozener.
    */
   public static Cozener getInstance()
   {
      return cInstance;
   }
}

// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/HowTo.java,v 1.5 2006/06/26 15:20:10 emerrill Exp $
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Displays the rules of the game.
 *
 * @version $version 1.1$
 */
public class HowTo
   extends InvokeMethodAction
{
   /**
    * Creates a new HowTo object.
    */
   public HowTo()
   {
      super("How To", "howTo");
      putValue(Action.NAME, "How To");
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('L',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
   }
}

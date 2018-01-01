// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/ChoosePuzzle.java,v 1.4 2006/06/26 15:20:10 emerrill Exp $
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Action to choos a puzzles.
 *
 * @author $author$
 * @version $Revision: 1.4 $
 */
public class ChoosePuzzle
   extends InvokeMethodAction
{
   /**
    * Creates a new ChoosePuzzle object.
    */
   private ChoosePuzzle()
   {
      super("Choose Puzzle", "choosePuzzle");
      putValue(Action.NAME, "Choose Puzzle");
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('N',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_Y));
   }

   /**
    * Single instance of ChoosePuzzle
    */
   private static ChoosePuzzle cInstance = new ChoosePuzzle();

   /**
    * Get singleton
    *
    * @return A ChoosePuzzle Instance
    */
   public static ChoosePuzzle getInstance()
   {
      return cInstance;
   }
}

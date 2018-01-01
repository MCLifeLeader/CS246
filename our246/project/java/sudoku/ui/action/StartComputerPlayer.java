// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/StartComputerPlayer.java,v 1.2 2006/06/26 21:04:26 emerrill Exp $
package sudoku.ui.action;

import sudoku.si.*;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Starts a computer player instance.
 *
 * @author $author$
 * @version $Revision: 1.2 $
 */
public class StartComputerPlayer
   extends InvokeMethodAction
{
   /**
    * Constructs a new StartComputerPlayer instance.
    */
   private StartComputerPlayer()
   {
      super("Start Computer Player", "startComputerPlayer");
      putValue(Action.NAME, "Start Computer Player");
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('A',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_A));
   }

   /**
    * The singleton instance of StartComputerPlayer.
    */
   private static StartComputerPlayer cInstance = new StartComputerPlayer();

   /**
    * Gets the singleton instance of StartComputerPlayer.
    *
    * @return the singleton instance of StartComputerPlayer.
    */
   public static StartComputerPlayer getInstance()
   {
      return cInstance;
   }
}

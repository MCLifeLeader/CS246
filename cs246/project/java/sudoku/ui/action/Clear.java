// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/Clear.java,v 1.1 2006/06/14 18:36:04 rk223 Exp $
package sudoku.ui.action;

import sudoku.ui.*;

import java.awt.event.*;

/**
 * Listens for the clear event in Menu, the button
 *
 * @author $Randall King$
 * @version $Revision: 1.1 $
  */
public class Clear
   implements ActionListener
{
   /**
    * Single instance of clear
    */
   private static Clear cInstance = new Clear();

   /**
    * Get singleton
    *
    * @return A Clear Instance
    */
   public static Clear getInstance()
   {
      return cInstance;
   }

   /**
    * Call the clear function in the sudoku panel class
    *
    * @param e An event!
    */
   public void actionPerformed(ActionEvent e)
   {
      Sudoku.getFrame().getPanel().clear();
   }
}

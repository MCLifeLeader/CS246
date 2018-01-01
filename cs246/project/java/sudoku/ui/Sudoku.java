// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/Sudoku.java,v 1.3 2006/06/16 04:12:58 mbcarey Exp $
package sudoku.ui;

import sudoku.cs.*;

/**
 * The MAIN Sudoku class, initializes the GUI (SudokuFrame)
 * and allows global access to it.
 */
public class Sudoku
{
   /**
    * Supports a "pseudo-Singleton" pattern.
    */
   private static SudokuFrame cFrameInstance = new SudokuFrame();

   /**
    * Gets the frame.
    *
    * @return the frame.
    */
   public static SudokuFrame getFrame()
   {
      return cFrameInstance;
   }

   /**
    * Gets the client model.
    *
    * @return the client model.
    */
   public static ClientModel getClientModel()
   {
      return getFrame().getClientModel();
   }

   /**
    * The main method of Sudoku gets things going.
    *
    * @param args the command-line arguments (none at present).
    */
   public static void main(String[] args)
   {
      getFrame().setVisible(true);
   }
}

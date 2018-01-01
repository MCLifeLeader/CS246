// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/action/Print.java,v 1.5 2006/06/17 15:46:14 benstodd Exp $
package sudoku.ui.action;

import sudoku.ui.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;

import javax.swing.*;

/**
 * Listens for the clear event in Menu, the button
 *
 * @author $Randall King$
 * @version $Revision: 1.5 $
 */
public class Print
   extends AbstractAction
   implements ActionListener
{
   /**
    * Creates a new Print object.
    */
   public Print()
   {
      super("Print");
      putValue(Action.NAME, "Print");
      putValue(Action.SMALL_ICON, new ImageIcon("config/images/printicon.gif"));
      putValue(Action.ACCELERATOR_KEY,
         KeyStroke.getKeyStroke('P',
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
      putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_P));
   }

   /**
    * Single instance of Print
    */
   private static Print cInstance = new Print();

   /**
    * Get singleton
    *
    * @return A Print Instance
    */
   public static Print getInstance()
   {
      return cInstance;
   }

   /**
    * Print the Main Panel
    *
    * @param e An event!
    */
   public void actionPerformed(ActionEvent e)
   {
       //       PrintUtilities.printComponent(Sudoku.getFrame().getPanel().getGameBoard());
   }
}


/**
 * This is code that I got from the internet it implements the
 * abstract Printable class, and allows for this to be easily
 * printed
 * www.apl.jhu.edu/~hall/java/Swing-Tutorial/Printing/PrintUtilities.java
 * @author $Marty Hall$
 * @version $Revision: 1.5 $
 */
class PrintUtilities
   implements Printable
{
   /**
    * Stores whatever "Component" you have
    *
    */
   private Component componentToBePrinted;

   /**
    * Print component
    *
    * @param c The current component
    */
   public static void printComponent(Component c)
   {
      new PrintUtilities(c).print();
   }

   /**
    * set the componentToBePrinted
    *
    * @param componentToBePrinted is what will be printed.
    */
   public PrintUtilities(Component componentToBePrinted)
   {
      this.componentToBePrinted = componentToBePrinted;
   }

   /**
    * Class the print function
    */
   public void print()
   {
      PrinterJob printJob = PrinterJob.getPrinterJob();
      printJob.setPrintable(this);

      if (printJob.printDialog())
      {
         try
         {
            printJob.print();
         }
         catch (PrinterException pe)
         {
            System.out.println("Error printing: " + pe);
         }
      }
   }

   /**
    * This exectues teh print job
    *
    * @param g The graphics
    * @param pageFormat The page format
    * @param pageIndex Which made
    *
    * @return if the page exists
    */
   public int print(Graphics g, PageFormat pageFormat, int pageIndex)
   {
      if (pageIndex > 0)
      {
         return (NO_SUCH_PAGE);
      }
      else
      {
         Graphics2D g2d = (Graphics2D) g;
         g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
         disableDoubleBuffering(componentToBePrinted);
         componentToBePrinted.paint(g2d);
         enableDoubleBuffering(componentToBePrinted);

         return (PAGE_EXISTS);
      }
   }

   /**
    * The speed and quality of printing suffers dramatically if
    *  any of the containers have double buffering turned on.
    *  So this turns if off globally.
    *  @see enableDoubleBuffering
    */
   public static void disableDoubleBuffering(Component c)
   {
      RepaintManager currentManager = RepaintManager.currentManager(c);
      currentManager.setDoubleBufferingEnabled(false);
   }

   /**
    *
    * Re-enables double buffering globally.
    */
   public static void enableDoubleBuffering(Component c)
   {
      RepaintManager currentManager = RepaintManager.currentManager(c);
      currentManager.setDoubleBufferingEnabled(true);
   }
}

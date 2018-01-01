// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/PrintUtility.java,v 1.4 2006/06/25 07:11:47 emerrill Exp $
package sudoku.ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;

import javax.swing.*;

/**
 * This is code that I got from the internet it implements the
 * abstract Printable class, and allows for this to be easily
 * printed.
 *
 * Source:
 *
 * www.apl.jhu.edu/~hall/java/Swing-Tutorial/Printing/PrintUtilities.java
 *
 * @author $Marty Hall$
 * @version $Revision: 1.4 $
 */
public class PrintUtility
   implements Printable
{
   /**
    * Stores whatever "Component" you have
    *
    */
   private Component componentToBePrinted;

   /**
    * Prints component
    *
    * @param c The current component
    */
   public static void printComponent(Component c)
   {
      new PrintUtility(c).print();
   }

   /**
    * sets the componentToBePrinted
    *
    * @param componentToBePrinted is what will be printed.
    */
   public PrintUtility(Component componentToBePrinted)
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
    * This exectues the print job
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
    * any of the containers have double buffering turned on.
    * This turns if off globally.
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

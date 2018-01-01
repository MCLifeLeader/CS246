// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/HowToDialog.java,v 1.6 2006/06/26 18:28:59 mbcarey Exp $
package sudoku.ui;

import sudoku.cs.*;

import sudoku.cs.event.*;

import sudoku.si.*;

import java.awt.*;
import java.awt.event.*;

import java.rmi.*;

import java.util.*;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Creates the window for How to play Sudoku.
 *
 * @author Alan Chase
 */
public class HowToDialog
   extends JDialog
{
   /**
    * Creates a HowToDialog.
    */
   private static HowToDialog cInstance = new HowToDialog();

   /**
    * The ok button.
    */
   private JButton mOk;

   /**
    * Gets instance of HowToDialog.
    *
    * @return instance of HowToDialog.
    */
   public static HowToDialog getInstance()
   {
      return cInstance;
   }

   /**
    * Creates a new HowToPlay object.
    */
   private HowToDialog()
   {
      super(Sudoku.getFrame(), "How To Play Sudoku", true);

      mOk = new JButton("OK");

      Properties pr = System.getProperties();
      String osName = pr.getProperty("os.name");

      if (osName.equals("Linux"))
      {
         setSize(400, 510);
      }
      else
      {
         setSize(400, 530);
      }

      init();
   }

   /**
    * Called by the Constructor.  init sets up HowToPlay window.
    */
   private void init()
   {
      SpringLayout layout = new SpringLayout();
      setLayout(layout);
      setResizable(false);
      setLocationRelativeTo(Sudoku.getFrame());
      mOk.addActionListener(new OKButtonActionListener());

      JTextArea TextHowToPlay = new JTextArea(
            "The rule is simple - each number from 1 through to 9" +
            " must appear once and only once in each row, in each" +
            " column, and in each 3 x 3 mini square, of which there " +
            "are 9 in total that make up the total puzzle. " +
            "                                                        " +
            "                                                         " +
            "                              " +
            "The idea is that from the starting position given using" +
            " logic alone you will be able to come up with the one unique " +
            "solution to the puzzle. From then on in, it is down to you" +
            " and your brain to deduce what the solution to the puzzle must be!" +
            "                                                        " +
            "                                         " +
            "Here are two basic strategy tips found at www.wikipedia.com. " +
            "                                                        " +
            "                                    " +
            "* Cross-hatching: the scanning of rows (or columns) to identify" +
            " which line in a particular region may contain a certain numeral " +
            "by a process of elimination. This process is then repeated with " +
            "the columns (or rows). For fastest results, the numerals are scanned" +
            " in order of their frequency. It is important to perform this process" +
            " systematically, checking all of the digits 1-9." +
            "                                                          " +
            "                                                          " +
            "                                      " +
            " * Counting 1-9 in regions, rows, and columns to identify missing" +
            " numerals. Counting based upon the last numeral discovered may speed" +
            " up the search. It also can be the case (typically in tougher puzzles)" +
            " that the easiest way to ascertain the value of an individual cell is by" +
            " counting in reverse - that is, by scanning the cell's region, row, and" +
            " column for values it cannot be, in order to see which is left.");

      TextHowToPlay.setLineWrap(true);
      TextHowToPlay.setWrapStyleWord(true);
      TextHowToPlay.setPreferredSize(new Dimension(360, 420));
      TextHowToPlay.setEditable(false);

      add(TextHowToPlay);
      add(mOk);

      layout.putConstraint(SpringLayout.WEST, TextHowToPlay, 15,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, TextHowToPlay, 15,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, mOk, 320, SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, mOk, 450, SpringLayout.NORTH,
         this);
   }

   /**
    * Ok Button action listener
    *
    * @author $author$
    * @version $Revision: 1.6 $
    */
   class OKButtonActionListener
      implements ActionListener
   {
      /**
       * Makes HowTo dialog invisible when the ok button is
       * pressed.
       */
      public void actionPerformed(ActionEvent event)
      {
         setVisible(false);
      }
   }
}

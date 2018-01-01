// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/Field.java,v 1.9 2006/06/17 15:55:54 benstodd Exp $
package sudoku.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

/*
 * Created on Jun 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author benstodd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Field
   extends JPanel
{
   /**
    * DOCUMENT ME!
    */
   private javax.swing.JPanel field = null;

   /**
    * DOCUMENT ME!
    */
   private JPanel guess = null;

   /**
    * DOCUMENT ME!
    */
   private JFormattedTextField actualNum = null;

   /**
    * DOCUMENT ME!
    */
   private static MaskFormatter mSudokuFormatter;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   private MaskFormatter getFormatter()
   {
      if (mSudokuFormatter == null)
      {
         try
         {
            mSudokuFormatter = new MaskFormatter("####");
            mSudokuFormatter.setInvalidCharacters("0");
         }
         catch (Exception e)
         {
         }
      }

      return mSudokuFormatter;
   }

   /**
    * This method initializes jTextField
    *
    * @return javax.swing.JTextField
    */
   private JTextField getActualNum()
   {
      if (actualNum == null)
      {
         actualNum = new JFormattedTextField();
         actualNum.setFont(new Font("Serif", 0, 20));
         actualNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
         actualNum.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
         actualNum.setFocusLostBehavior(JFormattedTextField.COMMIT);
         
         actualNum.addKeyListener(new KeyAdapter()
            {
               public void keyTyped(KeyEvent e)
               {
                  int actualLength = 0;
		  if (getPencilState())
		  {
         	         if ((e.getKeyChar() == KeyEvent.VK_BACK_SPACE)
                	    || (e.getKeyChar() == KeyEvent.VK_DELETE))
			   {
			    	actualLength = actualNum.getText().length();
			   }
			 else
			 {
			 actualLength = actualNum.getText().length() + 1;
			 }

			 if (actualLength <= 1)
			 {
			 	actualNum.setFont(new Font("Serif", 0, 20));
				actualNum.setForeground(Color.BLACK);
		         }
			 else if (actualLength > 1)
			 {
			 	actualNum.setFont(new Font("Serif", 0, 12));
				actualNum.setForeground(Color.GREEN);
			}
                   }
	        }
            });
      }

      return actualNum;
   }

   /**
    * This is the default constructor
    */
   public Field()
   {
      super();
      initialize();
   }

   /**
    * Creates a new Field object.
    *
    * @param c DOCUMENT ME!
    */
   public Field(Color c)
   {
      super();
      initialize();
      setColor(c);
   }

   /**
    * DOCUMENT ME!
    *
    * @param c DOCUMENT ME!
    */
   public void setColor(Color c)
   {
      actualNum.setBackground(c);
      this.setBackground(c);
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public JFormattedTextField getActualNumField()
   {
      return actualNum;
   }

   /**
    * This method initializes this
    *
    * @return void
    */
   private void initialize()
   {
      this.setLayout(new BorderLayout());
      this.setSize(30, 30);
      this.setBackground(java.awt.Color.white);
      this.setBorder(BorderFactory.createRaisedBevelBorder());
      this.setPreferredSize(new Dimension(21, 21));
      this.add(getActualNum(), BorderLayout.CENTER);
   }

   private Boolean getPencilState()
   {
   return Sudoku.getFrame().getPanel().isPencilSelected();
   }
   /**
    * This method initializes jContentPane
    *
    * @return javax.swing.JPanel
    */
   private javax.swing.JPanel getField()
   {
      this.setLayout(new java.awt.BorderLayout());
      this.setSize(73, 59);
      this.setBackground(java.awt.Color.white);
      this.add(getActualNum(), java.awt.BorderLayout.CENTER);

      return this;
   }
}

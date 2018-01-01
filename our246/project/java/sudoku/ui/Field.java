// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/Field.java,v 1.24 2006/06/25 07:11:47 emerrill Exp $
package sudoku.ui;

import sudoku.si.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Field is a JTextField that knows its position in a grid. It also processes
 * keyTyped events.
 *
 *  @author $Author: emerrill $
 *  @version $Revision: 1.24 $
 */
public class Field
   extends JTextField
{
   /**
    * The default normal font.
    */
   public static final Font NORMAL_FONT = new Font("Serif", 0, 20);

   /**
    * The default small font.
    */
   public static final Font SMALL_FONT = new Font("Serif", 0, 12);

   /**
    * The maximum text length.
    */
   public static final int MAX_LENGTH = 9;

   /**
    * The X coordinate of this field in the grid.
    */
   private int mX = 0;

   /**
    * The Y coordinate of this field in the grid.
    */
   private int mY = 0;

   /**
    * The Sudoku Panel.
    */
   private SudokuPanel mSudokuPanel;

   /**
    * Contains original color that was passed in when constructed.
    */
   private Color mOriginalColor;

   /**
    * Constructs a new Field instance.
    *
    * @param inSudokuPanel the Sudoku Panel.
    *
    * @param bgColor the background color.
    *
    * @param xpos the x position in the grid.
    *
    * @param ypos the y position in the grid.
    */
   public Field(SudokuPanel inSudokuPanel, Color bgColor, int xpos, int ypos)
   {
      mSudokuPanel = inSudokuPanel;
      initialize();
      setGridPosition(xpos, ypos);
      setBackground(bgColor);
      mOriginalColor = bgColor;
   }

   /**
    * Reverts background color to the color it was when it was instantiated.
    */
   public void revertToOriginalBGColor()
   {
      setBackground(mOriginalColor);
   }

   /**
    * Sets the x and y coordinates.
    *
    * @param x x corr.
    * @param y y corr.
    */
   public void setGridPosition(int x, int y)
   {
      mX = x;
      mY = y;
   }

   /**
    * Returns the horizontal position of the field.
    *
    * @return mX X corr.
    */
   public int getGridPositionX()
   {
      return mX;
   }

   /**
    * Returns the vertical position of the field.
    *
    * @return mY Y corr.
    */
   public int getGridPositionY()
   {
      return mY;
   }

   /**
    * Initializes the Sudoku text entry component of the game board.
    */
   private void initialize()
   {
      setPreferredSize(new Dimension(21, 21));
      setFont(NORMAL_FONT);
      setHorizontalAlignment(JTextField.CENTER);
   }

   /**
    * Sets the text in this field and other "look" properties
    * based on the pencil state and the length of the text.
    *
    * @param text the text to set.
    */
   public void setText(String text)
   {
      if (mSudokuPanel.isPencilMarkingAllowed())
      {
         if (text.length() <= 1)
         {
            setFont(NORMAL_FONT);
            setForeground(Color.BLACK);
         }
         else
         {
            setFont(SMALL_FONT);
            setForeground(Color.GREEN);
         }
      }

      super.setText(text);
   }

   /**
    * Processes a key event by consuming it
    * then delegating to super.
    *
    * @param e the key event
    */
   public void processKeyEvent(KeyEvent e)
   {
      if (e.getID() == KeyEvent.KEY_TYPED)
      {
         revertToOriginalBGColor();

         char ch = e.getKeyChar();

         boolean erasing = ((ch == KeyEvent.VK_BACK_SPACE) ||
            (ch == KeyEvent.VK_DELETE));

         String text = (mSudokuPanel.isPencilMarkingAllowed() ? getText() : "");

         if (Character.isDigit(ch) && (ch != '0'))
         {
            if (text.indexOf(ch) == -1) // not already there
            {
               mSudokuPanel.addGuess(Character.toString(ch), text, mX, mY);
            }
         }
         else if (erasing)
         {
            mSudokuPanel.removeGuess(getText(), mX, mY);
         }
      }

      if (! e.isControlDown())
      {
         e.consume();
      }

      super.processKeyEvent(e);
   }

   /**
    *  Creates the default implementation of the model to be used at
    *  construction if one isn't explicitly given.
    *  An instance of LimitDocument is returned.
    *
    *  @return The default model implementation.
    */
   protected Document createDefaultModel()
   {
      return new LimitDocument();
   }

   /**
    *  Customizes a JTextField to allow only a
    *  certain number of characters.  It will work even if text is pasted
    *  into the field from the clipboard or if it is altered via programmatic
    *  changes.
    */
   private class LimitDocument
      extends PlainDocument
   {
      /**
       * Forbids typed key to be inserted into StringBuffer
       * unless the buffer.length() + typed string is less than or equal to
       * MAX_LENGTH.
       *
       * @param offs current offset
       * @param str string being entered or key typed
       * @param a AttributeSet
       *
       * @throws BadLocationException insert position is not a valid.
       */
      public void insertString(int offs, String str, AttributeSet a)
         throws BadLocationException
      {
         StringBuffer buffer = new StringBuffer(getText(0, getLength()));

         if ((buffer.length() + str.length()) <= MAX_LENGTH)
         {
            super.insertString(offs, str, a);
         }
         else
         {
            Toolkit.getDefaultToolkit().beep();
         }
      }
   }
}

package sudoku.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

class NumberButtons
   extends JToggleButton
{
  static protected Font font;
  static protected ImageIcon selectedIcon, invisibleIcon;

  public NumberButtons(Icon label, boolean bool)
  {
	super(label, bool);
	setHorizontalTextPosition(AbstractButton.CENTER);
	setFocusPainted(false);
	setBorderPainted(false);

	if (font == null)
   {
	    font = new Font("serif", Font.BOLD, 24);
 	}
	setFont(font);

	if (selectedIcon == null)
   {
	    selectedIcon = new ImageIcon("config/images/newicon.gif");
	}
	setSelectedIcon(selectedIcon);

	/*
	 * No selected/pressed/rollover icons get shown unless
	 * the toggle button's default icon is non-null.  The 
	 * workaround is to create a transparent, full-sized icon 
	 * for the default icon.
	 */
	if (invisibleIcon == null)
   {
	    invisibleIcon = new ImageIcon("invisible.gif");
	}
	setIcon(invisibleIcon);
  }
   public static void main()
   {
   }
}

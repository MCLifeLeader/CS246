// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/NumberButton.java,v 1.1 2006/06/17 15:46:14 benstodd Exp $
package sudoku.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
class NumberButton
   extends JToggleButton
{
   /**
    * DOCUMENT ME!
    */
   static protected Font font;

   /**
    * DOCUMENT ME!
    */
   static protected ImageIcon selectedIcon;

   /**
    * DOCUMENT ME!
    */
   static protected ImageIcon invisibleIcon;

   /**
    * Creates a new NumberButtons object.
    *
    * @param label DOCUMENT ME!
    * @param bool DOCUMENT ME!
    */
   public NumberButton(String label, boolean bool)
   {
      super(label, bool);
      setHorizontalTextPosition(AbstractButton.CENTER);
      setFocusPainted(false);
      setBorderPainted(false);

      if (font == null)
      {
         font = new Font("serif", Font.BOLD, 12);
      }

      setFont(font);

      if (selectedIcon == null)
      {
         selectedIcon = new ImageIcon("config/images/newicon.gif");
      }

      setSelectedIcon(selectedIcon);
      setPreferredSize(new Dimension(100, 25));
      setHorizontalAlignment(AbstractButton.CENTER);
      setHorizontalTextPosition(AbstractButton.CENTER);

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
}

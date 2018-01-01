// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/CreateInterface.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class CreateInterface
   extends Command
{
   /**
    * DOCUMENT ME!
    */
   String _state;

   /**
    * DOCUMENT ME!
    */
   int _x;

   /**
    * DOCUMENT ME!
    */
   int _y;

   //TODO: Another one that may need to be changed.
   /**
    * DOCUMENT ME!
    */
   Interface _c;

   /**
    * Creates a new CreateInterface object.
    *
    * @param x DOCUMENT ME!
    * @param y DOCUMENT ME!
    * @param s DOCUMENT ME!
    */
   public CreateInterface(int x, int y, String s)
   {
      _x = x;
      _y = y;
      _state = s;
      _c = new Interface();
      _c.setState(s);
   }

   /**
    * DOCUMENT ME!
    */
   public void execute()
   {
      Umlet.getInstance().getPanel().add(_c);
      _c.setBounds(_x, _y, 120, 120);
      //_c.setLocation(_x,_y);
      _c.setVisible(true);

      Umlet.getInstance().getPanel().repaint();
   }

   /**
    * DOCUMENT ME!
    */
   public void undo()
   {
      Umlet.getInstance().getPanel().remove(_c);
      Umlet.getInstance().getPanel().repaint();
   }
}

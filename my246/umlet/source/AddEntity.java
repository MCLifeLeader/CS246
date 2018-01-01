// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/AddEntity.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
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
public class AddEntity
   extends Command
{
   /**
    * DOCUMENT ME!
    */
   Entity _entity;

   /**
    * DOCUMENT ME!
    */
   int _x;

   /**
    * DOCUMENT ME!
    */
   int _y;

   /**
    * Creates a new AddEntity object.
    *
    * @param e DOCUMENT ME!
    * @param x DOCUMENT ME!
    * @param y DOCUMENT ME!
    */
   public AddEntity(Entity e, int x, int y)
   {
      _entity = e;
      _x = x;
      _y = y;
   }

   /**
    * DOCUMENT ME!
    */
   public void execute()
   {
      Umlet.getInstance().getPanel().add(_entity);
      _entity.setLocation(_x, _y);
      Umlet.getInstance().getPanel().repaint();
   }

   /**
    * DOCUMENT ME!
    */
   public void undo()
   {
      Umlet.getInstance().getPanel().remove(_entity);
      Umlet.getInstance().getPanel().repaint();
   }
}

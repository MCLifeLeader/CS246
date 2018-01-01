// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/RemoveElement.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class RemoveElement
   extends Command
{
   /**
    * DOCUMENT ME!
    */
   Vector _entities;

   /**
    * Creates a new RemoveElement object.
    *
    * @param v DOCUMENT ME!
    */
   public RemoveElement(Vector v)
   {
      _entities = v;
   }

   /**
    * DOCUMENT ME!
    */
   public void execute()
   {
      for (int i = 0; i < _entities.size(); i++)
      {
         Entity e = (Entity) _entities.elementAt(i);
         Umlet.getInstance().getPanel().remove(e);
      }

      Umlet.getInstance().getPanel().repaint();
      Selector.getInstance().deselectAll();
   }

   /**
    * DOCUMENT ME!
    */
   public void undo()
   {
      for (int i = 0; i < _entities.size(); i++)
      {
         Entity e = (Entity) _entities.elementAt(i);
         Umlet.getInstance().getPanel().add(e);
      }

      Umlet.getInstance().getPanel().repaint();
   }
}

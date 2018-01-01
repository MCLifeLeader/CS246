// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Zoom.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
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
public class Zoom
   extends Command
{
   /**
    * DOCUMENT ME!
    */
   private Vector _entities;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Vector getEntities()
   {
      return _entities;
   }

   /**
    * DOCUMENT ME!
    */
   private double _z;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public double getZ()
   {
      return _z;
   }

   /**
    * Creates a new Zoom object.
    *
    * @param v DOCUMENT ME!
    * @param z DOCUMENT ME!
    */
   public Zoom(Vector v, double z)
   {
      _entities = v;
      _z = z;
   }

   /**
    * DOCUMENT ME!
    */
   public void execute()
   {
      int i;

      for (i = 0; i < _entities.size(); i++)
      {
         Entity e = (Entity) _entities.elementAt(i);
         e.zoomSize(_z);
         e.zoomLocation(_z, Umlet.getInstance().getCenterX(),
            Umlet.getInstance().getCenterY());
      }
   }

   /**
    * DOCUMENT ME!
    */
   public void undo()
   {
      int i;

      for (i = 0; i < _entities.size(); i++)
      {
         Entity e = (Entity) _entities.elementAt(i);
         e.zoomSize(1 / _z);
         e.zoomLocation(1 / _z, Umlet.getInstance().getCenterX(),
            Umlet.getInstance().getCenterY());
      }
   }
}

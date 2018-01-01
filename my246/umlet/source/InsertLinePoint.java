// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/InsertLinePoint.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.awt.*;
import java.awt.geom.*;

import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class InsertLinePoint
   extends Command
{
   /**
    * DOCUMENT ME!
    */
   private Relation _relation;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Relation getRelation()
   {
      return _relation;
   }

   /**
    * DOCUMENT ME!
    */
   private int _where;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getWhere()
   {
      return _where;
   }

   /**
    * DOCUMENT ME!
    */
   private int _x;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getX()
   {
      return _x;
   }

   /**
    * DOCUMENT ME!
    */
   private int _y;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getY()
   {
      return _y;
   }

   /**
    * Creates a new InsertLinePoint object.
    *
    * @param r DOCUMENT ME!
    * @param i DOCUMENT ME!
    * @param x DOCUMENT ME!
    * @param y DOCUMENT ME!
    */
   public InsertLinePoint(Relation r, int i, int x, int y)
   {
      _relation = r;
      _where = i;
      _x = x;
      _y = y;
   }

   /**
    * DOCUMENT ME!
    */
   public void execute()
   {
      Vector tmp = _relation.getLinePoints();
      tmp.insertElementAt(new Point(_x, _y), _where);
      _relation.repaint();
   }

   /**
    * DOCUMENT ME!
    */
   public void undo()
   {
      Vector tmp = _relation.getLinePoints();
      tmp.removeElementAt(_where);
      _relation.repaint();
   }
}

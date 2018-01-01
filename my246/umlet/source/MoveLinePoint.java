// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/MoveLinePoint.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
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
public class MoveLinePoint
   extends Command
{
   /**
    * DOCUMENT ME!
    */
   private Relation _relation;

   /**
    * DOCUMENT ME!
    */
   private int _linePointId;

   /**
    * DOCUMENT ME!
    */
   private int _diffx;

   /**
    * DOCUMENT ME!
    */
   private int _diffy;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getLinePointId()
   {
      return _linePointId;
   }

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
    *
    * @return DOCUMENT ME!
    */
   public int getDiffX()
   {
      return _diffx;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getDiffY()
   {
      return _diffy;
   }

   /**
    * Creates a new MoveLinePoint object.
    *
    * @param rel DOCUMENT ME!
    * @param i DOCUMENT ME!
    * @param diffx DOCUMENT ME!
    * @param diffy DOCUMENT ME!
    */
   public MoveLinePoint(Relation rel, int i, int diffx, int diffy)
   {
      _relation = rel;
      _linePointId = i;
      _diffx = diffx;
      _diffy = diffy;
   }

   /**
    * DOCUMENT ME!
    */
   public void execute()
   {
      Point p = (Point) _relation.getLinePoints().elementAt(_linePointId);
      p.x = p.x + _diffx;
      p.y = p.y + _diffy;
      _relation.repaint();
   }

   /**
    * DOCUMENT ME!
    */
   public void undo()
   {
      Point p = (Point) _relation.getLinePoints().elementAt(_linePointId);
      p.x = p.x - _diffx;
      p.y = p.y - _diffy;
      _relation.repaint();
   }

   /**
    * DOCUMENT ME!
    *
    * @param c DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public boolean isMergeableTo(Command c)
   {
      if (! (c instanceof MoveLinePoint))
      {
         return false;
      }

      MoveLinePoint mlp = (MoveLinePoint) c;

      if (this.getRelation() != mlp.getRelation())
      {
         return false;
      }

      if (this.getLinePointId() != mlp.getLinePointId())
      {
         return false;
      }

      return true;
   }

   /**
    * DOCUMENT ME!
    *
    * @param c DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Command mergeTo(Command c)
   {
      MoveLinePoint tmp = (MoveLinePoint) c;
      MoveLinePoint ret = new MoveLinePoint(this.getRelation(),
            this.getLinePointId(), this.getDiffX() + tmp.getDiffX(),
            this.getDiffY() + tmp.getDiffY());

      return ret;
   }
}

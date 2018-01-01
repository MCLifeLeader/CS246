// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Arrow.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.element.base.detail;

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
public class Arrow
   extends Rectangle
{
   /**
    * DOCUMENT ME!
    */
   private Point _arrowEndA;

   /**
    * DOCUMENT ME!
    */
   private Point _arrowEndB;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Point getArrowEndA()
   {
      return _arrowEndA;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Point getArrowEndB()
   {
      return _arrowEndB;
   }

   /**
    * DOCUMENT ME!
    *
    * @param p DOCUMENT ME!
    */
   public void setArrowEndA(Point p)
   {
      _arrowEndA = p;
   }

   /**
    * DOCUMENT ME!
    *
    * @param p DOCUMENT ME!
    */
   public void setArrowEndB(Point p)
   {
      _arrowEndB = p;
   }

   /**
    * DOCUMENT ME!
    */
   private String _arrowType = null;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String getString()
   {
      return _arrowType;
   }

   /**
    * Creates a new Arrow object.
    *
    * @param arrowType DOCUMENT ME!
    */
   public Arrow(String arrowType)
   {
      super(0, 0, 1, 1);
      _arrowType = arrowType;
   }
}

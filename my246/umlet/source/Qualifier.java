// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Qualifier.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
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
public class Qualifier
   extends Rectangle
{
   /**
    * DOCUMENT ME!
    */
   String _string;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String getString()
   {
      return _string;
   }

   /**
    * Creates a new Qualifier object.
    *
    * @param s DOCUMENT ME!
    * @param a DOCUMENT ME!
    * @param b DOCUMENT ME!
    * @param c DOCUMENT ME!
    * @param d DOCUMENT ME!
    */
   public Qualifier(String s, int a, int b, int c, int d)
   {
      super(a, b, c, d);
      _string = s;
   }
}

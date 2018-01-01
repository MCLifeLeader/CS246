// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Role.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.awt.Rectangle;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class Role
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
    * Creates a new Role object.
    *
    * @param s DOCUMENT ME!
    * @param a DOCUMENT ME!
    * @param b DOCUMENT ME!
    * @param c DOCUMENT ME!
    * @param d DOCUMENT ME!
    */
   public Role(String s, int a, int b, int c, int d)
   {
      super(a, b, c, d);
      _string = s;
   }
}

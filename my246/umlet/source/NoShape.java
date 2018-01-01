// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/NoShape.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.element.base.detail;

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
public class NoShape
   extends Rectangle
{
   /**
    * Creates a new NoShape object.
    */
   public NoShape()
   {
      super(0, 0, 1, 1);
   }
}

// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Command.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
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
public abstract class Command
{
   /**
    * DOCUMENT ME!
    */
   public void execute()
   {
   }

   /**
    * DOCUMENT ME!
    */
   public void undo()
   {
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
      return false;
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
      return null;
   }
}

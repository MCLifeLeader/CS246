// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Connector.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
class Connector
{
   /**
    * DOCUMENT ME!
    */
   private Entity mConnectedToEntity;

   /**
    * DOCUMENT ME!
    */
   private Entity mConnectedEntity;

   /**
    * Creates a new Connector object.
    *
    * @param cTo DOCUMENT ME!
    * @param cEnt DOCUMENT ME!
    */
   public Connector(Entity cTo, Entity cEnt)
   {
      mConnectedToEntity = cTo;
      mConnectedEntity = cEnt;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Entity getConnectedToEntity()
   {
      return mConnectedToEntity;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Entity getConnectedEntity()
   {
      return mConnectedEntity;
   }
}

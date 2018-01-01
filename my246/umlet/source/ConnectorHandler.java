// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/ConnectorHandler.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.util.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
class ConnectorHandler
{
   /**
    * Creates a new ConnectorHandler object.
    */
   private ConnectorHandler()
   {
   }

   /**
    * DOCUMENT ME!
    */
   private Vector mConnectors = new Vector();

   /**
    * DOCUMENT ME!
    */
   private static ConnectorHandler instance = new ConnectorHandler();

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static ConnectorHandler getInstance()
   {
      //if (instance == null) instance = new ConnectorHandler();
      return instance;
   }

   /**
    * DOCUMENT ME!
    *
    * @param cTo DOCUMENT ME!
    * @param cEnt DOCUMENT ME!
    */
   public void addConnector(Entity cTo, Entity cEnt)
   {
      if (! existsConnector(cTo, cEnt))
      {
         mConnectors.add(new Connector(cTo, cEnt));
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param cTo DOCUMENT ME!
    * @param cEnt DOCUMENT ME!
    */
   public void removeConnector(Entity cTo, Entity cEnt)
   {
      Connector tmpConnector = getConnector(cTo, cEnt);

      if (tmpConnector == null)
      {
         return;
      }

      mConnectors.removeElement(tmpConnector);
   }

   /**
    * DOCUMENT ME!
    *
    * @param cTo DOCUMENT ME!
    * @param cEnt DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public boolean existsConnector(Entity cTo, Entity cEnt)
   {
      return getConnector(cTo, cEnt) != null;
   }

   /**
    * DOCUMENT ME!
    *
    * @param cTo DOCUMENT ME!
    * @param cEnt DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Connector getConnector(Entity cTo, Entity cEnt)
   {
      Connector ret = null;

      for (int i = 0; i < mConnectors.size(); i++)
      {
         Connector tmpConnector = (Connector) mConnectors.elementAt(i);

         if ((tmpConnector.getConnectedToEntity() == cTo)
                && (tmpConnector.getConnectedEntity() == cEnt))
         {
            return tmpConnector;
         }
      }

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Vector getConnectorsByConnectedToEntity(Entity e)
   {
      Vector ret = new Vector();

      for (int i = 0; i < mConnectors.size(); i++)
      {
         Connector tmpConnector = (Connector) mConnectors.elementAt(i);
         Entity tmpEntity = tmpConnector.getConnectedToEntity();

         if (tmpEntity == e)
         {
            ret.add(tmpConnector);
         }
      }

      return ret;
   }
}

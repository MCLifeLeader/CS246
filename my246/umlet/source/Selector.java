// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Selector.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.awt.*;

import java.util.*;

import javax.swing.JComponent;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class Selector
{
   /**
    * DOCUMENT ME!
    */
   private Entity _ssiEntity;

   /**
    * DOCUMENT ME!
    */
   private int _ssiX = -99999;

   /**
    * DOCUMENT ME!
    */
   private int _ssiY = -99999;

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    * @param x DOCUMENT ME!
    * @param y DOCUMENT ME!
    */
   public void setSingleSelectorInformation(Entity e, int x, int y)
   {
      _ssiEntity = e;
      _ssiX = x;
      _ssiY = y;
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    * @param x DOCUMENT ME!
    * @param y DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public boolean hasSameSelectionPattern(Entity e, int x, int y)
   {
      return (_selectedEntities.contains(e) && (_selectedEntities.size() == 1)
      && (e == _ssiEntity) && (x == _ssiX) && (y == _ssiY));
   }

   /**
    * DOCUMENT ME!
    */
   private Vector _selectedEntities = new Vector();

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Vector getSelectedEntities()
   {
      return _selectedEntities;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Vector getSelectedEntitiesOnPanel()
   {
      Vector ret = new Vector();

      for (int i = 0; i < getSelectedEntities().size(); i++)
      {
         Entity e = (Entity) getSelectedEntities().elementAt(i);

         if (e.getParent() == Umlet.getInstance().getPanel())
         {
            ret.add(e);
         }
      }

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Vector getAllEntitiesOnPanel()
   {
      Component[] tmp = Umlet.getInstance().getPanel().getComponents();
      Vector ret = new Vector();

      for (int i = 0; i < tmp.length; i++)
      {
         ret.add(tmp[i]);
      }

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Vector getAllRelationsOnPanel()
   {
      Component[] tmp = Umlet.getInstance().getPanel().getComponents();
      Vector ret = new Vector();

      for (int i = 0; i < tmp.length; i++)
      {
         if (tmp[i] instanceof Relation)
         {
            ret.add(tmp[i]);
         }
      }

      return ret;
   }

   /**
    * DOCUMENT ME!
    */
   private static Selector _instance = new Selector();

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static Selector getInstance()
   {
      //if (_instance == null) _instance = new Selector();
      return _instance;
   }

   /**
    * Creates a new Selector object.
    */
   private Selector()
   {
   }

   /**
    * DOCUMENT ME!
    */
   void deselectAll()
   {
      for (int i = 0; i < _selectedEntities.size(); i++)
      {
         Entity e = (Entity) _selectedEntities.elementAt(i);
         e.onDeselected();
      }

      _selectedEntities = new Vector();
      updatePropertyPanel();
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    */
   void selectXXX(Entity e)
   {
      if (_selectedEntities.contains(e))
      {
         return;
      }

      _selectedEntities.add(e);

      e.onSelected();
      updatePropertyPanel();
   }

   /*  void selectOrDeselect(Entity e) {
      if (_selectedEntities.contains(e)) {
        _selectedEntities.removeElement(e);
        e.onDeselected();
      } else {
        _selectedEntities.add(e);
        e.onSelected();
      }
      updatePropertyPanel();
      }*/

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    */
   void deselect(Entity e)
   {
      if (_selectedEntities.contains(e))
      {
         _selectedEntities.removeElement(e);
         e.onDeselected();
         updatePropertyPanel();
      }
   }

   /**
    * DOCUMENT ME!
    */
   void updatePropertyPanel()
   {
      if (_selectedEntities.size() == 1)
      {
         Umlet.getInstance()
              .setPropertyPanelToEntity((Entity) _selectedEntities.elementAt(0));
      }
      else
      {
         Umlet.getInstance().setPropertyPanelToEntity(null);
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    * @param singleSelect DOCUMENT ME!
    */
   public void singleSelect(Entity e, boolean singleSelect)
   {
      boolean hasBeenSelected = false;

      if (_selectedEntities.contains(e))
      {
         hasBeenSelected = true;
      }

      if (singleSelect)
      {
         deselectAll();
      }

      _selectedEntities.add(e);
      e.onSelected();
      updatePropertyPanel();
   }
}

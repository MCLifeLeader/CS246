// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Entity.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.element.base;

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
public abstract class Entity
   extends JComponent
{
   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int[] getCoordinates()
   {
      int[] ret = new int[4];
      ret[0] = getX();
      ret[1] = getY();
      ret[2] = getWidth();
      ret[3] = getHeight();

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String getClassCode()
   {
      return this.getClass().getName().toUpperCase().substring(0, 4);
   }

   /*public String getStateAndHiddenState() {
      Vector tmp=new Vector();
      tmp.add(this.getState());
      tmp.add(this.getHiddenState());
   
           String ret=Constants.composeStrings(tmp, Constants.DELIMITER_STATE_AND_HIDDEN_STATE);
           return ret;
         }*/

   /**
    * DOCUMENT ME!
    *
    * @param s DOCUMENT ME!
    */
   private void setStateAndHiddenState(String s)
   {
      Vector tmp = Constants.decomposeStringsIncludingEmptyStrings(s,
            Constants.DELIMITER_STATE_AND_HIDDEN_STATE);

      if (tmp.size() != 2)
      {
         throw new RuntimeException();
      }

      String state = (String) tmp.elementAt(0);
      String hiddenState = (String) tmp.elementAt(1);

      this.setHiddenState(hiddenState);
      this.setState(state);
   }

   /**
    * DOCUMENT ME!
    */
   protected Color _selectedColor = Color.blue;

   /**
    * DOCUMENT ME!
    */
   protected Color _deselectedColor = Color.black;

   /**
    * DOCUMENT ME!
    */
   protected Color _activeColor = _deselectedColor;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Entity cloneFromMe()
   {
      return null;
   }

   /**
    * DOCUMENT ME!
    */
   protected boolean _selected = false;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public boolean isSelected()
   {
      return _selected;
   }

   /**
    * DOCUMENT ME!
    */
   protected String _state = "";

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String getPanelAttributes()
   {
      return _state;
   }

   /**
    * DOCUMENT ME!
    *
    * @param s DOCUMENT ME!
    */
   public void setState(String s)
   {
      _state = s;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String getAdditionalAttributes()
   {
      /*Vector tmp=new Vector();
         tmp.add(""+this.getX());
         tmp.add(""+this.getY());
         tmp.add(""+this.getWidth());
         tmp.add(""+this.getHeight());
         String ret=Constants.composeStrings(tmp, Constants.DELIMITER_FIELDS);*/
      return "";
   }

   /**
    * DOCUMENT ME!
    *
    * @param s DOCUMENT ME!
    */
   public void setAdditionalAttributes(String s)
   {
   }

   /**
    * DOCUMENT ME!
    *
    * @param s DOCUMENT ME!
    */
   public void setHiddenState(String s)
   {
      Vector tmp = Constants.decomposeStrings(s, Constants.DELIMITER_FIELDS);

      if (tmp.size() != 4)
      {
         throw new RuntimeException(
            "UMLet: Error in Entity.setHiddenState(), state value = " + s);
      }

      int x = Integer.parseInt((String) tmp.elementAt(0));
      int y = Integer.parseInt((String) tmp.elementAt(1));
      int w = Integer.parseInt((String) tmp.elementAt(2));
      int h = Integer.parseInt((String) tmp.elementAt(3));
      this.setBounds(x, y, w, h);
   }

   /**
    * Creates a new Entity object.
    *
    * @param s DOCUMENT ME!
    */
   public Entity(String s)
   {
      this.setStateAndHiddenState(s);

      this.addMouseListener(UniversalListener.getInstance());
      this.addMouseMotionListener(UniversalListener.getInstance());
      this.setVisible(true);
   }

   /**
    * Creates a new Entity object.
    */
   public Entity()
   {
      this.addMouseListener(UniversalListener.getInstance());
      this.addMouseMotionListener(UniversalListener.getInstance());
      this.setVisible(true);
   }

   /**
    * DOCUMENT ME!
    *
    * @param diffx DOCUMENT ME!
    * @param diffy DOCUMENT ME!
    */
   public void changeLocation(int diffx, int diffy)
   {
      this.setLocation(this.getX() + diffx, this.getY() + diffy);
   }

   /**
    * DOCUMENT ME!
    *
    * @param diffx DOCUMENT ME!
    * @param diffy DOCUMENT ME!
    */
   public void changeSize(int diffx, int diffy)
   {
      this.setSize(this.getWidth() + diffx, this.getHeight() + diffy);
   }

   /**
    * DOCUMENT ME!
    *
    * @param percent DOCUMENT ME!
    */
   public void zoomSize(double percent)
   {
      this.setSize((int) ((double) this.getWidth() * percent),
         (int) ((double) this.getHeight() * percent));
   }

   /**
    * DOCUMENT ME!
    *
    * @param percent DOCUMENT ME!
    * @param centerX DOCUMENT ME!
    * @param centerY DOCUMENT ME!
    */
   public void zoomLocation(double percent, int centerX, int centerY)
   {
      int newX = centerX + (int) (((double) (this.getX() - centerX)) * percent);
      int newY = centerY + (int) (((double) (this.getY() - centerY)) * percent);
      this.setLocation(newX, newY);
   }

   // bit 1 top, 2 right, 3 bottom, 4 left
   /**
    * DOCUMENT ME!
    *
    * @param x DOCUMENT ME!
    * @param y DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getResizeArea(int x, int y)
   {
      if (this instanceof Interface)
      {
         return 0;
      }

      int ret = 0;

      if (y < 5)
      {
         ret = 1;
      }
      else if ((this.getHeight() - y) < 5)
      {
         ret = 4;
      }

      if (x < 5)
      {
         ret = ret | 8;
      }
      else if ((this.getWidth() - x) < 5)
      {
         ret = ret | 2;
      }

      return ret;
   }

   /**
    * DOCUMENT ME!
    */
   public void onSelected()
   {
      _selected = true;
      _activeColor = _selectedColor;
      this.repaint();
   }

   /**
    * DOCUMENT ME!
    */
   public void onDeselected()
   {
      _selected = false;
      _activeColor = _deselectedColor;
      this.repaint();
   }

   /**
    * DOCUMENT ME!
    *
    * @param p DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int doesCoordinateAppearToBeConnectedToMe(Point p)
   {
      int ret = 0;

      int tmpX = p.x - this.getX();
      int tmpY = p.y - this.getY();

      if ((tmpX > -4) && (tmpX < (this.getWidth() + 4)))
      {
         if ((tmpY > -4) && (tmpY < 4))
         {
            ret += 1;
         }

         if ((tmpY > (this.getHeight() - 4)) && (tmpY < (this.getHeight() + 4)))
         {
            ret += 4;
         }
      }

      if ((tmpY > -4) && (tmpY < (this.getHeight() + 4)))
      {
         if ((tmpX > -4) && (tmpX < 4))
         {
            ret += 8;
         }

         if ((tmpX > (this.getWidth() - 4)) && (tmpX < (this.getWidth() + 4)))
         {
            ret += 2;
         }
      }

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @param action DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Vector getAffectedRelationLinePoints(int action)
   {
      Vector ret = new Vector();
      Vector allRelations = Selector.getInstance().getAllRelationsOnPanel();

      for (int i = 0; i < allRelations.size(); i++)
      {
         Relation r = (Relation) allRelations.elementAt(i);
         int tmpInt = r.getConnectedLinePoint(this, action);

         if (tmpInt > 0)
         {
            int first = (tmpInt % 1000) - 1;

            if (first > -1)
            {
               ret.add(new RelationLinePoint(r, first));
            }

            int last = (tmpInt / 1000) - 1;

            if (last > -1)
            {
               ret.add(new RelationLinePoint(r, last));
            }
         }
      }

      return ret;
   }
}

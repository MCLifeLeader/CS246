// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Actor.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.element.base;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
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
public class Actor
   extends Entity
{
   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Entity cloneFromMe()
   {
      Actor ret = new Actor();
      ret.setState(this.getPanelAttributes());

      ret.setBounds(this.getBounds());

      int i = (ret.getWidth() + Umlet.getInstance().getMainUnit()) - 1;
      i = i / Umlet.getInstance().getMainUnit();
      ret.setBounds(new Rectangle(i, ret.getHeight()));

      return ret;
   }

   /**
    * Creates a new Actor object.
    *
    * @param s DOCUMENT ME!
    */
   public Actor(String s)
   {
      super(s);
   }

   /**
    * Creates a new Actor object.
    */
   public Actor()
   {
      super();
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   private Vector getStringVector()
   {
      Vector ret = Constants.decomposeStrings(this.getPanelAttributes(), "\n");

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @param g DOCUMENT ME!
    */
   public void paint(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      g2.setFont(Constants.getFont());
      g2.setColor(_activeColor);
      Constants.getFRC(g2);

      /*FontRenderContext rendering;
         if (Constants.getFontsize()>12) {
              rendering=new FontRenderContext(null, true, true);
              g2.setRenderingHints(Constants.UxRenderingQualityHigh());
         }
         else {
              rendering=new FontRenderContext(null, false, false);
                g2.setRenderingHints(Constants.UxRenderingQualityLow());
           }*/
      boolean ADAPT_SIZE = false;

      Vector tmp = getStringVector();
      int yPos = 6 * Constants.getFontsize();

      for (int i = 0; i < tmp.size(); i++)
      {
         String s = (String) tmp.elementAt(i);

         if (s.equals("--"))
         {
            yPos += Constants.getDistTextToLine();
            g2.drawLine((this.getWidth() / 2) - (Constants.getFontsize() * 4),
               yPos, (this.getWidth() / 2) + (Constants.getFontsize() * 4), yPos);
            yPos += Constants.getDistLineToText();
         }
         else
         {
            yPos += Constants.getFontsize();

            TextLayout l = new TextLayout(s, Constants.getFont(),
                  Constants.getFRC(g2));
            Rectangle2D r2d = l.getBounds();
            int width = (int) r2d.getWidth();
            int xPos = (this.getWidth() / 2) - (width / 2);

            if (xPos < 0)
            {
               ADAPT_SIZE = true;

               break;
            }

            Constants.write(g2, s, this.getWidth() / 2, yPos, true);
            yPos += Constants.getDistTextToText();
         }
      }

      if (ADAPT_SIZE)
      {
         Controller.getInstance()
                   .executeCommandWithoutUndo(new Resize(this, 8,
               -Umlet.getInstance().getMainUnit(), 0));
         Controller.getInstance()
                   .executeCommandWithoutUndo(new Resize(this, 2,
               Umlet.getInstance().getMainUnit(), 0));

         return;
      }

      if (yPos > this.getHeight())
      {
         Controller.getInstance()
                   .executeCommandWithoutUndo(new Resize(this, 4, 0, 20));

         return;
      }

      int startx = this.getWidth() / 2;

      g2.drawOval(startx - (Constants.getFontsize() / 2), 0,
         Constants.getFontsize(), Constants.getFontsize());
      g2.drawLine(startx, Constants.getFontsize(), startx,
         (int) (Constants.getFontsize() * 2.5));
      g2.drawLine(startx - (2 * Constants.getFontsize()),
         (int) (Constants.getFontsize() * 1.3),
         startx + (2 * Constants.getFontsize()),
         (int) (Constants.getFontsize() * 1.3));

      // Feet
      g2.drawLine(startx, (int) (Constants.getFontsize() * 2.5),
         startx - Constants.getFontsize(), Constants.getFontsize() * 5);
      g2.drawLine(startx, (int) (Constants.getFontsize() * 2.5),
         startx + Constants.getFontsize(), Constants.getFontsize() * 5);
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
      int tmpX = p.x - this.getX();
      int tmpY = p.y - this.getY();

      int links = (this.getWidth() / 2) - Umlet.getInstance().getMainUnit();
      int rechts = (this.getWidth() / 2) + Umlet.getInstance().getMainUnit();

      int oben = 0;
      int unten = 4 * Umlet.getInstance().getMainUnit();

      if ((tmpX > (links - 4)) && (tmpX < (rechts + 4)))
      {
         if ((tmpY > (oben - 4)) && (tmpY < (unten + 4)))
         {
            return 15;
         }
      }

      if ((tmpY > (oben - 4)) && (tmpY < (unten + 4)))
      {
         if ((tmpX > (links - 4)) && (tmpX < (rechts + 4)))
         {
            return 15;
         }
      }

      return 0;
   }
}

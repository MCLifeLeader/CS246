// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/UseCase.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
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
public class UseCase
   extends Entity
{
   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Entity cloneFromMe()
   {
      UseCase c = new UseCase();
      c.setState(this.getPanelAttributes());
      //c.setVisible(true);
      c.setBounds(this.getBounds());

      return c;
   }

   /**
    * Creates a new UseCase object.
    *
    * @param s DOCUMENT ME!
    */
   public UseCase(String s)
   {
      super(s);
   }

   /**
    * Creates a new UseCase object.
    */
   public UseCase()
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
         } else {
              rendering=new FontRenderContext(null, false, false);
              g2.setRenderingHints(Constants.UxRenderingQualityLow());
           }*/
      Vector tmp = new Vector(getStringVector());

      if (tmp.contains("lt=."))
      {
         tmp.remove("lt=.");
         g2.setStroke(Constants.getStroke(1, 1));
      }

      int yPos = (this.getHeight() / 2)
         - ((tmp.size() * (Constants.getFontsize()
         + Constants.getDistTextToText())) / 2);

      boolean CENTER = true;

      for (int i = 0; i < tmp.size(); i++)
      {
         String s = (String) tmp.elementAt(i);

         if (s.equals("--"))
         {
            //CENTER=false;
            //yPos+=Constants.getDistTextToLine();
            g2.drawLine(0, yPos, this.getWidth(), yPos);
            yPos += Constants.getDistLineToText();
         }
         else
         {
            yPos += Constants.getFontsize();

            if (CENTER)
            {
               Constants.write(g2, s, (int) this.getWidth() / 2, yPos, true);
            }
            else
            {
               Constants.write(g2, s, Constants.getFontsize() / 2, yPos, false);
            }

            yPos += Constants.getDistTextToText();
         }
      }

      Rectangle r = this.getBounds();
      g.drawOval(0, 0, (int) r.getWidth() - 1, (int) r.getHeight() - 1);

      /*if (_selected) {
         g.drawOval(1,1,(int)r.getWidth()-3,(int)r.getHeight()-3);
         }*/
   }
}

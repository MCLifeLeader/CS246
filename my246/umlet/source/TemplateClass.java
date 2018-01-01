// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/TemplateClass.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.element.custom;

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
public class TemplateClass
   extends Entity
{
   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Entity cloneFromMe()
   {
      TemplateClass c = new TemplateClass();
      c.setState(this.getPanelAttributes());
      //c.setVisible(true);
      c.setBounds(this.getBounds());

      return c;
   }

   /**
    * Creates a new TemplateClass object.
    *
    * @param s DOCUMENT ME!
    */
   public TemplateClass(String s)
   {
      super(s);
   }

   /**
    * Creates a new TemplateClass object.
    */
   public TemplateClass()
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

      //Constants.getFRC(g2);
      Vector tmp = getStringVector();

      int yPos = 0;
      yPos += Constants.getDistLineToText();

      boolean CENTER = true;

      for (int i = 0; i < tmp.size(); i++)
      {
         String s = (String) tmp.elementAt(i);

         if (s.equals("--"))
         {
            CENTER = false;
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
      g.drawRect(0, 0, (int) r.getWidth() - 1, (int) r.getHeight() - 1);

      /*if (_selected) {
         g.drawRect(1,1,(int)r.getWidth()-3,(int)r.getHeight()-3);
         }*/
   }
}

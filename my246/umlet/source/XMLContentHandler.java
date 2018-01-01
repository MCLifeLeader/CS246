// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/XMLContentHandler.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.*;

import java.io.*;

import java.util.*;

import javax.swing.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
public class XMLContentHandler
   extends DefaultHandler
{
   /**
    * DOCUMENT ME!
    */
   private JPanel _p = null;

   /**
    * DOCUMENT ME!
    */
   private Entity e = null;

   /**
    * DOCUMENT ME!
    */
   private String elementtext;

   /**
    * DOCUMENT ME!
    */
   private int x;

   /**
    * DOCUMENT ME!
    */
   private int y;

   /**
    * DOCUMENT ME!
    */
   private int w;

   /**
    * DOCUMENT ME!
    */
   private int h;

   /**
    * Creates a new XMLContentHandler object.
    *
    * @param p DOCUMENT ME!
    */
   public XMLContentHandler(JPanel p)
   {
      _p = p;
   }

   /**
    * DOCUMENT ME!
    *
    * @param uri DOCUMENT ME!
    * @param localName DOCUMENT ME!
    * @param qName DOCUMENT ME!
    * @param attributes DOCUMENT ME!
    */
   public void startElement(java.lang.String uri, java.lang.String localName,
      java.lang.String qName, Attributes attributes)
   {
      elementtext = "";
   }

   /**
    * DOCUMENT ME!
    *
    * @param uri DOCUMENT ME!
    * @param localName DOCUMENT ME!
    * @param qName DOCUMENT ME!
    */
   public void endElement(java.lang.String uri, java.lang.String localName,
      java.lang.String qName)
   {
      String elementname = localName;

      if (elementname.equals("type"))
      {
         java.lang.Class c = null;

         try
         {
            c = java.lang.Class.forName(elementtext);
            e = (Entity) c.newInstance();
         }
         catch (Exception e)
         {
         }

         _p.add(e);

         return;
      }

      if (elementname.equals("x"))
      {
         Integer i = new Integer(elementtext);
         x = i.intValue();

         return;
      }

      if (elementname.equals("y"))
      {
         Integer i = new Integer(elementtext);
         y = i.intValue();

         return;
      }

      if (elementname.equals("w"))
      {
         Integer i = new Integer(elementtext);
         w = i.intValue();

         return;
      }

      if (elementname.equals("h"))
      {
         Integer i = new Integer(elementtext);
         h = i.intValue();

         e.setLocation(x, y);
         e.setSize(w, h);

         return;
      }

      if (elementname.equals("panel_attributes"))
      {
         e.setState(elementtext);

         return;
      }

      if (elementname.equals("additional_attributes"))
      {
         e.setAdditionalAttributes(elementtext);

         return;
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param ch DOCUMENT ME!
    * @param start DOCUMENT ME!
    * @param length DOCUMENT ME!
    */
   public void characters(char[] ch, int start, int length)
   {
      elementtext += (new String(ch)).substring(start, start + length);
   }
}

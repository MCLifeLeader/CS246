// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/GenSvg.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control.io;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.io.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.util.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
public class GenSvg
{
   /**
    * DOCUMENT ME!
    */
   private static GenSvg _instance = new GenSvg();

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static GenSvg getInstance()
   {
      //if (_instance == null)
      //{
      //   _instance = new GenSvg();
      //}
      return _instance;
   }

   /**
    * DOCUMENT ME!
    *
    * @param g2d DOCUMENT ME!
    */
   public void paint(Graphics2D g2d)
   {
      /*g2d.setPaint(Color.red);
         g2d.fill(new Rectangle(10, 10, 100, 100));
       */

      /*Actor a=new Actor();
         a.setSize(80,120);
         a.setState("Testactor");
         a.setLocation(40,150);*/
      Vector v = Selector.getInstance().getAllEntitiesOnPanel();

      for (int i = 0; i < v.size(); i++)
      {
         Entity e = (Entity) v.elementAt(i);
         g2d.translate(e.getX(), e.getY());
         e.paint(g2d);
         g2d.translate(-e.getX(), -e.getY());
      }

      //Frame.IS_CLIPPING=true;
      //Frame.getInstance().getPanel().paint(g2d);
   }

   /**
    * DOCUMENT ME!
    */
   public static void genSvg()
   {
      try
      {
         // Get a DOMImplementation
         DOMImplementation domImpl = GenericDOMImplementation
            .getDOMImplementation();

         // Create an instance of org.w3c.dom.Document
         Document document = domImpl.createDocument(null, "svg", null);

         // Create an instance of the SVG Generator
         SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

         // Ask the test to render into the SVG Graphics2D implementation
         GenSvg test = new GenSvg();
         test.paint(svgGenerator);

         // Finally, stream out SVG to the standard output using UTF-8
         // character to byte encoding
         boolean useCSS = true; // we want to use CSS style attribute
         Writer out = new OutputStreamWriter(System.out, "UTF-8");
         svgGenerator.stream(out, useCSS);
      }
      catch (IOException e)
      {
         System.out.println("IO Exception.");
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param filename DOCUMENT ME!
    */
   public static void createAndOutputSvgToFile(String filename)
   {
      try
      {
         // Get a DOMImplementation
         DOMImplementation domImpl = GenericDOMImplementation
            .getDOMImplementation();

         // Create an instance of org.w3c.dom.Document
         Document document = domImpl.createDocument(null, "svg", null);

         // Create an instance of the SVG Generator
         SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

         // Ask the test to render into the SVG Graphics2D implementation
         GenSvg test = new GenSvg();
         test.paint(svgGenerator);

         // Finally, stream out SVG to the standard output using UTF-8
         // character to byte encoding
         boolean useCSS = true; // we want to use CSS style attribute
         Writer out = new OutputStreamWriter(new FileOutputStream(filename),
               "UTF-8");
         svgGenerator.stream(out, useCSS);

         //OutputStream ostream = new FileOutputStream("c:\\t\\out.pdf");
         /*TranscoderOutput output = new TranscoderOutput(ostream);
            // save the image
            t.transcode(input, output);
            // flush and close the stream then exit
            ostream.flush();
            ostream.close();*/
      }
      catch (IOException e)
      {
         System.out.println("IO Exception.");
      }
   }
}

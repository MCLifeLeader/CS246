// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/GenPdf.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control.io;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;

import org.apache.fop.svg.PDFTranscoder;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.*;
import java.awt.geom.*;

import java.io.*;

import java.util.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
public class GenPdf
{
   /**
    * DOCUMENT ME!
    */
   private static GenPdf _instance = new GenPdf();

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static GenPdf getInstance()
   {
      //if (_instance == null)
      //{
      //   _instance = new GenPdf();
      //}
      return _instance;
   }

   /**
    * Creates a new GenPdf object.
    */
   private GenPdf()
   {
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   private Dimension calculateCanvas()
   {
      //int w=50;
      //int h=50;
      int maxx = 50;
      int minx = 0;
      int maxy = 50;
      int miny = 0;

      Vector v = Selector.getInstance().getAllEntitiesOnPanel();

      for (int i = 0; i < v.size(); i++)
      {
         Entity e = (Entity) v.elementAt(i);
         maxx = Math.max(maxx, e.getX() + e.getWidth() + 20);
         maxy = Math.max(maxy, e.getY() + e.getHeight() + 20);
         minx = Math.min(minx, e.getX() - 20);
         miny = Math.min(miny, e.getY() - 20);
      }

      return new Dimension(maxx - minx, maxy - miny);
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   private Dimension calculateTranslate()
   {
      //int w=50;
      //int h=50;
      int maxx = 50;
      int minx = 0;
      int maxy = 50;
      int miny = 0;

      Vector v = Selector.getInstance().getAllEntitiesOnPanel();

      for (int i = 0; i < v.size(); i++)
      {
         Entity e = (Entity) v.elementAt(i);
         maxx = Math.max(maxx, e.getX() + e.getWidth() + 20);
         maxy = Math.max(maxy, e.getY() + e.getHeight() + 20);
         minx = Math.min(minx, e.getX() - 20);
         miny = Math.min(miny, e.getY() - 20);
      }

      return new Dimension(minx, miny);
   }

   /**
    * DOCUMENT ME!
    *
    * @param filename DOCUMENT ME!
    */
   public void createAndOutputJpgToFile(String filename)
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
         Dimension trans = calculateTranslate();
         svgGenerator.translate(-(double) trans.getWidth(),
            -(double) trans.getHeight());
         svgGenerator.setSVGCanvasSize(calculateCanvas());

         // Ask the test to render into the SVG Graphics2D implementation
         GenSvg test = new GenSvg();
         test.paint(svgGenerator);

         // Finally, stream out SVG to the standard output using UTF-8
         // character to byte encoding
         boolean useCSS = true; // we want to use CSS style attribute
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         Writer out = new OutputStreamWriter(baos);

         svgGenerator.stream(out, useCSS);

         ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

         // create a JPEG transcoder
         JPEGTranscoder t = new JPEGTranscoder();

         // set the transcoding hints
         t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(1.0));

         // create the transcoder input
         TranscoderInput input = new TranscoderInput(bais);

         // create the transcoder output
         OutputStream ostream = new FileOutputStream(filename);
         TranscoderOutput output = new TranscoderOutput(ostream);

         // save
         t.transcode(input, output);

         // flush and close the stream then exit
         ostream.flush();
         ostream.close();

         //System.exit(0);
      }
      catch (Exception e)
      {
         System.out.println("UMLet: Error: Exception in outputJpeg: " + e);
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param filename DOCUMENT ME!
    */
   public void createAndOutputPdfToFile(String filename)
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
         Dimension trans = calculateTranslate();
         svgGenerator.translate(-(double) trans.getWidth(),
            -(double) trans.getHeight());
         svgGenerator.setSVGCanvasSize(calculateCanvas());

         // Ask the test to render into the SVG Graphics2D implementation
         GenSvg test = new GenSvg();
         test.paint(svgGenerator);

         // Finally, stream out SVG to the standard output using UTF-8
         // character to byte encoding
         boolean useCSS = true; // we want to use CSS style attribute
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         Writer out = new OutputStreamWriter(baos);

         svgGenerator.stream(out, useCSS);

         ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

         // create a PDF transcoder
         PDFTranscoder t = new PDFTranscoder();

         // set the transcoding hints
         // t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,new Float(.8));

         // create the transcoder input
         TranscoderInput input = new TranscoderInput(bais);

         // create the transcoder output
         OutputStream ostream = new FileOutputStream(filename);
         TranscoderOutput output = new TranscoderOutput(ostream);

         // save
         t.transcode(input, output);

         // flush and close the stream then exit
         ostream.flush();
         ostream.close();

         //System.exit(0);
      }
      catch (Exception e)
      {
         System.out.println("UMLet: Error: Exception in outputPdf: " + e);
      }
   }
}

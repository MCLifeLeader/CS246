// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Constants.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.*;
import java.awt.image.*;

import java.util.*;

import javax.swing.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
public abstract class Constants
{
   /**
    * DOCUMENT ME!
    *
    * @param g2 DOCUMENT ME!
    * @param s DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static int getPixelWidth(Graphics2D g2, String s)
   {
      TextLayout tl = new TextLayout(s, Constants.getFont(),
            Constants.getFRC(g2));

      return (int) tl.getBounds().getWidth();
   }

   /**
    * DOCUMENT ME!
    *
    * @param g2 DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static FontRenderContext getFRC(Graphics2D g2)
   {
      FontRenderContext rendering;

      if ((Constants.getFontsize() > 12) & (Umlet.IS_CLIPPING == false))
      {
         rendering = new FontRenderContext(null, true, true);
         g2.setRenderingHints(Constants.UxRenderingQualityHigh());
      }
      else
      {
         rendering = new FontRenderContext(null, false, false);
         g2.setRenderingHints(Constants.UxRenderingQualityLow());
      }

      return rendering;
   }

   /**
    * DOCUMENT ME!
    *
    * @param g2 DOCUMENT ME!
    * @param s DOCUMENT ME!
    * @param x DOCUMENT ME!
    * @param y DOCUMENT ME!
    * @param center DOCUMENT ME!
    */
   public static void write(Graphics2D g2, String s, int x, int y,
      boolean center)
   {
      if (s == null)
      {
         return;
      }

      boolean underline = false;
      boolean italic = false;

      if (s.startsWith("_") && s.endsWith("_"))
      {
         underline = true;
         s = s.substring(1, s.length() - 1);
      }

      if (s.startsWith("/") && s.endsWith("/"))
      {
         italic = true;
         g2.setFont(Constants.getFontItalic());
         s = s.substring(1, s.length() - 1);
      }

      if (s.startsWith("_") && s.endsWith("_"))
      {
         underline = true;
         s = s.substring(1, s.length() - 1);
      }

      if (s.startsWith("<<") && s.endsWith(">>"))
      {
         s = s.substring(2, s.length() - 2);
         s = "�" + s + "�";
      }

      if (center)
      {
         x = x - (Constants.getPixelWidth(g2, s) / 2);
      }

      g2.drawString(s, x, y);

      if (italic)
      {
         g2.setFont(Constants.getFont());
      }

      if (underline)
      {
         TextLayout l = new TextLayout(s, Constants.getFont(), getFRC(g2));
         Rectangle2D r2d = l.getBounds();
         g2.drawLine(x, y + (Constants.getDistTextToLine() / 2),
            x + (int) r2d.getWidth(), y + (Constants.getDistTextToLine() / 2));
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param comp DOCUMENT ME!
    * @param transparent DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   protected static ImageProducer convertToGIF(final JComponent comp,
      boolean transparent)
   {
      comp.setDoubleBuffered(false);

      JFrame frame = new JFrame();
      frame.setContentPane(comp);

      //frame.pack();

      // Get a graphics region, using the Frame
      Dimension size = new Dimension(comp.getWidth(), comp.getHeight());
      Image image = comp.createImage(size.width, size.height);
      final Graphics g = image.getGraphics();
      // Workaround for JDK 1.2.2 bug (Linux)
      g.setClip(0, 0, size.width, size.height);

      comp.paint(g);

      try
      {
         Thread.currentThread().sleep(1000);
      }
      catch (Exception e)
      {
      }

      /*try
         {
            // Paint the Swing component into the image, being careful to comply
            // with Swing's single thread requirements.
      
                  SwingUtilities.invokeAndWait(new Runnable()
                  {
                     public void run()
                     {
                        comp.paint(g);
                     }
                  });
               }
               catch (Exception x) { x.printStackTrace(); }
               finally
               {
                  g.dispose();
                  frame.dispose();
               }*/
      ImageProducer prod = image.getSource();

      // Add a filter to make the background transparent

      /*if (transparent)
         {
            Color bg = comp.getBackground();
            ImageFilter f = new TransparentFilter(bg);
            prod = new FilteredImageSource(prod,f);
         }*/
      return prod;
   }

   /**
    * DOCUMENT ME!
    */
   public static final String DELIMITER_ENTITIES = "~~~~~|||||~~~~~|||||";

   /**
    * DOCUMENT ME!
    */
   public static final String DELIMITER_STATE_AND_HIDDEN_STATE = "/////<<<<</////<<<<<";

   /**
    * DOCUMENT ME!
    */
   public static final String DELIMITER_FIELDS = "#####_____#####_____";

   /**
    * DOCUMENT ME!
    */
   public static final String DELIMITER_ADDITIONAL_ATTRIBUTES = ";";

   /**
    * DOCUMENT ME!
    *
    * @param strokeType DOCUMENT ME!
    * @param lineThickness DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static BasicStroke getStroke(int strokeType, int lineThickness)
   {
      BasicStroke stroke = null;

      switch (strokeType)
      {
         case 0:
            stroke = new BasicStroke(lineThickness);

            break;

         case 1:

            float[] dash1 = { 8.0f, 5.0f };
            stroke = new BasicStroke(lineThickness, BasicStroke.CAP_BUTT,
                  BasicStroke.JOIN_MITER, 5.0f, dash1, 0.0f);

            break;

         case 2:
            break;
      }

      return stroke;
   }

   /**
    * DOCUMENT ME!
    *
    * @param p DOCUMENT ME!
    * @param pixels DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static Point normalize(Point p, int pixels)
   {
      Point ret = new Point();
      double d = Math.sqrt((p.x * p.x) + (p.y * p.y));
      ret.x = (int) ((double) p.x / d * (double) pixels);
      ret.y = (int) ((double) p.y / d * (double) pixels);

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @param s DOCUMENT ME!
    * @param delimiter DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static Vector decomposeStrings(String s, String delimiter)
   {
      Vector ret = new Vector();

      for (;;)
      {
         int index = s.indexOf(delimiter);

         if (index < 0)
         {
            if (! s.equals(""))
            {
               ret.add(s);
            }

            return ret;
         }

         String tmp = s.substring(0, index);

         if (! tmp.equals(""))
         {
            ret.add(tmp);
         }

         s = s.substring(index + delimiter.length(), s.length());
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param s DOCUMENT ME!
    * @param delimiter DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static Vector decomposeStringsIncludingEmptyStrings(String s,
      String delimiter)
   {
      Vector ret = new Vector();

      for (;;)
      {
         int index = s.indexOf(delimiter);

         if (index < 0)
         {
            ret.add(s);

            return ret;
         }

         String tmp = s.substring(0, index);
         ret.add(tmp);
         s = s.substring(index + delimiter.length(), s.length());
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param v DOCUMENT ME!
    * @param delimiter DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static String composeStrings(Vector v, String delimiter)
   {
      String ret = null;

      if (v != null)
      {
         for (int i = 0; i < v.size(); i++)
         {
            if (ret == null)
            {
               ret = new String((String) (v.elementAt(i)));
            }
            else
            {
               ret = ret + delimiter + (String) (v.elementAt(i));
            }
         }
      }

      if (ret == null)
      {
         ret = "";
      }

      return ret;
   }

   /**
    * DOCUMENT ME!
    */
   public static int INTERFACE_LINE_LENGTH = 40;

   /**
    * DOCUMENT ME!
    */
   private static int _fontsize = 14;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static int getFontsize()
   {
      return _fontsize;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static int getDistLineToText()
   {
      return (int) _fontsize / 4;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static int getDistTextToLine()
   {
      return (int) _fontsize / 4;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static int getDistTextToText()
   {
      return (int) _fontsize / 4;
   }

   /**
    * DOCUMENT ME!
    */
   private static Font _font = new Font("SansSerif", Font.PLAIN, getFontsize());

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static Font getFont()
   {
      return _font;
   }

   /**
    * DOCUMENT ME!
    */
   private static Font _fontItalic = new Font("SansSerif", Font.ITALIC,
         getFontsize());

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static Font getFontItalic()
   {
      return _fontItalic;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   static Map UxRenderingQualityHigh()
   {
      HashMap renderingHints = new HashMap();
      renderingHints.put(RenderingHints.KEY_ANTIALIASING,
         RenderingHints.VALUE_ANTIALIAS_ON);
      renderingHints.put(RenderingHints.KEY_ALPHA_INTERPOLATION,
         RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
      renderingHints.put(RenderingHints.KEY_RENDERING,
         RenderingHints.VALUE_RENDER_QUALITY);
      renderingHints.put(RenderingHints.KEY_FRACTIONALMETRICS,
         RenderingHints.VALUE_FRACTIONALMETRICS_ON);
      renderingHints.put(RenderingHints.KEY_INTERPOLATION,
         RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
      renderingHints.put(RenderingHints.KEY_DITHERING,
         RenderingHints.VALUE_DITHER_ENABLE);
      renderingHints.put(RenderingHints.KEY_COLOR_RENDERING,
         RenderingHints.VALUE_COLOR_RENDER_QUALITY);
      renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING,
         RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

      return renderingHints;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   static Map UxRenderingQualityLow()
   {
      HashMap renderingHints = new HashMap();

      //renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
      //renderingHints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
      //renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      //renderingHints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
      //renderingHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
      //renderingHints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
      //renderingHints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
      //renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
      return renderingHints;
   }

   /**
    * DOCUMENT ME!
    *
    * @param color DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   static Color UxInverseColor(Color color)
   {
      int r = 255 - color.getRed();
      int g = 255 - color.getGreen();
      int b = 255 - color.getBlue();

      return new Color(r, g, b);
   }

   /**
    * DOCUMENT ME!
    *
    * @param shape DOCUMENT ME!
    * @param p1 DOCUMENT ME!
    * @param p2 DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   static Point calculateIntersection(Shape shape, Point p1, Point p2)
   {
      //if (!(!(shape.contains(p1.x, p1.y)) & shape.contains(p2.x, p2.y))) return null;
      Point inter = new Point(p1);
      Point inter2 = new Point(p2);
      Point inter3 = new Point();

      for (int i = 0; i < 1000; i++)
      {
         inter3.x = (inter.x + inter2.x) / 2;
         inter3.y = (inter.y + inter2.y) / 2;

         if (shape.contains(inter3.x, inter3.y))
         {
            inter2.x = inter3.x;
            inter2.y = inter3.y;
         }
         else
         {
            inter.x = inter3.x;
            inter.y = inter3.y;
         }

         if (inter.distance(inter2) <= 1)
         {
            break;
         }
      }

      return inter;
   }

   /**
    * DOCUMENT ME!
    *
    * @param s1 DOCUMENT ME!
    * @param s2 DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   static Shape mergeShapes(Shape s1, Shape s2)
   {
      if (s1 == null)
      {
         return s2;
      }

      GeneralPath path1 = new GeneralPath(s1);
      path1.append(s2, false);

      return path1.createTransformedShape(AffineTransform.getTranslateInstance(
            0, 0));
   }

   /**
    * DOCUMENT ME!
    *
    * @param s DOCUMENT ME!
    * @param x DOCUMENT ME!
    * @param y DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   static Shape translateShape(Shape s, double x, double y)
   {
      GeneralPath path = new GeneralPath(s);

      return path.createTransformedShape(AffineTransform.getTranslateInstance(
            x, y));
   }

   /**
    * DOCUMENT ME!
    *
    * @param v DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   static double sign(double v)
   {
      if (v >= 0)
      {
         return 1d;
      }
      else
      {
         return -1d;
      }
   }
}

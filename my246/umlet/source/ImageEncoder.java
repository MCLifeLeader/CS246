// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/ImageEncoder.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control.io;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.awt.Image;
import java.awt.image.*;

import java.io.*;

// ImageEncoder - abstract class for writing out an image
//
// Copyright (C) 1996 by Jef Poskanzer <jef@acme.com>.  All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
// 1. Redistributions of source code must retain the above copyright
//    notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above copyright
//    notice, this list of conditions and the following disclaimer in the
//    documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
// ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
// OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
// HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
// LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
// OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE.
//
// Visit the ACME Labs Java page for up-to-date versions of this and other
// fine Java utilities: http://www.acme.com/java/
import java.util.*;

/// Abstract class for writing out an image.
/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
public abstract class ImageEncoder
   implements ImageConsumer
{
   /**
    * DOCUMENT ME!
    */
   protected OutputStream out;

   /**
    * DOCUMENT ME!
    */
   private ImageProducer producer;

   /**
    * DOCUMENT ME!
    */
   private int width = -1;

   /**
    * DOCUMENT ME!
    */
   private int height = -1;

   /**
    * DOCUMENT ME!
    */
   private int hintflags = 0;

   /**
    * DOCUMENT ME!
    */
   private boolean started = false;

   /**
    * DOCUMENT ME!
    */
   private boolean encoding;

   /**
    * DOCUMENT ME!
    */
   private IOException iox;

   /**
    * DOCUMENT ME!
    */
   private static final ColorModel rgbModel = ColorModel.getRGBdefault();

   /**
    * DOCUMENT ME!
    */
   private Hashtable props = null;

   /// Constructor.
   /**
    * Creates a new ImageEncoder object.
    *
    * @param img DOCUMENT ME!
    * @param out DOCUMENT ME!
    *
    * @throws IOException DOCUMENT ME!
    */
   public ImageEncoder(Image img, OutputStream out) throws IOException
   {
      this(img.getSource(), out);
   }

   /// Constructor.
   /**
    * Creates a new ImageEncoder object.
    *
    * @param producer DOCUMENT ME!
    * @param out DOCUMENT ME!
    *
    * @throws IOException DOCUMENT ME!
    */
   public ImageEncoder(ImageProducer producer, OutputStream out)
      throws IOException
   {
      this.producer = producer;
      this.out = out;
   }

   // Methods that subclasses implement.
   /**
    * DOCUMENT ME!
    *
    * @param w DOCUMENT ME!
    * @param h DOCUMENT ME!
    *
    * @throws IOException DOCUMENT ME!
    */
   abstract void encodeStart(int w, int h) throws IOException;

   /// Subclasses implement this to actually write out some bits.  They
   /**
    * DOCUMENT ME!
    *
    * @param x DOCUMENT ME!
    * @param y DOCUMENT ME!
    * @param w DOCUMENT ME!
    * @param h DOCUMENT ME!
    * @param rgbPixels DOCUMENT ME!
    * @param off DOCUMENT ME!
    * @param scansize DOCUMENT ME!
    *
    * @throws IOException DOCUMENT ME!
    */
   abstract void encodePixels(int x, int y, int w, int h, int[] rgbPixels,
      int off, int scansize) throws IOException;

   /// Subclasses implement this to finish an encoding.
   /**
    * DOCUMENT ME!
    *
    * @throws IOException DOCUMENT ME!
    */
   abstract void encodeDone() throws IOException;

   // Our own methods.
   /**
    * DOCUMENT ME!
    *
    * @throws IOException DOCUMENT ME!
    */
   public synchronized void encode() throws IOException
   {
      encoding = true;
      iox = null;
      producer.startProduction(this);

      while (encoding)

         try
         {
            wait();
         }
         catch (InterruptedException e)
         {
         }

      if (iox != null)
      {
         throw iox;
      }
   }

   /**
    * DOCUMENT ME!
    */
   private boolean accumulate = false;

   /**
    * DOCUMENT ME!
    */
   private int[] accumulator;

   /**
    * DOCUMENT ME!
    *
    * @param x DOCUMENT ME!
    * @param y DOCUMENT ME!
    * @param w DOCUMENT ME!
    * @param h DOCUMENT ME!
    * @param rgbPixels DOCUMENT ME!
    * @param off DOCUMENT ME!
    * @param scansize DOCUMENT ME!
    *
    * @throws IOException DOCUMENT ME!
    */
   private void encodePixelsWrapper(int x, int y, int w, int h,
      int[] rgbPixels, int off, int scansize) throws IOException
   {
      if (! started)
      {
         started = true;
         encodeStart(width, height);

         if ((hintflags & TOPDOWNLEFTRIGHT) == 0)
         {
            accumulate = true;
            accumulator = new int[width * height];
         }
      }

      if (accumulate)
      {
         for (int row = 0; row < h; ++row)
            System.arraycopy(rgbPixels, (row * scansize) + off, accumulator,
               ((y + row) * width) + x, w);
      }
      else
      {
         encodePixels(x, y, w, h, rgbPixels, off, scansize);
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @throws IOException DOCUMENT ME!
    */
   private void encodeFinish() throws IOException
   {
      if (accumulate)
      {
         encodePixels(0, 0, width, height, accumulator, 0, width);
         accumulator = null;
         accumulate = false;
      }
   }

   /**
    * DOCUMENT ME!
    */
   private synchronized void stop()
   {
      encoding = false;
      notifyAll();
   }

   // Methods from ImageConsumer.
   /**
    * DOCUMENT ME!
    *
    * @param width DOCUMENT ME!
    * @param height DOCUMENT ME!
    */
   public void setDimensions(int width, int height)
   {
      this.width = width;
      this.height = height;
   }

   /**
    * DOCUMENT ME!
    *
    * @param props DOCUMENT ME!
    */
   public void setProperties(Hashtable props)
   {
      this.props = props;
   }

   /**
    * DOCUMENT ME!
    *
    * @param model DOCUMENT ME!
    */
   public void setColorModel(ColorModel model)
   {
      // Ignore.
   }

   /**
    * DOCUMENT ME!
    *
    * @param hintflags DOCUMENT ME!
    */
   public void setHints(int hintflags)
   {
      this.hintflags = hintflags;
   }

   /**
    * DOCUMENT ME!
    *
    * @param x DOCUMENT ME!
    * @param y DOCUMENT ME!
    * @param w DOCUMENT ME!
    * @param h DOCUMENT ME!
    * @param model DOCUMENT ME!
    * @param pixels DOCUMENT ME!
    * @param off DOCUMENT ME!
    * @param scansize DOCUMENT ME!
    */
   public void setPixels(int x, int y, int w, int h, ColorModel model,
      byte[] pixels, int off, int scansize)
   {
      int[] rgbPixels = new int[w];

      for (int row = 0; row < h; ++row)
      {
         int rowOff = off + (row * scansize);

         for (int col = 0; col < w; ++col)
            rgbPixels[col] = model.getRGB(pixels[rowOff + col] & 0xff);

         try
         {
            encodePixelsWrapper(x, y + row, w, 1, rgbPixels, 0, w);
         }
         catch (IOException e)
         {
            iox = e;
            stop();

            return;
         }
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param x DOCUMENT ME!
    * @param y DOCUMENT ME!
    * @param w DOCUMENT ME!
    * @param h DOCUMENT ME!
    * @param model DOCUMENT ME!
    * @param pixels DOCUMENT ME!
    * @param off DOCUMENT ME!
    * @param scansize DOCUMENT ME!
    */
   public void setPixels(int x, int y, int w, int h, ColorModel model,
      int[] pixels, int off, int scansize)
   {
      if (model == rgbModel)
      {
         try
         {
            encodePixelsWrapper(x, y, w, h, pixels, off, scansize);
         }
         catch (IOException e)
         {
            iox = e;
            stop();

            return;
         }
      }
      else
      {
         int[] rgbPixels = new int[w];

         for (int row = 0; row < h; ++row)
         {
            int rowOff = off + (row * scansize);

            for (int col = 0; col < w; ++col)
               rgbPixels[col] = model.getRGB(pixels[rowOff + col]);

            try
            {
               encodePixelsWrapper(x, y + row, w, 1, rgbPixels, 0, w);
            }
            catch (IOException e)
            {
               iox = e;
               stop();

               return;
            }
         }
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param status DOCUMENT ME!
    */
   public void imageComplete(int status)
   {
      producer.removeConsumer(this);

      if (status == ImageConsumer.IMAGEABORTED)
      {
         iox = new IOException("image aborted");
      }
      else
      {
         try
         {
            encodeFinish();
            encodeDone();
         }
         catch (IOException e)
         {
            iox = e;
         }
      }

      stop();
   }
}

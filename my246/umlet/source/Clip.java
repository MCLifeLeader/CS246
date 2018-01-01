// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Clip.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;

import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

/** Copies and Pastes images to the system clipboard. Requires Java 2, v1.4. */
public class Clip
   implements Transferable
{
   /**
    * DOCUMENT ME!
    */
   Clipboard clipboard;

   /**
    * DOCUMENT ME!
    */
   private static Clip _instance = new Clip();

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static Clip getInstance()
   {
      //if (_instance == null) _instance = new Clip();
      return _instance;
   }

   /**
    * Creates a new Clip object.
    */
   private Clip()
   {
      String s = System.getProperty("java.version");

      if (! s.startsWith("1.4"))
      {
         //System.out.println ("This plugin will only run using Java 2, v1.4");
         return;
      }

      Toolkit toolkit = Toolkit.getDefaultToolkit();
      clipboard = toolkit.getSystemClipboard();

      //Panel panel = new Panel();
   }

   /**
    * DOCUMENT ME!
    */
   public void copy()
   {
      //try {
      //	if (e.getSource() == copyButton)
      if (clipboard != null)
      {
         clipboard.setContents(this, null);
      }

      /*   else {
         Transferable transferable = clipboard.getContents(null);
         if (transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            Image img = (Image)transferable.getTransferData(DataFlavor.imageFlavor);
            new ImagePlus("Pasted Image", img).show();
         } else
            IJ.showMessage("System_Clipboard", "No image data in the system clipboard.");
         }
         } catch (Throwable t) {}*/
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public DataFlavor[] getTransferDataFlavors()
   {
      return new DataFlavor[]{ DataFlavor.imageFlavor };
   }

   /**
    * DOCUMENT ME!
    *
    * @param flavor DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public boolean isDataFlavorSupported(DataFlavor flavor)
   {
      return DataFlavor.imageFlavor.equals(flavor);
   }

   /**
    * DOCUMENT ME!
    *
    * @param flavor DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    *
    * @throws UnsupportedFlavorException DOCUMENT ME!
    */
   public Object getTransferData(DataFlavor flavor)
      throws UnsupportedFlavorException
   {
      if (! isDataFlavorSupported(flavor))
      {
         throw new UnsupportedFlavorException(flavor);
      }

      /*int h=Frame.getInstance().getPanel().getHeight();
         int w=Frame.getInstance().getPanel().getWidth();
         Image img = Frame.getInstance().getPanel().createImage(w, h);
         Graphics g = img.getGraphics();
         g.drawImage(ip.createImage(), 0, 0, null);
         g.dispose();*/

      //Frame.IS_CLIPPING=true;
      Dimension size = Umlet.getInstance().getPanel().getSize();
      BufferedImage im = new BufferedImage(size.width, size.height,
            BufferedImage.TYPE_INT_RGB);
      Graphics g = im.createGraphics();
      g.setColor(Color.white);
      g.fillRect(0, 0, size.width, size.height);
      Umlet.getInstance().getPanel().paint(g);

      //Frame.IS_CLIPPING=false;
      return im;
   }

   /*public Insets getInsets() {
      Insets i= super.getInsets();
      return new Insets(i.top+5, i.left+40, i.bottom+5, i.right+40);
      }*/
}

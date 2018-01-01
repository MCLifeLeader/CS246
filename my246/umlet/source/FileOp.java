// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/FileOp.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control.io;

import com.sun.image.codec.jpeg.*;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;

import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
public class FileOp
{
   /**
    * DOCUMENT ME!
    */
   public JFileChooser jfcOpenMlt;

   /**
    * DOCUMENT ME!
    */
   public JFileChooser jfcSaveAsMlt;

   /**
    * DOCUMENT ME!
    */
   public JFileChooser jfcSaveAsSvg;

   /**
    * DOCUMENT ME!
    */
   public JFileChooser jfcSaveAsPdf;

   /**
    * DOCUMENT ME!
    */
   public JFileChooser jfcSaveAsJpg;

   /**
    * DOCUMENT ME!
    */
   private String saveFileName = null;

   /**
    * DOCUMENT ME!
    */
   private static FileOp _instance = new FileOp();

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static FileOp getInstance()
   {
      //if (_instance == null) { _instance = new FileOp(); }
      return _instance;
   }

   /**
    * Creates a new FileOp object.
    */
   private FileOp()
   {
      //TODO: May need to change this also
      jfcOpenMlt = new JFileChooser();
      jfcSaveAsMlt = new JFileChooser();
      jfcSaveAsSvg = new JFileChooser();
      jfcSaveAsPdf = new JFileChooser();
      jfcSaveAsJpg = new JFileChooser();

      FileFilter filter = new FileFilter()
         {
            public boolean accept(File f)
            {
               return (f.getName().endsWith(".uxf") || f.isDirectory());
            }

            public String getDescription()
            {
               return "UMLet diagram format (*.uxf)";
            }
         };

      jfcOpenMlt.setFileFilter(filter);
      jfcSaveAsMlt.setFileFilter(filter);
      jfcSaveAsMlt.setSelectedFile(new File(".uxf"));

      filter = new FileFilter()
            {
               public boolean accept(File f)
               {
                  return (f.getName().endsWith(".jpg")
                  || f.getName().endsWith(".jpeg") || f.isDirectory());
               }

               public String getDescription()
               {
                  return "JPG format (*.jpg, *.jpeg)";
               }
            };
      jfcSaveAsJpg.setFileFilter(filter);
      jfcSaveAsJpg.setSelectedFile(new File(".jpg"));

      filter = new FileFilter()
            {
               public boolean accept(File f)
               {
                  return (f.getName().endsWith(".svg") || f.isDirectory());
               }

               public String getDescription()
               {
                  return "SVG format (*.svg)";
               }
            };
      jfcSaveAsSvg.setFileFilter(filter);
      jfcSaveAsSvg.setSelectedFile(new File(".svg"));

      filter = new FileFilter()
            {
               public boolean accept(File f)
               {
                  return (f.getName().endsWith(".pdf") || f.isDirectory());
               }

               public String getDescription()
               {
                  return "PDF format (*.pdf)";
               }
            };
      jfcSaveAsPdf.setFileFilter(filter);
      jfcSaveAsPdf.setSelectedFile(new File(".pdf"));
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String getJpgFilename()
   {
      int returnVal = jfcSaveAsJpg.showSaveDialog(Umlet.getInstance());

      if (returnVal != JFileChooser.APPROVE_OPTION)
      {
         return null;
      }

      return jfcSaveAsJpg.getSelectedFile().getAbsolutePath();
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String getSvgFilename()
   {
      int returnVal = jfcSaveAsSvg.showSaveDialog(Umlet.getInstance());

      if (returnVal != JFileChooser.APPROVE_OPTION)
      {
         return null;
      }

      return jfcSaveAsSvg.getSelectedFile().getAbsolutePath();
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String getPdfFilename()
   {
      int returnVal = jfcSaveAsPdf.showSaveDialog(Umlet.getInstance());

      if (returnVal != JFileChooser.APPROVE_OPTION)
      {
         return null;
      }

      return jfcSaveAsPdf.getSelectedFile().getAbsolutePath();
   }

   /**
    * DOCUMENT ME!
    *
    * @param ask_again DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String getMltSaveFilename(boolean ask_again)
   {
      if (ask_again)
      {
         int returnVal = jfcSaveAsMlt.showSaveDialog(Umlet.getInstance());

         if (returnVal != JFileChooser.APPROVE_OPTION)
         {
            return null;
         }

         saveFileName = jfcSaveAsMlt.getSelectedFile().getAbsolutePath();
         setSaveMenuItem(true);
      }

      return saveFileName;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String getMltOpenFilename()
   {
      String fileName = null;
      int returnVal = jfcOpenMlt.showOpenDialog(Umlet.getInstance());

      if (returnVal == JFileChooser.APPROVE_OPTION)
      {
         fileName = jfcOpenMlt.getSelectedFile().getAbsolutePath();
      }

      if (fileName != null)
      {
         saveFileName = fileName;
      }

      return fileName;
   }

   /**
    * DOCUMENT ME!
    *
    * @param b DOCUMENT ME!
    */
   public void setSaveMenuItem(boolean b)
   {
      if (Umlet.getInstance()._saveMenuItem != null)
      {
         Umlet.getInstance()._saveMenuItem.setEnabled(b);
      }
   }
}

// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Umlet.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.sun.image.codec.jpeg.*;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.Actor;
import com.umlet.element.base.Class;
import com.umlet.element.base.Note;
import com.umlet.element.base.Package;
import com.umlet.element.base.UseCase;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import org.apache.xerces.parsers.*;

import org.jdom.*;

import org.jdom.output.*;

import org.xml.sax.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;

import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

// 3.2.: 8h, neu starten
// 4.2.: 6h, Klassen
// 5.2.:     Interfaces
public class Umlet
   extends JFrame
{
   /**
    * DOCUMENT ME!
    */
   JFileChooser _chooser = new JFileChooser();

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String createStringToBeSaved()
   {
      Component[] components = this.getPanel().getComponents();

      //Vector tmp=new Vector();
      Document doc = new Document(new Element("umlet_diagram"));
      Element root = doc.getRootElement();

      for (int i = 0; i < components.length; i++)
      {
         Entity e = (Entity) components[i];

         //String s=e.getClassCode()+e.getStateAndHiddenState();
         java.lang.Class c = e.getClass();
         String sElType = c.getName();
         int[] coor = e.getCoordinates();
         String sElPanelAttributes = e.getPanelAttributes();
         String sElAdditionalAttributes = e.getAdditionalAttributes();

         Element el = new Element("element");
         root.addContent(el);

         Element elType = new Element("type");
         elType.setText(sElType);
         el.addContent(elType);

         Element elCoor = new Element("coordinates");
         el.addContent(elCoor);

         Element elX = new Element("x");
         elX.setText("" + coor[0]);
         elCoor.addContent(elX);

         Element elY = new Element("y");
         elY.setText("" + coor[1]);
         elCoor.addContent(elY);

         Element elW = new Element("w");
         elW.setText("" + coor[2]);
         elCoor.addContent(elW);

         Element elH = new Element("h");
         elH.setText("" + coor[3]);
         elCoor.addContent(elH);

         Element elPA = new Element("panel_attributes");
         elPA.setText(sElPanelAttributes);
         el.addContent(elPA);

         Element elAA = new Element("additional_attributes");
         elAA.setText(sElAdditionalAttributes);
         el.addContent(elAA);

         //tmp.add(s);
      }

      //String ret=Constants.composeStrings(tmp, Constants.DELIMITER_ENTITIES);
      XMLOutputter outputter = new XMLOutputter();
      String ret = outputter.outputString(doc);

      return ret;
   }

   /**
    * DOCUMENT ME!
    */
   public static boolean IS_CLIPPING = false;

   /**
    * DOCUMENT ME!
    */
   private int MAIN_UNIT = 10;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getMainUnit()
   {
      return MAIN_UNIT;
   }

   /**
    * DOCUMENT ME!
    */
   private String _fileName = null;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   private String getFileName()
   {
      return _fileName;
   }

   /**
    * DOCUMENT ME!
    */
   public JMenuItem _saveMenuItem;

   /**
    * DOCUMENT ME!
    *
    * @param s DOCUMENT ME!
    */
   private void setFileName(String s)
   {
      _fileName = s;

      if (_saveMenuItem != null)
      {
         if (_fileName != null)
         {
            _saveMenuItem.setEnabled(true);
         }
         else
         {
            _saveMenuItem.setEnabled(false);
         }
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param fileName DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   private static String getStringFromFile(String fileName)
   {
      try
      {
         StringBuffer sb = new StringBuffer();
         BufferedReader br = new BufferedReader(new FileReader(fileName));
         String line;

         while (((line = br.readLine()) != "") && (line != null))
            sb = new StringBuffer(sb + line + "\n");

         String ret = new String(sb);

         if (ret.length() > 0)
         {
            if (ret.charAt(ret.length() - 1) == '\n')
            {
               ret = ret.substring(0, ret.length() - 1);
            }
         }

         return ret;
      }
      catch (Exception e)
      {
         return null;
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param streamName DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   private static String getStringFromStream(String streamName)
   {
      try
      {
         StringBuffer sb = new StringBuffer();
         BufferedReader br = new BufferedReader(new InputStreamReader(
                  getInstance().getClass().getResourceAsStream(streamName)));
         String line;

         while (((line = br.readLine()) != "") && (line != null))
            sb = new StringBuffer(sb + line + "\n");

         String ret = new String(sb);

         if (ret.length() > 0)
         {
            if (ret.charAt(ret.length() - 1) == '\n')
            {
               ret = ret.substring(0, ret.length() - 1);
            }
         }

         return ret;
      }
      catch (Exception e)
      {
         return null;
      }
   }

   /**
    * DOCUMENT ME!
    */
   public void doOpen()
   {
      String fileName;
      fileName = FileOp.getInstance().getMltOpenFilename();

      if (fileName == null)
      {
         return;
      }

      Vector tmp = Selector.getInstance().getAllEntitiesOnPanel();
      Controller.getInstance().executeCommand(new RemoveElement(tmp));

      //String s=getStringFromFile(fileName);

      //setEntitiesOnPanel(s, Umlet.getInstance().getPanel());
      openFileToPanel(fileName, getInstance().getPanel());

      FileOp.getInstance().setSaveMenuItem(true);
   }

   /**
    * DOCUMENT ME!
    *
    * @param filename DOCUMENT ME!
    * @param p DOCUMENT ME!
    */
   private void openFileToPanel(String filename, JPanel p)
   {
      SAXParser parser = new SAXParser();
      parser.setContentHandler(new XMLContentHandler(p));

      try
      {
         parser.parse(new InputSource(new FileInputStream(filename)));
      }
      catch (Exception e)
      {
         StackTraceElement[] trace = e.getStackTrace();
         String out = "";

         for (int i = 0; i < trace.length; i++)
         {
            out += (trace[i].toString() + "\n");
         }

         Umlet.getInstance().getPropertyPanel().setText("EX=" + out);
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param s DOCUMENT ME!
    * @param p DOCUMENT ME!
    */
   private void setEntitiesOnPanel(String s, JPanel p)
   {
      Vector v = Constants.decomposeStrings(s, Constants.DELIMITER_ENTITIES);

      for (int i = 0; i < v.size(); i++)
      {
         String tmp = (String) v.elementAt(i);
         String s1 = tmp.substring(0, 4);
         String s2 = tmp.substring(4, tmp.length());

         if (s1.equals("CLAS"))
         {
            p.add(new Class(s2));
         }
         else if (s1.equals("INTE"))
         {
            p.add(new Interface(s2));
         }
         else if (s1.equals("PACK"))
         {
            p.add(new Package(s2));
         }
         else if (s1.equals("USEC"))
         {
            p.add(new UseCase(s2));
         }
         else if (s1.equals("NOTE"))
         {
            p.add(new Note(s2));
         }
         else if (s1.equals("ACTO"))
         {
            p.add(new Actor(s2));
         }
         else if (s1.equals("RELA"))
         {
            Relation r = new Relation(s2);
            p.add(r);
         }
      }

      p.repaint();
   }

   /**
    * DOCUMENT ME!
    *
    * @param s DOCUMENT ME!
    */
   public void appendToTitle(String s)
   {
      if (s == null)
      {
         this.getInstance().setTitle("UMLet");

         return;
      }

      this.getInstance().setTitle("UMLet - " + s);
   }

   /**
    * DOCUMENT ME!
    */
   public void doSaveAs()
   {
      String fileName = FileOp.getInstance().getMltSaveFilename(true);

      if (fileName == null)
      {
         return;
      }

      save(fileName);
   }

   /**
    * DOCUMENT ME!
    */
   public void doSave()
   {
      String fileName = FileOp.getInstance().getMltSaveFilename(false);

      if (fileName == null)
      {
         doSaveAs();
      }
      else
      {
         save(fileName);
      }
   }

   /**
    * DOCUMENT ME!
    */
   public void doSaveAsSvg()
   {
      String fileName = FileOp.getInstance().getSvgFilename();

      if (fileName == null)
      {
         return;
      }

      GenSvg.getInstance().createAndOutputSvgToFile(fileName);
   }

   /**
    * DOCUMENT ME!
    */
   public void doSaveAsPdf()
   {
      String fileName = FileOp.getInstance().getPdfFilename();

      if (fileName == null)
      {
         return;
      }

      GenPdf.getInstance().createAndOutputPdfToFile(fileName);
   }

   /**
    * DOCUMENT ME!
    */
   public void doSaveAsJPG()
   {
      String fileName = FileOp.getInstance().getJpgFilename();

      if (fileName == null)
      {
         return;
      }

      GenPdf.getInstance().createAndOutputJpgToFile(fileName);
   }

   /**
    * DOCUMENT ME!
    *
    * @param fileName DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   private String save(String fileName)
   {
      String tmp = this.createStringToBeSaved();

      try
      {
         PrintWriter out = new PrintWriter(new FileWriter(fileName));
         out.print(tmp);
         out.close();
      }
      catch (java.io.IOException e)
      {
         return null;
      }

      return fileName;
   }

   /**
    * DOCUMENT ME!
    */
   private Rectangle _oldBounds;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Rectangle getOldBounds()
   {
      return _oldBounds;
   }

   /**
    * DOCUMENT ME!
    *
    * @param d DOCUMENT ME!
    */
   public void setOldBounds(Rectangle d)
   {
      _oldBounds = d;
   }

   /**
    * DOCUMENT ME!
    */
   private int _lrSeparatorLocation;

   /**
    * DOCUMENT ME!
    */
   private int _tbSeparatorLocation;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getLrSeparatorLocation()
   {
      return _lrSeparatorLocation;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getTbSeparatorLocation()
   {
      return _tbSeparatorLocation;
   }

   /**
    * DOCUMENT ME!
    *
    * @param i DOCUMENT ME!
    */
   public void setLrSeparatorLocation(int i)
   {
      _lrSeparatorLocation = i;
      getJspMain().setDividerLocation(_lrSeparatorLocation);
   }

   /**
    * DOCUMENT ME!
    *
    * @param i DOCUMENT ME!
    */
   public void setTbSeparatorLocation(int i)
   {
      _tbSeparatorLocation = i;
      getJspRight().setDividerLocation(_tbSeparatorLocation);
   }

   /**
    * DOCUMENT ME!
    */
   private JTextPane _propertyPanel;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public JTextPane getPropertyPanel()
   {
      if (_propertyPanel == null)
      {
         _propertyPanel = new JTextPane();
      }

      return _propertyPanel;
   }

   /**
    * DOCUMENT ME!
    */
   private JScrollPane _scrollPanel;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public JScrollPane getScrollPanel()
   {
      if (_scrollPanel == null)
      {
         _scrollPanel = new JScrollPane(getPropertyPanel());
      }

      return _scrollPanel;
   }

   /**
    * DOCUMENT ME!
    */
   private static JTextPane _infoText;

   /**
    * DOCUMENT ME!
    *
    * @param s DOCUMENT ME!
    */
   public static void outputInfoText(String s)
   {
      _infoText.setText(s);
   }

   /**
    * DOCUMENT ME!
    */
   private Entity _editedEntity;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Entity getEditedEntity()
   {
      return _editedEntity;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String getPropertyString()
   {
      return _propertyPanel.getText();
   }

   /**
    * DOCUMENT ME!
    */
   private JPanel _panel;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public JPanel getPanel()
   {
      if (_panel == null)
      {
         _panel = new JPanel();
      }

      return _panel;
   }

   /**
    * DOCUMENT ME!
    */
   private JPanel _palettePanel;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public JPanel getPalettePanel()
   {
      if (_palettePanel == null)
      {
         _palettePanel = new JPanel();
      }

      return _palettePanel;
   }

   /**
    * DOCUMENT ME!
    */
   private static Umlet _instance = new Umlet();

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static Umlet getInstance()
   {
      //if (_instance == null) _instance = new Umlet();
      return _instance;
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    */
   public void setPropertyPanelToEntity(Entity e)
   {
      _editedEntity = e;

      if (e != null)
      {
         _propertyPanel.setText(e.getPanelAttributes());
      }
      else
      {
         _propertyPanel.setText("Welcome to UMLet!\n\n"
            + "Double-click on UML elements to add them to the diagram.\n"
            + "Edit element properties by modifying the text in this panel.\n"
            + "Edit the file 'palette.uxf' to store your own element palette.\n"
            + "Press Del or Backspace to remove elements from the diagram.\n"
            + "Hold down Ctrl key to select multiple elements.\n"
            + "Press C to copy the UML diagram to the system clipboard (Java 1.4+ required).");
      }

      Umlet.getInstance().getJspMain().requestFocus();
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Vector getAllEntities()
   {
      Vector v = new Vector();

      for (int i = 0; i < _panel.getComponentCount(); i++)
      {
         v.add(_panel.getComponent(i));
      }

      return v;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getCenterX()
   {
      return _panel.getWidth() / 2;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getCenterY()
   {
      return _panel.getHeight() / 2;
   }

   /**
    * DOCUMENT ME!
    */
   private JSplitPane _jspMain;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public JSplitPane getJspMain()
   {
      if (_jspMain == null)
      {
         _jspMain = new JSplitPane();
         _jspMain.setDividerSize(3);
      }

      return _jspMain;
   }

   /**
    * DOCUMENT ME!
    */
   private JSplitPane _jspRight;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public JSplitPane getJspRight()
   {
      if (_jspRight == null)
      {
         _jspRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
         _jspRight.setDividerSize(3);
      }

      return _jspRight;
   }

   /**
    * DOCUMENT ME!
    *
    * @param args DOCUMENT ME!
    */
   public static void main(String[] args)
   {
      getInstance()
         .addWindowListener((WindowListener) UniversalListener.getInstance());
      getInstance().setBounds(10, 10, 980, 780);
      getInstance().getContentPane().setLayout(new BorderLayout());

      // Add split panel
      getInstance().getContentPane()
         .add(getInstance().getJspMain(), BorderLayout.CENTER);

      // Add main panel
      getInstance().getPanel().setBounds(0, 0, 300, 300);
      getInstance().getPanel().setLayout(null);
      //getInstance().getPanel().addKeyListener(UniversalListener.getInstance());
      getInstance().getPanel().setBackground(Color.white);
      getInstance().getPanel().addMouseListener(UniversalListener.getInstance());
      getInstance().getPanel()
         .addMouseMotionListener(UniversalListener.getInstance());
      getInstance().getJspMain().add(getInstance().getPanel(), JSplitPane.LEFT);

      // Add palette panel
      getInstance().getJspRight()
         .add(getInstance().getPalettePanel(), JSplitPane.TOP);
      getInstance().getPalettePanel().setBackground(Color.white);
      getInstance().getPalettePanel().setLayout(null);

      // Add property panel
      getInstance().getPropertyPanel()
         .addKeyListener(UniversalListener.getInstance());
      getInstance().getJspRight()
         .add(getInstance().getScrollPanel(), JSplitPane.BOTTOM);

      getInstance().getJspMain()
         .add(getInstance().getJspRight(), JSplitPane.RIGHT);

      KeyStroke bUndo = KeyStroke.getKeyStroke('z');
      AbstractAction aUndo = new AbstractAction()
         {
            public void actionPerformed(ActionEvent e)
            {
               Selector.getInstance().deselectAll();
               Controller.getInstance().undo();
               System.out.println("undo");
            }
         };

      KeyStroke bRedo = KeyStroke.getKeyStroke('y');
      AbstractAction aRedo = new AbstractAction()
         {
            public void actionPerformed(ActionEvent e)
            {
               Controller.getInstance().redo();
            }
         };

      KeyStroke bClip = KeyStroke.getKeyStroke('c');
      AbstractAction aClip = new AbstractAction()
         {
            public void actionPerformed(ActionEvent e)
            {
               Clip.getInstance().copy();
            }
         };

      KeyStroke bBack = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0, true);
      AbstractAction aBack = new AbstractAction()
         {
            public void actionPerformed(ActionEvent e)
            {
               Vector v = Selector.getInstance().getSelectedEntitiesOnPanel();

               if (v.size() > 0)
               {
                  Controller.getInstance().executeCommand(new RemoveElement(v));
               }
            }
         };

      KeyStroke bDele = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, true);
      AbstractAction aDele = new AbstractAction()
         {
            public void actionPerformed(ActionEvent e)
            {
               Vector v = Selector.getInstance().getSelectedEntitiesOnPanel();

               if (v.size() > 0)
               {
                  Controller.getInstance().executeCommand(new RemoveElement(v));
               }
            }
         };

      InputMap inputMap = Umlet.getInstance().getJspMain()
                               .getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
      ActionMap actionMap = Umlet.getInstance().getJspMain().getActionMap();
      inputMap.put(bUndo, "undo");
      actionMap.put("undo", aUndo);
      inputMap.put(bRedo, "redo");
      actionMap.put("redo", aRedo);
      inputMap.put(bBack, "back");
      actionMap.put("back", aBack);
      inputMap.put(bDele, "dele");
      actionMap.put("dele", aDele);
      inputMap.put(bClip, "clip");
      actionMap.put("clip", aClip);

      // Add menu
      JMenuBar m = new JMenuBar();
      JMenu iFile = new JMenu("File");
      JMenu iEdit = new JMenu("Edit");
      m.add(iFile);
      m.add(iEdit);

      JMenuItem iNew = new JMenuItem("New");
      JMenuItem iOpen = new JMenuItem("Open");
      JMenuItem iSave = new JMenuItem("Save");
      getInstance()._saveMenuItem = iSave;
      getInstance().setFileName(null);

      JMenuItem iSaveAs = new JMenuItem("Save as..");
      JMenuItem iSaveAsGif = new JMenuItem("Save as JPG..");
      JMenuItem iSaveAsSvg = new JMenuItem("Save as SVG..");
      JMenuItem iSaveAsPdf = new JMenuItem("Save as PDF..");
      JMenuItem iDelete = new JMenuItem("Delete");
      JMenuItem iUndo = new JMenuItem("Undo");
      JMenuItem iRedo = new JMenuItem("Redo");
      iFile.add(iNew);
      iFile.add(iOpen);
      iFile.add(iSave);
      iFile.add(iSaveAs);
      iFile.add(iSaveAsGif);
      iFile.add(iSaveAsSvg);
      iFile.add(iSaveAsPdf);
      iEdit.add(iUndo);
      iEdit.add(iRedo);
      iEdit.add(iDelete);

      iNew.addActionListener(UniversalListener.getInstance());
      iOpen.addActionListener(UniversalListener.getInstance());
      iSave.addActionListener(UniversalListener.getInstance());
      iSaveAs.addActionListener(UniversalListener.getInstance());
      iSaveAsGif.addActionListener(UniversalListener.getInstance());
      iSaveAsSvg.addActionListener(UniversalListener.getInstance());

      iSaveAsPdf.addActionListener(UniversalListener.getInstance());

      iUndo.addActionListener(UniversalListener.getInstance());
      iRedo.addActionListener(UniversalListener.getInstance());
      iDelete.addActionListener(UniversalListener.getInstance());
      m.setLayout(new FlowLayout(0, 0, 0));
      getInstance().getContentPane().add(m, BorderLayout.NORTH);

      getInstance().setTitle("UMLet 1.8");
      getInstance().addComponentListener(UniversalListener.getInstance());
      //getInstance().addKeyListener(UniversalListener.getInstance());
      getInstance().setLrSeparatorLocation(520);
      getInstance().setTbSeparatorLocation(550);
      getInstance().setOldBounds(getInstance().getBounds());
      getInstance().setPropertyPanelToEntity(null);

      /*String palette=getInstance().getStringFromFile("."+File.separator+"palette.uxf");
         if (palette!=null) {
           getInstance().setEntitiesOnPanel(palette, getInstance().getPalettePanel());
         } else {
           palette = getInstance().getStringFromStream("palette.uxf");
           if (palette!=null) {
             getInstance().setEntitiesOnPanel(palette, getInstance().getPalettePanel());
           }
         }*/
      Umlet.getInstance()
           .openFileToPanel("./palette.uxf",
         Umlet.getInstance().getPalettePanel());

      getInstance().setVisible(true);

      //GenPdf.getInstance().outputPdf();
      //GenPdf.getInstance().outputJpeg();
   }
}

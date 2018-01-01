
// package default

import com.umlet.element.base.*;
import com.umlet.element.custom.*;
import com.umlet.element.base.detail.*;
import com.umlet.control.*;
import com.umlet.control.io.*;
import java.awt.geom.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import javax.xml.parsers.*;

import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;

import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Generates a .jpg file from a .uxf file containing a UML diagram.
 * Compile with:
 *   javac -extdirs ../lib -cp ../umlet.jar -d .. GenJpg.java
 */
public class GenJpg
   extends DefaultHandler
{
   private static String CUSTOM_ELEMENTS_PATH = "custom_elements/"; //LME

   private Collection<Entity> mEntities;
   private String[] mFilenames;
   private String mUxfFilename;
   private String mJpgFilename;
  	private Entity e;
  	private String elementtext;
  	private int x;
  	private int y;
  	private int w;
  	private int h;

  	private int customTypesLoaded=0;
  	private int customTypesLoadedWError=0;

   public static void main(String[] args)
   {
      new GenJpg(args).run();
   }

   public GenJpg(String[] inFilenames)
   {
      mEntities = new ArrayList<Entity>();
      mFilenames = inFilenames;
   }

   public void run()
   {
      for (String filename : mFilenames)
      {
         mUxfFilename = filename;
         int dotIndex = filename.lastIndexOf(".");
         mJpgFilename = filename.substring(0, dotIndex) + ".jpg";
         File uxfFile = new File(mUxfFilename);
         File jgpFile = new File(mJpgFilename);
         if (! jgpFile.exists() ||
             uxfFile.lastModified() > jgpFile.lastModified())
         {
            mEntities.clear();
            System.out.println("Generating JPG from " + mUxfFilename);
            readEntitiesFromUxfFile();
            createAndWriteJpgFile();
         }
      }
   }

   private void readEntitiesFromUxfFile()
   {
      try
      {
        //[UB]: Use the Factory instead of directly instantiating
        //      SAXParser because its constructor became protected
         SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
         InputSource inputsrc =
            new InputSource(new FileInputStream(mUxfFilename));
         parser.parse(inputsrc, this);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   private Dimension calculateCanvas()
   {
    	int maxx = 50;
    	int minx = 0;
    	int maxy = 50;
    	int miny = 0;

      for (Entity e : mEntities)
      {
    		maxx = Math.max(maxx, e.getX() + e.getWidth() + 20);
    		maxy = Math.max(maxy, e.getY() + e.getHeight() + 20);
    		minx = Math.min(minx, e.getX() - 20);
    		miny = Math.min(miny, e.getY() - 20);
    	}
    	return new Dimension(maxx - minx, maxy - miny);
   }

   private Dimension calculateTranslate()
   {
    	int maxx = 50;
    	int minx = 0;
    	int maxy = 50;
    	int miny = 0;

      for (Entity e : mEntities)
      {
    		maxx = Math.max(maxx, e.getX() + e.getWidth() + 20);
    		maxy = Math.max(maxy, e.getY() + e.getHeight() + 20);
    		minx = Math.min(minx, e.getX() - 20);
    		miny = Math.min(miny, e.getY() - 20);
    	}
    	return new Dimension(minx, miny);
    }

   public void createAndWriteJpgFile()
   {
      try
      {
         // Get a DOMImplementation
         DOMImplementation domImpl =
            GenericDOMImplementation.getDOMImplementation();

         // Create an instance of org.w3c.dom.Document
         Document document = domImpl.createDocument(null, "svg", null);

        // Create an instance of the SVG Generator

        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        Dimension trans = calculateTranslate();

        svgGenerator.translate(-(double) trans.getWidth(),
                               -(double) trans.getHeight());

        svgGenerator.setSVGCanvasSize(calculateCanvas());

        // Render entities into the SVG Graphics2D implementation

        render(svgGenerator);

        // Finally, stream out SVG to the standard output using UTF-8
        // character to byte encoding

        boolean useCSS = true; // we want to use CSS style attribute

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Writer out = new OutputStreamWriter(baos);
        svgGenerator.stream(out, useCSS);

        ByteArrayInputStream bais =
           new ByteArrayInputStream(baos.toByteArray());

        // create a JPEG transcoder
        JPEGTranscoder t = new JPEGTranscoder();

        // set the transcoding hints
        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(1.0));

        // create the transcoder input
        TranscoderInput input = new TranscoderInput(bais);

        // create the transcoder output
        OutputStream ostream = new FileOutputStream(mJpgFilename);

        TranscoderOutput output = new TranscoderOutput(ostream);

        // save
        t.transcode(input, output);

        // flush and close the stream
        ostream.flush();
        ostream.close();
      }
      catch (Exception e)
      {
      	System.out.println("Error: Exception in outputJpeg: " + e);
      }
   }

   private void render(Graphics2D g2d)
   {
      for (Entity e : mEntities)
      {
         g2d.translate(e.getX(), e.getY());
         e.paint(g2d);
         g2d.translate(-e.getX(), -e.getY());
      }
   }
	
	public void startElement(String uri, String localName,
                            String qName, Attributes attributes)
   {
		elementtext="";
	}
	
	public void endElement(String uri, String localName, String qName)
   {
      String elementname = qName;
		
		if (elementname.equals("type"))
      {
			try
         {
				//LME3 use own class loader
				String elementPath = elementtext.replace('.', File.separatorChar);
				String homePath = Umlet.getInstance().getHomePath();
				File classFile = new File(homePath + CUSTOM_ELEMENTS_PATH + elementPath + ".class");
				File javaFile = new File(homePath + CUSTOM_ELEMENTS_PATH + elementPath + ".java");
				if (classFile.exists() || javaFile.exists())
            {
					String[] fileNameStrArr = {
						homePath + CUSTOM_ELEMENTS_PATH + elementPath + ".java",
						elementtext
					};
               // compile and load the modified class, silent error reporting
					e = CustomElementLoader.getInstance().doLoadClass(fileNameStrArr, false, true);
					customTypesLoaded++; //LME4 count loaded classes
               // count errors while loading classes
					customTypesLoadedWError = CustomElementLoader.getInstance().getErrorCounter();
				}
            else
            {
               // load jar-classes conventionally
               e = (Entity) java.lang.Class.forName(elementtext).newInstance();
				}
			}
         catch (Exception e)
         { 
            e.printStackTrace();
			}
	
         mEntities.add(e);
		}
		else if (elementname.equals("x"))
      {
			x = new Integer(elementtext);
		}
		else if (elementname.equals("y"))
      {
			y = new Integer(elementtext);
		}
		else if (elementname.equals("w"))
      {
			w = new Integer(elementtext);
		}
		else if (elementname.equals("h"))
      {
			h = new Integer(elementtext);
			e.setLocation(x, y);
			e.setSize(w, h);
		}
		else if (elementname.equals("panel_attributes"))
      {
			e.setState(elementtext);
		}		
		else if (elementname.equals("additional_attributes"))
      {
			e.setAdditionalAttributes(elementtext);
		}
	}

	public void characters(char[] ch, int start, int length)
   {
		elementtext += (new String(ch)).substring(start, start + length);
   }
}

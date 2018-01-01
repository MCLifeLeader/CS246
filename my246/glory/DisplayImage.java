// $Header: /usr/local/cvsroot/students/cs246/car03009/glory/DisplayImage.java,v 1.3 2006/06/08 05:35:09 mbcarey Exp $
import java.awt.BorderLayout;

import java.io.File;
import java.io.RandomAccessFile;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * This is my Random Picture Slide Show Class
 *
 * @author $Unknown, Inital Code Provided by Brother Neff$
 * @version $Revision: 1.3 $
  */
public class DisplayImage
   extends JFrame
   implements Runnable
{
   /**
    * Display Image
    */
   private ImageIcon mImage;

   /**
    * JLabel for the Image
    */
   private JLabel imageLabel;

   /**
    * JLabel for the title
    */
   private JLabel titleLabel;

   /**
    * An Array List to keep track of the images for random selection
    */
   private ArrayList<String> mNames = new ArrayList<String>();

   /**
    * Creates a new DisplayImage object.
    */
   public DisplayImage()
   {
   }

   /**
    * Creates a new DisplayImage object.
    */
   public DisplayImage(String urlIn, String titleIn)
   {
      URL tUrl = GetURL(urlIn);
      
      if (tUrl != null)
      {
         mImage = new ImageIcon(tUrl);

         imageLabel = new JLabel(mImage, SwingConstants.CENTER);
         getContentPane().add(imageLabel, BorderLayout.CENTER);

         titleLabel = new JLabel(titleIn, SwingConstants.CENTER);
         getContentPane().add(titleLabel, BorderLayout.SOUTH);

         pack();
      }
      else
      {
         System.out.println("No Valid URL's Found");
      }
   }

   /**
    * The main runnable
    */
   public void run()
   {
      LoadImageURLs();

      String url = "http://web.mit.edu/carplos/www/stata%20center.jpg";
      String title = "Various Image Slide Show: ";

      DisplayImage myDisplayItem = new DisplayImage(url, (title + url));
      myDisplayItem.setVisible(true);

      while (Glory.getInstance().isRunning(Thread.currentThread()))
      {
         try
         {
            Thread.sleep(4000);
            String urlName = getImageURLName().trim();
            URL tUrl = GetURL(urlName);
            
            if (tUrl != null && urlName != null)
            {
               myDisplayItem.getContentPane().removeAll();
               mImage = new ImageIcon(tUrl);

               imageLabel = new JLabel(mImage, SwingConstants.CENTER);
               myDisplayItem.getContentPane().add(imageLabel, BorderLayout.CENTER);

               titleLabel = new JLabel((title + urlName), SwingConstants.CENTER);
               myDisplayItem.getContentPane().add(titleLabel, BorderLayout.SOUTH);

               myDisplayItem.pack();
               myDisplayItem.repaint();
            }
            else
            {
               System.out.println("No Valid URL's Found");
            }
         }
         catch (Exception ex)
         {
            System.out.println(ex);
         }
      }

      myDisplayItem.setVisible(false);
      myDisplayItem.dispose();
      this.setVisible(false);
      this.dispose();
   }

   /**
    * Load all of the images from a file
    */
   public void LoadImageURLs()
   {
      String tName;

      try
      {
         JFileChooser myChooser = new JFileChooser();
         myChooser.setMultiSelectionEnabled(false);
         myChooser.setDialogTitle("Select Your Slide Show File");
         myChooser.showOpenDialog(this);
         myChooser.setCurrentDirectory(new File("."));

         if (myChooser.getSelectedFile() != null)
         {
            RandomAccessFile file = new RandomAccessFile(
                    myChooser.getSelectedFile().getAbsoluteFile(), "r");

            while (true)
            {
               tName = file.readLine();

               if (tName != null)
               {
                  mNames.add(tName);
               }
               else
               {
                  break;
               }
            }

            file.close();
         }
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }
   }

   /**
    * Get the Random URL Text String
    */
   public String getImageURLName()
   {
      String name;
      
      try
      {
         java.util.Random rm = new Random();
         name = mNames.get(rm.nextInt(mNames.size()));
      }
      catch(Exception ex)
      {
         System.out.println(ex);
         name = null;
      }

      return name;
   }

   /**
    * Convert a text URL into a real URL
    */
   public URL GetURL(String u)
   {
      try
      {
         return (new URL(u));
      }
      catch (MalformedURLException e)
      {
         System.out.println("Bad URL " + u);
      }

      return (null);
   }
}

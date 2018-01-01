import javax.swing.*;
import java.awt.BorderLayout;
import java.net.*;

public class ImageView
   extends JFrame
{
   private URL source;
   private String title;
   private JLabel imageLabel;
   private JLabel titleLabel;
    
   public ImageView(String u, String t)
   {
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      try
      {
         source = new URL(u);
      }
      catch (MalformedURLException e)
      {
         System.out.println("Bad URL " + source);
         System.exit(1);
      }
      title = t;
      ImageIcon image = new ImageIcon(source);
      imageLabel = new JLabel(image, SwingConstants.CENTER);
      getContentPane().add(imageLabel, BorderLayout.CENTER);
      titleLabel = new JLabel(title, SwingConstants.CENTER);
      getContentPane().add(titleLabel, BorderLayout.SOUTH);
      pack();
   }
    
   public static void main(String[] args)
   {
      String url = "http://web.mit.edu/carplos/www/stata%20center.jpg";
      String title = "The New Stata Center";
      new ImageView(url, title).setVisible(true);
   }
}

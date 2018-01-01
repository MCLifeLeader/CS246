// $Header: /usr/local/cvsroot/courses/cs246/samplecode/gui/dpsse/DPSSE.java,v 1.2 2006/06/14 17:04:38 neffr Exp $

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Design Problem Solution Space Exploration
 */
public class DPSSE
   extends JFrame
   implements ActionListener	   
{
   /**
    * The center button.
    */
   JButton mCenterButton;

   /**
    * Instantiates a new DPSSE and makes it visible.
    */
   public static void main(String[] args)
   {
      new DPSSE().setVisible(true);
   }

   /**
    * Constructs a new DPSSE instance.
    */
   public DPSSE()
   {
      super("Design Problem Solution Space Exploration");
      setFont(new Font("Times", 1, 48));
      setLayout(new BorderLayout());
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      addButton("Red", "North");
      addButton("Green", "East");
      addButton("Blue", "West");
      addButton("Black", "South");
      mCenterButton = new JButton("Center");
      add(mCenterButton, "Center");
      pack();
   }

   void addButton(String label, String constraint)
   {
      JButton button = new JButton(label);
      add(button, constraint);
      button.addActionListener(this);
   }

   public void actionPerformed(ActionEvent ae)
   {
      String colorName = ae.getActionCommand().toUpperCase();
      Color color = null;
      try
      {
         color = (Color) Color.class.getField(colorName).get(null);
         mCenterButton.setForeground(color);
      }
      catch (Exception e)
      {
      }
   }
}



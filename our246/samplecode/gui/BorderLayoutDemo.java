// $Header: /usr/local/cvsroot/courses/cs246/samplecode/gui/BorderLayoutDemo.java,v 1.5 2006/05/31 16:54:32 neffr Exp $

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BorderLayoutDemo
   extends JFrame
   implements ActionListener
{
   private JFrame mFrame;

   public static void main(String[] args)
   {
      new BorderLayoutDemo().setVisible(true);
   }

   public BorderLayoutDemo()
   {
      super("Border Layout Demo");
      mFrame = this;
      // setDefaultCloseOperation(EXIT_ON_CLOSE);
      addWindowListener(new WindowAdapter()
	  {
	      public void windowClosing(WindowEvent e)
	      {
		  if (JOptionPane.showConfirmDialog(mFrame,
                      "Are you sure you want to exit?", "Confirm Exit",
                      JOptionPane.YES_NO_OPTION) == 0)
		  {
		      System.out.println("Bye now!");
		      System.exit(0);
		  }
	      }
	  });
      setFont(new Font("Arial", 1, 20));
      setLayout(new BorderLayout());
      
      add(new JButton("North"),  BorderLayout.NORTH);
      add(new JButton("South"),  BorderLayout.SOUTH);
      add(new JButton("East"),   BorderLayout.EAST);
      add(new JButton("West"),   BorderLayout.WEST);
      add(new JButton("Center"), BorderLayout.CENTER);

      pack();
   }

   public void actionPerformed(ActionEvent ae)
   {
      System.out.println(ae);
      JButton button = (JButton) ae.getSource();
      if (! button.getText().equals("Click me again!"))
	  button.setText("Click me again!");
      //      else
      //	  button.setText(button.getOriginalText());
   }

   private void add(JButton button, String constraint)
   {
      button.addActionListener(this);
      super.add(button, constraint);
   }
}

// $Header: /usr/local/cvsroot/courses/cs246/samplecode/gui/BorderLayoutDemo.java,v 1.1 2006/05/30 15:19:32 neffr Exp $

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

public class BorderLayoutDemo
   extends Dialog
{
   public static void main(String[] args)
   {
      new BorderLayoutDemo().setVisible(true);
   }

   public BorderLayoutDemo()
   {
      super(new Frame(), "Border Layout Demo");
      setFont(new Font("Arial", 1, 20));
      setLayout(new BorderLayout());
      
      add(new Button("North"),  BorderLayout.NORTH);
      add(new Button("South"),  BorderLayout.SOUTH);
      add(new Button("East"),   BorderLayout.EAST);
      add(new Button("West"),   BorderLayout.WEST);
      add(new Button("Center"), BorderLayout.CENTER);
   }
}

// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/AboutPopup.java,v 1.2 2006/06/15 01:21:07 rk223 Exp $
package sudoku.ui;

import java.awt.*;

import javax.swing.*;

/**
 * Creates the window for the about information
 * @author Eric Merrill
 */
public class AboutPopup
   extends JPanel
{
   /**
    * Creates a new AboutGui object.
    */
   public AboutPopup()
   {
      JFrame frame = new JFrame("About");
      frame.setResizable(false);

      SpringLayout layout = new SpringLayout();
      frame.setLayout(layout);
      frame.setSize(350, 400);
      frame.setVisible(true);

      ImageIcon icon = new ImageIcon("config/images/sudoku.jpg");
      JLabel sudoku = new JLabel(icon);

      JLabel title = new JLabel("A simple interpretation of the game Sudoku.");

      JLabel credits = new JLabel(
            "<html><strong><u> Credits <u></strong></html>");
      credits.setFont(new Font("Times", Font.PLAIN, 14));

      JLabel teacher = new JLabel("Professor: Rick Neff");
      JLabel students = new JLabel("Students:");
      JLabel studentNames = new JLabel(
            "<html><strong>Randall Booth<br> Mike Carey<br> Alan Chase<br> Richard Cochran<br> James Comish<br> Randy King<br> Eric Merrill<br> Steven Owens<br> James Ricks<br> Michael Ricks<br> Ben Stoddard </strong></html>");

      frame.add(sudoku);
      frame.add(title);
      frame.add(credits);

      layout.putConstraint(SpringLayout.WEST, sudoku, 125, SpringLayout.WEST,
         frame);
      layout.putConstraint(SpringLayout.NORTH, sudoku, 15, SpringLayout.NORTH,
         frame);

      layout.putConstraint(SpringLayout.WEST, title, 15, SpringLayout.WEST,
         frame);
      layout.putConstraint(SpringLayout.NORTH, title, 105, SpringLayout.NORTH,
         frame);

      layout.putConstraint(SpringLayout.WEST, credits, 30, SpringLayout.WEST,
         frame);
      layout.putConstraint(SpringLayout.NORTH, credits, 125,
         SpringLayout.NORTH, frame);

      frame.add(teacher);
      frame.add(students);
      frame.add(studentNames);

      layout.putConstraint(SpringLayout.WEST, teacher, 30, SpringLayout.WEST,
         frame);
      layout.putConstraint(SpringLayout.NORTH, teacher, 150,
         SpringLayout.NORTH, frame);

      layout.putConstraint(SpringLayout.WEST, students, 30, SpringLayout.WEST,
         frame);
      layout.putConstraint(SpringLayout.NORTH, students, 175,
         SpringLayout.NORTH, frame);

      layout.putConstraint(SpringLayout.WEST, studentNames, 45,
         SpringLayout.WEST, frame);
      layout.putConstraint(SpringLayout.NORTH, studentNames, 200,
         SpringLayout.NORTH, frame);
   }
}

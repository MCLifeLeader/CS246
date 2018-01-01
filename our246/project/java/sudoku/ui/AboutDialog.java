// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/AboutDialog.java,v 1.7 2006/06/25 07:11:47 emerrill Exp $
package sudoku.ui;

import sudoku.si.*;

import java.awt.*;

import java.util.*;

import javax.swing.*;

/**
 * Creates the window for the about information
 * @author Eric Merrill
 */
public class AboutDialog
   extends JDialog
{
   /**
    * Label displaying Sudoku information.
    */
   private JLabel mSudoku;

   /**
    * The title of the game.
    */
   private JLabel mTitle;

   /**
    * A label for credits.
    */
   private JLabel mCredits;

   /**
    * The teacher's name.
    */
   private JLabel mTeacher;

   /**
    * A label for the student group.
    */
   private JLabel mStudents;

   /**
    * The students who worked on this.
    */
   private JLabel mStudentNames;

   /**
    * Creates a new AboutGui object.
    */
   public AboutDialog()
   {
      super(Sudoku.getFrame(), "About Sudoku");

      try
      {
         Icon icon = new ImageIcon(getClass()
                                      .getResource("/config/images/sudoku.jpg"));
         mSudoku = new JLabel(icon);
      }
      catch (Exception ex)
      {
         mSudoku = new JLabel();
      }

      mTitle = new JLabel("A simple interpretation of the game Sudoku.");
      mCredits = new JLabel("<html><strong><u> Credits <u></strong></html>");
      mTeacher = new JLabel("Professor: Rick Neff");
      mStudents = new JLabel("Students:");
      mStudentNames = new JLabel(
            "<html><strong>Randall Booth<br> Mike Carey<br> Alan Chase<br> Richard Cochran<br> James Comish<br> Randy King<br> Eric Merrill<br> Steven Owens<br> James Ricks<br> Michael Ricks<br> Ben Stoddard </strong></html>");
      init();
   }

   /**
    * Called only by constructor. Creates the JDialog window.
    */
   private void init()
   {
      SpringLayout layout = new SpringLayout();
      mCredits.setFont(new Font("Times", Font.PLAIN, 14));
      setLayout(layout);

      Properties pr = System.getProperties();
      String osName = pr.getProperty("os.name");
      Logger.debug("The OS is : " + osName);

      if (osName.equals("Linux"))
      {
         setSize(350, 400);
      }
      else
      {
         setSize(350, 450);
      }

      setResizable(false);

      add(mSudoku);
      add(mTitle);
      add(mCredits);
      add(mTeacher);
      add(mStudents);
      add(mStudentNames);

      layout.putConstraint(SpringLayout.WEST, mSudoku, 125, SpringLayout.WEST,
         this);
      layout.putConstraint(SpringLayout.NORTH, mSudoku, 15, SpringLayout.NORTH,
         this);

      layout.putConstraint(SpringLayout.WEST, mTitle, 15, SpringLayout.WEST,
         this);
      layout.putConstraint(SpringLayout.NORTH, mTitle, 105, SpringLayout.NORTH,
         this);

      layout.putConstraint(SpringLayout.WEST, mCredits, 30, SpringLayout.WEST,
         this);
      layout.putConstraint(SpringLayout.NORTH, mCredits, 125,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, mTeacher, 30, SpringLayout.WEST,
         this);
      layout.putConstraint(SpringLayout.NORTH, mTeacher, 150,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, mStudents, 30, SpringLayout.WEST,
         this);
      layout.putConstraint(SpringLayout.NORTH, mStudents, 175,
         SpringLayout.NORTH, this);

      layout.putConstraint(SpringLayout.WEST, mStudentNames, 45,
         SpringLayout.WEST, this);
      layout.putConstraint(SpringLayout.NORTH, mStudentNames, 200,
         SpringLayout.NORTH, this);
   }
}

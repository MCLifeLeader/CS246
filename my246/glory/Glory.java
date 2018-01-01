// $Header: /usr/local/cvsroot/students/cs246/car03009/glory/Glory.java,v 1.3 2006/06/08 05:35:09 mbcarey Exp $
import org.jdesktop.layout.GroupLayout; //Needs the class files in org
import org.jdesktop.layout.LayoutStyle; //Needs the class files in org

import java.io.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

/**
 * Main Glory Thread Manager
 *
 * @author $Mike Carey$
 * @version $Revision: 1.3 $
  */
public class Glory
   extends javax.swing.JFrame
{
   /**
    * Used as part of the browse button for searching for classes
    */
   private File mFile;

   /**
    * To keep a list of runnable classes
    */
   private DefaultListModel mModelRunnablesList;

   /**
    * To keep a list of threads
    */
   private DefaultListModel mModelThreadsList;

   /**
    * A literal collection of threads
    */
   private ArrayList<Thread> mThreads;

   /**
    * Singleton declaration
    */
   private static Glory mGlory = new Glory();

   /**
    * Creates a new Glory object.
    */
   private Glory()
   {
      initComponents();

      mModelRunnablesList = new DefaultListModel();
      mModelThreadsList = new DefaultListModel();
      mThreads = new ArrayList<Thread>();
   }

   /**
    * Return the instance of this program
    */
   public static Glory getInstance()
   {
      return mGlory;
   }

   /**
    * Initialize the components on the form
    */
   private void initComponents()
   {
      formatPanel = new javax.swing.JPanel();
      jPanelRunnables = new javax.swing.JPanel();
      jSPListOfRunnables = new javax.swing.JScrollPane();
      jListOfRunnables = new javax.swing.JList();
      jBtnStart = new javax.swing.JButton();
      jPanelThreads = new javax.swing.JPanel();
      jSPListOfThreads = new javax.swing.JScrollPane();
      jListOfThreads = new javax.swing.JList();
      jBtnStop = new javax.swing.JButton();
      jPanelBrowse = new javax.swing.JPanel();
      jTxtBrowse = new javax.swing.JTextField();
      jBtnBrowse = new javax.swing.JButton();
      jBtnAdd = new javax.swing.JButton();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      formatPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
      formatPanel.setMinimumSize(null);
      jPanelRunnables.setBorder(javax.swing.BorderFactory.createTitledBorder(
            "Runnables"));
      jPanelRunnables.setToolTipText("List of Possible Runnables.");
      jListOfRunnables.setToolTipText(
         "Select a Runnable and click Start to begin the execution of that Runnable thread.");
      jSPListOfRunnables.setViewportView(jListOfRunnables);

      jBtnStart.setText("Start");
      jBtnStart.addActionListener(new java.awt.event.ActionListener()
         {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
               jBtnStartActionPerformed(evt);
            }
         });

      GroupLayout jPanelRunnablesLayout = new GroupLayout(jPanelRunnables);
      jPanelRunnables.setLayout(jPanelRunnablesLayout);
      jPanelRunnablesLayout.setHorizontalGroup(jPanelRunnablesLayout.createParallelGroup(
            GroupLayout.LEADING)
            .add(jSPListOfRunnables,
            GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
            .add(jPanelRunnablesLayout.createSequentialGroup()
            .add(jBtnStart)
            .addContainerGap()));
      jPanelRunnablesLayout.setVerticalGroup(jPanelRunnablesLayout.createParallelGroup(
            GroupLayout.LEADING)
            .add(jPanelRunnablesLayout.createSequentialGroup()
            .add(jSPListOfRunnables,
            GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
           .addPreferredGap(LayoutStyle.RELATED)
           .add(jBtnStart)));

      jPanelThreads.setBorder(javax.swing.BorderFactory.createTitledBorder(
            "Running Threads"));
      jPanelThreads.setToolTipText("List of Currently running Threads.");
      jListOfThreads.setToolTipText(
         "Select a Thread then click Stop to kill that running thread.");
      jSPListOfThreads.setViewportView(jListOfThreads);

      jBtnStop.setText("Stop");
      jBtnStop.addActionListener(new java.awt.event.ActionListener()
         {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
               jBtnStopActionPerformed(evt);
            }
         });

      GroupLayout jPanelThreadsLayout = new GroupLayout(jPanelThreads);
      jPanelThreads.setLayout(jPanelThreadsLayout);
      jPanelThreadsLayout.setHorizontalGroup(jPanelThreadsLayout.createParallelGroup(
            GroupLayout.LEADING)
            .add(jPanelThreadsLayout.createSequentialGroup()
            .add(jPanelThreadsLayout.createParallelGroup(
            GroupLayout.LEADING)
            .add(jSPListOfThreads,
            GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE).add(jBtnStop))
            .addContainerGap(GroupLayout.DEFAULT_SIZE,
            Short.MAX_VALUE)));
      
      jPanelThreadsLayout.setVerticalGroup(jPanelThreadsLayout.createParallelGroup(
            GroupLayout.LEADING)
            .add(jPanelThreadsLayout.createSequentialGroup()
            .add(jSPListOfThreads,
            GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
            .addPreferredGap(LayoutStyle.RELATED)
            .add(jBtnStop)));

      GroupLayout formatPanelLayout = new GroupLayout(formatPanel);
      formatPanel.setLayout(formatPanelLayout);
      formatPanelLayout.setHorizontalGroup(formatPanelLayout.createParallelGroup(
         GroupLayout.LEADING)
         .add(GroupLayout.TRAILING,
         formatPanelLayout.createSequentialGroup()
         .add(jPanelRunnables, GroupLayout.DEFAULT_SIZE,
         GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         .addPreferredGap(LayoutStyle.RELATED)
         .add(jPanelThreads, GroupLayout.DEFAULT_SIZE, 406,
         Short.MAX_VALUE)));

      formatPanelLayout.setVerticalGroup(formatPanelLayout.createParallelGroup(
         GroupLayout.LEADING)
         .add(jPanelThreads,
         0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         .add(jPanelRunnables,
         GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

      jPanelBrowse.setBorder(javax.swing.BorderFactory.createTitledBorder(
            "Enter Runnable"));
      jPanelBrowse.setToolTipText("Find or Enter a Runnable");
      jTxtBrowse.setToolTipText("Enter the Runnable name here.");

      jBtnBrowse.setText("Browse");
      jBtnBrowse.setToolTipText("Click Browse to search for a Runnable.");
      jBtnBrowse.addActionListener(new java.awt.event.ActionListener()
         {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
               jBtnBrowseActionPerformed(evt);
            }
         });

      jBtnAdd.setToolTipText("Click this to add the Runnable to the list.");
      jBtnAdd.setLabel("Add");
      jBtnAdd.addActionListener(new java.awt.event.ActionListener()
         {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
               jBtnAddActionPerformed(evt);
            }
         });

      GroupLayout jPanelBrowseLayout = new GroupLayout(jPanelBrowse);
      jPanelBrowse.setLayout(jPanelBrowseLayout);
      jPanelBrowseLayout.setHorizontalGroup(jPanelBrowseLayout.createParallelGroup(
         GroupLayout.LEADING)
         .add(jPanelBrowseLayout.createSequentialGroup()
         .addContainerGap()
         .add(jTxtBrowse,
         GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
         .addPreferredGap(LayoutStyle.RELATED)
         .add(jBtnBrowse)
         .addPreferredGap(LayoutStyle.RELATED)
         .add(jBtnAdd)
         .add(44,
         44, 44)));

      jPanelBrowseLayout.setVerticalGroup(jPanelBrowseLayout.createParallelGroup(
         GroupLayout.LEADING)
         .add(jPanelBrowseLayout.createSequentialGroup()
         .addContainerGap()
         .add(jPanelBrowseLayout.createParallelGroup(
         GroupLayout.BASELINE).add(jTxtBrowse).add(jBtnBrowse)
         .add(jBtnAdd))
         .addContainerGap(GroupLayout.DEFAULT_SIZE,
         Short.MAX_VALUE)));

      GroupLayout layout = new GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.LEADING)
         .add(GroupLayout.TRAILING,
         layout.createSequentialGroup()
         .add(layout.createParallelGroup(GroupLayout.TRAILING)
         .add(GroupLayout.LEADING, formatPanel,
         GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
         Short.MAX_VALUE)
         .add(jPanelBrowse, GroupLayout.DEFAULT_SIZE,
         GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
      
      layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
         .add(jPanelBrowse,
         GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
         GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.RELATED)
         .add(formatPanel,
         GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
         Short.MAX_VALUE)
         .addContainerGap(GroupLayout.DEFAULT_SIZE,
         Short.MAX_VALUE)));
      pack();
   }

   /**
    * Add a class to the list
    */
   private void jBtnAddActionPerformed(java.awt.event.ActionEvent evt)
   {
      if (! mModelRunnablesList.contains(jTxtBrowse.getText()))
      {
         mModelRunnablesList.addElement(jTxtBrowse.getText());
         jListOfRunnables.removeAll();
         jListOfRunnables.setModel(mModelRunnablesList);
      }
   }

   /**
    * Stop a running thread
    */
   private void jBtnStopActionPerformed(java.awt.event.ActionEvent evt)
   {
      try
      {
         Thread tKill;

         if ((mModelThreadsList.getSize() > 0)
                && ! jListOfThreads.isSelectionEmpty())
         {
            if (mThreads.get(jListOfThreads.getSelectedIndex()).isAlive())
            {
               tKill = mThreads.get(jListOfThreads.getSelectedIndex());

               mThreads.remove(jListOfThreads.getSelectedIndex());
               mModelThreadsList.remove(jListOfThreads.getSelectedIndex());
               jListOfThreads.removeAll();
               jListOfThreads.setModel(mModelThreadsList);

               int tBreak = 0;

               while (tKill.isAlive())
               {
                  if (tBreak > 4)
                  {
                     break;
                  }

                  Thread.currentThread().sleep(1001);
                  tBreak++;
               }

               tKill.stop();
               tKill = null;
            }
         }
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }
   }

   /**
    * Start a runnable thread
    */
   private void jBtnStartActionPerformed(java.awt.event.ActionEvent evt)
   {
      try
      {
         if (! jListOfRunnables.isSelectionEmpty())
         {
            Runnable runnable = (Runnable) StringUtil.toObject(
               jListOfRunnables.getModel()
               .getElementAt(jListOfRunnables
               .getSelectedIndex()).toString());

            if (runnable != null)
            {
               Thread addThread = new Thread(runnable);
               addThread.start();
               mThreads.add(addThread);

               mModelThreadsList.addElement(jListOfRunnables.getModel()
                  .getElementAt(jListOfRunnables
                  .getSelectedIndex()));
               jListOfThreads.removeAll();
               jListOfThreads.setModel(mModelThreadsList);
            }
         }
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }
   }

   /**
    * Browse for a class to load
    */
   private void jBtnBrowseActionPerformed(java.awt.event.ActionEvent evt)
   {
      JFileChooser myChooser = new JFileChooser();
      myChooser.setMultiSelectionEnabled(false);
      myChooser.setDialogTitle("Select Your Runnable");
      myChooser.showOpenDialog(this);
      myChooser.setCurrentDirectory(new File("."));

      if (myChooser.getSelectedFile() != null)
      {
         mFile = myChooser.getSelectedFile();
         jTxtBrowse.setText(mFile.getName().split("\\.")[0]);
      }
   }

   /**
    * The Main function
    */
   public static void main(String[] args)
   {
      try
      {
         java.awt.EventQueue.invokeLater(new Runnable()
            {
               public void run()
               {
                  getInstance().setVisible(true);
               }
            });
      }
      catch (Exception ex)
      {
         System.out.println(ex);
      }
   }

   /**
    * Return the current list of threads running
    */
   public ArrayList<Thread> getThreads()
   {
      return mThreads;
   }

   /**
    * remove a thread from the list of threads running
    */
   public boolean remove(Thread runningThread)
   {
      return (mThreads.remove(runningThread));
   }

   /**
    * see if the current thread running in the list
    */
   public boolean isRunning(Thread runningThread)
   {
      if (mThreads.contains(runningThread))
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   private javax.swing.JPanel formatPanel;
   private javax.swing.JButton jBtnAdd;
   private javax.swing.JButton jBtnBrowse;
   private javax.swing.JButton jBtnStart;
   private javax.swing.JButton jBtnStop;
   private javax.swing.JList jListOfRunnables;
   private javax.swing.JList jListOfThreads;
   private javax.swing.JPanel jPanelBrowse;
   private javax.swing.JPanel jPanelRunnables;
   private javax.swing.JPanel jPanelThreads;
   private javax.swing.JScrollPane jSPListOfRunnables;
   private javax.swing.JScrollPane jSPListOfThreads;
   private javax.swing.JTextField jTxtBrowse;
}


/**
 * Provides several utility methods for converting Strings to
 * arrays or Collections or Objects and back again.
 *
 * @author Brother Neff
 */
class StringUtil
{
   /**
    * Returns a collection of objects whose class
    * name are given in a String array.
    *
    * @param args the String array of class names
    *
    * @return the Collection of newly-instantiated objects.
    */
   public static Collection toCollection(String[] args)
   {
      Collection rtnval = new ArrayList();

      for (String arg : args)
      {
         Object o = toObject(arg);

         if (o != null)
         {
            rtnval.add(o);
         }
      }

      return rtnval;
   }

   /**
    * Returns a newly-instantiated object of the class
    * whose name is passed.
    *
    * @param name the name of the class to be dynamically
    *             loaded and instantiated.
    * @return the newly-instantiated object, or null if
    *         no such object/class can be created/loaded.
    */
   public static Object toObject(String name)
   {
      Object rtnval = null;

      try
      {
         // 1. find the Class for the Name name (the argument passed)
         // 2. make a new instance of that Class and assign it to rtnval
         rtnval = Class.forName(name).newInstance();
      }
      catch (Exception e)
      {
         System.out.println(e);
      }

      return rtnval;
   }

   /**
    * Returns a String formed by "smooshing" all the String args
    * together with one space separating them.
    *
    * @param args the array of String objects.
    *
    * @return the String so formed.
    */
   public static String toString(String[] args)
   {
      StringBuffer buffer = new StringBuffer();

      for (String s : args)
      {
         buffer.append(s);
         buffer.append(' ');
      }

      return buffer.toString().substring(0, buffer.length() - 1);
   }

   /**
    * Returns an array of Strings formed by splitting the String
    * argument into tokens (space separated) which become the
    * array elements.
    *
    * @param arg the String to split
    *
    * @return the array so formed.
    */
   public static String[] toArray(String arg)
   {
      // "abc def" => ["abc", "def"]
      StringTokenizer tokenizer = new StringTokenizer(arg);
      String[] rtnval = new String[tokenizer.countTokens()];
      int i = 0;

      while (tokenizer.hasMoreTokens())
      {
         rtnval[i++] = tokenizer.nextToken();
      }

      return rtnval;
   }
}

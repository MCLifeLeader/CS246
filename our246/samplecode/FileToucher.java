// $Header: /usr/local/cvsroot/courses/cs246/samplecode/FileToucher.java,v 1.3 2006/05/17 16:13:18 neffr Exp $

import java.io.File;
import java.io.FileFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class FileToucher
   implements Runnable
{
   private Date mDate = null;
   private FileFilter mFileFilter = null;
   private long mCounter = 0;
   private String mMode = null;
   private File mFiles[] = null;

   public static void main(String[] args)
   {
      // standard BOOPOP #1 pattern
      new FileToucher(args).run();
   }

   public FileToucher(String[] args)
   {
      mDate = new Date();
      if (args.length > 0)
      {
         parseArgs(args);
      }
      else
      {
         mMode = "count";
         mFiles = File.listRoots();
      }
   }

   public void parseArgs(String[] args)
   {
      Vector fileList = new Vector();

      for (int i = 0; i < args.length; i++)
      {
         String arg = args[i];
         if (arg.charAt(0) == '-')
         {
            try
            {
               if (arg.charAt(1) == 'c')
               {
                  mMode = "count";
               }
               else if (arg.charAt(1) == 't')
               {
                  mMode = "timestamp";
                  mDate = new SimpleDateFormat("yyyyMMddHHmm").
                     parse(args[i + 1]);
               }
               else if (arg.charAt(1) == 's')
               {
                  mMode = "soft";
               }
               else if (arg.charAt(1) == 'h' ||
                        arg.charAt(1) == '?' ||
                        arg.equals("--help"))
               {
                  mMode = "help";
               }
            }
            catch (ParseException e)
            {
            }
            catch (StringIndexOutOfBoundsException e)
            {
            }
            finally
            {
               if (mMode == null)
               {
                  showHelp();
                  System.exit(1);
               }
            }
         }
         else
         {
            fileList.add(arg);
         }
      }
      if (mFiles == null)
      {
         mFiles = new File[fileList.size()];
         for (int i = 0; i < fileList.size(); i++)
         {
            mFiles[i] = new File((String) fileList.get(i));
         }
      }
   }

   public void run()
   {
      if ("help".equals(mMode))
      {
         showHelp();
      }
      else if (mFiles != null)
      {
         for (File file : mFiles)
         {
            touchAll(file);
         }
         if ("count".equals(mMode))
         {
            System.out.println("Counted " + mCounter + " files.");
         }
      }
   }

   public void showHelp()
   {
      System.out.println("Usage: java FileToucher [OPTION]... [FILES]...");
      System.out.println("Touches the files defined by [FILES] using the");
      System.out.println("method defined by [OPTION]. If no option is");
      System.out.println("defined, it will default to '-c'. If no file is");
      System.out.println("defined, it will recursively touch all root");
      System.out.println("folders.");
      System.out.println("");
      System.out.println("  -s     Does not touch files, only lists them");
      System.out.println("  -c     Counts files specified");
      System.out.println("  -t     Updates the timestamp to now");
   }

   public void touchAll(File file)
   {
      if (! file.isDirectory())
      {
         if (file.exists())
         {
            touchOne(file);
         }
      }
      else
      {
         File[] files = file.listFiles(mFileFilter);
         if (files != null)
         {
            for (int i = 0; i < files.length; i++)
            {
               touchAll(files[i]);
            }
         }
      }
   }

   public void touchOne(File file)
   {
      if ("soft".equals(mMode))
      {
         System.out.println(file);
      }
      else if ("count".equals(mMode))
      {
         mCounter++;
      }
      else if ("timestamp".equals(mMode))
      {
         file.setLastModified(mDate.getTime());
      }
   }
}

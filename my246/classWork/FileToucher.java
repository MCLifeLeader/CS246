// $Header: /usr/local/cvsroot/students/cs246/car03009/classWork/FileToucher.java,v 1.3 2006/05/17 15:52:26 mbcarey Exp $

import java.io.File;

public class FileToucher
{
   public FileToucher()
   {
   }

   public static void main(String[] args)
      throws Exception
   {
      if (args.length > 0)
      {
         // get current time (now) in milliseconds
         long now = System.currentTimeMillis();

         // use command line args for filenames
         try
         {
            for (int i = 0; i < args.length; i++)
            {
               File file = new File(args[i]);
               if (file.exists())
               {
                  if (!file.setLastModified(now))
                     System.out.println("Failed to touch `" + file + "` Check File or Directory Permissions");
               }
               else
               {
                  if (!file.createNewFile())
                     System.out.println("Failed to Create File `" + file + "` Check File or Directory Permissions");
               }
            }
         }
         catch (Exception e)
         {
            System.out.println(e);
         }
      }
      else
         System.out.println("FileToucher filename1 filename2 filenameN");
   }
}


import java.io.File;
import java.io.FileFilter;

public class Mystery
   extends Thread
{
   public static void main(String[] args)
   {
      new Mystery().start();
   }

   private File newFileN(int n)
   {
      return new File(String.format("file.%05d", n));
   }

   Thread mCreator = new Thread()
      {
         private int mFileNumber = 0;

         public void run()
         {
            while (true)
            {
               try
               {
                  if (newFileN(mFileNumber).createNewFile())
                  {
                     mFileNumber++;
                  }
                  Thread.sleep(1000);
               }
               catch (Exception e) { }
            }
         }
      };

   Thread mDestroyer = new Thread()
      {
         private int mFileNumber = 0;

         public void run()
         {
            while (true)
            {
               try
               {
                  if (newFileN(mFileNumber).delete())
                  {
                     mFileNumber++;
                  }
                  Thread.sleep(2000);
               }
               catch (Exception e) { }
            }
         }
      };

   FileToucher mWatcher =
   new FileToucher(new String[]{"."})
      {
         private int mCounter = 0;
         public void touchOne(File file)
         {
            mCounter++;
         }
         public void report(File file)
         {
            System.out.println(mCounter);
            mCounter = 0;
         }
      };

   public void run()
   {
      mCreator.start();
      mDestroyer.start();
      mWatcher.mFileFilter = new FileFilter()
         {
            public boolean accept(File file)
            {
               return (file.isDirectory() ||
                       file.getName().startsWith("file."));
            }
         };

      while (true)
      {
         try
         {
            System.out.println("----------");
            new Thread(mWatcher).start();
            Thread.sleep(1000);
         }
         catch (Exception e) {}
      }
   }
}

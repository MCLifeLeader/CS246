import java.io.File;

public class Creator
   extends Thread
{
   public static void main(String[] args)
   {
      new Creator().start();
   }

   private int mFileNumber = 0;

   private File newFileN(int n)
   {
      return new File(String.format("file.%05d", n));
   }

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
         catch (Exception e)
         {
            // do nothing
         }
      }
   }
}

import java.io.File;

public class Destroyer
   extends Thread
{
   public static void main(String[] args)
   {
      new Destroyer().start();
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
            if (newFileN(mFileNumber).delete())
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

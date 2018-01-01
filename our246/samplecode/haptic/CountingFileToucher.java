import java.io.File;

public class CountingFileToucher
   extends FileToucher
{
   private int mCounter;

   CountingFileToucher(String[] args)
   {
      super(args);
      mCounter = 0;
   }
   
   public void touchOne(File file)
   {
      mCounter++;
   }

   public void report(File file)
   {
      System.out.println("There were " + mCounter + " files counted in '" + file +  "'.");
   }

   public static void main(String[] args)
   {
      new CountingFileToucher(args).run();
   }
}
    

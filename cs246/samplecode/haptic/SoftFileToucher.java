import java.io.File;
import java.io.FileFilter;

public class SoftFileToucher
   extends FileToucher
{
   SoftFileToucher(String[] args)
   {
      super(args);
   }

   public void touchOne(File file)
   {
      System.out.println(file);
   }

   public static void main(String[] args)
   {
      new SoftFileToucher(args).run();
   }
}

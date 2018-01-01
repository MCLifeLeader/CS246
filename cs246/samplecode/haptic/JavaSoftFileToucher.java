import java.io.File;
import java.io.FileFilter;

public class JavaSoftFileToucher
   extends SoftFileToucher
   implements FileFilter
{
   public JavaSoftFileToucher(String[] args)
   {
      super(args);
      mFileFilter = this;
   }
   
   //Accept all directories and .java files
   public boolean accept(File f)
   {
      if (f.isDirectory())
      {
         return true;
      }
      else
      {
         return (f.getName().endsWith(".java"));
      }
   }

   public static void main (String[] args)
   {
      new JavaSoftFileToucher(args).run();
   }
}

   

import java.io.File;

public class JavaSoftFileToucher
   extends SoftFileToucher
{
   public JavaSoftFileToucher(String[] args)
   {
      super(args);
      mFileFilter = new SuffixMatchingFileFilter(".java");
   }

   public static void main (String[] args)
   {
      new JavaSoftFileToucher(args).run();
   }
}

   

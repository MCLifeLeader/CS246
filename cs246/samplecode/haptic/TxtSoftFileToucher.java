import java.io.File;
import java.io.FileFilter;

/***
 *  Prints out list of all .txt files when given a directory as an argument.
 *  Otherwise just prints out the file name.
 *
 **/
public class TxtSoftFileToucher extends SoftFileToucher implements FileFilter
{
   public TxtSoftFileToucher (String[] args)
   {
      super(args);
      mFileFilter = this;
   }
   
   /***
    *  Accept all directories and .txt files
    **/
   public boolean accept(File f)
   {
      if (f.isDirectory())
      {
         return true;
      }
      if (f.getName().toLowerCase().endsWith(".txt"))
     {
        return true;
     }
     else
     {
        return false;
     }
   }

   /***
    * Creates new object and runs it.
    **/
   public static void main (String[] args)
   {
      new TxtSoftFileToucher (args).run();
   }
}

   

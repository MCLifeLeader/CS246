
/**
 * Prints out list of all .txt files when given a directory as an argument.
 * Otherwise just prints out the file name.
 */
public class TxtSoftFileToucher
   extends SoftFileToucher
{
   public TxtSoftFileToucher(String[] args)
   {
      super(args);
      mFileFilter = new SuffixMatchingFileFilter(".txt");
   }

   public static void main (String[] args)
   {
      new TxtSoftFileToucher(args).run();
   }
}

   

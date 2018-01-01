import java.io.File;
import java.io.FileFilter;

public class SuffixMatchingFileFilter
   implements FileFilter
{
   private String mSuffix;

   public SuffixMatchingFileFilter(String inSuffix)
   {
      setSuffix(inSuffix);
   }

   public void setSuffix(String inSuffix)
   {
      mSuffix = inSuffix;
   }

   public boolean accept(File f)
   {
      if (f.isDirectory())
      {
         return true;
      }
      else
      {
         return (f.getName().endsWith(mSuffix));
      }
   }
}

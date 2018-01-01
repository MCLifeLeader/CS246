import java.io.File;

public class Question29
{
   public static void main(String[] args)
   {
      for (String name : args)
      {
         // 1. find the Class for the Name name (the argument passed)
         // 2. make a new instance of that Class and assign it to rtnval
         Runnable runnable = (Runnable) StringUtil.toObject(name);
         new Thread(runnable).start();
      }
   }
}

class TxtFileCollectorInNotes
   extends CollectingFileToucher
{
   public TxtFileCollectorInNotes()
   {
      super(new String[]{"/home/cs246/notes/"});
      mFileFilter = new SuffixMatchingFileFilter(".txt");
   }

   public void report(File file)
   {
      System.out.println("Here are the .txt files in notes: " + getFiles());
   }
}

class JavaCountingFileToucher
   extends FileToucher
{
   private int mCounter;

   public JavaCountingFileToucher()
   {
      super(new String[]{"/home/cs246/samplecode/"});
      mFileFilter = new SuffixMatchingFileFilter(".java");
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
}

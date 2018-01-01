// $Header: /usr/local/cvsroot/courses/cs246/samplecode/FunRun.java,v 1.2 2006/05/16 17:11:55 neffr Exp $

public class FunRun
   extends Thread
{
   private static final int DEFAULT_RUNLIMIT = 5;

   private String mName;

   public FunRun(String inName)
   {
      mName = inName;
   }
      
   public void run()
   {
      int runlimit = DEFAULT_RUNLIMIT;
      try
      {
         runlimit = Integer.parseInt(System.getProperty("runlimit"));
      }
      catch (NumberFormatException e)
      {
      }
      for (int i = 0; i < runlimit; i++)
      {
         System.out.println(mName + " runs!");
      }
   }

   public static void main(String[] args)
   {
      for (String arg : args) // new java code idiom
      {
         new FunRun(arg).start();
      }
   }
}

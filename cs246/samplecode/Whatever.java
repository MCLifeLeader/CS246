public class Whatever
   implements Runnable
{
   public static void main(String[] args)
   {
      new Whatever(args).run();
   }

   public Whatever(String[] args)
   {
   }

   public void run()
   {
      System.out.println("I'm running, whatever!");
   }
}

public class Question28
{
   public static void main(String[] args)
   {
      try
      {
         Class.forName("DoesNotExist");
      }
      catch (ClassNotFoundException e)
      {
         System.out.println(e);
      }
      try
      {
         Class.forName("Bill").newInstance();
      }
      catch (ClassNotFoundException e)
      {
         System.out.println(e);
      }
      catch (InstantiationException e)
      {
         System.out.println(e);
      }
      catch (IllegalAccessException e)
      {
         System.out.println(e);
      }
   }
}

class Bill { public Bill(String str) {} }

class Bob { private Bob() {} }

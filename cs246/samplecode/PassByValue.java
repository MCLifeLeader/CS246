/**
 * Demonstrate java's pass-by-value semantics.
 */
public class PassByValue
{
   /**
    * A generic object
    */
   private String mValue;

   /**
    * Constructs a new PassByValue
    *
    * @param inValue
    */
   public PassByValue(String inValue)
   {
      setValue(inValue);
   }

   public void setValue(String inValue)
   {
      mValue = inValue;
   }

   /**
    * Converts the obj value to a string.
    *
    * @return the object value itself
    */
   public String toString()
   {
      return mValue;
   }

   /**
    * A static method to "try" to change its argument.
    *
    * @param p
    * @param i
    */
   public static void change(PassByValue p, int i)
   {
      p = new PassByValue("Value 2");
      i = 2;
   }

   /**
    * Instantiates a new PassByValue object and tries to change it.
    *
    * @param args command-line arguments
    */
   public static void main(String[] args)
   {
      PassByValue p = new PassByValue("Value 1");
      int i = 1;

      change(p, i);

      System.out.println("p = " + p + ", i = " + i);
   }
}

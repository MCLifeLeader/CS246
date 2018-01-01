public class Dog
{
   private int weight;
   private String name;

   public void setWeight(int w)
   {
      weight = w;
   }

   public int getWeight()
   {
      return weight;
   }

   public void setName(String n)
   {
      name = n;
   }

   public String getName()
   {
      return name;
   }

   public void bark()
   {
      System.out.println("Ruff!");
   }

   public void bark(int num)
   {
      for (int i = 0; i < num; i++)
      {
         bark();
      }
   }

   public static void main(String[] args)
   {
      // test dog stuff
      Dog d = new Dog();
      d.setWeight(50);
      d.setName("Buffy");
      System.out.println(d.getWeight());
      System.out.println(d.getName());
      d.bark(3);
   }
}

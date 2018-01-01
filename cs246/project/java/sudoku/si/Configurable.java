// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/si/Configurable.java,v 1.3 2006/06/13 20:51:24 neffr Exp $
package sudoku.si;

/**
 * Maintains a piece of configurable data.
 * <p>
 * The term "configurable" means that there is some way for the
 * user to select or specify a "Configuration Point" (which is a
 *  shorter way to say "a piece of configurable data").
 * <p>
 * A configuration point is a "point" in some n-dimensional space
 * (where n is usually 3, 4 or 5 -- of the following):
 * <ul>
 *   <li>name
 *   <li>type
 *   <li>value
 *   <li>default value
 *   <li>set or range of valid values
 * </ul>
 */
public class Configurable
{
   /**
    * The name of this configuration point.
    */
   private String name;

   /**
    * The value of this configuration point.
    */
   private Object value;

   /**
    * Constructs a new Configurable instance.
    *
    * @param inName its name.
    * @param inValue its value.
    */
   public Configurable(String inName, Object inValue)
   {
      name = inName;
      value = inValue;
   }

   /**
    * Gets the name of this configuration point.
    *
    * @return the name of this configuration point.
    */
   public String getName()
   {
      return name;
   }

   /**
    * Gets the value of this configuration point.
    *
    * @return the value of this configuration point.
    */
   public Object getValue()
   {
      return value;
   }

   /**
    * Sets the value of this configuration point.
    *
    * @param inValue its new value.
    */
   public void setValue(Object inValue)
   {
      value = inValue;
   }

   /**
    * Tests for equality of two configurable objects.
    *
    * @param o the other configurable object to compare with.
    *
    * @return whether or not two configurable objects have the
    *         same name and value.
    */
   public boolean equals(Object o)
   {
      boolean rtnval = false;

      if (o instanceof Configurable)
      {
         Configurable configurable = (Configurable) o;
         rtnval = (name.equals(configurable.getName()) &&
                   value.equals(configurable.getValue()));
      }

      return rtnval;
   }

   /**
    * Returns a readable representation of this Configurable object.
    *
    * @return the readable representation of this Configurable object.
    */
   public String toString()
   {
      return ("{" + name + ", " + value.getClass().getName() +
              ", " + value + "}");
   }
}

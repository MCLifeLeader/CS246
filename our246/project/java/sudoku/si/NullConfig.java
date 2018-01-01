// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/si/NullConfig.java,v 1.3 2006/06/16 02:41:48 neffr Exp $
package sudoku.si;

import java.io.*;

/**
 * A "Null Object" pattern instance. The root Config
 * when traversing from parent to parent peters out.
 * System properties are the configuration points of
 * last resort that gets on this Config object return.
 */
public class NullConfig
   extends Config
{
   /**
    * The singleton instance of the NullConfig class.
    */
   private static NullConfig cInstance = new NullConfig();

   /**
    * Gets the singleton instance of the NullConfig class.
    *
    * @return the sole instance.
    */
   public static NullConfig getInstance()
   {
      return cInstance;
   }

   /**
    * Constructs a NullConfig instance, only called once
    * (per the Singleton Design Pattern).
    */
   private NullConfig()
   {
      super(null);
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    * @return the Configurable's String value.
    */
   protected String getInternal(String inName, String inValue)
   {
      return System.getProperty(inName, inValue);
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Integer value.
    */
   protected Integer getInternal(String inName, int inValue)
   {
      String systemProp = System.getProperty(inName);

      try
      {
         return new Integer(systemProp);
      }
      catch (Exception e)
      {
         return new Integer(inValue);
      }
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Float value.
    */
   protected Float getInternal(String inName, float inValue)
   {
      String systemProp = System.getProperty(inName);

      try
      {
         return new Float(systemProp);
      }
      catch (Exception e)
      {
         return new Float(inValue);
      }
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Double value.
    */
   protected Double getInternal(String inName, double inValue)
   {
      String systemProp = System.getProperty(inName);

      try
      {
         return new Double(systemProp);
      }
      catch (Exception e)
      {
         return new Double(inValue);
      }
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Character value.
    */
   protected Character getInternal(String inName, char inValue)
   {
      String systemProp = System.getProperty(inName);

      try
      {
         return new Character(systemProp.charAt(0));
      }
      catch (Exception e)
      {
         return new Character(inValue);
      }
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's String value.
    */
   protected Boolean getInternal(String inName, boolean inValue)
   {
      String systemProp = System.getProperty(inName,
            String.valueOf(inValue));

      try
      {
         return new Boolean(systemProp);
      }
      catch (Exception e)
      {
         return new Boolean(inValue);
      }
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Object value.
    */
   protected Object getInternal(String inName, Object inValue)
   {
      if (inValue instanceof Class<?>)
      {
         Class<?> supertype = (Class<?>) inValue;

         try
         {
            Class<?> c = Class.forName(inName);

            if (supertype.isAssignableFrom(c))
            {
               inValue = c.newInstance();
            }
         }
         catch (Exception e)
         {
            inValue = e;
         }
      }

      return inValue;
   }

   /**
    * Sets a configuration point (NO-OP).
    *
    * @param inName the name of the Configurable.
    * @param inValue the value of the Configurable.
    */
   protected void setInternal(String inName, Object inValue)
   {
      // intentionally empty
   }

   /**
    * Synchronizes all configurables in this Config (NO-OP).
    */
   protected void sync()
   {
      // intentionally empty
   }

   /**
    * Saves this Config object (NO-OP).
    */
   protected void saveInternal()
   {
      // intentionally empty
   }

   /**
    * Saves this Config object to an output stream (NO-OP).
    *
    * @param os the output stream.
    */
   protected void saveInternal(OutputStream os)
   {
      // intentionally empty
   }
}

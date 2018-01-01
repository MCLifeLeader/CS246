// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/si/Config.java,v 1.2 2006/06/19 14:25:43 neffr Exp $
package sudoku.si;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.*;

import java.io.*;

import java.lang.reflect.*;

import java.util.*;

/**
 * Configures the configurables.
 */
public class Config
{
   /**
    * The static map of xstream alias keys to alias values.
    */
   private static Map<String, Class> cAliases =
      new HashMap<String, Class>();

   /**
    * Puts default aliases for Config and Configurable.
    */
   static
   {
      putAlias("configuration", Config.class);
      putAlias("point", Configurable.class);
   }
     
   /**
    * Puts a new alias mapping for xstream.
    *
    * @param inKey the key.
    * @param inValue the value.
    */
   public static void putAlias(String inKey, Class inValue)
   {
      cAliases.put(inKey, inValue);
   }

   /**
    * The static map of Config names to Config objects.
    */
   private static Map<String, Config> cConfigs =
      new HashMap<String, Config>();

   /**
    * Finds a Config object based on implicit code context.
    * The context is where in the code this method is called,
    * either at the method, the class or the package level.
    *
    * @return the implicitly named Config object.
    */
   public static Config find()
   {
      return find(getContext());
   }

   /**
    * Finds a Config object based on explicit code context.
    *
    * @param name the context name
    *
    * @return the explicitly named Config object.
    */
   static Config find(String name)
   {
      Config rtnval = cConfigs.get(name);

      if (rtnval == null)
      {
         rtnval = load(name);
         cConfigs.put(name, rtnval);
      }

      return rtnval;
   }

   /**
    * Loads a Config object from a disk file or jar file.
    *
    * @param name the context name.
    *
    * @return the named Config object.
    */
   private static Config load(String name)
   {
      Config rtnval = null;

      try
      {
         rtnval = read(new FileInputStream(getFilename(name)));
      }
      catch (Exception e)
      {
      }

      if (rtnval == null)
      {
         try
         {
            rtnval = read(Config.class.getResourceAsStream(getPath(name)));
         }
         catch (Exception e)
         {
         }
      }

      if (rtnval == null)
      {
         rtnval = new Config(name);
      }

      return rtnval;
   }

   /**
    * Gets a Config file name implicitly from the code context.
    *
    * @return the name of the file.
    */
   public static String getFilename()
   {
      return Config.find().getFilenameInternal();
   }

   /**
    * Saves a Config object.
    * Not meant to be called by clients.
    */
   protected void saveInternal()
   {
      // only save if there's something worth saving
      if (! list.isEmpty())
      {
         try
         {
            saveInternal(new FileOutputStream(getFilename(name)));
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }

   /**
    * Saves a Config object to an output stream.
    * Not meant to be called by clients.
    *
    * @param os the output stream to save to.
    */
   protected void saveInternal(OutputStream os)
   {
      // only save if there's something worth saving
      if (! list.isEmpty())
      {
         try
         {
            write(os);
            os.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }

   /**
    * Gets the code context name for the current context.
    *
    * @return the name of the context.
    */
   private static String getContext()
   {
      return Context.get(Config.class.getName()).atClass;
   }

   /**
    * Gets the path to the Config file name.
    *
    * @param name the context name.
    *
    * @return the path to the Config file name.
    */
   private static String getPath(String name)
   {
      return "/config/" + getFilename(name);
   }

   /**
    * Gets the Config file name for the given named context.
    *
    * @param name the context name.
    *
    * @return the Config file name.
    */
   private static String getFilename(String name)
   {
      return name + ".config";
   }

   /**
    * Creates and returns an XStream object for converting to and from XML.
    * The aliases become the tag names in the XML document.
    * There CANNOT be a space in a tag name.
    */
   private static XStream getXStream()
   {
      XStream xstream = new XStream(new DomDriver());

      for (Map.Entry<String, Class> entry : cAliases.entrySet())
      {
         xstream.alias(entry.getKey(), entry.getValue());
      }
      return xstream;
   }

   /**
    * Converts a Configuration object to an XML file.
    */
   public void write(OutputStream out) throws IOException
   {
      OutputStreamWriter writer = new OutputStreamWriter(out);
      getXStream().toXML(this, writer);
      writer.close();
   }

   /**
    * Returns a Config object that has been saved to a file
    * connected to an input stream and converts from XML
    * to a Java object.
    */
   public static Config read(InputStream in) throws IOException
   {
      Config rtnval =
         (Config) getXStream().fromXML(new InputStreamReader(in));
      rtnval.init();

      return rtnval;
   }

   /**
    * The name of this Config object.
    */
   private String name;

   /**
    * The list of all Configurable objects.
    */
   private List<Configurable> list;

   /**
    * The map of Configurable names to Configurable objects.
    */
   private transient Map<String, Configurable> map;

   /**
    * A "parent" Config that may hold values for any keys not
    * found in this one.
    */
   private transient Config mParent;

   /**
    * Creates a new Config object.
    *
    * @param inName the explicit name of the Config
    *               (if null, no initialization happens).
    */
   public Config(String inName)
   {
      if (inName != null)
      {
         name = inName;
         init();
      }
   }

   /**
    * Initializes the Config instance.
    * Sets its parent to the Config object loaded from the parent context,
    * if any, otherwise to the NullConfig singleton.  Then calls sync().
    */
   private void init()
   {
      String parent = getParentContext();
      mParent =
         ((parent == null) ? NullConfig.getInstance() : load(parent));
      sync();
   }

   /**
    * Gets the parent context for this Config.
    * getParentContext("sudoku.si.Config") returns "sudoku.si"
    * getParentContext("sudoku.si") returns "sudoku"
    * getParentContext("sudoku") returns null
    */
   private String getParentContext()
   {
      int lastDotIndex = name.lastIndexOf(".");

      return ((lastDotIndex == -1) ?
              null : name.substring(0, lastDotIndex));
   }

   /**
    * Gets the name of the Config.
    *
    * @return the name of the Config.
    */
   protected String getNameInternal()
   {
      return name;
   }

   /**
    * Gets the file name of the Config.
    *
    * @return the file name of the Config.
    */
   protected String getFilenameInternal()
   {
      return getFilename(name);
   }

   /**
    * Synchronizes the Configurables list with the
    * Configurables map.
    */
   protected void sync()
   {
      if (map == null)
      {
         map = new HashMap<String, Configurable>();
      }
      else
      {
         map.clear();
      }

      if (list == null)
      {
         list = new ArrayList<Configurable>();
      }

      for (Configurable configurable : list)
      {
         map.put(configurable.getName(), configurable);
      }
   }

   /**
    * Returns a readable representation of the Config.
    *
    * @return a readable representation of the Config.
    */
   public String toString()
   {
      return name + ":" + list;
   }

   /**
    * Adds a Configurable to the list and the map.
    *
    * @param configurable the Configurable to add.
    */
   private void add(Configurable configurable)
   {
      // prevent duplicates
      Configurable alreadyThere = map.get(configurable.getName());

      if (alreadyThere == null) // not already there
      {
         list.add(configurable);
         map.put(configurable.getName(), configurable);
      }
      else if (! alreadyThere.getValue().equals(configurable.getValue()))
      {
         alreadyThere.setValue(configurable.getValue());
      }
   }

   /**
    * Sets a configuration point by adding a Configurable object
    *
    * @param inName the name of the Configurable.
    * @param inValue the value of the Configurable.
    */
   void setInternal(String inName, Object inValue)
   {
      add(new Configurable(inName, inValue));
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's String value.
    */
   String getInternal(String inName, String inValue)
   {
      Configurable configurable = map.get(inName);

      return ((configurable != null) ?
              (String) configurable.getValue() :
              mParent.getInternal(inName, inValue));
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Integer value.
    */
   Integer getInternal(String inName, int inValue)
   {
      Configurable configurable = map.get(inName);

      return ((configurable != null) ?
              (Integer) configurable.getValue() :
              mParent.getInternal(inName, inValue));
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Float value.
    */
   Float getInternal(String inName, float inValue)
   {
      Configurable configurable = map.get(inName);

      return ((configurable != null) ?
              (Float) configurable.getValue() :
              mParent.getInternal(inName, inValue));
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Double value.
    */
   Double getInternal(String inName, double inValue)
   {
      Configurable configurable = map.get(inName);

      return ((configurable != null) ?
              (Double) configurable.getValue() :
              mParent.getInternal(inName, inValue));
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Character value.
    */
   Character getInternal(String inName, char inValue)
   {
      Configurable configurable = map.get(inName);

      return ((configurable != null) ?
              (Character) configurable.getValue() :
              mParent.getInternal(inName, inValue));
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Boolean value.
    */
   Boolean getInternal(String inName, boolean inValue)
   {
      Configurable configurable = map.get(inName);

      return ((configurable != null) ?
              (Boolean) configurable.getValue() :
              mParent.getInternal(inName, inValue));
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Object value.
    */
   Object getInternal(String inName, Object inValue)
   {
      Configurable configurable = map.get(inName);
      Object rtnval = ((configurable != null) ?
                       configurable.getValue() :
                       mParent.getInternal(inName, inValue));

      try
      {
         Method initMethod = rtnval.getClass().getMethod("init");
         initMethod.invoke(rtnval);
      }
      catch (Exception e)
      {
      }

      return rtnval;
   }

   /**
    * Sets a configuration point's value
    *
    * @param inName the name of the Configurable.
    * @param inValue the value of the Configurable.
    */
   public static void set(String inName, Object inValue)
   {
      Config.find().setInternal(inName, inValue);
   }

   /**
    * Gets the name of a Config object.
    *
    * @return the name of this Config object.
    */
   public static String getName()
   {
      return Config.find().getNameInternal();
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's String value.
    */
   public static String get(String inName, String inValue)
   {
      return Config.find().getInternal(inName, inValue);
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Integer value.
    */
   public static Integer get(String inName, int inValue)
   {
      return Config.find().getInternal(inName, inValue);
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Float value.
    */
   public static Float get(String inName, float inValue)
   {
      return Config.find().getInternal(inName, inValue);
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Double value.
    */
   public static Double get(String inName, double inValue)
   {
      return Config.find().getInternal(inName, inValue);
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Character value.
    */
   public static Character get(String inName, char inValue)
   {
      return Config.find().getInternal(inName, inValue);
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Boolean value.
    */
   public static Boolean get(String inName, boolean inValue)
   {
      return Config.find().getInternal(inName, inValue);
   }

   /**
    * Gets a configuration point by name.
    *
    * @param inName the name of the Configurable.
    * @param inValue the default value of the Configurable.
    *
    * @return the Configurable's Object value.
    */
   public static Object get(String inName, Object inValue)
   {
      return Config.find().getInternal(inName, inValue);
   }

   /**
    * Saves all Config objects by writing them to an output stream.
    *
    * @param os the output stream to be written to.
    */
   public static void save(OutputStream os)
   {
      for (Config config : cConfigs.values())
      {
         config.saveInternal(os);
      }
   }

   /**
    * Saves all Config objects to disk.
    */
   public static void save()
   {
      for (Config config : cConfigs.values())
      {
         config.saveInternal();
      }
   }

   /**
    * Shows all Config objects by saving them to an internal
    * byte array output stream which is then printed to standard out.
    */
   public static void show()
   {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      for (Config config : cConfigs.values())
      {
         config.saveInternal(baos);
         baos.write('\n');
      }

      System.out.println(baos);
      System.out.println();
   }
}

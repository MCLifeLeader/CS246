// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/si/TestConfig.java,v 1.1 2006/06/13 16:13:03 neffr Exp $
package sudoku.si;

import junit.framework.*;

import java.io.*;
import java.net.*;

/**
 * A junit test case for the TestConfig class.
 */
public class TestConfig
   extends TestCase
{
   /**
    * A byte array to store data.
    */
   private byte[] mData;

   /**
    * Constructs a new TestConfig instance.
    */
   public TestConfig()
   {
      mData = new byte[]
         {
            -2,  1,  6, -5,  7,  8, -4,  3,  9,
            -4, -7, -3, -9,  6, -2, -1,  8, -5,
             9, -8, -5, -1,  4,  3, -6, -2, -7,
             5, -6,  4, -2, -8,  1,  7,  9, -3,
            -3,  2,  7, -4, -9, -5,  8,  6, -1,
            -1,  9,  8,  7, -3, -6,  2, -5,  4,
            -8, -3, -9,  6,  1, -7, -5, -4,  2,
            -7,  4, -2, -8,  5, -9, -3, -1, -6,
             6,  5, -1,  3,  2, -4,  9,  7, -8, 
         };
   }

   /**
    * Sets up the test by setting configurables.
    */
   public void setUp()
   {
      Config.set("Data", mData);
      Config.set("RMIRegistryHostAddressSuffix", 225);
   }

   /**
    * Tears down the test and shows the Config object.
    */
   public void tearDown()
   {
      Config.show();
   }

   /**
    * Tests getting an integer using a default value
    * and a true assertion.
    */
   public void testGetInteger1()
   {
      int one = Config.get("one", 1);
      assertEquals(one, 1);
   }

   /**
    * Tests getting an integer using a default value
    * and a false assertion.
    */
   public void testGetInteger2()
   {
      int two = Config.get("two", 2);
      assertFalse(two == 0);
   }

   /**
    * Tests getting a boolean using a default value
    * and a true assertion.
    */
   public void testGetBoolean()
   {
      boolean sbTrue = Config.get("sbTrue", true);
      assertTrue(sbTrue);
   }

   /**
    * Tests getting Person objects (after setting them)
    * and assorted assertions.
    */
   public void testGetPerson()
   {
      Config.set("Mary", new Person("Mary", 20, Person.Gender.Female));

      Person defaultPerson = new Person("unknown", 0, Person.Gender.Male);
      Person p = (Person) Config.get("Mary", defaultPerson);
      assertFalse(p.equals(defaultPerson));
      assertEquals(Config.get("Bill", defaultPerson), defaultPerson);
   }

   /**
    * Tests the Config save operation.
    */
   public void testConfigSave()
   {
      Config.set("NumPlayers", new Integer(12));

      int thirteen = Config.get("NumPlayers", 2) + 1;
      Config.set("NumPlayers", new Integer(thirteen));
      Config.save();

      File savedFile = new File(Config.getFilename());
      assertTrue(savedFile.exists());

      if (Boolean.getBoolean("show"))
      {
         try
         {
            Config.show();
            System.out.println(
               "The configuration shown above should match the one below.");
            System.out.println();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                     new FileInputStream(savedFile)));
            String line = null;

            while ((line = br.readLine()) != null)
            {
               System.out.println(line);
            }

            br.close();
         }
         catch (Exception e)
         {
            // should not happen
         }
      }

      if (! Boolean.getBoolean("keep"))
      {
         savedFile.delete();
      }
   }
}

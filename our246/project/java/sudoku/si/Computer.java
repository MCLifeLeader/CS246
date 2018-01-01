// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/si/Computer.java,v 1.7 2006/06/24 22:19:42 manxman Exp $
package sudoku.si;

import java.io.*;

import java.util.*;

/**
 * Encapsulates a computer's data.
 */
public class Computer
   implements Serializable
{
   /**
    * The Computer's name.
    */
   private String mName;

   /**
    * The Computer's OSName.
    * IE Linux, Windows, Macintosh
    */
   private String mOSName;

   /**
    * The Computer's OS Version.
    */
   private String mOSVersion;

   /**
    * The Computer's Hardware Type
    * IE x86, etc.
    */
   private String mArchType;

   /**
    * Constructs a new Computer instance.
    *
    * @param inName the computer's name.
    * @param inOSName the computer's OS Name.
    * @param inOSVersion the computer's OS Version.
    * @param inArchType the computer's Architecture type.
    */
   public Computer(String inName, String inOSName, String inOSVersion,
      String inArchType)
   {
      mName = inName;
      mOSName = inOSName;
      mOSVersion = inOSVersion;
      mArchType = inArchType;
   }

   /**
    * Creates a new Computer object.
    *
    * @param inName the computer's name.
    */
   public Computer(String inName)
   {
      Properties pr = java.lang.System.getProperties();

      mName = inName;

      mOSName = pr.getProperty("os.name");
      mOSVersion = pr.getProperty("os.version");
      mArchType = pr.getProperty("os.arch");
   }

   /**
    * Creates a new Computer object.
    */
   public Computer()
   {
      //intentionally left empty
   }

   /**
    * Returns a readable representation of the computer.
    *
    * @return the computer's name as a string.
    */
   public String toString()
   {
      return mName;
   }

   /**
    * Tests for equality of two computer objects.
    *
    * @param o the other computer object to compare with.
    *
    * @return whether or not two computer objects have the
    *         same name, OS name, OS version, and processor type.
    */
   public boolean equals(Object o)
   {
      boolean rtnval = false;

      try
      {
         Computer computer = (Computer) o;
         rtnval = ((mName.equals(computer.mName) &&
            (mOSName.equals(computer.mOSName)) &&
            (mOSVersion.equals(computer.mOSVersion)) &&
            (mArchType.equals(computer.mArchType))));
      }
      catch (Exception e)
      {
      }

      return rtnval;
   }

   /**
    * Gets the computer's name.
    *
    * @return the name.
    */
   public String getName()
   {
      return mName;
   }

   /**
    * Sets the computer's name.
    *
    * @param inName the computer's new name.
    */
   public void setName(String inName)
   {
      mName = inName;
   }

   /**
    * Gets the OS Name.
    *
    * @return the name.
    */
   public String getOSName()
   {
      return mOSName;
   }

   /**
    * Sets the OS name.
    *
    * @param inOSName the OS name.
    */
   public void setOSName(String inOSName)
   {
      mOSName = inOSName;
   }

   /**
    * Gets the OS version.
    *
    * @return the OS version.
    */
   public String getOSVersion()
   {
      return mOSVersion;
   }

   /**
    * Sets the OS version.
    *
    * @param inOSVersion the OS version.
    */
   public void setOSVersion(String inOSVersion)
   {
      mOSVersion = inOSVersion;
   }

   /**
    * Gets the Hardware Architecture type.
    *
    * @return the Hardware Architecture type.
    */
   public String getArchType()
   {
      return mArchType;
   }

   /**
    * Sets the Hardware Architecture type.
    *
    * @param inArchType the Hardware Architecture type.
    */
   public void setArchType(String inArchType)
   {
      mArchType = inArchType;
   }
}

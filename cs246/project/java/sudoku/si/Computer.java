// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/si/Computer.java,v 1.3 2006/06/16 02:33:54 neffr Exp $
package sudoku.si;

import java.util.*;

/**
 * Encapsulates a computer's data.
 */
public class Computer
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
    * IE Pentinum IV, AMD 2600, etc.
    */
   private String mProcType;

   /**
    * Constructs a new Computer instance.
    *
    * @param inName the computer's name.
    * @param inOSName the computer's OS Name.
    * @param inOSVersion the computer's OS Version.
    * @param inProcType the computer's processor type.
    */
   public Computer(String inName, String inOSName, String inOSVersion,
                   String inProcType)
   {
      mName = inName;
      mOSName = inOSName;
      mOSVersion = inOSVersion;
      mProcType = inProcType;
   }

   /**
    * Creates a new Computer object.
    *
    * @param inName the computer's name.
    */
   public Computer(String inName)
   {
      mName = inName;

      //TODO: Find functions to fill these variables.
      //mOSName = ?;
      //mOSVersion = ?;
      //mProcType = ?;
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
                    (mProcType.equals(computer.mProcType))));
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
    * Gets the processor type.
    *
    * @return the processor type.
    */
   public String getProcType()
   {
      return mProcType;
   }

   /**
    * Sets the processor type.
    *
    * @param inProcType the processor type.
    */
   public void setProcType(String inProcType)
   {
      mProcType = inProcType;
   }
}

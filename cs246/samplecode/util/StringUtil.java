// $Header: /usr/local/cvsroot/courses/cs246/samplecode/util/StringUtil.java,v 1.1 2006/06/05 17:16:54 neffr Exp $

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

/**
 * Provides several utility methods for converting Strings to
 * arrays or Collections or Objects and back again.
 *
 * @author Brother Neff
 */
public class StringUtil
{
   /**
    * Returns a collection of objects whose class
    * name are given in a String array.
    *
    * @param args the String array of class names
    *
    * @return the Collection of newly-instantiated objects.
    */
   public static Collection toCollection(String[] args)
   {
      Collection rtnval = new ArrayList();
      for (String arg : args)
      {
         Object o = toObject(arg);
         if (o != null)
         {
            rtnval.add(o);
         }
      }
      return rtnval;
   }

   /**
    * Returns a newly-instantiated object of the class
    * whose name is passed.
    *
    * @param name the name of the class to be dynamically
    *             loaded and instantiated.
    * @return the newly-instantiated object, or null if
    *         no such object/class can be created/loaded.
    */
   public static Object toObject(String name)
   {
      Object rtnval = null;
      try
      {
         // 1. find the Class for the Name name (the argument passed)
         // 2. make a new instance of that Class and assign it to rtnval
         rtnval = Class.forName(name).newInstance();
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
      return rtnval;
   }

   /**
    * Returns a String formed by "smooshing" all the String args
    * together with one space separating them.
    *
    * @param args the array of String objects.
    *
    * @return the String so formed.
    */
   public static String toString(String[] args)
   {
      StringBuffer buffer = new StringBuffer();
      for (String s : args)
      {
         buffer.append(s);
         buffer.append(' ');
      }
      return buffer.toString().substring(0, buffer.length() - 1);
   }

   /**
    * Returns an array of Strings formed by splitting the String
    * argument into tokens (space separated) which become the
    * array elements.
    *
    * @param arg the String to split
    *
    * @return the array so formed.
    */
   public static String[] toArray(String arg)
   {
      // "abc def" => ["abc", "def"]
      StringTokenizer tokenizer = new StringTokenizer(arg);
      String[] rtnval = new String[tokenizer.countTokens()];
      int i = 0;
      while (tokenizer.hasMoreTokens())
      {
         rtnval[i++] = tokenizer.nextToken();
      }
      return rtnval;
   }
}

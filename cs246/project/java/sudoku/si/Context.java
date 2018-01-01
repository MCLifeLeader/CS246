// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/si/Context.java,v 1.2 2006/06/16 21:36:33 neffr Exp $
package sudoku.si;

import java.io.*;

/**
 * "Struct" class (with public members) that knows how to extract a
 * method calling context (method name, class name, package name)
 * from an Exception stack trace.
 */
public class Context
{
   /**
    * The package name.
    */
   public String atPackage;

   /**
    * The class name.
    */
   public String atClass;

   /**
    * The method name.
    */
   public String atMethod;

   /**
    * Creates a new Context object.
    *
    * @param inPackage the package name.
    * @param inClass the class name.
    * @param inMethod the method name.
    */
   public Context(String inPackage, String inClass, String inMethod)
   {
      atPackage = inPackage;
      atClass = inClass;
      atMethod = inMethod;
   }

   /**
    * Gets the Context from an exception stack trace.
    *
    * @param name the name of the context.
    *
    * @return the Context.
    */
   public static Context get(String name)
   {
      StringWriter sw = new StringWriter();
      new Exception().printStackTrace(new PrintWriter(sw));

      String s = sw.toString();
      int at = s.indexOf("at");
      String line = null;

      while (at != -1)
      {
         int closeParenIndex = s.indexOf(")", at);
         line = s.substring(at + 3, closeParenIndex + 1);

         if ((! line.startsWith(name)) &&
             (! line.startsWith(Context.class.getName())) &&
             (! line.startsWith("sun.reflect.")) &&
             (! line.startsWith("java.lang.reflect.")) &&
             (! line.startsWith("junit")))
         {
            int openParenIndex = line.indexOf("(");
            int lastDotIndex = line.lastIndexOf(".", openParenIndex);

            return new Context("", line.substring(0, lastDotIndex), line);
         }

         at = s.indexOf("at", closeParenIndex);
      }

      return new Context(getPackageName(Context.class),
                         Context.class.getName(), "");
   }

   /**
    * Gets the package name of a class.
    * First tries to get the Package from the Class,
    * but this fails with the graphical JUnit (it
    * returns null) because of ClassLoader differences.
    *
    * Second tries to parse the package name from the class name.
    *
    * @param inClass the class.
    */
   public static String getPackageName(Class inClass)
   {
      Package thePackage = inClass.getPackage();
      String className = inClass.getName();
      String packageName = null;
      if (thePackage == null)
      {
         packageName = className.substring(0, className.lastIndexOf("."));
      }
      else
      {
         packageName = thePackage.getName();
      }
      return packageName;
   }
}

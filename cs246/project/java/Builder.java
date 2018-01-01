// $Header: /usr/local/cvsroot/courses/cs246/project/java/Builder.java,v 1.4 2006/06/16 20:09:43 owe04013 Exp $

// package default (intentionally)
import java.io.*;

import java.util.*;
import junit.framework.*;

/**
 * Builds java code modularly.
 *
 * The Builder finds all .java files in the current working directory and
 * all of its subdirectories. It creates a JAR file from the .class files
 * resulting from compiling these .java files. If there is already a JAR
 * file, the class will compile all .java files newer than the JAR and
 * will then build the JAR. Builder implements FilenameFilter so it can
 * be the object passed to the File.listFiles method which finds the
 * files to be built.  The implementation of the accept() method filters
 * in files (ending in ".java") that are newer than the JAR file, thereby
 * minimizing the work of the Builder in compiling code.
 * <p>
 * The Builder works in two modes, <b>module</b> and <b>all</b>.
 * To build a module, connect to its directory first and then say
 * "java Builder".  To build all modules, connect to the project root
 * directory (the one that has the "java" subdirectory in it) and then say
 * "java Builder".  The current directory context is what allows
 * the Builder to distinguish these two modes.
 * <p>
 * The Builder is sensitive to the directory structure.  It requires all
 * java code to be found in the "java" subdirectory (and only .java files
 * should be put there).  The tree under the "java" directory should be
 * two deep before finding .java files (with the sole exception of
 * Builder.java, which is in the default package and therefore belongs at
 * java/Builder.java).
 *
 * @author Rick Neff
 */
public class Builder
   extends TestSuite
   implements FilenameFilter
{
   /**
    * Constant string file extension for .java files.
    */
   final String JAVA_EXTENSION = ".java";

   /**
    * Constant string file extension for .class files.
    */
   final String CLASS_EXTENSION = ".class";

   /**
    * The array of command-line arguments.
    */
   String[] mArgs;

   /**
    * The current working directory.
    */
   File mDir;

   /**
    * The jar file to be created.
    */
   File mJarFile;

   /**
    * The <parent>/java directory.
    */
   File mJavaDir;

   /**
    * The <parent>/class/<whatever> directory.
    */
   File mClassDir;

   /**
    * The parent of the current working directory.
    */
   File mParentDir;

   /**
    * The "inner" module name (e.g., "ui").
    */
   String mInnerModuleName;

   /**
    * The "outer" module name (e.g., "sudoku").
    */
   String mOuterModuleName;

   /**
    * The module name (outer + "." + inner module names).
    */
   String mModuleName;

   
   /**
    * The arguments for javac.
    */
   Collection<String> mJavacArgs;

   
   /**
    * All source files needing to be built.
    */
   Collection<String> mJavaSourceFiles;

   /**
    * Whether or not to show help.
    */
   boolean mShowHelp;

   /**
    * Whether or not to test.
    */
   boolean mTest;

   /**
    * Whether or not to debug.
    */
   boolean mDebug;

   /**
    * Whether or not to warn.
    */
   boolean mWarn;

   /**
    * Whether or not to build all.
    */
   boolean mBuildAll;

   /**
    * Whether or not to build javadocs.
    */
   boolean mBuildJavadocs;

   /**
    * Whether or not to build the jar.
    */
   String mBuildJar;

   /**
    * The file extension (defaults to JAVA_EXTENSION).
    */
   String mFileExtension = JAVA_EXTENSION;

   
   /**
    * The map of RMI classes.
    */
   Map<String, String> mRMIClasses;

   
   /**
    * The map of module dependencies.
    */
   Map<String, Set<String>> mModuleDependencies;

   /**
    * name of the DEFAULT Keystore to use
    */
   static final String KEYSTORE_DEFAULT = "config/sudokuKeystore";

   /**
    * name of the DEFAULT alias to use
    */
   static final String ALIAS_DEFAULT = "myself";

   /**
    * the DEFAULT password for the Keystore
    */
   static final String STORE_PASS_DEFAULT = "sudoku";

   /**
    * the DEFAULT password for the alias in the Keystore
    */
   static final String ALIAS_PASS_DEFAULT = "sudoku";

   /**
    * the properties for the builder
    */
   private Properties builderProperties;

   /**
    * Instantiates a Builder and calls its run method.
    * Then calls System.exit(0);
    *
    * @param args the command-line arguments.
    */
   public static void main(String[] args)
   {
      new Builder(args).build();
      System.exit(0);
   }

   /**
    * Runs the constructed test suite.
    */
   public static Test suite()
   {
      return new Builder().build();
   }

   /**
    * Constructs a new Builder instance.
    * Creates the test suite.
    */
   public Builder()
   {
      this(new String[]{ "test" });
   }

   /**
    * Constructs a new Builder instance.
    * Initializes collections.
    *
    * @param args the command-line arguments.
    */
   public Builder(String[] args)
   {
      mArgs = args;
      mJavacArgs = new ArrayList<String>();
      mJavaSourceFiles = new ArrayList<String>();
      mDir = new File(System.getProperty("user.dir"));
      mRMIClasses = new HashMap<String, String>();
      mModuleDependencies = new HashMap<String, Set<String>>();
      mBuildAll = new File(mDir, "java").isDirectory();
      mBuildJavadocs = false;
      mShowHelp = false;
      mTest = false;
      mDebug = false;
      mWarn = false;
      mBuildJar = null;
      builderProperties = new Properties();

      try
      {
         builderProperties.load(new FileInputStream(
               new File("config/builder.Config")));
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Builds various things.
    * Calls methods to initialize variables and build the module
    */
   public Builder build()
   {
      init();

      try
      {
         if (mShowHelp)
         {
            showHelp();
         }
         else if (mTest)
         {
            addTests();
         }
         else if (mBuildJar != null)
         {
            buildJar(mBuildJar);
         }
         else if (mBuildJavadocs)
         {
            buildJavadocs();
         }
         else if (mBuildAll)
         {
            buildAll();
         }
         else
         {
            buildOne(null);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

      return this;
   }

   /**
    * Initializes the Builder instance.
    * Sets its member variables for use in other methods.
    * Parses command-line arguments.
    */
   private void init()
   {
      setVariables(null);

      // parse command line arguments
      for (String arg : mArgs)
      {
         if (arg.equalsIgnoreCase("debug"))
         {
            mDebug = true;
         }
         else if (arg.equalsIgnoreCase("warn"))
         {
            mWarn = true;
         }
         else if (arg.equalsIgnoreCase("jdoc"))
         {
            mBuildJavadocs = true;
         }
         else if (arg.endsWith(".jar"))
         {
            mBuildJar = arg;
         }
         else if (arg.equalsIgnoreCase("help"))
         {
            mShowHelp = true;
         }
         else if (arg.equalsIgnoreCase("test"))
         {
            mTest = true;
         }
      }
   }

   /**
    * Sets state/context variables.
    *
    * @param moduleName the module name.
    */
   private void setVariables(String moduleName)
   {
      if (moduleName != null)
      {
         mDir = new File(mJavaDir, moduleName.replace('.', '/'));
      }

      // set JAR name to <directory name>.jar
      mParentDir = new File(mDir.getParent());

      String jarFilename = mParentDir.getName() + mDir.getName() + ".jar";

      mJarFile = new File(jarFilename);
      mJavaDir = new File(mDir, "java");

      // looks up the directory tree until it finds either a "java"
      // directory or a file that matches mJarFile
      while (! mJarFile.canWrite() && ! mJavaDir.isDirectory())
      {
         mJarFile = new File(mParentDir, mJarFile.getName());
         mJavaDir = new File(mParentDir, "java");
         mClassDir = new File(mParentDir, "class");
         mParentDir = mParentDir.getParentFile();
      }

      // save the module names for later
      File tmp = new File(mDir.getParent());
      mOuterModuleName = tmp.getName();
      mInnerModuleName = mDir.getName();
      mModuleName = mOuterModuleName + "." + mInnerModuleName;
   }

   /**
    * Shows help/usage information.
    */
   private void showHelp()
   {
      System.out.println("Usage: java Builder [options]\n" + " Options are:\n"
         + "   (none)          Build all modules if in project"
         + " directory, otherwise build current module.\n"
         + "   jdoc            Build javadocs.\n"
         + "   debug           Build java code with debugging enabled.\n"
         + "   warn            Build java code with compiler warnings enabled.\n"
         + "   something.jar   Build the something.jar file using the"
         + " config/something.mf manifest file.\n"
         + "   help            Show this help.");
   }

   /**
    * Builds all modules.
    *
    * @throws Exception if any source files cannot be read.
    */
   public void buildAll() throws Exception
   {
      String packageOuterName = getPackageModuleDependencies();

      // System.out.println(mModuleDependencies);
      List<String> buildOrder = getModuleBuildOrder();

      for (String module : buildOrder)
      {
         StringBuffer jarFilename = new StringBuffer(module);
         jarFilename.deleteCharAt(module.indexOf(".")).append(".jar");

         File jarFile = new File(jarFilename.toString());
         jarFile.delete();
      }

      // remove all .class files under class/<packageOuterName>
      File classModuleSubDir = new File("class/" + packageOuterName);
      mFileExtension = CLASS_EXTENSION;
      deleteClassFiles(classModuleSubDir);
      mFileExtension = JAVA_EXTENSION;

      for (String module : buildOrder)
      {
         buildOne(module);
      }
   }

   /**
    * Gets package module dependencies.
    *
    * @return package module dependencies as a string.
    *
    * @throws Exception if any source files cannot be read.
    */
   private String getPackageModuleDependencies() throws Exception
   {
      mJavaSourceFiles.clear();
      findFiles(mDir);

      String packageOuterName = null;

      for (String f : mJavaSourceFiles)
      {
         BufferedReader br = new BufferedReader(new InputStreamReader(
                  new FileInputStream(f)));
         String line = null;

         String packageName = null;
         Set<String> dependencySet = null;
         StringBuffer outerName = new StringBuffer();
         StringBuffer innerName = new StringBuffer();
         String packageInnerName = null;

         while ((line = br.readLine()) != null)
         {
            // TODO: use regex here
            if (line.startsWith("package "))
            {
               packageName = line.substring("package ".length());
               packageName = packageName.substring(0, packageName.length() - 1);

               getTwoPartPackageName(packageName, outerName, innerName);
               packageOuterName = outerName.toString();
               packageInnerName = innerName.toString();
               packageName = packageOuterName + "." + packageInnerName;
               dependencySet = mModuleDependencies.get(packageName);

               if (dependencySet == null)
               {
                  // not already in, so put it in
                  mModuleDependencies.put(packageName, new HashSet<String>());
               }
            }
            else if (line.startsWith("import "))
            {
               dependencySet = mModuleDependencies.get(packageName);

               if (dependencySet != null)
               {
                  String dependency = line.substring("import ".length());
                  int lastDotIndex = dependency.lastIndexOf(".");

                  if (lastDotIndex != -1)
                  {
                     dependency = dependency.substring(0, lastDotIndex);
                     getTwoPartPackageName(dependency, outerName, innerName);
                     dependency = (outerName.toString() + "."
                        + innerName.toString());

                     if (dependency.startsWith(packageOuterName))
                     {
                        if (! dependency.startsWith(packageName))
                        {
                           dependencySet.add(dependency);
                        }
                     }
                  }
               }
               else
               {
                  System.out.println(f + ": " + line);
               }
            }
            else if (line.startsWith("   extends UnicastRemoteObject"))
            {
               String name = packageName + "." + new File(f).getName();
               mRMIClasses.put(f.toString(), name.replaceAll("[.]java", ""));
            }
         }

         br.close();
      }

      return packageOuterName;
   }

   /**
    * Gets the module build order.
    *
    * @return the module build order as an ordered list of string names.
    */
   private List<String> getModuleBuildOrder()
   {
      List<String> rtnval = new ArrayList<String>();

      // first priority to those modules with zero dependents
      for (String s : mModuleDependencies.keySet())
      {
         Set<String> dependents = mModuleDependencies.get(s);

         if (dependents.size() == 0)
         {
            rtnval.add(s);
         }
      }

      // remove any reference to zero-dependent modules
      for (Set<String> ss : mModuleDependencies.values())
      {
         for (String s : rtnval)
         {
            ss.remove(s);
         }
      }

      // next priority to those modules with mutual dependencies
      for (String s : mModuleDependencies.keySet())
      {
         Set<String> dependents = mModuleDependencies.get(s);

         if (dependents.size() == 1)
         {
            String[] array = dependents.toArray(new String[0]);
            String ds = array[0];
            Set<String> dsDependents = mModuleDependencies.get(ds);

            if ((dsDependents != null) && (dsDependents.size() == 1))
            {
               array = dsDependents.toArray(array);

               if (array[0].equals(s))
               {
                  rtnval.add(s);
               }
            }
         }
      }

      // remove from consideration whatever is in rtnval currently
      Iterator<String> it = mModuleDependencies.keySet().iterator();

      while (it.hasNext())
      {
         String next = it.next();

         if (rtnval.contains(next))
         {
            it.remove();
         }
      }

      // finally, add whatever is left, removing it from
      // the module dependencies in the process
      it = mModuleDependencies.keySet().iterator();

      while (it.hasNext())
      {
         String next = it.next();
         rtnval.add(next);
         it.remove();
      }

      return rtnval;
   }

   /**
    * Gets the two-part package name.
    *
    * @param packageName the full package name.
    * @param outerName where to put the outer name part
    *                  of the full package name.
    * @param innerName where to put the inner name part
    *                  of the full package name.
    */
   private void getTwoPartPackageName(String packageName,
      StringBuffer outerName, StringBuffer innerName)
   {
      outerName.setLength(0);
      innerName.setLength(0);

      int dot1 = packageName.indexOf(".");

      if (dot1 == -1)
      {
         outerName.append(packageName);
      }
      else
      {
         outerName.append(packageName.substring(0, dot1));

         int dot2 = packageName.indexOf(".", dot1 + 1);

         if (dot2 == -1)
         {
            innerName.append(packageName.substring(dot1 + 1));
         }
         else
         {
            innerName.append(packageName.substring(dot1 + 1, dot2));
         }
      }
   }

   /**
    * Finds the .java files that have changed, builds them,
    * and updates the JAR file for this module.
    *
    * @param module the module name.
    *
    * @throws Exception if file.getCanonicalPath generates an IOException
    */
   public void buildOne(String module) throws Exception
   {
      if (module != null)
      {
         setVariables(module);
      }

      // gets all the .java files that are newer than the current JAR
      mJavaSourceFiles.clear();
      findFiles(mDir);

      // see if nothing is newer than current JAR
      if (mJavaSourceFiles.isEmpty())
      {
         System.out.println("\nNothing needs building in the " + mModuleName
            + " module.");
      }
      else // compile all the newer .java files
      {
         System.out.println("\nBuilding " + mModuleName + " module:");

         int len = mDir.getCanonicalPath().length();

         for (String sourceFilename : mJavaSourceFiles)
         {
            System.out.print(sourceFilename.substring(len + 1) + " ");
         }

         System.out.println();

         String jfParentDir = mJarFile.getParent();
         File rootDir = new File((jfParentDir != null) ? jfParentDir : ".");
         String rootPath = rootDir.getAbsolutePath();
         String classPath = rootPath + "/class";
         new File(rootDir, "class").mkdirs();

         // initialize the compiler arguments
         if (mDebug)
         {
            mJavacArgs.add("-g");
         }

         if (mWarn)
         {
            mJavacArgs.add("-Xlint:deprecation");
            mJavacArgs.add("-Xlint:unchecked");
         }

         mJavacArgs.add("-extdirs");
         mJavacArgs.add(rootPath + "/lib");
         mJavacArgs.add("-classpath");
         mJavacArgs.add(classPath);
         mJavacArgs.add("-sourcepath");
         mJavacArgs.add(rootPath + "/java");
         mJavacArgs.add("-d");
         mJavacArgs.add(classPath);
         mJavacArgs.addAll(mJavaSourceFiles);

         // call javac tool's Main class static compile method
         String[] javacArgs = (String[]) mJavacArgs.toArray(new String[0]);
         int compilerExitStatus = com.sun.tools.javac.Main.compile(javacArgs);

         // if compilation is error-free, add files to JAR
         if (compilerExitStatus == 0)
         {
            boolean status = false;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);

            // first see if there are any RMI classes
            // to compile in this module
            List<String> rmiArgsList = new ArrayList<String>();
            List<String> rmiClassesList = new ArrayList<String>();

            for (String javaSourceFile : mRMIClasses.keySet())
            {
               if (mJavaSourceFiles.contains(javaSourceFile))
               {
                  rmiClassesList.add(mRMIClasses.get(javaSourceFile));
               }
            }

            if (! rmiClassesList.isEmpty())
            {
               rmiArgsList.add("-classpath");
               rmiArgsList.add(classPath);
               rmiArgsList.add("-d");
               rmiArgsList.add(classPath);

               rmiArgsList.addAll(rmiClassesList);

               String[] rmiArgs = (String[]) rmiArgsList.toArray(new String[0]);

               // call jar tool's Main class run method
               // on a newly constructed Main object
               sun.rmi.rmic.Main rmiMain = new sun.rmi.rmic.Main(ps, "rmic");

               status = rmiMain.compile(rmiArgs);

               System.out.println("rmic " + rmiClassesList
                  + (status ? " succeeded." : " failed."));
            }

            // call jar tool's Main class run method
            // on a newly constructed Main object
            sun.tools.jar.Main jarMain = new sun.tools.jar.Main(ps, System.err,
                  "jar");

            status = jarMain.run(new String[]
                  {
                     "cvf", mJarFile.getCanonicalPath(), "-C", classPath,
                     mOuterModuleName + File.separator + mInnerModuleName
                  });
            System.out.print("module jar file");
            System.out.print(status ? " " : " NOT ");
            System.out.println("created.");

            if (status)
            {
               sign(mJarFile.getCanonicalPath());
            }
         }
         else
         {
            System.exit(compilerExitStatus);
         }
      }
   }

   /**
    * Finds .java files in the current directory and
    * recursively through all its subdirectories.
    *
    * @param inFile the file/directory to start searching in.
    */
   public void findFiles(File inFile)
   {
      if (inFile.isDirectory()) //get all files in directory
      {
         //This class implements FilenameFilter. For information
         //on accepted files, view accept()
         File[] files = inFile.listFiles(this);

         if (files != null)
         {
            for (File file : files)
            {
               findFiles(file);
            }
         }
      }
      else //add inFile to list of files to be compiled
      {
         try
         {
            mJavaSourceFiles.add(inFile.getCanonicalPath());
         }
         catch (Exception e)
         {
            System.out.println(e);
         }
      }
   }

   /**
    * Deletes .class files in the current directory and
    * recursively through all its subdirectories.
    *
    * @param inFile the file/directory to start searching in.
    */
   private void deleteClassFiles(File inFile)
   {
      if (inFile.isDirectory()) //get all files in directory
      {
         // this class implements FilenameFilter. For information
         // on accepted files, see accept()
         File[] files = inFile.listFiles(this);

         if (files != null)
         {
            for (File file : files)
            {
               deleteClassFiles(file);
            }
         }
      }
      else if (inFile.getName().endsWith(".class"))
      {
         inFile.delete();
      }
   }

   /**
    * Accepts files.
    * Implementation for the FileNameFilter interface,
    * Determines which files are accepted, namely, .java
    * (or .class) files newer than the module JAR file.
    *
    * @param   dir    the directory in which the file was found.
    * @param   name   the name of the file.
    *
    * @return  <code>true</code> if and only if the name should be
    * included in the file list; <code>false</code> otherwise.
    */
   public boolean accept(File dir, String name)
   {
      File file = new File(dir, name);

      return (file.isDirectory()
      || (! name.equals("Builder.java") && name.endsWith(mFileExtension)
      && (file.lastModified() > mJarFile.lastModified())));
   }

   /**
    * Adds tests.
    *
    * @throws Exception if something goes wrong.
    */
   private void addTests() throws Exception
   {
      String packageOuterName = getPackageModuleDependencies();

      List<String> buildOrder = getModuleBuildOrder();

      for (String module : buildOrder)
      {
         String testName = module + ".TestModule";
         addTest((TestSuite) Class.forName(testName).newInstance());
      }
   }

   /**
    * Builds jar file.
    *
    * @param jarFilename the name of the jar file to build.
    *
    * @throws Exception if something goes wrong.
    */
   private void buildJar(String jarFilename) throws Exception
   {
      String fs = File.separator;
      File jarFile = new File(jarFilename);
      // Rename jarFilename to its name without the file extension
      // For example, "foo.jar" becomes "foo"
      jarFilename = jarFilename.replaceAll("[.]jar", "");

      String manifestName = "config" + fs + jarFilename + ".mf";

      Collection<String> jarArgs = new ArrayList<String>();
      jarArgs.add("cvfm");
      jarArgs.add(jarFile.getName());
      jarArgs.add(manifestName);

      String line = null;
      BufferedReader br = new BufferedReader(new InputStreamReader(
               new FileInputStream(manifestName)));

      while ((line = br.readLine()) != null)
      {
         if (line.startsWith("Other-Resources:"))
         {
            line = line.substring(line.indexOf(":") + 1);

            break;
         }
      }

      if (line != null)
      {
         StringTokenizer tokenizer = new StringTokenizer(line);

         while (tokenizer.hasMoreTokens())
         {
            jarArgs.add(tokenizer.nextToken());
         }
      }

      // convert jarArgs to a String array for execution
      String[] jarArgsArray = (String[]) jarArgs.toArray(new String[0]);

      // invoke jar tool Main class main method
      sun.tools.jar.Main.main(jarArgsArray);
      sign(jarFilename + ".jar");
   }

   /**
    * Builds javadocs.
    *
    * @throws Exception if something goes wrong.
    */
   private void buildJavadocs() throws Exception
   {
      String packageOuterName = getPackageModuleDependencies();

      Collection<String> jdocArgs = new ArrayList<String>();

      // initialize jdocArgs
      jdocArgs.add("-quiet");
      jdocArgs.add("-author");
      jdocArgs.add("-link");
      jdocArgs.add("http://java.sun.com/j2se/1.5.0/docs/api/");
      jdocArgs.add("-d");
      jdocArgs.add("jdoc");
      jdocArgs.add("-sourcepath");
      jdocArgs.add("java");
      jdocArgs.add("-classpath");
      jdocArgs.add("class");
      jdocArgs.add("-extdirs");
      jdocArgs.add("lib");
      jdocArgs.add("-private");
      jdocArgs.add("java/Builder.java");
      jdocArgs.add("-subpackages");

      StringBuffer subpackages = new StringBuffer();

      for (String module : getModuleBuildOrder())
      {
         subpackages.append(module);
         subpackages.append(':');
      }

      jdocArgs.add(subpackages.toString());

      System.out.print("Building javadocs for " + packageOuterName);
      System.out.println(" using " + jdocArgs);

      // Convert jdocsArgs to a String array for execution
      String[] jdocArgsArray = (String[]) jdocArgs.toArray(new String[0]);

      com.sun.tools.javadoc.Main.execute(jdocArgsArray);
   }

   /**
    * Signs jars
    */
   private void sign(String filename)
   {
      try
      {
         if (! filename.endsWith(".jar"))
         {
            filename = filename + ".jar";
         }

         if (new File(filename).exists())
         {
            Collection<String> jarSignerArgs = new ArrayList<String>();
            jarSignerArgs.add("-keystore");
            jarSignerArgs.add(builderProperties.getProperty("keystore",
                  KEYSTORE_DEFAULT));
            jarSignerArgs.add("-storepass");
            jarSignerArgs.add(builderProperties.getProperty("StorePass",
                  STORE_PASS_DEFAULT));
            jarSignerArgs.add("-keypass");
            jarSignerArgs.add(builderProperties.getProperty("AliasPass",
                  ALIAS_PASS_DEFAULT));
            jarSignerArgs.add(filename);
            jarSignerArgs.add(builderProperties.getProperty("Alias",
                  ALIAS_DEFAULT));

            // Convert jarSignerArgs to a String array for execution
            String[] jarSignerArgsArray = (String[]) jarSignerArgs.toArray(new String[0]);
            sun.security.tools.JarSigner.main(jarSignerArgsArray);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}

import junit.framework.*;
import java.io.*;
import java.util.*;
/*
 * BuilderTest.java
 * JUnit based test
 *
 * Created on June 26, 2006, 1:57 PM
 */

/**
 *
 * @author mbcarey
 */
public class BuilderTest extends TestCase
{
   
   public BuilderTest(String testName)
   {
      super(testName);
   }

   protected void setUp() throws Exception
   {
   }

   protected void tearDown() throws Exception
   {
   }

   public static Test suite()
   {
      TestSuite suite = new TestSuite(BuilderTest.class);
      
      return suite;
   }

   /**
    * Test of main method, of class Builder.
    */
   public void testMain()
   {
      System.out.println("main");
      
      String[] args = null;
      
      Builder.main(args);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of suite method, of class Builder.
    */
   public void testSuite()
   {
      System.out.println("suite");
      
      Test expResult = null;
      Test result = Builder.suite();
      assertEquals(expResult, result);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of build method, of class Builder.
    */
   public void testBuild()
   {
      System.out.println("build");
      
      Builder instance = new Builder();
      
      Builder expResult = null;
      Builder result = instance.build();
      assertEquals(expResult, result);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of buildAll method, of class Builder.
    */
   public void testBuildAll() throws Exception
   {
      System.out.println("buildAll");
      
      Builder instance = new Builder();
      
      instance.buildAll();
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of buildOne method, of class Builder.
    */
   public void testBuildOne() throws Exception
   {
      System.out.println("buildOne");
      
      String module = "";
      Builder instance = new Builder();
      
      instance.buildOne(module);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of findFiles method, of class Builder.
    */
   public void testFindFiles()
   {
      System.out.println("findFiles");
      
      File inFile = null;
      Builder instance = new Builder();
      
      instance.findFiles(inFile);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of accept method, of class Builder.
    */
   public void testAccept()
   {
      System.out.println("accept");
      
      File dir = null;
      String name = "";
      Builder instance = new Builder();
      
      boolean expResult = true;
      boolean result = instance.accept(dir, name);
      assertEquals(expResult, result);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }
   
}

import junit.framework.*;
import java.io.*;
import java.util.*;
/*
 * SignerUtilTest.java
 * JUnit based test
 *
 * Created on June 26, 2006, 1:57 PM
 */

/**
 *
 * @author mbcarey
 */
public class SignerUtilTest extends TestCase
{
   
   public SignerUtilTest(String testName)
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
      TestSuite suite = new TestSuite(SignerUtilTest.class);
      
      return suite;
   }

   /**
    * Test of setKeystore method, of class SignerUtil.
    */
   public void testSetKeystore()
   {
      System.out.println("setKeystore");
      
      String pathOfKeystore = "";
      SignerUtil instance = new SignerUtil();
      
      instance.setKeystore(pathOfKeystore);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getKeystore method, of class SignerUtil.
    */
   public void testGetKeystore()
   {
      System.out.println("getKeystore");
      
      SignerUtil instance = new SignerUtil();
      
      String expResult = "";
      String result = instance.getKeystore();
      assertEquals(expResult, result);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of setKeystorePassword method, of class SignerUtil.
    */
   public void testSetKeystorePassword()
   {
      System.out.println("setKeystorePassword");
      
      String KeystorePassword = "";
      SignerUtil instance = new SignerUtil();
      
      instance.setKeystorePassword(KeystorePassword);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getKeystorePassword method, of class SignerUtil.
    */
   public void testGetKeystorePassword()
   {
      System.out.println("getKeystorePassword");
      
      SignerUtil instance = new SignerUtil();
      
      String expResult = "";
      String result = instance.getKeystorePassword();
      assertEquals(expResult, result);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of setAliasPassword method, of class SignerUtil.
    */
   public void testSetAliasPassword()
   {
      System.out.println("setAliasPassword");
      
      String AliasPassword = "";
      SignerUtil instance = new SignerUtil();
      
      instance.setAliasPassword(AliasPassword);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getAliasPassword method, of class SignerUtil.
    */
   public void testGetAliasPassword()
   {
      System.out.println("getAliasPassword");
      
      SignerUtil instance = new SignerUtil();
      
      String expResult = "";
      String result = instance.getAliasPassword();
      assertEquals(expResult, result);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of setAlias method, of class SignerUtil.
    */
   public void testSetAlias()
   {
      System.out.println("setAlias");
      
      String Alias = "";
      SignerUtil instance = new SignerUtil();
      
      instance.setAlias(Alias);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getAlias method, of class SignerUtil.
    */
   public void testGetAlias()
   {
      System.out.println("getAlias");
      
      SignerUtil instance = new SignerUtil();
      
      String expResult = "";
      String result = instance.getAlias();
      assertEquals(expResult, result);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of setProperty method, of class SignerUtil.
    */
   public void testSetProperty()
   {
      System.out.println("setProperty");
      
      String key = "";
      String value = "";
      SignerUtil instance = new SignerUtil();
      
      instance.setProperty(key, value);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of sign method, of class SignerUtil.
    */
   public void testSign()
   {
      System.out.println("sign");
      
      String filename = "";
      SignerUtil instance = new SignerUtil();
      
      instance.sign(filename);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }
   
}

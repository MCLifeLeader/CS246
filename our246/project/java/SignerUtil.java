// $Header: /usr/local/cvsroot/courses/cs246/project/java/SignerUtil.java,v 1.2 2006/06/21 00:09:45 owe04013 Exp $

// package default (intentionally)
import java.io.*;

import java.util.*;

/**
 * a util that can be used to sign jar files
 *
 * @author steven owens
 * @version 1.2
  */
public class SignerUtil
{
   /**
    * the properties for the signer
    */
   private Properties signerProperties;

   /**
    * Creates a new SignerUtil object.
    */
   public SignerUtil()
   {
      signerProperties = new Properties();
   }

   /**
    * Creates a new SignerUtil object with Properties defaultProperties.
    *
    * @param defaultProperties - the loaded properties
    */
   public SignerUtil(Properties defaultProperties)
   {
      signerProperties = defaultProperties;
   }

   /**
    * Creates a new SignerUtil object with Properties load from propertiesPath.
    *
    * @param propertiesPath - the path to properties or config file
    */
   public SignerUtil(String propertiesPath)
   {
      signerProperties = new Properties();

      try
      {
         signerProperties.load(new FileInputStream(new File(propertiesPath)));
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Sets the Keystore
    *
    * @param pathOfKeystore - path to the new keystore
    */
   public void setKeystore(String pathOfKeystore)
   {
      signerProperties.setProperty("Keystore", pathOfKeystore);
   }

   /**
    * gets the Keystore
    *
    * @return path to the keystore
    */
   public String getKeystore()
   {
      return signerProperties.getProperty("Keystore");
   }

   /**
    * Sets the password for Keystore
    *
    * @param KeystorePassword - the password for Keystore
    */
   public void setKeystorePassword(String KeystorePassword)
   {
      signerProperties.setProperty("StorePass", KeystorePassword);
   }

   /**
    * gets the password for Keystore
    *
    * @return the password for Keystore
    */
   public String getKeystorePassword()
   {
      return signerProperties.getProperty("StorePass");
   }

   /**
    * Sets the password for the Alias
    *
    * @param AliasPassword - the new password for the Alias
    */
   public void setAliasPassword(String AliasPassword)
   {
      signerProperties.setProperty("AliasPass", AliasPassword);
   }

   /**
    * gets the password for the Alias
    *
    * @return the password for the Alias
    */
   public String getAliasPassword()
   {
      return signerProperties.getProperty("AliasPass");
   }

   /**
    * Sets the Alias to use
    *
    * @param Alias - the new Alias
    */
   public void setAlias(String Alias)
   {
      signerProperties.setProperty("Alias", Alias);
   }

   /**
    * gets the Alias
    *
    * @return the Alias
    */
   public String getAlias()
   {
      return signerProperties.getProperty("Alias");
   }

   /**
    * Sets other Properties
    *
    * @param key - the key for the Property
    * @param value - the new value for the Property
    */
   public void setProperty(String key, String value)
   {
      signerProperties.setProperty(key, value);
   }

   /**
    * Gets other Properties
    *
    * @param key - the key for the Property
    *
    * @return the value for the Property
    */
   public String getAlias(String key)
   {
      return signerProperties.getProperty(key);
   }

   /**
    * Handles the args common to both keytool an jarsigner
    *
    * @param args - the array of args to the args in
    */
   private void setCommonArgs(Collection<String> args)
   {
      String keystore = signerProperties.getProperty("Keystore");
      String storePassword = signerProperties.getProperty("StorePass");
      String storeType = signerProperties.getProperty("StoreType");
      String providerName = signerProperties.getProperty("ProviderName");
      String aliasPassword = signerProperties.getProperty("AliasPass");
      String protectedPath = signerProperties.getProperty("Protected");

      if (keystore != null)
      {
         args.add("-keystore");
         args.add(keystore);
      }

      if (storePassword != null)
      {
         args.add("-storepass");
         args.add(storePassword);
      }

      if (storeType != null)
      {
         args.add("-storetype");
         args.add(storeType);
      }

      if (providerName != null)
      {
         args.add("-providerName");
         args.add(providerName);
      }

      if (aliasPassword != null)
      {
         args.add("-keypass");
         args.add(aliasPassword);
      }

      if (protectedPath != null)
      {
         if (protectedPath.toLowerCase().equals("true"))
         {
            args.add("-protected");
         }
      }
   }

   /**
    * signs a jar
    *
    * @param filename - the file to sign
    */
   public void sign(String filename)
   {
      try
      {
         if (! filename.endsWith(".jar"))
         {
            filename = filename + ".jar";
         }

         if (new File(filename).exists())
         {
            String alias = signerProperties.getProperty("Alias");
            String timestampingAuthority = signerProperties.getProperty(
                  "TimestampingAuthority");
            String timestampingAuthorityCertificate = signerProperties.getProperty(
                  "TimestampingAuthorityCertificate");
            String altSigningMechanism = signerProperties.getProperty(
                  "AltSigningMechanism");
            String altSigningMechanismLoc = signerProperties.getProperty(
                  "AltSigningMechanismLocation");
            String sectionsOnly = signerProperties.getProperty("SectionsOnly");
            String verbose = signerProperties.getProperty("Verbose");
            String displayCertificates = signerProperties.getProperty(
                  "DisplayCertificates");
            Collection<String> jarSignerArgs = new ArrayList<String>();
            setCommonArgs(jarSignerArgs);

            if (timestampingAuthority != null)
            {
               jarSignerArgs.add("-tsa");
               jarSignerArgs.add(timestampingAuthority);
            }

            if (timestampingAuthorityCertificate != null)
            {
               jarSignerArgs.add("-tsacert");
               jarSignerArgs.add(timestampingAuthorityCertificate);
            }

            if (altSigningMechanism != null)
            {
               jarSignerArgs.add("-altsigner");
               jarSignerArgs.add(altSigningMechanism);
            }

            if (altSigningMechanismLoc != null)
            {
               jarSignerArgs.add("-altsignerpath");
               jarSignerArgs.add(altSigningMechanismLoc);
            }

            if (verbose != null)
            {
               if (verbose.toLowerCase().equals("true"))
               {
                  jarSignerArgs.add("-verbose");
               }
            }

            if (displayCertificates != null)
            {
               if (displayCertificates.toLowerCase().equals("true"))
               {
                  jarSignerArgs.add("-certs");
               }
            }

            jarSignerArgs.add(filename);

            if (alias != null)
            {
               jarSignerArgs.add(alias);
            }

            // Convert jarSignerArgs to a String array for execution
            String[] jarSignerArgsArray = (String[]) jarSignerArgs.toArray(new String[0]);
            //System.out.println(jarSignerArgs);
            sun.security.tools.JarSigner.main(jarSignerArgsArray);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}

import java.net.InetAddress;
import java.util.Properties;


public class Info
{
   public static void main(String[] args)
      throws Exception
   {
      Properties pr = java.lang.System.getProperties();
      System.out.println(pr.getProperty("os.version"));
      System.out.println(pr.getProperty("os.arch"));
      System.out.println(pr.getProperty("os.name"));
      
      InetAddress inet = InetAddress.getLocalHost();
      String [] ipOct = inet.getHostAddress().split("\\.");      
      System.out.println(ipOct[3]);
      System.out.println(inet.getHostAddress());     
   }
}


import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.File;

// This class only works for touching existing files
// it will not touch directories except for the root directory
// it will also not create a file.
public class TimestampingFileToucher extends FileToucher
{
   private Date mDate = null;

   public TimestampingFileToucher(String[]args)
   {
      super(args);

      try
      {
         String sDate=System.getProperty("timestamp");

         if( sDate != null )
         {
            mDate = new SimpleDateFormat("yyyyMMddHHmm").parse(sDate);  
         }
         else
         {
            mDate = new Date();
         }
      }
      catch (ParseException e)
      {
         mDate = new Date();
         System.out.println( e );
      }
   }

   public void touchOne(File file)
   {
      file.setLastModified(mDate.getTime());
   }

   public void report(File file)
   {
      System.out.println( file.getName() );
   }
}

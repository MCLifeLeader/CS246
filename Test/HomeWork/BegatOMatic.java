import java.text.MessageFormat;
import java.util.StringTokenizer;

// Runnable class
public class BegatOMatic
   implements Runnable
{
   // object that does it all
   private BegatIt mBegat = null;

   // java main
   public static void main(String[] args)
   {
      new BegatOMatic(args).run();
   }

   // constructor
   public BegatOMatic(String[] args)
   {
      mBegat = new BegatIt(args);
   }

   // where it all gets run from
   public void run()
   {
      try
      {
         // run it!
         mBegat.printVerses();
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
   }
}

class BegatIt
{
   // starting verse number
   private String mStartVerse;
   // name of persons and their ages
   private String mData;
   // formatting string
   private String mFormat;
   // an array for use with the data input string
   private String[] mDataArray = null;

   // setup all the data
   public BegatIt(String[] args)
   {
      try
      {
         // grab some information from the command line environment variables
         mStartVerse = System.getProperty("startverse");
         mData = System.getProperty("data");
         mFormat = System.getProperty("format");

         if (mStartVerse == null || mData == null || mFormat == null)
         {
            System.out.println("No values were entered in on the environment " +
                    "command line");
            System.out.println("Use -D for these options.");
            System.out.println("startverse, data, format");
            System.exit(1);
         }

         StringTokenizer tokenizer = null;

         tokenizer = new StringTokenizer(mFormat);

         //tokenizer.countTokens() was not working so I hard coded a value of 4;
         String[] tMsgArray = new String[4];

         // parse out the text string so it's in the format I want
         for (int i = 0; i < 4; i++)
         {
            tMsgArray[i] = tokenizer.nextToken(":").trim();
         }

         // reconstruct the text string in the format I want
         mFormat = tMsgArray[0] + ":" + tMsgArray[1] + ":" + tMsgArray[2] +
                 ": " + tMsgArray[3];

         tokenizer = new StringTokenizer(mData);
         mDataArray = new String[4];

         // Parse out the data array
         for (int i = 0; i < 4; i++)
         {
            mDataArray[i] = tokenizer.nextToken(":").trim();
         }
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
   }

   public void printVerses()
   {
      try
      {
         int iVerse = Integer.parseInt(mStartVerse);

         for (int i = 0; i < 3; i++)
         {
            // create the format object
            Object[] arguments = {
            new Integer(iVerse++),
            mDataArray[i].split(",")[0],
            this.convertNumber(Integer.parseInt(mDataArray[i].split(",")[1])),
            mDataArray[i+1].split(",")[0],
            "\n" + new Integer(iVerse++),
            this.convertNumber(Integer.parseInt(mDataArray[i].split(",")[2])),
            "\n" + new Integer(iVerse++),
            this.convertNumber(Integer.parseInt( mDataArray[i].split(",")[1]) + 
               Integer.parseInt( mDataArray[i].split(",")[2])) };

            // print the formatted information
            System.out.print(MessageFormat.format(mFormat, arguments) + "\n");
         }
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
   }

   // convert the integer number into a text number
   protected String convertNumber(int number)
   {
      String returnValue = null;

      switch (number)
      {
         case 65:
            returnValue = "sixty and five";
            break;
         case 70:
            returnValue = "seventy";
            break;
         case 90:
            returnValue = "ninety";
            break;
         case 815:
            returnValue = "eight hundred and fifteen";
            break;
         case 830:
            returnValue = "eight hundred and thirty";
            break;
         case 840:
            returnValue = "eight hundred and forty";
            break;
         case 895:
            returnValue = "eight hundred and ninety and five";
            break;
         case 905:
            returnValue = "nine hundred and five";
            break;
         case 910:
            returnValue = "nine hundred and ten";
            break;
         default:
            returnValue = "Invalid Value Entered!";
            break;
      }

      return returnValue;
   }

   // return the starting verse value
   public String getStartVerse()
   {
      return mStartVerse;
   }

   // return the original data portion entered
   public String getData()
   {
      return mData;
   }

   // return the format value entered
   public String getFormat()
   {
      return mFormat;
   }

   public String[] getMessageDataArray()
   {
      return mDataArray;
   }
}

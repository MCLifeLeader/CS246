import java.io.RandomAccessFile;

// $Header: /usr/local/cvsroot/students/cs246/car03009/ebr/SplitDBF2.java,v 1.1 2006/05/17 19:31:58 mbcarey Exp $
////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) CS246 2005
// All Rights Reserved
//
// THIS WORK IS AN UNPUBLISHED WORK AND CONTAINS CONFIDENTIAL, PROPRIETARY,
// AND TRADE SECRET INFORMATION OF CS346. ACCESS TO THIS
// WORK IS RESTRICTED TO CS346 PARTICIPANTS AND TO ENTITIES
// OTHER THAN CS346 WHO ARE PARTIES TO CURRENT LICENSE AND
// CONFIDENTIALITY AGREEMENTS WITH CS346 OR ITS AUTHORIZED
// DISTRIBUTORS. NO PART OF THIS WORK MAY BE USED, PRACTICED, PERFORMED,
// COPIED, DISTRIBUTED, REPRODUCED, REVISED, MODIFIED, TRANSLATED,
// ABRIDGED, CONDENSED, EXPANDED, COLLECTED, COMPILED, LINKED, RECAST,
// TRANSFORMED, ADAPTED, OR REVERSE ENGINEERED WITHOUT THE PRIOR WRITTEN
// CONSENT OF CS346. ANY USE OR EXPLOITATION OF THIS WORK
// WITHOUT EXPRESS AUTHORIZATION COULD SUBJECT THE PERPETRATOR TO CRIMINAL
// AND CIVIL LIABILITY.
//
////////////////////////////////////////////////////////////////////////////

public class SplitDBF2
{
   //get the size of the file
   public static long getSize( String filename )
      throws Exception
   {
      long rtnval = 0;

      RandomAccessFile file = new RandomAccessFile(filename, "r");

      // simple file length return
      rtnval = file.length();

      file.close();
      return rtnval;
   }

   // Gather all of the file offsets for each dbf file's starting points.
   public static int[] getOffsets( String filename )
      throws Exception
   {
      int filePos = 0;
      int offset = 0;
      int[] rtnval = new int[ 8 ];

      // open the file for reading
      RandomAccessFile file = new RandomAccessFile(filename, "r");
      long maxFileLen = file.length();

      rtnval[ 0 ] = 0;

      // create a byte array for searching through
      byte[] buffer = new byte[ ( int ) maxFileLen ];
      file.readFully(buffer);
      file.close();

      // while not at the end of the file
      while ( maxFileLen >= filePos )
      {
         try
         {
            // look for the first byte ID and then look for values that are
            // close to a date. YYMMDD
            if ( (byte) 0x03 == buffer[ filePos ] &&
               ( buffer[ filePos + 1 ] > 0 && buffer[ filePos + 1 ] < 99 ) &&
               ( buffer[ filePos + 2 ] > 0 && buffer[ filePos + 2 ] < 12 ) &&
               ( buffer[ filePos + 3 ] > 0 && buffer[ filePos + 3 ] < 31 ) )
            {
               // record postion
               rtnval[ offset++ ] = filePos;
            }
            filePos++;
         }
         catch ( Exception e )
         {
            // get the last position
            rtnval[ offset ] = --filePos;
            break;
         }
      }

      return rtnval;
   }

   // here are all of my file names
   private static String[] cNames = { 
   "acctno", "check", "claimno", "dicode", "policy", "recall", "trcode", };

   public static String filename( int i )
   {
      return cNames[ i ] + ".dbf";
      //--> your code goes here (or does it?!)
   }

   public static void main( String[] args )
      throws Exception
   {
      if ( args.length < 1 )
      {
         System.out.println("Usage: SplitDBF <filename>");
         return;
      }

      String filename = args[ 0 ];
      long size = getSize(filename);
      int[] offsets = getOffsets(filename);
      RandomAccessFile file = new RandomAccessFile(filename, "r");
      byte[] buffer = new byte[ ( int ) size ];
      file.readFully(buffer);
      file.close();

      for ( int i = 0; i < offsets.length - 1; i++ )
      {
         int offset = offsets[ i ];
         int len = offsets[ i + 1 ] - offset;
         RandomAccessFile newfile = new RandomAccessFile(filename(i), "rw");
         newfile.write(buffer, offset, len);
         newfile.write((byte) 26); // write the ^Z terminator
         newfile.close();
      }
   }
}

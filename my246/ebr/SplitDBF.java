import java.io.RandomAccessFile;

// $Header: /usr/local/cvsroot/students/cs246/car03009/ebr/SplitDBF.java,v 1.9 2006/05/17 19:32:54 mbcarey Exp $
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

/*
 * Idea came from this link for reading the bytes correctly from the file.
 * <a href=http://www.cs.cornell.edu/Courses/cs212/2001fa/Project/Part1/dbf.htm>
 * (bArr[3]) << 24 | (bArr[2] & 0xff) << 16 | (bArr[1] & 0xff) << 8 | (bArr[0] & 0xff);
 */

public class SplitDBF
{
   //get the size of the file
   public static long getSize(String filename)
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
   public static int[] getOffsets(String filename)
      throws Exception
   {
      short headSize = 0;
      short recSize = 0;

      int numRecords = 0;
      int numDBFChunks = 0;

      long rtnvalt = 0;
      long filePos = 0;
      long maxFileLen = 0;

      // array to hold the data from the dbf file
      byte[] bArr = new byte[16];

      int[] rtnval = null;

      // find the number of dbf chunks out of the file
      numDBFChunks = getDBFChunks(filename) + 1;

      if (numDBFChunks > 0)
         rtnval = new int[numDBFChunks];
      else
         throw new Exception("Allocation cannot be 0");

      rtnval[0] = 0;

      numDBFChunks = 0;

      // open the file for reading
      RandomAccessFile file = new RandomAccessFile(filename, "r");
      maxFileLen = file.length();

      // while not at the end of the file
      while (maxFileLen >= filePos)
      {
         try
         {
            file.readInt(); // Throw away first 4 bytes

            // read the number of records
            file.read(bArr, 0, 4);
            numRecords = (bArr[3]) << 24 | (bArr[2] & 0xff) << 16 | 
               (bArr[1] & 0xff) << 8 | (bArr[0] & 0xff);

            // read the number of bytes in the header
            file.read(bArr, 0, 2);
            headSize = (short)((bArr[1] & 0xff) << 8 | (bArr[0] & 0xff));

            // read the number of bytes per record
            file.read(bArr, 0, 2);
            recSize = (short)((bArr[1] & 0xff) << 8 | (bArr[0] & 0xff));

            // do the math, if newer dbf version + 1;
            // eventual todo, verify dbf version and automatically + 1;
            // found in first byte
            rtnvalt = headSize + (numRecords * recSize);

            numDBFChunks++;
            filePos += rtnvalt;

            // put that file position value into our array
            rtnval[numDBFChunks] = (int)filePos;

            file.seek(filePos);
         }
         catch (Exception e)
         {
            //System.out.println(e);
            break;
         }
      }

      file.close();
      return rtnval;
   }

   // Scan for the number of dbf chunks
   public static int getDBFChunks(String filename)
      throws Exception
   {
      short headSize = 0;
      short recSize = 0;

      int numRecords = 0;
      int numDBFChunks = 0;

      long rtnvalt = 0;
      long filePos = 0;
      long maxFileLen = 0;

      byte[] bArr = new byte[16]; ;

      RandomAccessFile file = new RandomAccessFile(filename, "r");
      maxFileLen = file.length();

      while (maxFileLen >= filePos)
      {
         try
         {
            file.readInt(); // Throw away first 4 bytes

            // read the number of records
            file.read(bArr, 0, 4);
            numRecords = ( bArr[ 3 ] ) << 24 | ( bArr[ 2 ] & 0xff ) << 16 | 
               ( bArr[ 1 ] & 0xff ) << 8 | ( bArr[ 0 ] & 0xff );

            // read the number of bytes in the header
            file.read(bArr, 0, 2);
            headSize = (short) ( ( bArr[ 1 ] & 0xff ) << 8 | 
               ( bArr[ 0 ] & 0xff ) );

            // read the number of bytes per record
            file.read(bArr, 0, 2);
            recSize = (short) ( ( bArr[ 1 ] & 0xff ) << 8 | 
               ( bArr[ 0 ] & 0xff ) );

            // do the math, if newer dbf version + 1;
            // eventual todo, verify dbf version and automatically + 1;
            // found in first byte
            rtnvalt = headSize + ( numRecords * recSize );

            numDBFChunks++;
            filePos += rtnvalt;
            file.seek(filePos);
         }
         catch (Exception e)
         {
            //System.out.println(e);
            break;
         }
      }

      file.close();
      return numDBFChunks;
   }

   //private static String[] cNames = { "acctno", "check", "claimno", };

   private static String[] cNames = { 
   "acctno", "check", "claimno", "dicode", "policy", "recall", "trcode", };

   public static String filename(int i)
   {
      return cNames[i] + ".dbf";
      //--> your code goes here (or does it?!)

      // Normally you would not know what the names where in the file
      // so it is better just to simply number then 1+n until they are
      // all listed. Then you can go through the process to figure out which
      // db file belongs to which number.
      // In this case they are asciibetical.
      //return (i + ".dbf");
   }

   public static void main(String[] args)
      throws Exception
   {
      if (args.length < 1)
      {
         System.out.println("Usage: SplitDBF <filename>");
         return;
      }

      String filename = args[0];
      long size = getSize(filename);
      int[] offsets = getOffsets(filename);
      RandomAccessFile file = new RandomAccessFile(filename, "r");
      byte[] buffer = new byte[(int)size];
      file.readFully(buffer);
      file.close();

      for (int i = 0; i < offsets.length - 1; i++)
      {
         int offset = offsets[i];
         int len = offsets[i + 1] - offset;
         RandomAccessFile newfile = new RandomAccessFile(filename(i), "rw");
         newfile.write(buffer, offset, len);
         newfile.write((byte)26); // write the ^Z terminator
         newfile.close();
      }
   }
}


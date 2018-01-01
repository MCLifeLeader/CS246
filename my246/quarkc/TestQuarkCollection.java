// $Header: /usr/local/cvsroot/students/cs246/car03009/quarkc/TestQuarkCollection.java,v 1.1 2006/05/07 04:37:52 neffr Exp $
////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) CS346 2004
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

import java.util.Collection;
import java.util.Iterator;

public class TestQuarkCollection
{
   // EOL = End Of Line
   private static String EOL = System.getProperty("line.separator");

   private static boolean VERBOSE = Boolean.getBoolean("verbose");

   private StringBuffer mResults;

   public TestQuarkCollection()
   {
      mResults = new StringBuffer();
   }

   private void println(Object o)
   {
      mResults.append(o);
      mResults.append(EOL);
      if (VERBOSE)
      {
         System.out.println(o);
      }
   }

   public String getActualResults()
   {
      return mResults.toString();
   }

   public static void main(String[] args)
   {
      TestQuarkCollection testQC = new TestQuarkCollection();
      testQC.run();
      String actual = testQC.getActualResults();
      String expected = testQC.getExpectedResults();

      assert actual.equals(expected) :
         "the actual results are not the same as the expected results.";
   }

   public void run()
   {
      Collection qc = new QuarkCollection();
      boolean added = false;

      assert qc.isEmpty() :
         "isEmpty failed.";

      added = qc.add(new UpQuark("Larry"));

      assert !qc.isEmpty() :
         "isEmpty failed.";

      assert added :
         "add of UpQuark Larry failed to return true.";

      assert qc.size() == 1 :
         "size != 1";

      println(qc);

      added = qc.add(new UpQuark("Moe"));

      assert qc.size() == 2 :
         "size != 2";

      assert added :
         "add of UpQuark Moe failed to return true.";

      println(qc);

      added = qc.add(new DownQuark("Curly"));

      assert qc.size() == 3 :
         "size != 3";

      assert added :
         "add of UpQuark Curly failed to return true.";

      println(qc);

      qc.clear();

      assert qc.size() == 0 :
         "size != 0";

      added = qc.add(new DownQuark("Ben"));

      assert added :
         "add of DownQuark Ben failed to return true.";

      // try adding a NON-Quark object
      added = qc.add(new QuarkCollection());

      assert ! added :
         "add of NON-Quark object failed to return false.";

      added = qc.add(new DownQuark("Bob"));

      assert added :
         "add of DownQuark Bob failed to return true.";

      // try adding a DIFFERENT Quark object with the SAME NAME. 
      added = qc.add(new DownQuark("Bob"));

      assert ! added :
         "DownQuark Bob already added, failed to return false.";

      // try adding the SAME Quark object
      Quark boo = new DownQuark("Boo");
      qc.add(boo);

      added = qc.add(boo);

      assert ! added :
         "DownQuark Boo already added, failed to return false.";

      added = qc.add(new DownQuark("Bogus"));

      assert ! added :
         "add DownQuark Bogus makes too many quarks, failed to return false!";

      assert qc.size() == 3 :
         "size != 3";

      println(qc);

      assert qc.equals(qc) :
         "equals failed.";

      assert !qc.equals(mResults) :
         "!equals failed.";

      Collection qc2 = new QuarkCollection();
      qc2.addAll(qc);

      assert qc2.equals(qc) :
         "two collections are not equal.";

      assert qc2.containsAll(qc) :
         "containsAll failed.";

      assert !qc2.retainAll(qc) :
         "retainAll failed.";

      boolean removedAll = true;
      try
      {
         qc2.removeAll(qc);
      }
      catch (UnsupportedOperationException e)
      {
         removedAll = false;
      }

      assert !removedAll :
         "removedAll is true.";

      assert !qc2.isEmpty() :
         "isEmpty is true.";

      Object[] array = qc.toArray();

      for (int i = 0; i < array.length; i++)
      {
         println(array[i]);
      }

      Object[] array2 = qc.toArray(new Quark[3]);

      for (int i = 0; i < array2.length; i++)
      {
         println(array2[i]);
      }

      Object[] array3 = qc.toArray(new Quark[0]);

      for (int i = 0; i < array3.length; i++)
      {
         println((Quark) array3[i]);
      }

      Quark one = (Quark) array3[0];
      Quark two = (Quark) array3[1];
      Quark three = (Quark) array3[2];

      boolean oneRemoved = qc.remove(one);

      println(one + (oneRemoved ? " was " : " was not ") + "removed.");

      assert qc.size() == 2 :
         "size != 2";

      println(qc);

      oneRemoved = qc.remove(one);

      println(one + (oneRemoved ? " was " : " was not ") + "removed.");

      assert qc.size() == 2 :
         "size != 2";

      println(qc);

      boolean threeRemoved = qc.remove(three);

      println(three + (threeRemoved ? " was " : " was not ") + "removed.");

      assert qc.size() == 1 :
         "size != 1";

      println(qc);

      boolean twoRemoved = qc.remove(two);

      println(two + (twoRemoved ? " was " : " was not ") + "removed.");

      assert qc.size() == 0 :
         "size != 0";

      println(qc);

      Iterator it = qc2.iterator();
      while (it.hasNext())
      {
         println(it.next());
      }

      println("hashCode = " + qc2.hashCode());

      assert qc2.hashCode() == 66239671 :
         "hashCode failed.";

      assert !qc2.equals(qc) :
         "equals failed.";

      assert qc2.hashCode() != qc.hashCode() :
         "hashCode equality failed.";

      qc.addAll(qc2);

      assert qc2.equals(qc) :
         "equals failed.";

      assert qc2.hashCode() == qc.hashCode() :
         "hashCode equality failed.";
   }

   private String getExpectedResults()
   {
      return (
         "[Larry]" + EOL +
         "[Larry, Moe]" + EOL +
         "[Larry, Moe, Curly]" + EOL +
         "[Ben, Bob, Boo]" + EOL +
         "Ben" + EOL +
         "Bob" + EOL +
         "Boo" + EOL +
         "Ben" + EOL +
         "Bob" + EOL +
         "Boo" + EOL +
         "Ben" + EOL +
         "Bob" + EOL +
         "Boo" + EOL +
         "Ben was removed." + EOL +
         "[Bob, Boo]" + EOL +
         "Ben was not removed." + EOL +
         "[Bob, Boo]" + EOL +
         "Boo was removed." + EOL +
         "[Bob]" + EOL +
         "Bob was removed." + EOL +
         "[]" + EOL +
         "Ben" + EOL +
         "Bob" + EOL +
         "Boo" + EOL +
         "hashCode = 66239671" + EOL
         );
   }
}

class Quark
{
   private String mName;

   public Quark(String name)
   {
      mName = name;
   }

   public String toString()
   {
      return mName;
   }

   public boolean equals(Object obj)
   {
      return (obj != null && (obj.hashCode() == hashCode()));
   }

   public int hashCode()
   {
      return (mName == null ? super.hashCode() : mName.hashCode());
   }
}

class DownQuark
   extends Quark
{
   public DownQuark(String name)
   {
      super(name);
   }
}

class UpQuark
   extends Quark
{
   public UpQuark(String name)
   {
      super(name);
   }
}

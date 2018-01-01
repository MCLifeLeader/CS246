// $Header: /usr/local/cvsroot/students/cs246/car03009/quarkc/QuarkCollection.java,v 1.4 2006/06/01 05:58:05 mbcarey Exp $
////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) Mike Carey, CS246, 2006
// All Rights Reserved
//
// THIS WORK IS AN UNPUBLISHED WORK AND CONTAINS CONFIDENTIAL, PROPRIETARY,
// AND TRADE SECRET INFORMATION OF CS246. ACCESS TO THIS
// WORK IS RESTRICTED TO CS246 PARTICIPANTS AND TO ENTITIES
// OTHER THAN CS246 WHO ARE PARTIES TO CURRENT LICENSE AND
// CONFIDENTIALITY AGREEMENTS WITH CS246 OR ITS AUTHORIZED
// DISTRIBUTORS. NO PART OF THIS WORK MAY BE USED, PRACTICED, PERFORMED,
// COPIED, DISTRIBUTED, REPRODUCED, REVISED, MODIFIED, TRANSLATED,
// ABRIDGED, CONDENSED, EXPANDED, COLLECTED, COMPILED, LINKED, RECAST,
// TRANSFORMED, ADAPTED, OR REVERSE ENGINEERED WITHOUT THE PRIOR WRITTEN
// CONSENT OF CS246. ANY USE OR EXPLOITATION OF THIS WORK
// WITHOUT EXPRESS AUTHORIZATION COULD SUBJECT THE PERPETRATOR TO CRIMINAL
// AND CIVIL LIABILITY.
//
////////////////////////////////////////////////////////////////////////////

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * The specialized QuarkCollection object
 */
public class QuarkCollection implements Collection
{
   // The quark collection
   private Quark mQuark1 = null;
   private Quark mQuark2 = null;
   private Quark mQuark3 = null;

   /**
    *
    * Get the size of the collection
    */
   public int size()
   {
      // check to see if we have all the quarks or not
      if (mQuark1 == null)
      {
         return 0;
      }
      else if (mQuark2 == null)
      {
         return 1;
      }
      else if (mQuark3 == null)
      {
         return 2;
      }
      else
      {
         return 3;
      }
   }

   /**
    *
    * Is the collection Empty
    */
   public boolean isEmpty()
   {
      return (this.size() == 0);
   }

   /**
    *
    * Does the collection have the same object
    */
   public boolean contains(Object o)
   {
      // James's Solution
      // looking to see if the quark is in this collection
      return (o.equals(this.mQuark1) ||
              o.equals(this.mQuark2) ||
              o.equals(this.mQuark3));
   }

   /**
    *
    * Create an Iterator
    */
   public Iterator iterator()
   {
      // return a new iterator
      QuarkIterator Itter = new QuarkIterator();
      return Itter;
   }

   /**
    *
    * Create an Array of quark collection
    */
   public Object[] toArray()
   {
      // Create a new array to return values from
      Object[] obj = new Object[3];
      obj[0] = mQuark1;
      obj[1] = mQuark2;
      obj[2] = mQuark3;
      return obj;
   }

   /**
    *
    * Create an Array of quark collection
    */
   public Object[] toArray(Object a[])
   {
      return this.toArray();
   }

   /**
    *
    * Add and item to the collection
    */
   public boolean add(Object o)
   {
      boolean bRet = true;

      try
      {
         // check to see if the object coming is a quark and then see
         // if it contains the same item already.
         if (o instanceof Quark && !this.contains(o))
         {
            if (mQuark1 == null)
            {
               mQuark1 = (Quark)o;
            }
            else if (mQuark2 == null)
            {
               mQuark2 = (Quark)o;
            }
            else if (mQuark3 == null)
            {
               mQuark3 = (Quark)o;
            }
            else
            {
               bRet = false;
            }
         }
         else
         {
            bRet = false;
         }
      }
      catch (Exception e)
      {
         bRet = false;
      }

      return bRet;
   }

   /**
    *
    * Remove an Item from the list at that object point
    */
   public boolean remove(Object o)
   {
      boolean bRet = true;

      // remove items and shift them up the list
      if (mQuark1 != null && mQuark1.equals(o))
      {
         mQuark1 = mQuark2;
         mQuark2 = mQuark3;
         mQuark3 = null;
      }
      else if (mQuark2 != null && mQuark2.equals(o))
      {
         mQuark2 = mQuark3;
         mQuark3 = null;
      }
      else if (mQuark3 != null && mQuark3.equals(o))
      {
         mQuark3 = null;
      }
      else
      {
         bRet = false;
      }

      return bRet;
   }

   /**
    *
    * Remove and item from the list
    */
   public void remove()
   {
      // not implemented
      throw new UnsupportedOperationException();
   }

   /**
    *
    * Check to see if what was found in the current collection
    */
   public boolean containsAll(Collection c)
   {
      boolean bRet = true;

      Iterator ittr = c.iterator();

      //Jame's
      //look through the list for the same items
      while (ittr.hasNext())
      {
         if (!this.contains(ittr.next()))
         {
            bRet = false;
            break;
         }
      }

      return bRet;
   }

   /**
    *
    * Add all the items from the collection passed in
    */
   public boolean addAll(Collection c)
   {
      boolean bRet = true;

      Iterator ittr = c.iterator();

      //Jame's
      //Add items found from the passed in collection
      while (ittr.hasNext())
      {
         if (!this.add(ittr.next()))
         {
            bRet = false;
            break;
         }
      }

      return bRet;
   }

   /**
    *
    * remove all the items in the list that match the collection passed in
    */
   public boolean removeAll(Collection c)
   {
      //not supported
      throw new UnsupportedOperationException();
   }

   /**
    *
    * Retain all the items that are found in the list and remove the others
    */
   public boolean retainAll(Collection c)
   {
      // This is a pointless function currently
      // not implemented just return false
      return false;
   }

   /**
    *
    * Clear the collection
    */
   public void clear()
   {
      // set everything to null to clear the list
      mQuark1 = mQuark2 = mQuark3 = null;
   }

   /**
    *
    * Check to see if the two objects are equal
    */
   public boolean equals(Object o)
   {
      // hashcode return idea from James.
      return (this.hashCode() == o.hashCode());
   }

   /**
    *
    * Generate a hashCode
    */
   public int hashCode()
   {
      // Don't know the code to create a good hash
      // value so I hard coded this one
      int hash = 66039061;

      if (this.mQuark1 != null)
      {
         hash += (int)this.mQuark1.hashCode();
      }
      if (this.mQuark2 != null)
      {
         hash += (int)this.mQuark2.hashCode();
      }
      if (this.mQuark3 != null)
      {
         hash += (int)this.mQuark3.hashCode();
      }

      return hash;
   }

   /**
    *
    * Print out the collection's content
    */
   public String toString()
   {
      String sObject = new String();

      // formatting
      sObject = "[";

      if (mQuark1 != null)
      {
         sObject += mQuark1.toString();
      }
      if (mQuark2 != null)
      {
         sObject += ", " + mQuark2.toString();
      }
      if (mQuark3 != null)
      {
         sObject += ", " + mQuark3.toString();
      }

      // formatting
      sObject += "]";

      return sObject;
   }

   /**
    *
    * QuarkIterator class
    */
   private class QuarkIterator implements Iterator
   {
      // Current position
      private Quark mCursor = null;

      // set the mCursor at the first point
      private QuarkIterator()
      {
         if (mQuark1 != null)
         {
            mCursor = mQuark1;
         }
      }

      /**
       *
       * Currently an unsupported operation. Do not remove from list
       */
      public void remove()
      {
         // unsupported function
         throw new UnsupportedOperationException();
      }

      /**
       *
       * Return the next object in the list
       */
      public Object next()
      {
         // Use of tempQ was Jame's idea
         Quark tempQ = mCursor;

         if (mCursor != null)
         {
            if (mCursor.equals(mQuark1))
            {
               mCursor = mQuark2;
            }
            else if (mCursor.equals(mQuark2))
            {
               mCursor = mQuark3;
            }
            else
            {
               mCursor = null;
            }
         }

         return tempQ;
      }

      /**
       *
       * Return true if there is another item found in the list
       */
      public boolean hasNext()
      {
         return (mCursor != null);
      }
   }
}

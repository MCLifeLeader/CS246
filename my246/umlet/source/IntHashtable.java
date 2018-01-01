// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/IntHashtable.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control.io;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

// IntHashtable - a Hashtable that uses ints as the keys
//
// This is 90% based on JavaSoft's java.util.Hashtable.
//
// Visit the ACME Labs Java page for up-to-date versions of this and other
// fine Java utilities: http://www.acme.com/java/
import java.util.*;

/// A Hashtable that uses ints as the keys.
/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
public class IntHashtable
   extends Dictionary
   implements Cloneable
{
   /// The hash table data.
   /**
    * DOCUMENT ME!
    */
   private IntHashtableEntry[] table;

   /// The total number of entries in the hash table.
   /**
    * DOCUMENT ME!
    */
   private int count;

   /// Rehashes the table when count exceeds this threshold.
   /**
    * DOCUMENT ME!
    */
   private int threshold;

   /// The load factor for the hashtable.
   /**
    * DOCUMENT ME!
    */
   private float loadFactor;

   /// Constructs a new, empty hashtable with the specified initial
   /**
    * Creates a new IntHashtable object.
    *
    * @param initialCapacity DOCUMENT ME!
    * @param loadFactor DOCUMENT ME!
    */
   public IntHashtable(int initialCapacity, float loadFactor)
   {
      if ((initialCapacity <= 0) || (loadFactor <= 0.0))
      {
         throw new IllegalArgumentException();
      }

      this.loadFactor = loadFactor;
      table = new IntHashtableEntry[initialCapacity];
      threshold = (int) (initialCapacity * loadFactor);
   }

   /// Constructs a new, empty hashtable with the specified initial
   /**
    * Creates a new IntHashtable object.
    *
    * @param initialCapacity DOCUMENT ME!
    */
   public IntHashtable(int initialCapacity)
   {
      this(initialCapacity, 0.75f);
   }

   /// Constructs a new, empty hashtable. A default capacity and load factor
   /**
    * Creates a new IntHashtable object.
    */
   public IntHashtable()
   {
      this(101, 0.75f);
   }

   /// Returns the number of elements contained in the hashtable.
   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int size()
   {
      return count;
   }

   /// Returns true if the hashtable contains no elements.
   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public boolean isEmpty()
   {
      return count == 0;
   }

   /// Returns an enumeration of the hashtable's keys.
   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public synchronized Enumeration keys()
   {
      return new IntHashtableEnumerator(table, true);
   }

   /// Returns an enumeration of the elements. Use the Enumeration methods
   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public synchronized Enumeration elements()
   {
      return new IntHashtableEnumerator(table, false);
   }

   /// Returns true if the specified object is an element of the hashtable.
   /**
    * DOCUMENT ME!
    *
    * @param value DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public synchronized boolean contains(Object value)
   {
      if (value == null)
      {
         throw new NullPointerException();
      }

      IntHashtableEntry[] tab = table;

      for (int i = tab.length; i-- > 0;)
      {
         for (IntHashtableEntry e = tab[i]; e != null; e = e.next)
         {
            if (e.value.equals(value))
            {
               return true;
            }
         }
      }

      return false;
   }

   /// Returns true if the collection contains an element for the key.
   /**
    * DOCUMENT ME!
    *
    * @param key DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public synchronized boolean containsKey(int key)
   {
      IntHashtableEntry[] tab = table;
      int hash = key;
      int index = (hash & 0x7FFFFFFF) % tab.length;

      for (IntHashtableEntry e = tab[index]; e != null; e = e.next)
      {
         if ((e.hash == hash) && (e.key == key))
         {
            return true;
         }
      }

      return false;
   }

   /// Gets the object associated with the specified key in the
   /**
    * DOCUMENT ME!
    *
    * @param key DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public synchronized Object get(int key)
   {
      IntHashtableEntry[] tab = table;
      int hash = key;
      int index = (hash & 0x7FFFFFFF) % tab.length;

      for (IntHashtableEntry e = tab[index]; e != null; e = e.next)
      {
         if ((e.hash == hash) && (e.key == key))
         {
            return e.value;
         }
      }

      return null;
   }

   /// A get method that takes an Object, for compatibility with
   /**
    * DOCUMENT ME!
    *
    * @param okey DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Object get(Object okey)
   {
      if (! (okey instanceof Integer))
      {
         throw new InternalError("key is not an Integer");
      }

      Integer ikey = (Integer) okey;
      int key = ikey.intValue();

      return get(key);
   }

   /// Rehashes the content of the table into a bigger table.
   /**
    * DOCUMENT ME!
    */
   protected void rehash()
   {
      int oldCapacity = table.length;
      IntHashtableEntry[] oldTable = table;

      int newCapacity = (oldCapacity * 2) + 1;
      IntHashtableEntry[] newTable = new IntHashtableEntry[newCapacity];

      threshold = (int) (newCapacity * loadFactor);
      table = newTable;

      for (int i = oldCapacity; i-- > 0;)
      {
         for (IntHashtableEntry old = oldTable[i]; old != null;)
         {
            IntHashtableEntry e = old;
            old = old.next;

            int index = (e.hash & 0x7FFFFFFF) % newCapacity;
            e.next = newTable[index];
            newTable[index] = e;
         }
      }
   }

   /// Puts the specified element into the hashtable, using the specified
   /**
    * DOCUMENT ME!
    *
    * @param key DOCUMENT ME!
    * @param value DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public synchronized Object put(int key, Object value)
   {
      // Make sure the value is not null.
      if (value == null)
      {
         throw new NullPointerException();
      }

      // Makes sure the key is not already in the hashtable.
      IntHashtableEntry[] tab = table;
      int hash = key;
      int index = (hash & 0x7FFFFFFF) % tab.length;

      for (IntHashtableEntry e = tab[index]; e != null; e = e.next)
      {
         if ((e.hash == hash) && (e.key == key))
         {
            Object old = e.value;
            e.value = value;

            return old;
         }
      }

      if (count >= threshold)
      {
         // Rehash the table if the threshold is exceeded.
         rehash();

         return put(key, value);
      }

      // Creates the new entry.
      IntHashtableEntry e = new IntHashtableEntry();
      e.hash = hash;
      e.key = key;
      e.value = value;
      e.next = tab[index];
      tab[index] = e;
      ++count;

      return null;
   }

   /// A put method that takes an Object, for compatibility with
   /**
    * DOCUMENT ME!
    *
    * @param okey DOCUMENT ME!
    * @param value DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Object put(Object okey, Object value)
   {
      if (! (okey instanceof Integer))
      {
         throw new InternalError("key is not an Integer");
      }

      Integer ikey = (Integer) okey;
      int key = ikey.intValue();

      return put(key, value);
   }

   /// Removes the element corresponding to the key. Does nothing if the
   /**
    * DOCUMENT ME!
    *
    * @param key DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public synchronized Object remove(int key)
   {
      IntHashtableEntry[] tab = table;
      int hash = key;
      int index = (hash & 0x7FFFFFFF) % tab.length;

      for (IntHashtableEntry e = tab[index], prev = null; e != null;
             prev = e, e = e.next)
      {
         if ((e.hash == hash) && (e.key == key))
         {
            if (prev != null)
            {
               prev.next = e.next;
            }
            else
            {
               tab[index] = e.next;
            }

            --count;

            return e.value;
         }
      }

      return null;
   }

   /// A remove method that takes an Object, for compatibility with
   /**
    * DOCUMENT ME!
    *
    * @param okey DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Object remove(Object okey)
   {
      if (! (okey instanceof Integer))
      {
         throw new InternalError("key is not an Integer");
      }

      Integer ikey = (Integer) okey;
      int key = ikey.intValue();

      return remove(key);
   }

   /// Clears the hash table so that it has no more elements in it.
   /**
    * DOCUMENT ME!
    */
   public synchronized void clear()
   {
      IntHashtableEntry[] tab = table;

      for (int index = tab.length; --index >= 0;)
         tab[index] = null;

      count = 0;
   }

   /// Creates a clone of the hashtable. A shallow copy is made,
   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public synchronized Object clone()
   {
      try
      {
         IntHashtable t = (IntHashtable) super.clone();
         t.table = new IntHashtableEntry[table.length];

         for (int i = table.length; i-- > 0;)
            t.table[i] = (table[i] != null)
               ? (IntHashtableEntry) table[i].clone() : null;

         return t;
      }
      catch (CloneNotSupportedException e)
      {
         // This shouldn't happen, since we are Cloneable.
         throw new InternalError();
      }
   }

   /// Converts to a rather lengthy String.
   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public synchronized String toString()
   {
      int max = size() - 1;
      StringBuffer buf = new StringBuffer();
      Enumeration k = keys();
      Enumeration e = elements();
      buf.append("{");

      for (int i = 0; i <= max; ++i)
      {
         String s1 = k.nextElement().toString();
         String s2 = e.nextElement().toString();
         buf.append(s1 + "=" + s2);

         if (i < max)
         {
            buf.append(", ");
         }
      }

      buf.append("}");

      return buf.toString();
   }
}


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
class IntHashtableEntry
{
   /**
    * DOCUMENT ME!
    */
   int hash;

   /**
    * DOCUMENT ME!
    */
   int key;

   /**
    * DOCUMENT ME!
    */
   Object value;

   /**
    * DOCUMENT ME!
    */
   IntHashtableEntry next;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   protected Object clone()
   {
      IntHashtableEntry entry = new IntHashtableEntry();
      entry.hash = hash;
      entry.key = key;
      entry.value = value;
      entry.next = (next != null) ? (IntHashtableEntry) next.clone() : null;

      return entry;
   }
}


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
class IntHashtableEnumerator
   implements Enumeration
{
   /**
    * DOCUMENT ME!
    */
   boolean keys;

   /**
    * DOCUMENT ME!
    */
   int index;

   /**
    * DOCUMENT ME!
    */
   IntHashtableEntry[] table;

   /**
    * DOCUMENT ME!
    */
   IntHashtableEntry entry;

   /**
    * Creates a new IntHashtableEnumerator object.
    *
    * @param table DOCUMENT ME!
    * @param keys DOCUMENT ME!
    */
   IntHashtableEnumerator(IntHashtableEntry[] table, boolean keys)
   {
      this.table = table;
      this.keys = keys;
      this.index = table.length;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public boolean hasMoreElements()
   {
      if (entry != null)
      {
         return true;
      }

      while (index-- > 0)

         if ((entry = table[index]) != null)
         {
            return true;
         }

      return false;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Object nextElement()
   {
      if (entry == null)
      {
         while ((index-- > 0) && ((entry = table[index]) == null))
            ;
      }

      if (entry != null)
      {
         IntHashtableEntry e = entry;
         entry = e.next;

         return keys ? new Integer(e.key) : e.value;
      }

      throw new NoSuchElementException("IntHashtableEnumerator");
   }
}

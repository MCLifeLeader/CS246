// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/si/Person.java,v 1.2 2006/06/16 02:33:54 neffr Exp $
package sudoku.si;

import java.util.*;

/**
 * Encapsulates a person's data.
 */
public class Person
{
   /**
    * The choices for gender are enumerated as two.
    */
   public enum Gender
   {
      Male, Female
   }

   /**
    * The person's name.
    */
   private String name;

   /**
    * The person's birth date.
    */
   private Date birthdate;

   /**
    * The person's age (should not be saved, but computed.)
    */
   private transient int age;

   /**
    * The person's gender.
    */
   private Gender gender;

   /**
    * Constructs a new Person instance.
    *
    * @param inName the person's name.
    * @param inAge the person's age.
    * @param inGender the person's gender.
    */
   public Person(String inName, int inAge, Gender inGender)
   {
      name = inName;
      age = inAge;
      gender = inGender;

      Calendar c = Calendar.getInstance();
      c.set(Calendar.YEAR, c.get(Calendar.YEAR) - age);
      birthdate = c.getTime();
   }

   /**
    * Constructs a new Person instance.
    *
    * @param inName the person's name.
    */
   public Person(String inName)
   {
      name = inName;
   }

   /**
    * Constructs a new Person instance.
    */
   public Person()
   {
      //intentionally left empty
   }

   /**
    * Returns a readable representation of the person.
    *
    * @return the person's name as a string.
    */
   public String toString()
   {
      return name;
   }

   /**
    * Tests for equality of two person objects.
    *
    * @param o the other person object to compare with.
    *
    * @return whether or not two person objects have the
    *         same name, birthdate and gender.
    */
   public boolean equals(Object o)
   {
      boolean rtnval = false;

      try
      {
         Person person = (Person) o;
         rtnval = ((name.equals(person.name) &&
                    (birthdate == person.birthdate) &&
                    (gender == person.gender)));
      }
      catch (Exception e)
      {
      }

      return rtnval;
   }

   /**
    * Gets the person's name.
    *
    * @return the name.
    */
   public String getName()
   {
      return name;
   }

   /**
    * Sets the person's name.
    *
    * @param inName the person's new name.
    */
   public void setName(String inName)
   {
      name = inName;
   }

   /**
    * Gets the person's birth date.
    *
    * @return the birth date.
    */
   public Date getBirthdate()
   {
      return birthdate;
   }

   /**
    * Sets the person's birth date.
    *
    * @param inBirthDate the person's new birth date.
    */
   public void setBirthDate(Date inBirthDate)
   {
      birthdate = inBirthDate;
   }

   /**
    * Gets the person's age.
    *
    * @return the age.
    */
   public int getAge()
   {
      return age;
   }

   /**
    * Gets the person's gender.
    *
    * @return the gender.
    */
   public Gender getGender()
   {
      return gender;
   }

   /**
    * Sets the person's gender.
    *
    * @param inGender the person's new gender.
    */
   public void setGender(Gender inGender)
   {
      gender = inGender;
   }
}

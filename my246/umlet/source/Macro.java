// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Macro.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.util.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
public class Macro
   extends Command
{
   /**
    * DOCUMENT ME!
    */
   private Vector _commands;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Vector getCommands()
   {
      return _commands;
   }

   /**
    * Creates a new Macro object.
    *
    * @param v DOCUMENT ME!
    */
   public Macro(Vector v)
   {
      _commands = v;
   }

   /**
    * DOCUMENT ME!
    */
   public void execute()
   {
      for (int i = 0; i < _commands.size(); i++)
      {
         Command c = (Command) _commands.elementAt(i);
         c.execute();
      }
   }

   /**
    * DOCUMENT ME!
    */
   public void undo()
   {
      for (int i = 0; i < _commands.size(); i++)
      {
         Command c = (Command) _commands.elementAt(i);
         c.undo();
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param c DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public boolean isMergeableTo(Command c)
   {
      if (! (c instanceof Macro))
      {
         return false;
      }

      Macro m = (Macro) c;
      Vector v = m.getCommands();

      if (this.getCommands().size() != v.size())
      {
         return false;
      }

      for (int i = 0; i < this.getCommands().size(); i++)
      {
         Command c1 = (Command) this.getCommands().elementAt(i);
         Command c2 = (Command) v.elementAt(i);

         if (! (c1.isMergeableTo(c2)))
         {
            return false;
         }
      }

      return true;
   }

   /**
    * DOCUMENT ME!
    *
    * @param c DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Command mergeTo(Command c)
   {
      Macro m = (Macro) c;
      Vector v = m.getCommands();

      Vector vectorOfCommands = new Vector();
      Command ret = new Macro(vectorOfCommands);

      for (int i = 0; i < this.getCommands().size(); i++)
      {
         Command c1 = (Command) this.getCommands().elementAt(i);
         Command c2 = (Command) v.elementAt(i);
         Command c3 = c1.mergeTo(c2);
         vectorOfCommands.add(c3);
      }

      return ret;
   }
}

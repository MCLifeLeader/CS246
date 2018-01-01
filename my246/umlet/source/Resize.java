// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Resize.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class Resize
   extends Command
{
   /**
    * DOCUMENT ME!
    */
   int _where;

   /**
    * DOCUMENT ME!
    */
   int _diffx;

   /**
    * DOCUMENT ME!
    */
   int _diffy;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getX()
   {
      return _diffx;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getY()
   {
      return _diffy;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getWhere()
   {
      return _where;
   }

   /**
    * DOCUMENT ME!
    */
   Entity _entity;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Entity getEntity()
   {
      return _entity;
   }

   /**
    * Creates a new Resize object.
    *
    * @param entity DOCUMENT ME!
    * @param where DOCUMENT ME!
    * @param diffx DOCUMENT ME!
    * @param diffy DOCUMENT ME!
    */
   public Resize(Entity entity, int where, int diffx, int diffy)
   {
      _entity = entity;
      _where = where;
      _diffx = diffx;
      _diffy = diffy;
   }

   /**
    * DOCUMENT ME!
    */
   public void execute()
   {
      Entity e = _entity;

      if (_where == 1)
      {
         e.changeLocation(0, _diffy);
         e.changeSize(0, -_diffy);
      }
      else if (_where == 3)
      {
         e.changeLocation(0, _diffy);
         e.changeSize(_diffx, -_diffy);
      }
      else if (_where == 9)
      {
         e.changeLocation(_diffx, _diffy);
         e.changeSize(-_diffx, -_diffy);
      }
      else if (_where == 8)
      {
         e.changeLocation(_diffx, 0);
         e.changeSize(-_diffx, 0);
      }
      else if (_where == 12)
      {
         e.changeLocation(_diffx, 0);
         e.changeSize(-_diffx, _diffy);
      }
      else if (_where == 2)
      {
         e.changeSize(_diffx, 0);
      }
      else if (_where == 4)
      {
         e.changeSize(0, _diffy);
      }
      else if (_where == 6)
      {
         e.changeSize(_diffx, _diffy);
      }
   }

   /**
    * DOCUMENT ME!
    */
   public void undo()
   {
      Entity e = _entity;

      if (_where == 1)
      {
         e.changeLocation(0, -_diffy);
         e.changeSize(0, _diffy);
      }
      else if (_where == 3)
      {
         e.changeLocation(0, -_diffy);
         e.changeSize(-_diffx, _diffy);
      }
      else if (_where == 9)
      {
         e.changeLocation(-_diffx, -_diffy);
         e.changeSize(_diffx, _diffy);
      }
      else if (_where == 8)
      {
         e.changeLocation(-_diffx, 0);
         e.changeSize(_diffx, 0);
      }
      else if (_where == 12)
      {
         e.changeLocation(-_diffx, 0);
         e.changeSize(_diffx, -_diffy);
      }
      else if (_where == 2)
      {
         e.changeSize(-_diffx, 0);
      }
      else if (_where == 4)
      {
         e.changeSize(0, -_diffy);
      }
      else if (_where == 6)
      {
         e.changeSize(-_diffx, -_diffy);
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
      if (! (c instanceof Resize))
      {
         return false;
      }

      Resize r = (Resize) c;

      if (this.getEntity() != r.getEntity())
      {
         return false;
      }

      if (this.getWhere() != r.getWhere())
      {
         return false;
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
      Resize tmp = (Resize) c;
      Resize ret = new Resize(this.getEntity(), this.getWhere(),
            this.getX() + tmp.getX(), this.getY() + tmp.getY());

      return ret;
   }
}

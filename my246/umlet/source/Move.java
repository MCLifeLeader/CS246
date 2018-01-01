// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Move.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
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
public class Move
   extends Command
{
   //private Vector _entitiesToBeMoved;
   /**
    * DOCUMENT ME!
    */
   private Entity _entity;

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
    * DOCUMENT ME!
    */
   private int _x;

   /**
    * DOCUMENT ME!
    */
   private int _y;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getX()
   {
      return _x;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getY()
   {
      return _y;
   }

   /**
    * Creates a new Move object.
    *
    * @param e DOCUMENT ME!
    * @param x DOCUMENT ME!
    * @param y DOCUMENT ME!
    */
   public Move(Entity e, int x, int y)
   {
      _entity = e;
      _x = x;
      _y = y;
   }

   /**
    * DOCUMENT ME!
    */
   public void execute()
   {
      this.getEntity().changeLocation(_x, _y);
   }

   /**
    * DOCUMENT ME!
    */
   public void undo()
   {
      this.getEntity().changeLocation(-_x, -_y);
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
      if (! (c instanceof Move))
      {
         return false;
      }

      Move m = (Move) c;

      return this.getEntity() == m.getEntity();
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
      //System.out.println(Controller.getInstance().commands.size()+", ");
      Move m = (Move) c;
      Move ret = new Move(this.getEntity(), this.getX() + m.getX(),
            this.getY() + m.getY());

      return ret;
   }
}

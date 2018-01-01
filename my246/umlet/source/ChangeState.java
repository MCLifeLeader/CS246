// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/ChangeState.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class ChangeState
   extends Command
{
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
   private String _newState;

   /**
    * DOCUMENT ME!
    */
   private String _oldState;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String getNewState()
   {
      return _newState;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String getOldState()
   {
      return _oldState;
   }

   /**
    * Creates a new ChangeState object.
    *
    * @param e DOCUMENT ME!
    * @param oldState DOCUMENT ME!
    * @param newState DOCUMENT ME!
    */
   public ChangeState(Entity e, String oldState, String newState)
   {
      _entity = e;
      _newState = newState;
      _oldState = oldState;
   }

   /**
    * DOCUMENT ME!
    */
   public void execute()
   {
      _entity.setState(_newState);
      _entity.repaint();
   }

   /**
    * DOCUMENT ME!
    */
   public void undo()
   {
      _entity.setState(_oldState);
      _entity.repaint();
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
      if (! (c instanceof ChangeState))
      {
         return false;
      }

      ChangeState cs = (ChangeState) c;

      if (this.getEntity() != cs.getEntity())
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
      ChangeState tmp = (ChangeState) c;
      ChangeState ret = new ChangeState(this.getEntity(), tmp.getOldState(),
            this.getNewState());

      return ret;
   }
}

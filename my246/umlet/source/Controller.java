// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Controller.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.util.*;

/*import Move;
   import Frame;*/

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class Controller
{
   //TODO: Check to see if this can be changed to private
   /**
    * DOCUMENT ME!
    */
   public Vector commands = new Vector(); // auf private zur�cksetzen

   /**
    * DOCUMENT ME!
    */
   int _cursor = -1;

   /**
    * DOCUMENT ME!
    */
   private static Controller _instance = new Controller();

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static Controller getInstance()
   {
      //if (_instance == null)
      //{
      //   _instance = new Controller();
      //}
      return _instance;
   }

   /**
    * Creates a new Controller object.
    */
   private Controller()
   {
   }

   /**
    * DOCUMENT ME!
    *
    * @param newCommand DOCUMENT ME!
    */
   public void executeCommandWithoutUndo(Command newCommand)
   {
      newCommand.execute();
   }

   /**
    * DOCUMENT ME!
    *
    * @param newCommand DOCUMENT ME!
    */
   public void executeCommand(Command newCommand)
   {
      // Remove future commands
      for (int i = commands.size() - 1; i > _cursor; i--)
      {
         commands.removeElementAt(i);
      }

      commands.add(newCommand);
      newCommand.execute();

      if (commands.size() >= 2)
      {
         Command c_n;
         Command c_nMinus1;
         c_n = (Command) commands.elementAt(commands.size() - 1);
         c_nMinus1 = (Command) commands.elementAt(commands.size() - 2);

         if (c_n.isMergeableTo(c_nMinus1))
         {
            commands.removeElement(c_n);
            commands.removeElement(c_nMinus1);

            Command c = c_n.mergeTo(c_nMinus1);
            commands.add(c);
         }

         /*if ((c_n instanceof Macro) & (c_nMinus1 instanceof Macro)) {
            if (c_n.isMergeableTo(c_nMinus1)) {
              commands.removeElement(c_n);
              commands.removeElement(c_nMinus1);
                  Command c=c_n.mergeTo(c_nMinus1);
                  commands.add(c);
            }
            } else if ((c_n instanceof Move) & (c_nMinus1 instanceof Move)) {
              Move m_n=(Move)c_n;
              Move m_nMinus1=(Move)c_nMinus1;
                 if (m_n.isMergeableTo(m_nMinus1)) {
                   commands.removeElement(m_n);
                   commands.removeElement(m_nMinus1);
                  Command m=m_n.mergeTo(m_nMinus1);
                  commands.add(m);
                 }
               } else if ((c_n instanceof Resize) & (c_nMinus1 instanceof Resize)) {
                 Resize r_n=(Resize)c_n;
                 Resize r_nMinus1=(Resize)c_nMinus1;
                 if (r_n.getEntity()==r_nMinus1.getEntity()) {
                   if (r_n.getWhere()==r_nMinus1.getWhere()) {
                     commands.removeElement(r_n);
                     commands.removeElement(r_nMinus1);
                     Resize r=new Resize(r_n.getEntity(), r_n.getWhere(), r_n.getX()+r_nMinus1.getX(), r_n.getY()+r_nMinus1.getY());
                     commands.add(r);
                   }
                 }
               } else if ((c_n instanceof ChangeState) & (c_nMinus1 instanceof ChangeState)) {
                 ChangeState cs_n=(ChangeState)c_n;
                 ChangeState cs_nMinus1=(ChangeState)c_nMinus1;
                 if (cs_n.getEntity()==cs_nMinus1.getEntity()) {
                   commands.removeElement(cs_n);
                   commands.removeElement(cs_nMinus1);
                   ChangeState cs=new ChangeState(cs_n.getEntity(), cs_nMinus1.getOldState(), cs_n.getNewState());
                   commands.add(cs);
                 }
               } else if ((c_n instanceof MoveLinePoint) & (c_nMinus1 instanceof MoveLinePoint)) {
                 MoveLinePoint mlp_n=(MoveLinePoint)c_n;
                 MoveLinePoint mlp_nMinus1=(MoveLinePoint)c_nMinus1;
                 if (mlp_n.getLinePointId()==mlp_nMinus1.getLinePointId()) {
                   commands.removeElement(mlp_n);
                   commands.removeElement(mlp_nMinus1);
                   MoveLinePoint mlp=new MoveLinePoint(mlp_n.getRelation(), mlp_n.getLinePointId(), mlp_n.getDiffX()+mlp_nMinus1.getDiffX(), mlp_n.getDiffY()+mlp_nMinus1.getDiffY());
                   commands.add(mlp);
                 }
               }*/
      }

      _cursor = commands.size() - 1;

      //Frame.getInstance().displayMessage(""+_cursor+", ");
   }

   /**
    * DOCUMENT ME!
    */
   public void undo()
   {
      if (_cursor >= 0)
      {
         Command c = (Command) commands.elementAt(_cursor);
         c.undo();
         _cursor--;
      }

      //Frame.getInstance().displayMessage(""+_cursor+", ");
   }

   /**
    * DOCUMENT ME!
    */
   public void redo()
   {
      if (_cursor < (commands.size() - 1))
      {
         Command c = (Command) commands.elementAt(_cursor + 1);
         c.execute();
         _cursor++;
      }

      //Frame.getInstance().displayMessage(""+_cursor+", ");
   }
}

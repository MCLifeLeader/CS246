// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/UniversalListener.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.control;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.awt.*;
import java.awt.event.*;

import java.util.*;

import javax.swing.*;

/*import Move;*/

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class UniversalListener
   extends ComponentAdapter
   implements WindowListener,
      MouseListener,
      MouseMotionListener,
      ActionListener,
      KeyListener
{
   /**
    * DOCUMENT ME!
    */
   private Cursor lrCursor = new Cursor(Cursor.E_RESIZE_CURSOR);

   /**
    * DOCUMENT ME!
    */
   private Cursor tbCursor = new Cursor(Cursor.N_RESIZE_CURSOR);

   /**
    * DOCUMENT ME!
    */
   private Cursor nwCursor = new Cursor(Cursor.NW_RESIZE_CURSOR);

   /**
    * DOCUMENT ME!
    */
   private Cursor neCursor = new Cursor(Cursor.NE_RESIZE_CURSOR);

   /**
    * DOCUMENT ME!
    */
   private Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

   /**
    * DOCUMENT ME!
    */
   private Cursor defCursor = new Cursor(Cursor.DEFAULT_CURSOR);

   /**
    * DOCUMENT ME!
    */
   private Cursor crossCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);

   /**
    * DOCUMENT ME!
    */
   private boolean IS_DRAGGING = false;

   /**
    * DOCUMENT ME!
    */
   private boolean IS_DRAGGING_LINEPOINT = false;

   /**
    * DOCUMENT ME!
    */
   private boolean IS_RESIZING = false;

   /**
    * DOCUMENT ME!
    */
   private int LINEPOINT = -1;

   /**
    * DOCUMENT ME!
    */
   private int RESIZE_DIRECTION = 0;

   /**
    * DOCUMENT ME!
    */
   private boolean IS_FIRST_DRAGGING_OVER = false;

   /**
    * DOCUMENT ME!
    */
   private boolean IS_FIRST_RESIZE_OVER = false;

   /**
    * DOCUMENT ME!
    */
   private Vector ALL_MOVE_COMMANDS = null;

   /**
    * DOCUMENT ME!
    */
   private Vector ALL_RESIZE_COMMANDS = null;

   /**
    * DOCUMENT ME!
    */
   private static UniversalListener _instance = new UniversalListener();

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static UniversalListener getInstance()
   {
      //if (_instance == null) _instance = new UniversalListener();
      return _instance;
   }

   /**
    * DOCUMENT ME!
    */
   int _xOffset;

   /**
    * DOCUMENT ME!
    */
   int _yOffset;

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    */
   public void actionPerformed(ActionEvent e)
   {
      JMenuItem b = (JMenuItem) e.getSource();

      if (b.getText() == "Delete")
      {
         Vector v = Selector.getInstance().getSelectedEntitiesOnPanel();

         if (v.size() > 0)
         {
            Controller.getInstance().executeCommand(new RemoveElement(v));
         }
      }
      else if (b.getText() == "Undo")
      {
         Selector.getInstance().deselectAll();
         Controller.getInstance().undo();
      }
      else if (b.getText() == "Redo")
      {
         Controller.getInstance().redo();
      }
      else if (b.getText() == "Open")
      {
         Umlet.getInstance().doOpen();
      }
      else if (b.getText() == "Save")
      {
         Umlet.getInstance().doSave();
      }
      else if (b.getText() == "Save as..")
      {
         Umlet.getInstance().doSaveAs();
      }
      else if (b.getText() == "New")
      {
         Vector tmp = Selector.getInstance().getAllEntitiesOnPanel();
         Controller.getInstance().executeCommand(new RemoveElement(tmp));
      }
      else if (b.getText() == "Save as JPG..")
      {
         Umlet.getInstance().doSaveAsJPG();
      }
      else if (b.getText() == "Save as SVG..")
      {
         Umlet.getInstance().doSaveAsSvg();
      }
      else if (b.getText() == "Save as PDF..")
      {
         Umlet.getInstance().doSaveAsPdf();
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param me DOCUMENT ME!
    */
   public void mouseClicked(MouseEvent me)
   {
   }

   /**
    * DOCUMENT ME!
    *
    * @param me DOCUMENT ME!
    */
   public void mouseEntered(MouseEvent me)
   {
   }

   /**
    * DOCUMENT ME!
    *
    * @param me DOCUMENT ME!
    */
   public void mousePressed(MouseEvent me)
   {
      Component c = me.getComponent();

      if (me.getClickCount() == 2)
      {
         if (! (c instanceof Entity))
         {
            return;
         }

         //if (me.getComponent().getParent()==Frame.getInstance().getPanel()) return; // Ignore double click on panel
         Entity tmp = (Entity) me.getComponent();
         Entity e = tmp.cloneFromMe();
         Controller.getInstance().executeCommand(new AddEntity(e, 20, 20));
         Selector.getInstance().singleSelect(e, true);

         Container cont = e.getParent();
         cont.remove(e);
         cont.add(e, 0);

         return;
      }

      // Otherwise..
      if (c == Umlet.getInstance().getPanel())
      {
         _xOffset = me.getX();
         _yOffset = me.getY();
      }
      else
      {
         _xOffset = me.getX() + c.getX();
         _yOffset = me.getY() + c.getY();
      }

      if (c instanceof Relation)
      {
         Relation rel = (Relation) c;
         int where = rel.getLinePoint(new Point(me.getX(), me.getY()));

         if (where >= 0)
         {
            IS_DRAGGING_LINEPOINT = true;
            LINEPOINT = where;
         }
      }

      if (c instanceof Entity)
      {
         Entity e = (Entity) c;
         int ra = e.getResizeArea(me.getX(), me.getY());

         if (ra != 0)
         {
            IS_RESIZING = true;
            RESIZE_DIRECTION = ra;

            return;
         }
         else
         {
            IS_DRAGGING = true;

            int mod = me.getModifiers();

            if ((mod & me.CTRL_MASK) != 0)
            {
               if (e.isSelected())
               {
                  Selector.getInstance().deselect(e);
               }
               else
               {
                  Selector.getInstance().singleSelect(e, false);
               }
            }
            else
            {
               Selector.getInstance().singleSelect(e, true);

               if (Selector.getInstance()
                               .hasSameSelectionPattern(e, me.getX(), me.getY()))
               {
                  Container cont = e.getParent();
                  cont.remove(e);
                  cont.add(e, -1);
               }
               else
               {
                  Container cont = e.getParent();
                  cont.remove(e);
                  cont.add(e, 0);
               }

               Selector.getInstance()
                       .setSingleSelectorInformation(e, me.getX(), me.getY());
            }
         }
      }
      else
      {
         // Otherwise
         Selector.getInstance().deselectAll();

         //Frame.getInstance().getJspMain().requestFocus();
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param me DOCUMENT ME!
    */
   public void mouseReleased(MouseEvent me)
   {
      if (IS_DRAGGING_LINEPOINT & (LINEPOINT >= 0))
      {
         Relation rel = (Relation) me.getComponent();

         if (rel.isOnLine(LINEPOINT))
         {
            Controller.getInstance()
                      .executeCommand(new RemoveLinePoint(rel, LINEPOINT));
         }
      }

      IS_DRAGGING = false;
      IS_DRAGGING_LINEPOINT = false;
      IS_RESIZING = false;
      IS_FIRST_DRAGGING_OVER = false;
      IS_FIRST_RESIZE_OVER = false;
      ALL_MOVE_COMMANDS = null;
      ALL_RESIZE_COMMANDS = null;
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    */
   public void mouseExited(MouseEvent e)
   {
   }

   /**
    * DOCUMENT ME!
    *
    * @param me DOCUMENT ME!
    */
   public void mouseDragged(MouseEvent me)
   {
      // Get new mouse coordinates
      Component c = me.getComponent();
      int xNewOffset;
      int yNewOffset;

      if (c == Umlet.getInstance().getPanel())
      {
         xNewOffset = me.getX();
         yNewOffset = me.getY();
      }
      else
      {
         xNewOffset = me.getX() + c.getX();
         yNewOffset = me.getY() + c.getY();
      }

      int MAIN_UNIT = Umlet.getInstance().getMainUnit();

      int new_x_eff = MAIN_UNIT * ((xNewOffset - (MAIN_UNIT / 2)) / MAIN_UNIT);
      int new_y_eff = MAIN_UNIT * ((yNewOffset - (MAIN_UNIT / 2)) / MAIN_UNIT);
      int old_x_eff = MAIN_UNIT * ((_xOffset - (MAIN_UNIT / 2)) / MAIN_UNIT);
      int old_y_eff = MAIN_UNIT * ((_yOffset - (MAIN_UNIT / 2)) / MAIN_UNIT);

      //delta
      int delta_x = 0;
      int delta_y = 0;

      if (IS_RESIZING)
      {
         if ((RESIZE_DIRECTION == 3) || (RESIZE_DIRECTION == 2)
                || (RESIZE_DIRECTION == 6))
         {
            delta_x = (c.getX() + c.getWidth()) % MAIN_UNIT;
         }

         if ((RESIZE_DIRECTION == 12) || (RESIZE_DIRECTION == 4)
                || (RESIZE_DIRECTION == 6))
         {
            delta_y = (c.getY() + c.getHeight()) % MAIN_UNIT;
         }
      }
      else if (IS_DRAGGING_LINEPOINT)
      {
         Relation r = (Relation) c;
         Vector tmp = r.getLinePoints();
         Point p = (Point) tmp.elementAt(LINEPOINT);
         delta_x = (r.getX() + p.x) % MAIN_UNIT;
         delta_y = (r.getY() + p.y) % MAIN_UNIT;
      }

      int diffx = new_x_eff - old_x_eff - delta_x;
      int diffy = new_y_eff - old_y_eff - delta_y;

      _xOffset = xNewOffset;
      _yOffset = yNewOffset;

      if (c instanceof Relation)
      {
         Relation r = (Relation) c;

         if (IS_DRAGGING_LINEPOINT & (LINEPOINT >= 0))
         {
            Controller.getInstance()
                      .executeCommand(new MoveLinePoint(r, LINEPOINT, diffx,
                  diffy));

            return;
         }

         int where = r.getLinePoint(new Point(me.getX(), me.getY()));

         if (where >= 0)
         {
            IS_DRAGGING_LINEPOINT = true;
            LINEPOINT = where;
            Controller.getInstance()
                      .executeCommand(new MoveLinePoint(r, where, diffx, diffy));

            return;
         }
         else
         {
            Point p = new Point(me.getX(), me.getY());
            int ins = r.getWhereToInsert(p);

            if (ins > 0)
            {
               IS_DRAGGING_LINEPOINT = true;
               LINEPOINT = ins;
               Controller.getInstance()
                         .executeCommand(new InsertLinePoint(r, ins, me.getX(),
                     me.getY()));

               return;
            }
         }
      }

      if (IS_DRAGGING_LINEPOINT)
      {
         return;
      }

      if (c instanceof Entity)
      {
         Entity e = (Entity) c;

         if (IS_DRAGGING == true)
         {
            if (IS_FIRST_DRAGGING_OVER == false)
            {
               Vector entitiesToBeMoved = new Vector(Selector.getInstance()
                                                             .getSelectedEntities());

               //Vector allRelations=Selector.getInstance().getAllRelationsOnPanel();
               Vector moveCommands = new Vector();

               for (int i = 0; i < entitiesToBeMoved.size(); i++)
               {
                  Entity tmpEntity = (Entity) entitiesToBeMoved.elementAt(i);
                  moveCommands.add(new Move(tmpEntity, diffx, diffy));
               }

               Vector linepointCommands = new Vector();

               for (int i = 0; i < entitiesToBeMoved.size(); i++)
               {
                  Entity tmpEntity = (Entity) entitiesToBeMoved.elementAt(i);

                  if (tmpEntity instanceof Relation)
                  {
                     continue;
                  }

                  Vector affectedRelationPoints = tmpEntity
                     .getAffectedRelationLinePoints(15);

                  for (int j = 0; j < affectedRelationPoints.size(); j++)
                  {
                     RelationLinePoint tmpRlp = (RelationLinePoint) affectedRelationPoints
                        .elementAt(j);

                     if (entitiesToBeMoved.contains(tmpRlp.getRelation()))
                     {
                        continue;
                     }

                     linepointCommands.add(new MoveLinePoint(
                           tmpRlp.getRelation(), tmpRlp.getLinePointId(),
                           diffx, diffy));
                  }
               }

               Vector allCommands = new Vector();
               allCommands.addAll(moveCommands);
               allCommands.addAll(linepointCommands);

               ALL_MOVE_COMMANDS = allCommands;
               IS_FIRST_DRAGGING_OVER = true;
            }
            else
            {
               Vector tmpVector = new Vector();

               for (int i = 0; i < ALL_MOVE_COMMANDS.size(); i++)
               {
                  Command tmpCommand = (Command) ALL_MOVE_COMMANDS.elementAt(i);

                  if (tmpCommand instanceof Move)
                  {
                     Move m = (Move) tmpCommand;
                     tmpVector.add(new Move(m.getEntity(), diffx, diffy));
                  }
                  else if (tmpCommand instanceof MoveLinePoint)
                  {
                     MoveLinePoint m = (MoveLinePoint) tmpCommand;
                     tmpVector.add(new MoveLinePoint(m.getRelation(),
                           m.getLinePointId(), diffx, diffy));
                  }
               }

               ALL_MOVE_COMMANDS = tmpVector;
            }

            Controller.getInstance().executeCommand(new Macro(ALL_MOVE_COMMANDS));
         }
         else if (IS_RESIZING == true)
         {
            if ((RESIZE_DIRECTION == 9) || (RESIZE_DIRECTION == 8)
                   || (RESIZE_DIRECTION == 12))
            {
               if ((e.getWidth() - diffx) < Constants.getFontsize())
               {
                  return;
               }
               else if (RESIZE_DIRECTION == 8)
               {
                  diffy = 0;
               }
            }

            if ((RESIZE_DIRECTION == 3) || (RESIZE_DIRECTION == 2)
                   || (RESIZE_DIRECTION == 6))
            {
               if ((e.getWidth() + diffx) < Constants.getFontsize())
               {
                  return;
               }
               else if (RESIZE_DIRECTION == 2)
               {
                  diffy = 0;
               }
            }

            if ((RESIZE_DIRECTION == 9) || (RESIZE_DIRECTION == 1)
                   || (RESIZE_DIRECTION == 3))
            {
               if ((e.getHeight() - diffy) < Constants.getFontsize())
               {
                  return;
               }
               else if (RESIZE_DIRECTION == 1)
               {
                  diffx = 0;
               }
            }

            if ((RESIZE_DIRECTION == 12) || (RESIZE_DIRECTION == 4)
                   || (RESIZE_DIRECTION == 6))
            {
               if ((e.getHeight() + diffy) < Constants.getFontsize())
               {
                  return;
               }
               else if (RESIZE_DIRECTION == 4)
               {
                  diffx = 0;
               }
            }

            Vector resizeCommands = new Vector();
            resizeCommands.add(new Resize(e, RESIZE_DIRECTION, diffx, diffy));

            if (IS_FIRST_RESIZE_OVER == false)
            {
               ALL_RESIZE_COMMANDS = new Vector();

               Vector affectedRelationLinePoints = e
                  .getAffectedRelationLinePoints(RESIZE_DIRECTION);

               for (int i = 0; i < affectedRelationLinePoints.size(); i++)
               {
                  RelationLinePoint rlp = (RelationLinePoint) affectedRelationLinePoints
                     .elementAt(i);
                  ALL_RESIZE_COMMANDS.add(new MoveLinePoint(rlp.getRelation(),
                        rlp.getLinePointId(), diffx, diffy));
               }

               IS_FIRST_RESIZE_OVER = true;
            }
            else
            {
               Vector tmpVector = new Vector();

               for (int i = 0; i < ALL_RESIZE_COMMANDS.size(); i++)
               {
                  Command tmpCommand = (Command) ALL_RESIZE_COMMANDS.elementAt(i);
                  MoveLinePoint m = (MoveLinePoint) tmpCommand;
                  tmpVector.add(new MoveLinePoint(m.getRelation(),
                        m.getLinePointId(), diffx, diffy));
               }

               ALL_RESIZE_COMMANDS = tmpVector;
            }

            resizeCommands.addAll(ALL_RESIZE_COMMANDS);
            Controller.getInstance().executeCommand(new Macro(resizeCommands));
         }
      }
      else if (c instanceof JPanel)
      {
         Vector v = Selector.getInstance().getAllEntitiesOnPanel();
         Vector moveCommands = new Vector();

         for (int i = 0; i < v.size(); i++)
         {
            Entity e = (Entity) v.elementAt(i);
            moveCommands.add(new Move(e, diffx, diffy));
         }

         Controller.getInstance().executeCommand(new Macro(moveCommands));
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param me DOCUMENT ME!
    */
   public void mouseMoved(MouseEvent me)
   {
      //Frame.getInstance().setTitle(""+me.getX()+", "+me.getY());
      Component c = me.getComponent();

      if (c instanceof Relation)
      {
         Relation rel = (Relation) c;
         int where = rel.getLinePoint(new Point(me.getX(), me.getY()));

         if (where >= 0)
         {
            Umlet.getInstance().setCursor(handCursor);
         }
         else
         {
            Umlet.getInstance().setCursor(crossCursor);
         }

         return;
      }

      if (c instanceof Entity)
      {
         Entity e = (Entity) c;
         int ra = e.getResizeArea(me.getX(), me.getY());

         if (0 != ra)
         {
            if ((ra == 1) | (ra == 4))
            {
               Umlet.getInstance().setCursor(tbCursor);
            }

            if ((ra == 2) | (ra == 8))
            {
               Umlet.getInstance().setCursor(lrCursor);
            }

            if ((ra == 3) | (ra == 12))
            {
               Umlet.getInstance().setCursor(neCursor);
            }

            if ((ra == 6) | (ra == 9))
            {
               Umlet.getInstance().setCursor(nwCursor);
            }
         }
         else
         {
            Umlet.getInstance().setCursor(handCursor);
         }
      }
      else
      {
         Umlet.getInstance().setCursor(defCursor);
      }
   }

   /**
    * Creates a new UniversalListener object.
    */
   private UniversalListener()
   {
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    */
   public void keyPressed(KeyEvent e)
   {
   }

   /**
    * DOCUMENT ME!
    *
    * @param ke DOCUMENT ME!
    */
   public void keyReleased(KeyEvent ke)
   {
      Component c = ke.getComponent();

      if (c == Umlet.getInstance().getPropertyPanel())
      {
         String s = Umlet.getInstance().getPropertyString();
         Entity e = Umlet.getInstance().getEditedEntity();

         if (e != null)
         {
            Controller.getInstance()
                      .executeCommand(new ChangeState(e,
                  e.getPanelAttributes(), s));
         }

         ke.consume();
      }
      else
      {
         /*if (ke.getKeyCode()==ke.VK_DELETE || ke.getKeyCode()==ke.VK_BACK_SPACE) {
            Vector v=Selector.getInstance().getSelectedEntitiesOnPanel();
            if (v.size()>0) {
              Controller.getInstance().executeCommand(new RemoveElement(v));
            }
            } else if (ke.getKeyChar()=='z') {
              Selector.getInstance().deselectAll();
              Controller.getInstance().undo();
            } else if (ke.getKeyChar()=='y') {
              Controller.getInstance().redo();
            }
          */
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param ke DOCUMENT ME!
    */
   public void keyTyped(KeyEvent ke)
   {
   }

   /**
    * DOCUMENT ME!
    *
    * @param ce DOCUMENT ME!
    */
   public void componentResized(ComponentEvent ce)
   {
      if (ce.getComponent() == Umlet.getInstance())
      {
         Rectangle rOld = Umlet.getInstance().getOldBounds();
         Rectangle rNew = Umlet.getInstance().getBounds();
         int diffX = (int) (rNew.getWidth() - rOld.getWidth());
         int diffY = (int) (rNew.getHeight() - rOld.getHeight());
         Umlet.getInstance()
              .setLrSeparatorLocation(Umlet.getInstance().getJspMain()
                                           .getDividerLocation() + diffX);
         Umlet.getInstance()
              .setTbSeparatorLocation(Umlet.getInstance().getJspRight()
                                           .getDividerLocation() + diffY);
         Umlet.getInstance().setOldBounds(rNew);
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    */
   public void windowActivated(WindowEvent e)
   {
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    */
   public void windowClosed(WindowEvent e)
   {
      System.exit(0);
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    */
   public void windowClosing(WindowEvent e)
   {
      System.exit(0);
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    */
   public void windowDeactivated(WindowEvent e)
   {
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    */
   public void windowDeiconified(WindowEvent e)
   {
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    */
   public void windowIconified(WindowEvent e)
   {
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    */
   public void windowOpened(WindowEvent e)
   {
   }
}

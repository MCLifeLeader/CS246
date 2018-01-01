// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/Relation.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.element.base;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.*;

import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class Relation
   extends Entity
{
   /**
    * DOCUMENT ME!
    */
   String beginQualifier;

   /**
    * DOCUMENT ME!
    */
   String endQualifier;

   /**
    * DOCUMENT ME!
    */
   String beginArrow;

   /**
    * DOCUMENT ME!
    */
   String endArrow;

   /**
    * DOCUMENT ME!
    */
   String beginMultiplicity;

   /**
    * DOCUMENT ME!
    */
   String endMultiplicity;

   /**
    * DOCUMENT ME!
    */
   String beginRole;

   /**
    * DOCUMENT ME!
    */
   String endRole;

   /**
    * DOCUMENT ME!
    */
   String lineType;

   /**
    * DOCUMENT ME!
    */
   Vector _strings;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   private Vector getStrings()
   {
      if (_strings == null)
      {
         _strings = new Vector();
      }

      return _strings;
   }

   /**
    * DOCUMENT ME!
    *
    * @param v DOCUMENT ME!
    */
   private void setStrings(Vector v)
   {
      _strings = v;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   private Point getCenterOfLine()
   {
      Point ret = new Point();

      if ((this.getLinePoints().size() % 2) == 1)
      {
         ret = (Point) this.getLinePoints()
                           .elementAt(this.getLinePoints().size() / 2);
      }
      else
      {
         Point p1 = (Point) this.getLinePoints()
                                .elementAt(this.getLinePoints().size() / 2);
         Point p2 = (Point) this.getLinePoints()
                                .elementAt((this.getLinePoints().size() / 2)
               - 1);
         ret.x = (p1.x + p2.x) / 2;
         ret.y = (p1.y + p2.y) / 2;
      }

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public String getAdditionalAttributes()
   {
      Vector tmp = new Vector();

      //tmp.add(beginQualifier);
      //tmp.add(endQualifier);
      //tmp.add(beginArrow);
      //tmp.add(endArrow);
      //tmp.add(beginMultiplicity);
      //tmp.add(endMultiplicity);
      //tmp.add(beginRole);
      //tmp.add(endRole);
      //tmp.add(lineType);

      /*tmp.add(""+this.getX());
         tmp.add(""+this.getY());
         tmp.add(""+this.getWidth());
         tmp.add(""+this.getHeight());*/
      for (int i = 0; i < this.getLinePoints().size(); i++)
      {
         Point p = (Point) this.getLinePoints().elementAt(i);
         String s1 = "" + p.x;
         String s2 = "" + p.y;
         tmp.add(s1);
         tmp.add(s2);
      }

      String ret = Constants.composeStrings(tmp,
            Constants.DELIMITER_ADDITIONAL_ATTRIBUTES);

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @param s DOCUMENT ME!
    */
   public void setHiddenState(String s)
   {
      Vector tmp = Constants.decomposeStringsIncludingEmptyStrings(s,
            Constants.DELIMITER_FIELDS);

      // 9 attributes, 4 for start/end point, 4 for x,y,w,h
      if (tmp.size() < 17)
      {
         throw new RuntimeException(
            "UMLet: Error in Relation.setHiddenState(), state value = " + s);
      }

      beginQualifier = (String) tmp.elementAt(0);
      endQualifier = (String) tmp.elementAt(1);
      beginArrow = (String) tmp.elementAt(2);
      endArrow = (String) tmp.elementAt(3);
      beginMultiplicity = (String) tmp.elementAt(4);
      endMultiplicity = (String) tmp.elementAt(5);
      beginRole = (String) tmp.elementAt(6);
      endRole = (String) tmp.elementAt(7);
      lineType = (String) tmp.elementAt(8);

      int x = Integer.parseInt((String) tmp.elementAt(9));
      int y = Integer.parseInt((String) tmp.elementAt(10));
      int w = Integer.parseInt((String) tmp.elementAt(11));
      int h = Integer.parseInt((String) tmp.elementAt(12));
      this.setBounds(x, y, w, h);

      for (int i = 13; i < tmp.size(); i = i + 2)
      {
         int xx = Integer.parseInt((String) tmp.elementAt(i));
         int yy = Integer.parseInt((String) tmp.elementAt(i + 1));
         Point p = new Point(xx, yy);
         this.getLinePoints().add(p);
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param s DOCUMENT ME!
    */
   public void setAdditionalAttributes(String s)
   {
      Vector tmp = Constants.decomposeStringsIncludingEmptyStrings(s,
            Constants.DELIMITER_ADDITIONAL_ATTRIBUTES);

      // 9 attributes, 4 for start/end point, 4 for x,y,w,h
      /*if (tmp.size()<17) throw new RuntimeException("UMLet: Error in Relation.setHiddenState(), state value = "+s);
      
                      beginQualifier=(String)tmp.elementAt(0);
                      endQualifier=(String)tmp.elementAt(1);
                      beginArrow=(String)tmp.elementAt(2);
                      endArrow=(String)tmp.elementAt(3);
                      beginMultiplicity=(String)tmp.elementAt(4);
                      endMultiplicity=(String)tmp.elementAt(5);
                      beginRole=(String)tmp.elementAt(6);
                      endRole=(String)tmp.elementAt(7);
                      lineType=(String)tmp.elementAt(8);
      
                        int x=Integer.parseInt((String)tmp.elementAt(9));
                        int y=Integer.parseInt((String)tmp.elementAt(10));
                        int w=Integer.parseInt((String)tmp.elementAt(11));
                        int h=Integer.parseInt((String)tmp.elementAt(12));
                        this.setBounds(x,y,w,h);*/
      for (int i = 0; i < tmp.size(); i = i + 2)
      {
         int xx = Integer.parseInt((String) tmp.elementAt(i));
         int yy = Integer.parseInt((String) tmp.elementAt(i + 1));
         Point p = new Point(xx, yy);
         this.getLinePoints().add(p);
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @param state DOCUMENT ME!
    */
   public void setState(String state)
   {
      beginQualifier = "";
      endQualifier = "";
      beginArrow = "";
      endArrow = "";
      beginMultiplicity = "";
      endMultiplicity = "";
      beginRole = "";
      endRole = "";
      lineType = "-";
      this.setStrings(null);

      _state = state;

      Vector tmp = Constants.decomposeStrings(state, "\n");

      for (int i = 0; i < tmp.size(); i++)
      {
         String s = (String) tmp.elementAt(i);

         if (s.startsWith("q1=") & (s.length() > 3))
         {
            beginQualifier = s.substring(3, s.length());
         }
         else if (s.startsWith("q2=") & (s.length() > 3))
         {
            endQualifier = s.substring(3, s.length());
         }
         else if (s.startsWith("m1=") & (s.length() > 3))
         {
            beginMultiplicity = s.substring(3, s.length());
         }
         else if (s.startsWith("m2=") & (s.length() > 3))
         {
            endMultiplicity = s.substring(3, s.length());
         }
         else if (s.startsWith("r1=") & (s.length() > 3))
         {
            beginRole = s.substring(3, s.length());
         }
         else if (s.startsWith("r2=") & (s.length() > 3))
         {
            endRole = s.substring(3, s.length());
         }
         else if (s.startsWith("lt=") & (s.length() > 3))
         {
            if (s.indexOf("<<<<") >= 0)
            {
               beginArrow = "X";
            }
            else if (s.indexOf("<<<") >= 0)
            {
               beginArrow = "x";
            }
            else if (s.indexOf("<<") >= 0)
            {
               beginArrow = "<<";
            }
            else if (s.indexOf("<") >= 0)
            {
               beginArrow = "<";
            }

            if (s.indexOf(">>>>") >= 0)
            {
               endArrow = "X";
            }
            else if (s.indexOf(">>>") >= 0)
            {
               endArrow = "x";
            }
            else if (s.indexOf(">>") >= 0)
            {
               endArrow = "<<";
            }
            else if (s.indexOf(">") >= 0)
            {
               endArrow = "<";
            }

            if (s.indexOf(".") >= 0)
            {
               lineType = ".";
            }
         }
         else
         {
            this.getStrings().add(s);
         }
      }
   }

   // Created objects have no sideeffects
   /**
    * DOCUMENT ME!
    *
    * @param r DOCUMENT ME!
    * @param points DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static Vector getIntersectingLineSegment(Area r, Vector points)
   {
      Vector ret = new Vector();

      // If no segment found, take last two points
      Point pp_end = (Point) points.elementAt(points.size() - 1);
      Point pp_start = (Point) points.elementAt(points.size() - 2);

      for (int i = 1; i < points.size(); i++)
      {
         pp_end = (Point) points.elementAt(i);

         if (! r.contains(pp_end))
         {
            // End point of intersecting line found
            pp_start = (Point) points.elementAt(i - 1);

            ret.add(pp_start);
            ret.add(pp_end);

            return ret;
         }
      }

      ret.add(pp_start);
      ret.add(pp_end);

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @param rFixed DOCUMENT ME!
    * @param rMovable DOCUMENT ME!
    * @param pStart DOCUMENT ME!
    * @param pEnd DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static Point moveNextTo(Area rFixed, Rectangle rMovable,
      Point pStart, Point pEnd)
   {
      // These ints can simply be added to line
      int centerDiffX = (int) -rMovable.getWidth() / 2;
      int centerDiffY = (int) -rMovable.getHeight() / 2;

      int vectorX = pEnd.x - pStart.x;
      int vectorY = pEnd.y - pStart.y;

      int startx = pStart.x;
      int starty = pStart.y;
      int endx = pEnd.x;
      int endy = pEnd.y;

      for (int i = 0;; i++)
      {
         endx += vectorX;
         endy += vectorY;
         rMovable.setLocation(endx + centerDiffX, endy + centerDiffY);

         if (! rFixed.intersects(rMovable))
         {
            break;
         }
      }

      int newx = 0;
      int newy = 0;

      for (int i = 0; i < 10; i++)
      {
         newx = (endx + startx) / 2;
         newy = (endy + starty) / 2;
         rMovable.setLocation(newx + centerDiffX, newy + centerDiffY);

         if (rFixed.intersects(rMovable))
         {
            startx = newx;
            starty = newy;
         }
         else
         {
            endx = newx;
            endy = newy;
         }
      }

      Point ret = new Point(newx + centerDiffX, newy + centerDiffY);

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @param shapes DOCUMENT ME!
    * @param points DOCUMENT ME!
    * @param hotspotx DOCUMENT ME!
    * @param hotspoty DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public static boolean lineUp(Vector shapes, Vector points, int hotspotx,
      int hotspoty)
   {
      // Remove point with the same coordinates
      for (int i = points.size() - 1; i > 0; i--)
      {
         Point p1 = (Point) points.elementAt(i);
         Point p2 = (Point) points.elementAt(i - 1);

         if ((p1.x == p2.x) & (p1.y == p2.y))
         {
            points.removeElementAt(i);
         }
      }

      if (points.size() <= 1)
      {
         return false;
      }

      if (shapes.size() <= 1)
      {
         return true;
      }

      // Vector ret=new Vector();

      //Rectangle rFixed;
      Rectangle rMovable;
      Area tmpArea = new Area();

      for (int i = 0; i < (shapes.size() - 1); i++)
      {
         Rectangle r = (Rectangle) shapes.elementAt(i);

         if (i == 0)
         { // The hotspot of the first element is set

            Point p = (Point) points.elementAt(0);
            r.setLocation(p.x - hotspotx, p.y - hotspoty);
         }

         Area a = new Area(r);
         tmpArea.add(a);

         //rFixed=(Rectangle)shapes.elementAt(i);
         rMovable = (Rectangle) shapes.elementAt(i + 1);

         /*if (i==0) { // The hotspot of the first element is set
            Point p=(Point)points.elementAt(0);
            rFixed.setLocation(p.x-hotspotx,p.y-hotspoty);
            }*/
         Vector tmp = getIntersectingLineSegment(tmpArea, points);
         Point startIntersectingLine = (Point) tmp.elementAt(0);
         Point endIntersectingLine = (Point) tmp.elementAt(1);

         Point res = moveNextTo(tmpArea, rMovable, startIntersectingLine,
               endIntersectingLine);

         //ret.add(res);
         if (rMovable instanceof Arrow)
         {
            Arrow arrow = (Arrow) rMovable;

            Point diffA = new Point(-startIntersectingLine.x
                  + endIntersectingLine.x,
                  -startIntersectingLine.y + endIntersectingLine.y);
            Point diffB1 = new Point(diffA.y, -diffA.x);
            Point diffB2 = new Point(-diffB1.x, -diffB1.y);
            Point a1 = new Point((2 * diffA.x) + diffB1.x,
                  (2 * diffA.y) + diffB1.y);
            Point a2 = new Point((2 * diffA.x) + diffB2.x,
                  (2 * diffA.y) + diffB2.y);

            a1 = Constants.normalize(a1, Constants.getFontsize());
            a2 = Constants.normalize(a2, Constants.getFontsize());

            arrow.setArrowEndA(a1);
            arrow.setArrowEndB(a2);
         }

         // ATTENTION: this Recangle will become the rFixed in the next loop
         rMovable.setLocation(res);
      }

      return true;
   }

   /**
    * DOCUMENT ME!
    *
    * @param i DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public boolean isOnLine(int i)
   {
      if (((i - 1) >= 0) & ((i + 1) < getLinePoints().size()))
      {
         Point x1 = (Point) getLinePoints().elementAt(i - 1);
         Point x2 = (Point) getLinePoints().elementAt(i + 1);
         Point p = (Point) getLinePoints().elementAt(i);

         if ((pyth(p, x1) + pyth(p, x2)) < (pyth(x1, x2) + 5))
         {
            return true;
         }
      }

      return false;
   }

   /**
    * DOCUMENT ME!
    *
    * @param p DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getWhereToInsert(Point p)
   {
      for (int i = 0; i < (getLinePoints().size() - 1); i++)
      {
         Point x1 = (Point) getLinePoints().elementAt(i);
         Point x2 = (Point) getLinePoints().elementAt(i + 1);

         if ((pyth(p, x1) + pyth(p, x2)) < (pyth(x1, x2) + 5))
         {
            return i + 1;
         }
      }

      return -1;
   }

   /**
    * DOCUMENT ME!
    *
    * @param p DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getLinePoint(Point p)
   {
      for (int i = 0; i < getLinePoints().size(); i++)
      {
         Point x = (Point) getLinePoints().elementAt(i);

         if (pyth(p, x) < 15)
         {
            return i;
         }
      }

      return -1;
   }

   /**
    * DOCUMENT ME!
    *
    * @param x1 DOCUMENT ME!
    * @param x2 DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   private int pyth(Point x1, Point x2)
   {
      int a = x1.x - x2.x;
      int b = x1.y - x2.y;

      return (int) Math.sqrt((a * a) + (b * b));
   }

   /**
    * DOCUMENT ME!
    *
    * @param v DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   private Vector flipVector(Vector v)
   {
      Vector ret = new Vector();

      for (int i = v.size() - 1; i >= 0; i--)
      {
         ret.add(v.elementAt(i));
      }

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @param p DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public boolean contains(Point p)
   {
      for (int i = 0; i < (getLinePoints().size() - 1); i++)
      {
         Point x1 = (Point) getLinePoints().elementAt(i);
         Point x2 = (Point) getLinePoints().elementAt(i + 1);

         if ((pyth(p, x1) + pyth(p, x2)) < (pyth(x1, x2) + 5))
         {
            return true;
         }
      }

      for (int i = 0; i < getLinePoints().size(); i++)
      {
         Point x = (Point) getLinePoints().elementAt(i);

         if (pyth(p, x) < 11)
         {
            return true;
         }
      }

      return false;
   }

   /**
    * DOCUMENT ME!
    *
    * @param x DOCUMENT ME!
    * @param y DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public boolean contains(int x, int y)
   {
      return contains(new Point(x, y));
   }

   /**
    * DOCUMENT ME!
    */
   private Vector _points;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Vector getLinePoints()
   {
      if (_points == null)
      {
         _points = new Vector();
      }

      return _points;
   }

   /**
    * DOCUMENT ME!
    *
    * @param v DOCUMENT ME!
    */
   public void setLinePoints(Vector v)
   {
      _points = v;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Entity cloneFromMe()
   {
      Relation c = new Relation();

      c.setState(this.getPanelAttributes());
      c.setAdditionalAttributes(this.getAdditionalAttributes());

      c.setVisible(true);
      c.setBounds(this.getBounds());

      return c;
   }

   /**
    * Creates a new Relation object.
    */
   public Relation()
   {
      super();
   }

   /**
    * Creates a new Relation object.
    *
    * @param s DOCUMENT ME!
    */
   public Relation(String s)
   {
      super(s);
   }

   /**
    * DOCUMENT ME!
    *
    * @param g DOCUMENT ME!
    */
   public void paint(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      g2.setFont(Constants.getFont());
      g2.setColor(_activeColor);
      Constants.getFRC(g2); // Just to set anti-aliasing, even if no text operations occur

      Vector startShapes = new Vector();
      Vector endShapes = new Vector();

      startShapes.add(new NoShape());
      endShapes.add(new NoShape());

      if ((beginQualifier != null) && (beginQualifier.length() > 0))
      {
         TextLayout tl = new TextLayout(beginQualifier, Constants.getFont(),
               Constants.getFRC(g2));
         Qualifier q = new Qualifier(beginQualifier, 0, 0,
               (int) tl.getBounds().getWidth() + (Constants.getFontsize() * 2),
               (int) tl.getBounds().getHeight() + (Constants.getFontsize() / 2));
         startShapes.add(q);
      }

      if ((endQualifier != null) && (endQualifier.length() > 0))
      {
         TextLayout tl = new TextLayout(endQualifier, Constants.getFont(),
               Constants.getFRC(g2));
         Qualifier q = new Qualifier(endQualifier, 0, 0,
               (int) tl.getBounds().getWidth() + (Constants.getFontsize() * 2),
               (int) tl.getBounds().getHeight() + (Constants.getFontsize() / 2));
         endShapes.add(q);
      }

      if ((beginArrow != null) && (beginArrow.length() > 0))
      {
         Arrow a = new Arrow(beginArrow);
         startShapes.add(a);
      }

      if ((endArrow != null) && (endArrow.length() > 0))
      {
         Arrow a = new Arrow(endArrow);
         endShapes.add(a);
      }

      if ((beginMultiplicity != null) && (beginMultiplicity.length() > 0))
      {
         EmptyShape e = new EmptyShape();
         startShapes.add(e);

         TextLayout tl = new TextLayout(beginMultiplicity, Constants.getFont(),
               Constants.getFRC(g2));
         Multiplicity m = new Multiplicity(beginMultiplicity, 0, 0,
               (int) tl.getBounds().getWidth(), (int) tl.getBounds().getHeight());
         startShapes.add(m);
      }

      if ((endMultiplicity != null) && (endMultiplicity.length() > 0))
      {
         EmptyShape e = new EmptyShape();
         endShapes.add(e);

         TextLayout tl = new TextLayout(endMultiplicity, Constants.getFont(),
               Constants.getFRC(g2));
         Multiplicity m = new Multiplicity(endMultiplicity, 0, 0,
               (int) tl.getBounds().getWidth(), (int) tl.getBounds().getHeight());
         endShapes.add(m);
      }

      if ((beginRole != null) && (beginRole.length() > 0))
      {
         EmptyShape e = new EmptyShape();
         startShapes.add(e);

         TextLayout tl = new TextLayout(beginRole, Constants.getFont(),
               Constants.getFRC(g2));
         Role r = new Role(beginRole, 0, 0, (int) tl.getBounds().getWidth(),
               (int) tl.getBounds().getHeight());
         startShapes.add(r);
      }

      if ((endRole != null) && (endRole.length() > 0))
      {
         EmptyShape e = new EmptyShape();
         endShapes.add(e);

         TextLayout tl = new TextLayout(endRole, Constants.getFont(),
               Constants.getFRC(g2));
         Role r = new Role(endRole, 0, 0, (int) tl.getBounds().getWidth(),
               (int) tl.getBounds().getHeight());
         endShapes.add(r);
      }

      Vector startPoints = new Vector(this.getLinePoints());
      Vector endPoints = flipVector(startPoints);

      boolean a = lineUp(startShapes, startPoints, 0, 0);
      boolean b = lineUp(endShapes, endPoints, 0, 0);

      if ((a == false) || (b == false))
      {
         return;
      }

      if (lineType.equals("-"))
      {
         g2.setStroke(Constants.getStroke(0, 1));
      }
      else
      {
         g2.setStroke(Constants.getStroke(1, 1));
      }

      for (int i = 0; i < (getLinePoints().size() - 1); i++)
      {
         Point p1 = (Point) getLinePoints().elementAt(i);
         Point p2 = (Point) getLinePoints().elementAt(i + 1);
         g2.drawLine(p1.x, p1.y, p2.x, p2.y);
      }

      g2.setStroke(Constants.getStroke(0, 1));

      if (_selected)
      {
         for (int i = 0; i < getLinePoints().size(); i++)
         {
            Point p = (Point) getLinePoints().elementAt(i);
            g2.drawOval(p.x - 10, p.y - 10, 20, 20);
         }
      }

      Vector tmp = new Vector(startShapes);
      tmp.addAll(endShapes);

      for (int i = 0; i < tmp.size(); i++)
      {
         Rectangle r = (Rectangle) tmp.elementAt(i);

         if (r instanceof Qualifier)
         {
            Qualifier q = (Qualifier) r;
            g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(),
               (int) r.getHeight());
            Constants.write(g2, q.getString(),
               (int) r.getX() + Constants.getFontsize(),
               (int) r.getY() + Constants.getFontsize(), false);
         }
         else if (r instanceof Arrow)
         {
            Arrow arrow = (Arrow) r;
            g2.drawLine((int) arrow.getX(), (int) arrow.getY(),
               (int) arrow.getX() + (int) arrow.getArrowEndA().x,
               (int) arrow.getY() + (int) arrow.getArrowEndA().y);
            g2.drawLine((int) arrow.getX(), (int) arrow.getY(),
               (int) arrow.getX() + (int) arrow.getArrowEndB().x,
               (int) arrow.getY() + (int) arrow.getArrowEndB().y);

            //System.out.println(arrow.getString());
            if (arrow.getString().equals("<<"))
            {
               g2.drawLine((int) arrow.getX() + (int) arrow.getArrowEndA().x,
                  (int) arrow.getY() + (int) arrow.getArrowEndA().y,
                  (int) arrow.getX() + (int) arrow.getArrowEndB().x,
                  (int) arrow.getY() + (int) arrow.getArrowEndB().y);
            }
            else if (arrow.getString().equals("x"))
            {
               int[] ax = new int[4];
               int[] ay = new int[4];
               ax[0] = (int) arrow.getX();
               ay[0] = (int) arrow.getY();
               ax[1] = (int) arrow.getX() + (int) arrow.getArrowEndA().x;
               ay[1] = (int) arrow.getY() + (int) arrow.getArrowEndA().y;
               ax[3] = (int) arrow.getX() + (int) arrow.getArrowEndB().x;
               ay[3] = (int) arrow.getY() + (int) arrow.getArrowEndB().y;

               ax[2] = -(int) arrow.getX() + ax[1] + ax[3];
               ay[2] = -(int) arrow.getY() + ay[1] + ay[3];

               g2.draw(new Polygon(ax, ay, 4));
            }
            else if (arrow.getString().equals("X"))
            {
               int[] ax = new int[4];
               int[] ay = new int[4];
               ax[0] = (int) arrow.getX();
               ay[0] = (int) arrow.getY();
               ax[1] = (int) arrow.getX() + (int) arrow.getArrowEndA().x;
               ay[1] = (int) arrow.getY() + (int) arrow.getArrowEndA().y;
               ax[3] = (int) arrow.getX() + (int) arrow.getArrowEndB().x;
               ay[3] = (int) arrow.getY() + (int) arrow.getArrowEndB().y;

               ax[2] = -(int) arrow.getX() + ax[1] + ax[3];
               ay[2] = -(int) arrow.getY() + ay[1] + ay[3];

               g2.fill(new Polygon(ax, ay, 4));
            }
         }
         else if (r instanceof Multiplicity)
         {
            Multiplicity m = (Multiplicity) r;
            //g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
            Constants.write(g2, m.getString(), (int) r.getX(),
               (int) r.getY() + Constants.getFontsize(), false);
         }
         else if (r instanceof Role)
         {
            Role role = (Role) r;
            //g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
            Constants.write(g2, role.getString(), (int) r.getX(),
               (int) r.getY() + Constants.getFontsize(), false);
         }
      }

      if (this.getStrings() != null)
      {
         if (this.getStrings().size() > 0)
         {
            Point start = this.getCenterOfLine();
            int yPos = start.y;
            int xPos = start.x;

            for (int i = 0; i < getStrings().size(); i++)
            {
               String s = (String) this.getStrings().elementAt(i);
               Constants.write(g2, s, xPos, yPos, true);
               yPos += Constants.getFontsize();
               yPos += Constants.getDistTextToText();
            }
         }
      }

      Vector criticalPoints = new Vector();

      for (int i = 1; i < startShapes.size(); i++)
      {
         Rectangle r = (Rectangle) startShapes.elementAt(i);
         Point p1 = new Point((int) r.getX() - 2, (int) r.getY() - 2);
         Point p2 = new Point((int) r.getX() + (int) r.getWidth() + 2,
               (int) r.getY() + (int) r.getHeight() + 2);
         criticalPoints.add(p1);
         criticalPoints.add(p2);
      }

      for (int i = 1; i < endShapes.size(); i++)
      {
         Rectangle r = (Rectangle) endShapes.elementAt(i);
         Point p1 = new Point((int) r.getX() - 2, (int) r.getY() - 2);
         Point p2 = new Point((int) r.getX() + (int) r.getWidth() + 2,
               (int) r.getY() + (int) r.getHeight() + 2);
         criticalPoints.add(p1);
         criticalPoints.add(p2);
      }

      if (this.getStrings() != null)
      {
         if (this.getStrings().size() > 0)
         {
            Point start = this.getCenterOfLine();
            int yPos = start.y;
            int xPos = start.x;

            for (int i = 0; i < getStrings().size(); i++)
            {
               String s = (String) this.getStrings().elementAt(i);
               int width = Constants.getPixelWidth(g2, s);
               criticalPoints.add(new Point(xPos - (width / 2) - 20,
                     yPos - Constants.getFontsize() - 20));
               criticalPoints.add(new Point(xPos + (width / 2) + 20, yPos + 20));
               yPos += Constants.getFontsize();
               yPos += Constants.getDistTextToText();
            }
         }
      }

      {
         int minx = 99999;
         int maxx = -99999;
         int miny = 99999;
         int maxy = -99999;

         for (int i = 0; i < getLinePoints().size(); i++)
         {
            Point p = (Point) getLinePoints().elementAt(i);
            minx = Math.min(minx, p.x);
            miny = Math.min(miny, p.y);
            maxx = Math.max(maxx, p.x);
            maxy = Math.max(maxy, p.y);

            //TEST
            minx = Math.min(minx, p.x - 20);
            miny = Math.min(miny, p.y - 20);
            maxx = Math.max(maxx, p.x + 20);
            maxy = Math.max(maxy, p.y + 20);
         }

         for (int i = 0; i < criticalPoints.size(); i++)
         {
            Point p = (Point) criticalPoints.elementAt(i);
            minx = Math.min(minx, p.x);
            miny = Math.min(miny, p.y);
            maxx = Math.max(maxx, p.x);
            maxy = Math.max(maxy, p.y);
         }

         int diffminx = minx;
         int diffminy = miny;
         int diffmaxx = maxx - getWidth();
         int diffmaxy = maxy - getHeight();

         if (minx != 0)
         {
            Controller.getInstance()
                      .executeCommandWithoutUndo(new Resize(this, 8, diffminx, 0));
         }

         if (maxx != 0)
         {
            Controller.getInstance()
                      .executeCommandWithoutUndo(new Resize(this, 2, diffmaxx, 0));
         }

         if (miny != 0)
         {
            Controller.getInstance()
                      .executeCommandWithoutUndo(new Resize(this, 1, 0, diffminy));
         }

         if (maxy != 0)
         {
            Controller.getInstance()
                      .executeCommandWithoutUndo(new Resize(this, 4, 0, diffmaxy));
         }

         if ((minx != 0) | (miny != 0))
         {
            for (int i = 0; i < getLinePoints().size(); i++)
            {
               Point p = (Point) getLinePoints().elementAt(i);
               p.x += -minx;
               p.y += -miny;
            }
         }
      }
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   private Point getStartPoint()
   {
      Point ret = (Point) this.getLinePoints().elementAt(0);

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   private Point getEndPoint()
   {
      Point ret = (Point) this.getLinePoints()
                              .elementAt(this.getLinePoints().size() - 1);

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Point getAbsoluteCoorStart()
   {
      Point ret = new Point();
      ret.x = this.getX() + this.getStartPoint().x;
      ret.y = this.getY() + this.getStartPoint().y;

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Point getAbsoluteCoorEnd()
   {
      Point ret = new Point();
      ret.x = this.getX() + this.getEndPoint().x;
      ret.y = this.getY() + this.getEndPoint().y;

      return ret;
   }

   /**
    * DOCUMENT ME!
    *
    * @param e DOCUMENT ME!
    * @param action DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getConnectedLinePoint(Entity e, int action)
   {
      int ret1 = -1;
      int ret2 = -1;

      Point p = this.getStartPoint();
      Point abs = new Point((int) (this.getX() + p.getX()),
            (int) (this.getY() + p.getY()));
      int where = e.doesCoordinateAppearToBeConnectedToMe(abs);

      if ((where > 0) && ((where & action) > 0))
      {
         ret1 = 0;
      }

      p = this.getEndPoint();
      abs = new Point((int) (this.getX() + p.getX()),
            (int) (this.getY() + p.getY()));
      where = e.doesCoordinateAppearToBeConnectedToMe(abs);

      if ((where > 0) && ((where & action) > 0))
      {
         ret2 = this.getLinePoints().size() - 1;
      }

      ret1++;
      ret2++;

      return ret1 + (1000 * ret2);
   }
}

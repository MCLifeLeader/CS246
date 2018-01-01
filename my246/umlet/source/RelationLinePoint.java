// $Header: /usr/local/cvsroot/students/cs246/car03009/umlet/source/RelationLinePoint.java,v 1.3 2006/06/03 00:46:12 mbcarey Exp $
package com.umlet.element.base.detail;

import com.umlet.control.*;
import com.umlet.control.io.*;

import com.umlet.element.base.*;
import com.umlet.element.base.detail.*;
import com.umlet.element.custom.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
public class RelationLinePoint
{
   /**
    * DOCUMENT ME!
    */
   private Relation _relation;

   /**
    * DOCUMENT ME!
    */
   private int _linePointId;

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public Relation getRelation()
   {
      return _relation;
   }

   /**
    * DOCUMENT ME!
    *
    * @return DOCUMENT ME!
    */
   public int getLinePointId()
   {
      return _linePointId;
   }

   /**
    * Creates a new RelationLinePoint object.
    *
    * @param r DOCUMENT ME!
    * @param lp DOCUMENT ME!
    */
   public RelationLinePoint(Relation r, int lp)
   {
      _relation = r;
      _linePointId = lp;
   }
}

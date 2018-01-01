// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/si/InvokeMethodAction.java,v 1.1 2006/06/16 13:12:33 neffr Exp $
package sudoku.si;

import java.awt.*;
import java.awt.event.*;

import java.lang.reflect.*;

import javax.swing.*;

/**
 * Invoke-a-method action.
 * Takes a method name and finds a VoidMethodInvokable somewhere
 * up in its containment ancestry, and invokes it as its action.
 * For this to work, the method named must have a void return type
 * and take no arguments.
 *
 * @see VoidMethodInvokable
 */
public class InvokeMethodAction
   extends AbstractAction
{

   /**
    * The action name.
    */
   private String mName;

   /**
    * The method name.
    */
   private String mMethodName;

   /**
    * The method to be invoked.
    */
   private Method mMethod;

   /**
    * The invokee of the method (the object the method is invoked on).
    */
   private Object mInvokee;

   /**
    * Constructs a new InvokeMethodAction instance.
    * Initializes the name and methodName for the action.
    *
    * @param inName the name of the action.
    * @param inMethodName the method name of the action.
    */
   public InvokeMethodAction(String inName, String inMethodName)
   {
      super(inName);
      mName = inName;
      mMethodName = inMethodName;
      mInvokee = null;
   }

   /**
    * Constructs a new InvokeMethodAction instance.
    * Initializes the name for the action, and derives
    * the method name from the name by lowercasing it.
    *
    * @param inName the name of the action.
    */
   public InvokeMethodAction(String inName)
   {
      this(inName, inName.toLowerCase());
   }

   /**
    * Performs the action of invoking the method on the invokee.
    * Does a lazy look for the invokee in the action's containment
    * hierarchy and sets the method via "reflection" on the
    * method name.
    *
    * @param ae the action event.
    */
   public void actionPerformed(ActionEvent ae)
   {
      if (mMethod == null)
      {
         Component component = (Component) ae.getSource();
         Container container = component.getParent();
         while (container != null &&
                (! VoidMethodInvokable.class.isInstance(container)))
         {
            if (container instanceof JPopupMenu)
            {
               container = (Container) ((JPopupMenu) container).getInvoker();
            }
            else
            {
               container = container.getParent();
            }
         }
         if (container != null)
         {
            mInvokee = container;
            try
            {
               mMethod = mInvokee.getClass().getMethod(mMethodName);
            }
            catch (Exception e)
            {
            }
         }
      }
      if (mMethod != null && mInvokee != null)
      {
         try
         {
            mMethod.invoke(mInvokee);
         }
         catch (Exception e)
         {
         }
      }
   }
}

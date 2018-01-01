// $Header: /usr/local/cvsroot/courses/cs246/samplecode/Whatever.java,v 1.1 2006/06/05 17:22:33 neffr Exp $
/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
  */
public class Whatever
   implements Runnable
{
   /**
    * DOCUMENT ME!
    *
    * @param args DOCUMENT ME!
    */
   public static void main(String[] args)
   {
      new Whatever(args).run();
   }

   /**
    * Creates a new Whatever object.
    *
    * @param args DOCUMENT ME!
    */
   public Whatever(String[] args)
   {
   }

   /**
    * DOCUMENT ME!
    */
   public void run()
   {
      System.out.println("I'm running, whatever!");
   }
}

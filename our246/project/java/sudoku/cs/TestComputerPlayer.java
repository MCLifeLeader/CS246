// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/TestComputerPlayer.java,v 1.3 2006/06/24 17:56:12 manxman Exp $
package sudoku.cs;

import junit.framework.*;

/**
 * Test case for the ComputerPlayer
 *
 * @author $Randall Booth$
 * @version $Revision: 1.3 $
 */
public class TestComputerPlayer
   extends TestPlayer
{
   /**
    * Creates a new TestComputerPlayer object.
    */
   public TestComputerPlayer()
   {
      //intentionally empty
   }

   /**
    * Sets up the test case.
    */
   public void setUp()
   {
   }

   /**
    * Test to see it the constructor and toString work.
    */
   public void testName()
   {
      Player person = new ComputerPlayer("Bob");
      assertEquals(person.toString(), "Player[Bob]");
      assertFalse("Player[Bill]".equals(person));
   }
}

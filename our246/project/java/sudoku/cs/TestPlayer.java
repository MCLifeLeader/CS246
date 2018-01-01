// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/TestPlayer.java,v 1.7 2006/06/24 17:56:12 manxman Exp $
package sudoku.cs;

import junit.framework.*;

/**
 * Test the Player class
 *
 * @author $Randall Booth, James Comish$
 * @version $Revision: 1.7 $
 */
public class TestPlayer
   extends TestCase
{
   /**
    * Creates a new TestPlayer object.
    */
   public TestPlayer()
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
      Player person = new HumanPlayer("Bob");
      assertEquals(person.toString(), "Player[Bob]");
      assertFalse("Player[Bill]".equals(person));
   }

   /**
    * Test to see if the getPuzzle really does get a puzzle.
    */
   public void testGetPuzzle()
   {
      Player player = new HumanPlayer("Bob");
      assertFalse(player.getPuzzle().equals(null));
   }
}

// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/event/GameStatusChangedEvent.java,v 1.3 2006/06/24 17:56:40 manxman Exp $
package sudoku.cs.event;


/**
 * Encapsulates the event that the game's status has changed.
 *
 * @author $Rick Neff$
 * @version $Revision: 1.3 $
 */
public class GameStatusChangedEvent
{
   /**
    * The game's previous state.
    */
   private State mPrevState;

   /**
    * The game's current state.
    */
   private State mCurrState;

   /**
    * Constructs a GameStatusChangedEvent instance.
    *
    * @param oldState the old state of the game.
    * @param newState the new state of the game.
    */
   public GameStatusChangedEvent(State oldState, State newState)
   {
      mPrevState = oldState;
      mCurrState = newState;
   }

   /**
    * Gets the previous state of the game.
    *
    * @return the previous state of the game.
    */
   public State getPrevState()
   {
      return mPrevState;
   }

   /**
    * Gets the current state of the game.
    *
    * @return the current state of the game.
    */
   public State getCurrState()
   {
      return mCurrState;
   }

   /**
    * Returns a readable representation of the event.
    *
    * @return a readable representation of the event.
    */
   public String toString()
   {
      return ("{" + getPrevState() + " --> " + getCurrState() + "}");
   }
}

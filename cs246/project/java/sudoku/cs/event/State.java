// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/cs/event/State.java,v 1.1 2006/06/13 16:13:03 neffr Exp $
package sudoku.cs.event;

/**
 * Enumeration of Client (game) states.
 * <p>
 * <a href="../../../../docs/clientStateDiagram.jpg">State Diagram</a>
 */
public enum State
{
   /**
    * The state of the Client when it is looking for a server.
    */
   LOOKING_FOR_SERVER, 

   /**
    * The state of the Client when it is waiting for players.
    */
   WAITING_FOR_PLAYERS, 

   /**
    * The state of the Client when the game is in progress.
    */
   GAME_IN_PROGRESS, 

   /**
    * The state of the Client when the game is over.
    */
   GAME_OVER;
}

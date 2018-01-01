// $Header: /usr/local/cvsroot/courses/cs246/project/java/sudoku/ui/PlayerDataModel.java,v 1.4 2006/06/25 07:11:47 emerrill Exp $
/*
 * PlayerDataTable.java
 *
 * Created on June 23, 2006, 10:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package sudoku.ui;

import sudoku.cs.Player;

import sudoku.si.Config;

import java.util.*;

import javax.swing.table.AbstractTableModel;

/**
 * Used to display the player's statistics to the screen.
 *
 * @author mbcarey
 */
public class PlayerDataModel
   extends AbstractTableModel
{
   /**
    * A collection of Players.
    */
   private Vector<Player> mData = new Vector<Player>();

   /**
    * The number of columns to be displayed.
    */
   private final int NUM_COLUMNS = 3;

   /**
    * The first column row name.
    */
   private final String NAME = Config.get("StatPanelName", "Name");

   /**
    * The second column row for guesses.
    */
   private final String GUESSES = Config.get("StatPanelGuesses", "Guesses");

   /**
    * The third column row for squares left.
    */
   private final String SQUARES = Config.get("StatPanelSquares", "Squares Left");

   /**
    * gets the column name information.
    *
    * @param col Which column to show it from.
    *
    * @return the string name of that column.
    */
   public String getColumnName(int col)
   {
      String rtnval = null;

      switch (col)
      {
         case 0:
            rtnval = NAME;

            break;

         case 1:
            rtnval = GUESSES;

            break;

         case 2:
            rtnval = SQUARES;

            break;
      }

      return rtnval;
   }

   /**
    * gets the number of columns.
    *
    * @return returns the count of columns.
    */
   public int getColumnCount()
   {
      return NUM_COLUMNS;
   }

   /**
    * gets the number of rows.
    *
    * @return total number of rows.
    */
   public int getRowCount()
   {
      return mData.size();
   }

   /**
    * Get the exact data at a particular location
    *
    * @param row the row location of the data.
    * @param col the column locatio of the data.
    *
    * @return the string value of the data.
    */
   public Object getValueAt(int row, int col)
   {
      String rtnval = null;

      try
      {
         Player p = (Player) mData.elementAt(row);

         switch (col)
         {
            case 0:
               rtnval = p.getName();

               break;

            case 1:
               rtnval = p.getPlayerStats().getNumberOfGuesses() + "";

               break;

            case 2:
               rtnval = p.getPlayerStats().getSquaresLeft() + "";

               break;
         }
      }
      catch (Exception e)
      {
      }

      return rtnval;
   }

   /**
    * Updates the list of players in the data model.
    *
    * @param Players the Player computers and their players.
    */
   public void updatePlayers(Collection<Player> Players)
   {
      mData.clear();
      mData.addAll(Players);
      fireTableDataChanged();
   }

   /**
    * Updates the window information.
    */
   public void updateDisplay()
   {
      fireTableDataChanged();
   }

   /**
    * clears all the player data.
    */
   public void clear()
   {
      mData.clear();
   }
}

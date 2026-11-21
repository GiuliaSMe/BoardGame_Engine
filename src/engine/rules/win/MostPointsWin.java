package engine.rules.win;

import engine.board.*;
import engine.core.Player;

public class MostPointsWin implements WinCondition{

    @Override 
    public Player getWinner(Board board, Player[] players){
        Player best = null;
        boolean tie = false;

        for (Player p : players){
            if (best == null || p.getPoints()> best.getPoints()){
                best = p;
                tie = false;
            } else if(p.getPoints() == best.getPoints()){
                tie = true;
            }
        }
        return tie ? null : best;
    }
}
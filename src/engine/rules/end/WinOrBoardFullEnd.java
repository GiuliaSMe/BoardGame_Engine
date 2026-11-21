package engine.rules.end;

import engine.core.Board;
import engine.core.Player;
import engine.rules.win.WinCondition;

public class WinOrBoardFullEnd implements EndCondition{

    private WinCondition win;

    public WinOrBoardFullEnd(WinCondition win){
        this.win = win;
    }

    @Override
    public boolean isGameOver(Board board, Player[] players){
        if (win.getWinner(board, players)!= null || board.isBoardFull()){
            return true;
        }
        return false;
    }
}

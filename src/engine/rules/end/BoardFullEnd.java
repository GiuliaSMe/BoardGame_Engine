package engine.rules.end;


import engine.core.Board;
import engine.core.Player;

public class BoardFullEnd implements EndCondition{

    @Override 
    public boolean isGameOver(Board board, Player[] players){
        return board.isBoardFull();
    }
}
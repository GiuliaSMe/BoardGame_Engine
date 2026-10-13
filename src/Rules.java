public class Rules{
    //checa se o board ta completo
    public boolean isGameOver(Board board){
        if (board.isBoardFull()){return true;}
        else {return false;}
    }

    //WIP (chckpnt2): lógica funciona somente para 2 players
    public int getWinner(Player p1, Player p2){
        if (p1.getPoints() > p2.getPoints()){return p1.getId();}
        else if (p1.getPoints() < p2.getPoints()) {return p2.getId();}
        else {return -1;}
    }

    //a verificaçao de verdade fica no Board (que conhece as paredes), rules só repassa a info
    public boolean isValidMove(Board board, Movement move){
        return board.isValidMove(move.getLine(), move.getColumn(), move.getPosition());
    }
}
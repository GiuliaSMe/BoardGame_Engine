package engine.board;

import engine.core.*;

public class GridBoard extends Board{

    //construtor
    public GridBoard(int lines, int columns){
        super(lines,columns);
    }

    @Override public String getBoard() {
        StringBuilder board = new StringBuilder();
        String separator = "   " + "+---".repeat(columns) + "+\n";

        board.append("   "); // espaço da coluna dos números das linhas
        for (int c = 0; c < columns; c++) {
            board.append(String.format("  %-2d", c));
        }
        board.append("\n");

        for (int l = 0; l < lines; l++) {
            board.append(separator);
            board.append(String.format("%2d ", l));
            for (int c = 0; c < columns; c++) {
                String symbol = matrixOcupied[l][c];
                board.append("|");
                board.append(symbol != null ? " " + symbol + " " : "   ");
            }
            board.append("|\n");
        }

        board.append(separator); // linha final
        return board.toString();
    }

    @Override public void setOcupation(Movement movement, Player player){
        //TODO
        int line  = movement.getLine();
        int column  = movement.getColumn();

        if (isValidMove(movement)){matrixOcupied[line][column] = player.getSymbol();}
    }

    @Override public boolean isValidMove(Movement movement){
        int line = movement.getLine();
        int column = movement.getColumn();

        if(!cellExists(line, column)){return false;}
        else {return true;}
    }
}
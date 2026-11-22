package engine.board;

import engine.core.*;

public class GridBoard extends Board{

    //construtor
    public GridBoard(int rows, int columns){
        super(rows,columns);
    }

    @Override public String getBoard() {
        StringBuilder board = new StringBuilder();
        String separator = "   " + "+---".repeat(columns) + "+\n";

        board.append("   "); // espaço da coluna dos números das linhas
        for (int c = 0; c < columns; c++) {
            board.append(String.format("  %-2d", c));
        }
        board.append("\n");

        for (int r = 0; r < rows; r++) {
            board.append(separator);
            board.append(String.format("%2d ", r));
            for (int c = 0; c < columns; c++) {
                String symbol = matrixOcupied[r][c];
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
        int row  = movement.getRow();
        int column  = movement.getColumn();

        if (isValidMove(movement)){matrixOcupied[row][column] = player.getSymbol();}
    }

    @Override public boolean isValidMove(Movement movement){
        int row = movement.getRow();
        int column = movement.getColumn();

        if(!cellExists(row, column)){return false;}
        else {return true;}
    }
}
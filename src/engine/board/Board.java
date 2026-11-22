package engine.board;

import engine.core.*;

public abstract class Board{    
    protected  int columns; 
    protected int rows;
    protected String[][] matrixOcupied; 

    //construtor
    public Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.matrixOcupied = new String[rows][columns]; 
    }

    //seta a ocupação né, meio obvio 
    public abstract void setOcupation(Movement movement, Player player);
    
    //verifica se a jogada é válida (posição existe, célula existe)
    public abstract boolean isValidMove(Movement movement);

    /**
     * verifica se a celula existe
     * @return boolean
     */
    public boolean cellExists(int row, int column){
        if ((row > (this.rows-1)) || (column > (this.columns-1))){return false;}
        else if ((row < 0) || (column < 0)){return false;}
        else {return true;}
    }

    /**
     * verifica se a celula está ocupada
     * @return boolean
     */
    public boolean isCellOcupied(int row, int column){
        //ou na matriz ocupada tá vazio, ou na matriz paredes tem 4 paredes
        if (this.matrixOcupied[row][column] != null){return true;}
        else {return false;}
    }

    /**
     * checa se o ta buleiro está completo
     * @return boolean
    */
    public boolean isBoardFull(){
        boolean full = true;
        for (int l = 0; l < rows; l++){
            for (int c = 0; c < columns; c++){
                if (matrixOcupied[l][c] == null){full = false;}
            }
        }
        return full;
    }

    //metodos get
    public int getRows(){return this.rows;}
    public int getColumns(){return this.columns;}
    public abstract String getBoard();
}
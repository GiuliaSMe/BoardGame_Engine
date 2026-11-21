package engine.board;

import engine.core.*;

public abstract class Board{    
    protected  int columns; 
    protected int lines;
    protected String[][] matrixOcupied; 

    //construtor
    public Board(int lines, int columns){
        this.lines = lines;
        this.columns = columns;
        this.matrixOcupied = new String[lines][columns]; 
    }

    //seta a ocupação né, meio obvio 
    public abstract void setOcupation(Movement movement, Player player);
    
    //verifica se a jogada é válida (posição existe, célula existe)
    public abstract boolean isValidMove(Movement movement);

    /**
     * verifica se a celula existe
     * @return boolean
     */
    public boolean cellExists(int line, int column){
        if ((line > (this.lines-1)) || (column > (this.columns-1))){return false;}
        else if ((line < 0) || (column < 0)){return false;}
        else {return true;}
    }

    /**
     * verifica se a celula está ocupada
     * @return boolean
     */
    public boolean isCellOcupied(int line, int column){
        //ou na matriz ocupada tá vazio, ou na matriz paredes tem 4 paredes
        if (this.matrixOcupied[line][column] != null){return true;}
        else {return false;}
    }

    /**
     * checa se o ta buleiro está completo
     * @return boolean
    */
    public boolean isBoardFull(){
        boolean full = true;
        for (int l = 0; l < lines; l++){
            for (int c = 0; c < columns; c++){
                if (matrixOcupied[l][c] == null){full = false;}
            }
        }
        return full;
    }

    //metodos get
    public int getLines(){return this.lines;}
    public int getColumns(){return this.columns;}
    public abstract String getBoard();
}
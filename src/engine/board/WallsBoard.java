package engine.board;

import engine.core.*;

public class WallsBoard extends Board{
    private int[][] matrixWalls; 

    //valores binários simbolizam cada posiçao de parede (0b inicial indica pro java que é binario) (escolha arbitraria)
    private static int up = 0b1000;
    private static int down = 0b0100;
    private static int left = 0b0010;
    private static int right = 0b0001;

    public WallsBoard(int rows, int columns){
        super(rows,columns);
        this.matrixWalls = new int[rows][columns]; 
    }

    //lógica binária para setar quais paredes estão em que célula
    public void setWall(Movement movement, Player player){

        char position = movement.getPosition();
        int row = movement.getRow();
        int column = movement.getColumn();

        int wall; //parede na célula escolhida
        int neighborWall; //mesma parede vista pela célula vizinha
        int neighborrow = row;
        int neighborColumn = column;

        //define a parede na celula escolhida e na celula vizinha de acordo com a posiçao da parede
        switch (position) {
            case 'u': wall = up;    neighborWall = down;  neighborrow = row - 1;     break;
            case 'd': wall = down;  neighborWall = up;    neighborrow = row + 1;     break;
            case 'l': wall = left;  neighborWall = right; neighborColumn = column - 1; break;
            case 'r': wall = right; neighborWall = left;  neighborColumn = column + 1; break;
            default: return;
        }

        //adiciona parede e verifica se fechou a célula
        this.matrixWalls[row][column] = this.matrixWalls[row][column] | wall;
        setOcupation(movement, player); //nao só seta a ocupacao de uma celula fechada, mas tambem verifica se as condiçoes para fechamento foram atendidas

        //ajusta célula adjacente e verifica se ela também fechou
        if (cellExists(neighborrow, neighborColumn)){
            this.matrixWalls[neighborrow][neighborColumn] = this.matrixWalls[neighborrow][neighborColumn] | neighborWall;
            Movement neighbordMovement = new Movement(neighborrow, neighborColumn, 'u'); //posiçao escolhida arbitrariamente enquanto não temos um movement generico
            setOcupation(neighbordMovement, player);
        }
    }

    //verifica se a parede está ocupada
    public boolean isWallOcupied(int row, int column, char position){
        switch (position) {
            case 'u':
                if ((this.matrixWalls[row][column] & up) != 0){return true;}
                else {return false;}
            case 'd':
                if ((this.matrixWalls[row][column] & down) != 0){return true;}
                else {return false;}
            case 'l':
                if ((this.matrixWalls[row][column] & left) != 0){return true;}
                else {return false;}
            case 'r':
                if ((this.matrixWalls[row][column] & right) != 0){return true;}
                else {return false;}
            default:
                return false;
        }
    }

    @Override public String getBoard(){
        StringBuilder board = new StringBuilder();

        board.append("   "); // espaço da coluna dos números das linhas
        for (int c = 0; c < columns; c++) {
            board.append(String.format("  %-2d", c));
        }
        board.append("\n");

        for (int l = 0; l < rows; l++) {
            // linha dos pontos + paredes horizontais
            board.append("   ");
            for (int c = 0; c < columns; c++) {
                board.append("+");
                board.append(isWallOcupied(l, c, 'u') ? "---" : "   ");
            }
            board.append("+\n");

            // linha das células: parede esquerda + conteúdo
            board.append(String.format("%2d ", l));
            for (int c = 0; c < columns; c++) {
                board.append(isWallOcupied(l, c, 'l') ? "|" : " ");
                String symbol = matrixOcupied[l][c];
                board.append(symbol != null ? " " + symbol + " " : "   ");
            }
            board.append(isWallOcupied(l, columns - 1, 'r') ? "|" : " ");
            board.append("\n");
        }

        // última linha de pontos
        board.append("   ");
        for (int c = 0; c < columns; c++) {
            board.append("+");
            board.append(isWallOcupied(rows - 1, c, 'd') ? "---" : "   ");
        }
        board.append("+\n");

        return board.toString();
    }

    @Override public boolean isValidMove(Movement movement){

        char position = movement.getPosition();
        int row = movement.getRow();
        int column = movement.getColumn();

        if (position != 'u' && position != 'd' && position != 'l' && position != 'r'){return false;}
        else if(!cellExists(row, column)){return false;}
        else if(isWallOcupied(row, column, position)){return false;}
        else {return true;}
    }

    @Override public void setOcupation(Movement movement, Player player){

        int row = movement.getRow();
        int column = movement.getColumn();

        if (this.matrixWalls[row][column] == 0b1111 && this.matrixOcupied[row][column] == null){
            this.matrixOcupied[row][column] = player.getSymbol();
            player.addPoints(1);
        }
    }
}
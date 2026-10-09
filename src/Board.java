public class Board{
    private int columns;
    private int lines;
    private int[][] matrixWalls = new int[lines][columns];    
    private String[][] matrixOcupied = new String[lines][columns]; 

    //valores binários simbolizam cada posiçao de parede (0b inicial indica pro java que é binario) (escolha arbitraria)
    private static int up = 0b1000;
    private static int down = 0b0100;
    private static int left = 0b0010;
    private static int right = 0b0001;

    public Board(int lines, int columns){
        this.lines = lines;
        this.columns = columns;
        this.matrixWalls = new int[lines][columns]; 
        this.matrixOcupied = new String[lines][columns]; 
    }
    
    //lógica binária para setar quais que paredes estão em que célula
    public void setWall(int line, int column, char position, Player player){
        if (cellExists(line, column) && !isCellOcupied(line, column) && !isWallOcupied(line, column, position)){
            switch (position) {
                case 'u':
                    //adiciona parede
                    this.matrixWalls[line][column] = this.matrixWalls[line][column] | up;

                    //ajusta celula adjascente
                    if (cellExists(line - 1, column)){this.matrixWalls[line-1][column] = this.matrixWalls[line-1][column] | down;}

                    //se fechar as 4 paredes, setar ocupação
                    if (isCellOcupied(line, column)){setOcupation(line, column, player);}
                    break;
                case 'd':
                    //adiciona parede
                    this.matrixWalls[line][column] = this.matrixWalls[line][column] | down;

                    //ajusta celula adjascente
                    if (cellExists(line + 1, column)){this.matrixWalls[line+1][column] = this.matrixWalls[line+1][column] | up;}

                    //se fechar as 4 paredes, setar ocupação
                    if (isCellOcupied(line, column)){setOcupation(line, column, player);}
                    break;
                case 'l':
                    //adiciona parede
                    this.matrixWalls[line][column] = this.matrixWalls[line][column] | left;

                    //ajusta celula adjascente 
                    if (cellExists(line, column-1)){this.matrixWalls[line][column-1] = this.matrixWalls[line][column-1] | right;}

                    //se fechar as 4 paredes, setar ocupação
                    if (isCellOcupied(line, column)){setOcupation(line, column, player);}
                    break;
                case 'r':
                    //adiciona parede
                    this.matrixWalls[line][column] = this.matrixWalls[line][column] | right;

                    //ajusta celula adjascente 
                    if (cellExists(line, column+1)){this.matrixWalls[line][column+1] = this.matrixWalls[line][column+1] | left;}

                    //se fechar as 4 paredes, setar ocupação
                    if (isCellOcupied(line, column)){setOcupation(line, column, player);}
                    break;
                default:
                    System.out.println("Invalid move");
                    break;
            }
        }
    }
                
    //atualiza a matriz de ocupação e adiciona pontos ao jogador
    public void setOcupation(int line, int column, Player player){
        if (isCellOcupied(line, column)){
            matrixOcupied[line][column] = player.getSymbol();
            player.addPoints(1);
        }
    }

    //verifica se a celula existe
    public boolean cellExists(int line, int column){
        if ((line > (this.lines-1)) || (column > (this.columns-1))){return false;}
        else if ((line < 0) || (column < 0)){return false;}
        else {return true;}
    }

    //verifica se a celula está ocupada
    public boolean isCellOcupied(int line, int column){
        //ou na matriz ocupada tá vazio, ou na matriz paredes tem 4 paredes
        if (this.matrixOcupied[line][column] != null || this.matrixWalls[line][column] == 15){return true;}
        else {return false;}
    }

    //verifica se a parede está ocupada
    public boolean isWallOcupied(int line, int column, char position){
        switch (position) {
            case 'u':
                if ((this.matrixWalls[line][column] & up) != 0){return true;}
                else {return false;}
            case 'd':
                if ((this.matrixWalls[line][column] & down) != 0){return true;}
                else {return false;}
            case 'l':
                if ((this.matrixWalls[line][column] & left) != 0){return true;}
                else {return false;}
            case 'r':
                if ((this.matrixWalls[line][column] & right) != 0){return true;}
                else {return false;}
            default:
                return false;
        }
    }

    public int getLines(){return this.lines;}
    public int getColumns(){return this.columns;}

    public String getBoard() {
    
        StringBuilder board = new StringBuilder();

        board.append("   "); // espaço da coluna dos números das linhas
        for (int c = 0; c < columns; c++) {
            board.append(String.format("  %-2d", c));
        }
        board.append("\n");

        for (int l = 0; l < lines; l++) {
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
            board.append(isWallOcupied(lines - 1, c, 'd') ? "---" : "   ");
        }
        board.append("+\n");

        return board.toString();
    }
}
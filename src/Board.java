public class Board{
    //atributos
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
        //construtor
        this.lines = lines;
        this.columns = columns;
        this.matrixWalls = new int[lines][columns]; 
        this.matrixOcupied = new String[lines][columns]; 
    }
    
    //lógica binária para setar quais que paredes estão em que célula
    public void setWall(int line, int column, char position, Player player){
        int wall;               //bit da parede na célula escolhida
        int neighborWall;       //bit da mesma parede vista pela célula vizinha
        int neighborLine = line;
        int neighborColumn = column;

        switch (position) {
            case 'u': wall = up;    neighborWall = down;  neighborLine = line - 1;     break;
            case 'd': wall = down;  neighborWall = up;    neighborLine = line + 1;     break;
            case 'l': wall = left;  neighborWall = right; neighborColumn = column - 1; break;
            case 'r': wall = right; neighborWall = left;  neighborColumn = column + 1; break;
            default: return;
        }

        //adiciona parede e verifica se fechou a célula
        this.matrixWalls[line][column] = this.matrixWalls[line][column] | wall;
        setOcupation(line, column, player);

        //ajusta célula adjacente e verifica se ela também fechou
        if (cellExists(neighborLine, neighborColumn)){
            this.matrixWalls[neighborLine][neighborColumn] = this.matrixWalls[neighborLine][neighborColumn] | neighborWall;
            setOcupation(neighborLine, neighborColumn, player);
        }
    }

    //se a célula ficou com as 4 paredes e ainda não tem dono, marca e dá o ponto
    public void setOcupation(int line, int column, Player player){
        if (this.matrixWalls[line][column] == 15 && this.matrixOcupied[line][column] == null){
            this.matrixOcupied[line][column] = player.getSymbol();
            player.addPoints(1);
        }
    }
    
    //verifica se a jogada é válida (posição existe, célula existe e parede está livre)
    public boolean isValidMove(int line, int column, char position){
        if (position != 'u' && position != 'd' && position != 'l' && position != 'r'){return false;}
        if (!cellExists(line, column)){return false;}
        return !isWallOcupied(line, column, position);
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
        if (this.matrixOcupied[line][column] != null || this.matrixWalls[line][column] == 0b1111){return true;}
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
    //metodos get
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
    //checa se o ta buleiro está completo
    public boolean isBoardFull(){
        boolean full = true;
        for (int l = 0; l < lines; l++){
            for (int c = 0; c < columns; c++){
                if (matrixOcupied[l][c] == null){full = false;}
            }
        }
        return full;
    }
}
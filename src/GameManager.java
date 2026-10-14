public class GameManager {
    //atributos
    private Board board;
    private Player[] players;
    private Rules rules;
    private UiManager ui;
    private int currentPlayer; //índice dos players

    //construtor
    public GameManager(Board board, Player[] players, Rules rules, UiManager ui) {
        this.board = board;
        this.players = players;
        this.rules = rules;
        this.ui = ui;
        this.currentPlayer = 0;
    }
    
    //game loop
    public void play() {
        //enquanto a regra de fim de jogo nao é atendida, continua mostrando o tabuleiro e solicitando novo movimento do player vigente
        while (!rules.isGameOver(board)) {
            ui.showBoard(board, players[0], players[1]);

            Player player = players[currentPlayer]; //simplificaçao para o player vigente
            Movement move = askValidMove(player); //chama a ui e adjascentes de solicitaçao de movimento

            //setWall é void, ent pra descobrir se fechou a caixa compara os pontos antes e depois
            //necessário pq quando o player faz um ponto ele ganha uma jogada adicional
            int pointsBefore = player.getPoints();
            board.setWall(move.getLine(), move.getColumn(), move.getPosition(), player);

            //só passa a vez se NÃO fechou caixinha
            if (player.getPoints() == pointsBefore) {nextTurn();}
        }
        //mostra tabuleiro final ao fim da partida e chama o encerramento do jogo
        ui.showBoard(board, players[0], players[1]);
        endGame();
    }

    //solicita um movimento e só aceita se o movimento for válido, repete até ser válido
    private Movement askValidMove(Player player) {
        Movement move = ui.playerMovement(player); //solicita a entrada de movimento

        while (!rules.isValidMove(board, move)) {
            ui.showError("\nJogada inválida, tente de novo.");
            move = ui.playerMovement(player);
        }
        return move;
    }

    private void nextTurn() {
        currentPlayer = (currentPlayer + 1) % players.length; //resto da divisão determina o id do player vigente (funciona para qualquer quantidade de players)
    }

    private void endGame() {
        int winner = rules.getWinner(players[0], players[1]);  
        // -1 = empate
        if (winner == -1){ui.showDraw();} 
        else {ui.showWinner(players[winner]);}
    }
}

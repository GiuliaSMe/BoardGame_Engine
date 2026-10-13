/*TODO:
[x] Implementar regra de turno extra ao fechar uma caixa
[x] Implementar alternância de jogador quando nenhuma caixa é fechada
[x] implementar o game loop
[x] inputHandler <-- ficou na UiManager
 */

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
    
    public void play() {
        while (!rules.isGameOver(board)) {
            ui.showBoard(board, players[0], players[1]);

            Player player = players[currentPlayer];
            Movement move = askValidMove(player);

            //setWall é void: descobre se fechou caixa comparando os pontos antes e depois
            int pointsBefore = player.getPoints();
            board.setWall(move.getLine(), move.getColumn(), move.getPosition(), player);

            if (player.getPoints() == pointsBefore) {   // regra: só passa a vez se NÃO fechou caixinha
                nextTurn();
            }
        }
        ui.showBoard(board, players[0], players[1]);
        endGame();
    }

    private Movement askValidMove(Player player) {
        Movement move = ui.playerMovement(player);
        while (!rules.isValidMove(board, move)) {
            ui.showError("Jogada inválida, tente de novo.");
            move = ui.playerMovement(player);
        }
        return move;
    }

    private void nextTurn() {
        currentPlayer = (currentPlayer + 1) % players.length;
    }

    private void endGame() {
        int winner = rules.getWinner(players[0], players[1]);   // -1 = empate
        if (winner == -1) {
            ui.showDraw();
        } else {
            ui.showWinner(players[winner]);
        }
    }
}

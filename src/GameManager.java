/*TODO:
[ ] Implementar regra de turno extra ao fechar uma caixa
[ ] Implementar alternância de jogador quando nenhuma caixa é fechada
[ ] implementar o game loop
[ ] inputHandler <-- aqui ou na ui fodasse
 */

public class GameManager {
    
    private Board board;
    private Player[] players;
    private Rules rules;
    private UiManager ui;
    private int currentPlayer;   // índice em players: 0, 1, 0, 1...

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

            Move move = askValidMove(players[currentPlayer]);
            boolean closedBox = board.markEdge(move.getLinha(), move.getCol(), move.isHorizontal(), currentPlayer);

            if (!closedBox) {        // regra: fechou caixinha, joga de novo
                nextTurn();
            }
        }
        ui.showBoard(board);
        endGame();
    }

    private Move askValidMove(Player player) {
        Move move = ui.readMove(player);
        while (!rules.isValidMove(board, move)) {
            ui.showError("Jogada inválida, tente de novo.");
            move = ui.readMove(player);
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

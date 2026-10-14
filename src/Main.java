import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] names = { "Ana", "Bia" };
        String[] symbols = { "A", "B" };
        Player[] players = new Player[names.length];

        //garante que o id do player é o indice do array de players, garantindo que o player saiba qual o seu indice (usado por rules)
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(names[i], symbols[i], i);
        }

        Board board = new Board(1, 3);
        Rules rules = new Rules();
        UiManager ui = new UiManager(scanner);

        GameManager game = new GameManager(board, players, rules, ui);
        game.play();

        scanner.close();
    }
}

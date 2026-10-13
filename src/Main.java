import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] nomes = { "Ana", "Bia" };
        String[] simbolos = { "A", "B" };

        Player[] players = new Player[nomes.length];

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(nomes[i], simbolos[i], i);
        }

        Board board = new Board(5, 5);
        Rules rules = new Rules();
        UiManager ui = new UiManager(scanner);

        GameManager game = new GameManager(board, players, rules, ui);
        game.play();

        scanner.close();
    }
}

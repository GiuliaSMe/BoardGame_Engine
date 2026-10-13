import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        String[] nomes = { "Ana", "Bia" };
        String[] simbolos = { "A", "B" };

        Player[] players = new Player[nomes.length];

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(nomes[i], simbolos[i], i);
        }
    /*
    //Board board = new Board(5);
    Player[] players = { new Player("Ana", "A",0), new Player("Bia", "B",1) };
    //GameRules rules = new GameRules();
    //UIManager ui = new UIManager();

    GameManager game = new GameManager(board, players, rules, ui);
    game.play();
     */
    }
}
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        UiManager ui = new UiManager();
        Board board = new Board(5, 5);
        Player player = new Player("p1", "1");
        Scanner scanner = new Scanner(System.in);

        int option = -1;

        while (option != 4){

            option =ui.menu(board, player, scanner);

        }

        scanner.close();
    }
}
import java.util.Scanner;

public class UiManager{
    public int menu(Board board, Player player, Scanner scanner){
        System.out.println(board.getBoard());

        System.out.print("Linha: ");
        int line = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Coluna: ");
        int column = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Posição: ");
        char position = scanner.next().charAt(0);

        board.setWall(line, column, position, player);

        System.out.print("Digite 4 para sair: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        return option;

    }
}
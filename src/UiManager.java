import java.util.Scanner;

public class UiManager{
    //FIXME: ainda está em modo de testes
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

        //board.setWall(line, column, position, player);

        System.out.print("Digite 4 para sair: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        return option;

    }

    public void showBoard(Board board, Player p1, Player p2){
        showScore(p1, p2);
        System.out.println(board.getBoard());
    }

    public void showScore(Player p1, Player p2){
        String text = String.format(""" 
                %s | %s
                %d | %d
                """,p1.getSymbol(), p2.getSymbol(), p1.getPoints(), p2.getPoints());

        System.out.println(text);
    }

    public Movement playerMovement(Player player, Scanner scanner, Movement move){
        System.out.print("Linha: ");
        int line = scanner.nextInt();
        scanner.nextLine(); //limpa buffer

        System.out.print("Coluna: ");
        int column = scanner.nextInt();
        scanner.nextLine(); //limpa buffer

        System.out.print("Posição: ");
        char position = scanner.next().charAt(0); //por algum motivo nao existe nextchar()

        return new Movement(line, column, position);
    }

    public void showError(String ErrorText){
        System.out.println(ErrorText);
    }

    //TODO: ui empate
    public void showDraw(){

    }

    //TODO: ui vencedor
    public void showWinner(Player player){

    }
}
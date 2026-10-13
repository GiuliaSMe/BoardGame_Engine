import java.util.Scanner;

public class UiManager{
    private Scanner scanner;

    public UiManager(Scanner scanner){
        this.scanner = scanner;
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

    public Movement playerMovement(Player player){
        System.out.printf("Vez de %s (%s)%n", player.getName(), player.getSymbol());

        int line = readInt("Linha: ");
        int column = readInt("Coluna: ");

        System.out.print("Posição (u=cima, d=baixo, l=esquerda, r=direita): ");
        String input = scanner.nextLine().trim().toLowerCase();
        //entrada vazia vira ' ', que o isValidMove recusa
        char position = input.isEmpty() ? ' ' : input.charAt(0); //por algum motivo nao existe nextchar()

        return new Movement(line, column, position);
    }

    //lê a linha inteira e converte; nextInt() quebrava o programa se o usuário digitasse letra
    private int readInt(String label){
        while (true){
            System.out.print(label);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                showError("Digite um número.");
            }
        }
    }

    public void showError(String ErrorText){
        System.out.println(ErrorText);
    }

    public void showDraw(){
        System.out.println("Fim de jogo! Empate.");
    }

    public void showWinner(Player player){
        System.out.printf("Fim de jogo! %s (%s) venceu com %d pontos.%n",
                player.getName(), player.getSymbol(), player.getPoints());
    }
}

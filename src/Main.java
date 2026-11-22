import config.GameConfig;
import config.GameLoader;
import engine.exceptions.InvalidConfigurationException;

//só serve de testes, ainda nao gera nenhum jogo pelo json, mas tá lendo ele corretamente
//oq falta é o gamemanager

public class Main {
    public static void main(String[] args) {
        try {
            GameConfig config = GameLoader.load("games/liga_pontos.json");

            System.out.println("Jogo: " + config.getGame());
            System.out.println("Linhas: " + config.getBoard().getRows());
            System.out.println("Colunas: " + config.getBoard().getColumns());
            System.out.println("Jogadores: " + config.getPlayers().getCount());
            System.out.println("Símbolos: " + String.join(", ", config.getPlayers().getSymbols()));
            System.out.println("Placement: " + config.getRules().getPlacement());
            System.out.println("Turn: " + config.getRules().getTurn());
            System.out.println("End: " + config.getRules().getEndCondition());
            System.out.println("Win type: " + config.getRules().getWinCondition().getType());

        } catch (InvalidConfigurationException e) {
            System.out.println("Erro na configuração: " + e.getMessage());
        }
    }
}
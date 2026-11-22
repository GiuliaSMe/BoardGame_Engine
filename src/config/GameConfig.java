package config;

/**
 * guarda os dados lidos do JSON, como tamanho do tabuleiro, quantidade de players, etc.
 */
public class GameConfig {
    String game;
    BoardConfig board;
    PlayersConfig players;
    RulesConfig rules;

    public static class BoardConfig{
        int rows; 
        int columns;

        //getters
        public int getRows(){return this.rows;}
        public int getColumns(){return this.columns;}
    }

    public static class PlayersConfig{
        int count;
        String[] symbols;

        //getters
        public int getCount(){return this.count;}
        public String[] getSymbols(){return this.symbols;}
    }

    public static class RulesConfig{
        String placement;
        String turn;
        String endCondition;
        WinConfig winCondition;

        //getters
        public String getPlacement(){return this.placement;}
        public String getTurn(){return this.turn;}
        public String getEndCondition(){return this.endCondition;}
        public WinConfig getWinCondition(){return this.winCondition;}
    }

    public static class WinConfig{
        String type;
        int quantity;

        //getters
        public String getType(){return this.type;}
        public int getQuantity(){return this.quantity;}
    }

    //getters
    public String getGame(){return this.game;}
    public BoardConfig getBoard(){return this.board;}
    public PlayersConfig getPlayers(){return this.players;}
    public RulesConfig getRules(){return this.rules;}
}

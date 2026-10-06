public class Player{
    private String name;
    private String symbol;
    private int points;

    //construtor
    public Player(String name, String symbol, int points){
        this.name = name;
        this.symbol = symbol;
        this.points = points;
    }

    public void addPoints(int pointAmount){this.points += pointAmount;}

    //getters
    public int getPoints(){return this.points;}
    public String getName(){return this.name;}
    public String getSymbol(){return this.symbol;}
}
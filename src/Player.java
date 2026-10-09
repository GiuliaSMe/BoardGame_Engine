public class Player{
    private String name;
    private String symbol;
    private int points = 0;

    //construtor
    public Player(String name, String symbol){
        this.name = name;
        this.symbol = symbol;
    }

    public void addPoints(int pointAmount){this.points += pointAmount;}

    //getters
    public int getPoints(){return this.points;}
    public String getName(){return this.name;}
    public String getSymbol(){return this.symbol;}
}
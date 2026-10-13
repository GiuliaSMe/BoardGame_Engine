public class Player{
    //atributos
    private String name;
    private String symbol; //simbolo q aparece na celula ocupada 
    private int id; //posição no array players, 2 players 2 numeros.
    private int points = 0;

    //construtor
    public Player(String name, String symbol, int id){
        this.name = name;
        this.symbol = symbol;
        this.id = id;
    }

    public void addPoints(int pointAmount){this.points += pointAmount;}

    //getters
    public int getPoints(){return this.points;}
    public int getId(){return this.id;}
    public String getName(){return this.name;}
    public String getSymbol(){return this.symbol;}
}
public class Movement{
    private int line;
    private int column;
    private char position;

    public Movement(int line, int column, char position){
        this.line = line;
        this.column = column;
        this.position = position;
    }

    public int getColumn(){return column;}
    public int getLine(){return line;}
    public char getPosition(){return position;}
}
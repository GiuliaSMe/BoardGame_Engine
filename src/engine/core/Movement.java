package engine.core;
public class Movement{
    private int row;
    private int column;
    private char position;

    public Movement(int row, int column, char position){
        this.row = row;
        this.column = column;
        this.position = position;
    }

    public int getColumn(){return column;}
    public int getRow(){return row;}
    public char getPosition(){return position;}
}
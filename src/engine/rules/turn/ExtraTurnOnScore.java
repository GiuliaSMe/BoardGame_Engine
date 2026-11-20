package engine.rules.turn;

public class ExtraTurnOnScore implements TurnRule{

    @Override
    //Volta pro mesmo jogador caso pontue. ex: ligar pontos.
    public int nextPlayer(int current, int playerCount, boolean scored){
        if (scored){
            return current;
        }
        return (current + 1) % playerCount;
    }
}
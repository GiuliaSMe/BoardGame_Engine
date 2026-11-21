package engine.rules.turn;

public class AlternateTurn implements TurnRule {

    @Override
    public int nextPlayer(int current, int playerCount, boolean scored){
        //alterna o jogador da vez, levando em consideração jogos de 1 ou + players (%playerCount)
        return (current + 1) % playerCount;
    }
}

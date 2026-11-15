package engine.rules.turn;

//contrato de turno
//Liga dos Pontos: repete a vez se o jogador fechou caixinha
//Jogo da Velha: sempre alterna
public interface TurnRule {

    //current = índice de quem acabou de jogar, playerCount = quantidade de jogadores,
    //scored = se a última jogada pontuou (vem do MoveRule.apply)
    //devolve o índice de quem joga agora
    int nextPlayer(int current, int playerCount, boolean scored);
}

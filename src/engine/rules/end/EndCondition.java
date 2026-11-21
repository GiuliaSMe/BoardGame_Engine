package engine.rules.end;

import engine.core.Board;
import engine.core.Player;

//contrato de fim de jogo
//Liga dos Pontos: tabuleiro cheio
//Jogo da Velha: alguém venceu OU tabuleiro cheio (pode usar uma WinCondition dentro, por composição)
//empate = partida acabou e WinCondition.getWinner devolveu null
public interface EndCondition {

    boolean isGameOver(Board board, Player[] players);
}

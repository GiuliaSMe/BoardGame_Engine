package engine.rules.win;

import engine.board.*;
import engine.core.Player;

//contrato de vitória
//Liga Pontos: quem tem mais pontos
//Jogo da Velha: quem alinhou as peças
public interface WinCondition {

    //devolve o vencedor, ou null se ninguém venceu (empate, ou partida ainda sem vencedor)
    Player getWinner(Board board, Player[] players);
}

package engine.rules.move;

import engine.board.*;
import engine.core.Movement;
import engine.core.Player;

//contrato de jogada
//Liga dos Pontos: coloca uma parede num lado da célula
//Jogo da Velha: coloca a peça do jogador numa célula vazia
public interface MoveRule {

    //true se a jogada pode ser feita no estado atual do tabuleiro
    boolean isValid(Board board, Movement move);

    //aplica a jogada no tabuleiro
    //devolve true se o jogador pontuou (usado pela TurnRule para decidir quem joga depois)
    boolean apply(Board board, Movement move, Player player);
}

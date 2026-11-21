package config;

import engine.board.Board;
import engine.core.*;
import engine.rules.*;

/**
 * guarda os dados lidos do JSON, como tamanho do tabuleiro, quantidade de players, etc.
 */
public class GameConfig {
    String game;
    Player player; 
    Board board; 
    Rules rules;
}

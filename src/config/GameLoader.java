package config;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.IOException;
import engine.exceptions.InvalidConfigurationException;
//import java.util.List;

/**
 * lê o arquivo de configuração do jogo (JSON) e cria os objetos necessários para iniciar o jogo, como Board, Players, etc.
 */
public class GameLoader {
    public static GameConfig load(String path) throws InvalidConfigurationException{
        try{
            FileReader reader = new FileReader(path);
            GameConfig config = new Gson().fromJson(reader, GameConfig.class);
            return config;
        }
        catch (IOException e){
            throw new InvalidConfigurationException("");
        }
        catch (JsonSyntaxException e){
            throw new InvalidConfigurationException("");
        }
    }
}

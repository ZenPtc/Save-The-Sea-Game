package com.TK.savetheseas;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlayerData {
    private int highScore;

    private static final Path path = Paths.get("playerdata.txt");

    public void savePlayerData(int highScore){
        try{
            PlayerData data = new PlayerData();
            data.setHighScore(highScore);
            Files.writeString(path,""+highScore, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Unable to save data.");
            e.printStackTrace();
        }
    }

    public void loadPlayerData(){
        try{
            int i = Integer.parseInt(Files.readString(path));
            setHighScore(i);
        } catch (Exception e) {
            setHighScore(0);
        }
    }

    //GETTER
    public int getHighScore(){        return highScore;    }
    //SETTER
    public void setHighScore(int highScore){        this.highScore = highScore;    }
}

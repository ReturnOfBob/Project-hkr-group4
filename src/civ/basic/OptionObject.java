package civ.basic;

import java.io.Serializable;

public class OptionObject implements Serializable {
      
//--------------------------------VARIABLES-----------------------------------\\

    private String difficulty;
    private int roundLimit;
    private Boolean musicSet;
    private Integer musicChoice;

//----------------------------NON-FXML METHODS--------------------------------\\
    
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setRoundLimit(int roundLimit) {
        this.roundLimit = roundLimit;
    }

    public void setMusicSet(Boolean musicSet) {
        this.musicSet = musicSet;
    }

    public void setMusicChoice(Integer musicChoice) {
        this.musicChoice = musicChoice;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getRoundLimit() {
        return roundLimit;
    }

    public Boolean isMusicSet() {
        return musicSet;
    }

    public Integer getMusicChoice() {
        return musicChoice;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;

import java.io.Serializable;

/**
 *
 * @author Henrik
 */
public class OptionObject implements Serializable {

    private String difficulty;
    private int roundLimit;
    private Boolean musicSet;
    private Integer musicChoice;

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

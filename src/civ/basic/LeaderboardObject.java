
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;

/**
 *
 * @author Henrik
 */
public class LeaderboardObject {

    private String name;
    private int score;
    private String difficulty;

    public LeaderboardObject(String name, int score, String difficulty) {
        this.name = name;
        this.score = score;
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    

}

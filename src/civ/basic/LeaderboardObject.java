package civ.basic;

public class LeaderboardObject {
    
//--------------------------------VARIABLES-----------------------------------\\

    private String name;
    private int score;
    private String difficulty;
    
//----------------------------NON-FXML METHODS--------------------------------\\

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

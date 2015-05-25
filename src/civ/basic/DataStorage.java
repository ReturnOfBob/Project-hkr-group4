package civ.basic;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DataStorage {
//------------------------------VARIABLES-------------------------------------\\   

    private static DataStorage dataStorage;
    private String newSceneIs;
    private String newActiveUser;
    private String difficulty = "normal";
    private int roundLimit = 50;
    private int score;
    private Boolean musicSet = false;
    private Integer musicChoice = 1;
    private Boolean loadOnceCheck;
    private boolean highestScoreCheck;
    private boolean leaderboardCheatCodeBlock;

//------------------------------CONSTRUCTOR-----------------------------------\\    
    private DataStorage() {

    }
//---------------------GETS INFORMATION FROM DATASTORAGE----------------------\\    

    public static DataStorage getInstance() {
        if (dataStorage == null) {
            dataStorage = new DataStorage();
        }

        return dataStorage;
    }
//--------------------------------METHODS-------------------------------------\\    
    //This method is used everywhere in the program switch between different scenes

    public void sceneSwitch(ActionEvent event, String newSceneIs) {

        this.newSceneIs = newSceneIs;

        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(newSceneIs));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println("ERROR SCENESWITCH!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//-------------------------GETTERS AND SETTERS--------------------------------\\    

    public String getNewSceneIs() {
        return newSceneIs;
    }

    public void setNewActiveUser(String newActiveUser) {
        this.newActiveUser = newActiveUser;
    }

    public String getNewActiveUser() {
        return newActiveUser;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;

    }

    public Integer getRoundLimit() {
        return roundLimit;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public void setRoundLimit(int roundLimit) {
        this.roundLimit = roundLimit;
    }

    public Boolean isMusicSet() {
        return musicSet;
    }

    public void setMusicSet(Boolean musicSet) {
        this.musicSet = musicSet;
    }

    public Integer getMusicChoice() {
        return musicChoice;
    }

    public void setMusicChoice(Integer musicChoice) {
        this.musicChoice = musicChoice;
    }

    public Boolean isLoadOnceCheck() {
        return loadOnceCheck;
    }

    public void setLoadOnceCheck(Boolean loadOnceCheck) {
        this.loadOnceCheck = loadOnceCheck;
    }

    public boolean isHighestScoreCheck() {
        return highestScoreCheck;
    }

    public void setHighestScoreCheck(boolean highestScoreCheck) {
        this.highestScoreCheck = highestScoreCheck;
    }

    public boolean isLeaderboardCheatCodeBlock() {
        return leaderboardCheatCodeBlock;
    }

    public void setLeaderboardCheatCodeBlock(boolean leaderboardCheatCodeBlock) {
        this.leaderboardCheatCodeBlock = leaderboardCheatCodeBlock;
    }

}

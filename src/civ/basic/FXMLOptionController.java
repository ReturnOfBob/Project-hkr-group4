/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;

/**
 * FXML Controller class
 *
 * @author Henrik
 */
public class FXMLOptionController implements Initializable, Serializable {
//--------------------------------VARIABLES-----------------------------------\\ 

    @FXML
    private RadioButton noobDifficulty, normalDifficulty, asianDifficulty, lowTurnLimit, mediumTurnLimit, highTurnLimit, musicOn, musicOff, musicPack1, musicPack2, musicPack3;
    private String buttonText;
    //private final SoundPlayer player = new SoundPlayer();
    @FXML
    String difficulty;
    int roundLimit;
    Boolean musicSet;
    int musicChoice;
    OptionObject objectSave = new OptionObject();
//---------------------------ON SCENE LOAD-UP---------------------------------\\

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /*private FXMLOptionController(String difficulty, Integer roundLimit, Boolean musicSet, Integer musicChoice) {
     this.difficulty = difficulty;
     this.roundLimit = roundLimit;
     this.musicSet = musicSet;
     this.musicChoice = musicChoice;

     }*/
//------------------------------FXML METHODS----------------------------------\\    
    @FXML //This method handles all of the clicks in on buttons in this scene
    private void optionButtonClick(ActionEvent event) {
        buttonText = ((Button) event.getSource()).getText();

        if (buttonText.equals("Back")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLMainMenu.fxml");
        } else if (buttonText.equals("Save settings")) {
            submitOption();

        } else {
            System.out.println("ERROR!");
        }
    }   

    private void submitOption() {
        if (noobDifficulty.isSelected()) {
            DataStorage.getInstance().setDifficulty("noob");
        } else if (normalDifficulty.isSelected()) {
            DataStorage.getInstance().setDifficulty("normal");
        } else {
            DataStorage.getInstance().setDifficulty("asian");
        }
        if (lowTurnLimit.isSelected()) {
            DataStorage.getInstance().setRoundLimit(20);
        } else if (mediumTurnLimit.isSelected()) {
            DataStorage.getInstance().setRoundLimit(50);
        } else {
            DataStorage.getInstance().setRoundLimit(100);
        }
        if (musicOn.isSelected()) {
            //("Länk till music class med  setter för music on")
        } else {
            //Länk till music class med setter för music off
        }

        if (musicPack1.isSelected()){
           SoundPlayer.getInstance().setMusicStop();
            SoundPlayer.getInstance().playMusic(1);
        }
        else if (musicPack2.isSelected()){
           SoundPlayer.getInstance().setMusicStop();
            SoundPlayer.getInstance().playMusic(2);
        } else {
            SoundPlayer.getInstance().setMusicStop();
            SoundPlayer.getInstance().playMusic(3);
        }
        objectSave.setDifficulty(DataStorage.getInstance().getDifficulty());
        objectSave.setMusicChoice(DataStorage.getInstance().getMusicChoice());
        objectSave.setMusicSet(DataStorage.getInstance().isMusicSet());
        objectSave.setRoundLimit(DataStorage.getInstance().getRoundLimit());
        writeOptionSettingToFile();

        System.out.println("submitOption");
    }

    private void writeOptionSettingToFile() {
        File file = new File(DataStorage.getInstance().getNewActiveUser() + ".txt");
        try (ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(file))) {
            oOut.writeObject(objectSave);
            oOut.close();
            System.out.println("Filsparning lyckades");

        } catch (Exception ex) {
            System.out.println("Informationen kunde inte sparas till fil!");

        }
    }

}

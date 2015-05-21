/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Sweetpink
 */
public class FXMLMainMenuController implements Initializable {
//--------------------------------VARIABLES-----------------------------------\\    

    private String buttonText;
//---------------------------ON SCENE LOAD-UP---------------------------------\\    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(DataStorage.getInstance().isMusicOnCheck().equals(true)){
        SoundPlayer.getInstance().setMusicStop();
        }
        readOptionSettingFromFile();
        //if(DataStorage.getInstance().isMusicSet().equals(false)){
        SoundPlayer.getInstance().playMusic(DataStorage.getInstance().getMusicChoice());
        //}
    }
//------------------------------FXML METHODS----------------------------------\\    

    @FXML //This method handles all of the clicks in the menu clicks in this scene
    private void menuClick(ActionEvent event) {
        buttonText = ((Button) event.getSource()).getText();

        if (buttonText.equals("New Game")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLGame.fxml");
        } else if (buttonText.equals("Leaderboard")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLLeaderboard.fxml");
        } else if (buttonText.equals("Options")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLOption.fxml");
            System.out.println("Options");
        } else if (buttonText.equals("Log Out")) {
            //Temporary sceneswitch back to login
            DataStorage.getInstance().sceneSwitch(event, "FXMLLogInMenu.fxml");
            DataStorage.getInstance().setNewActiveUser("");
            System.out.println(DataStorage.getInstance().getNewActiveUser());
        } else if (buttonText.equals("Exit")) {
            try {
                System.exit(0);
            } catch (Exception ex) {
                System.out.println("ERROR EXIT!");
            }
        } else {
            System.out.println("ERROR!");
        }
    }

    private void readOptionSettingFromFile() {
        OptionObject objectload = new OptionObject();
        try (ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(DataStorage.getInstance().getNewActiveUser() + ".txt"))) {
            objectload = (OptionObject) oIn.readObject();
            System.out.println("Filöppning lyckades");
            DataStorage.getInstance().setDifficulty(objectload.getDifficulty());
            DataStorage.getInstance().setMusicChoice(objectload.getMusicChoice());
            DataStorage.getInstance().setMusicSet(objectload.isMusicSet());
            DataStorage.getInstance().setRoundLimit(objectload.getRoundLimit());

        } catch (Exception ex) {
            System.out.println("Ingen fil skapad");

        }
    }

}

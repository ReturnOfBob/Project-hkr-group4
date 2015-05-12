/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

/**
 * FXML Controller class
 *
 * @author Henrik
 */
public class FXMLOptionController implements Initializable {
//--------------------------------VARIABLES-----------------------------------\\ 

    @FXML
    private RadioButton noobDifficulty, normalDifficulty, asianDifficulty, lowTurnLimit, mediumTurnLimit, highTurnLimit, musicOn,musicOff ;
    private String buttonText;
    private final SoundPlayer player = new SoundPlayer();

//---------------------------ON SCENE LOAD-UP---------------------------------\\
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }
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
        if (musicOn.isSelected()){
            //("Länk till music class med  setter för music on")
        }
        else{
            //Länk till music class med setter för music off
        }

        System.out.println("submitOption");

    }

}

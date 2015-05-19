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
import javafx.scene.control.RadioButton;

/**
 * FXML Controller class
 *
 * @author Henrik
 */
public class FXMLOptionController implements Initializable, Serializable {
//--------------------------------VARIABLES-----------------------------------\\ 
    @FXML
    private RadioButton noobDifficulty, normalDifficulty, asianDifficulty, lowTurnLimit, mediumTurnLimit, highTurnLimit, musicOn, musicOff;
    private String buttonText;
    private final SoundPlayer player = new SoundPlayer();
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
            objectSave.setDifficulty("noob");
        } else if (normalDifficulty.isSelected()) {
            DataStorage.getInstance().setDifficulty("normal");
            objectSave.setDifficulty("normal");
        } else {
            DataStorage.getInstance().setDifficulty("asian");
            objectSave.setDifficulty("asian");
        }
        if (lowTurnLimit.isSelected()) {
            DataStorage.getInstance().setRoundLimit(20);
            objectSave.setRoundLimit(20);
        } else if (mediumTurnLimit.isSelected()) {
            DataStorage.getInstance().setRoundLimit(50);
            objectSave.setRoundLimit(50);
        } else {
            DataStorage.getInstance().setRoundLimit(100);
            objectSave.setRoundLimit(100);
        }
        if (musicOn.isSelected()) {
            //("Länk till music class med  setter för music on")
        } else {
            //Länk till music class med setter för music off
        }
        writeOptionSettingToFile();

        System.out.println("submitOption");
    }

    private void writeOptionSettingToFile() {
        //   FXMLOptionController optionObject = new FXMLOptionController(DataStorage.getInstance().getDifficulty(),DataStorage.getInstance().getRoundLimit(),true,3);
        readOptionSettingFromFile();
        File file = new File("option.opt");
        try (ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(file))) {
            oOut.writeObject(objectSave);
            oOut.close();
            System.out.println("Filsparning lyckades");

        } catch (Exception ex) {
            System.out.println("Informationen kunde inte sparas till fil!");
           

        }
    }

    public void readOptionSettingFromFile() {

        try (ObjectInputStream oIn = new ObjectInputStream(new FileInputStream("option.opt"))) {
            objectSave = (OptionObject) oIn.readObject();
            System.out.println("Filöppning lyckades");
            DataStorage.getInstance().setDifficulty(objectSave.getDifficulty());
            DataStorage.getInstance().setRoundLimit(objectSave.getRoundLimit());

        } catch (Exception ex) {
            System.out.println("Ingen fil skapad");
            

        }
    }

}

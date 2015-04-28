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

/**
 * FXML Controller class
 *
 * @author Henrik
 */
public class FXMLOptionController implements Initializable {

    private String buttonText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO

    }}

    //------------------------------FXML METHODS----------------------------------\\    
    //@FXML //This method handles all buttonclicks in this scene
   /** private void menuClick(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        buttonText = ((Button) event.getSource()).getText();

        if (buttonText.equals("Back")) {
            DataStorage.getInstance().setNewSceneIs("FXMLMainMenu.fxml");
            DataStorage.getInstance().sceneSwitch(event);

        }
        else if (buttonText.equals("Music1")) {
            DataStorage.getInstance().setNewActiveMusic(1);

        }
             else if (buttonText.equals("Music2")) {
           DataStorage.getInstance().setNewActiveMusic(2);

        }
       
        
        
        
        else {
            System.out.println("ERROR");
        }
    }*/

    



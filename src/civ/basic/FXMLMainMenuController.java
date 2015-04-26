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
 * @author Sweetpink
 */
public class FXMLMainMenuController implements Initializable {
//--------------------------------VARIABLES-----------------------------------\\    
    private String buttonText;
//---------------------------ON SCENE LOAD-UP---------------------------------\\    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
//------------------------------FXML METHODS----------------------------------\\    
    @FXML //This method handles all buttonclicks in this scene
    private void menuClick(ActionEvent event){
        buttonText = ((Button)event.getSource()).getText();
        
        if(buttonText.equals("New Game")){
           // DataStorage.getInstance().setNewSceneIs("FXMLGame.fxml");
            DataStorage.getInstance().sceneSwitch(event, "FXMLGame.fxml");
            
        }
        else if(buttonText.equals("Load Game")){
            //Code will come here eventually
            System.out.println("Load Game");
        }
        else if(buttonText.equals("Options")){
            //Codde will come here eventually
            System.out.println("Options");
        }
        else if(buttonText.equals("Log Out")){
            //Temporary sceneswitch back to login
            //DataStorage.getInstance().setNewSceneIs("FXMLLogInMenu.fxml");
            DataStorage.getInstance().sceneSwitch(event, "FXMLLogInMenu.fxml");
            
        }
        else if(buttonText.equals("Exit")){
            try {
                System.exit(0);
            }catch (Exception ex) {
                System.out.println("ERROR EXIT!");
            }
        }
        else{
            System.out.println("ERROR!");
        }
    }
    
}

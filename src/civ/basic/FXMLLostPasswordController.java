/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Henrik
 */
public class FXMLLostPasswordController implements Initializable {
//------------------------------VARIABLES-------------------------------------\\    

    private String storedName;
    private String buttonText;
//---------------------------------GUI----------------------------------------\\     
    @FXML
    private Label showInfo;
    @FXML
    private TextField inputRequiredText;
//-----------------------------MYSQL CONNECTION-------------------------------\\    
    private final DataBaseConnector connector = new DataBaseConnector();
//----------------------------ON SCENE LOADUP---------------------------------\\

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
//------------------------------FXML METHODS----------------------------------\\    

    @FXML //This method handles all of the clicks in the menu in this scene
    private void menuClick(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        buttonText = ((Button) event.getSource()).getText();

        if (buttonText.equals("Get clue")) {
            handleButtonRetriveClue();

        } else if (buttonText.equals("Retrieve password")) {
            handleButtonRetrievePassword();
        } else if (buttonText.equals("Back to Log in")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLLogInMenu.fxml");
        } else {
            System.out.println("ERROR");
        }

    }
//----------------------------NON-FXML METHODS--------------------------------\\    

    private void handleButtonRetriveClue() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String questionCheck = "SELECT  Security_Question FROM accounts WHERE Username = '" + inputRequiredText.getText() + "'";//Ändra:   inputRequiredText.getText() istället för Henrik. Hämta även svar i samma kod så att det är gjort

            if (connector.getResult(questionCheck).next()) {
                storedName = inputRequiredText.getText();
                System.out.println(connector.getResultSet().getString(1));
                showInfo.setText(connector.getResultSet().getString(1));
            } else {
                System.out.println("Hittar inte användarnamnet");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLLostPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleButtonRetrievePassword() {
        try {

            String questionAnswer = "SELECT Answer FROM accounts WHERE Username = '" + storedName + "'"; //Ändra till ett kommando istället för två se AND
            String passwordGetter = "SELECT Password FROM accounts WHERE Username = '" + storedName + "'";

            if (connector.getResult(questionAnswer).next()) {
                if (connector.getResultSet().getString(1).equals(inputRequiredText.getText())) {

                    if (connector.getResult(passwordGetter).next()) {
                        showInfo.setText(connector.getResultSet().getString(1));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Henrik
 */
public class FXMLLogInMenuController implements Initializable {
//------------------------------VARIABLES-------------------------------------\\
    private String buttonText;
    private boolean userCorrectCheck = false;
//---------------------------------GUI----------------------------------------\\   
    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Label loginErrorLabel;
//-----------------------------MYSQL CONNECTION-------------------------------\\    
    private final PreparedStatement stt = null;
    private final String URL = "jdbc:mysql://127.0.0.1:3306/civ-basic?user=root&password=root";
//----------------------------ON SCENE LOADUP---------------------------------\\
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
//------------------------------FXML METHODS----------------------------------\\    
    @FXML //This method handles all of the clicks in the menu in this scene
    private void menuClick(ActionEvent event) {
        buttonText = ((Button)event.getSource()).getText();

        if (buttonText.equals("Log in")) {
            handleButtonLogin();

            if (userCorrectCheck == true) {
                userCorrectCheck = false;
                DataStorage.getInstance().sceneSwitch(event, "FXMLMainMenu.fxml");
            }

        } else if (buttonText.equals("Create new account")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLcreateAccount.fxml");
        } else if (buttonText.equals("Lost my password")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLLostPassword.fxml");
        } else {
            System.out.println("ERROR");
        }

    }
//----------------------------NON-FXML METHODS--------------------------------\\
    //This method checks if the users login info is correct
    private void handleButtonLogin() {
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection c = DriverManager.getConnection(URL);
            Statement st = c.createStatement();
            String dataCheck = "SELECT * FROM accounts WHERE Username = '" + userName.getText() + "' AND Password = '" + userPassword.getText() + "'";

            ResultSet rs = st.executeQuery(dataCheck);
            
            if (rs.next()) {
                System.out.println("Dina uppgifter är rätt. Du är inloggad");
                DataStorage.getInstance().setNewActiveUser(userName.getText());
                System.out.println("Active user is:  " + DataStorage.getInstance().getNewActiveUser());
                userCorrectCheck = true;
            
            } else {
                System.out.println("Fel användarnamn eller lösenord!");
                loginErrorLabel.setText("Fel användarnamn eller lösenord!");
            }

        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            loginErrorLabel.setText("ERROR: " + e);
        }
    }

}

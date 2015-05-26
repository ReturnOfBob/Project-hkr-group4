package civ.basic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    
    private final DataBaseConnector connector = new DataBaseConnector();
    
//----------------------------ON SCENE LOADUP---------------------------------\\

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DataStorage.getInstance().setLoadOnceCheck(false);
    }
    
//------------------------------FXML METHODS----------------------------------\\    

    @FXML //This method handles all of the clicks in the menu in this scene
    private void menuClick(ActionEvent event) {
        buttonText = ((Button) event.getSource()).getText();

        if (buttonText.equals("Log in")) {
            handleButtonLogin();

            if (userCorrectCheck == true) {
                userCorrectCheck = false;
                DataStorage.getInstance().sceneSwitch(event, "FXMLMainMenu.fxml");
            }

        } else if (buttonText.equals("Create new account")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLCreateAccount.fxml");
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
            if (connector.getResult(connector.getGenericAndCommand("*", "accounts", "Username", userName.getText(), "Password", userPassword.getText())).next()) {
                System.out.println("Dina uppgifter är rätt. Du är inloggad");
                DataStorage.getInstance().setNewActiveUser(userName.getText());
                System.out.println("Active user is:  " + DataStorage.getInstance().getNewActiveUser());
                userCorrectCheck = true;

            } else {
                System.out.println("Fel användarnamn eller lösenord!");
                loginErrorLabel.setText("Fel användarnamn eller lösenord!");
            }
            connector.close();

        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            loginErrorLabel.setText("ERROR: " + e);
        }
    }

}

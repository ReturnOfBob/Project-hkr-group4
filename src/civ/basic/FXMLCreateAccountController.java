package civ.basic;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLCreateAccountController implements Initializable {
    
//--------------------------------VARIABLES-----------------------------------\\     

    private String buttonText;
    
//-----------------------------------GUI--------------------------------------\\     
    @FXML
    
    private TextField name, question, questionAnswer;

    @FXML
    private PasswordField password, passwordAgain;

    @FXML
    private Label createAccountDoneLabel, createAccountErrorLabel;
    
//-----------------------------MYSQL CONNECTION-------------------------------\\ 
    
    private final DataBaseConnector connector = new DataBaseConnector();
    
//---------------------------ON SCENE LOAD-UP---------------------------------\\

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
//------------------------------FXML METHODS----------------------------------\\    

    @FXML //This method handles all of the clicks in the menu in this scene
    private void menuClick(ActionEvent event) throws SQLException {
        buttonText = ((Button) event.getSource()).getText();

        if (buttonText.equals("Create Account")) {
            handleButtonCreateAccount();
        } else if (buttonText.equals("Back to login")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLLogInMenu.fxml");
        } else {
            System.out.println("ERROR!");
        }
    }
//----------------------------NON-FXML METHODS--------------------------------\\     

    private void handleButtonCreateAccount() throws SQLException {
        boolean createAccountBlocker = false;
        try {

            if (connector.getResult(connector.getGenericCommand("*", "accounts", "Username", name.getText())).next()) {
                System.out.println("Användare finns redan");
                createAccountErrorLabel.setText("Användare finns redan");
                createAccountBlocker = true;
            }

            if (createAccountBlocker == false && !"".equals(name.getText()) && !"".equals(password.getText()) && !"".equals(question.getText()) && !"".equals(questionAnswer.getText()) && password.getText().equals(passwordAgain.getText())) {

                PreparedStatement prepSt = connector.getConnection().prepareStatement(connector.getGenericInsertCommand("accounts", "Username", "Password",
                        "Security_Question", "Answer", "Privilege"));

                prepSt.setString(1, name.getText());
                prepSt.setString(2, password.getText());
                prepSt.setString(3, question.getText());
                prepSt.setString(4, questionAnswer.getText());
                prepSt.setString(5, "Basic user");

                prepSt.executeUpdate();
                connector.close();
                createAccountErrorLabel.setText("");
                System.out.println("Acccount created");
                createAccountDoneLabel.setText("Acccount created");
            } else if (!password.getText().equals(passwordAgain.getText())) {
                System.out.println("Lösenorden matchar inte!");
                createAccountDoneLabel.setText("");
                createAccountErrorLabel.setText("");
                createAccountErrorLabel.setText("Lösenorden matchar inte!");
            } else {
                if (createAccountBlocker == false){
                createAccountDoneLabel.setText("");
                createAccountErrorLabel.setText("");
                System.out.println("Du måste fylla i alla fält!");
                createAccountErrorLabel.setText("Du måste fylla i alla fält!");
                }
            }

        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        }
    }

}

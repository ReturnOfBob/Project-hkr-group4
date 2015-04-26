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
 * FXML Controller class
 *
 * @author Henrik
 *
 */
public class FXMLcreateAccountController implements Initializable {
//--------------------------------VARIABLES-----------------------------------\\     
    private String buttonText;
    private boolean userExistCheck = true;
//-----------------------------------GUI--------------------------------------\\     
    @FXML
    private TextField name, question, questionAnswer;
    
    @FXML 
    private PasswordField password, passwordAgain;
    
    @FXML 
    private Label createAccountDoneLabel, createAccountErrorLabel;
//-----------------------------MYSQL CONNECTION-------------------------------\\ 
    String URL = "jdbc:mysql://127.0.0.1:3306/civ-basic?user=root&password=root";
    PreparedStatement stt = null;
//---------------------------ON SCENE LOAD-UP---------------------------------\\
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
//------------------------------FXML METHODS----------------------------------\\    
    @FXML
    private void menuClick(ActionEvent event){
        buttonText = ((Button)event.getSource()).getText();
        
        if(buttonText.equals("Create Account")){
            handleButtonCreateAccount();
        }
        else if(buttonText.equals("Back to login")){
            //DataStorage.getInstance().setNewSceneIs("FXMLLogInMenu.fxml");
            DataStorage.getInstance().sceneSwitch(event, "FXMLLogInMenu.fxml");
        }
        else{
            System.out.println("ERROR!");
        }
    }
    
//----------------------------NON-FXML METHODS--------------------------------\\     
    private void handleButtonCreateAccount() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection c = DriverManager.getConnection(URL);
            Statement st = c.createStatement();

            String inputData = "INSERT INTO accounts (Username,Password,Security_Question,Answer,Privilege) VALUES (?,?,?,?,?)";
            String dataCheck = "SELECT * FROM accounts WHERE Username = '" + name.getText() + "'";

            userExistCheck = true;
            ResultSet rs = st.executeQuery(dataCheck);
            
            if (rs.next()) {
                System.out.println("Användare finns redan");
                userExistCheck = false;
            }

            if (!"".equals(name.getText()) && !"".equals(password.getText()) && !"".equals(question.getText()) && !"".equals(questionAnswer.getText()) && password.getText().equals(passwordAgain.getText())) {
                PreparedStatement prepSt = c.prepareStatement(inputData);
                
                prepSt.setString(1, name.getText());
                prepSt.setString(2, password.getText());
                prepSt.setString(3, question.getText());
                prepSt.setString(4, questionAnswer.getText());
                prepSt.setString(5, "Basic user");
                
                prepSt.executeUpdate();
                createAccountErrorLabel.setText("");
                System.out.println("Acccount created");
                createAccountDoneLabel.setText("Acccount created");
            }else if(!password.getText().equals(passwordAgain.getText())){
                System.out.println("Lösenorden matchar inte!");
                createAccountDoneLabel.setText("");
                createAccountErrorLabel.setText("");
                createAccountErrorLabel.setText("Lösenorden matchar inte!");
            }
            else{
                createAccountDoneLabel.setText("");
                createAccountErrorLabel.setText("");
                System.out.println("Du måste fylla i alla fält!");
                createAccountErrorLabel.setText("Du måste fylla i alla fält!");
            }
        }       // while(rs.next()){
                //   String pasw = rs.getString("userPassword");
                //  String name = rs.getString("userName");
                //System.out.println(name);
                //System.out.println(pasw);
                // c.close();
                // System.out.println("Pressed ok");
                //}
                //if(userName.getText(){
                // }
                //} 
        catch (Exception e){
            System.err.println("ERROR: " + e);
        }
    }

}

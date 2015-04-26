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
    private String storedName;
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
    PreparedStatement stt = null;
    String URL = "jdbc:mysql://127.0.0.1:3306/civ-basic?user=root&password=root";
    
//----------------------------ON SCENE LOADUP---------------------------------\\
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
//------------------------------FXML METHODS----------------------------------\\    
    @FXML //This method handles all buttonclicks in this scene
    private void menuClick(ActionEvent event){
        buttonText = ((Button)event.getSource()).getText();
        
        if(buttonText.equals("log in")){
            handleButtonLogin();
            
            if (userCorrectCheck == true) {
                userCorrectCheck = false;
                //DataStorage.getInstance().setNewSceneIs("FXMLMainMenu.fxml");
                DataStorage.getInstance().sceneSwitch(event, "FXMLMainMenu.fxml");
            }

        }
        else if(buttonText.equals("Create new account")){
            //DataStorage.getInstance().setNewSceneIs("FXMLcreateAccount.fxml");
            DataStorage.getInstance().sceneSwitch(event,"FXMLcreateAccount.fxml" );
        }
        else if(buttonText.equals("Lost my password")){
            //DataStorage.getInstance().setNewSceneIs("FXMLLostPassword.fxml");
            DataStorage.getInstance().sceneSwitch(event, "FXMLLostPassword.fxml");
        }
        else{
            System.out.println("ERROR");
        }
    
    }

//----------------------------NON-FXML METHODS--------------------------------\\
    //This method checks if the users login info is correct
    private void handleButtonLogin() {
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // String URL = "jdbc:mysql://194.47.47.18:3306/YOUR_DATABASE_NAME?user=YOUR_USER_NAME&password=YOUR_PASSWORD";

            Connection c = DriverManager.getConnection(URL);
            Statement st = c.createStatement();
            // ResultSet rs = st.executeQuery("SELECT * FROM userlogin");
            //   String inputData = "INSERT INTO ´userlogin´ (userName,userPassword) VALUES (?,?";
            //st =inputData.prepareStatement(sql);
            //st.(userName.getText(), userPassword.getText());
            String dataCheck = "SELECT * FROM accounts WHERE Username = '" + userName.getText() + "' AND Password = '" + userPassword.getText() + "'";
            //
            //PreparedStatement s = c.prepareStatement(dataCheck);
            ResultSet rs = st.executeQuery(dataCheck);
            if (rs.next()) {
                System.out.println("Dina uppgifter är rätt. Du är inloggad");
                storedName = userName.getText();
                System.out.println("Active user is:  " + storedName);
                userCorrectCheck = true;

            } else {
                System.out.println("Fel användarnamn eller lösenord!");
                loginErrorLabel.setText("Fel användarnamn eller lösenord!");
            }

            // if(s.setString(1, userName.getText())&&  s.setString(2, userPassword.getText())){
            //  st.executeQuery(dataCheck);
            //}u
            //      else {  //else if isselected == true
            // System.out.println("Felaktiga uppgifter. Försök igen");//ska frågas om man vill skapa ett konto eller om felaktiga uppgifter
            //  PreparedStatement prepSt = c.prepareStatement(inputData);   //Skapar konto nedan
            //Ruta konmmer upp som kräver att man ska lägga välja  en hint som också läggs till. Ska läggas 2 extra på inputData då.
            // prepSt.setString(1, userName.getText());
            //prepSt.setString(2, userPassword.getText());
            //prepSt.executeUpdate();
            // while(rs.next()){
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
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            loginErrorLabel.setText("ERROR: " + e);
        }
    }    

}
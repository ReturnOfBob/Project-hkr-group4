/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Henrik
 */
public class FXMLLostPasswordController implements Initializable {
    @FXML
    private Label showInfo;
    @FXML
    private TextField inputRequiredText;
    PreparedStatement stt = null;
    String URL = "jdbc:mysql://127.0.0.1:3306/basicciv?user=root&password=root";
    private String storedName;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void handleButtonBackToLogin(ActionEvent event) {

        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLogInMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println("Error while changing scene to create account scene");
        }
    }

    @FXML
    public void handleButtonRetriveClue(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
       Connection c = DriverManager.getConnection(URL);
        Statement st = c.createStatement();
        String questionCheck = "SELECT  passwordHint FROM userlogin WHERE userName = '" + inputRequiredText.getText() + "'";//Ändra:   inputRequiredText.getText() istället för Henrik. Hämta även svar i samma kod så att det är gjort
      

         ResultSet rs = st.executeQuery(questionCheck);
            if (rs.next()) {
               
                storedName = inputRequiredText.getText();
                System.out.println(rs.getString(1));
               showInfo.setText(rs.getString(1));
         // c.close();
           } 
            
            
            else{
                System.out.println("Hittar inte användarnamnet");
            }
            //ResultSet rst = st.executeQuery(questionAnswer);
            //if(rs.next()){
              //  if(questionAnswer.equals(inputRequiredText.getText())){
                //   showInfo.setText(questionAnswer);
                //}
       
            }catch (SQLException ex) {
            Logger.getLogger(FXMLLostPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void handleButtonRetrivePassword(ActionEvent event)  {
        try{
            Connection c = DriverManager.getConnection(URL);
        Statement st = c.createStatement();
             String questionAnswer = "SELECT passwordHintAnswer FROM userlogin WHERE userName = '" + inputRequiredText.getText() + "'"; //Ändra till ett kommando istället för två se AND
             String passwordGetter = "SELECT userPassword FROM userlogin WHERE userName = '" + storedName + "'";
             ResultSet rt = st.executeQuery(passwordGetter); 
             ResultSet rs = st.executeQuery(questionAnswer);
            if (rs.next()) {
               
        
               if(rs.getString(1).equals(inputRequiredText)){   //Här ska man få lösenordet. Om hintsvar stämmer med hintlagrat svar så ska nedan göras
                   
                showInfo.setText(rt.getString(1));
                System.out.println(rt.getString(1));
            
                }
             
          //c.close();
           } 
            
        }
            
        catch(Exception ex){
            
        }
    }
}

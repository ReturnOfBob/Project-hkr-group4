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
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Henrik
 *
 */
public class FXMLcreateAccountController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField password;
    @FXML
    private TextField passwordAgain;
    @FXML
    private TextField question;
    @FXML
    private TextField questionAnswer;
    private boolean userExistCheck = true;

    String URL = "jdbc:mysql://127.0.0.1:3306/basic-civ?user=root&password=root";
    PreparedStatement stt = null;

    @FXML
    private void handleButtonCreateAccount(ActionEvent event) {
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection c = DriverManager.getConnection(URL);
            Statement st = c.createStatement();

            String inputData = "INSERT INTO userlogin (userName,userPassword,passwordHint,passwordHintAnswer,userPrivilege) VALUES (?,?,?,?,?)";
            String dataCheck = "SELECT * FROM userlogin WHERE userName = '" + name.getText() + "'";

            userExistCheck = true;
            ResultSet rs = st.executeQuery(dataCheck);
            if (rs.next()) {
                System.out.println("Användare finns redan");
                userExistCheck = false;

            }

            if (!"".equals(name.getText()) && !"".equals(password.getText()) && !"".equals(question.getText())
                    && !"".equals(questionAnswer.getText()) && password.getText().equals(passwordAgain.getText())) {

                PreparedStatement prepSt = c.prepareStatement(inputData);
                prepSt.setString(1, name.getText());
                prepSt.setString(2, password.getText());
                prepSt.setString(3, question.getText());
                prepSt.setString(4, questionAnswer.getText());
                prepSt.setString(5, "Basic user");
                prepSt.executeUpdate();
                System.out.println("Acccount created");
            } else if(!password.getText().equals(passwordAgain.getText())){
                System.out.println("Lösenorden matchar inte*!");
            }
            else {
                System.out.println("Du måste fylla i alla fält!");

            }

        } // while(rs.next()){
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
        catch (Exception e) {
            System.err.println("ERROR: " + e);
        }
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}

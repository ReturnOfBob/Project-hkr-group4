/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package civ.basic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Sweetpink
 */
public class FXMLGameController implements Initializable {
    
    @FXML
    private Button testButton;
    
    @FXML
    public void testMethod(){
        System.out.println("blab");
        //testButton.setOpacity(0);
        //testButton.setDisable(true);
        testButton.setVisible(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Sweetpink
 */
public class FXMLGameController implements Initializable {
    
    @FXML
    private Label goldLabel, woodLabel, stoneLabel, foodLabel, humanLabel, ironLabel, coalLabel, steelLabel;
    
    @FXML
    private Button houseButton, woodmillButton, farmButton, stonemasonryButton, nextturnButton;
    
    @FXML
    private TableView resourcesTableview, buildingsTableview, statviewTableview;
    
    @FXML
    private TextArea eventlogTextArea;
    
    //@FXML
    //private TableColumn<> nameColumn, amountColumn; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
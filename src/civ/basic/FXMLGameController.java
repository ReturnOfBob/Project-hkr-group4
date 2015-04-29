/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package civ.basic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
//------------------------------VARIABLES-------------------------------------\\    
    private double amountOfGold = 0;
    private double amountOfWood = 0;
    private double amountOfStone = 0;
    private double amountOfFood = 0;
    private double amountOfHuman = 0;
    private double amountOfIron = 0;
    private double amountOfCoal = 0;
    private double amountOfSteel = 0;
    private int currentTurn = 0;
    
    
    @FXML
    private Label goldLabel, woodLabel, stoneLabel, foodLabel, humanLabel, ironLabel, coalLabel, steelLabel, currentTurnLabel;
    
    @FXML
    private Button houseButton, woodmillButton, farmButton, stonemasonryButton, nextTurnButton;
    
    @FXML
    private TableView resourcesTableview, buildingsTableview, statviewTableview;
    
    @FXML
    private TextArea eventlogTextArea;
    
    //@FXML
    //private TableColumn<> nameColumn, amountColumn; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onNewGame();
    }    
    
    //------------------------------FXML METHODS----------------------------------\\
    @FXML
    private void nextTurn(ActionEvent event){
        currentTurn++;
        currentTurnLabel.setText("Current turn: " + currentTurn);
        
        refreshResources();
        
        if(currentTurn > 20){
            DataStorage.getInstance().sceneSwitch(event, "FXMLMainMenu.fxml");
        }

    }
    
    //----------------------------NON-FXML METHODS--------------------------------\\
    private void refreshResources(){
        
        goldLabel.setText("Gold: " + amountOfGold);
        woodLabel.setText("Wood: " + amountOfWood);
        stoneLabel.setText("Stone: " + amountOfStone);
        foodLabel.setText("Food: " + amountOfFood);
        humanLabel.setText("Human: " + amountOfHuman);
        ironLabel.setText("Iron: " + amountOfIron);
        coalLabel.setText("Coal: " + amountOfCoal);
        steelLabel.setText("Steel: " + amountOfSteel);
    }
    
    private void onNewGame(){
        currentTurn++;
        currentTurnLabel.setText("Current turn: " + currentTurn);
        
        refreshResources();
        
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package civ.basic;

import java.net.URL;
import static java.sql.Types.NULL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Sweetpink
 */
public class FXMLGameController implements Initializable {
//------------------------------VARIABLES-------------------------------------\\    
    private int amountOfGold = 0;
    private int amountOfWood = 0;
    private int amountOfStone = 0;
    private int amountOfFood = 0;
    private int amountOfHuman = 0;
    private int amountOfIron = 0;
    private int amountOfCoal = 0;
    private int amountOfSteel = 0;
    private int currentTurn = 0;
    
    private ObservableList<Resource> resourceList = FXCollections.observableArrayList();
    
//---------------------------------GUI----------------------------------------\\    
    @FXML
    private Label goldLabel, woodLabel, stoneLabel, foodLabel, humanLabel, ironLabel, coalLabel, steelLabel, currentTurnLabel, activeUserLabel;
    
    @FXML
    private Button houseButton, woodmillButton, farmButton, stonemasonryButton, bankButton, marketButton, ironMineButton, coalMineButton, storageButton, steelworksButton, cottageButton, nextTurnButton;
    
    @FXML
    private TableView /*resourcesTableview, buildingsTableview,*/ statviewTableview;
    
    @FXML
    private TextArea eventlogTextArea;
    
    
//------------------------RESOURCES TABLEVIEW---------------------------------\\
    
    @FXML
    private TableColumn<Resource, String> resourceNameColumn;
    @FXML
    private TableColumn<Resource, Number> resourceByTurnColumn;
    @FXML
    private TableView<Resource> resourcesTableview;
    
    
    //@FXML
    //private TableColumn<> nameColumn, amountColumn;     
    
//----------------------TEST VARIABLAR----------------------------------------\\    
    
    private ObservableList<Building> buildingList = FXCollections.observableArrayList();
    @FXML
    private TableColumn <Building, String> buildingNameColumn;
    @FXML
    private TableColumn <Building, Number> buildingAmountColumn;
    @FXML
    private TableView<Building> buildingsTableview;
    
    
//----------------------------ON SCENE LOADUP---------------------------------\\    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        resourceAdder();  //method calls must be in this order, otherwise a crash will occur
        buildingAdder();
        onNewGame();


    }    
//------------------------------FXML METHODS----------------------------------\\
    @FXML
    private void nextTurn(ActionEvent event){
        
        currentTurn++;
        currentTurnLabel.setText("Current turn: " + currentTurn);
        
        refreshResources();
        
        if(DataStorage.getInstance().getRoundLimit().equals(NULL)){
            DataStorage.getInstance().setRoundLimit(20);
        }
        
        if(currentTurn > DataStorage.getInstance().getRoundLimit()){
            DataStorage.getInstance().sceneSwitch(event, "FXMLMainMenu.fxml");
        }

    }

//----------------------------NON-FXML METHODS--------------------------------\\
    private void refreshResources(){
        amountOfGold += resourceList.get(0).getByTurn().getValue();
       
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
        activeUserLabel.setText(DataStorage.getInstance().getNewActiveUser());
    }
    
    private void rngHandler(){
        Random random = new Random();
        int randomNum = random.nextInt(1000);
        
        if(randomNum < 3){
            System.out.println("Kaboom, en meteorit small ner");
        }
    }
    private void resourceAdder(){
        resourcesTableview.setItems(resourceList);
        resourceNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        resourceByTurnColumn.setCellValueFactory(cellData -> cellData.getValue().getByTurn());

        resourceList.add(new Resource("Gold", 10));
        resourceList.add(new Resource("Wood", 10));
        resourceList.add(new Resource("stone", 5));
        resourceList.add(new Resource("Food", 10));
        resourceList.add(new Resource("Human", 2));
        resourceList.add(new Resource("Iron", 0));
        resourceList.add(new Resource("Coal", 0));
        resourceList.add(new Resource("Steel", 0));
    }
    
    private void buildingAdder(){
        buildingsTableview.setItems(buildingList);
        buildingNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        buildingAmountColumn.setCellValueFactory(cellData -> cellData.getValue().getAmount());
        
        buildingList.add(new NormalBuilding("House", 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2));
        buildingList.add(new NormalBuilding("Woodmill", 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, -1));
    } 
        public int getamountOfGold(){
        return amountOfGold;
    }
      public void setamountGold(int amountOfGold) {
        this.amountOfGold = amountOfGold;
    }
        public int getamountOfWood(){
        return amountOfWood;
    }
      public void setamountOfWood(int amountOfWood) {
        this.amountOfWood = amountOfWood;
    }
      public int getamountOfStone(){
        return amountOfWood;
    }
      public void setamountOfStone(int amountOfStone) {
        this.amountOfStone = amountOfStone;
    }
      public int getamountOfFood(){
        return amountOfFood;
    }
      public void setamountOfFood(int amountOfFood) {
        this.amountOfFood = amountOfFood;
    }
      public int getamountOfHuman(){
        return amountOfHuman;
    }
      public void setamountOfHuman(int amountOfHuman) {
        this.amountOfHuman = amountOfHuman;
    }
      public int getamountOfIron(){
        return amountOfIron;
    }
      public void setamountOfIron(int amountOfIron) {
        this.amountOfIron = amountOfIron;
    }
      public int getamountOfCoal(){
        return amountOfCoal;
    }
      public void setamountOfCoal(int amountOfCoal) {
        this.amountOfCoal = amountOfCoal;
    }
      public int getamountOfSteel(){
        return amountOfSteel;
    }
      public void setamountOfSteel(int amountOfSteel) {
        this.amountOfSteel = amountOfSteel;
    }
}



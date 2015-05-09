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
import static java.sql.Types.NULL;
import java.util.ArrayList;
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
    private int amountOfWood = 3/2;
    private int amountOfStone = 0;
    private int amountOfFood = 0;
    private int amountOfHuman = 0;
    private int amountOfIron = 0;
    private int amountOfCoal = 0;
    private int amountOfSteel = 0;
    private int currentTurn = 0;
    
    private ObservableList<Resource> resourceList = FXCollections.observableArrayList();
    private ObservableList<NormalBuilding> buildingList = FXCollections.observableArrayList();
    private DataBaseConnector connector = new DataBaseConnector();
    
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
    
//----------------------BUILDINGS TABLEVIEW-----------------------------------\\    

    @FXML
    private TableColumn <NormalBuilding, String> buildingNameColumn;
    @FXML
    private TableColumn <NormalBuilding, Number> buildingAmountColumn;
    @FXML
    private TableView<NormalBuilding> buildingsTableview;
    
    
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
        resourcePerTurnCalc();
        
        if(DataStorage.getInstance().getRoundLimit().equals(NULL)){
            DataStorage.getInstance().setRoundLimit(20);
        }
        
        if(currentTurn > DataStorage.getInstance().getRoundLimit()){
            DataStorage.getInstance().sceneSwitch(event, "FXMLMainMenu.fxml");
        }

    }
    
     @FXML
    private void buttonBuilderHandler(ActionEvent event){
        String buttonText;
        buttonText = ((Button) event.getSource()).getText();
        for(NormalBuilding building : buildingList){
            if(building.getName().getValue().equals(buttonText)){
                building.setAmount(1);
            }
        }
        resourcePerTurnCalc();
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
    
    private void resourcePerTurnCalc(){
        
        for(int i = 0; i < resourceList.size(); i++){
            resourceList.get(i).resetByTurn();
        }
        //buildingList.get(3).setAmount(2);  //test, shall be deleted after testing is done
        for(int i = 0; i < resourceList.size(); i++){
            resourceList.get(0).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getGold());
            resourceList.get(1).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getWood());
            resourceList.get(2).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getStone());
            resourceList.get(3).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getFood());
            resourceList.get(4).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getHuman());
            resourceList.get(5).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getIron());
            resourceList.get(6).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getCoal());
            resourceList.get(7).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getSteel());
        }
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

        resourceList.add(new Resource("Gold",0));
        resourceList.add(new Resource("Wood", 0));
        resourceList.add(new Resource("stone", 0));
        resourceList.add(new Resource("Food", 0));
        resourceList.add(new Resource("Human", 0));
        resourceList.add(new Resource("Iron", 0));
        resourceList.add(new Resource("Coal", 0));
        resourceList.add(new Resource("Steel", 0));
    }
    
    private void buildingAdder(){
        
        ArrayList<String> nameList = new ArrayList<>();
        
        int initialGoldint;
        int initialWoodint;
        int initialStoneint;
        int initialIronint;
        int initialCoalint;
        int initialSteelint;
        int initialFoodint;
        int initialHumanint;
        int amount = 0;  // Don't touch!
        int goldint;
        int woodint;
        int stoneint;
        int ironint;
        int coalint;
        int steelint;
        int foodint;
        int humanint;

        nameList.add("House");
        nameList.add("Farm");
        nameList.add("Market");
        nameList.add("Woodmill");
        nameList.add("Steelworks");
        nameList.add("Storage");
        nameList.add("Stonemasonry");
        nameList.add("Iron mine");
        nameList.add("Coal mine");
        
        buildingsTableview.setItems(buildingList);
        buildingNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        buildingAmountColumn.setCellValueFactory(cellData -> cellData.getValue().getAmount());
        
        for(int i = 0; i < 9; i++){
            
            initialGoldint = 0;
            initialWoodint = 0;
            initialStoneint = 0;
            initialIronint = 0;
            initialCoalint = 0;
            initialSteelint = 0;
            initialFoodint = 0;
            initialHumanint = 0;

            goldint = 0;
            woodint = 0;
            stoneint = 0;
            ironint = 0;
            coalint = 0;
            steelint = 0;
            foodint = 0;
            humanint = 0;
            
            try{

            String initialGold = "SELECT Initial_Cost FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Gold'";
            String initialWood = "SELECT Initial_Cost FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Wood'";
            String initialStone = "SELECT Initial_Cost FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Stone'";
            String initialIron = "SELECT Initial_Cost FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Iron'";
            String initialCoal = "SELECT Initial_Cost FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Coal'";
            String initialSteel = "SELECT Initial_Cost FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Steel'";
            String initialFood = "SELECT Initial_Cost FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Food'";
            String initialHuman = "SELECT Initial_Cost FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Human'";
            String gold = "SELECT Turn_Resource_Gain FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Gold'";
            String wood = "SELECT Turn_Resource_Gain FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Wood'";
            String stone = "SELECT Turn_Resource_Gain FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Stone'";
            String iron = "SELECT Turn_Resource_Gain FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Iron'";
            String coal = "SELECT Turn_Resource_Gain FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Coal'";
            String steel = "SELECT Turn_Resource_Gain FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Steel'";
            String food = "SELECT Turn_Resource_Gain FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Food'";
            String human = "SELECT Turn_Resource_Gain FROM buildings_manages_resources WHERE Buildings_Name = '" + nameList.get(i) + "' AND Resources_Name = 'Human'";
       
            if(connector.getResult(initialGold).next()){
                initialGoldint = Integer.parseInt(connector.getResultSet().getString(1));
            }
        
            if(connector.getResult(initialWood).next()){
                initialWoodint = Integer.parseInt(connector.getResultSet().getString(1));
            }
        
            if(connector.getResult(initialStone).next()){
                initialStoneint = Integer.parseInt(connector.getResultSet().getString(1));
            }
        
            if(connector.getResult(initialIron).next()){
                initialIronint = Integer.parseInt(connector.getResultSet().getString(1));
            }
        
            if(connector.getResult(initialCoal).next()){
                initialCoalint = Integer.parseInt(connector.getResultSet().getString(1));
            }
        
            if(connector.getResult(initialSteel).next()){
                initialSteelint = Integer.parseInt(connector.getResultSet().getString(1));
            }
        
            if(connector.getResult(initialFood).next()){
                initialFoodint = Integer.parseInt(connector.getResultSet().getString(1));
            }
        
            if(connector.getResult(initialHuman).next()){
                initialHumanint = Integer.parseInt(connector.getResultSet().getString(1));
            }

            if(connector.getResult(gold).next()){
                goldint = Integer.parseInt(connector.getResultSet().getString(1));
            }

            if(connector.getResult(wood).next()){
                woodint = Integer.parseInt(connector.getResultSet().getString(1));
            }

            if(connector.getResult(stone).next()){
                stoneint = Integer.parseInt(connector.getResultSet().getString(1));
            }
        
            if(connector.getResult(iron).next()){
                ironint = Integer.parseInt(connector.getResultSet().getString(1));
            }
        
            if(connector.getResult(coal).next()){
                coalint = Integer.parseInt(connector.getResultSet().getString(1));
            }

            if(connector.getResult(steel).next()){
                steelint = Integer.parseInt(connector.getResultSet().getString(1));
            }

            if(connector.getResult(food).next()){
                foodint = Integer.parseInt(connector.getResultSet().getString(1));
            }

            if(connector.getResult(human).next()){
                humanint = Integer.parseInt(connector.getResultSet().getString(1));
            }
        
            buildingList.add(new NormalBuilding(nameList.get(i), initialGoldint, initialWoodint, initialStoneint, initialIronint, initialCoalint,
                initialSteelint, initialFoodint, initialHumanint, amount, goldint, woodint, stoneint, ironint, coalint, steelint,
                foodint, humanint));
        
            }catch(Exception ex){
                System.out.println("Error with mySQL search");
                ex.printStackTrace();
            }
        }
            
        for(int i = 0; i < 9; i++){
            System.out.println(buildingList.get(i).getInitialWood());
            System.out.println(buildingList.get(i).getInitialHuman());
        }
    }
  
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package civ.basic;

import java.net.URL;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
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
    private int amountOfGold = 5;
    private int amountOfWood = 10;
    private int amountOfStone = 5;
    private int amountOfFood = 10;
    private int amountOfHuman = 2;
    private int amountOfIron = 0;
    private int amountOfCoal = 0;
    private int amountOfSteel = 0;
    private int currentTurn = 0;
    private int eventGoldMultiplier = 1;
    private int eventWoodMultiplier = 1;
    private int eventStoneMultiplier = 1;
    private int eventFoodMultiplier = 1;
    private int eventHumanMultiplier = 1;
    private int eventIronMultiplier = 1;
    private int eventCoalMultiplier = 1;
    private int eventSteelMultiplier = 1;
    
    private int resourceCap = 500;
    private boolean resourceAmountCheck = false;
    
    final private ObservableList<Resource> resourceList = FXCollections.observableArrayList();
    final private ObservableList<NormalBuilding> buildingList = FXCollections.observableArrayList();
    final private DataBaseConnector connector = new DataBaseConnector();
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
        
        //Events starts after the 5th turn
        if(currentTurn > 5){
            generateEvent();
        }
        //Handles the resources gain/lost after events
        addEventResources();
        addEventPercentageResources();
        //Resets the event resource values to their default values
        EventStorage.getInstance().resetEventResources();
        
        addResourcesToAmount();
        refreshResources();
        
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
        /*for(NormalBuilding building : buildingList){
            if(building.getName().getValue().equals(buttonText)){
                building.setAmount(1);
            }
        }*/
        resourceReducter(event);
        resourcePerTurnCalc();
        refreshResources();
    }

//----------------------------NON-FXML METHODS--------------------------------\\
    private void refreshResources(){
        goldLabel.setText("Gold: " + amountOfGold + "/" + resourceCap);
        woodLabel.setText("Wood: " + amountOfWood + "/" + resourceCap);
        stoneLabel.setText("Stone: " + amountOfStone + "/" + resourceCap);
        foodLabel.setText("Food: " + amountOfFood + "/" + resourceCap);
        humanLabel.setText("Human: " + amountOfHuman + "/" + resourceCap);
        ironLabel.setText("Iron: " + amountOfIron + "/" + resourceCap);
        coalLabel.setText("Coal: " + amountOfCoal + "/" + resourceCap);
        steelLabel.setText("Steel: " + amountOfSteel + "/" + resourceCap);
    }
    
    private void addResourcesToAmount(){
        amountOfGold += resourceList.get(0).getByTurn().getValue();
        amountOfWood += resourceList.get(1).getByTurn().getValue();
        amountOfStone += resourceList.get(2).getByTurn().getValue();
        amountOfFood += resourceList.get(3).getByTurn().getValue();
        amountOfIron += resourceList.get(5).getByTurn().getValue();
        amountOfCoal += resourceList.get(6).getByTurn().getValue();
        amountOfSteel += resourceList.get(7).getByTurn().getValue();
    }
    
    private void resourcePerTurnCalc(){
        
        for(int i = 0; i < resourceList.size(); i++){
            resourceList.get(i).resetByTurn();
        }
        for(int i = 0; i < buildingList.size(); i++){
            resourceList.get(0).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getGold() * eventGoldMultiplier);
            resourceList.get(1).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getWood() * eventWoodMultiplier);
            resourceList.get(2).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getStone() * eventStoneMultiplier);
            resourceList.get(3).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getFood() * eventFoodMultiplier);
            resourceList.get(4).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getHuman() * eventHumanMultiplier);
            resourceList.get(5).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getIron() * eventIronMultiplier);
            resourceList.get(6).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getCoal() * eventCoalMultiplier);
            resourceList.get(7).setByTurn(buildingList.get(i).getAmount().getValue() * buildingList.get(i).getSteel() * eventSteelMultiplier);
        }
    }
    
    private void resourceReducter(ActionEvent event){
        String buttonText;
        int amountGoldCheck = amountOfGold;
        int amountWoodCheck = amountOfWood;
        int amountStoneCheck = amountOfStone;
        int amountFoodCheck = amountOfFood;
        int amountHumanCheck = amountOfHuman;
        int amountIronCheck = amountOfIron;
        int amountCoalCheck = amountOfCoal;
        int amountSteelCheck = amountOfSteel;
        double multiplyer = 1;
        
        buttonText = ((Button) event.getSource()).getText();
        for(NormalBuilding building : buildingList){
            if(building.getName().getValue().equals(buttonText)){
                
                for(int i = 0; i < building.getAmount().getValue(); i++){
                    multiplyer *= 1.1;
                }
                
                amountGoldCheck -= (building.getInitialGold() * multiplyer);
                amountWoodCheck -= (building.getInitialWood() * multiplyer);
                amountStoneCheck -= (building.getInitialStone() * multiplyer);
                amountFoodCheck -= (building.getInitialFood() * multiplyer);
                amountHumanCheck -= building.getInitialHuman();
                amountIronCheck -= (building.getInitialIron() * multiplyer);
                amountCoalCheck -= (building.getInitialCoal() * multiplyer);
                amountSteelCheck -= (building.getInitialSteel() * multiplyer);
                
                if(amountGoldCheck < 0 || amountWoodCheck < 0 || amountStoneCheck < 0 || amountFoodCheck < 0 || amountHumanCheck < 0 ||
                        amountIronCheck < 0 || amountCoalCheck < 0 || amountSteelCheck < 0){
                    
                    resourceAmountCheck = true;
                }
                
                if(resourceAmountCheck == false){
                    amountOfGold -= (building.getInitialGold() * multiplyer);
                    amountOfWood -= (building.getInitialWood() * multiplyer);
                    amountOfStone -= (building.getInitialStone() * multiplyer);
                    amountOfFood -= (building.getInitialFood() * multiplyer);
                    amountOfHuman -= building.getInitialHuman();
                    amountOfIron -= (building.getInitialIron() * multiplyer);
                    amountOfCoal -= (building.getInitialCoal() * multiplyer);
                    amountOfSteel -= (building.getInitialSteel() * multiplyer);
                    
                    building.setAmount(1);
                }
                resourceAmountCheck = false;
            }
        } 
    }
    
    private void onNewGame(){
        
        currentTurn++;
        currentTurnLabel.setText("Current turn: " + currentTurn);
        
        refreshResources();
        activeUserLabel.setText(DataStorage.getInstance().getNewActiveUser());
    }
    
    private void generateEvent(){

        Random random = new Random();
        int randomNum = random.nextInt(1000);
        System.out.println(randomNum);
        
        if(EventHandler.getInstance().getEventDuration() < 1){
            EventHandler.getInstance().setEventIsActive(false);
            
        }
        
        EventHandler.getInstance().calculateEvent(randomNum);
        System.out.println(EventStorage.getInstance().getEventText());
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
        nameList.add("Stonemason");
        nameList.add("Iron mine");
        nameList.add("Coal mine");
        
        buildingsTableview.setItems(buildingList);
        buildingNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        buildingAmountColumn.setCellValueFactory(cellData -> cellData.getValue().getAmount());
        
        for(int i = 0; i < nameList.size(); i++){
            
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

           String initialGold = connector.getRescourseCommand("Initial_Cost",nameList.get(i), "Gold");   
           String initialWood = connector.getRescourseCommand("Initial_Cost",nameList.get(i), "Wood"); 
           String initialStone = connector.getRescourseCommand("Initial_Cost",nameList.get(i), "Stone");   
           String initialIron = connector.getRescourseCommand("Initial_Cost",nameList.get(i), "Iron"); 
           String initialCoal = connector.getRescourseCommand("Initial_Cost",nameList.get(i), "Coal");   
           String initialSteel = connector.getRescourseCommand("Initial_Cost",nameList.get(i), "Steel"); 
           String initialFood = connector.getRescourseCommand("Initial_Cost",nameList.get(i), "Food");   
           String initialHuman = connector.getRescourseCommand("Initial_Cost",nameList.get(i), "Human"); 
           String gold = connector.getRescourseCommand("Turn_Resource_Gain",nameList.get(i), "Gold");   
           String wood = connector.getRescourseCommand("Turn_Resource_Gain",nameList.get(i), "Wood"); 
           String stone = connector.getRescourseCommand("Turn_Resource_Gain",nameList.get(i), "Stone");   
           String iron = connector.getRescourseCommand("Turn_Resource_Gain",nameList.get(i), "Iron"); 
           String coal = connector.getRescourseCommand("Turn_Resource_Gain",nameList.get(i), "Coal");   
           String steel = connector.getRescourseCommand("Turn_Resource_Gain",nameList.get(i), "Steel"); 
           String food = connector.getRescourseCommand("Turn_Resource_Gain",nameList.get(i), "Food"); 
           String human = connector.getRescourseCommand("Turn_Resource_Gain",nameList.get(i), "Human"); 
           
            
           
            
            
            if(connector.getResult(initialGold).next()){
                initialGoldint = connector.getResultSet().getInt(1);
            }
        
            if(connector.getResult(initialWood).next()){
                initialWoodint = connector.getResultSet().getInt(1);
            }
        
            if(connector.getResult(initialStone).next()){
                initialStoneint = connector.getResultSet().getInt(1);
            }
        
            if(connector.getResult(initialIron).next()){
                initialIronint = connector.getResultSet().getInt(1);
            }
        
            if(connector.getResult(initialCoal).next()){
                initialCoalint = connector.getResultSet().getInt(1);
            }
        
            if(connector.getResult(initialSteel).next()){
                initialSteelint = connector.getResultSet().getInt(1);
            }
        
            if(connector.getResult(initialFood).next()){
                initialFoodint = connector.getResultSet().getInt(1);
            }
        
            if(connector.getResult(initialHuman).next()){
                initialHumanint = connector.getResultSet().getInt(1);
            }

            if(connector.getResult(gold).next()){
                goldint = connector.getResultSet().getInt(1);
            }

            if(connector.getResult(wood).next()){
                woodint = connector.getResultSet().getInt(1);
            }

            if(connector.getResult(stone).next()){
                stoneint = connector.getResultSet().getInt(1);
            }
        
            if(connector.getResult(iron).next()){
                ironint = connector.getResultSet().getInt(1);
            }
        
            if(connector.getResult(coal).next()){
                coalint = connector.getResultSet().getInt(1);
            }

            if(connector.getResult(steel).next()){
                steelint = connector.getResultSet().getInt(1);
            }

            if(connector.getResult(food).next()){
                foodint = connector.getResultSet().getInt(1);
            }

            if(connector.getResult(human).next()){
                humanint = connector.getResultSet().getInt(1);
            }
        
            buildingList.add(new NormalBuilding(nameList.get(i), initialGoldint, initialWoodint, initialStoneint, initialIronint, initialCoalint,
                initialSteelint, initialFoodint, initialHumanint, amount, goldint, woodint, stoneint, ironint, coalint, steelint,
                foodint, humanint));
        
            }catch(Exception ex){
                System.out.println("Error with mySQL search");
                ex.printStackTrace();
            }
        }
    }
    
    private void addEventResources(){
        
        amountOfGold += EventStorage.getInstance().getEventChangeGold();
        amountOfWood += EventStorage.getInstance().getEventChangeWood();
        amountOfStone += EventStorage.getInstance().getEventChangeStone();
        amountOfFood += EventStorage.getInstance().getEventChangeFood();
        amountOfHuman += EventStorage.getInstance().getEventChangeHuman();
        amountOfIron += EventStorage.getInstance().getEventChangeIron();
        amountOfCoal += EventStorage.getInstance().getEventChangeCoal();
        amountOfSteel += EventStorage.getInstance().getEventChangeSteel();
    }
    
    private void addEventPercentageResources(){
        amountOfGold = (int) (amountOfGold * EventStorage.getInstance().getEventPercentageChangeGold());
        amountOfWood = (int) (amountOfWood * EventStorage.getInstance().getEventPercentageChangeWood());
        amountOfStone = (int) (amountOfStone * EventStorage.getInstance().getEventPercentageChangeStone());
        amountOfFood = (int) (amountOfFood * EventStorage.getInstance().getEventPercentageChangeFood());
        amountOfHuman = (int) (amountOfHuman * EventStorage.getInstance().getEventPercentageChangeHuman());
        amountOfIron = (int) (amountOfIron * EventStorage.getInstance().getEventPercentageChangeIron());
        amountOfCoal = (int) (amountOfCoal * EventStorage.getInstance().getEventPercentageChangeCoal());
        amountOfSteel = (int) (amountOfSteel * EventStorage.getInstance().getEventPercentageChangeSteel());
    }
    
}

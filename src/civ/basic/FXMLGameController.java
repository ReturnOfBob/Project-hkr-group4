/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package civ.basic;

import java.net.URL;
import java.sql.SQLException;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private double uniqueBonusGold = 1;
    private double uniqueBonusWood = 1;
    private double uniqueBonusStone = 1;
    private double uniqueBonusFood = 1;
    private double uniqueBonusIron = 1;
    private double uniqueBonusCoal = 1;
    private double uniqueBonusSteel = 1;
    
    private int resourceCap = 500;
    private boolean resourceAmountCheck = false;
    private double difficultyMultiplier;
    
    final private ObservableList<Resource> resourceList = FXCollections.observableArrayList();
    final private ObservableList<NormalBuilding> normalBuildingList = FXCollections.observableArrayList();
    final private ObservableList<UniqueBuilding> uniqueBuildingList = FXCollections.observableArrayList();
    final private DataBaseConnector connector = new DataBaseConnector();
//---------------------------------GUI----------------------------------------\\    
    @FXML
    private Label goldLabel, woodLabel, stoneLabel, foodLabel, humanLabel, ironLabel, coalLabel, steelLabel, currentTurnLabel, activeUserLabel;
    
    @FXML
    private Button houseButton, woodmillButton, farmButton, stonemasonryButton, bankButton, marketButton, ironMineButton, coalMineButton, storageButton, steelworksButton, cottageButton, nextTurnButton,
            graneryButton, sawmillButton, bazaarButton, stoneworksButton, lumberjackButton, schoolButton, aqueductButton, workshopButton;
    
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
        try {
            buildingAdder();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        onNewGame();
        difficultySetter();
    }  
//------------------------------FXML METHODS----------------------------------\\
    @FXML
    private void nextTurn(ActionEvent event){
        currentTurn++;
        currentTurnLabel.setText("Current turn: " + currentTurn);
        
        if(EventHandler.getInstance().getEventDuration() > 0){
            EventHandler.getInstance().setEventDuration(EventHandler.getInstance().getEventDuration()-1);    
            System.out.println("Event dur: " + EventHandler.getInstance().getEventDuration());
        }
        
        //Events starts after the 3th turn
        if(currentTurn > 3){
            generateEvent();
        }
        
        
        //Handles the resources gain/lost after events
        addEventResources();
        addEventPercentageResources();
        //Resets the event resource values to their default values
        EventStorage.getInstance().resetEventResources();
        
        addResourcesToAmount();
        resourcePerTurnCalc();
        refreshResources();
        upgradeBuildingButtonsHandler();
        
        System.out.println(EventStorage.getInstance().getEventChangeWoodMultiplier());
        if(DataStorage.getInstance().getRoundLimit().equals(NULL)){
            DataStorage.getInstance().setRoundLimit(50);
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
        upgradeBuildingButtonsHandler();
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
        resourceCapHandler();
    }
    
    private void resourcePerTurnCalc(){
        uniqueBuildingBonusAdder();
        for(int i = 0; i < resourceList.size(); i++){
            resourceList.get(i).resetByTurn();
        }
        for(int i = 0; i < normalBuildingList.size(); i++){
            resourceList.get(0).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getGold() * EventStorage.getInstance().getEventChangeGoldMultiplier() * uniqueBonusGold));
            resourceList.get(1).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getWood() * EventStorage.getInstance().getEventChangeWoodMultiplier() * uniqueBonusWood));
            resourceList.get(2).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getStone() * EventStorage.getInstance().getEventChangeStoneMultiplier() * uniqueBonusStone));
            resourceList.get(3).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getFood() * EventStorage.getInstance().getEventChangeFoodMultiplier() * uniqueBonusFood));
            resourceList.get(4).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getHuman() * EventStorage.getInstance().getEventChangeHumanMultiplier()));
            resourceList.get(5).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getIron() * EventStorage.getInstance().getEventChangeIronMultiplier() * uniqueBonusIron));
            resourceList.get(6).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getCoal() * EventStorage.getInstance().getEventChangeCoalMultiplier() * uniqueBonusCoal));
            resourceList.get(7).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getSteel() * EventStorage.getInstance().getEventChangeSteelMultiplier() * uniqueBonusSteel));
            
        }
    }
    
    private void uniqueBuildingBonusAdder(){
        
        uniqueBonusGold = 1;
        uniqueBonusWood = 1;
        uniqueBonusStone = 1;
        uniqueBonusFood = 1;
        uniqueBonusIron = 1;
        uniqueBonusCoal = 1;
        uniqueBonusSteel = 1;
      
        for(int i = 0; i < uniqueBuildingList.size(); i++){
            uniqueBonusGold += (uniqueBuildingList.get(i).getBonusGold() * uniqueBuildingList.get(i).getAmount().getValue());
            uniqueBonusWood += (uniqueBuildingList.get(i).getBonusWood() * uniqueBuildingList.get(i).getAmount().getValue());
            uniqueBonusStone += (uniqueBuildingList.get(i).getBonusStone() * uniqueBuildingList.get(i).getAmount().getValue());
            uniqueBonusFood += (uniqueBuildingList.get(i).getBonusFood() * uniqueBuildingList.get(i).getAmount().getValue());
            uniqueBonusIron += (uniqueBuildingList.get(i).getBonusIron() * uniqueBuildingList.get(i).getAmount().getValue());
            uniqueBonusCoal += (uniqueBuildingList.get(i).getBonusCoal() * uniqueBuildingList.get(i).getAmount().getValue());
            uniqueBonusSteel += (uniqueBuildingList.get(i).getBonusSteel() * uniqueBuildingList.get(i).getAmount().getValue());
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
        double multiplier = 1;
        
        buttonText = ((Button) event.getSource()).getText();
        for(NormalBuilding building : normalBuildingList){
            if(building.getName().getValue().equals(buttonText)){
                System.out.println("NormalBuilding");  //delete when done with game
                
                for(int i = 0; i < building.getAmount().getValue(); i++){
                    multiplier *= 1.1;
                }
                
                amountGoldCheck -= (building.getInitialGold() * multiplier * difficultyMultiplier);
                amountWoodCheck -= (building.getInitialWood() * multiplier * difficultyMultiplier);
                amountStoneCheck -= (building.getInitialStone() * multiplier * difficultyMultiplier);
                amountFoodCheck -= (building.getInitialFood() * multiplier * difficultyMultiplier);
                amountHumanCheck -= building.getInitialHuman();
                amountIronCheck -= (building.getInitialIron() * multiplier * difficultyMultiplier);
                amountCoalCheck -= (building.getInitialCoal() * multiplier * difficultyMultiplier);
                amountSteelCheck -= (building.getInitialSteel() * multiplier * difficultyMultiplier);
                
                if(amountGoldCheck < 0 || amountWoodCheck < 0 || amountStoneCheck < 0 || amountFoodCheck < 0 || amountHumanCheck < 0 ||
                        amountIronCheck < 0 || amountCoalCheck < 0 || amountSteelCheck < 0){
                    
                    resourceAmountCheck = true;
                }
                
                if(resourceAmountCheck == false){
                    amountOfGold -= (building.getInitialGold() * multiplier * difficultyMultiplier);
                    amountOfWood -= (building.getInitialWood() * multiplier * difficultyMultiplier);
                    amountOfStone -= (building.getInitialStone() * multiplier * difficultyMultiplier);
                    amountOfFood -= (building.getInitialFood() * multiplier * difficultyMultiplier);
                    amountOfHuman -= building.getInitialHuman();
                    amountOfIron -= (building.getInitialIron() * multiplier * difficultyMultiplier);
                    amountOfCoal -= (building.getInitialCoal() * multiplier * difficultyMultiplier);
                    amountOfSteel -= (building.getInitialSteel() * multiplier * difficultyMultiplier);
                    
                    if(building.getName().getValue().equals("Storage")){
                        resourceCap += 100;
                    }
                    
                    building.setAmount(1);
                }
                resourceAmountCheck = false;
            }
        }
        for(UniqueBuilding building : uniqueBuildingList){
            if(building.getName().getValue().equals(buttonText)){
                
                amountGoldCheck -= (building.getInitialGold() * difficultyMultiplier);
                amountWoodCheck -= (building.getInitialWood() * difficultyMultiplier);
                amountStoneCheck -= (building.getInitialStone() * difficultyMultiplier);
                amountFoodCheck -= (building.getInitialFood() * difficultyMultiplier);
                amountHumanCheck -= (building.getInitialHuman() * difficultyMultiplier);
                amountIronCheck -= (building.getInitialIron() * difficultyMultiplier);
                amountCoalCheck -= (building.getInitialCoal() * difficultyMultiplier);
                amountSteelCheck -= (building.getInitialSteel() * difficultyMultiplier);
                
                if(amountGoldCheck < 0 || amountWoodCheck < 0 || amountStoneCheck < 0 || amountFoodCheck < 0 || amountHumanCheck < 0 ||
                        amountIronCheck < 0 || amountCoalCheck < 0 || amountSteelCheck < 0 || building.getAmount().getValue() > 0){
                    
                    resourceAmountCheck = true;
                }
                if(resourceAmountCheck == false){
                    amountOfGold -= (building.getInitialGold() * difficultyMultiplier);
                    amountOfWood -= (building.getInitialWood() * difficultyMultiplier);
                    amountOfStone -= (building.getInitialStone() * difficultyMultiplier);
                    amountOfFood -= (building.getInitialFood() * difficultyMultiplier);
                    amountOfHuman -= (building.getInitialHuman() * difficultyMultiplier);
                    amountOfIron -= (building.getInitialIron() * difficultyMultiplier);
                    amountOfCoal -= (building.getInitialCoal() * difficultyMultiplier);
                    amountOfSteel -= (building.getInitialSteel() * difficultyMultiplier);
                    
                    building.setAmount(1);
                }
                resourceAmountCheck = false;
            }
        }        
    }
    
    private void resourceCapHandler(){
        if(amountOfGold > resourceCap){
            amountOfGold = resourceCap;
        }
        if(amountOfWood > resourceCap){
            amountOfWood = resourceCap;
        }
        if(amountOfStone > resourceCap){
            amountOfStone = resourceCap;
        }
        if(amountOfFood > resourceCap){
            amountOfFood = resourceCap;
        }
        if(amountOfHuman > resourceCap){
            amountOfHuman = resourceCap;
        }
        if(amountOfIron > resourceCap){
            amountOfIron = resourceCap;
        }
        if(amountOfCoal > resourceCap){
            amountOfCoal = resourceCap;
        }
        if(amountOfSteel > resourceCap){
            amountOfSteel = resourceCap;
        }
        if(amountOfGold < 0){
            amountOfGold = 0;
        }
        if(amountOfWood < 0){
            amountOfWood = 0;
        }
        if(amountOfStone < 0){
            amountOfStone = 0;
        }
        if(amountOfFood < 0){
            amountOfFood = 0;
        }
        if(amountOfHuman < 0){
            amountOfHuman = 0;
        }
        if(amountOfIron < 0){
            amountOfIron = 0;
        }
        if(amountOfCoal < 0){
            amountOfCoal = 0;
        }
        if(amountOfSteel < 0){
            amountOfSteel = 0;
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
        int randomNum = random.nextInt(500);
        System.out.println(randomNum);
        
        if(EventHandler.getInstance().getEventDuration() < 1){
            EventHandler.getInstance().setEventIsActive(false);
            EventStorage.getInstance().resetTimedEvents();
        }
        
        EventHandler.getInstance().calculateEvent(randomNum);
        System.out.println(EventStorage.getInstance().getEventText());
        EventStorage.getInstance().setEventText("");
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
    
    private void buildingAdder() throws SQLException{
        ArrayList<String> nameList = new ArrayList<>();
        int changeBuildingTypeChecker = 0;
        
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
        nameList.add("Cottage");
        nameList.add("Granery");
        nameList.add("Stoneworks");
        nameList.add("Bazaar");
        nameList.add("Sawmill");
        nameList.add("Bank");
        nameList.add("Lumberjack school");
        nameList.add("School");
        nameList.add("Aqueduct");
        nameList.add("Workshop");
        
        buildingsTableview.setItems(normalBuildingList);
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
                connector.close();
            }
        
            if(connector.getResult(initialWood).next()){
                initialWoodint = connector.getResultSet().getInt(1);
                connector.close();
            }
        
            if(connector.getResult(initialStone).next()){
                initialStoneint = connector.getResultSet().getInt(1);
                connector.close();
            }
        
            if(connector.getResult(initialIron).next()){
                initialIronint = connector.getResultSet().getInt(1);
                connector.close();
            }
        
            if(connector.getResult(initialCoal).next()){
                initialCoalint = connector.getResultSet().getInt(1);
                connector.close();
            }
        
            if(connector.getResult(initialSteel).next()){
                initialSteelint = connector.getResultSet().getInt(1);
                connector.close();
            }
        
            if(connector.getResult(initialFood).next()){
                initialFoodint = connector.getResultSet().getInt(1);
                connector.close();
            }
        
            if(connector.getResult(initialHuman).next()){
                initialHumanint = connector.getResultSet().getInt(1);
                connector.close();
            }

            if(connector.getResult(gold).next()){
                goldint = connector.getResultSet().getInt(1);
                connector.close();
            }

            if(connector.getResult(wood).next()){
                woodint = connector.getResultSet().getInt(1);
                connector.close();
            }

            if(connector.getResult(stone).next()){
                stoneint = connector.getResultSet().getInt(1);
                connector.close();
            }
        
            if(connector.getResult(iron).next()){
                ironint = connector.getResultSet().getInt(1);
                connector.close();
            }
        
            if(connector.getResult(coal).next()){
                coalint = connector.getResultSet().getInt(1);
                connector.close();
            }

            if(connector.getResult(steel).next()){
                steelint = connector.getResultSet().getInt(1);
                connector.close();
            }

            if(connector.getResult(food).next()){
                foodint = connector.getResultSet().getInt(1);
                connector.close();
            }

            if(connector.getResult(human).next()){
                humanint = connector.getResultSet().getInt(1);
                connector.close();
            }
            
            System.out.println(changeBuildingTypeChecker);
            if(changeBuildingTypeChecker < 14){
                normalBuildingList.add(new NormalBuilding(nameList.get(i), initialGoldint, initialWoodint, initialStoneint, initialIronint, initialCoalint,
                    initialSteelint, initialFoodint, initialHumanint, amount, goldint, woodint, stoneint, ironint, coalint, steelint,
                    foodint, humanint));
                    changeBuildingTypeChecker++;
            }else{
                uniqueBuildingList.add(new UniqueBuilding(nameList.get(i), initialGoldint, initialWoodint, initialStoneint, initialIronint, initialCoalint,
                    initialSteelint, initialFoodint, initialHumanint, amount, goldint, woodint, stoneint, ironint, coalint, steelint,
                    foodint, humanint));
            }
            
            
            
            }catch(Exception ex){
                System.out.println("Error with mySQL search");
                ex.printStackTrace();
            }finally{
                connector.close();
            }
            
        }
        System.out.println(normalBuildingList.get(2).getGold());
        
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
    
    private void upgradeBuildingButtonsHandler(){
        if(normalBuildingList.get(0).getAmount().getValue() >= 15){
            cottageButton.setDisable(false);
            cottageButton.setOpacity(1);       
        }
        if(normalBuildingList.get(1).getAmount().getValue() >= 7){
            graneryButton.setDisable(false);
            graneryButton.setOpacity(1);
        }
        if(normalBuildingList.get(2).getAmount().getValue() >= 8){
            bazaarButton.setDisable(false);
            bazaarButton.setOpacity(1);
        }
        if(normalBuildingList.get(3).getAmount().getValue() >= 13){
            sawmillButton.setDisable(false);
            sawmillButton.setOpacity(1);
        }
        if(normalBuildingList.get(6).getAmount().getValue() >= 10){
            stoneworksButton.setDisable(false);
            stoneworksButton.setOpacity(1);
        }
        if(uniqueBuildingList.get(0).getAmount().getValue() > 0){
            bankButton.setDisable(true);
            bankButton.setOpacity(0.3);
        }
        if(uniqueBuildingList.get(1).getAmount().getValue() > 0){
            lumberjackButton.setDisable(true);
            lumberjackButton.setOpacity(0.3);
        }
        if(uniqueBuildingList.get(2).getAmount().getValue() > 0){
            schoolButton.setDisable(true);
            schoolButton.setOpacity(0.3);
        }
        if(uniqueBuildingList.get(3).getAmount().getValue() > 0){
            aqueductButton.setDisable(true);
            aqueductButton.setOpacity(0.3);
        }
        if(uniqueBuildingList.get(4).getAmount().getValue() > 0){
            workshopButton.setDisable(true);
            workshopButton.setOpacity(0.3);
        }
    }
    
    private void difficultySetter(){
            
            if(DataStorage.getInstance().getDifficulty().equals("noob")){
                difficultyMultiplier = 0.8; 
            }else if(DataStorage.getInstance().getDifficulty().equals("normal")){
                difficultyMultiplier = 1;
            }else if(DataStorage.getInstance().getDifficulty().equals("asian")){
                difficultyMultiplier = 1.2;
            }
        }
}

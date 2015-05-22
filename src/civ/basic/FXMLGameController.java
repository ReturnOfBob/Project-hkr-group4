/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;


import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sweetpink
 */
public class FXMLGameController implements Initializable {
//------------------------------VARIABLES-------------------------------------\\    

    private int amountOfGold = 10;
    private int amountOfWood = 25;
    private int amountOfStone = 15;
    private int amountOfFood = 130;
    private int amountOfHuman = 4;
    private int amountOfIron = 0;
    private int amountOfCoal = 0;
    private int amountOfSteel = 0;
    private int currentTurn = 0;
    private int resourceCap = 500;

    private double uniqueBonusGold = 1;
    private double uniqueBonusWood = 1;
    private double uniqueBonusStone = 1;
    private double uniqueBonusFood = 1;
    private double uniqueBonusIron = 1;
    private double uniqueBonusCoal = 1;
    private double uniqueBonusSteel = 1;
    private double difficultyMultiplier;

    private String statsViewBuildingName;
    private String eventLogDisplayText;

    private boolean resourceAmountCheck = false;

    final private ObservableList<Resource> resourceList = FXCollections.observableArrayList();
    final private ObservableList<NormalBuilding> normalBuildingList = FXCollections.observableArrayList();
    final private ObservableList<UniqueBuilding> uniqueBuildingList = FXCollections.observableArrayList();
    final private ObservableList<StatsViewDisplayObject> statsViewDisplayList = FXCollections.observableArrayList();
    final private DataBaseConnector connector = new DataBaseConnector();
//---------------------------------GUI----------------------------------------\\    
    @FXML
    private Label goldLabel, woodLabel, stoneLabel, foodLabel, humanLabel, ironLabel, coalLabel, steelLabel, currentTurnLabel, activeUserLabel, statsViewBuildingLabel, statsViewNoteLabel, scoreLabel, cheatCodeLabel, cheatCodeErrorLabel;

    @FXML
    private Button houseButton, woodmillButton, farmButton, stonemasonryButton, bankButton, marketButton, ironMineButton, coalMineButton, storageButton, steelworksButton, cottageButton, nextTurnButton,
            graneryButton, sawmillButton, bazaarButton, stoneworksButton, lumberjackButton, schoolButton, aqueductButton, workshopButton, ingameMenuButton, resumeGameButton, cheatCodeButton, backToMainMenuButton, exitGame, cheatCodesOkButton;

    @FXML
    private TextArea eventlogTextArea;

    @FXML
    private TextField cheatCodeField;

    @FXML
    private AnchorPane popUp, ingameMenuPopUp, ingameMenuBlocker;

    @FXML
    private Button popUpButton;

    @FXML
    private TextArea popUpText;

//------------------------RESOURCES TABLEVIEW---------------------------------\\
    @FXML
    private TableColumn<Resource, String> resourceNameColumn;
    @FXML
    private TableColumn<Resource, Number> resourceByTurnColumn;
    @FXML
    private TableView<Resource> resourcesTableview;

//----------------------BUILDINGS TABLEVIEW-----------------------------------\\    
    @FXML
    private TableColumn<NormalBuilding, String> buildingNameColumn;
    @FXML
    private TableColumn<NormalBuilding, Number> buildingAmountColumn;
    @FXML
    private TableView<NormalBuilding> buildingsTableview;
//--------------------------BUILDING STATSVIEW TABLE--------------------------\\ 

    @FXML
    private TableView statviewTableview;
    @FXML
    private TableColumn<StatsViewDisplayObject, String> statsViewResource, statsViewCost, statsViewProduces;

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
        scoreCalculator();

        statsViewDisplayList.add(new StatsViewDisplayObject("", "", "", "", ""));
        statviewTableview.setItems(statsViewDisplayList);
        refreshEventLogText("Turn: " + currentTurn);
        resourceList.get(1).setByTurn(2);
        
    }
//------------------------------FXML METHODS----------------------------------\\

    @FXML
    private void nextTurn(ActionEvent event) {
        currentTurn++;
        currentTurnLabel.setText("Current turn: " + currentTurn);
        refreshEventLogText("Turn: " + currentTurn);

        if (EventHandler.getInstance().getEventDuration() > 0) {
            EventHandler.getInstance().setEventDuration(EventHandler.getInstance().getEventDuration() - 1);
            System.out.println("Event dur: " + EventHandler.getInstance().getEventDuration());
        }

        //Events starts after the 3th turn
        if (currentTurn > 3) {
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
        scoreCalculator();

        System.out.println(EventStorage.getInstance().getEventChangeWoodMultiplier());

        if (currentTurn > DataStorage.getInstance().getRoundLimit()) {
            insertHighScore();
            DataStorage.getInstance().sceneSwitch(event, "FXMLMainMenu.fxml");
        }

    }

    @FXML
    private void buttonBuilderHandler(ActionEvent event) {
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
        scoreCalculator();
    }

    @FXML
    private void popUpButtonHandler(ActionEvent event) {
        popUp.setOpacity(0);
        popUp.setDisable(true);
        popUpText.setDisable(true);
    }

    @FXML
    private void showStatsViewColumns(MouseEvent event) {

        statsViewBuildingName = ((Button) event.getSource()).getText();
        System.out.println("In");

        switch (statsViewBuildingName) {
            case "House":
                refreshStatsViewColumns(true, 0);
                break;
            case "Farm":
                refreshStatsViewColumns(true, 1);
                break;
            case "Market":
                refreshStatsViewColumns(true, 2);
                break;
            case "Woodmill":
                refreshStatsViewColumns(true, 3);
                break;
            case "Steelworks":
                refreshStatsViewColumns(true, 4);
                break;
            case "Storage":
                refreshStatsViewColumns(true, 5);
                statsViewNoteLabel.setText("Note: Gives more space for resources.");
                break;
            case "Stonemason":
                refreshStatsViewColumns(true, 6);
                break;
            case "Iron mine":
                refreshStatsViewColumns(true, 7);
                break;
            case "Coal mine":
                refreshStatsViewColumns(true, 8);
                break;
            case "Cottage":
                refreshStatsViewColumns(true, 9);
                break;
            case "Granery":
                refreshStatsViewColumns(true, 10);
                break;
            case "Stoneworks":
                refreshStatsViewColumns(true, 11);
                break;
            case "Bazaar":
                refreshStatsViewColumns(true, 12);
                break;
            case "Sawmill":
                refreshStatsViewColumns(true, 13);
                break;
            case "Bank":
                refreshStatsViewColumns(false, 0);
                break;
            case "Lumberjack school":
                refreshStatsViewColumns(false, 1);
                break;
            case "School":
                refreshStatsViewColumns(false, 2);
                break;
            case "Aqueduct":
                refreshStatsViewColumns(false, 3);
                break;
            case "Workshop":
                refreshStatsViewColumns(false, 4);
                break;
        }

    }

    @FXML
    private void hideStatsViewColumns() {
        System.out.println("Out");

        statsViewDisplayList.removeAll(statsViewDisplayList);
        statsViewDisplayList.add(new StatsViewDisplayObject("", "", "", "", ""));
        statviewTableview.setItems(statsViewDisplayList);

        statsViewBuildingLabel.setText("");
        statsViewNoteLabel.setText("");

    }

    @FXML
    private void showIngameMenu() {
        ingameMenuPopUp.setVisible(true);
        ingameMenuBlocker.setVisible(true);
    }

    @FXML
    private void ingameMenuButtonClicked(ActionEvent event) {
        String buttonText;
        buttonText = ((Button) event.getSource()).getText();

        if (buttonText.equals("Resume Game")) {
            ingameMenuPopUp.setVisible(false);
            ingameMenuBlocker.setVisible(false);
            hideCheatCodeTools();
        } else if (buttonText.equals("Cheat Code")) {

            cheatCodeLabel.setLayoutX(53);
            cheatCodeLabel.setLayoutY(255);
            cheatCodeLabel.setText("Enter chead code:");
            cheatCodeErrorLabel.setVisible(false);
            cheatCodeField.setVisible(true);
            cheatCodesOkButton.setVisible(true);
        } else if (buttonText.equals("Back To Main Menu")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLMainMenu.fxml");
        } else if (buttonText.equals("Exit Game")) {
            try {
                System.exit(0);
            } catch (Exception ex) {
                System.out.println("ERROR EXIT!");
            }
        }

    }

    @FXML
    private void handleCheatCodes() {
        if (cheatCodeField.getText().equals("haxmedlax")) {
            amountOfGold = amountOfGold + 100;
            amountOfWood = amountOfWood + 100;
            amountOfStone = amountOfStone + 100;
            refreshResources();
            codeSuccessfull();
        } else if (cheatCodeField.getText().equals("thereisnospoon")) {
            amountOfSteel = amountOfSteel + 200;
            refreshResources();
            codeSuccessfull();
        } else {

            cheatCodeErrorLabel.setVisible(true);
        }

        cheatCodeField.setText("");
    }

//----------------------------NON-FXML METHODS--------------------------------\\
    private void refreshResources() {
        goldLabel.setText("Gold: " + amountOfGold + "/" + resourceCap);
        woodLabel.setText("Wood: " + amountOfWood + "/" + resourceCap);
        stoneLabel.setText("Stone: " + amountOfStone + "/" + resourceCap);
        foodLabel.setText("Food: " + amountOfFood + "/" + resourceCap);
        humanLabel.setText("Human: " + amountOfHuman + "/" + resourceCap);
        ironLabel.setText("Iron: " + amountOfIron + "/" + resourceCap);
        coalLabel.setText("Coal: " + amountOfCoal + "/" + resourceCap);
        steelLabel.setText("Steel: " + amountOfSteel + "/" + resourceCap);
    }

    private void addResourcesToAmount() {
        amountOfGold += resourceList.get(0).getByTurn().getValue();
        amountOfWood += resourceList.get(1).getByTurn().getValue();
        amountOfStone += resourceList.get(2).getByTurn().getValue();
        amountOfFood += resourceList.get(3).getByTurn().getValue();
        amountOfIron += resourceList.get(5).getByTurn().getValue();
        amountOfCoal += resourceList.get(6).getByTurn().getValue();
        amountOfSteel += resourceList.get(7).getByTurn().getValue();
        resourceCapHandler();
    }

    private void resourcePerTurnCalc() {
        uniqueBuildingBonusAdder();
        for (int i = 0; i < resourceList.size(); i++) {
            resourceList.get(i).resetByTurn();
        }
        for (int i = 0; i < normalBuildingList.size(); i++) {
            resourceList.get(0).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getGold() * EventStorage.getInstance().getEventChangeGoldMultiplier() * uniqueBonusGold));
            resourceList.get(1).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getWood() * EventStorage.getInstance().getEventChangeWoodMultiplier() * uniqueBonusWood));
            resourceList.get(2).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getStone() * EventStorage.getInstance().getEventChangeStoneMultiplier() * uniqueBonusStone));
            resourceList.get(3).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getFood() * EventStorage.getInstance().getEventChangeFoodMultiplier() * uniqueBonusFood));
            resourceList.get(4).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getHuman() * EventStorage.getInstance().getEventChangeHumanMultiplier()));
            resourceList.get(5).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getIron() * EventStorage.getInstance().getEventChangeIronMultiplier() * uniqueBonusIron));
            resourceList.get(6).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getCoal() * EventStorage.getInstance().getEventChangeCoalMultiplier() * uniqueBonusCoal));
            resourceList.get(7).setByTurn((int) (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getSteel() * EventStorage.getInstance().getEventChangeSteelMultiplier() * uniqueBonusSteel));
            
        }
        resourceList.get(1).setByTurn(2);
    }

    private void uniqueBuildingBonusAdder() {

        uniqueBonusGold = 1;
        uniqueBonusWood = 1;
        uniqueBonusStone = 1;
        uniqueBonusFood = 1;
        uniqueBonusIron = 1;
        uniqueBonusCoal = 1;
        uniqueBonusSteel = 1;

        for (int i = 0; i < uniqueBuildingList.size(); i++) {
            uniqueBonusGold += (uniqueBuildingList.get(i).getBonusGold() * uniqueBuildingList.get(i).getAmount().getValue());
            uniqueBonusWood += (uniqueBuildingList.get(i).getBonusWood() * uniqueBuildingList.get(i).getAmount().getValue());
            uniqueBonusStone += (uniqueBuildingList.get(i).getBonusStone() * uniqueBuildingList.get(i).getAmount().getValue());
            uniqueBonusFood += (uniqueBuildingList.get(i).getBonusFood() * uniqueBuildingList.get(i).getAmount().getValue());
            uniqueBonusIron += (uniqueBuildingList.get(i).getBonusIron() * uniqueBuildingList.get(i).getAmount().getValue());
            uniqueBonusCoal += (uniqueBuildingList.get(i).getBonusCoal() * uniqueBuildingList.get(i).getAmount().getValue());
            uniqueBonusSteel += (uniqueBuildingList.get(i).getBonusSteel() * uniqueBuildingList.get(i).getAmount().getValue());
        }
    }

    private void resourceReducter(ActionEvent event) {
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
        for (NormalBuilding building : normalBuildingList) {
            if (building.getName().getValue().equals(buttonText)) {

                for (int i = 0; i < building.getAmount().getValue(); i++) {
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

                if (amountGoldCheck < 0 || amountWoodCheck < 0 || amountStoneCheck < 0 || amountFoodCheck < 0 || amountHumanCheck < 0
                        || amountIronCheck < 0 || amountCoalCheck < 0 || amountSteelCheck < 0) {

                    resourceAmountCheck = true;
                }

                if (resourceAmountCheck == false) {
                    amountOfGold -= (building.getInitialGold() * multiplier * difficultyMultiplier);
                    amountOfWood -= (building.getInitialWood() * multiplier * difficultyMultiplier);
                    amountOfStone -= (building.getInitialStone() * multiplier * difficultyMultiplier);
                    amountOfFood -= (building.getInitialFood() * multiplier * difficultyMultiplier);
                    amountOfHuman -= building.getInitialHuman();
                    amountOfIron -= (building.getInitialIron() * multiplier * difficultyMultiplier);
                    amountOfCoal -= (building.getInitialCoal() * multiplier * difficultyMultiplier);
                    amountOfSteel -= (building.getInitialSteel() * multiplier * difficultyMultiplier);

                    if (building.getName().getValue().equals("Storage")) {
                        resourceCap += 100;
                    }

                    building.setAmount(1);
                    refreshEventLogText("A " + buttonText + " was successfully built.");
                }
                resourceAmountCheck = false;
            }
        }
        for (UniqueBuilding building : uniqueBuildingList) {
            if (building.getName().getValue().equals(buttonText)) {

                amountGoldCheck -= (building.getInitialGold() * difficultyMultiplier);
                amountWoodCheck -= (building.getInitialWood() * difficultyMultiplier);
                amountStoneCheck -= (building.getInitialStone() * difficultyMultiplier);
                amountFoodCheck -= (building.getInitialFood() * difficultyMultiplier);
                amountHumanCheck -= (building.getInitialHuman() * difficultyMultiplier);
                amountIronCheck -= (building.getInitialIron() * difficultyMultiplier);
                amountCoalCheck -= (building.getInitialCoal() * difficultyMultiplier);
                amountSteelCheck -= (building.getInitialSteel() * difficultyMultiplier);

                if (amountGoldCheck < 0 || amountWoodCheck < 0 || amountStoneCheck < 0 || amountFoodCheck < 0 || amountHumanCheck < 0
                        || amountIronCheck < 0 || amountCoalCheck < 0 || amountSteelCheck < 0 || building.getAmount().getValue() > 0) {

                    resourceAmountCheck = true;
                }
                if (resourceAmountCheck == false) {
                    amountOfGold -= (building.getInitialGold() * difficultyMultiplier);
                    amountOfWood -= (building.getInitialWood() * difficultyMultiplier);
                    amountOfStone -= (building.getInitialStone() * difficultyMultiplier);
                    amountOfFood -= (building.getInitialFood() * difficultyMultiplier);
                    amountOfHuman -= (building.getInitialHuman() * difficultyMultiplier);
                    amountOfIron -= (building.getInitialIron() * difficultyMultiplier);
                    amountOfCoal -= (building.getInitialCoal() * difficultyMultiplier);
                    amountOfSteel -= (building.getInitialSteel() * difficultyMultiplier);

                    building.setAmount(1);
                    refreshEventLogText("A " + buttonText + " was successfully built.");
                }
                resourceAmountCheck = false;
            }
        }
    }

    private void resourceCapHandler() {
        if (amountOfGold > resourceCap) {
            amountOfGold = resourceCap;
        }
        if (amountOfWood > resourceCap) {
            amountOfWood = resourceCap;
        }
        if (amountOfStone > resourceCap) {
            amountOfStone = resourceCap;
        }
        if (amountOfFood > resourceCap) {
            amountOfFood = resourceCap;
        }
        if (amountOfHuman > resourceCap) {
            amountOfHuman = resourceCap;
        }
        if (amountOfIron > resourceCap) {
            amountOfIron = resourceCap;
        }
        if (amountOfCoal > resourceCap) {
            amountOfCoal = resourceCap;
        }
        if (amountOfSteel > resourceCap) {
            amountOfSteel = resourceCap;
        }
        if (amountOfGold < 0) {
            amountOfGold = 0;
        }
        if (amountOfWood < 0) {
            amountOfWood = 0;
        }
        if (amountOfStone < 0) {
            amountOfStone = 0;
        }
        if (amountOfFood < 0) {
            amountOfFood = 0;
        }
        if (amountOfHuman < 0) {
            amountOfHuman = 0;
        }
        if (amountOfIron < 0) {
            amountOfIron = 0;
        }
        if (amountOfCoal < 0) {
            amountOfCoal = 0;
        }
        if (amountOfSteel < 0) {
            amountOfSteel = 0;
        }
    }

    private void onNewGame() {

        currentTurn++;
        currentTurnLabel.setText("Current turn: " + currentTurn);

        refreshResources();
        activeUserLabel.setText(" " + DataStorage.getInstance().getNewActiveUser());
    }

    private void generateEvent() {

        Random random = new Random();
        int randomNum = random.nextInt(500);
        System.out.println(randomNum);

        if (EventHandler.getInstance().getEventDuration() < 1) {
            EventHandler.getInstance().setEventIsActive(false);
            EventStorage.getInstance().resetTimedEvents();
        }
        EventHandler.getInstance().calculateEvent(randomNum);
        System.out.println(EventStorage.getInstance().getEventText());

        if (EventStorage.getInstance().getEventText() != null) {
            refreshEventLogText(EventStorage.getInstance().getEventText());
        }

        popUpText.setText(EventStorage.getInstance().getEventText());
        if (EventStorage.getInstance().isEventActive() == true) {
            popUp.setDisable(false);
            popUpText.setDisable(false);
            popUp.setOpacity(1);
        } else {
            popUp.setOpacity(0);
            popUp.setDisable(true);
            popUpText.setDisable(true);
        }
        EventStorage.getInstance().setEventText(null);
        EventStorage.getInstance().setEventActive(false);
    }

    private void resourceAdder() {
        resourcesTableview.setItems(resourceList);
        resourceNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        resourceByTurnColumn.setCellValueFactory(cellData -> cellData.getValue().getByTurn());

        resourceList.add(new Resource("Gold", 0));
        resourceList.add(new Resource("Wood", 0));
        resourceList.add(new Resource("stone", 0));
        resourceList.add(new Resource("Food", 0));
        resourceList.add(new Resource("Human", 0));
        resourceList.add(new Resource("Iron", 0));
        resourceList.add(new Resource("Coal", 0));
        resourceList.add(new Resource("Steel", 0));
    }

    private void buildingAdder() throws SQLException {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Integer> scoreList = new ArrayList<>();
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

        scoreList.add(5); //Score house
        scoreList.add(10); //Score farm 
        scoreList.add(13); //Score market
        scoreList.add(8); //Score woodmill
        scoreList.add(26); //Score steelworks
        scoreList.add(36); //Score storage
        scoreList.add(9); //Score stonemason
        scoreList.add(16); //Score iron mine
        scoreList.add(20); //Score coal mine
        scoreList.add(13); //Score cottage
        scoreList.add(21); //Score granery
        scoreList.add(19); //Score stoneworks
        scoreList.add(28); //Score bazaar
        scoreList.add(18); //Score sawmill
        scoreList.add(40); //Score bank
        scoreList.add(43); //Score lumberjack school
        scoreList.add(52); //Score school
        scoreList.add(63); //Score aqueduct
        scoreList.add(71); //Score workshop

        buildingsTableview.setItems(normalBuildingList);
        buildingNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        buildingAmountColumn.setCellValueFactory(cellData -> cellData.getValue().getAmount());

        for (int i = 0; i < nameList.size(); i++) {

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

            try {

                String initialGold = connector.getRescourseCommand("Initial_Cost", nameList.get(i), "Gold");
                String initialWood = connector.getRescourseCommand("Initial_Cost", nameList.get(i), "Wood");
                String initialStone = connector.getRescourseCommand("Initial_Cost", nameList.get(i), "Stone");
                String initialIron = connector.getRescourseCommand("Initial_Cost", nameList.get(i), "Iron");
                String initialCoal = connector.getRescourseCommand("Initial_Cost", nameList.get(i), "Coal");
                String initialSteel = connector.getRescourseCommand("Initial_Cost", nameList.get(i), "Steel");
                String initialFood = connector.getRescourseCommand("Initial_Cost", nameList.get(i), "Food");
                String initialHuman = connector.getRescourseCommand("Initial_Cost", nameList.get(i), "Human");
                String gold = connector.getRescourseCommand("Turn_Resource_Gain", nameList.get(i), "Gold");
                String wood = connector.getRescourseCommand("Turn_Resource_Gain", nameList.get(i), "Wood");
                String stone = connector.getRescourseCommand("Turn_Resource_Gain", nameList.get(i), "Stone");
                String iron = connector.getRescourseCommand("Turn_Resource_Gain", nameList.get(i), "Iron");
                String coal = connector.getRescourseCommand("Turn_Resource_Gain", nameList.get(i), "Coal");
                String steel = connector.getRescourseCommand("Turn_Resource_Gain", nameList.get(i), "Steel");
                String food = connector.getRescourseCommand("Turn_Resource_Gain", nameList.get(i), "Food");
                String human = connector.getRescourseCommand("Turn_Resource_Gain", nameList.get(i), "Human");

                if (connector.getResult(initialGold).next()) {
                    initialGoldint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(initialWood).next()) {
                    initialWoodint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(initialStone).next()) {
                    initialStoneint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(initialIron).next()) {
                    initialIronint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(initialCoal).next()) {
                    initialCoalint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(initialSteel).next()) {
                    initialSteelint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(initialFood).next()) {
                    initialFoodint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(initialHuman).next()) {
                    initialHumanint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(gold).next()) {
                    goldint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(wood).next()) {
                    woodint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(stone).next()) {
                    stoneint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(iron).next()) {
                    ironint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(coal).next()) {
                    coalint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(steel).next()) {
                    steelint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(food).next()) {
                    foodint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                if (connector.getResult(human).next()) {
                    humanint = connector.getResultSet().getInt(1);
                    connector.close();
                }

                System.out.println(changeBuildingTypeChecker);
                if (changeBuildingTypeChecker < 14) {
                    normalBuildingList.add(new NormalBuilding(nameList.get(i), initialGoldint, initialWoodint, initialStoneint, initialIronint, initialCoalint,
                            initialSteelint, initialFoodint, initialHumanint, amount, scoreList.get(i), goldint, woodint, stoneint, ironint, coalint, steelint,
                            foodint, humanint));
                    changeBuildingTypeChecker++;
                } else {
                    uniqueBuildingList.add(new UniqueBuilding(nameList.get(i), initialGoldint, initialWoodint, initialStoneint, initialIronint, initialCoalint,
                            initialSteelint, initialFoodint, initialHumanint, amount, scoreList.get(i), goldint, woodint, stoneint, ironint, coalint, steelint,
                            foodint, humanint));
                }

            } catch (Exception ex) {
                System.out.println("Error with mySQL search");
                ex.printStackTrace();
            } finally {
                connector.close();
            }

        }
        System.out.println(normalBuildingList.get(2).getGold());

    }

    private void addEventResources() {

        amountOfGold += EventStorage.getInstance().getEventChangeGold();
        amountOfWood += EventStorage.getInstance().getEventChangeWood();
        amountOfStone += EventStorage.getInstance().getEventChangeStone();
        amountOfFood += EventStorage.getInstance().getEventChangeFood();
        amountOfHuman += EventStorage.getInstance().getEventChangeHuman();
        amountOfIron += EventStorage.getInstance().getEventChangeIron();
        amountOfCoal += EventStorage.getInstance().getEventChangeCoal();
        amountOfSteel += EventStorage.getInstance().getEventChangeSteel();
    }

    private void addEventPercentageResources() {
        amountOfGold = (int) (amountOfGold * EventStorage.getInstance().getEventPercentageChangeGold());
        amountOfWood = (int) (amountOfWood * EventStorage.getInstance().getEventPercentageChangeWood());
        amountOfStone = (int) (amountOfStone * EventStorage.getInstance().getEventPercentageChangeStone());
        amountOfFood = (int) (amountOfFood * EventStorage.getInstance().getEventPercentageChangeFood());
        amountOfHuman = (int) (amountOfHuman * EventStorage.getInstance().getEventPercentageChangeHuman());
        amountOfIron = (int) (amountOfIron * EventStorage.getInstance().getEventPercentageChangeIron());
        amountOfCoal = (int) (amountOfCoal * EventStorage.getInstance().getEventPercentageChangeCoal());
        amountOfSteel = (int) (amountOfSteel * EventStorage.getInstance().getEventPercentageChangeSteel());
    }

    private void upgradeBuildingButtonsHandler() {
        if (normalBuildingList.get(0).getAmount().getValue() >= 15) {
            cottageButton.setDisable(false);
            cottageButton.setOpacity(1);
        }
        if (normalBuildingList.get(1).getAmount().getValue() >= 7) {
            graneryButton.setDisable(false);
            graneryButton.setOpacity(1);
        }
        if (normalBuildingList.get(2).getAmount().getValue() >= 8) {
            bazaarButton.setDisable(false);
            bazaarButton.setOpacity(1);
        }
        if (normalBuildingList.get(3).getAmount().getValue() >= 13) {
            sawmillButton.setDisable(false);
            sawmillButton.setOpacity(1);
        }
        if (normalBuildingList.get(6).getAmount().getValue() >= 10) {
            stoneworksButton.setDisable(false);
            stoneworksButton.setOpacity(1);
        }
        if (uniqueBuildingList.get(0).getAmount().getValue() > 0) {
            bankButton.setDisable(true);
            bankButton.setOpacity(0.3);
        }
        if (uniqueBuildingList.get(1).getAmount().getValue() > 0) {
            lumberjackButton.setDisable(true);
            lumberjackButton.setOpacity(0.3);
        }
        if (uniqueBuildingList.get(2).getAmount().getValue() > 0) {
            schoolButton.setDisable(true);
            schoolButton.setOpacity(0.3);
        }
        if (uniqueBuildingList.get(3).getAmount().getValue() > 0) {
            aqueductButton.setDisable(true);
            aqueductButton.setOpacity(0.3);
        }
        if (uniqueBuildingList.get(4).getAmount().getValue() > 0) {
            workshopButton.setDisable(true);
            workshopButton.setOpacity(0.3);
        }
    }

    private double buildingMultiplier(){
        double multiplier = 1;
        for (NormalBuilding building : normalBuildingList){
            if(building.getName().getValue().equals(statsViewBuildingName)){
                for(int i = 0; i < building.getAmount().getValue(); i++){
                    multiplier *= 1.1;
                }
            }
        }
        return multiplier;
    }
    
    private void refreshStatsViewColumns(boolean isNormalBuilding, int statsViewBuildingID) {
        statsViewDisplayList.removeAll(statsViewDisplayList);
        statsViewBuildingLabel.setText(statsViewBuildingName);

        if (isNormalBuilding == true) {
            statsViewDisplayList.add(new StatsViewDisplayObject("Gold:", Integer.toString((int) (normalBuildingList.get(statsViewBuildingID).getInitialGold() * buildingMultiplier())), Integer.toString(normalBuildingList.get(statsViewBuildingID).getGold()), "", ""));
            statsViewDisplayList.add(new StatsViewDisplayObject("Wood:", Integer.toString((int) (normalBuildingList.get(statsViewBuildingID).getInitialWood() * buildingMultiplier())), Integer.toString(normalBuildingList.get(statsViewBuildingID).getWood()), "", ""));
            statsViewDisplayList.add(new StatsViewDisplayObject("Stone:", Integer.toString((int) (normalBuildingList.get(statsViewBuildingID).getInitialStone() * buildingMultiplier())), Integer.toString(normalBuildingList.get(statsViewBuildingID).getStone()), "", ""));
            statsViewDisplayList.add(new StatsViewDisplayObject("Iron:", Integer.toString((int) (normalBuildingList.get(statsViewBuildingID).getInitialIron() * buildingMultiplier())), Integer.toString(normalBuildingList.get(statsViewBuildingID).getIron()), "", ""));
            statsViewDisplayList.add(new StatsViewDisplayObject("Coal:", Integer.toString((int) (normalBuildingList.get(statsViewBuildingID).getInitialCoal() * buildingMultiplier())), Integer.toString(normalBuildingList.get(statsViewBuildingID).getCoal()), "", ""));
            statsViewDisplayList.add(new StatsViewDisplayObject("Steel:", Integer.toString((int) (normalBuildingList.get(statsViewBuildingID).getInitialSteel() * buildingMultiplier())), Integer.toString(normalBuildingList.get(statsViewBuildingID).getSteel()), "", ""));
            statsViewDisplayList.add(new StatsViewDisplayObject("Food:", Integer.toString((int) (normalBuildingList.get(statsViewBuildingID).getInitialFood() * buildingMultiplier())), Integer.toString(normalBuildingList.get(statsViewBuildingID).getFood()), "", ""));
            statsViewDisplayList.add(new StatsViewDisplayObject("Human:", Integer.toString((int) (normalBuildingList.get(statsViewBuildingID).getInitialHuman())), Integer.toString(normalBuildingList.get(statsViewBuildingID).getHuman()), "", ""));

            statsViewResource.setCellValueFactory(new PropertyValueFactory<>("resourceName"));
            statsViewCost.setCellValueFactory(new PropertyValueFactory<>("normalBuildingCost"));
            statsViewProduces.setCellValueFactory(new PropertyValueFactory<>("normalBuildingProduces"));

            statviewTableview.setItems(statsViewDisplayList);

        } else if (isNormalBuilding == false) {
            statsViewDisplayList.add(new StatsViewDisplayObject("Gold:", "", "", Integer.toString(uniqueBuildingList.get(statsViewBuildingID).getInitialGold()), Integer.toString((int) (100 * uniqueBuildingList.get(statsViewBuildingID).getBonusGold())) + "%"));
            statsViewDisplayList.add(new StatsViewDisplayObject("Wood:", "", "", Integer.toString(uniqueBuildingList.get(statsViewBuildingID).getInitialWood()), Integer.toString((int) (100 * uniqueBuildingList.get(statsViewBuildingID).getBonusWood())) + "%"));
            statsViewDisplayList.add(new StatsViewDisplayObject("Stone:", "", "", Integer.toString(uniqueBuildingList.get(statsViewBuildingID).getInitialStone()), Integer.toString((int) (100 * uniqueBuildingList.get(statsViewBuildingID).getBonusStone())) + "%"));
            statsViewDisplayList.add(new StatsViewDisplayObject("Iron:", "", "", Integer.toString(uniqueBuildingList.get(statsViewBuildingID).getInitialIron()), Integer.toString((int) (100 * uniqueBuildingList.get(statsViewBuildingID).getBonusIron())) + "%"));
            statsViewDisplayList.add(new StatsViewDisplayObject("Coal:", "", "", Integer.toString(uniqueBuildingList.get(statsViewBuildingID).getInitialCoal()), Integer.toString((int) (100 * uniqueBuildingList.get(statsViewBuildingID).getBonusCoal())) + "%"));
            statsViewDisplayList.add(new StatsViewDisplayObject("Steel:", "", "", Integer.toString(uniqueBuildingList.get(statsViewBuildingID).getInitialSteel()), Integer.toString((int) (100 * uniqueBuildingList.get(statsViewBuildingID).getBonusSteel())) + "%"));
            statsViewDisplayList.add(new StatsViewDisplayObject("Food:", "", "", Integer.toString(uniqueBuildingList.get(statsViewBuildingID).getInitialFood()), Integer.toString((int) (100 * uniqueBuildingList.get(statsViewBuildingID).getBonusFood())) + "%"));
            statsViewDisplayList.add(new StatsViewDisplayObject("Human:", "", "", Integer.toString(uniqueBuildingList.get(statsViewBuildingID).getInitialHuman()), Integer.toString((int) (100 * uniqueBuildingList.get(statsViewBuildingID).getBonusHuman())) + "%"));

            statsViewResource.setCellValueFactory(new PropertyValueFactory<>("resourceName"));
            statsViewCost.setCellValueFactory(new PropertyValueFactory<>("uniqueBuildingCost"));
            statsViewProduces.setCellValueFactory(new PropertyValueFactory<>("uniqueBuildingProduces"));

            statviewTableview.setItems(statsViewDisplayList);
        }
    }

    private void difficultySetter() {

        if (DataStorage.getInstance().getDifficulty().equals("noob")) {
            difficultyMultiplier = 0.8;
        } else if (DataStorage.getInstance().getDifficulty().equals("normal")) {
            difficultyMultiplier = 1;
        } else if (DataStorage.getInstance().getDifficulty().equals("asian")) {
            difficultyMultiplier = 1.2;
        }
    }

    private void refreshEventLogText(String eventLogNewText) {
        if (eventLogDisplayText == null) {
            eventLogDisplayText = eventLogNewText;
            eventlogTextArea.setText(eventLogDisplayText);
        } else {
            eventLogDisplayText = eventLogNewText + "\n" + eventLogDisplayText;
            eventlogTextArea.setText(eventLogDisplayText);

        }
    }

    private int scoreCalculator() {
        int score = 0;
        for (int i = 0; i < 14; i++) {

            score += (normalBuildingList.get(i).getAmount().getValue() * normalBuildingList.get(i).getScoreValue());
            if (i < 5) {
                score += (uniqueBuildingList.get(i).getAmount().getValue() * uniqueBuildingList.get(i).getScoreValue());
            }

        }
        if(currentTurn == DataStorage.getInstance().getRoundLimit()){
            score += (((amountOfGold*2)+ amountOfWood + amountOfStone + amountOfFood + (amountOfHuman*10) + (amountOfIron*2) + (amountOfCoal*3) + (amountOfSteel*4)/15));
        }
        if (DataStorage.getInstance().getDifficulty().equals("noob")) {
            score *= 0.8;
        } else if (DataStorage.getInstance().getDifficulty().equals("asian")) {
            score *= 1.2;
        }
        
        scoreLabel.setText("Score: " + score);
        DataStorage.getInstance().setScore(score);
        System.out.println("The score" + score);

        return score;
    }

    private void insertHighScore() {
        int leaderBoardObjectCounter = 0;
        try {
            Statement sta = connector.getConnection().createStatement();
            ResultSet res = sta.executeQuery(connector.getOneAttributeCommand("ID","leaderboard"));
            while (res.next()) {
                leaderBoardObjectCounter++;
            }
            System.out.println(leaderBoardObjectCounter);
            PreparedStatement prepSt = connector.getConnection().prepareStatement(connector.getInsertHighScoreCommand());
            prepSt.setInt(1, leaderBoardObjectCounter + 1);
            prepSt.setString(2, DataStorage.getInstance().getNewActiveUser());
            prepSt.setInt(3, scoreCalculator());
            prepSt.setString(4, DataStorage.getInstance().getDifficulty());
            prepSt.setInt(5, DataStorage.getInstance().getRoundLimit());
            prepSt.executeUpdate();
            connector.close();
            System.out.println("Highscore lagrat");
        } catch (Exception ex) {
            System.out.println("Error vid sparning av highscore");
            Logger.getLogger(FXMLLeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void hideCheatCodeTools() {
        cheatCodeLabel.setText("");
        cheatCodeField.setVisible(false);
        cheatCodesOkButton.setVisible(false);
        cheatCodeErrorLabel.setVisible(false);
    }

    private void codeSuccessfull() {
        hideCheatCodeTools();
        cheatCodeField.setText("");
        cheatCodeLabel.setText("Success!");
        cheatCodeLabel.setLayoutX(78);
        cheatCodeLabel.setLayoutY(295);
    }
}

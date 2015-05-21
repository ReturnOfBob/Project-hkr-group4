/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Henrik
 */
public class FXMLLeaderboardController implements Initializable {

    private final DataBaseConnector connector = new DataBaseConnector();

    @FXML
    private TableColumn<LeaderboardObject, String> nameColumn;
    @FXML
    private TableColumn<LeaderboardObject, Number> scoreColumn;
    @FXML
    private TableColumn<LeaderboardObject, String> difficultyColumn;
    @FXML
    private TableView<LeaderboardObject> leaderboardList;

    final private ObservableList<LeaderboardObject> leaderboardListd = FXCollections.observableArrayList();

    private String buttonText;

    ResultSet res;

    /**
     * Initializes the controller class.
     */
    /*public int calculateRescourseScore(int gold, int wood, int stone, int food, int human, int iron, int coal, int steel) {
     int rescourseScore = gold * 2 + wood * 2 + stone * 2 + +food * 2 + human * 3 + iron * 2 + coal * 2 + steel * 2;
     return rescourseScore;
     }

     public int calculateBuildingAmountScore(int bankAmount, int coalMineAmount, int cottageAmmount,
     int farmAmmount, int houseAmmount, int ironMineAmmount, int marketAmmount, int steelWorksAmmount, int stoneMasonAmmount, int storageAmmount, int woodMillAmmount) {
     int buildingAmountScore = bankAmount * 4 + coalMineAmount * 4 + cottageAmmount * 4 + farmAmmount * 4 + houseAmmount * 4 + ironMineAmmount * 4 + marketAmmount * 4 + steelWorksAmmount * 4 + stoneMasonAmmount * 4 + storageAmmount * 4 + woodMillAmmount * 4;
     return buildingAmountScore;
     }

     public int calculateFinalScore(int rescourseScore, int buildingAmountScore, int technologyScore, int ArmyScore) {
     int finalScore = rescourseScore + buildingAmountScore + technologyScore + ArmyScore;
     return finalScore;
     }

     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setLeaderBoard(20);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLLeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML //This method handles all of the clicks in the menu clicks in this scene
    private void buttonClicks(ActionEvent event) throws SQLException {
        buttonText = ((Button) event.getSource()).getText();

        if (buttonText.equals("To main menu")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLMainMenu.fxml");
        } else if (buttonText.equals("Delete selected row")) {
            changeLeaderboard();
        } else if (buttonText.equals("20 rounds")) {
            setLeaderBoard(20);
            System.out.println("20 button");
        } else if (buttonText.equals("50 rounds")) {
            setLeaderBoard(50);
            System.out.println("50 button");
        } else if (buttonText.equals("100 rounds")) {
            setLeaderBoard(100);
            System.out.println("100 button");
        } else {
            System.out.println("ERROR!");
        }
    }

    private void changeLeaderboard() {
        //if ("Admin".equals(DataStorage.getInstance().getNewActiveUser())) { //Bortkommenterad så länge
        //  LeaderboardObject markedRow = (LeaderboardObject)delete.

        try {
            LeaderboardObject markedRow = leaderboardList.getSelectionModel().getSelectedItem();
            System.out.println(markedRow.getName() + markedRow.getScore()+ markedRow.getDifficulty());
            connector.getDelete(connector.getDeleteFromTableCommand(markedRow.getName(), markedRow.getScore(),markedRow.getDifficulty())).execute();
            System.out.println("Deleted");
            leaderboardListd.remove(leaderboardList.getSelectionModel().getSelectedItem());
            // leaderboardListd.removeAll(leaderboardListd);
            System.out.println("Test");
                //setLeaderBoard();
            connector.close();
            //else System.oout.println("Require admin right");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLLeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setLeaderBoard(int roundChoice) throws SQLException {
        leaderboardListd.removeAll(leaderboardListd);
        //Det funkar inte av någon anleding om man använder preparedstatement eller vanligt statement från databaseconnector. den skriver bara ut samma massa gånger. skriver man kodskiten här sp fungerar det konstigt nog.
        /* String test = "SELECT Username,Score FROM leaderboard ORDER BY Score DESC LIMIT 5";
         //connector.getConnection();
     
        
         while (connector.getResult(connector.getGenericHighScoreCommand()).next()){
         // leaderboardListd.add(connector.getResultSet().getInt("Score"),connector.getResultSet().getString("Username"));  
  
         System.out.println(connector.getResultSet().getInt("Score")+ connector.getResultSet().getString("Username"));
         }
         // leaderboardList.setItems(leaderboardListd);
         }
   
         */
        Statement sta = connector.getConnection().createStatement();

        if (roundChoice == 50) {
            res = sta.executeQuery(connector.getGenericHighScoreCommand(50));
        } else if (roundChoice == 100) {
            res = sta.executeQuery(connector.getGenericHighScoreCommand(100));
        } else {
            res = sta.executeQuery(connector.getGenericHighScoreCommand(20));
        }
      
        //connector.getConnection();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        leaderboardList.setItems(leaderboardListd);

        while (res.next()) {

            System.out.println(res.getInt("Score") + res.getString("Accounts_Username")+ res.getString("Difficulty"));
            LeaderboardObject leaderboardObject = new LeaderboardObject(res.getString("Accounts_Username"), res.getInt("Score"),  res.getString("Difficulty")); //Fast värden för svårighetsgrad så länge. Ska tas från fil eller från datastorage
            //DataStorage.getInstance().getDifficulty()
            leaderboardListd.add(leaderboardObject);

        }
        connector.close();
    }
    
}

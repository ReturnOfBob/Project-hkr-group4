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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLLeaderboardController implements Initializable {

//-----------------------------MYSQL CONNECTION-------------------------------\\
    
private final DataBaseConnector connector = new DataBaseConnector();
    

//-----------------------------------GUI--------------------------------------\\ 
    @FXML
    private TableColumn<LeaderboardObject, String> nameColumn;
    @FXML
    private TableColumn<LeaderboardObject, Number> scoreColumn;
    @FXML
    private TableColumn<LeaderboardObject, String> difficultyColumn;
    @FXML
    private TableView<LeaderboardObject> leaderboardTabelView;
    @FXML
    private Label errorLabel;

    final private ObservableList<LeaderboardObject> leaderboardList = FXCollections.observableArrayList();

//--------------------------------VARIABLES-----------------------------------\\
    
    private String buttonText;
    private ResultSet res;

//----------------------------ON SCENE LOADUP---------------------------------\\
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setLeaderBoard(20);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLLeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//------------------------------FXML METHODS----------------------------------\\

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

        try {
            if (connector.getResult(connector.getGenericCommand("Privilege", "accounts", "Username", DataStorage.getInstance().getNewActiveUser())).next()) {
                if (connector.getResultSet().getString(1).equals("Admin")) {
                    LeaderboardObject markedRow = leaderboardTabelView.getSelectionModel().getSelectedItem();
                    System.out.println(markedRow.getName() + markedRow.getScore() + markedRow.getDifficulty());
                    connector.getDelete(connector.getDeleteFromTableCommand(markedRow.getName(), markedRow.getScore(), markedRow.getDifficulty())).execute();
                    System.out.println("Deleted score");
                    leaderboardList.remove(leaderboardTabelView.getSelectionModel().getSelectedItem());
                    connector.close();

                } else {
                    System.out.println("Require admin rights");
                    errorLabel.setVisible(true);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLLeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//----------------------------NON-FXML METHODS--------------------------------\\
    
    private void setLeaderBoard(int roundChoice) throws SQLException {
        leaderboardList.removeAll(leaderboardList);

        Statement sta = connector.getConnection().createStatement();

        if (roundChoice == 50) {
            res = sta.executeQuery(connector.getGenericHighScoreCommand(50));
        } else if (roundChoice == 100) {
            res = sta.executeQuery(connector.getGenericHighScoreCommand(100));
        } else {
            res = sta.executeQuery(connector.getGenericHighScoreCommand(20));
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        leaderboardTabelView.setItems(leaderboardList);

        while (res.next()) {

            System.out.println(res.getInt("Score") + res.getString("Accounts_Username") + res.getString("Difficulty"));
            LeaderboardObject leaderboardObject = new LeaderboardObject(res.getString("Accounts_Username"), res.getInt("Score"), res.getString("Difficulty"));
            leaderboardList.add(leaderboardObject);
        }
        connector.close();
    }
}

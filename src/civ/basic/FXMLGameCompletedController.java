
package civ.basic;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class FXMLGameCompletedController implements Initializable {

    private final DataBaseConnector connector = new DataBaseConnector();
    
    private String buttonText;
    
    @FXML
    private Label congratulationsLabel, scoreLabel, highestScoreLabel,cheatCodeLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(DataStorage.getInstance().isHighestScoreCheck() == true){
            highestScoreLabel.setVisible(true);
        }
        if(DataStorage.getInstance().isLeaderboardCheatCodeBlock() == true){
            cheatCodeLabel.setVisible(true);
        }
        
        if(DataStorage.getInstance().getNewActiveUser().length() < 3){
            congratulationsLabel.setLayoutX(291);
        }
        else if(DataStorage.getInstance().getNewActiveUser().length() > 2 && DataStorage.getInstance().getNewActiveUser().length() < 5){
            congratulationsLabel.setLayoutX(277);
        }
        
        else if(DataStorage.getInstance().getNewActiveUser().length() > 4 && DataStorage.getInstance().getNewActiveUser().length() < 7){
            congratulationsLabel.setLayoutX(248);
        }
        
        else if(DataStorage.getInstance().getNewActiveUser().length() > 6 && DataStorage.getInstance().getNewActiveUser().length() < 9){
            congratulationsLabel.setLayoutX(216);
        }
        
        else if(DataStorage.getInstance().getNewActiveUser().length() > 8 && DataStorage.getInstance().getNewActiveUser().length() < 11){
            congratulationsLabel.setLayoutX(191);
        }
        else if(DataStorage.getInstance().getNewActiveUser().length() > 10 && DataStorage.getInstance().getNewActiveUser().length() < 13){
            congratulationsLabel.setLayoutX(159);
        }
        else if(DataStorage.getInstance().getNewActiveUser().length() > 12 && DataStorage.getInstance().getNewActiveUser().length() < 15){
            congratulationsLabel.setLayoutX(138);
        }
        else if(DataStorage.getInstance().getNewActiveUser().length() > 14 && DataStorage.getInstance().getNewActiveUser().length() < 17){
            congratulationsLabel.setLayoutX(104);
        }
        else if(DataStorage.getInstance().getNewActiveUser().length() > 16 && DataStorage.getInstance().getNewActiveUser().length() < 19){
            congratulationsLabel.setLayoutX(78);
        }
        else if(DataStorage.getInstance().getNewActiveUser().length() > 18 && DataStorage.getInstance().getNewActiveUser().length() < 21){
            congratulationsLabel.setLayoutX(46);
        }
        else{
            congratulationsLabel.setLayoutX(178);
        }
        
        
        
      //  if(DataStorage.getInstance().getScore() > connector.getHighestScoreFromLeaderBoard(DataStorage.getInstance().getRoundLimit())){
       //     highestScoreLabel.setVisible(true);
        //}
        
        
        congratulationsLabel.setText("Congratulations " + DataStorage.getInstance().getNewActiveUser() + "!");
        scoreLabel.setText("Your score: " + DataStorage.getInstance().getScore());
        
    }    
    
    @FXML
    private void menuClick(ActionEvent event){
        buttonText = ((Button) event.getSource()).getText();

        if (buttonText.equals("Back to main menu")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLMainMenu.fxml");
        } else if (buttonText.equals("Go to leaderboard")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLLeaderboard.fxml");
        }
        else {
            System.out.println("ERROR!");
        }
    }
    
    
    
}

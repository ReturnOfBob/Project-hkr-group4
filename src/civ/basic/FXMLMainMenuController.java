package civ.basic;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class FXMLMainMenuController implements Initializable {
//--------------------------------VARIABLES-----------------------------------\\    

    @FXML
    private TextArea informationArea;
    @FXML
    private AnchorPane helpAnchorpane;
    @FXML
    private Button backButton;
    @FXML
    private Label errorLabel;
    
    private String buttonText;

//---------------------------ON SCENE LOAD-UP---------------------------------\\    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try{
        if (DataStorage.getInstance().isLoadOnceCheck() == false) {
            readOptionSettingFromFile();

            if (DataStorage.getInstance().isMusicSet() == false) {
                SoundPlayer.getInstance().playMusic(DataStorage.getInstance().getMusicChoice());
            }
        }
        backToMenuFromHelp();
      }
        catch (Exception e) {
            System.out.println("ERROR: " + e);
            errorLabel.setText("Initialzine error");
        }

    }
//------------------------------FXML METHODS----------------------------------\\    

    @FXML //This method handles all of the clicks in the menu clicks in this scene
    private void menuClick(ActionEvent event) {
        buttonText = ((Button) event.getSource()).getText();

        if (buttonText.equals("New Game")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLGame.fxml");
        } else if (buttonText.equals("Leaderboard")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLLeaderboard.fxml");
        } else if (buttonText.equals("Options")) {
            DataStorage.getInstance().sceneSwitch(event, "FXMLOption.fxml");
            System.out.println("Options");
        } else if (buttonText.equals("Log Out")) {
            //Temporary sceneswitch back to login
            DataStorage.getInstance().sceneSwitch(event, "FXMLLogInMenu.fxml");
            DataStorage.getInstance().setNewActiveUser("");
            SoundPlayer.getInstance().setMusicStop();
            System.out.println(DataStorage.getInstance().getNewActiveUser());
        } else if (buttonText.equals("Exit")) {
            try {
                System.exit(0);
            } catch (Exception ex) {
                System.out.println("ERROR EXIT!");
            }
        } else if (buttonText.equals("Help")) {
            helpViewer();
        } else if (buttonText.equals("Back")) {
            backToMenuFromHelp();
        } else {
            System.out.println("ERROR!");
        }
    }

    private void readOptionSettingFromFile() {
        OptionObject objectload = new OptionObject();
        try (ObjectInputStream oIn = new ObjectInputStream(new FileInputStream("./AccountSettings/" + DataStorage.getInstance().getNewActiveUser() + ".txt"))) {
            objectload = (OptionObject) oIn.readObject();
            System.out.println("Filöppning lyckades");
            DataStorage.getInstance().setDifficulty(objectload.getDifficulty());
            DataStorage.getInstance().setMusicChoice(objectload.getMusicChoice());
            DataStorage.getInstance().setRoundLimit(objectload.getRoundLimit());
            DataStorage.getInstance().setMusicSet(objectload.isMusicSet());
            DataStorage.getInstance().setLoadOnceCheck(true);
        } catch (Exception ex) {
            System.out.println("No file created");

        }
    }

    private void helpViewer() {
        helpAnchorpane.setVisible(true);
        informationArea.setVisible(true);
        informationArea.setText("This game is about building a civi\n"
                + "-lization and being the best at it.\n"
                + "You control the game with the mouse \n"
                + "and in order to build new buildings \n"
                + "you press a button which has a\n"
                + "name of a building. \n\n"
                + "If you have forgotten your\n"
                + "password, please return to log-in\n"
                + "menu and click lost password button\n");
        backButton.setVisible(true);
    }

    private void backToMenuFromHelp() {
        helpAnchorpane.setVisible(false);
        informationArea.setVisible(false);
        backButton.setVisible(false);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nicklas
 */
public class DataStorage {
//------------------------------VARIABLES-------------------------------------\\   
    private static DataStorage dataStorage;
    private String newSceneIs;
    private String newActiveUser;
    private String difficulty;
    private int RoundLimit;
//------------------------------CONSTRUCTOR-----------------------------------\\    
    private DataStorage(){
    
    }
//---------------------GETS INFORMATION FROM DATASTORAGE----------------------\\    
    public static DataStorage getInstance(){
        if(dataStorage == null){
            dataStorage = new DataStorage();
        }
        
        return dataStorage;
    }
//--------------------------------METHODS-------------------------------------\\    
    //This method is used everywhere in the program switch between different scenes
    public void sceneSwitch(ActionEvent event, String newSceneIs){
        
        this.newSceneIs = newSceneIs;
        
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(newSceneIs));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (IOException ex) {
            System.out.println("ERROR SCENESWITCH!");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
//-------------------------GETTERS AND SETTERS--------------------------------\\    
    public String getNewSceneIs(){
        return newSceneIs;
    }
      public void setNewActiveUser(String newActiveUser) {
        this.newActiveUser = newActiveUser;
    }

    public String getNewActiveUser() {
        return newActiveUser;
    }
       public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }
       public void setRoundLimit(Integer RoundLimit) {
        this.RoundLimit = RoundLimit;
    }

    public Integer getRoundLimit() {
        return RoundLimit;
    }
    
}

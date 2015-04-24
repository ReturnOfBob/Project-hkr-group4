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
//------------------------------CONSTRUCTOR-----------------------------------\\    
    private DataStorage(){
    
    }
//---------------------GETS INFORMATION FROM DATASTORAGE---------------------\\    
    public static DataStorage getInstance(){
        if(dataStorage == null){
            dataStorage = new DataStorage();
        }
        
        return dataStorage;
    }
//--------------------------------METHODS-------------------------------------\\    
    //This method is used everywhere in the program switch between different scenes
    public void sceneSwitch(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(newSceneIs));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println("ERROR SCENESWITCH!");
            ex.printStackTrace();
        }
    }
//-------------------------GETTERS AND SETTERS--------------------------------\\    
    public void setNewSceneIs(String newSceneIs){
        this.newSceneIs = newSceneIs;
    }
    
    public String getNewSceneIs(){
        return newSceneIs;
    }
    
}
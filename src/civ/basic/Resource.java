/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package civ.basic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Sweetpink
 */
public class Resource {
    
    private StringProperty name;
    private IntegerProperty byTurn;
    
    public Resource(String name, int byTurn){
        this.name = new SimpleStringProperty(name);
        this.byTurn = new SimpleIntegerProperty(byTurn);
    }
    
    public StringProperty getName(){
        return name;
    }
    public IntegerProperty getByTurn(){
        return byTurn;
    }
    public void setByTurn(int byTurn){
        this.byTurn.set(byTurn);
    }
    
}

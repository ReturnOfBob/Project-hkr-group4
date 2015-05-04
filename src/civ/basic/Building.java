package civ.basic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Henrik
 */
public abstract class Building {
//------------------------------VARIABLES-------------------------------------\\ 
    private StringProperty name;
    private int initialGold;
    private int initialWood;
    private int initialStone;
    private int initialIron;
    private int initialCoal;
    private int initialSteel;
    private int initialFood;
    private int initialHuman;
    private IntegerProperty amount;
//------------------------------CONSTRUCTOR-----------------------------------\\
    public Building(String name, int initialGold, int initialWood, int initialStone, int initialIron, int initialCoal, 
            int initialSteel, int initialFood, int initialHuman, int amount) {
        this.name = new SimpleStringProperty(name);
        this.initialGold = initialGold;
        this.initialWood = initialWood;
        this.initialStone = initialStone;
        this.initialIron = initialIron;
        this.initialCoal = initialCoal;
        this.initialSteel = initialSteel;
        this.initialFood = initialFood;
        this.initialHuman = initialHuman;
        this.amount = new SimpleIntegerProperty(amount);
    }
    
    public void setAmount(int amount){
        this.amount = new SimpleIntegerProperty(amount);
    }
    public IntegerProperty getAmount(){
        return amount;
    }
    public StringProperty getName(){
        return name;
    }
}

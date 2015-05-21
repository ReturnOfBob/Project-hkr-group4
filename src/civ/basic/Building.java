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
    private int initialSoldier;
    private IntegerProperty amount;
    private int scoreValue;
//------------------------------CONSTRUCTOR-----------------------------------\\
    public Building(String name, int initialGold, int initialWood, int initialStone, int initialIron, int initialCoal,  int initialSoldier, 
            int initialSteel, int initialFood, int initialHuman, int amount, int scoreValue) {
        this.name = new SimpleStringProperty(name);
        this.initialGold = initialGold;
        this.initialWood = initialWood;
        this.initialStone = initialStone;
        this.initialIron = initialIron;
        this.initialCoal = initialCoal;
        this.initialSteel = initialSteel;
        this.initialFood = initialFood;
        this.initialHuman = initialHuman;
        this.initialSoldier = initialSoldier;
        this.amount = new SimpleIntegerProperty(amount);
        this.scoreValue = scoreValue;
    }
    
    public void setAmount(int add){
        int amount = add + this.amount.getValue();
        this.amount.set(amount);
    }
    public IntegerProperty getAmount(){
        return amount;
    }
    public StringProperty getName(){
        return name;
    }
    public int getInitialGold(){
        return initialGold;
    }
    public int getInitialWood(){
        return initialWood;
    }
    public int getInitialStone(){
        return initialStone;
    }
    public int getInitialFood(){
        return initialFood;
    }
    public int getInitialHuman(){
        return initialHuman;
    }
    public int getInitialIron(){
        return initialIron;
    }
    public int getInitialCoal(){
        return initialCoal;
    }
    public int getInitialSteel(){
        return initialSteel;
    }

    public int getInitialSoldier() {
        return initialSoldier;
    }
    public int getScoreValue(){
        return scoreValue;
    }
}

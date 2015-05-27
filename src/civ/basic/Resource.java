package civ.basic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Resource {

//--------------------------------VARIABLES-----------------------------------\\
    
    private final StringProperty name;
    private IntegerProperty byTurn;

//----------------------------NON-FXML METHODS--------------------------------\\
    
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
    public void setByTurn(int add){
        int byTurn = add + this.byTurn.getValue();
        this.byTurn.set(byTurn);
    }
    public void resetByTurn(){
        this.byTurn.set(0);
    }
}

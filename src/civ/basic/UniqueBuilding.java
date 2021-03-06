package civ.basic;

public class UniqueBuilding extends Building {
//--------------------------------VARIABLES-----------------------------------\\     

    private final double bonusGold;
    private final double bonusWood;
    private final double bonusStone;
    private final double bonusIron;
    private final double bonusCoal;
    private final double bonusSteel;
    private final double bonusFood;
    private final double bonusHuman;
//------------------------------CONSTRUCTOR-----------------------------------\\        

    public UniqueBuilding(String name, int initialGold, int initialWood, int initialStone, int initialIron, int initialCoal,
            int initialSteel, int initialFood, int initialHuman, int amount, int scoreValue, double bonusGold, double bonusWood, double bonusStone, double bonusIron,
            double bonusCoal, double bonusSteel, double bonusFood, double bonusHuman) {

        super(name, initialGold, initialWood, initialStone, initialIron, initialCoal, initialSteel, initialFood, initialHuman, amount, scoreValue);
        this.bonusGold = (bonusGold / 100);
        this.bonusWood = (bonusWood / 100);
        this.bonusStone = (bonusStone / 100);
        this.bonusIron = (bonusIron / 100);
        this.bonusCoal = (bonusCoal / 100);
        this.bonusSteel = (bonusSteel / 100);
        this.bonusFood = (bonusFood / 100);
        this.bonusHuman = (bonusHuman / 100);

    }

//----------------------------NON-FXML METHODS--------------------------------\\
    
    public double getBonusGold() {
        return bonusGold;
    }

    public double getBonusWood() {
        return bonusWood;
    }

    public double getBonusStone() {
        return bonusStone;
    }

    public double getBonusIron() {
        return bonusIron;
    }

    public double getBonusCoal() {
        return bonusCoal;
    }

    public double getBonusSteel() {
        return bonusSteel;
    }

    public double getBonusFood() {
        return bonusFood;
    }

    public double getBonusHuman() {
        return bonusHuman;
    }

}

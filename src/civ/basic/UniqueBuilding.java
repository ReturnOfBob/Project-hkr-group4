package civ.basic;

/**
 *
 * @author Henrik
 */
public class UniqueBuilding extends Building {
//--------------------------------VARIABLES-----------------------------------\\     
    private double bonusGold;
    private double bonusWood;
    private double bonusStone;
    private double bonusIron;
    private double bonusCoal;
    private double bonusSteel;
    private double bonusFood;
    private double bonusHuman;
//------------------------------CONSTRUCTOR-----------------------------------\\        
    public UniqueBuilding(double initialGold, double initialWood, double initialStone, double initialIron, double initialCoal,
            double initialSteel, double initialFood, double initialHuman, double bonusGold, double bonusWood, double bonusStone, double bonusIron,
            double bonusCoal, double bonusSteel, double bonusFood, double bonusHuman) {
        
        super(initialGold, initialWood, initialStone, initialIron, initialCoal, initialSteel, initialFood, initialHuman);
        this.bonusGold = bonusGold;
        this.bonusWood = bonusWood;
        this.bonusStone = bonusStone;
        this.bonusIron = bonusIron;
        this.bonusCoal = bonusCoal;
        this.bonusSteel = bonusSteel;
        this.bonusFood = bonusFood;
        this.bonusHuman = bonusHuman;
    }

}

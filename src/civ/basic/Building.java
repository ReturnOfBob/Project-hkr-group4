package civ.basic;

/**
 *
 * @author Henrik
 */
public abstract class Building {
//------------------------------VARIABLES-------------------------------------\\ 
    private double initialGold;
    private double initialWood;
    private double initialStone;
    private double initialIron;
    private double initialCoal;
    private double initialSteel;
    private double initialFood;
    private double initialHuman;
//------------------------------CONSTRUCTOR-----------------------------------\\
    public Building(double initialGold, double initialWood, double initialStone, double initialIron, double initialCoal, 
            double initialSteel, double initialFood, double initialHuman) {
        this.initialGold = initialGold;
        this.initialWood = initialWood;
        this.initialStone = initialStone;
        this.initialIron = initialIron;
        this.initialCoal = initialCoal;
        this.initialSteel = initialSteel;
        this.initialFood = initialFood;
        this.initialHuman = initialHuman;
    }
}

package civ.basic;

/**
 *
 * @author Henrik
 */
public class UniqueBuilding extends Building {
//--------------------------------VARIABLES-----------------------------------\\     
    private int bonusGold;
    private int bonusWood;
    private int bonusStone;
    private int bonusIron;
    private int bonusCoal;
    private int bonusSteel;
    private int bonusFood;
    private int bonusHuman;
//------------------------------CONSTRUCTOR-----------------------------------\\        
    public UniqueBuilding(String name, int initialGold, int initialWood, int initialStone, int initialIron, int initialCoal,
            int initialSteel, int initialFood, int initialHuman,int amount, int bonusGold, int bonusWood, int bonusStone, int bonusIron,
            int bonusCoal, int bonusSteel, int bonusFood, int bonusHuman) {
        
        super(name, initialGold, initialWood, initialStone, initialIron, initialCoal, initialSteel, initialFood, initialHuman, amount);
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

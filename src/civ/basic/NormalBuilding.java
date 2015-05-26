package civ.basic;

public class NormalBuilding extends Building {
//--------------------------------VARIABLES-----------------------------------\\ 

    private final int gold;
    private final int wood;
    private final int stone;
    private final int iron;
    private final int coal;
    private final int steel;
    private final int food;
    private final int human;
//------------------------------CONSTRUCTOR-----------------------------------\\        

    public NormalBuilding(String name, int initialGold, int initialWood, int initialStone, int initialIron, int initialCoal,
            int initialSteel, int initialFood, int initialHuman, int amount, int scoreValue, int gold,
            int wood, int stone, int iron, int coal, int steel, int food, int human) {

        super(name, initialGold, initialWood, initialStone, initialIron, initialCoal, initialSteel,
                initialFood, initialHuman, amount, scoreValue);
        this.gold = gold;
        this.wood = wood;
        this.stone = stone;
        this.iron = iron;
        this.coal = coal;
        this.steel = steel;
        this.food = food;
        this.human = human;
    }
//----------------------------NON-FXML METHODS--------------------------------\\
    public int getGold() {
        return gold;
    }

    public int getWood() {
        return wood;
    }

    public int getStone() {
        return stone;
    }

    public int getIron() {
        return iron;
    }

    public int getCoal() {
        return coal;
    }

    public int getSteel() {
        return steel;
    }

    public int getFood() {
        return food;
    }

    public int getHuman() {
        return human;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;

/**
 *
 * @author Henrik
 */
public class NormalBuilding extends Building {
//--------------------------------VARIABLES-----------------------------------\\ 
    private int gold;
    private int wood;
    private int stone;
    private int iron;
    private int coal;
    private int steel;
    private int food;
    private int human;
//------------------------------CONSTRUCTOR-----------------------------------\\        
    public NormalBuilding(String name, int initialGold, int initialWood, int initialStone, int initialIron, int initialCoal, 
            int initialSteel, int initialFood, int initialHuman, int amount, int gold, 
            int wood, int stone, int iron, int coal, int steel, int food, int human) {
        
        super(name, initialGold,initialWood,initialStone, initialIron, initialCoal, initialSteel, 
                initialFood, initialHuman, amount );
        this.gold = gold;
        this.wood = wood;
        this.stone = stone;
        this.iron = iron;
        this.coal = coal;
        this.steel = steel;
        this.food = food;
        this.human = human;
    }
    public int getGold(){
        return gold;
    }
    public int getWood(){
        return wood;
    }
    public int getStone(){
        return stone;
    }
    public int getIron(){
        return iron;
    }
    public int getCoal(){
        return coal;
    }
    public int getSteel(){
        return steel;
    }
    public int getFood(){
        return food;
    }
    public int getHuman(){
        return human;
    }

}

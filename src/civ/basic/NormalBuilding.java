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
    private double gold;
    private double wood;
    private double stone;
    private double iron;
    private double coal;
    private double steel;
    private double food;
    private double human;
//------------------------------CONSTRUCTOR-----------------------------------\\        
    public NormalBuilding(double initialGold, double initialGoldWood, double initialGoldStone, double initialGoldIron, double initialGoldCoal, 
            double initialGoldSteel, double initialGoldFood, double initialGoldHuman,double gold, 
            double wood, double stone, double iron, double coal, double steel, double food, double human) {
        
        super(initialGold,initialGoldWood,initialGoldStone, initialGoldIron, initialGoldCoal, initialGoldSteel, initialGoldFood, initialGoldHuman );
        this.gold = gold;
        this.wood = wood;
        this.stone = stone;
        this.iron = iron;
        this.coal = coal;
        this.steel = steel;
        this.food = food;
        this.human = human;
    }

}

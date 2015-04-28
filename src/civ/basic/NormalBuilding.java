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

    private double Gold;
    private double Wood;
    private double Stone;
    private double Iron;
    private double Coal;
    private double Steel;
    private double Food;
    private double Human;

    public NormalBuilding(double initialGold, double initialGoldWood, double initialGoldStone, double initialGoldIron, double initialGoldCoal, 
            double initialGoldSteel, double initialGoldFood, double initialGoldHuman,double Gold, 
            double Wood, double Stone, double Iron, double Coal, double Steel, double Food, double Human) {
        super(initialGold,initialGoldWood,initialGoldStone, initialGoldIron, initialGoldCoal, initialGoldSteel, initialGoldFood, initialGoldHuman );
        this.Gold = Gold;
        this.Wood = Wood;
        this.Stone = Stone;
        this.Iron = Iron;
        this.Coal = Coal;
        this.Steel = Steel;
        this.Food = Food;
        this.Human = Human;
      

    }

}

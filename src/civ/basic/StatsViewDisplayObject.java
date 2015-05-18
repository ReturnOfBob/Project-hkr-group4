/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;

/**
 *
 * @author Nicklas
 */
//1bd5f6
public class StatsViewDisplayObject {
    String resourceName;
    String normalBuildingCost;
    String normalBuildingProduces;
    String uniqueBuildingCost;
    String uniqueBuildingProduces;
    
    public StatsViewDisplayObject(String resourceName, String normalBuildingCost, String normalBuildingProduces, String uniqueBuildingCost, String uniqueBuildingProduces){
        this.resourceName = resourceName;
        this.normalBuildingCost = normalBuildingCost;
        this.normalBuildingProduces = normalBuildingProduces;
        this.uniqueBuildingCost = uniqueBuildingCost;
        this.uniqueBuildingProduces = uniqueBuildingProduces;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getNormalBuildingCost() {
        return normalBuildingCost;
    }

    public void setNormalBuildingCost(String normalBuildingCost) {
        this.normalBuildingCost = normalBuildingCost;
    }

    public String getNormalBuildingProduces() {
        return normalBuildingProduces;
    }

    public void setNormalBuildingProduces(String normalBuildingProduces) {
        this.normalBuildingProduces = normalBuildingProduces;
    }

    public String getUniqueBuildingCost() {
        return uniqueBuildingCost;
    }

    public void setUniqueBuildingCost(String uniqueBuildingCost) {
        this.uniqueBuildingCost = uniqueBuildingCost;
    }

    public String getUniqueBuildingProduces() {
        return uniqueBuildingProduces;
    }

    public void setUniqueBuildingProduces(String uniqueBuildingProduces) {
        this.uniqueBuildingProduces = uniqueBuildingProduces;
    }
    
    
}

package civ.basic;

public class StatsViewDisplayObject {
    
//--------------------------------VARIABLES-----------------------------------\\

    private String resourceName;
    private String normalBuildingCost;
    private String normalBuildingProduces;
    private String uniqueBuildingCost;
    private String uniqueBuildingProduces;
    
//----------------------------NON-FXML METHODS--------------------------------\\

    public StatsViewDisplayObject(String resourceName, String normalBuildingCost, String normalBuildingProduces, String uniqueBuildingCost, String uniqueBuildingProduces) {
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

package civ.basic;

/**
 *
 * @author Erik
 */
public class EventStorage {
    
    //--------------------------------VARIABLES-----------------------------------\\
    private static EventStorage eventStorage;
    private String eventText;

    private int eventChangeGold = 0;
    private int eventChangeWood = 0;
    private int eventChangeStone = 0;
    private int eventChangeFood = 0;
    private int eventChangeHuman = 0;
    private int eventChangeIron = 0;
    private int eventChangeCoal = 0;
    private int eventChangeSteel = 0;
    private int eventChangeGoldMultiplier = 1;
    private int eventChangeWoodMultiplier = 1;
    private int eventChangeStoneMultiplier = 1;
    private int eventChangeFoodMultiplier = 1;
    private int eventChangeHumanMultiplier = 1;
    private int eventChangeIronMultiplier = 1;
    private int eventChangeCoalMultiplier = 1;
    private int eventChangeSteelMultiplier = 1;
    
    private double eventPercentageChangeGold = 1;
    private double eventPercentageChangeWood = 1;
    private double eventPercentageChangeStone = 1;
    private double eventPercentageChangeFood = 1;
    private double eventPercentageChangeHuman = 1;
    private double eventPercentageChangeIron = 1;
    private double eventPercentageChangeCoal = 1;
    private double eventPercentageChangeSteel = 1;
            
    private EventStorage(){
    
    }
//---------------------GETS INFORMATION FROM EVENTSTORAGE---------------------\\    
    public static EventStorage getInstance(){
        if(eventStorage == null){
            eventStorage = new EventStorage();
        }
        
        return eventStorage;
    }
//-------------------------------------EVENTS---------------------------------\\
    public void Events(int eventID){
    switch (eventID) {
            case 1:
    
                eventText = "One of your citys guards helps a old man on the road, as a reward the old man give your city 10 Gold pices.\n+10 Gold";
                eventChangeGold = 10;
                break;
            case 2:
    
                eventText ="A evil little dwarf comes and steals 20% of your gold! All you can do is with a sobbing voice screm: You have violated the law, come back here you little lawbreaking halfman!! At which you can hear a fleeting voice answer back: I'm guilty of a far more monstrous crime: I'm guilty of being a dwarf!\n-20% Gold";
                eventPercentageChangeGold = 0.8;
                break;
            case 3:
    
                eventText = "It's been a very good year for wood, so you get extra.";
                eventChangeWood = 10;
                break;
         /*   case 4:
    
                eventText = "Oh nooes, 20% of all your stored wood just went bad, so you have to throw it away!";
                eventResultProcentage = (int) (FXMLGameController.getInstance().getamountOfWood() * 0.8);
                FXMLGameController.getInstance().setamountOfWood((int) eventResultProcentage);
                break;
    
            case 5:
    
                eventText = "The holy Bob have arived, you get 10 of all resources!";
                eventResult = 10 + FXMLGameController.getInstance().getamountOfIron();
                FXMLGameController.getInstance().setamountOfIron(eventResult);
                eventResult = 10 + FXMLGameController.getInstance().getamountOfFood();
                FXMLGameController.getInstance().setamountOfFood(eventResult);
                eventResult = 10 + FXMLGameController.getInstance().getamountOfSteel();
                FXMLGameController.getInstance().setamountOfSteel(eventResult);
                eventResult = 10 + FXMLGameController.getInstance().getamountOfHuman();
                FXMLGameController.getInstance().setamountOfHuman(eventResult);
                eventResult = 10 + FXMLGameController.getInstance().getamountOfCoal();
                FXMLGameController.getInstance().setamountOfCoal(eventResult);
                eventResult = 10 + FXMLGameController.getInstance().getamountOfWood();
                FXMLGameController.getInstance().setamountOfWood(eventResult);
                eventResult = 10 + FXMLGameController.getInstance().getamountOfStone();
                FXMLGameController.getInstance().setamountOfStone(eventResult);
    
                break;
            case 6:
    
                eventText ="Sacrifice 10 food to appease the holy Bob!!";
                eventResult = 10 + FXMLGameController.getInstance().getamountOfFood();
                FXMLGameController.getInstance().setamountOfFood(eventResult);
    
                break;
            case 7:
    
                eventText = "One of the kids in the town found a chest under a rock outside of the town, he now wants to give the town all 10 iron ingots that was inside!";
                eventResult = 10 + FXMLGameController.getInstance().getamountOfIron();
                FXMLGameController.getInstance().setamountOfIron(eventResult);
    
                break;
            case 8:
    
                eventText = "The traders that was in the town forgot 5 steel, none would care... right? RIGHT??";
                eventResult = 5 + FXMLGameController.getInstance().getamountOfSteel();
                FXMLGameController.getInstance().setamountOfSteel(eventResult);
    
                break;
    
            case 9:
    
                eventText = "Bandits attack and steals all your gold!";
                FXMLGameController.getInstance().setamountGold(0);
    
                break;
            case 10:
    
                eventText = "You where out and walking, when it hit you! The pebbels on the ground is made out of... stone, so you gather some and takes it home in your pockets, you recive 15 stone!";
                eventResult = 10 + FXMLGameController.getInstance().getamountOfStone();
                FXMLGameController.getInstance().setamountOfStone(eventResult);
    
                break;
            case 11:
    
                eventText = "5 people have heard of your town! So they want to move in!";
                eventResult = 15 + FXMLGameController.getInstance().getamountOfHuman();
                FXMLGameController.getInstance().setamountOfHuman(eventResult);
    
                break;*/
            /*case 12:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 13:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 14:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 15:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 16:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 17:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 18:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 19:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 20:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
    
            case 21:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 22:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 23:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 24:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
    
            case 25:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 26:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 27:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 28:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 29:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 30:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 31:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 32:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            default:
                
    
                break;*/
        }
    }
    
    public void resetEventResources(){
        eventChangeGold = 0;
        eventChangeWood = 0;
        eventChangeStone = 0;
        eventChangeFood = 0;
        eventChangeHuman = 0;
        eventChangeIron = 0;
        eventChangeCoal = 0;
        eventChangeSteel = 0;
    
        eventPercentageChangeGold = 1;
        eventPercentageChangeWood = 1;
        eventPercentageChangeStone = 1;
        eventPercentageChangeFood = 1;
        eventPercentageChangeHuman = 1;
        eventPercentageChangeIron = 1;
        eventPercentageChangeCoal = 1;
        eventPercentageChangeSteel= 1;
        
    }
//--------------------------GETTERS & SETTERS---------------------------------\\
    public String getEventText() {
        return eventText;
    }

    public void setEventText(String eventText) {
        this.eventText = eventText;
    }

    public int getEventChangeGold() {
        return eventChangeGold;
    }

    public void setEventChangeGold(int eventChangeGold) {
        this.eventChangeGold = eventChangeGold;
    }

    public int getEventChangeWood() {
        return eventChangeWood;
    }

    public void setEventChangeWood(int eventChangeWood) {
        this.eventChangeWood = eventChangeWood;
    }

    public int getEventChangeStone() {
        return eventChangeStone;
    }

    public void setEventChangeStone(int eventChangeStone) {
        this.eventChangeStone = eventChangeStone;
    }

    public int getEventChangeFood() {
        return eventChangeFood;
    }

    public void setEventChangeFood(int eventChangeFood) {
        this.eventChangeFood = eventChangeFood;
    }

    public int getEventChangeHuman() {
        return eventChangeHuman;
    }

    public void setEventChangeHuman(int eventChangeHuman) {
        this.eventChangeHuman = eventChangeHuman;
    }

    public int getEventChangeIron() {
        return eventChangeIron;
    }

    public void setEventChangeIron(int eventChangeIron) {
        this.eventChangeIron = eventChangeIron;
    }

    public int getEventChangeCoal() {
        return eventChangeCoal;
    }

    public void setEventChangeCoal(int eventChangeCoal) {
        this.eventChangeCoal = eventChangeCoal;
    }

    public int getEventChangeSteel() {
        return eventChangeSteel;
    }

    public void setEventChangeSteel(int eventChangeSteel) {
        this.eventChangeSteel = eventChangeSteel;
    }

    public double getEventPercentageChangeGold() {
        return eventPercentageChangeGold;
    }

    public void setEventPercentageChangeGold(double eventPercentageChangeGold) {
        this.eventPercentageChangeGold = eventPercentageChangeGold;
    }

    public double getEventPercentageChangeWood() {
        return eventPercentageChangeWood;
    }

    public void setEventPercentageChangeWood(double eventPercentageChangeWood) {
        this.eventPercentageChangeWood = eventPercentageChangeWood;
    }

    public double getEventPercentageChangeStone() {
        return eventPercentageChangeStone;
    }

    public void setEventPercentageChangeStone(double eventPercentageChangeStone) {
        this.eventPercentageChangeStone = eventPercentageChangeStone;
    }

    public double getEventPercentageChangeFood() {
        return eventPercentageChangeFood;
    }

    public void setEventPercentageChangeFood(double eventPercentageChangeFood) {
        this.eventPercentageChangeFood = eventPercentageChangeFood;
    }

    public double getEventPercentageChangeHuman() {
        return eventPercentageChangeHuman;
    }

    public void setEventPercentageChangeHuman(double eventPercentageChangeHuman) {
        this.eventPercentageChangeHuman = eventPercentageChangeHuman;
    }

    public double getEventPercentageChangeIron() {
        return eventPercentageChangeIron;
    }

    public void setEventPercentageChangeIron(double eventPercentageChangeIron) {
        this.eventPercentageChangeIron = eventPercentageChangeIron;
    }

    public double getEventPercentageChangeCoal() {
        return eventPercentageChangeCoal;
    }

    public void setEventPercentageChangeCoal(double eventPercentageChangeCoal) {
        this.eventPercentageChangeCoal = eventPercentageChangeCoal;
    }

    public double getEventPercentageChangeSteel() {
        return eventPercentageChangeSteel;
    }

    public void setEventPercentageChangeSteel(double eventPercentageChangeSteel) {
        this.eventPercentageChangeSteel = eventPercentageChangeSteel;
    }
    
    
    
}
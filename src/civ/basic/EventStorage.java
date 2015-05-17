package civ.basic;

/**
 *
 * @author Erik
 */
public class EventStorage {
    
    //--------------------------------VARIABLES-----------------------------------\\
    private static EventStorage eventStorage;
    private String eventText;
    private boolean eventActive = false;

    private int eventChangeGold = 0;
    private int eventChangeWood = 0;
    private int eventChangeStone = 0;
    private int eventChangeFood = 0;
    private int eventChangeHuman = 0;
    private int eventChangeIron = 0;
    private int eventChangeCoal = 0;
    private int eventChangeSteel = 0;
    
    private double eventChangeGoldMultiplier = 1;
    private double eventChangeWoodMultiplier = 1;
    private double eventChangeStoneMultiplier = 1;
    private double eventChangeFoodMultiplier = 1;
    private double eventChangeHumanMultiplier = 1;
    private double eventChangeIronMultiplier = 1;
    private double eventChangeCoalMultiplier = 1;
    private double eventChangeSteelMultiplier = 1;
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
                eventActive = true;
                eventText = "One of your citys guards helps a old man on the road, as a reward the old man give your city 10 Gold pices.\n+10 Gold";
                eventChangeGold = 10;
                break;
            case 2:
                eventActive = true;
                eventText ="A evil little dwarf comes and steals 20% of your gold! All you can do is with a sobbing voice screm: You have violated the law, come back here you little lawbreaking halfman!! At which you can hear a fleeting voice answer back: I'm guilty of a far more monstrous crime: I'm guilty of being a dwarf!\n-20% Gold";
                eventPercentageChangeGold = 0.8;
                break;
            case 3:   
                eventActive = true;
                eventText = "It's been a very good year for wood, so you get extra.\n+10 Wood";
                eventChangeWood = 10;
                break;
            case 4:
                eventActive = true;
                eventText = "Oh nooes, 20% of all your stored wood just went bad, so you have to throw it away!";
                eventPercentageChangeFood = 0.8;
                break;
            case 5: 
                eventActive = true;
                eventText = "The holy Bob have arived, you get 20 of all resources!";
                eventChangeSteel = 20;
                eventChangeWood = 20;
                eventChangeStone = 20;
                eventChangeFood = 20;
                eventChangeIron = 20;
                eventChangeCoal = 20;
                eventChangeGold = 20;
                break;
            case 6:  
                eventActive = true;
                eventText ="Sacrifice 40 food to appease the holy Bob!!";
                eventChangeFood = -40;
                break;
            case 7:
                eventActive = true;
                eventText = "One of the kids in the town found a chest under a rock outside of the town, he now wants to give the town all 10 iron ingots that was inside!";
                eventChangeSteel = 10;
                break;
            case 8:
                eventActive = true;
                eventText = "The traders that was in the town forgot 15 steel, none would care... right? RIGHT??";
                eventChangeSteel = 15;
                break;
            case 9:
                eventActive = true;
                eventText = "Bandits attack and steals all your gold!";
                eventPercentageChangeGold = 0;
                break;
            case 10:
                eventActive = true;
                eventText = "You where out and walking, when it hit you! The pebbels on the ground is made out of... stone, so you gather some and takes it home in your pockets, you recive 15 stone!";
                eventChangeStone = 15;
                break;
            case 11:
                eventActive = true;
                eventText = "5 people have heard of your town! So they want to move in!";
                eventChangeHuman = 5;
                break;
            case 12:
                eventActive = true;
                EventHandler.getInstance().setEventIsActive(true);
                EventHandler.getInstance().setEventDuration(5);
                eventText = "Your lumberjack have found a huge forest with plenty of trees in it, you will have 20% more wood per turn for 5 turns.\n+20% Wood/p turn";
                eventChangeWoodMultiplier = 1.2;
                break;
            case 13:
                eventActive = true;
                eventText = "Bandits attack and steals all your wood!";
                eventPercentageChangeWood = 0;
                break;
            case 14:
                eventActive = true;
                eventText = "Bandits attack and steals all your stone!";
                eventPercentageChangeStone = 0;
                break;
            case 15:
                eventActive = true;
                eventText = "Bandits attack and steals all your food!";
                eventPercentageChangeFood = 0;
                break;            
            case 16:
                eventActive = true;
                eventText = "You found a shit-ton of loggs by the gate! Better put them in the storage...";
                eventChangeWood = 15;
                break;
            case 17:
                eventActive = true;
                eventText = "A astroid was sighted by your astrologs, the end time is coming!! You lose 20 food!";
                eventChangeFood = -20;
                break;
            case 18:   
                eventActive = true;
                eventText = "You where fishing, when you caught the whale in the lake! You chopp it up for 40 food!";
                eventChangeFood = 40;
                break;
          /*  case 19:
    
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
    
                break;*/
            default:
                eventActive = false;  
                break;
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
    
    public void resetTimedEvents(){
        eventChangeGoldMultiplier = 1;
        eventChangeWoodMultiplier = 1;
        eventChangeStoneMultiplier = 1;
        eventChangeFoodMultiplier = 1;
        eventChangeHumanMultiplier = 1;
        eventChangeIronMultiplier = 1;
        eventChangeCoalMultiplier = 1;
        eventChangeSteelMultiplier = 1;
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

    public double getEventChangeGoldMultiplier() {
        return eventChangeGoldMultiplier;
    }

    public void setEventChangeGoldMultiplier(double eventChangeGoldMultiplier) {
        this.eventChangeGoldMultiplier = eventChangeGoldMultiplier;
    }

    public double getEventChangeWoodMultiplier() {
        return eventChangeWoodMultiplier;
    }

    public void setEventChangeWoodMultiplier(double eventChangeWoodMultiplier) {
        this.eventChangeWoodMultiplier = eventChangeWoodMultiplier;
    }

    public double getEventChangeStoneMultiplier() {
        return eventChangeStoneMultiplier;
    }

    public void setEventChangeStoneMultiplier(double eventChangeStoneMultiplier) {
        this.eventChangeStoneMultiplier = eventChangeStoneMultiplier;
    }

    public double getEventChangeFoodMultiplier() {
        return eventChangeFoodMultiplier;
    }

    public void setEventChangeFoodMultiplier(double eventChangeFoodMultiplier) {
        this.eventChangeFoodMultiplier = eventChangeFoodMultiplier;
    }

    public double getEventChangeHumanMultiplier() {
        return eventChangeHumanMultiplier;
    }

    public void setEventChangeHumanMultiplier(double eventChangeHumanMultiplier) {
        this.eventChangeHumanMultiplier = eventChangeHumanMultiplier;
    }

    public double getEventChangeIronMultiplier() {
        return eventChangeIronMultiplier;
    }

    public void setEventChangeIronMultiplier(double eventChangeIronMultiplier) {
        this.eventChangeIronMultiplier = eventChangeIronMultiplier;
    }

    public double getEventChangeCoalMultiplier() {
        return eventChangeCoalMultiplier;
    }

    public void setEventChangeCoalMultiplier(double eventChangeCoalMultiplier) {
        this.eventChangeCoalMultiplier = eventChangeCoalMultiplier;
    }

    public double getEventChangeSteelMultiplier() {
        return eventChangeSteelMultiplier;
    }

    public void setEventChangeSteelMultiplier(double eventChangeSteelMultiplier) {
        this.eventChangeSteelMultiplier = eventChangeSteelMultiplier;
    }

    public boolean isEventActive() {
        return eventActive;
    }

    public void setEventActive(boolean eventActive) {
        this.eventActive = eventActive;
    }
    
    
    
}
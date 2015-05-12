package civ.basic;

/**
 *
 * @author Erik
 */
public class EventStorage {
    
    //--------------------------------VARIABLES-----------------------------------\\
    
    String eventText;
    int eventResult;
    double eventResultProcentage;
    private FXMLGameController resource = new FXMLGameController();

    
    //---------------------------------EVENTS-----------------------------------\\
    private void Events(int eventID){
    switch (eventID) {
            case 1:
    
                eventText = "One of your citys guards helps a old man on the road, as a reward the old man give your city 10 Gold pices.";
                eventResult = 10 + resource.getamountOfGold();
                resource.setamountGold(eventResult);
                eventResultProcentage = 1;
    
                break;
            case 2:
    
                eventText ="A evil little dwarf comes and steals 20% of your gold! All you can do is with a sobbing voice screm: You have violated the law, come back here you little lawbreaking halfman!! At which you can hear a fleeting voice answer back: I'm guilty of a far more monstrous crime: I'm guilty of being a dwarf!";
                eventResultProcentage = (int) (resource.getamountOfGold() * 0.8);
                resource.setamountGold((int) eventResultProcentage);
                break;
            case 3:
    
                eventText = "It's been a very good year for wood, so you get extra.";
                eventResult = 10 + resource.getamountOfWood();
                resource.setamountOfWood(eventResult);
                break;
            case 4:
    
                eventText = "Oh nooes, 20% of all your stored wood just went bad, so you have to throw it away!";
                eventResultProcentage = (int) (resource.getamountOfWood() * 0.8);
                resource.setamountOfWood((int) eventResultProcentage);
                break;
    
            case 5:
    
                eventText = "The holy Bob have arived, you get 10 of all resources!";
                eventResult = 10 + resource.getamountOfIron();
                resource.setamountOfIron(eventResult);
                eventResult = 10 + resource.getamountOfFood();
                resource.setamountOfFood(eventResult);
                eventResult = 10 + resource.getamountOfSteel();
                resource.setamountOfSteel(eventResult);
                eventResult = 10 + resource.getamountOfHuman();
                resource.setamountOfHuman(eventResult);
                eventResult = 10 + resource.getamountOfCoal();
                resource.setamountOfCoal(eventResult);
                eventResult = 10 + resource.getamountOfWood();
                resource.setamountOfWood(eventResult);
                eventResult = 10 + resource.getamountOfStone();
                resource.setamountOfStone(eventResult);
    
                break;
            case 6:
    
                eventText ="Sacrifice 10 food to appease the holy Bob!!";
                eventResult = 10 + resource.getamountOfFood();
                resource.setamountOfFood(eventResult);
    
                break;
            case 7:
    
                eventText = "One of the kids in the town found a chest under a rock outside of the town, he now wants to give the town all 10 iron ingots that was inside!";
                eventResult = 10 + resource.getamountOfIron();
                resource.setamountOfIron(eventResult);
    
                break;
            case 8:
    
                eventText = "The traders that was in the town forgot 5 steel, none would care... right? RIGHT??";
                eventResult = 5 + resource.getamountOfSteel();
                resource.setamountOfSteel(eventResult);
    
                break;
    
            case 9:
    
                eventText = "Bandits attack and steals all your gold!";
                resource.setamountGold(0);
    
                break;
            case 10:
    
                eventText = "You where out and walking, when it hit you! The pebbels on the ground is made out of... stone, so you gather some and takes it home in your pockets, you recive 15 stone!";
                eventResult = 10 + resource.getamountOfStone();
                resource.setamountOfStone(eventResult);
    
                break;
            case 11:
    
                eventText = "5 people have heard of your town! So they want to move in!";
                eventResult = 15 + resource.getamountOfHuman();
                resource.setamountOfHuman(eventResult);
    
                break;
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
}
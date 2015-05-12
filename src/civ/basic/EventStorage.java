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
    private void Events(){
    switch () {
            case 1:
    
                eventText = "One of your citys guards helps a old man on the road, as a reward the old man give your city 10 Gold pices.";
                eventResult = 10 + resource.getamountOfGold();
                resource.setamountGold(eventResult);
                eventResultProcentage = 1;
    
                break;
            case 2:
    
                eventText ="A evil little dwarf comes and steals 20% of your gold! All you can do is with a sobbing voice screm: You have violated the law, come back here you little lawbreaking halfman!! At which he answer: I'm guilty of a far more monstrous crime: I'm guilty of being a dwarf!";
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
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 6:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 7:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 8:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
    
            case 9:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 10:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 11:
    
                eventText =
                eventResult =
                eventResultProcentage =
    
                break;
            case 12:
    
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
                
    
                break;
        }
}
}
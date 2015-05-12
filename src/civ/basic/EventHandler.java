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
public class EventHandler {
    private static EventHandler eventHandler;
    private boolean eventIsActive = false;
    private int eventDuration = 0;
    
    private EventHandler(){
    
    }
    
    public static EventHandler getInstance(){
        if(eventHandler == null){
            eventHandler = new EventHandler();
        }
        
        return eventHandler;
    }
//--------------------------------METHODS-------------------------------------\\    
    //Takes the random generated number and decides which event that should be used, 
    //if the number does not match any events chance of happening then nothing special will happen this turn
    public void calculateEvent(int randomGeneratedNumber){
        //If an event is already active, only 1-turn bonusevents will happen
        if(eventIsActive == true){
            if(randomGeneratedNumber == 10){
                //event.geteventExample1
                
            }
            else if(randomGeneratedNumber > 30 && randomGeneratedNumber < 50){
                //event.getEventExample2
            }
            else{
                //No event will occur
            }
        }
        //If it is false however, there is a possibility for every event to happen
        else{ 
            if(randomGeneratedNumber < 500){
                EventStorage.getInstance().Events(1);
            }
            else if(randomGeneratedNumber > 500){
             EventStorage.getInstance().Events(2);
            }
            /*else if(randomGeneratedNumber > 30 && randomGeneratedNumber < 50){
                eventCall.Events(3);
            }*/
            else{
                //No event will occur
            }
        }
    }
//-------------------------GETTERS AND SETTERS--------------------------------\\     
    public void setEventIsActive(boolean eventIsActive){
        this.eventIsActive = eventIsActive;
    }
    
    public boolean getEventIsActive(){
        return eventIsActive;
    }
    
    public void setEventDuration(int eventDuration){
        this.eventDuration = eventDuration;
    }
    
    public int getEventDuration(){
        return eventDuration;
    }
    
    
}
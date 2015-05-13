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
            if(randomGeneratedNumber > 0 && randomGeneratedNumber < 10){
                EventStorage.getInstance().Events(1);
            }
            else if(randomGeneratedNumber > 11 && randomGeneratedNumber < 20){
                EventStorage.getInstance().Events(2);
            }
            else if(randomGeneratedNumber > 21 && randomGeneratedNumber < 30){
                EventStorage.getInstance().Events(3);
            }
            else if(randomGeneratedNumber > 31 && randomGeneratedNumber < 40){
                EventStorage.getInstance().Events(4);
            }
            else if(randomGeneratedNumber > 41 && randomGeneratedNumber < 50){
                EventStorage.getInstance().Events(5);
            }
            else if(randomGeneratedNumber > 51 && randomGeneratedNumber < 60){
                EventStorage.getInstance().Events(6);
            }
            else if(randomGeneratedNumber > 61 && randomGeneratedNumber < 70){
                EventStorage.getInstance().Events(7);
            }
            else if(randomGeneratedNumber > 71 && randomGeneratedNumber < 80){
                EventStorage.getInstance().Events(8);
            }
            else if(randomGeneratedNumber > 81 && randomGeneratedNumber < 90){
                EventStorage.getInstance().Events(9);
            }
            else if(randomGeneratedNumber > 91 && randomGeneratedNumber < 10){
                EventStorage.getInstance().Events(10);
            }
            else if(randomGeneratedNumber > 101 && randomGeneratedNumber < 110){
                EventStorage.getInstance().Events(11);
            }
             else if(randomGeneratedNumber > 121 && randomGeneratedNumber < 130){ 
                EventStorage.getInstance().Events(13);                           
            }
              else if(randomGeneratedNumber > 131 && randomGeneratedNumber < 140){ 
                EventStorage.getInstance().Events(14);                           
            }
              else if(randomGeneratedNumber > 141 && randomGeneratedNumber < 150){ 
                EventStorage.getInstance().Events(15);                           
            }
              else if(randomGeneratedNumber > 151 && randomGeneratedNumber < 160){ 
                EventStorage.getInstance().Events(16);                           
            }
              else if(randomGeneratedNumber > 161 && randomGeneratedNumber < 170){ 
                EventStorage.getInstance().Events(17);                           
            }
               else if(randomGeneratedNumber > 171 && randomGeneratedNumber < 180){ 
                EventStorage.getInstance().Events(18);                           
            }
               else if(randomGeneratedNumber > 181 && randomGeneratedNumber < 190){ 
                EventStorage.getInstance().Events(19);                           
            }
            else{
                //No event will occur
            }
        }
        //If it is false however, there is a possibility for every event to happen
        else{ 
            if(randomGeneratedNumber > 0 && randomGeneratedNumber < 10){
                EventStorage.getInstance().Events(1);
            }
            else if(randomGeneratedNumber > 11 && randomGeneratedNumber < 20){
                EventStorage.getInstance().Events(2);
            }
            else if(randomGeneratedNumber > 21 && randomGeneratedNumber < 30){
                EventStorage.getInstance().Events(3);
            }
            else if(randomGeneratedNumber > 31 && randomGeneratedNumber < 40){
                EventStorage.getInstance().Events(4);
            }
            else if(randomGeneratedNumber > 41 && randomGeneratedNumber < 50){
                EventStorage.getInstance().Events(5);
            }
            else if(randomGeneratedNumber > 51 && randomGeneratedNumber < 60){
                EventStorage.getInstance().Events(6);
            }
            else if(randomGeneratedNumber > 61 && randomGeneratedNumber < 70){
                EventStorage.getInstance().Events(7);
            }
            else if(randomGeneratedNumber > 71 && randomGeneratedNumber < 80){
                EventStorage.getInstance().Events(8);
            }
            else if(randomGeneratedNumber > 81 && randomGeneratedNumber < 90){
                EventStorage.getInstance().Events(9);
            }
            else if(randomGeneratedNumber > 91 && randomGeneratedNumber < 100){
                EventStorage.getInstance().Events(10);
            }
            else if(randomGeneratedNumber > 101 && randomGeneratedNumber < 110){
                EventStorage.getInstance().Events(11);
            }
            else if(randomGeneratedNumber > 111 && randomGeneratedNumber < 120){ //OBS Timed Event!!
                EventStorage.getInstance().Events(12);                           //OBS Timed Event!!
            }
             else if(randomGeneratedNumber > 121 && randomGeneratedNumber < 130){ 
                EventStorage.getInstance().Events(13);                           
            }
              else if(randomGeneratedNumber > 131 && randomGeneratedNumber < 140){ 
                EventStorage.getInstance().Events(14);                           
            }
              else if(randomGeneratedNumber > 141 && randomGeneratedNumber < 150){ 
                EventStorage.getInstance().Events(15);                           
            }
              else if(randomGeneratedNumber > 151 && randomGeneratedNumber < 160){ 
                EventStorage.getInstance().Events(16);                           
            }
              else if(randomGeneratedNumber > 161 && randomGeneratedNumber < 170){ 
                EventStorage.getInstance().Events(17);                           
            }
               else if(randomGeneratedNumber > 171 && randomGeneratedNumber < 180){ 
                EventStorage.getInstance().Events(18);                           
            }
               else if(randomGeneratedNumber > 181 && randomGeneratedNumber < 190){ 
                EventStorage.getInstance().Events(19);                           
            }
            
            
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
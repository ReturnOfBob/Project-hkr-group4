/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ.basic;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Henrik
 */
public class SoundPlayer {

    private MediaPlayer musicPlayer;

    public void playMusic(int musicChoice) {
        if (musicChoice == 1) {

            Media audioFile = new Media(getClass().getResource("/Resources/Soundtrack/SoundPack1.mp3").toString());

            try {

                musicPlayer = new MediaPlayer(audioFile);
                musicPlayer.setCycleCount(musicPlayer.INDEFINITE);
                musicPlayer.play();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }

        if (musicChoice == 2) {

            Media audioFile = new Media(getClass().getResource("/Resources/Soundtrack/SoundPack2.mp3").toString());

            try {

                musicPlayer = new MediaPlayer(audioFile);
                musicPlayer.setCycleCount(musicPlayer.INDEFINITE);
                musicPlayer.play();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        
        if (musicChoice == 3) {

            Media audioFile = new Media(getClass().getResource("/Resources/Soundtrack/SoundPack3.mp3").toString());

            try {
                
                musicPlayer = new MediaPlayer(audioFile);
                musicPlayer.setCycleCount(musicPlayer.INDEFINITE);
                musicPlayer.play();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }

    public void setMusicPause() {
        musicPlayer.pause();
    }
   /* public void setMusicStop(){
        //Kolla om den är igång först
        musicPlayer.stop();
    }*/

    public void setMusicOn() {
        musicPlayer.play();
    }

    public void setMusicVolume(double volumeValue) {
        musicPlayer.setVolume(volumeValue);
    }

    private void createMusic(Media musicChoice) {
        musicPlayer = new MediaPlayer(musicChoice);
        musicPlayer.setCycleCount(musicPlayer.INDEFINITE);
        setMusicOn();
    }
}

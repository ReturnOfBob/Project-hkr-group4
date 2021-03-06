package civ.basic;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {
    
//--------------------------------VARIABLES-----------------------------------\\

    private static SoundPlayer soundPlayer;
    private MediaPlayer musicPlayer;
    private Media audioFile;

    private SoundPlayer() {

    }
//----------------------------NON-FXML METHODS--------------------------------\\
    
    public static SoundPlayer getInstance() {
        if (soundPlayer == null) {
            soundPlayer = new SoundPlayer();
        }

        return soundPlayer;
    }

    public void playMusic(int musicChoice) {
        if (musicChoice == 1) {

            audioFile = new Media(getClass().getResource("/Resources/Soundtrack/SoundPack1.mp3").toString());

            try {

                createMusic(audioFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }

        if (musicChoice == 2) {

            audioFile = new Media(getClass().getResource("/Resources/Soundtrack/SoundPack2.mp3").toString());

            try {

                createMusic(audioFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }

        if (musicChoice == 3) {

            audioFile = new Media(getClass().getResource("/Resources/Soundtrack/SoundPack3.mp3").toString());

            try {

                createMusic(audioFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }

    public void setMusicMute(boolean value) {
        musicPlayer.setMute(value);
        DataStorage.getInstance().setMusicSet(value);
    }

    public void setMusicStop() {
        if (DataStorage.getInstance().isMusicSet() == false) {
            musicPlayer.stop();
        }
    }

    public void setMusicOn() {
        musicPlayer.play();
    }

    private void createMusic(Media musicChoice) {
        musicPlayer = new MediaPlayer(musicChoice);
        musicPlayer.setCycleCount(musicPlayer.INDEFINITE);
        setMusicOn();
    }
}

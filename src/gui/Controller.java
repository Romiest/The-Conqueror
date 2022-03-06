package gui;

import engine.Game;
import javafx.scene.Parent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;

public class Controller {

    private Stage window;
    private String name;
    private Game game;
    private boolean fullScreen = true;
    private String currentCity;
    boolean showGrid = false;
    private MediaPlayer mainMusic;
    private MediaPlayer otherMusic;
    private double mediaVolume = 1;



    public Controller(Stage window) {
        this.window = window;
        window.setOnCloseRequest(e -> {
            if (mainMusic != null) {
                mainMusic.dispose();
            }
            if (otherMusic != null) {
                otherMusic.dispose();
            }
        });
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public double getMediaVolume() {
        return mediaVolume;
    }

    public void setMediaVolume(double mediaVolume) {
        this.mediaVolume = mediaVolume;
    }

    public void changeScene(Parent newContent) {
        window.getScene().setRoot(newContent);
    }

    public void setMainMusic(String path) {
        this.mainMusic = new MediaPlayer(new Media(new File(path).toURI().toString()));
        mainMusic.setVolume(mediaVolume);
        mainMusic.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                mainMusic.seek(Duration.ZERO);
            }
        });
    }

    public void setOtherMusic(String path) {
        if (otherMusic != null) {
            otherMusic.stop();
        }
        this.otherMusic = new MediaPlayer(new Media(new File(path).toURI().toString()));
        otherMusic.setVolume(mediaVolume);
        otherMusic.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                otherMusic.seek(Duration.ZERO);
            }
        });
    }

    public void playMainMusic() {
        if (otherMusic != null) {
            otherMusic.stop();
        }
        mainMusic.play();
    }

    public void startOtherMusic() {
        mainMusic.pause();
        otherMusic.play();
    }

}



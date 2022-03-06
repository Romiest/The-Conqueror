package gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.NodeOrientation;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.*;
import java.util.*;

public class LoopPlayer extends MediaView {
    String path;

    public LoopPlayer(String path) {
        super();
        //Instantiating Media class
        Media media = new Media(new File(path).toURI().toString());

        //Instantiating MediaPlayer class
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //Instantiating MediaView class
        setMediaPlayer(mediaPlayer);

        final DoubleProperty width = this.fitWidthProperty();
        final DoubleProperty height = this.fitHeightProperty();

        width.bind(Bindings.selectDouble(this.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(this.sceneProperty(), "height"));
        this.setPreserveRatio(false);

        //by setting this property to true, the Video will be played
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
    }
}



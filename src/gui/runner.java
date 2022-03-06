package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class runner extends Application {


    MediaPlayer music;
    MediaPlayer otherMusic;
    public static void main(String[] args) {
        launch(args);
    }

    boolean main = true;
    Duration time;

    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane s = new StackPane();

        Media media = new Media(new File("resources/music/THE_REDWOODS.mp3").toURI().toString());
        music = new MediaPlayer(media);
        time = new Duration(0);
        Media newMedia = new Media(new File("resources/music/Flight Hymn_trimmed.mp3").toURI().toString());
        otherMusic = new MediaPlayer(newMedia);

        music.setAutoPlay(true);
        music.setVolume(0.5);

        MyButton b = new MyButton("Click me");
        b.setOnAction(e -> {
            if (main) {
                music.pause();
                otherMusic.play();
            } else {
                otherMusic.stop();
                music.play();
            }
            System.out.println(time);
            main = !main;
        });
        s.getChildren().add(b);

        Scene scene = new Scene(s, 500, 500);
        scene.getStylesheets().add("gui/buttonStyle.css");
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
    }
}



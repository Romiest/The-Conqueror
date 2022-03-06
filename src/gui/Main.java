package gui;
import engine.Game;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.embed.swing.SwingNode;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;


import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import gui.helpers;

public class Main extends Application {

    Stage window;
    Font cf2;
    double height;
    double width;

    double factor = 1;

    Game game;




    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }




    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        //Fixing bug with swing and javafx not working correctly
//        final SwingNode swingNode = new SwingNode();
//        createAndSetSwingContent(swingNode);
//        StackPane paneWithSwing = new StackPane(swingNode);
//
//        // create invisible pane to initialize SwingPane in Scene
//        Pane invisiblePane = new Pane(paneWithSwing);
//        invisiblePane.setPrefWidth(0);
//        invisiblePane.setPrefHeight(0);
//        invisiblePane.setOpacity(0);

        //End of fix





        Stage window = new Stage();

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();


        Controller control = new Controller(window);

        MediaView video = new MediaView();

        Media media = new Media(new File("resources/videos/Intro.mp4").toURI().toString());

        //Instantiating MediaPlayer class
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //Instantiating MediaView class
        video.setMediaPlayer(mediaPlayer);

        final DoubleProperty width = video.fitWidthProperty();
        final DoubleProperty height = video.fitHeightProperty();

        width.bind(Bindings.selectDouble(video.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(video.sceneProperty(), "height"));
        video.setPreserveRatio(false);


        mediaPlayer.setOnEndOfMedia(() -> {
            StartView start = new StartView(control);
            mediaPlayer.dispose();
            control.changeScene(start.getRoot());
        });
        //by setting this property to true, the Video will be played
        mediaPlayer.setAutoPlay(true);

        StackPane layout = new StackPane();
        layout.getChildren().add(video);


        //System info, properties and any other data



        //setting group and scene
        Scene scene = new Scene(layout, screenBounds.getWidth(), screenBounds.getHeight());
        scene.getStylesheets().add("gui/buttonStyle.css");

//        scene.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());


        //Size listeners for dynamic content
        //All sizes are updated in here
//        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
//            width = window.getWidth();
//            height = window.getHeight();
//            factor = width/relWidth;
//            cf2 = cf2.deriveFont((float)(factor*100));
//            l2.getContent().setFont(cf2);
//            home.setAlignment(Pos.CENTER);
//        };
//
//
//        window.widthProperty().addListener(stageSizeListener);
//        window.heightProperty().addListener(stageSizeListener);


        window.setFullScreen(control.isFullScreen());
        window.setFullScreenExitHint("");
        window.setScene(scene);
        window.show();
    }
    







    public static void main(String[] args) {
        launch(args);
    }

}



package gui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class StartView extends View{

    Stage window;
    String name;


    public StartView(Controller control) {
        ModifiedGridPane foreground = new ModifiedGridPane(11, 9);
        foreground.setGridLinesVisible(control.showGrid);


        window = control.getWindow();

        control.setMainMusic("resources/music/THE_REDWOODS.mp3");
        control.playMainMusic();
        control.setMediaVolume(0.8);
        control.setOtherMusic("resources/music/Norwegian Pirate.mp3");



        Image title = new Image("resources/images/title.png");
        title.setPreserveRatio(true);
        title.setFitWidth(1200);
//        cf2 = helpers.getFont("resources/fonts/IMFellGreatPrimer-Regular.ttf", (float) (100*factor));
//
//        BorderPane layoutTest = new BorderPane();
//        JLabel titleText = new JLabel("THE CONQUEROR");
        foreground.add(title, 2, 1, 6, 2);

        TextField nameBox = new TextField();
        nameBox.setPromptText("Enter Your Name!");
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setPrefColumnCount(10);
        foreground.add(nameBox,4, 5,3,1);




//        SwingNode l2 =new SwingNode();
//        l2.setContent(titleText);
//        l2.getContent().setFont(cf2);
//        l2.getContent().setForeground(new Color(108,87,70));
//        props.setProperty("swing.jlf.contentPaneTransparent", "true");

        MyButton start = new MyButton();
        start.setText("Start");

//        TilePane home = new TilePane();
//        home.setPrefRows(3);
//        home.setPrefColumns(1);
//        Pane invisiblePane = new Pane();
//        invisiblePane.setPrefWidth(0);
//        invisiblePane.setPrefHeight(200);
//        invisiblePane.setOpacity(0);


//
//        home.getChildren().addAll(l2, invisiblePane, nameBox, start);
//        home.setOrientation(Orientation.VERTICAL);
//
//        layoutTest.setCenter(home);
//        home.setAlignment(Pos.CENTER);
//        home.setTileAlignment(Pos.CENTER);

        foreground.add(start,5, 7, 1, 1);
        foreground.setHalignment(start, HPos.CENTER);


        Parent nextScene = null;
        try {
            nextScene = new CityChooser(control).getRoot();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Parent finalNextScene = nextScene;
        start.setOnAction(e -> {
            if (!nameBox.getText().equals("")) {
                name = nameBox.getText();
                control.setName(name);
                control.changeScene(finalNextScene);
                window.setFullScreen(control.isFullScreen());
            }
        });

        window.setTitle("The Conqueror");

        LoopPlayer player = new LoopPlayer("resources/videos/background_loop1.mp4");

        //Layout
        StackPane layout = new StackPane();
        layout.getChildren().addAll(player, foreground);

        mainLayout = layout;
    }

}



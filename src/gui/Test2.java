package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class Test2 extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Properties props = System.getProperties();
        props.setProperty("swing.jlf.contentPaneTransparent", "true");
        Font cf2 = helpers.getFont("resources/fonts/IMFellGreatPrimer-Regular.ttf", (float) (100));

        BorderPane layoutTest = new BorderPane();
        JLabel titleText = new JLabel("THE CONQUEROR");



        SwingNode l2 =new SwingNode();
        l2.setContent(titleText);
        l2.getContent().setFont(cf2);
        l2.getContent().setForeground(new Color(108,87,70));
        props.setProperty("swing.jlf.contentPaneTransparent", "true");

        MyButton start = new MyButton();
        start.setText("Start");

        VBox home = new VBox();
        home.getChildren().addAll(l2, start);
        layoutTest.setCenter(home);
        home.setAlignment(Pos.CENTER);

        LoopPlayer player = new LoopPlayer("resources/videos/background_loop1.mp4");
        StackPane layout = new StackPane();
        layout.getChildren().addAll(player, layoutTest);


        Scene scene = new Scene(layout);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}



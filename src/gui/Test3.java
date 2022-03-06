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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Screen;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Test3 extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        BorderPane foreGround = new BorderPane();

        Label top = new Label();
        top.setText("Choose Your City");
        top.setStyle("-fx-font-size: 40");
        top.setAlignment(Pos.CENTER);



        HBox cities = new HBox();

        InputStream stream = new FileInputStream("resources/images/egypt4.png");
        Image image = new Image(stream);
        //Creating the image view
        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(image);
        //Setting the image view parameters
        imageView.setPreserveRatio(true);

//        CityChoice cairo = new CityChoice("resources/images/egypt4.png", "resources/images/egypt4_bw.png",  "Cairo", main);
//        CityChoice sparta = new CityChoice("resources/images/sparta2.png","resources/images/sparta2_bw.png", "Sparta", primaryStage);
//        CityChoice rome = new CityChoice("resources/images/rome2.png","resources/images/rome2_bw.png", "Rome", primaryStage);
//        cities.getChildren().addAll(cairo.getChoice(), sparta.getChoice(), rome.getChoice());


        cities.setAlignment(Pos.CENTER);
        cities.setSpacing(35);
        foreGround.setCenter(cities);
        foreGround.setTop(top);
        foreGround.setAlignment(top, Pos.CENTER);
        foreGround.setAlignment(cities, Pos.CENTER);




        LoopPlayer player = new LoopPlayer("resources/videos/background_loop1.mp4");
        StackPane layout = new StackPane();
        layout.getChildren().addAll(player, foreGround);


        Scene scene = new Scene(layout);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}



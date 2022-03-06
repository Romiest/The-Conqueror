package gui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class CityChooser {
    Parent mainLayout;


    public CityChooser(Controller controller) throws FileNotFoundException {

        BorderPane foreGround = new BorderPane();

        Label top = new Label();
        top.setText("Choose Your City");
        top.setStyle("-fx-font-size: 40");
        top.setAlignment(Pos.CENTER);



        HBox cities = new HBox();

//        InputStream stream = new FileInputStream("resources/images/egypt4.png");
//        Image image = new Image(stream);
//        //Creating the image view
//        ImageView imageView = new ImageView();
//        //Setting image to the image view
//        imageView.setImage(image);
//        //Setting the image view parameters
//        imageView.setPreserveRatio(true);

        CityChoice cairo = new CityChoice("resources/images/egypt4.png", "resources/images/egypt4_bw.png",  "Cairo", controller);
        CityChoice sparta = new CityChoice("resources/images/sparta2.png","resources/images/sparta2_bw.png", "Sparta", controller);
        CityChoice rome = new CityChoice("resources/images/rome2.png","resources/images/rome2_bw.png", "Rome", controller);



        cities.getChildren().addAll(sparta.getChoice(), rome.getChoice(), cairo.getChoice());


        cities.setAlignment(Pos.CENTER);
        cities.setSpacing(35);
        foreGround.setCenter(cities);
        foreGround.setTop(top);
        foreGround.setAlignment(top, Pos.CENTER);
        foreGround.setAlignment(cities, Pos.CENTER);




        LoopPlayer player = new LoopPlayer("resources/videos/background_loop1.mp4");
        StackPane layout = new StackPane();
        layout.getChildren().addAll(player, foreGround);

        mainLayout = layout;
    }

    public Parent getRoot() {
        return mainLayout;
    }


}


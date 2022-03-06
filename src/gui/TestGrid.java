package gui;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class TestGrid extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ModifiedGridPane foreground = new ModifiedGridPane(10, 9);
        foreground.setGridLinesVisible(true);

        Image map = new Image("resources/images/world_map.png");
        map.setPreserveRatio(false);

        final DoubleProperty width = map.fitWidthProperty();
        final DoubleProperty height = map.fitHeightProperty();

        width.bind(Bindings.selectDouble(map.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(map.sceneProperty(), "height"));

        Image cairo = new Image("resources/images/castle_blue.png");
        cairo.setPreserveRatio(true);
        cairo.setFitWidth(170);
        foreground.add(cairo, 8, 4, 2, 2);
        foreground.setHalignment(cairo, HPos.CENTER);

        Image sparta = new Image("resources/images/castle_yellow_bw.png");
        sparta.setPreserveRatio(true);
        sparta.setFitWidth(170);
        foreground.add(sparta, 3, 3, 2, 2);
        foreground.setHalignment(sparta, HPos.CENTER);

        Image rome = new Image("resources/images/castle_red_bw.png");
        rome.setPreserveRatio(true);
        rome.setFitWidth(170);
        foreground.add(rome, 0, 4, 2, 1);
        foreground.setHalignment(rome, HPos.CENTER);


        StackPane layout = new StackPane();
        layout.getChildren().addAll(map, foreground);

        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();


    }


}



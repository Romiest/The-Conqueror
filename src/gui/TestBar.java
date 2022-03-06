package gui;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import units.Army;

import java.io.*;
import java.util.*;

public class TestBar extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Rectangle box = new Rectangle();
        box.setHeight(300);
        box.setWidth(1000);
        box.setFill(Color.WHITE);
        box.setArcWidth(30.0);
        box.setArcHeight(20.0);

        Army army = new Army("Cairo");

        Label title = new Label("Army in " + army.getCurrentLocation());
        Label status = new Label("Status: " + army.getCurrentStatus());
        Label location = new Label("Current Location: " + army.getCurrentLocation());
        Label distanceToTarget = new Label("Distance to Target: " + army.getDistancetoTarget());
        Label target = new Label ("Target: " + army.getTarget());


        FlowPane info = new FlowPane();
        info.getChildren().addAll(title, status, location);
        if (!(army.getDistancetoTarget() == -1)) {
            info.getChildren().add(distanceToTarget);
            info.getChildren().add(target);
        }
        info.setAlignment(Pos.CENTER);
        info.setOrientation(Orientation.VERTICAL);
        info.setPrefWidth(500);
        info.setVgap(30);


        Button view = new Button("View Units");
        Button close = new Button("Close");
        VBox buttons = new VBox();
        buttons.getChildren().addAll(view, close);
        buttons.setAlignment(Pos.CENTER);
        buttons.setFillWidth(true);
        buttons.setSpacing(40);
        buttons.setPrefWidth(500);

        HBox foreground = new HBox();
        foreground.getChildren().addAll(info, buttons);
        foreground.setAlignment(Pos.TOP_CENTER);
        foreground.setFillHeight(true);

        StackPane bar = new StackPane();
        bar.getChildren().addAll(box, foreground);


        ModifiedGridPane layout = new ModifiedGridPane(10, 9);
        layout.add(bar, 2, 7, 6, 3);
        layout.setGridLinesVisible(true);
        layout.setHalignment(bar, HPos.CENTER);
//        layout.setValignment(bar, VPos.BOTTOM);
//        layout.setGridLinesVisible(true);


        Scene scene = new Scene(layout);


        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}



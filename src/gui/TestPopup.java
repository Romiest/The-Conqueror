package gui;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;


public class TestPopup extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox contents = new VBox();

        Image map = new Image("resources/images/world_map.png");
        map.setPreserveRatio(false);

        final DoubleProperty width = map.fitWidthProperty();
        final DoubleProperty height = map.fitHeightProperty();

        width.bind(Bindings.selectDouble(map.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(map.sceneProperty(), "height"));

        StackPane foreground = new StackPane();
        String titleText = "Title";
        Label title = new Label(titleText);
        int armyLength = 20;
        ArrayList<String> armies = new ArrayList<String>();
        armies.add("army a");
        armies.add("army b");
        armies.add("army c");

        StackPane layout = new StackPane();
//        PopupDialog pop = new PopupDialog("Army", armies, layout);
//        layout.getChildren().addAll(map, pop.getRoot());
//        pop.getChoice().addListener(new ChangeListener() {
//            @Override
//            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//                System.out.println(pop.getChoice().getValue());
//            }
//        });
        

        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();


    }

}



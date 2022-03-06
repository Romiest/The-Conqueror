package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Screen;



public class Test extends Application{

    public static void main(String [] args) {
        launch(args);
    }

    Stage window;
    double height;
    double width;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Rectangle2D dims = Screen.getPrimary().getBounds();
        height = dims.getHeight();
        width = dims.getWidth();

        window.setTitle("The Conqueror");
        window.setFullScreen(true);
        window.setFullScreenExitHint("");

        //Button
        MyButton btn = new MyButton("Test");
        btn.setOnAction(e -> {
            System.out.println(dims);
            System.out.println(window.getWidth() + " " + window.getHeight());
        });

        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            width = window.getWidth();
            height = window.getHeight();
            btn.setPrefSize(width*0.3, height*0.3);
        };

        window.widthProperty().addListener(stageSizeListener);
        window.heightProperty().addListener(stageSizeListener);

        MyButton btn2 = new MyButton("Fullscreen");
        btn2.setOnAction(e -> {
            window.setFullScreen(true);
        });
//        btn.setStyle("-fx-background-color: transparent;");
//        btn2.setStyle("-fx-background-color: transparent;");

        //Layout
        HBox layout = new HBox();
        layout.getChildren().addAll(btn, btn2);

        //Scene
        Scene scene = new Scene(layout, 500, 500);
        window.setScene(scene);
        window.show();



    }
}



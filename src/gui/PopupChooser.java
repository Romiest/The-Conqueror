package gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.util.*;

public class PopupChooser {

    private Parent mainLayout;
    private StringProperty choice = new SimpleStringProperty();

    public PopupChooser(String text, String choice1, String choice2, StackPane parent) {
        Image box = new Image("resources/images/horizontal_scroll.png");
        box.setFitHeight(300);
        box.setFitWidth(500);

        Label title = new Label(text);
        title.setStyle("-fx-font-size: 20");

        MyButton choice1Btn = new MyButton(choice1);
        MyButton choice2Btn = new MyButton(choice2);
        MyButton close = new MyButton("Close");

        choice1Btn.setOnAction(e -> {
            choice.setValue(choice1);
        });

        choice2Btn.setOnAction(e -> {
            choice.setValue(choice2);
        });

        close.setOnAction(e -> {
            parent.getChildren().remove(this.getRoot());
        });

        HBox choices = new HBox();
        VBox foreground = new VBox();

        foreground.setSpacing(30);
        choices.setAlignment(Pos.CENTER);
        choices.setSpacing(10);

        foreground.setAlignment(Pos.CENTER);

        choices.getChildren().addAll(choice1Btn, choice2Btn);
        foreground.getChildren().addAll(title, choices);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(box, foreground);

        mainLayout = layout;
    }





    public StringProperty getChoice() {
        return choice;
    }

    public Parent getRoot() {
        return mainLayout;
    }
}



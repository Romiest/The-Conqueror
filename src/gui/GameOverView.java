package gui;

import engine.City;
import engine.Game;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.io.*;
import java.util.*;

public class GameOverView extends View{
    Game game;
    Image display;
    int turns;
    ArrayList<City> citiesControlled;
    boolean win;


    public GameOverView(Controller controller) {
        game = controller.getGame();
        turns = game.getCurrentTurnCount();
        citiesControlled = game.getPlayer().getControlledCities();

        win = (turns <= game.getMaxTurnCount()) && (citiesControlled.size() == game.getAvailableCities().size());


        controller.setOtherMusic("resources/music/Flight Hymn_trimmed.mp3");
        controller.startOtherMusic();
        
        MyButton exit = new MyButton("Exit Game");
        exit.setOnAction(e -> {
            System.exit(0);
        });

        ModifiedGridPane foreground = new ModifiedGridPane(10, 9);
        foreground.setGridLinesVisible(controller.showGrid);

        VBox text = new VBox();
        text.setAlignment(Pos.CENTER);
        text.setSpacing(10);

        Label lost = new Label("Game Lost");
        lost.setTextFill(Color.WHITE);
        lost.setAlignment(Pos.CENTER);
        Label controlledCities = new Label("You controlled:");
        controlledCities.setTextFill(Color.WHITE);
        controlledCities.setAlignment(Pos.CENTER);
        for (City c : citiesControlled) {
            controlledCities.setText(controlledCities.getText() + "\n" + c.getName());
        }
        controlledCities.setTextAlignment(TextAlignment.CENTER);


        Label noOfTurns = new Label("It took you " + turns + " turns to conquer the world.");
        noOfTurns.setWrapText(true);
        noOfTurns.setAlignment(Pos.CENTER);
        noOfTurns.setTextFill(Color.WHITE);




        if (win) {
            display = new Image("resources/videos/win.gif");
            text.getChildren().add(noOfTurns);
        }
        else {
            display = new Image("resources/videos/lose.gif");
            text.getChildren().addAll(lost, controlledCities);
        }

        text.getChildren().add(exit);
        display.setPreserveRatio(false);


        final DoubleProperty width = display.fitWidthProperty();
        final DoubleProperty height = display.fitHeightProperty();

        width.bind(Bindings.selectDouble(display.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(display.sceneProperty(), "height"));

        foreground.add(text, 0, 5, 10, 4);
        foreground.setHalignment(text, HPos.CENTER);


        StackPane layout = new StackPane();
        layout.getChildren().addAll(display, foreground);


        mainLayout = layout;



    }

}



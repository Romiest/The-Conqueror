package gui.battle;

import gui.Image;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import units.Archer;
import units.Cavalry;
import units.Infantry;
import units.Unit;

import java.io.*;
import java.util.*;

public class UnitData {

    private Parent mainLayout;
    Unit unit;
    EventHandler eventy;
    String border = "-fx-border-color: red;\n" +
            "-fx-border-insets: 5;\n" +
            "-fx-border-width: 3;\n" +
            "-fx-border-style: solid;\n" +
            "-fx-border-radius: 15 15 0 15";
    final String noBorder = "-fx-border-color: transparent;\n";


    public UnitData(Unit unit, Pos pos, boolean mainPane) {
        Label level = new Label("Level: " + unit.getLevel());
        Label soldierCount = new Label(unit.getCurrentSoldierCount() + " Soldiers");
        Label type = new Label(unit.getClass().getSimpleName());
        level.setTextAlignment(TextAlignment.CENTER);
        soldierCount.setTextAlignment(TextAlignment.CENTER);



        Image unitIcon = null;

        if (mainPane) {
            if (unit instanceof Archer) {
                unitIcon = new Image("resources/images/archer_banner_friendly.png");
            } else if (unit instanceof Infantry) {
                unitIcon = new Image("resources/images/infantry_banner_friendly.png");
            } else if (unit instanceof Cavalry) {
                unitIcon = new Image("resources/images/cavalry_banner_friendly.png");
            }
        } else {
            if (unit instanceof Archer) {
                unitIcon = new Image("resources/images/archer_banner.png");
            } else if (unit instanceof Infantry) {
                unitIcon = new Image("resources/images/infantry_banner.png");
            } else if (unit instanceof Cavalry) {
                unitIcon = new Image("resources/images/cavalry_banner.png");
            }
        }
        unitIcon.setFitWidth(70);
        unitIcon.setPreserveRatio(true);

//        unitIcon.onMouseClicked(e -> eventy.handle(e));



        VBox info = new VBox();
        info.getChildren().addAll(type, level, soldierCount);
        info.setAlignment(pos);
        info.setFillWidth(false);


        BorderPane layout = new BorderPane();



        VBox container = new VBox();
        container.setSpacing(10);
        container.setAlignment(pos);

        container.getChildren().addAll(unitIcon, info);
        container.setFillWidth(false);



        container.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> eventy.handle(e));

        layout.setCenter(container);


        container.setStyle(noBorder);

        layout.setMaxWidth(100);

        mainLayout = layout;
    }

    public void setEvent(EventHandler event) {
        eventy = event;
    }

    public Parent getRoot() {
        return mainLayout;
    }

    public Unit getUnit() {
        return unit;
    }

    public void highlight() {
        mainLayout.setStyle(border);
    }

    public void noHighlight() {
        mainLayout.setStyle(noBorder);
    }

}



package gui.battle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.util.Duration;
import units.Unit;

import java.io.*;
import java.util.*;

public class UnitsPane {
    Parent mainLayout;
    EventHandler event;
    Unit choice;
    ArrayList<Unit> units;
    Pos pos;
    ArrayList<UnitData> unitsData;
    boolean mainPane = false;

    public UnitsPane(ArrayList<Unit> units, Pos alignment, boolean mainPane) {
        this.units = units;
        pos = alignment;
        VBox unitsPane = new VBox();
//        unitsPane.setColumnHalignment(HPos.LEFT);
//        unitsPane.setOrientation(Orientation.VERTICAL);
        ArrayList<UnitData> unitsData = new ArrayList<UnitData>();
        for (int i = 0; i < units.size(); i++) {
            Unit value = units.get(i);
            UnitData unitData = new UnitData(value, alignment, mainPane);
            unitsPane.getChildren().add(unitData.getRoot());
            unitsData.add(unitData);
            int finalI = i;
            unitData.setEvent(e -> {
                if (mainPane) {
                    for (int j = 0; j < unitsData.size(); j++) {
                        if (unitsData.get(j) != unitData) {
                            unitsData.get(j).noHighlight();
                        } else {
                            unitsData.get(j).highlight();
                        }
                    }
                }
//                System.out.println(value);
                choice = value;
//                System.out.println(choice);
                event.handle(e);
            });
        }

        unitsPane.setAlignment(Pos.CENTER);
        unitsPane.setSpacing(30);
        StackPane holder = new StackPane(unitsPane);
        holder.setAlignment(Pos.CENTER);


        ScrollPane scrollable = new ScrollPane();
        scrollable.setContent(holder);

        holder.minWidthProperty().bind(Bindings.createDoubleBinding(() ->
                scrollable.getViewportBounds().getWidth(), scrollable.viewportBoundsProperty()));

        scrollable.setStyle("-fx-background-color:transparent; -fx-background: transparent;");

        VBox layout = new VBox();
        layout.getChildren().add(scrollable);
        layout.setAlignment(Pos.CENTER);
        layout.setFillWidth(true);

        mainLayout = layout;
    }

    public Parent getRoot() {
        return mainLayout;
    }

    public Unit getChoice() {
        return choice;
    }

    public void onChoice(EventHandler event) {
        this.event = event;
    }


    public void setMainPane(boolean main) {
        mainPane = main;
    }




}



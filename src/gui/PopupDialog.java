package gui;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import units.*;

import java.io.*;
import java.util.*;

public class PopupDialog {

    Parent mainLayout;
    IntegerProperty choice;
    EventHandler event;
    Army chosenArmy;
    Unit chosenUnit;


    public PopupDialog(String text, ArrayList elements, StackPane parent, View parentView) {
        choice = new SimpleIntegerProperty(-1);
        StackPane layout = new StackPane();
        VBox foreground = new VBox();
        foreground.setSpacing(60);
        Label title = new Label("Choose " + text);
        title.setStyle("-fx-font-size: 40");
        MyButton confirm = new MyButton("Confirm " + text);
        ChoiceBox<String> dropDown = new ChoiceBox<String>();

        Image box = new Image("resources/images/Scroll.png");
        box.setFitWidth(400);
        box.setPreserveRatio(true);
//        box.setHeight(200);
//        box.setWidth(250);
//        box.setFill(Color.DARKGOLDENROD);
//        box.setArcWidth(30.0);
//        box.setArcHeight(20.0);


        if (elements.size() > 0) {
            dropDown = new ChoiceBox<String>();
            int length = elements.size();
            ArrayList<String> contents = new ArrayList<String>();
            if (text.equals("Army")) {
                for (int i = 1; i <= length; i++) {
                    String inOn = "in";
                    Army army = (Army) elements.get(i-1);
                    String loc = army.getCurrentLocation();
                    if (army.getCurrentLocation().equals("Road")) {
                        inOn = "marching to";
                        loc = army.getTarget();
                    }
                    if (parentView instanceof CityView) {
                        if (i == 1&& !((CityView)parentView).getController().getGame().getPlayer().getControlledArmies().contains(army)) {
                            contents.add(i+". Defending Army of " + ((CityView) parentView).getCityName());
                            continue;
                        }
                    }
                    contents.add(i + ". " + "Army " + army.getId() + " " + inOn  + " " + loc);
                }
            } else if (text.equals("Unit")) {
                for (int i = 1; i <= length; i++) {
                    String type = "Unknown";
                    if (elements.get(i-1) instanceof Archer) {
                        type = "Archer";
                    } else if (elements.get(i-1) instanceof Cavalry) {
                        type = "Cavalry";
                    } else if (elements.get(i-1) instanceof Infantry) {
                        type = "Infantry";
                    }
                    contents.add(i + ". " + type + " Level " + ((Unit)elements.get(i-1)).getLevel());
                }
            }


            dropDown.getItems().addAll(contents);

            foreground.getChildren().addAll(title, dropDown, confirm);
        } else {
            Label noItems = new Label("No items available!");
            confirm = new MyButton("Close");
            foreground.getChildren().addAll(title, noItems, confirm);
        }

        Pane invis = new Pane();
        invis.setMinHeight(10);
        foreground.getChildren().addAll(invis);
        foreground.setAlignment(Pos.CENTER);


        layout.getChildren().addAll(box, foreground);
        mainLayout = layout;

        ChoiceBox<String> finalDropDown = dropDown;
        confirm.setOnAction(e -> {
            if (finalDropDown.getItems().size() == 0 || finalDropDown.getValue() == null)
                parent.getChildren().remove(mainLayout);
            else if (finalDropDown.getItems().size() > 0) {
                String[] split = finalDropDown.getValue().split("\\.");
                choice.setValue(Integer.parseInt(split[0])-1);
                Object o = elements.get(choice.getValue());
                if (o instanceof Unit) {
                    chosenUnit = (Unit) o;
                    if (event == null) {
                        BarPane2 bar = new BarPane2(chosenUnit, parent, parentView);
                        bar.openPane();
                    } else {
                        event.handle(e);
                    }


                } else if (o instanceof Army) {
                    chosenArmy = (Army) o;
                    if (event == null) {
                    	BarPane2 bar = new BarPane2(chosenArmy,  parentView,parent);
                        bar.openPane();
                    } else {
                    	event.handle(e);
                    }

                }
                parent.getChildren().remove(mainLayout);
            }
        });


    }


    public Army getChosenArmy() {
        return chosenArmy;
    }
    public Unit getChosenUnit() {
        return chosenUnit;
    }

    public Parent getRoot() {
        return mainLayout;
    }


    public IntegerProperty getChoice() {
        return choice;
    }

    
    public void onConfirm(EventHandler e) {
    	event = e;
    }


}





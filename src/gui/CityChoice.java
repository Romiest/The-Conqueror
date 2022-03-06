package gui;

import engine.Game;
import exceptions.MaxCapacityException;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import units.Archer;
import units.Army;
import units.Status;

import java.io.*;
import java.util.*;

public class CityChoice {


    VBox Choice;


    public CityChoice(String path, String bw, String name, Controller controller) throws FileNotFoundException {
        Choice = new VBox();
        Image imageView = new Image(bw);
        imageView.setPreserveRatio(true);

        imageView.onMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                imageView.setImage(path);

            }
        });

        imageView.onMouseExited(event -> {

            imageView.setImage(bw);

        });

        imageView.onMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Parent nextScene = null;

                try {
                    controller.setGame(new Game(controller.getName(), name));
                    controller.getGame().getPlayer().setTreasury(5000);
                    //START TEST TODO: Testing
//                    Army test1 = new Army("Cairo");
//                    Army test2 = new Army("Cairo");
//                    Army test3 = new Army("Road");
//                    Archer a1 = new Archer(1,60,90, 3,9);
//                    try {
//                        test1.relocateUnit(a1);
//                    } catch (MaxCapacityException e) {
//                        System.out.println("Max Capacity");
//                    }
//                    test3.setCurrentStatus(Status.MARCHING);
//                    test3.setTarget("Sparta");
//
//                    ArrayList<Army> test = controller.getGame().getPlayer().getControlledArmies();
//                    test.add(test1);
//                    test.add(test2);
//                    test.add(test3);
                    //END TEST
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(controller.getGame().getPlayer().getName() + name);
                nextScene = new WorldMapView(controller).getRoot();

                controller.changeScene(nextScene);
            }
        });



        Label text = new Label(name);
        text.setStyle("-fx-font-size: 20; -fx-background-color: white;");
        text.setPrefWidth(576);
        text.setAlignment(Pos.CENTER);
        Choice.getChildren().addAll(imageView, text);
        Choice.setAlignment(Pos.CENTER);



    }
    public VBox getChoice() {
        return Choice;
    }



}



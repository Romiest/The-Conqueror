package gui;

import engine.City;
import engine.Game;
import engine.Player;
import exceptions.MaxCapacityException;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import units.Archer;
import units.Army;
import units.Status;

import java.io.*;
import java.util.*;

public class WorldMapView extends View {




    public WorldMapView(Controller controller) {

        Game game = controller.getGame();
        this.controller = controller;
        Stage window = controller.getWindow();
        Player player = game.getPlayer();
        boolean cairoFound = false;
        boolean spartaFound = false;
        boolean romeFound = false;


        for (City c : game.getPlayer().getControlledCities()) {
            String name = c.getName();
            if (name.equals("Cairo"))
                cairoFound = true;
            if (name.equals("Sparta"))
                spartaFound = true;
            if (name.equals("Rome"))
                romeFound = true;
             
        }
        final boolean cairoFF=cairoFound;
        final boolean spartaFF=spartaFound;
        final boolean romeFF=romeFound;



        ModifiedGridPane foreground = new ModifiedGridPane(10, 9);
        foreground.setGridLinesVisible(controller.showGrid);

        popUpBase = new StackPane ();
        popUpBase.getChildren().addAll(foreground);

        Image map = new Image("resources/images/worldmap2.png");
        map.setPreserveRatio(false);
         

        final DoubleProperty width = map.fitWidthProperty();
        final DoubleProperty height = map.fitHeightProperty();

        width.bind(Bindings.selectDouble(map.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(map.sceneProperty(), "height"));


//        System.out.println("Player controlled: " + player.getControlledCities().size());
//        System.out.println("Available-1: " + (game.amamygetAvailableCities().size() -1));
        if (player.getControlledCities().size() == game.getAvailableCities().size() -1) {
            controller.setOtherMusic("resources/music/Warriors to the End.mp3");
            System.out.println("Changed music");
        }




        City cairoCity = null;
        City spartaCity = null;
        City romeCity= null;



        for (City c : game.getAvailableCities()) {
            if (c.getName().equals("Rome")) {
                romeCity = c;
            } else if (c.getName().equals("Cairo")) {
                cairoCity = c;
            } else if (c.getName().equals("Sparta")) {
                spartaCity = c;
            }
        }

        Label cairoTurnsUnderSiege = new Label(cairoCity.getTurnsUnderSiege() + " turns under siege");
        cairoTurnsUnderSiege.setTextFill(Color.WHITE);
        cairoTurnsUnderSiege.setTextAlignment(TextAlignment.CENTER);
        Label romeTurnsUnderSiege = new Label(romeCity.getTurnsUnderSiege() + " turns under siege");
        romeTurnsUnderSiege.setTextFill(Color.WHITE);
        romeTurnsUnderSiege.setTextAlignment(TextAlignment.CENTER);
        Label spartaTurnsUnderSiege = new Label(spartaCity.getTurnsUnderSiege() + " turns under siege");
        spartaTurnsUnderSiege.setTextFill(Color.WHITE);
        spartaTurnsUnderSiege.setTextAlignment(TextAlignment.CENTER);

        //Loading city cairo
        Image cairo;
        if (cairoFound)
            cairo = new Image("resources/images/castle_blue.png");
        else
            cairo = new Image("resources/images/castle_blue_bw.png");
        cairo.setPreserveRatio(true);
        cairo.setFitWidth(350);
        foreground.add(cairo, 7, 4, 2, 2);
        if (cairoCity.isUnderSiege()) {
            foreground.add(cairoTurnsUnderSiege, 6, 5, 2, 2);
        }
        foreground.setHalignment(cairoTurnsUnderSiege, HPos.CENTER);
        foreground.setHalignment(cairo, HPos.CENTER);
        
       
        cairo.onMouseClicked(e -> {
            	if(cairoFF) {
            	
                Parent nextScene = null;
                CityView c;
                try {
                	 c=new CityView(controller,"Cairo");
                	 nextScene=c.getRoot();
                }
                catch (FileNotFoundException e2){
                	System.out.println("abc");
                }
                controller.changeScene(nextScene);
                } else {
                    ArrayList<Army> cairoArmies = getLocalArmies("Cairo");
                    PopupDialog popUpCairo = new PopupDialog ("Army", cairoArmies, popUpBase, this);
                    popUpBase.getChildren().add(popUpCairo.getRoot());
                }
            });



        //Loading city sparta
        Image sparta;
        if (spartaFound)
            sparta = new Image("resources/images/castle_yellow.png");
        else
            sparta = new Image("resources/images/castle_yellow_bw.png");
        sparta.setPreserveRatio(true);
        sparta.setFitWidth(250);
        foreground.add(sparta, 1, 2, 2, 2);
        if (spartaCity.isUnderSiege()) {
            foreground.add(spartaTurnsUnderSiege, 0, 2, 2, 2);
        }
        foreground.setHalignment(spartaTurnsUnderSiege, HPos.CENTER);
        foreground.setHalignment(sparta, HPos.CENTER);
        
        
        sparta.onMouseClicked(e -> {
            	if (spartaFF) {

                Parent nextScene = null;
                CityView c;
                try {
                	 c=new CityView(controller,"Sparta");
                	 nextScene=c.getRoot();
                }
                catch (FileNotFoundException e4){
                	System.out.println("abc");
                }
                controller.changeScene(nextScene);
            } else {
                    ArrayList<Army> spartaArmies = getLocalArmies("Sparta");
                    PopupDialog popUpSparta = new PopupDialog ("Army", spartaArmies, popUpBase, this);
                    popUpBase.getChildren().add(popUpSparta.getRoot());
                }
            });



        //Loading city rome
        Image rome;
        if (romeFound)
            rome = new Image("resources/images/castle_red.png");
        else
            rome = new Image("resources/images/castle_red_bw.png");
        rome.setPreserveRatio(true);
        rome.setFitWidth(250);
        foreground.add(rome, 0, 4, 2, 2);
        if (romeCity.isUnderSiege()) {
            foreground.add(romeTurnsUnderSiege, 0, 5, 2, 2);
        }
        foreground.setHalignment(romeTurnsUnderSiege, HPos.CENTER);
        foreground.setHalignment(rome, HPos.CENTER);
        
        
        
        
        rome.onMouseClicked(e -> {
            	if(romeFF) {
            	
            	
                Parent nextScene = null;
                CityView c;
                try {
                	 c=new CityView(controller,"Rome");
                	 nextScene=c.getRoot();
                }
                catch (FileNotFoundException e2){
                	System.out.println("abc");
                }
                controller.changeScene(nextScene);
                } else {
            	    ArrayList<Army> romeArmies = getLocalArmies("Rome");
            	    PopupDialog popUpRome = new PopupDialog ("Army", romeArmies, popUpBase, this);
                    popUpBase.getChildren().add(popUpRome.getRoot());
                }
            });

        
        
        // left bar info
        Image info = new Image("resources/images/Info Bar.png");
        
        info.setFitWidth(350);
        info.setPreserveRatio(true);
        StackPane stackL = new StackPane();
        VBox leftBox = new VBox();
        
        Label name = new Label();
        
        name.setText(player.getName());
        name.setStyle("-fx-text-fill: white;-fx-font-size:25");
        Label turns = new Label("Current Turn: "+game.getCurrentTurnCount());
        leftBox.getChildren().addAll(name,turns);
        turns.setStyle("-fx-text-fill: white;-fx-font-size:18");
        stackL.getChildren().addAll(info,leftBox);
        
        leftBox.setAlignment(Pos.CENTER);
        
        
        
        foreground.add(stackL, 0, 0,2,1);
        stackL.setAlignment(leftBox, Pos.CENTER);
        stackL.setAlignment(info, Pos.CENTER);
//        stackL.setAlignment(Pos.TOP_LEFT);
//        foreground.setHalignment(info, HPos.LEFT);
//        foreground.setValignment(info, VPos.TOP);
//        foreground.setHalignment(leftBox, HPos.LEFT);
//        foreground.setValignment(leftBox, VPos.TOP);
        foreground.setHalignment(stackL,HPos.LEFT);
        foreground.setValignment(stackL, VPos.TOP);
        
        
        Image info1 = new Image("resources/images/Info Bar.png");
        
        info1.setFitWidth(350);
        info1.setPreserveRatio(true);
        StackPane stackR = new StackPane();
        VBox rBox = new VBox();
        
        Label gold = new Label();
        
        gold.setText("Gold: "+((int) player.getTreasury()));
        gold.setStyle("-fx-text-fill: white;-fx-font-size:18");
        Label food = new Label("Food: "+((int) player.getFood()));
        rBox.getChildren().addAll(gold,food);
        food.setStyle("-fx-text-fill: white;-fx-font-size:18");
        stackR.getChildren().addAll(info1,rBox);
        
        rBox.setAlignment(Pos.CENTER);
        foreground.add(stackR, 8, 0,2,1);

        //Idle Controlled Armies
        MyButton idleBtn = new MyButton("Idle Armies");
        foreground.add(idleBtn, 3, 8, 2, 1);
        foreground.setHalignment(idleBtn, HPos.CENTER);
        ArrayList<Army> controlledArmies = controller.getGame().getPlayer().getControlledArmies();
        ArrayList<Army> idleArmies = new ArrayList<Army>();


        //Marching Armies
        MyButton marchingBtn = new MyButton ("Marching Armies");
        foreground.add(marchingBtn, 4, 8, 2, 1);
        foreground.setHalignment(marchingBtn, HPos.CENTER);
        ArrayList<Army> marchingArmies = new ArrayList<Army>();

        //Besieging Armies
        MyButton besiegingBtn = new MyButton("Besieging Armies");
        foreground.add(besiegingBtn, 5, 8, 2, 1);
        foreground.setHalignment(besiegingBtn, HPos.CENTER);

        ArrayList<Army> besiegingArmies = new ArrayList<Army>();


        //Setting each army
        for (Army a : controlledArmies) {
            if (a.getCurrentStatus() == Status.MARCHING) {
                marchingArmies.add(a);
            } else if (a.getCurrentStatus() == Status.IDLE) {
                idleArmies.add(a);
            } else if (a.getCurrentStatus() == Status.BESIEGING) {
                besiegingArmies.add(a);
            }
        }


        PopupDialog popUpIdle = new PopupDialog ("Army", idleArmies, popUpBase, this);
        PopupDialog popUpMarching = new PopupDialog("Army", marchingArmies, popUpBase, this);
        PopupDialog popUpBesieging = new PopupDialog("Army", besiegingArmies, popUpBase, this);


        //Listeners for buttons that display the popup
        idleBtn.setOnAction(e -> {
            popUpBase.getChildren().add(popUpIdle.getRoot());
        });
        marchingBtn.setOnAction(e -> {
            popUpBase.getChildren().add(popUpMarching.getRoot());
        });
        besiegingBtn.setOnAction(e -> {
            popUpBase.getChildren().add(popUpBesieging.getRoot());
        });




        stackR.setAlignment(rBox, Pos.CENTER);
        stackR.setAlignment(info1, Pos.CENTER);
//        stackL.setAlignment(Pos.TOP_LEFT);
//        foreground.setHalignment(info, HPos.LEFT);
//        foreground.setValignment(info, VPos.TOP);
//        foreground.setHalignment(leftBox, HPos.LEFT);
//        foreground.setValignment(leftBox, VPos.TOP);
        foreground.setHalignment(stackR,HPos.RIGHT);
        foreground.setValignment(stackR, VPos.TOP);

        MyButton endTurn = new EndTurnButton(game, this).getButton();
        foreground.add(endTurn, 9, 8);
        foreground.setHalignment(endTurn, HPos.CENTER);
        


        StackPane layout = new StackPane();

        layout.getChildren().addAll(map, popUpBase);

        mainLayout = layout;
    }


    public ArrayList<Army> getLocalArmies(String cityName) {
        ArrayList<Army> res = new ArrayList<Army>();
        for (Army a : controller.getGame().getPlayer().getControlledArmies()) {
            if (a.getCurrentLocation().equals(cityName)) {
                res.add(a);
            }
        }
        return res;
    }


    public Controller getController() {
        return controller;
    }

}



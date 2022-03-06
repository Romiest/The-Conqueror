package gui;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

import engine.Game;


public class TestCityView extends Application{
	int barracksLevel=0;
	int archeryRangeLevel=0;
	int stableLevel=0;
	int farmLevel=0;
	int marketLevel=0;
	Parent layout;
			
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ModifiedGridPane foreground = new ModifiedGridPane(10, 9);
        foreground.setGridLinesVisible(true);

        Image map = new Image("resources/images/world_map.png");
        map.setPreserveRatio(false);

        final DoubleProperty width = map.fitWidthProperty();
        final DoubleProperty height = map.fitHeightProperty();

        width.bind(Bindings.selectDouble(map.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(map.sceneProperty(), "height"));
        
        
        

        Image barracksImg ;
        if (barracksLevel==0) {
        	barracksImg=new Image("resources/images/castle_yellow_bw.png");
        }
        else {
        	barracksImg=new Image("resources/images/castle_yellow.png");
        }
        barracksImg.setPreserveRatio(true);
        barracksImg.setFitWidth(170);
        foreground.add(barracksImg, 6, 2, 2, 2);
        foreground.setHalignment(barracksImg, HPos.CENTER);
        
        
        
        
        
        Image marketImg ;
        if (marketLevel==0) {
        	marketImg=new Image("resources/images/castle_yellow_bw.png");
        }
        else {
        	marketImg=new Image("resources/images/castle_yellow.png");
        }
        

        marketImg = new Image("resources/images/castle_yellow_bw.png");
        marketImg.setPreserveRatio(true);
        marketImg.setFitWidth(170);
        foreground.add(marketImg, 8, 1, 2, 2);
        foreground.setHalignment(marketImg, HPos.CENTER);
        

        
        
        Image farmImg ;
        if (farmLevel==0) {
        	farmImg=new Image("resources/images/castle_yellow_bw.png");
        }
        else {
        	farmImg=new Image("resources/images/castle_yellow.png");
        }
        
        
        
        

        farmImg  = new Image("resources/images/castle_red_bw.png");
        farmImg.setPreserveRatio(true);
        farmImg.setFitWidth(170);
        foreground.add(farmImg, 0, 1, 2, 2);
        foreground.setHalignment(farmImg, HPos.CENTER);
        
        
        
        
        
        Image stableImg ;
        if (stableLevel==0) {
        	stableImg=new Image("resources/images/castle_yellow_bw.png");
        }
        else {
        	stableImg=new Image("resources/images/castle_yellow.png");
        }
        stableImg.setPreserveRatio(true);
        stableImg.setFitWidth(170);
        foreground.add(stableImg, 2, 2, 2, 2);
        foreground.setHalignment(stableImg, HPos.CENTER);
        
        
        
        
        Image archeryRangeImg ;
        if (archeryRangeLevel==0) {
        	archeryRangeImg=new Image("resources/images/castle_yellow_bw.png");
        }
        else {
        	archeryRangeImg=new Image("resources/images/castle_yellow.png");
        }
        archeryRangeImg.setPreserveRatio(true);
        archeryRangeImg.setFitWidth(170);
        foreground.add(archeryRangeImg, 4, 2, 2, 2);
        foreground.setHalignment(archeryRangeImg, HPos.CENTER);
        
        

        
        Image flag= new Image("resources/images/banner.png");
        flag.setPreserveRatio(true);
        flag.setFitWidth(50);
        foreground.add(flag, 4, 3, 2, 2);
        foreground.setHalignment(flag, HPos.CENTER);


        StackPane layout = new StackPane();
        
        
        
        
        
        
        layout.getChildren().addAll(map, foreground);

        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

    }

}



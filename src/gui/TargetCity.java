package gui;

import engine.City;
import engine.Game;
import engine.Player;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import units.Army;

public class TargetCity {

    Parent mainLayout;

    public TargetCity(Army army, Controller controller) {
        Game game = controller.getGame();
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


        ModifiedGridPane foreground = new ModifiedGridPane(10, 9);
        foreground.setGridLinesVisible(controller.showGrid);
        Label chooseCity = new Label("Choose Target City");
        MyButton cancel = new MyButton("Cancel");

        Image map = new Image("resources/images/worldmap2.png");
        map.setPreserveRatio(false);


        final DoubleProperty width = map.fitWidthProperty();
        final DoubleProperty height = map.fitHeightProperty();

        width.bind(Bindings.selectDouble(map.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(map.sceneProperty(), "height"));

        //Loading city cairo
        Image cairo;
        if (!cairoFound) {
            cairo = new Image("resources/images/castle_blue_bw.png");
            cairo.setPreserveRatio(true);
            cairo.setFitWidth(350);
            foreground.add(cairo, 7, 4, 2, 2);
            foreground.setHalignment(cairo, HPos.CENTER);


            cairo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    controller.getGame().targetCity(army, "Cairo");
                    System.out.println("Cairo");
                    controller.changeScene(new WorldMapView(controller).getRoot());
                }
            });
        }


        //Loading city sparta
        Image sparta;
        if (!spartaFound) {
            sparta = new Image("resources/images/castle_yellow_bw.png");
            sparta.setPreserveRatio(true);
            sparta.setFitWidth(250);
            foreground.add(sparta, 1, 2, 2, 2);
            foreground.setHalignment(sparta, HPos.CENTER);


            sparta.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    controller.getGame().targetCity(army, "Sparta");
                    System.out.println("Sparta");
                    controller.changeScene(new WorldMapView(controller).getRoot());
                }

            });
        }


        //Loading city rome
        Image rome;
        if (!romeFound) {
            rome = new Image("resources/images/castle_red_bw.png");
            rome.setPreserveRatio(true);
            rome.setFitWidth(250);
            foreground.add(rome, 0, 4, 2, 2);
            foreground.setHalignment(rome, HPos.CENTER);


            rome.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    controller.getGame().targetCity(army, "Rome");
                    System.out.println("Rome");
                    controller.changeScene(new WorldMapView(controller).getRoot());
                }

            });
        }

        cancel.setOnAction(e -> {
            controller.changeScene(new WorldMapView(controller).getRoot());
        });

        foreground.add(cancel, 9, 8);

        StackPane layout = new StackPane();

        layout.getChildren().addAll(map, foreground);

        mainLayout = layout;


    }

    public Parent getRoot() {
        return mainLayout;
    }

}



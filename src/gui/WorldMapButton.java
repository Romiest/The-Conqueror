package gui;

import javafx.scene.control.Button;

public class WorldMapButton extends MyButton {
    public WorldMapButton(Controller controller) {
        super("Back");
        this.setOnAction(e -> {
            WorldMapView newMap = new WorldMapView(controller);
            controller.changeScene(newMap.getRoot());
        });
    }
}

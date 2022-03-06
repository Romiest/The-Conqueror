package gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.*;

public class Image extends ImageView {
    public Image(String path) {
        super();
        InputStream stream = null;
        try {
            stream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found at " + path);
        }
        javafx.scene.image.Image image = new javafx.scene.image.Image(stream);
        //Creating the image view
        //Setting image to the image view
        this.setImage(image);
        //Setting the image view parameters
//        final DoubleProperty width = this.fitWidthProperty();
//        final DoubleProperty height = this.fitHeightProperty();
//
//        width.bind(Bindings.selectDouble(this.sceneProperty(), "width"));
//        height.bind(Bindings.selectDouble(this.sceneProperty(), "height"));
    }

    public void setImage(String path) {
        InputStream stream = null;
        try {
            stream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found at " + path);
        }
        javafx.scene.image.Image image = new javafx.scene.image.Image(stream);
        //Creating the image view
        //Setting image to the image view
        this.setImage(image);
    }

    public void onMouseClicked(EventHandler<MouseEvent> event) {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event);
    }

    public void onMouseEntered(EventHandler<MouseEvent> event) {
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, event);
    }

    public void onMouseExited(EventHandler<MouseEvent> event) {
        this.addEventHandler(MouseEvent.MOUSE_EXITED, event);
    }
}



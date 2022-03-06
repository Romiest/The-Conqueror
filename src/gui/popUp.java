package gui;

import java.util.Stack;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class popUp {
	Parent layout;
	EventHandler event;


	public popUp(String title,String message,StackPane c) {
		 StackPane layout = new StackPane();
		 VBox foreground = new VBox();
		 foreground.setSpacing(60);
		 Label titleL = new Label( title);
		 titleL.setStyle("-fx-font-size:30");
		 Label messageL= new Label(message);
		 messageL.setTextAlignment(TextAlignment.CENTER);
        MyButton confirm = new MyButton("Confirm ");
		 confirm.setOnAction(e->{
		     c.getChildren().remove(layout);
		     if (event != null ) {
		         event.handle(e);
		     }
		 });


		 //ChoiceBox<String> dropDown = new ChoiceBox<String>();
		 Image box = new Image("resources/images/Scroll.png");
		 box.setFitWidth(400);
		 box.setPreserveRatio(true);

        Pane invis = new Pane();
        invis.setMinHeight(10);

        foreground.getChildren().addAll(titleL,messageL,confirm, invis);
        layout.getChildren().addAll(box,foreground);
        foreground.setAlignment(Pos.CENTER);



        this.layout=layout;
	}
	public Parent getRoot() {
		return layout;
	}

	public void onConfirm(EventHandler e) {
	    event = e;
    }


}

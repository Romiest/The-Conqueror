//package gui;
//
//import buildings.ArcheryRange;
//import buildings.Barracks;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import buildings.*;
//import engine.City;
//import engine.Game;
//import engine.Player;
//import exceptions.BuildingInCoolDownException;
//import exceptions.BuildingLimitException;
//import exceptions.MaxRecruitedException;
//import exceptions.NotEnoughGoldException;
//import javafx.geometry.HPos;
//import javafx.geometry.Orientation;
//import javafx.geometry.Pos;
//import javafx.scene.Parent;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//import units.Army;
//
//
//public class BarPane {
//    Parent mainLayout;
//
//    String bname;
//    int blevel;
//    Game game;
//    Player player;
//    CityView cv;
//    Building a;
//
//
//
//    public BarPane(City city, String bname, int blevel, Game game, Player player, CityView cv) {
//
//
//        Rectangle box = new Rectangle();
//        box.setHeight(300);
//        box.setWidth(1000);
//        box.setFill(Color.WHITE);
//        box.setArcWidth(30.0);
//        box.setArcHeight(20.0);
//        this.game=game;
//        this.player=player;
//
//
//        FlowPane info = new FlowPane();
//        //	        info.getChildren().addAll(title, status, location);
//        //	        if (!(army.getDistancetoTarget() == -1)) {
//        //	            info.getChildren().add(distanceToTarget);
//        //	            info.getChildren().add(target);
//        //	        }
//        info.setAlignment(Pos.CENTER);
//        info.setOrientation(Orientation.VERTICAL);
//        info.setPrefWidth(500);
//        info.setVgap(30);
//
//        //
//        //	        Button view = new Button("View Units");
//        //	        Button close = new Button("Close");
//        VBox buttons = new VBox();
//        //	        buttons.getChildren().addAll(view, close);
//        buttons.setAlignment(Pos.CENTER);
//        buttons.setFillWidth(true);
//        buttons.setSpacing(40);
//        buttons.setPrefWidth(500);
//
//        HBox foreground = new HBox();
//        foreground.getChildren().addAll(info, buttons);
//        foreground.setAlignment(Pos.TOP_CENTER);
//        foreground.setFillHeight(true);
//
//        StackPane bar = new StackPane();
//        bar.getChildren().addAll(box, foreground);
//
//
//        Label cost ;
//        Label upgradecost;
//        Label recruitcost;
//
//
//
//
//        //butttons
//
//
//         Button Build = new Button("Build");
//         Build.setOnAction(e ->{
//             try {
//                 cv.build(city,bname);
//             }
//             catch(FileNotFoundException f){
//                 System.out.println("xxxx");
//             }
//             catch(NotEnoughGoldException x) {
//                 //TODO
//             }
//             catch(BuildingLimitException m) {
//                 //TODO
//             }
//
//
//         });
//         Button Recruit = new Button("Recruit");
//            Recruit.setOnAction(e ->{
//                 try {
//                     player.recruitUnit(city.getName(),bname);
//                 }
//                 catch(FileNotFoundException f){
//                     System.out.println("xxxx");
//                 }
//                 catch(IOException m) {
//                     //TODO:exception
//                 }
//                 catch(BuildingInCoolDownException m) {
//                     //TODO:exception
//                 }
//                 catch(NotEnoughGoldException m) {
//                     //TODO:exception
//                 }
//                 catch(MaxRecruitedException m) {
//                     //TODO:exception
//                 }
//
//
//             });
//             Button Upgrade =new Button("Upgrade") ;
//             Upgrade.setOnAction(e->{
//                    cv.upgrade(bname);
//
//             });
//
//
//
//
//
//
//         if(blevel==0) {
//             if(bname.equals("ArcheryRange")) {
//                 ArcheryRange a = new ArcheryRange();
//                 cost=new Label("The Cost of new "+bname+" is "+a.getCost());
//             }
//             if(bname.equals("Stable")) {
//                 Stable a= new Stable();
//                 cost=new Label("The Cost of new "+bname+" is "+a.getCost());
//
//             }
//             if(bname.equals("Barracks")){
//                 Barracks a= new Barracks();
//                 cost=new Label("The Cost of new "+bname+" is "+a.getCost());
//             }
//             if(bname.equals("Farm")){
//                 Farm a= new Farm();
//                 cost=new Label("The Cost of new "+bname+" is "+a.getCost());
//             }
//             if(bname.equals("Market")){
//                 Market a= new Market();
//                 cost=new Label("The Cost of new "+bname+" is "+a.getCost());
//             }
//             //TODO: display cost label and build  button
//
//
//
//
//
//
//         }else{
//
//
//
//             if(bname.equals("ArcheryRange")) {
//                 a = cv.archeryRange;
//
//                 upgradecost=new Label("The Cost of new "+bname+" is "+a.getUpgradeCost());
//                 recruitcost=new Label("The Cost of new "+bname+" is "+a.getRecruitmentCost());
//
//             }
//             if(bname.equals("Stable")) {
//                 a= cv.stable;
//                 a.setLevel(blevel);
//                 upgradecost=new Label("The Cost of new "+bname+" is "+a.getUpgradeCost());
//                 recruitcost=new Label("The Cost of new "+bname+" is "+a.getRecruitmentCost());
//             }
//
//             if(bname.equals("Barracks")){
//                 a= cv.barracks;
//                 a.setLevel(blevel);
//                 upgradecost=new Label("The Cost of new "+bname+" is "+a.getUpgradeCost());
//                 recruitcost=new Label("The Cost of new "+bname+" is "+a.getRecruitmentCost());
//
//             }
//
//             if (bname.equals("Farm") || bname.equals("Market"))
//                if(bname.equals("Farm")){
//                     a = cv.farm;
//                     a.setLevel(blevel);
//                     upgradecost=new Label("The Cost of new "+bname+" is "+a.getUpgradeCost());
//                }
//                if(bname.equals("Market")){
//                     a = cv.market;
//                     a.setLevel(blevel);
//                     upgradecost=new Label("The Cost of new "+bname+" is "+a.getUpgradeCost());
//                }
//             //TODO:if market or farm add upgrade button and upgradecost label and if  barracks,stable or ArcheryRange
//             //add upgrade button and upgradecost label and recruit button and recruit label
//         }
//
//
//                //TODO: if  barracks,stable or ArcheryRange add recruit button and recruit label
//
//
//
//
//
//
//
//
//
//
//
//
//                mainLayout = bar;
//    //	        ModifiedGridPane layout = new ModifiedGridPane(10, 9);
//    //	        layout.add(bar, 2, 7, 6, 3);
//    //	        layout.setGridLinesVisible(true);
//    //	        layout.setHalignment(bar, HPos.CENTER);
//    //	 }
//
//     }
//
//
//}

package gui;

import buildings.*;
import engine.City;
import engine.Game;
import engine.Player;
import exceptions.*;
import gui.battle.BattleView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import units.Army;
import units.Status;
import units.Unit;

import java.io.*;
import java.util.*;

public class BarPane2 {
    Parent mainLayout;
    Game game;
    Player player;
    CityView cv;
    Building b;
    final Color color = Color.WHITE;
    StackPane parent;

    public BarPane2() {
        StackPane bar = new StackPane();
        Rectangle box = new Rectangle();
        box.setHeight(300);
        box.setWidth(1000);
        box.setFill(color);
        box.setArcWidth(30.0);
        box.setArcHeight(20.0);


        bar.getChildren().add(box);
        mainLayout = bar;

    }

    public void openPane() {
        parent.getChildren().add(this.mainLayout);
    }

    public void closePane() {
        parent.getChildren().remove(this.mainLayout);
    }

    public BarPane2(String bname, City city, StackPane parent, CityView cv) {
        this.parent = parent;
        Image box = new Image("resources/images/barPane_black.png");

        box.setFitWidth(1200);
        box.setFitHeight(500);


        FlowPane info = new FlowPane();
        info.setOrientation(Orientation.VERTICAL);
        info.setAlignment(Pos.CENTER);
        info.setPrefWidth(500);
        info.setVgap(20);

        //info.getChildren().addAll();


        VBox buttons = new VBox();
        //	        buttons.getChildren().addAll(view, close);
        buttons.setAlignment(Pos.CENTER);
        buttons.setFillWidth(true);
        buttons.setSpacing(40);
        buttons.setPrefWidth(500);

        HBox foreground = new HBox();
        foreground.setAlignment(Pos.TOP_CENTER);
        foreground.setFillHeight(true);
        foreground.setPadding(new Insets(0, 0, 30, 0));


        StackPane bar = new StackPane();

        //Labels
        Label cost = null;
        Label name = new Label(bname);
        name.setStyle("-fx-text-fill: white;-fx-font-size:30; -fx-font-weight: bold");


        //Buttons
        MyButton build = new MyButton("Build");
        MyButton close = new MyButton("Close");
        build.setOnAction(e ->{
            try {
                Building b = cv.build(city,bname);
                this.closePane();
                cv.updateInfo();
//                BarPane2 newBar = new BarPane2(city, cv, b, parent);
//                newBar.openPane();
            }
            catch(FileNotFoundException f) {
                System.out.println("xxxx");
            }

        });

        close.setOnAction(e -> {
            this.closePane();
        });


        if(bname.equals("ArcheryRange")) {
            ArcheryRange a = new ArcheryRange();
            cost=new Label("The Cost: " + a.getCost());
            cost.setStyle("-fx-text-fill: white;-fx-font-size:15");
        }
        if(bname.equals("Stable")) {
            Stable a= new Stable();
            cost=new Label("The Cost: " + a.getCost());
            cost.setStyle("-fx-text-fill: white;-fx-font-size:15");

        }
        if(bname.equals("Barracks")){
            Barracks a= new Barracks();
            cost=new Label("The Cost: " + a.getCost());
            cost.setStyle("-fx-text-fill: white;-fx-font-size:15");
        }
        if(bname.equals("Farm")){
            Farm a= new Farm();
            cost=new Label("The Cost: " + a.getCost());
            cost.setStyle("-fx-text-fill: white;-fx-font-size:15");
        }
        if(bname.equals("Market")){
            Market a= new Market();
            cost=new Label("The Cost: " + a.getCost());
            cost.setStyle("-fx-text-fill: white;-fx-font-size:15");
        }

        info.getChildren().addAll(name, cost);
        buttons.getChildren().addAll(build, close);

        bar.getChildren().addAll(box, foreground);
        foreground.getChildren().addAll(info, buttons);

        bar.setAlignment(Pos.CENTER);
        ModifiedGridPane grid = new ModifiedGridPane(10, 9);
        grid.add(bar, 2, 7, 6, 3);
        grid.setHalignment(bar, HPos.CENTER);


        mainLayout = grid;

    }

    public BarPane2(City city, CityView cv, Building b, StackPane parent) {
        this.parent = parent;
        Image box = new Image("resources/images/barPane_black.png");

        box.setFitWidth(1200);
        box.setFitHeight(500);


        FlowPane info = new FlowPane();
        info.setOrientation(Orientation.VERTICAL);
        info.setAlignment(Pos.CENTER);
        info.setPrefWidth(500);
        info.setVgap(20);

        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setFillWidth(true);
        buttons.setSpacing(40);
        buttons.setPrefWidth(500);

        MyButton close = new MyButton("Close");
        close.setOnAction(e -> {
            this.closePane();
        });


        Label title = new Label(b.getClass().getSimpleName() + " Level " + b.getLevel());
        title.setStyle("-fx-text-fill: white;-fx-font-size:30; -fx-font-weight: bold");

        Label cooldown = new Label("Cooling Down: " + b.isCoolDown());
        cooldown.setStyle("-fx-text-fill: white;-fx-font-size:15");
        info.getChildren().addAll(title, cooldown);
        if (b.getLevel() != 3) {
            MyButton upgrade = new MyButton("Upgrade");
            Label upgradeCost = new Label("Upgrade Cost: " + b.getUpgradeCost());
            upgradeCost.setStyle("-fx-text-fill: white;-fx-font-size:15");

            upgrade.setOnAction(e -> {
                cv.upgrade(b.getClass().getSimpleName());
                this.closePane();
            });
            info.getChildren().add(upgradeCost);
            buttons.getChildren().add(upgrade);

        }




        if (b instanceof MilitaryBuilding) {
            MilitaryBuilding mb = (MilitaryBuilding) b;
            Label recruitmentCost = new Label("Recruitment Cost: " + mb.getRecruitmentCost());
            recruitmentCost.setStyle("-fx-text-fill: white;-fx-font-size:15");
            Label currentRecruit = new Label("Current Recruit: " + mb.getCurrentRecruit());
            currentRecruit.setStyle("-fx-text-fill: white;-fx-font-size:15");
            Label maxRecruit = new Label("Max Recruit: " + mb.getMaxRecruit());
            maxRecruit.setStyle("-fx-text-fill: white;-fx-font-size:15");
            MyButton recruit = new MyButton("Recruit");

            recruit.setOnAction(e -> {
                try {
                    cv.getController().getGame().getPlayer().recruitUnit(((MilitaryBuilding) b).getRecruit(), city.getName());
                    cv.updateInfo();
                    BarPane2 newBar = new BarPane2(city, cv, b, parent);
                    closePane();
                    newBar.openPane();
                    closePane();
                }
                    catch (NotEnoughGoldException notEnoughGoldException) {
                        popUp exceptionPopup = new popUp("Not Enough Gold!", "You don't have " + ((MilitaryBuilding) b).getRecruitmentCost() + " gold\nto recruit.", cv.getPopUpBase());
                        cv.getPopUpBase().getChildren().add(exceptionPopup.getRoot());
                    }
                catch (BuildingInCoolDownException buildingInCoolDownException) {
                    popUp buildingCooldown = new popUp("Cooling Down!", "You can't recruit\nwhile in cooldown.", cv.getPopUpBase());
                    cv.getPopUpBase().getChildren().add(buildingCooldown.getRoot());
                } catch (MaxRecruitedException maxRecruitedException) {
                    popUp buildingCooldown = new popUp("Max Recruitments\nReached!", "You can't recruit more than 3\nunits in a turn!", cv.getPopUpBase());
                    cv.getPopUpBase().getChildren().add(buildingCooldown.getRoot());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                

            });

            info.getChildren().addAll(recruitmentCost, currentRecruit, maxRecruit);
            buttons.getChildren().addAll(recruit);
        }

        buttons.getChildren().add(close);


        //	        info.getChildren().addAll(title, status, location);
        //	        if (!(army.getDistancetoTarget() == -1)) {
        //	            info.getChildren().add(distanceToTarget);
        //	            info.getChildren().add(target);
        //	        }


        //
        //	        Button view = new Button("View Units");
        //	        Button close = new Button("Close");

        HBox foreground = new HBox();
        foreground.getChildren().addAll(info, buttons);
        foreground.setAlignment(Pos.TOP_CENTER);
        foreground.setFillHeight(true);
        foreground.setPadding(new Insets(0, 0, 30, 0));


        StackPane bar = new StackPane();
        bar.getChildren().addAll(box, foreground);

        ModifiedGridPane grid = new ModifiedGridPane(10, 9);
        grid.add(bar, 2, 7, 6, 3);
        grid.setHalignment(bar, HPos.CENTER);

        mainLayout = grid;

    }

    
    public BarPane2(Army army, View wmv ,StackPane parent) {
        this.parent = parent;
        player = wmv.getController().getGame().getPlayer();
        game = wmv.getController().getGame();
        System.out.println(parent);
        boolean flag = false;
        MyButton besiege;
        MyButton autoresolve;
        MyButton viewUnits;
        MyButton attack;
        MyButton close;
        MyButton targetCity;


        Image box = new Image("resources/images/barPane_black.png");

        box.setFitWidth(1200);
        box.setFitHeight(500);

        FlowPane info = new FlowPane();
        info.setOrientation(Orientation.VERTICAL);
        info.setAlignment(Pos.CENTER);
        info.setPrefWidth(500);
        info.setVgap(20);

        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setFillWidth(true);
        buttons.setSpacing(20);
        buttons.setPrefWidth(500);

        String inOn = "in";
        if (army.getCurrentLocation().equals("Road")) {
            inOn = "on";
        }
        Label title = new Label("Army " + inOn + " " + army.getCurrentLocation());
        title.setStyle("-fx-text-fill: white;-fx-font-size:30; -fx-font-weight: bold");
        Label ArmyStatus = new Label("Status: " + army.getCurrentStatus());
        ArmyStatus.setStyle("-fx-text-fill: white;-fx-font-size:15");


        info.getChildren().addAll(title, ArmyStatus);


        close = new MyButton("Close");
        close.setOnAction(e -> {
            closePane();
        });
        if (army.getUnits().size() > 0) {
            viewUnits = new MyButton("View " + army.armySize() + " Units");
            viewUnits.setOnAction(e -> {
                PopupDialog popUnits = new PopupDialog("Unit", army.getUnits(), parent, wmv);
                parent.getChildren().add(popUnits.getRoot());
                popUnits.onConfirm(e2 -> {
                    Unit chosenUnit = popUnits.getChosenUnit();
                    if (chosenUnit != null) {
                        closePane();
                        BarPane2 bar = new BarPane2(chosenUnit, parent, wmv);
                        bar.openPane();
                    }
                });
            });
            buttons.getChildren().addAll(viewUnits);
        } else {
            Label noUnits = new Label("No Units in Army");
            noUnits.setStyle("-fx-text-fill: white;-fx-font-size:15");
            buttons.getChildren().addAll(noUnits);
        }

        if (wmv instanceof WorldMapView) {
            if (army.armySize() == 0) {
                MyButton remove = new MyButton("Remove Army Safely");
                remove.setOnAction(e -> {
                    player.getControlledArmies().removeIf(a -> a == army);
                    wmv.getController().changeScene(new WorldMapView(wmv.getController()).getRoot());
                });
                buttons.getChildren().add(remove);
            }

            if (army.getCurrentStatus().equals(Status.IDLE)) {
                for (City city : player.getControlledCities()) {

                    if (army.getCurrentLocation().equals(city.getName())) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    //army idle in a controlled city
                    targetCity = new MyButton("Target City");
                    targetCity.setOnAction(e -> {
                        wmv.getController().changeScene(new TargetCity(army, wmv.getController()).getRoot());
                    });
                    buttons.getChildren().addAll(targetCity);

                } else {
                    //army idle in an enemy city
                    City target = null;


                    for (City city2 : game.getAvailableCities()) {
                        if (city2.getName().equals(army.getCurrentLocation())) {
                            target = city2;
                        }
                    }

                    if (!target.isUnderSiege()) {
                        besiege = new MyButton("Besiege " + target.getName());
                        buttons.getChildren().addAll(besiege);

                        final City targetedCity = target;
                        besiege.setOnAction(e -> {
                            try {
                                player.laySiege(army, targetedCity);
                                popUp pop = new popUp("Besieged!", "City " + targetedCity.getName() + "\nhas been besieged.", parent);
                                parent.getChildren().add(pop.getRoot());
                                pop.onConfirm(e2 -> {
                                    WorldMapView newWmv = new WorldMapView(wmv.getController());
                                    wmv.getController().changeScene(newWmv.getRoot());
                                });
                            } catch (TargetNotReachedException m) {
                                popUp exceptionPopup = new popUp("Target not Reached", "Can't siege a target that\nhasn't been reached.", wmv.getPopUpBase());
                                wmv.getPopUpBase().getChildren().add(exceptionPopup.getRoot());
                            } catch (FriendlyCityException m) {
                                popUp exceptionPopup = new popUp("Friendly Fire!", "You can't attack a friendly city!", wmv.getPopUpBase());
                                wmv.getPopUpBase().getChildren().add(exceptionPopup.getRoot());
                            }
                        });
                    }



                    attack = new MyButton("Attack " + target.getName());
                    attack.setOnAction(e -> {
                        PopupChooser attackChooser = new PopupChooser("How would you like to attack?", "Manual Attack", "Auto Resolve", parent);
                        parent.getChildren().add(attackChooser.getRoot());
                        closePane();

                        attackChooser.getChoice().addListener((o, old, newVal) -> {
                            parent.getChildren().remove(attackChooser.getRoot());
                            String cityString = army.getCurrentLocation();
                            City targetedCity1 = null;
                            for (City city : wmv.getController().getGame().getAvailableCities()) {
                                if (city.getName().equals(cityString)) {
                                    targetedCity1 = city;
                                }
                            }

                            Army defendingArmy = targetedCity1.getDefendingArmy();
                            if ((attackChooser).getChoice().getValue().equals("Manual Attack")) {
                                wmv.getController().startOtherMusic();
                                BarPane2 starter = new BarPane2("battle");
                                BattleView bv = new BattleView(wmv.getController(), army, defendingArmy, starter);
                                wmv.getController().changeScene(bv.getRoot());
                            } else if ((attackChooser).getChoice().getValue().equals("Auto Resolve")) {
                                wmv.getController().startOtherMusic();
                                closePane();
                                try {
                                    String log = game.autoResolve(army, defendingArmy);
                                    closePane();
                                    BarPane2 logBar = new BarPane2("battle");
                                    logBar.addToLog(log);
                                    BattleView bv = new BattleView(wmv.getController(), army, defendingArmy, logBar);
                                    wmv.getController().changeScene(bv.getRoot());

                                } catch (FriendlyFireException friendlyFireException) {
                                    popUp exceptionPopup = new popUp("Friendly Fire!", "You can't attack a friendly city!", wmv.getPopUpBase());
                                    wmv.getPopUpBase().getChildren().add(exceptionPopup.getRoot());
                                }

                            }

                        });
                    });


                    buttons.getChildren().add(attack);

            }


        } else if (army.getCurrentStatus().equals(Status.MARCHING)) {
            //TODO:add all common buttons defined outside if statement
            Label ArmyTarget = new Label("Current Target: " + army.getTarget());
            ArmyTarget.setStyle("-fx-text-fill: white;-fx-font-size:15");
            Label Armydistance = new Label("Distnace to Target: " + army.getDistancetoTarget());
            Armydistance.setStyle("-fx-text-fill: white;-fx-font-size:15");
            info.getChildren().addAll(ArmyTarget, Armydistance);

        } else if (army.getCurrentStatus().equals(Status.BESIEGING)) {
            attack = new MyButton("Attack");
            City sieged = new City("a");
            for (City city2 : game.getAvailableCities()) {
                if (city2.getClass().getSimpleName().equals(army.getCurrentLocation())) {
                    sieged = city2;
                }
            }
            final City siegedCity = sieged;
            attack.setOnAction(e -> {
                PopupChooser attackChooser = new PopupChooser("How would you like to attack?", "Manual Attack", "Auto Resolve", parent);
                parent.getChildren().add(attackChooser.getRoot());
//                closePane();

                attackChooser.getChoice().addListener((o, old, newVal) -> {
                    String cityString = army.getCurrentLocation();
                        City targetedCity = null;
                        for (City city : wmv.getController().getGame().getAvailableCities()) {
                            if (city.getName().equals(cityString)) {
                                targetedCity = city;
                            }
                        }
                        Army defendingArmy = targetedCity.getDefendingArmy();
                        if ((attackChooser).getChoice().getValue().equals("Manual Attack")) {
                            wmv.getController().startOtherMusic();
                            BarPane2 starter = new BarPane2("battle");
                            BattleView bv = new BattleView(wmv.getController(), army, defendingArmy, starter);
                            wmv.getController().changeScene(bv.getRoot());
                        } else if ((attackChooser).getChoice().getValue().equals("Auto Resolve")) {
                            wmv.getController().startOtherMusic();
                            closePane();
                            try {
                                String log = game.autoResolve(army, defendingArmy);
                                closePane();
                                BarPane2 logBar = new BarPane2("battle");
                                logBar.addToLog(log);
                                BattleView bv = new BattleView(wmv.getController(), army, defendingArmy, logBar);
                                wmv.getController().changeScene(bv.getRoot());
                            } catch (FriendlyFireException friendlyFireException) {
                                popUp exceptionPopup = new popUp("Friendly Fire!", "You can't attack a friendly city!", wmv.getPopUpBase());
                                wmv.getPopUpBase().getChildren().add(exceptionPopup.getRoot());
                            }
                        }
                });
            });

                buttons.getChildren().add(attack);

        }
        } else if (wmv instanceof CityView){
            if (army.armySize() > 0) {
                MyButton relocateall = new MyButton("Relocate All Units");

                relocateall.setOnAction(e->{
                    System.out.println("abc");
                    ArrayList <Army> armies=new ArrayList <Army>();
                    for (Army a :((CityView)wmv).stationedArmies) {
                        armies.add(a);
                    }
                    armies.add(((CityView)wmv).defendingArmy);
                    armies.remove(army);
                    PopupDialog pop=new PopupDialog("Army",armies,parent, wmv);
                    System.out.println("abcsssssss");
                    parent.getChildren().add(pop.getRoot());
                    pop.onConfirm(event -> {
                        System.out.println("abcde");
                        Army choice =pop.getChosenArmy();
                        if (choice != null) {
                            try {
                                int len = army.getUnits().size();
                                for (int i = 0; i < len; i++) {
                                    if (choice.getUnits().size() >= choice.getMaxToHold()) {
                                        break;
                                    }
                                    Unit u = army.getUnits().get(0);
                                    System.out.println(army);
                                    choice.relocateUnit(u);
                                }
                                closePane();


                            } catch (MaxCapacityException em) {
                                popUp exceptionPopup = new popUp("Max Capacity!", "Army has reached max\na capacity of " + choice.getMaxToHold() + ".", wmv.getPopUpBase());
                                wmv.getPopUpBase().getChildren().add(exceptionPopup.getRoot());

                            }
                        }

                    });




                });
                buttons.getChildren().add(relocateall);
            }
        }
    	buttons.getChildren().add(close);


        HBox foreground = new HBox();
        foreground.getChildren().addAll(info, buttons);
        foreground.setAlignment(Pos.TOP_CENTER);
        foreground.setFillHeight(true);
        foreground.setPadding(new Insets(0, 0, 30, 0));


        StackPane bar = new StackPane();
        bar.getChildren().addAll(box, foreground);

        ModifiedGridPane grid = new ModifiedGridPane(10, 9);
        grid.add(bar, 2, 7, 6, 3);
        grid.setHalignment(bar, HPos.CENTER);

        mainLayout = grid;

    //TODO: Test

    }
    public BarPane2(Unit unit ,StackPane parent, View parentView) {
    	

        this.parent = parent;
        Image box = new Image("resources/images/barPane_black.png");

        box.setFitWidth(1200);
        box.setFitHeight(500);

        FlowPane info = new FlowPane();
        info.setOrientation(Orientation.VERTICAL);
        info.setAlignment(Pos.CENTER);
        info.setPrefWidth(500);
        info.setVgap(20);

        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setFillWidth(true);
        buttons.setSpacing(40);
        buttons.setPrefWidth(500);

        Label title = new Label(unit.getClass().getSimpleName() + " Level " + unit.getLevel());
        title.setStyle("-fx-text-fill: white;-fx-font-size:30; -fx-font-weight: bold");
        Label currentSC = new Label("Current Soldier Count: "+unit.getCurrentSoldierCount());
        currentSC.setStyle("-fx-text-fill: white;-fx-font-size:15");
        Label maxSC = new Label("Max Soldier Count: "+unit.getMaxSoldierCount());
        maxSC.setStyle("-fx-text-fill: white;-fx-font-size:15");
        Label idleUpkeep = new Label("Idle Upkeep: " + unit.getIdleUpkeep());
        idleUpkeep.setStyle("-fx-text-fill: white;-fx-font-size:15");
        Label marchingUpkeep = new Label("Marching Upkeep: " + unit.getMarchingUpkeep());
        marchingUpkeep.setStyle("-fx-text-fill: white;-fx-font-size:15");
        Label siegeUpkeep = new Label("Siege Upkeep: " + unit.getSiegeUpkeep());
        siegeUpkeep.setStyle("-fx-text-fill: white;-fx-font-size:15");

        MyButton close = new MyButton("Close");
        close.setOnAction(e -> closePane());
        
        if(parentView instanceof CityView) {
            MyButton relocate = new MyButton("Relocate Unit");
        	buttons.getChildren().add(relocate);
            
            relocate.setOnAction(e->{
            	System.out.println("abc");
            	ArrayList <Army> armies=new ArrayList <Army>();
            	for (Army a :((CityView)parentView).stationedArmies) {
            		armies.add(a);
            	}
            	armies.add(((CityView)parentView).defendingArmy);
            	armies.remove(unit.getParentArmy());
            	PopupDialog pop=new PopupDialog("Army",armies,parent, parentView);
            	System.out.println("abcsssssss");
            	pop.onConfirm(event -> {
            		System.out.println("abcde");
            		 int choice =pop.getChoice().getValue();
            		 try {
            			 armies.get(choice).relocateUnit(unit);
            			 parentView.getController().changeScene((new CityView(parentView.getController(),((CityView) parentView).getCity().getName())).getRoot());
            			 System.out.println("xyzl");
            		 } catch(MaxCapacityException em) {
                         popUp exceptionPopup = new popUp("Max Capacity!", "Army has reached max capacity of " + armies.get(choice).getMaxToHold(), parentView.getPopUpBase());
                         parentView.getPopUpBase().getChildren().add(exceptionPopup.getRoot());

                     } catch(FileNotFoundException em) {
                         System.out.println("File not found");
                     }

                });
            	

                parent.getChildren().add(pop.getRoot());
            });
        }
        
        if(parentView instanceof CityView) {
            MyButton initiate = new MyButton("Initaite Army");
        	buttons.getChildren().add(initiate);
            
        	initiate.setOnAction(e->{

            	CityView cv= (CityView)parentView;

            	try {
            		cv.player.initiateArmy(cv.getCity(), unit);
       			 parentView.getController().changeScene((new CityView(parentView.getController(),((CityView) parentView).getCity().getName())).getRoot());
       			 System.out.println("xyzl");
       		 }
       		 catch(FileNotFoundException em) {
                 System.out.println("File not found");
             }


            });
        }
        


        info.getChildren().addAll(title, currentSC, maxSC, idleUpkeep, marchingUpkeep, siegeUpkeep);
        buttons.getChildren().add(close);


        HBox foreground = new HBox();
        foreground.getChildren().addAll(info, buttons);
        foreground.setAlignment(Pos.TOP_CENTER);
        foreground.setFillHeight(true);
        foreground.setPadding(new Insets(0, 0, 30, 0));

        StackPane bar = new StackPane();
        bar.getChildren().addAll(box, foreground);

        ModifiedGridPane grid = new ModifiedGridPane(10, 9);
        grid.add(bar, 2, 7, 6, 3);
        grid.setHalignment(bar, HPos.CENTER);

        mainLayout = grid;

    }

    private Label log;
    public BarPane2(String battle) {
        Image box = new Image("resources/images/barPane_black.png");

        box.setFitWidth(1200);
        box.setFitHeight(500);

        Label title = new Label("Battle Log");
        title.setAlignment(Pos.TOP_LEFT);
        title.setStyle("-fx-text-fill: white; -fx-font-size: 20; -fx-font-weight: bold");
        log = new Label("\n\n");
        log.setStyle("-fx-text-fill: white; -fx-font-size: 15");



        ScrollPane container = new ScrollPane();
        container.setPrefWidth(500);
        container.setMaxHeight(250);
        container.setStyle("-fx-background-color:transparent; -fx-background: transparent;");
        container.setContent(log);



        slowScrollToBottom(container);


        VBox big = new VBox();
        big.setMaxWidth(500);
        big.setMaxHeight(275);
        big.getChildren().addAll(title, container);
        big.setAlignment(Pos.TOP_CENTER);
        big.setSpacing(20);


        StackPane bar = new StackPane();
        bar.getChildren().addAll(box, big);

        mainLayout = bar;

    }


    public void setParent(StackPane parent) {
        this.parent = parent;
    }

    public void addToLog(String newLog) {
        log.setText(newLog + "\n" + log.getText());
    }

    public String getLog() {
        return log.getText();
    }

    public Parent getRoot() {
        return mainLayout;
    }

    public void setRoot(Parent otherLayout) {
        mainLayout = otherLayout;
    }

    static void slowScrollToBottom(ScrollPane scrollPane) {
        Animation animation = new Timeline(
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(scrollPane.vvalueProperty(), 1)));
        animation.play();
    }

}



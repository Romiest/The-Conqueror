package gui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import buildings.*;

import engine.City;
import engine.Game;
import engine.Player;
import exceptions.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import units.Archer;
import units.Army;

public class CityView extends View {



    int barracksLevel=0;
	int archeryRangeLevel=0;
	int stableLevel=0;
	int farmLevel=0;
	int marketLevel=0;
	Image barracksImg ;
	Image marketImg ;
	Image farmImg ;
	Image stableImg ;
	Image archeryRangeImg ;
	City city;
	Barracks barracks;
	Stable stable;
	ArcheryRange archeryRange;
	Farm farm;
	Market market;
    Label gold;
    Label food;
    Label turns;
    Army defendingArmy;
    ArrayList <Army> stationedArmies;
    String CityName;

    public String getCityName() {
        return CityName;
    }

    Game game;
	Player player;
	
	public CityView(Controller controller, String CityName)  throws FileNotFoundException{
		this.CityName= CityName;
		popUpBase = new StackPane();
        this.controller = controller;
        game=controller.getGame();
		player=game.getPlayer();
		stationedArmies=new ArrayList<Army>();


		for (City city :player.getControlledCities() ) {

			if(city.getName().equals(CityName)) {
				this.city=city;
				defendingArmy = city.getDefendingArmy();

			for (MilitaryBuilding mb:city.getMilitaryBuildings() ) {

				if(mb instanceof Stable) {
					stableLevel=mb.getLevel();
					stable = (Stable) mb;
				}
				if(mb instanceof ArcheryRange) {
					archeryRangeLevel=mb.getLevel();
					archeryRange = (ArcheryRange) mb;
				}
				if(mb instanceof Barracks) {
					barracksLevel=mb.getLevel();
					barracks = (Barracks) mb;
				}
			}
			for (EconomicBuilding mb:city.getEconomicalBuildings()  ) {
				if(mb instanceof Farm) {
					farmLevel=mb.getLevel();
					farm = (Farm) mb;
				}
				if(mb instanceof Market) {
					marketLevel=mb.getLevel();
					market = (Market) mb;
				}
				
			}
			
		}
		}
		
		
		for( Army a:player.getControlledArmies()) {
			if(a.getCurrentLocation().equals(CityName)) {
				stationedArmies.add(a);
			}
		}
        ModifiedGridPane foreground = new ModifiedGridPane(10, 9);
        foreground.setGridLinesVisible(controller.showGrid);


        String imgPath = "";


        if (barracksLevel==0) {
            barracksImg=new Image("resources/images/Barracks_bw.png");
        }
        else {
            barracksImg=new Image("resources/images/Barracks.png");
        }
        barracksImg.setPreserveRatio(true);
        foreground.setHalignment(barracksImg, HPos.CENTER);



        if (marketLevel==0) {
            marketImg=new Image("resources/images/Market_bw.png");
        }
        else {
            marketImg=new Image("resources/images/Market.png");
        }
        marketImg.setPreserveRatio(true);
        foreground.setHalignment(marketImg, HPos.CENTER);






        if (farmLevel==0) {
            farmImg=new Image("resources/images/Farm_bw.png");
        }
        else {
            farmImg=new Image("resources/images/Farm.png");
        }
        farmImg.setPreserveRatio(true);
        foreground.setHalignment(farmImg, HPos.CENTER);




        if (stableLevel==0) {
            stableImg=new Image("resources/images/Stables_bw.png");
        } else {
            stableImg=new Image("resources/images/Stables.png");
        }
        stableImg.setPreserveRatio(true);
        foreground.setHalignment(stableImg, HPos.CENTER);




        if (archeryRangeLevel==0) {
            archeryRangeImg=new Image("resources/images/Archery Range_bw.png");
        }
        else {
            archeryRangeImg=new Image("resources/images/Archery Range.png");
        }
        archeryRangeImg.setPreserveRatio(true);
        foreground.setHalignment(archeryRangeImg, HPos.CENTER);





        Image flag= new Image("resources/images/banner.png");
        flag.setPreserveRatio(true);
        flag.setFitWidth(150);
        foreground.setHalignment(flag, HPos.CENTER);






        if(CityName.equalsIgnoreCase("Cairo")) {
            imgPath = "resources/images/egypt_map.png";

            foreground.add(barracksImg, 0, 1, 2, 2);
            barracksImg.setFitWidth(150);

            foreground.add(archeryRangeImg, 1, 0, 4, 8);
            archeryRangeImg.setFitHeight(175);

            foreground.add(stableImg, 1, 6, 4, 3);
            stableImg.setFitWidth(220);


            foreground.add(marketImg, 5, 1, 2, 2);
            marketImg.setFitWidth(220);


            foreground.add(farmImg, 8, 0, 2, 6);
            farmImg.setFitHeight(220);



            Image img = new Image("resources/images/Cairo.png");
        	img.setPreserveRatio(true);
        	img.setFitWidth(170);
        	foreground.add(img, 4, 0, 2, 2);
        	foreground.setHalignment(img,HPos.CENTER);
        }
        else if(CityName.equalsIgnoreCase("Rome")) {
            imgPath = "resources/images/rome_map.png";



            foreground.add(barracksImg, 0, 5, 2, 4);
            barracksImg.setFitHeight(200);

            foreground.add(archeryRangeImg, 1, 0, 2, 6);
            archeryRangeImg.setFitHeight(200);

            foreground.add(stableImg, 2, 3, 3, 6);
            stableImg.setFitWidth(220);


            foreground.add(marketImg, 5, 4, 3, 3);
            marketImg.setFitWidth(250);


            foreground.add(farmImg, 8, 3, 2, 7);
            farmImg.setFitHeight(200);



            Image img = new Image("resources/images/Rome.png");
        	img.setPreserveRatio(true);
        	img.setFitWidth(170);
        	foreground.add(img, 4, 0, 2, 2);
        	foreground.setHalignment(img,HPos.CENTER);
        }
        else if(CityName.equalsIgnoreCase("Sparta")) {
            imgPath = "resources/images/sparta_map.png";

            foreground.add(barracksImg, 0, 0, 2, 8);
            barracksImg.setFitHeight(200);

            foreground.add(archeryRangeImg, 0, 4, 2, 6);
            archeryRangeImg.setFitWidth(200);


            foreground.add(stableImg, 2, 4, 3, 3);
            stableImg.setFitWidth(190);



            foreground.add(marketImg, 7, 3, 3, 3);
            marketImg.setFitWidth(200);


            foreground.add(farmImg, 7, 0, 2, 3);
            farmImg.setFitWidth(220);



            Image img = new Image("resources/images/Sparta.png");
        	img.setPreserveRatio(true);
        	img.setFitWidth(300);
        	foreground.add(img, 4, 0, 2, 2);
        	foreground.setHalignment(img,HPos.CENTER);

        }

        Image map = new Image(imgPath);


        map.setPreserveRatio(controller.showGrid);

        final DoubleProperty width = map.fitWidthProperty();
        final DoubleProperty height = map.fitHeightProperty();
        width.bind(Bindings.selectDouble(map.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(map.sceneProperty(), "height"));






        //TODO:

        
        
        
        
        //TODO:


        
        
       
        //TODO:

        
        
        
        
        


        
        
        

        

        

        foreground.add(flag, 3, 4, 4, 3);


        
        Image info = new Image("resources/images/Info Bar.png");
        
        info.setFitWidth(350);
        info.setPreserveRatio(true);
        StackPane stackL = new StackPane();
        VBox leftBox = new VBox();
        
        Label name = new Label();
        
        name.setText(player.getName());
        name.setStyle("-fx-text-fill: white;-fx-font-size:25");
        turns = new Label("Current Turn: "+game.getCurrentTurnCount());
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
        
        gold = new Label();
        
        gold.setText("Gold: "+ (int) player.getTreasury());
        gold.setStyle("-fx-text-fill: white;-fx-font-size:18");
        food = new Label("Food: "+ (int) player.getFood());
        rBox.getChildren().addAll(gold,food);
        food.setStyle("-fx-text-fill: white;-fx-font-size:18");
        stackR.getChildren().addAll(info1,rBox);
        
        rBox.setAlignment(Pos.CENTER);
        foreground.add(stackR, 8, 0,2,1);

        StackPane layout = new StackPane();

        popUpBase.getChildren().addAll(foreground);

        barracksImg.onMouseClicked(e -> {
            BarPane2 bar;
            if (barracksLevel == 0) {
                bar = new BarPane2("Barracks", city, popUpBase, this);
            } else {
                bar = new BarPane2(city, this, barracks, popUpBase);
            }
            bar.openPane();
        });

        stableImg.onMouseClicked(e -> {
            BarPane2 bar;
            if (stableLevel == 0) {
                bar = new BarPane2("Stable", city, popUpBase, this);
            } else {
                bar = new BarPane2(city, this, stable, popUpBase);
            }
            bar.openPane();
        });
        archeryRangeImg.onMouseClicked(e -> {
            BarPane2 bar;
            if (archeryRangeLevel == 0) {
                bar = new BarPane2("ArcheryRange", city, popUpBase, this);
            } else {
                bar = new BarPane2(city, this, archeryRange, popUpBase);
            }
            bar.openPane();
        });
        marketImg.onMouseClicked(e -> {
            BarPane2 bar;
            if (marketLevel == 0) {
                bar = new BarPane2("Market", city, popUpBase, this);
            } else {
                bar = new BarPane2(city, this, market, popUpBase);
            }
            bar.openPane();
        });
        farmImg.onMouseClicked(e -> {
            BarPane2 bar;
            if (farmLevel == 0) {
                bar = new BarPane2("Farm", city, popUpBase, this);
            } else {
                System.out.println("Clicked");
                bar = new BarPane2(city, this, farm, popUpBase);
            }
            bar.openPane();
        });
        
        flag.onMouseClicked(e -> {
            ArrayList <Army> armies = new <Army> ArrayList();
            armies.add(defendingArmy);
            for (Army a : stationedArmies) {
            	armies.add(a);
            }
            PopupDialog pop=new PopupDialog("Army",armies,popUpBase,this );
            popUpBase.getChildren().add(pop.getRoot());
            
            
        });



        MyButton endTurn = new EndTurnButton(game, this).getButton();
        foreground.add(endTurn, 9, 8);
        foreground.setHalignment(endTurn, HPos.CENTER);

        WorldMapButton back = new WorldMapButton(controller);
        foreground.add(back, 0, 8);
        foreground.setHalignment(back, HPos.CENTER);



        layout.getChildren().addAll(map, popUpBase);
        this.mainLayout =layout;
	}
	
	
	


	
	public static void main(String[] args) {
		
		

	}



	public Building build(City city, String bname) throws FileNotFoundException {
        Building b = null;
        try {
            b = player.build(bname, city.getName());
            if(bname.equals("ArcheryRange")) {
                archeryRangeImg.setImage("resources/images/Archery Range.png");
                archeryRangeLevel = 1;
                archeryRange = (ArcheryRange) b;
            }
            if(bname.equals("Stable")) {
                stableImg.setImage("resources/images/Stables.png");
                stableLevel = 1;
                stable = (Stable) b;
            }
            if(bname.equals("Barracks")){
                barracksImg.setImage("resources/images/Barracks.png");
                barracksLevel = 1;
                barracks = (Barracks) b;
            }
            if(bname.equals("Farm")){
                farmImg.setImage("resources/images/Farm.png");
                farmLevel = 1;
                farm = (Farm) b;
            }
            if(bname.equals("Market")){
                marketImg.setImage("resources/images/Market.png");
                marketLevel = 1;
                market = (Market) b;
            }
        } catch (NotEnoughGoldException e) {
            popUp exceptionPopup = new popUp("Not Enough Gold!", "You don't have " + getCost(bname) + " gold\nto build.", popUpBase);
            popUpBase.getChildren().add(exceptionPopup.getRoot());
        }


        updateInfo();
        return b;

    }

    private int getCost(String bname) {
	    Building b = null;
        if(bname.equals("ArcheryRange")) {
            b = new ArcheryRange();
        }
        if(bname.equals("Stable")) {
            b = new Stable();
        }
        if(bname.equals("Barracks")){
            b = new Barracks();
        }
        if(bname.equals("Farm")){
            b = new Farm();
        }
        if(bname.equals("Market")){
            b = new Market();
        }
        return b.getCost();
    }


	public void upgrade(String bname) {
		if(bname.equals("Barracks")||bname.equals("Stable")||bname.equals("ArcheryRange")) {
            for(MilitaryBuilding mb : city.getMilitaryBuildings()) {
				if(bname.equals(mb.getClass().getSimpleName())) {
                    try {
						player.upgradeBuilding(mb);
					} catch (MaxLevelException e) {
                        popUp buildingCooldown = new popUp("Max Level!", "You can't upgrade the building\nbeyond level 3.", popUpBase);
                        popUpBase.getChildren().add(buildingCooldown.getRoot());
                    } catch (BuildingInCoolDownException e) {
                        popUp buildingCooldown = new popUp("Cooling Down!", "You can't upgrade the building\nwhile in cooldown.", popUpBase);
                        popUpBase.getChildren().add(buildingCooldown.getRoot());
                    } catch (NotEnoughGoldException e) {
                        popUp exceptionPopup = new popUp("Not Enough Gold!", "You don't have " + mb.getUpgradeCost() + " gold\nto upgrade the building.", popUpBase);
                        popUpBase.getChildren().add(exceptionPopup.getRoot());
                    }
                }
			}
		}
		else {
			for(EconomicBuilding  mb : city.getEconomicalBuildings()) {
				if(bname.equals(mb.getClass().getSimpleName())) {
					try {
						player.upgradeBuilding(mb);
					} catch (MaxLevelException e) {
                        popUp buildingCooldown = new popUp("Max Level!", "You can't upgrade the building\nbeyond level 3.", popUpBase);
                        popUpBase.getChildren().add(buildingCooldown.getRoot());
					} catch (BuildingInCoolDownException e) {
                        popUp buildingCooldown = new popUp("Cooling Down!", "You can't upgrade the building\nwhile in cooldown", popUpBase);
                        popUpBase.getChildren().add(buildingCooldown.getRoot());
                    } catch (NotEnoughGoldException e) {
                        popUp exceptionPopup = new popUp("Not Enough Gold!", "You don't have " + mb.getUpgradeCost() + "gold\nto upgrade the building.", popUpBase);
                        popUpBase.getChildren().add(exceptionPopup.getRoot());
					}
                }
			}
		}
		 updateInfo();

		
	}

	public void updateInfo() {
        gold.setText("Gold: " + (int) player.getTreasury());
        food.setText("Food: "+ (int)player.getFood());
    }

	public City getCity() {
	    return city;
    }





}

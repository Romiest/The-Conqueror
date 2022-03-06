package gui;

import engine.City;
import engine.Game;
import engine.Player;
import exceptions.FriendlyFireException;
import gui.battle.BattleView;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import units.Army;
import units.Status;
import units.Unit;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;

public class EndTurnButton {
    MyButton button;
	Game game;
	Event end;
	View view;

	public EndTurnButton(Game game, View view) {
	    this.view = view;
		this.game=game;
		button= new MyButton("End Turn");
		button.setOnAction(e->{
			game.endTurn();
            if (!game.isGameOver()) {
                siegeChecker();
            } else {
                view.getController().changeScene(new GameOverView(view.getController()).getRoot());
            }
		});


		this.button=button;
	}
	public MyButton getButton() {
		return button;
	}

	public void siegeChecker() {
        Player player = game.getPlayer();
        AtomicBoolean cityUnderSiege = new AtomicBoolean(false);
        for (City city : game.getAvailableCities()) {
            if (!player.getControlledCities().contains(city)) {
                System.out.println("enemy city" + city.getName());
                if (city.getTurnsUnderSiege() == -1) {
                    System.out.println("sieged city found");
                    cityUnderSiege.set(true);
                    Army friendly = new Army("Balabizo");
                    for (Army a : player.getControlledArmies()) {
                        if (a.getCurrentLocation().equals(city.getName()) && a.getCurrentStatus() == Status.BESIEGING) {
                            a.setCurrentStatus(Status.IDLE);
                            friendly = a;
                        }
                    }

                    PopupChooser attackChooser = new PopupChooser("We cannot hold the siege on " + city.getName() + "\nmuch longer!", "Manual Attack", "Auto Resolve", view.getPopUpBase());
                    Army finalFriendly = friendly;
                    Army defendingArmy = city.getDefendingArmy();
                    friendly.setCurrentStatus(Status.IDLE);

                    view.getPopUpBase().getChildren().add(attackChooser.getRoot());

                    attackChooser.getChoice().addListener((o, old, newVal) -> {
                        view.getController().startOtherMusic();
                        if ((attackChooser).getChoice().getValue().equals("Manual Attack")) {
                            BarPane2 starter = new BarPane2("battle");
                            BattleView bv = new BattleView(view.getController(), finalFriendly, defendingArmy, starter);
                            view.getController().changeScene(bv.getRoot());
                        } else if ((attackChooser).getChoice().getValue().equals("Auto Resolve")) {
                            try {
                                String log = game.autoResolve(finalFriendly, defendingArmy);
                                BarPane2 logBar = new BarPane2("battle");
                                logBar.addToLog(log);
                                BattleView bv = new BattleView(view.getController(), finalFriendly, defendingArmy, logBar);
                                view.getController().changeScene(bv.getRoot());
                            } catch (FriendlyFireException friendlyFireException) {
                                popUp exceptionPopup = new popUp("Friendly Fire!", "You can't attack a friendly city!", view.getPopUpBase());
                                view.getPopUpBase().getChildren().add(exceptionPopup.getRoot());
                            }
                        }
                    });
                    break;
                }
            }
        }
        if (!cityUnderSiege.get()) {
            if (view instanceof CityView) {
                try {
                    CityView c = new CityView(view.getController(), ((CityView) view).getCity().getName());
                    view.getController().changeScene(c.getRoot());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            } else if (view instanceof WorldMapView) {
                WorldMapView map = new WorldMapView(view.getController());
                view.getController().changeScene(map.getRoot());
            }
        }
    }

}

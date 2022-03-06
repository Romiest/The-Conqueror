package gui.battle;

import engine.City;
import exceptions.FriendlyFireException;
import gui.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import units.Army;
import units.Unit;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class BattleView extends View {
    Parent mainLayout;
    Controller controller;
    Army friendly;
    Army enemy;
    Army winningArmy;
    boolean attacking = false;
    StackPane popUpBase;

    public BattleView(Controller controller, Army friendly, Army enemy, BarPane2 bar) {
        this.controller = controller;
        this.friendly = friendly;
        this.enemy = enemy;


        ModifiedGridPane foreground = new ModifiedGridPane(10, 9);

        foreground.add(bar.getRoot(), 2, 7, 6, 3);

        Image map = new Image("resources/images/battle.png");
        map.setPreserveRatio(false);

        final DoubleProperty width = map.fitWidthProperty();
        final DoubleProperty height = map.fitHeightProperty();

        width.bind(Bindings.selectDouble(map.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(map.sceneProperty(), "height"));


        ArrayList<Unit> friendlyUnits = friendly.getUnits();
        ArrayList<Unit> enemyUnits = enemy.getUnits();



        UnitsPane friendlyPane = new UnitsPane(friendlyUnits, Pos.CENTER_LEFT, true);
        UnitsPane enemyPane = new UnitsPane(enemyUnits, Pos.CENTER_RIGHT, false);


        AtomicReference<Unit> friendlyChoice = new AtomicReference<Unit>();



        friendlyPane.onChoice(e -> {
//            friendlyChoice.set(friendlyPane.getChoice());
//            System.out.println(friendlyPane.getChoice());
//            foreground.add(enemyPane.getRoot(), 7, 1, 2, 7);
            attacking = true;
        });

        popUpBase = new StackPane();
        setPopUpBase(popUpBase);
        enemyPane.onChoice(e2 -> {
            if (attacking) {
                friendlyChoice.set(friendlyPane.getChoice());
                AtomicReference<Unit> enemyChoice = new AtomicReference<Unit>();
                enemyChoice.set(enemyPane.getChoice());
                try {
                    String[] logSplit1 = friendlyChoice.get().attack(enemyChoice.get());
                    int friendlyLen = friendly.armySize();
                    int friendlyIndex = (int)(Math.random()*friendlyLen);
                    int enemyLen = enemy.armySize();

                    String log1 = "Friendly: " + logSplit1[0] + " attacked " + logSplit1[1] + " killing " + logSplit1[2] + " soldiers.";
                    if (enemyLen > 0) {
                        int enemyIndex = (int) (Math.random() * enemyLen);
                        Unit friendlyUnit = friendly.getUnits().get(friendlyIndex);
                        Unit enemyUnit = enemy.getUnits().get(enemyIndex);
                        String[] logSplit2 = enemyUnit.attack(friendlyUnit);
                        String log2 = "Enemy: " + logSplit2[0] + " attacked " + logSplit2[1] + " killing " + logSplit2[2] + " soldiers.";

                        bar.addToLog(log2);
                    }
                    bar.addToLog(log1);
                    bar.addToLog("\n");
//                    enemyChoice.get().attack(friendlyChoice.get());
                    foreground.getChildren().remove(friendlyPane.getRoot());
                    foreground.getChildren().remove(enemyPane.getRoot());
//                    foreground.add(friendlyPane.getRoot(), 1, 1, 2, 7);
//                    foreground.add(enemyPane.getRoot(), 7, 1, 2, 7);

                } catch (FriendlyFireException friendlyFireException) {
                    popUp exceptionPopup = new popUp("Friendly Fire!", "You can't attack a friendly city!", this.getPopUpBase());
                    this.getPopUpBase().getChildren().add(exceptionPopup.getRoot());
                }

                BattleView bv = new BattleView(controller, friendly, enemy, bar);
                controller.changeScene(bv.getRoot());
            }
        });

        popUpBase.getChildren().add(foreground);

        winningArmy = armyWon();
        popUp result;
        if (winningArmy != null) {
            if (winningArmy == enemy) { //Enemy Won
                result = new popUp("Better Luck\nNext Time", enemy.getCurrentLocation() + " has defeated You", popUpBase);

                for (City city : controller.getGame().getAvailableCities()) {
                    if (city.getName().equals(enemy.getCurrentLocation())) {
                        city.setTurnsUnderSiege(0);
                        city.setUnderSiege(false);
                    }
                    controller.getGame().getPlayer().getControlledArmies().remove(friendly);
                }

            } else { //Friendly Won
                result = new popUp("Congratulations!", "You have defeated " + enemy.getCurrentLocation(), popUpBase);
                controller.getGame().occupy(friendly, enemy.getCurrentLocation());
            }
            popUpBase.getChildren().add(result.getRoot());
            result.onConfirm(e -> {

                if (!controller.getGame().isGameOver()) {
                    controller.playMainMusic();
                    WorldMapView next = new WorldMapView(controller);
                    EndTurnButton checkMoreSiege = new EndTurnButton(controller.getGame(), next);
                    checkMoreSiege.siegeChecker();
                    controller.changeScene(next.getRoot());
                } else {
                    controller.changeScene(new GameOverView(controller).getRoot());
                }
            });
        }



        foreground.add(friendlyPane.getRoot(), 1, 1, 2, 7);
        foreground.add(enemyPane.getRoot(), 7, 1, 2, 7);






        StackPane layout = new StackPane();
        layout.getChildren().addAll(map, popUpBase);
        mainLayout = layout;

    }

    public Parent getRoot() {
        return mainLayout;
    }


    public Army armyWon() {
        if (friendly.armySize() == 0) {
            return enemy;
        } else if (enemy.armySize() == 0) {
            return friendly;
        } else {
            return null;
        }
    }

    public Controller getController() {
        return this.controller;
    }
}



package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public abstract class Building {
    private int cost;
    private int level;
    private int upgradeCost;
    private boolean coolDown;

    public Building(int cost, int upgradeCost) {
        this.cost = cost;
        this.upgradeCost = upgradeCost;
        this.level = 1;
        this.coolDown = true;
    }

    abstract public void upgrade() throws BuildingInCoolDownException, MaxLevelException;

    public int getCost() {
        return this.cost;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUpgradeCost() {
        return this.upgradeCost;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public boolean isCoolDown() {
        return this.coolDown;
    }

    public void setCoolDown(boolean coolDown) {
        this.coolDown = coolDown;
    }

    public abstract String getName();
}

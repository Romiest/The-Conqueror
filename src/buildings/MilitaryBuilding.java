package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxRecruitedException;
import java.io.IOException;
import units.Unit;

public abstract class MilitaryBuilding extends Building {
    private int recruitmentCost;
    private int currentRecruit;
    private final int maxRecruit = 3;

    public MilitaryBuilding(int cost, int upgradeCost, int recruitmentCost) {
        super(cost, upgradeCost);
        this.recruitmentCost = recruitmentCost;
    }

    public int getRecruitmentCost() {
        return this.recruitmentCost;
    }

    public void setRecruitmentCost(int recruitmentCost) {
        this.recruitmentCost = recruitmentCost;
    }

    public int getCurrentRecruit() {
        return this.currentRecruit;
    }

    public void setCurrentRecruit(int currentRecruit) {
        this.currentRecruit = currentRecruit;
    }

    public int getMaxRecruit() {
        return 3;
    }

    public abstract Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException, IOException;

    public abstract String getRecruit();
}

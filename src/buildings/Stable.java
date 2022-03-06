package buildings;

import csv.ReadingCSVFile;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import java.io.IOException;
import units.Cavalry;
import units.Unit;

public class Stable extends MilitaryBuilding {
    public Stable() {
        super(2500, 1500, 600);
    }

    @Override
    public String getName() {
        return "Stable";
    }

    public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
        if (this.isCoolDown()) {
            throw new BuildingInCoolDownException();
        } else if (this.getLevel() == 3) {
            throw new MaxLevelException();
        } else {
            if (this.getLevel() == 2) {
                this.setLevel(3);
                this.setRecruitmentCost(700);
            } else if (this.getLevel() == 1) {
                this.setUpgradeCost(2000);
                this.setLevel(2);
                this.setRecruitmentCost(650);
            }

            this.setCoolDown(true);
        }
    }

    public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException, IOException {
        if (this.isCoolDown()) {
            throw new BuildingInCoolDownException();
        } else if (this.getCurrentRecruit() >= this.getMaxRecruit()) {
            throw new MaxRecruitedException();
        } else {
            int l = this.getLevel();
            String[][] cavOutput = ReadingCSVFile.readFile("Cavalry.csv");
            this.setCurrentRecruit(this.getCurrentRecruit() + 1);
            return new Cavalry(l, Integer.parseInt(cavOutput[l - 1][1]), Double.parseDouble(cavOutput[l - 1][2]), Double.parseDouble(cavOutput[l - 1][3]), Double.parseDouble(cavOutput[l - 1][4]));
        }
    }

    public String getRecruit() {
        return "cavalry";
    }

}

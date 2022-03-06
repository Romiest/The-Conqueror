package buildings;

import csv.ReadingCSVFile;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import java.io.IOException;
import units.Archer;
import units.Infantry;
import units.Unit;

public class Barracks extends MilitaryBuilding {
    public Barracks() {
        super(2000, 1000, 500);
    }

    @Override
    public String getName() {
        return "Barracks";
    }

    public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
        if (this.isCoolDown()) {
            throw new BuildingInCoolDownException();
        } else if (this.getLevel() == 3) {
            throw new MaxLevelException();
        } else {
            if (this.getLevel() == 2) {
                this.setLevel(3);
                this.setRecruitmentCost(600);
            } else if (this.getLevel() == 1) {
                this.setUpgradeCost(1500);
                this.setLevel(2);
                this.setRecruitmentCost(550);
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
            String[][] infOutput = ReadingCSVFile.readFile("Infantry.csv");
            this.setCurrentRecruit(this.getCurrentRecruit() + 1);
            return new Infantry(l, Integer.parseInt(infOutput[l - 1][1]), Double.parseDouble(infOutput[l - 1][2]), Double.parseDouble(infOutput[l - 1][3]), Double.parseDouble(infOutput[l - 1][4]));
        }
    }
    public String getRecruit() {
        return "infantry";
    }
}

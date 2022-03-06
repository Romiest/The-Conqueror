package buildings;

import csv.ReadingCSVFile;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import java.io.IOException;
import units.Archer;
import units.Unit;

public class ArcheryRange extends MilitaryBuilding {
    public ArcheryRange() {
        super(1500, 800, 400);
    }

    public String getName() {
        return "ArcheryRange";
    }
    public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
        if (this.isCoolDown()) {
            throw new BuildingInCoolDownException();
        } else if (this.getLevel() == 3) {
            throw new MaxLevelException();
        } else {
            if (this.getLevel() == 2) {
                this.setLevel(3);
                this.setRecruitmentCost(500);
            } else if (this.getLevel() == 1) {
                this.setUpgradeCost(700);
                this.setLevel(2);
                this.setRecruitmentCost(450);
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
            String[][] archOutput = ReadingCSVFile.readFile("Archer.csv");
            this.setCurrentRecruit(this.getCurrentRecruit() + 1);
            return new Archer(l, Integer.parseInt(archOutput[l - 1][1]), Double.parseDouble(archOutput[l - 1][2]), Double.parseDouble(archOutput[l - 1][3]), Double.parseDouble(archOutput[l - 1][4]));
        }
    }

    public String getRecruit() {
        return "archer";
    }

}

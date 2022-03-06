package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Market extends EconomicBuilding {
    public Market() {
        super(1500, 700);
    }

    @Override
    public String getName() {
        return "Market";
    }

    public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
        if (this.isCoolDown()) {
            throw new BuildingInCoolDownException();
        } else if (this.getLevel() == 3) {
            throw new MaxLevelException();
        } else {
            if (this.getLevel() == 1) {
                this.setLevel(2);
                this.setUpgradeCost(1000);
            } else if (this.getLevel() == 2) {
                this.setLevel(3);
            }

            this.setCoolDown(true);
        }
    }

    public int harvest() {
        if (this.getLevel() == 1) {
            return 1000;
        } else {
            return this.getLevel() == 2 ? 1500 : 2000;
        }
    }
}

package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Farm extends EconomicBuilding {
    public Farm() {
        super(1000, 500);
    }


    public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
        if (this.isCoolDown()) {
            throw new BuildingInCoolDownException();
        } else if (this.getLevel() == 3) {
            throw new MaxLevelException();
        } else {
            if (this.getLevel() == 1) {
                this.setLevel(2);
                this.setUpgradeCost(700);
            } else if (this.getLevel() == 2) {
                this.setLevel(3);
            }

            this.setCoolDown(true);
        }
    }

    @Override
    public String getName() {
        return "Farm";
    }

    public int harvest() {
        if (this.getLevel() == 1) {
            return 500;
        } else {
            return this.getLevel() == 2 ? 700 : 1000;
        }
    }
    public static void main(String[] args) {
		Farm f= new Farm();
		System.out.println(f.getClass().getSimpleName());
	}
}

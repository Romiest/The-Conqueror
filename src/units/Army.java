package units;

import exceptions.MaxCapacityException;



import java.util.ArrayList;


public class Army {
    private Status currentStatus = Status.IDLE;
    private ArrayList<Unit> units;
    private int distancetoTarget = -1;
    private String target = "";
    private String currentLocation;
    private final int maxToHold = 10;
    private static String lastId = "A";
    private static int counter = 0;
    private String id;


    public String getId() {
        return id;
    }

    public Army(String currentLocation) {
        id = lastId;
        if (lastId.charAt(0) == 'Z') {
            lastId = (char)('A'-1) + "";
            counter++;
        }
        if (counter > 0) {
            lastId = (char)(lastId.charAt(0)+1) + "" + counter;
        } else {
            lastId = (char)(lastId.charAt(0)+1) + "";
        }

        this.currentLocation = currentLocation;
        units = new ArrayList<Unit>();
    }


    public int getMaxToHold() {
        return maxToHold;
    }


    public Status getCurrentStatus() {
        return currentStatus;
    }


    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }


    public ArrayList<Unit> getUnits() {
        return units;
    }


    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }


    public int getDistancetoTarget() {
        return distancetoTarget;
    }


    public void setDistancetoTarget(int distancetoTarget) {
        this.distancetoTarget = distancetoTarget;
    }


    public String getTarget() {
        return target;
    }


    public void setTarget(String target) {
        this.target = target;
    }


    public String getCurrentLocation() {
        return currentLocation;
    }


    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void addUnit(Unit u) throws MaxCapacityException {
        if (this.armySize() >= maxToHold) { //Army exceeds 10. Armies in csv are already more than 10... Msh hatkalem
            throw new MaxCapacityException();
        } else {
            u.setParentArmy(this);
            units.add(u);
        }
    }

    public void relocateUnit(Unit unit) throws MaxCapacityException {
        if (unit.getParentArmy() != null) {
            ArrayList<Unit> parentArmy = unit.getParentArmy().getUnits();
            addUnit(unit);
            parentArmy.remove(unit);
            
        }
       
    }

    public void handleArmyDamage(double factor) {
        for (Unit u : this.getUnits()) {
            u.damage(u, factor);
        }
    }

    public int armySize() {
        return units.size();
    }

    public void handleAttackedUnit(Unit u) {
        if (u.getCurrentSoldierCount() <= 0) {
            if (u.getParentArmy() != null) {
                u.getParentArmy().getUnits().remove(u);
            }
            u.setCurrentSoldierCount(0);
            this.getUnits().remove(u) ;
        }
    }

    public double foodNeeded() {
        double totalFood = 0;
        for (Unit unit : units) {
            double upKeep = 1;
            if (currentStatus == Status.IDLE)
                upKeep = unit.getIdleUpkeep();
            else if (currentStatus == Status.BESIEGING)
                upKeep = unit.getSiegeUpkeep();
            else if (currentStatus == Status.MARCHING)
                upKeep = unit.getMarchingUpkeep();
            double foodNeeded = unit.getCurrentSoldierCount() * upKeep;
            totalFood += foodNeeded;
        }
        return totalFood;
    }

}



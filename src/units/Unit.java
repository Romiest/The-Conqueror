package units;

import exceptions.*;

import java.io.IOException;

import csv.*;

abstract public class Unit {
    private final int level;
    private final int maxSoldierCount;
    private int currentSoldierCount;
    private final double idleUpkeep;
    private final double marchingUpkeep;
    private final double siegeUpkeep;
    private Army parentArmy;


    public Unit(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
        this.level = level;
        this.maxSoldierCount = maxSoldierCount;
        this.currentSoldierCount = maxSoldierCount;

        this.idleUpkeep = idleUpkeep;
        this.marchingUpkeep = marchingUpkeep;
        this.siegeUpkeep = siegeUpkeep;


    }


    public int getCurrentSoldierCount() {
        return currentSoldierCount;
    }


    public void setCurrentSoldierCount(int currentSoldierCount) {
        this.currentSoldierCount = currentSoldierCount;
    }


    public int getLevel() {
        return level;
    }


    public int getMaxSoldierCount() {
        return maxSoldierCount;
    }


    public double getIdleUpkeep() {
        return idleUpkeep;
    }


    public double getMarchingUpkeep() {
        return marchingUpkeep;
    }


    public double getSiegeUpkeep() {
        return siegeUpkeep;
    }


    public Army getParentArmy() {
        return parentArmy;
    }

    public void setParentArmy(Army parentArmy) {
        this.parentArmy = parentArmy;
    }


    public String unitType() {
        if (this instanceof Archer) {
            return "Archer";
        } else if (this instanceof Cavalry) {
            return "Cavalry";
        } else return "Infantry";
    }


    public String[] attack(Unit target) throws FriendlyFireException {
        String targetType = target.unitType();
        Unit myUnit = this;
        String myType = myUnit.unitType();


        if (target.parentArmy == this.parentArmy)
            throw new FriendlyFireException();



        int myLevel = myUnit.getLevel();
        double factor = 0;
        if (myType.equals("Infantry")) {
            String[][] infDmg = new String[0][];
            try {
                infDmg = ReadingCSVFile.readFile("Infantry_dmg.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < infDmg.length; i++) {
                if (("" + myLevel).equals(infDmg[i][0]) && targetType.equals(infDmg[i][1])) {
                    factor = Double.parseDouble(infDmg[i][2]);
                    break;
                }
            }
        } else if (myType.equals("Archer")) {
            String[][] archDmg = new String[0][];
            try {
                archDmg = ReadingCSVFile.readFile("Archer_dmg.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < archDmg.length; i++) {
                if (("" + myLevel).equals(archDmg[i][0]) && targetType.equals(archDmg[i][1])) {
                    factor = Double.parseDouble(archDmg[i][2]);
                    break;
                }
            }
        } else if (myType.equals("Cavalry")) {
            String[][] cavDmg = new String[0][];
            try {
                cavDmg = ReadingCSVFile.readFile("Cavalry_dmg.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < cavDmg.length; i++) {
                if (("" + myLevel).equals(cavDmg[i][0]) && targetType.equals(cavDmg[i][1])) {
                    factor = Double.parseDouble(cavDmg[i][2]);
                    break;
                }
            }
        }


        return myUnit.damage(target, factor);

    }


    public String[] damage(Unit u, double factor) {
        int currentSoldierCountBefore = u.getCurrentSoldierCount();
        int loss = (int)(this.getCurrentSoldierCount() * factor);
        int remaining =  (currentSoldierCountBefore - loss);
        u.setCurrentSoldierCount(remaining);
//        if(u.getParentArmy()!=null) {
            u.parentArmy.handleAttackedUnit(u);
//        }
        return new String[]{this.getClass().getSimpleName(), u.getClass().getSimpleName(), loss + ""};
    }

  
}


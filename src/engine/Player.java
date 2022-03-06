package engine;
import buildings.*;
import exceptions.*;
import units.*;

import java.io.IOException;
import java.util.ArrayList;

public class Player {

	private String name;
	private ArrayList<City> controlledCities;
	private ArrayList<Army> controlledArmies;
	private double treasury;
	private double food;
	
	
	public String getName() {
		return name;
	}


	public double getTreasury() {
		return treasury;
	}


	public void setTreasury(double treasury) {
		this.treasury = treasury;
	}


	public double getFood() {
		return food;
	}


	public void setFood(double food) {
		this.food = food;
	}


	public ArrayList<City> getControlledCities() {
		return controlledCities;
	}


	public ArrayList<Army> getControlledArmies() {
		return controlledArmies;
	}
	
	public Player (String name) {
		this.name=name;
		controlledCities=  new ArrayList<City>();
		controlledArmies=  new ArrayList<Army>();

	}


    public void recruitUnit(String type, String cityName) throws BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException, IOException {
        City targetCity = null;
	    for (City city : controlledCities) {
            if (city.getName().equals(cityName)) {
                targetCity = city;
                break;
            }
        }
	    if (targetCity == null)
	        return;
        type = type.toLowerCase();
	    MilitaryBuilding targetBuilding = null;
        for (MilitaryBuilding building : targetCity.getMilitaryBuildings()) {
	        if (building instanceof ArcheryRange && type.equals("archer")) {
                targetBuilding = building;
                break;
            } else if (building instanceof Barracks && type.equals("infantry")) {
                targetBuilding = building;
                break;
            } else if (building instanceof Stable && type.equals("cavalry")) {
                targetBuilding = building;
                break;
            }
        }
        if (targetBuilding == null)
            return;

        Unit unit = targetBuilding.recruit();

        if(targetBuilding.getRecruitmentCost()>treasury) {
       	    throw new NotEnoughGoldException();
        }


        targetCity.getDefendingArmy().getUnits().add(unit);
        unit.setParentArmy(targetCity.getDefendingArmy());
        treasury -= targetBuilding.getRecruitmentCost();

    }



    public void payFromTreasury(double cost) throws NotEnoughGoldException {
        if (treasury < cost) {
            throw new NotEnoughGoldException();
        } else {
            treasury -= cost;
        }
    }

    public Building build(String type,String cityName) throws NotEnoughGoldException {
        City targetCity = null;
        for (City city : controlledCities) {
            if (city.getName().equals(cityName)) {
                targetCity = city;
                break;
            }
        }
        type = type.toLowerCase();
        Building targetBuilding = null;

        if (type.equals("archeryrange")) {
            targetBuilding = new ArcheryRange();
        } else if (type.equals("barracks")) {
            targetBuilding = new Barracks();
        } else if (type.equals("stable")) {
            targetBuilding = new Stable();
        } else if (type.equals("farm")) {
            targetBuilding = new Farm();
        } else if (type.equals("market")) {
            targetBuilding = new Market();
        }

        for (MilitaryBuilding building : targetCity.getMilitaryBuildings()) {
            if (building instanceof ArcheryRange && type.equals("archeryrange")) {
                return targetBuilding;
            } else if (building instanceof Barracks && type.equals("barracks")) {
                return targetBuilding;
            } else if (building instanceof Stable && type.equals("stable")) {
                return targetBuilding;
            }
        }
        for (EconomicBuilding building : targetCity.getEconomicalBuildings()) {
            if (building instanceof Farm && type.equals("farm")) {
                return targetBuilding;
            } else if (building instanceof Market && type.equals("market")) {
                return targetBuilding;
            }
        }

        payFromTreasury(targetBuilding.getCost());
        

        if (targetBuilding instanceof EconomicBuilding)
            targetCity.getEconomicalBuildings().add((EconomicBuilding)targetBuilding);
        else if (targetBuilding instanceof MilitaryBuilding)
            targetCity.getMilitaryBuildings().add((MilitaryBuilding) targetBuilding);
        return targetBuilding;

    }

    public void upgradeBuilding(Building b) throws NotEnoughGoldException, BuildingInCoolDownException, MaxLevelException {
    	
   int z=b.getUpgradeCost();
    	
    	if(b.getUpgradeCost()<=treasury) {
    		b.upgrade();
    	treasury-=z;
    	}
    	else {
    		throw new NotEnoughGoldException();
    	}
    	
    	
	    

    }

    public void initiateArmy(City city,Unit unit) {
	    city.getDefendingArmy().getUnits().remove(unit);
	    Army newArmy = new Army(city.getName());
	    try {
            newArmy.addUnit(unit);
        } catch (MaxCapacityException e) {}
	    controlledArmies.add(newArmy);
    }

    public void laySiege(Army army, City city) throws TargetNotReachedException, FriendlyCityException {
	    if (!army.getCurrentLocation().equals(city.getName()))
	        throw new TargetNotReachedException();
	    for (City city1 : controlledCities)
	        if (city1 == city)
	            throw new FriendlyCityException();

	    army.setCurrentStatus(Status.BESIEGING);
	    city.setUnderSiege(true);
	    city.setTurnsUnderSiege(0);
    }

    public boolean removeArmy(Army army) {
        return controlledArmies.remove(army);
    }


}

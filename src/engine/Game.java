package engine;

import java.io.IOException;

import buildings.*;
import exceptions.FriendlyFireException;
import exceptions.MaxCapacityException;
import exceptions.TargetNotReachedException;
import units.*;
import java.util.ArrayList;
import java.util.Arrays;

import csv.*;

public class Game {


	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount=50;
	private int currentTurnCount;

	public Game(String playerName,String playerCity) throws IOException {
		 player=new Player(playerName);

		 availableCities=new ArrayList<City>();
		 distances=new ArrayList<Distance>();
		 currentTurnCount=1;
		 loadCitiesAndDistances();

		 for (int i = 0; i < availableCities.size(); i++) {
		 	City cur = availableCities.get(i);
		 	if (!cur.getName().equals(playerCity)) {
		 		loadArmy(cur.getName(), cur.getName().toLowerCase() + "_army.csv");
			} else {
				player.getControlledCities().add(cur);
			}
		 }
	}

	public Player getPlayer() {
		return player;
	}



	public void setPlayer(Player player) {
		this.player = player;
	}



	public int getCurrentTurnCount() {
		return currentTurnCount;
	}



	public void setCurrentTurnCount(int currentTurnCount) {
		this.currentTurnCount = currentTurnCount;
	}



	public ArrayList<City> getAvailableCities() {
		return availableCities;
	}



	public ArrayList<Distance> getDistances() {
		return distances;
	}



	public int getMaxTurnCount() {
		return maxTurnCount;
	}

	public Army loadArmy(String cityName,String path) throws IOException {

		String [] [] csvOutput= ReadingCSVFile.readFile(path);
		String [] [] archOutput= ReadingCSVFile.readFile("Archer.csv");
		String [] [] cavOutput= ReadingCSVFile.readFile("Cavalry.csv");
		String [] [] infOutput= ReadingCSVFile.readFile("Infantry.csv");

		City target = new City(cityName);

		for (int i = 0; i < availableCities.size(); i++) {
			City curCity = availableCities.get(i);
			if (curCity.getName() == cityName) {
				target = curCity;
				break;
			}
		}

		ArrayList <Unit> arm = new ArrayList <Unit>();
		for(int i=0;i<csvOutput.length;i++) {
			Unit u;
			int level =Integer.parseInt(csvOutput[i][1]);
			if(csvOutput[i][0].equals("Archer")) {
				u=new Archer(level,Integer.parseInt(archOutput[level-1][1]),Double.parseDouble(archOutput[level-1][2]),Double.parseDouble(archOutput[level-1][3]),Double.parseDouble(archOutput[level-1][4]));
			}
			else if(csvOutput[i][0].equals("Infantry")) {
				u=new Infantry(level,Integer.parseInt(infOutput[level-1][1]),Double.parseDouble(infOutput[level-1][2]),Double.parseDouble(infOutput[level-1][3]),Double.parseDouble(infOutput[level-1][4]));
			}
			else {
				u = new Cavalry(level, Integer.parseInt(cavOutput[level - 1][1]), Double.parseDouble(cavOutput[level - 1][2]), Double.parseDouble(cavOutput[level - 1][3]), Double.parseDouble(cavOutput[level - 1][4]));
			}
			
             target.getDefendingArmy().getUnits().add(u);
             u.setParentArmy(target.getDefendingArmy());
           

		}
		return target.getDefendingArmy();


	}

    public void targetCity(Army army, String targetName)  {
	    if (targetName.equals("")||army.getCurrentStatus()==Status.MARCHING)
	        return;// removed the exception

	    String curLocation = army.getCurrentLocation();
	    for (Distance dist : distances) {
	        if (((dist.getFrom().equals(curLocation) && dist.getTo().equals(targetName))) || (dist.getTo().equals(curLocation) && dist.getFrom().equals(targetName))) {
	            army.setDistancetoTarget(dist.getDistance());
	            army.setTarget(targetName);
	            army.setCurrentLocation("Road");
	            break;
            }
        }
	    army.setCurrentStatus(Status.MARCHING);
    }

    public void endTurn() {
	    currentTurnCount++;
	    	
	    for (City city : availableCities) {
	
	        if (city.isUnderSiege()&&city.getTurnsUnderSiege()<3) {
                city.setTurnsUnderSiege(city.getTurnsUnderSiege() + 1);
                city.getDefendingArmy().handleArmyDamage(0.1);
            }
	        else if(city.isUnderSiege()) {
                city.setUnderSiege(false);
                city.setTurnsUnderSiege(-1);
	        }
	       
	    }
	    for(City city:player.getControlledCities()) {

	        for (MilitaryBuilding building : city.getMilitaryBuildings()) {
                building.setCoolDown(false);
                building.setCurrentRecruit(0);
            }
            for (EconomicBuilding building : city.getEconomicalBuildings()) {
                building.setCoolDown(false);
                if (building instanceof Farm) {
                    Farm farm = (Farm) building;
                    player.setFood(player.getFood() + farm.harvest());
                } else if (building instanceof Market) {
                    Market market = (Market) building;
                    player.setTreasury(player.getTreasury() + market.harvest());
                }
            }
        }


	    for (Army army : player.getControlledArmies()) {
            double food = army.foodNeeded();
            double remFood = player.getFood() - food;
            if (remFood <= 0) { //Why take food and damage the army? Shouldn't we only take food if we have enough?
                player.setFood(0);
                army.handleArmyDamage(0.1);
            } else {
                player.setFood(remFood);
            }

	    	if(army.getCurrentStatus() == Status.MARCHING || !army.getTarget().equals("")) {
	        	army.setDistancetoTarget(army.getDistancetoTarget() - 1);
	        }

	    	if((army.getCurrentStatus() == Status.MARCHING|| !army.getTarget().equals("")) && army.getDistancetoTarget() <= 0) { //Status check MARCHING to add
	        	army.setCurrentStatus(Status.IDLE);
                army.setCurrentLocation(army.getTarget());
                army.setTarget("");
	        	army.setDistancetoTarget(-1);
	        }

	    	if (army.armySize() == 0) {

            }

        }



    }

    public void occupy(Army b, String cityName) {
	    for (City city : availableCities) {
	        if (cityName.equals(city.getName())) {
	        	System.out.println("zzzzzzzzzzzzzzzzzz");
	        	System.out.println(currentTurnCount);
	            player.getControlledCities().add(city);
	            player.getControlledArmies().remove(b);
	            city.setDefendingArmy(b);
	            city.setUnderSiege(false);
	            city.setTurnsUnderSiege(0);
            }
        }
    }

    public String autoResolve(Army attacker, Army defender) throws FriendlyFireException {
	    boolean attackTurn = true;
	    String log = "";
	    while (attacker.armySize() > 0 && defender.armySize() > 0) {
	        int attackLen = attacker.armySize();
            int defendLen = defender.armySize();

            int attackerIndex = (int)(Math.random()*attackLen);
            int defenderIndex = (int)(Math.random()*defendLen);

            Unit attackUnit = attacker.getUnits().get(attackerIndex);
            Unit defendUnit = defender.getUnits().get(defenderIndex);

            String[] res;

            if (attackTurn) {
                res = attackUnit.attack(defendUnit);
                log += "Friendly: " + res[0] + " attacked " + res[1] + " killing " + res[2] + " soldiers." + "\n";
            } else {
                res = defendUnit.attack(attackUnit);
                log += "Enemy: " + res[0] + " attacked " + res[1] + " killing " + res[2] + " soldiers." + "\n\n";
            }


            attackTurn = !attackTurn;
        }
	    if (defender.getUnits().size() == 0) { //Attacker Won
	       // occupy(attacker, defender.getCurrentLocation());
        }
	    else if(attacker.getUnits().size()==0) {
	    	 for (City city : availableCities) {
	    		 if(city.getName().equals(defender.getCurrentLocation())) {
	    			city.setTurnsUnderSiege(0);
	    			city.setUnderSiege(false);
	    		 }
	    	 }
	    }
        return log;
    }

    public boolean isGameOver() {
    	System.out.println(player.getControlledCities().size());
    	System.out.println(availableCities.size());
    	
	    return (currentTurnCount > maxTurnCount) || (player.getControlledCities().size() == availableCities.size());
    }

	public void loadCitiesAndDistances() throws IOException{
		String [] [] res= ReadingCSVFile.readFile("distances.csv");
		ArrayList <String> cities= new ArrayList<String>();
		for(int i=0;i<res.length;i++) {
			for(int j=0;j<2;j++) {
				if(!cities.contains((String)res[i][j])) {
					cities.add((String)res[i][j]);
					City C= new City((String)res[i][j]);
					availableCities.add(C);
				}
			}
		}
		for(int i=0;i<res.length;i++) {
			String from=res[i][0];
			String to=res[i][1];
			int dist=Integer.parseInt(res[i][2]);
			Distance d=new Distance(from,to,dist);
			distances.add(d);
		}


	}



	void printArmy() {
		for (int i = 0; i < availableCities.size(); i++) {
			City curCity = availableCities.get(i);

		}
	}

	

}
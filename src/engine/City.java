package engine;
import buildings.*;
import units.*;


import java.util.ArrayList;

public class City {
	private String name;
	

	private ArrayList <EconomicBuilding> economicalBuildings;
	private ArrayList <MilitaryBuilding> militaryBuildings;
	private Army defendingArmy;
	private int turnsUnderSiege;
	private boolean underSiege = false;
	
	public City(String name) {
		this.name=name;
		economicalBuildings = new ArrayList<EconomicBuilding>();
		militaryBuildings = new ArrayList<MilitaryBuilding>();
		defendingArmy = new Army(name);
		turnsUnderSiege=0; //initialized with 0
	}
	
	public Army getDefendingArmy() {
		return defendingArmy;
	}


	public void setDefendingArmy(Army defendingArmy) {
		this.defendingArmy = defendingArmy;
	}


	public int getTurnsUnderSiege() {
		return turnsUnderSiege;
	}


	public void setTurnsUnderSiege(int turnsUnderSiege) {
		this.turnsUnderSiege = turnsUnderSiege;
	}


	public boolean isUnderSiege() {
		return underSiege;
	}


	public void setUnderSiege(boolean underSiege) {
		this.underSiege = underSiege;
	}


	public String getName() {
		return name;
	}


	public ArrayList<EconomicBuilding> getEconomicalBuildings() {
		return economicalBuildings;
	}


	public ArrayList<MilitaryBuilding> getMilitaryBuildings() {
		return militaryBuildings;
	}




}

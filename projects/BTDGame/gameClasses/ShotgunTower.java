package gameClasses;

import info.gridworld.grid.Location;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import levelRunners.BTDRunnerOne;

public class ShotgunTower extends Tower{
	//fix constructor
	public ShotgunTower(){
		super();
		ArrayList<Location> locs = BTDRunnerOne.getPlayer().getGrid().getOccupiedLocations();
		//System.out.println(locs.toString());
		boolean hasPlayer = false; 
		for(Location loc: locs){
			if((BTDRunnerOne.getPlayer().getGrid().get(loc)) instanceof BalloonGenerator){
				hasPlayer = true;
				break;
			}
		}
		
		if(hasPlayer){
			String[] dirs = {"NORTH", "EAST", "SOUTH", "WEST"};
			int choice=JOptionPane.showOptionDialog
					(null, "What direction do you want your Shotgun Tower to face? ", "Directions", 
							0, 3, null, dirs, null); 
			int answer = 0;
			if(choice == 0)
				answer = Location.NORTH; 
			if(choice == 1) 
				answer = Location.EAST; 
			if(choice == 2) 
				answer = Location.SOUTH; 
			if (choice == 3) 
				answer = Location.WEST;  
			this.setDirection(answer);
			
		}
		
	}
	public int obtainMod(){
		return 5;
	}
	public ArrayList<Balloon> findBalloons(){

		int dirs[] = {getDirection()+45, getDirection(), getDirection()-45};
		ArrayList<Balloon> neighBals = new ArrayList<Balloon>();
		for(int d : dirs){
			Location loc = getLocation().getAdjacentLocation(d);
			if((getGrid().get(loc)) instanceof Balloon){
				neighBals.add(((Balloon)getGrid().get(loc)));
			}
		}
		return neighBals;
	}
	public int obtainBuyPrice(){
		return 400;
	}
}

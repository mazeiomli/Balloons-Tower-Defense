package gameClasses;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class DartTower extends Tower{
	//fix constructor
	public DartTower(){
		super();
	}
	public ArrayList<Balloon> findBalloons(){
		int x = getLocation().getRow();
		int y = getLocation().getCol();
		ArrayList<Balloon> neighBal = new ArrayList<Balloon>();
		for(int i = x-2; i <= x+2; i++){
			for(int j = y-2; j <= y+2; j++){
				Location loc = new Location(i,j);
				if(getGrid().isValid(loc) && getGrid().get(loc)!= null
						&& getGrid().get(loc) instanceof Balloon){
					neighBal.add(((Balloon)getGrid().get(loc)));
				}
			}
		}
		ArrayList<Balloon> singleBal = new ArrayList<Balloon>();
		if(neighBal.size()<=0)
			return singleBal; 
		else{
			int i = (int) (Math.random()*(neighBal.size()));
			singleBal.add((neighBal.get(i)));
			return singleBal;
		}
	}
	public int obtainMod(){
		return 3;
	}
	public int obtainBuyPrice(){
		return 250;
	}
}

package gameClasses;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class PinTower extends Tower{
	//fix constructor
	public PinTower(){
		super();
	}
	public ArrayList<Balloon> findBalloons(){
		ArrayList<Actor> actors = getGrid().getNeighbors(getLocation());
		ArrayList<Balloon> neighBals = new ArrayList<Balloon>();
		if(actors.size() <= 0)
			return neighBals;
		else{
			for(Actor a: actors){
				if(a instanceof Balloon){
					neighBals.add((Balloon)a);
				}
			}
			return neighBals;
		}
	}
	public int obtainMod(){
		return 6;
	}
	public int obtainBuyPrice(){
		return 600;
	}
}

package gameClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.gui.WorldFrame;
import info.gridworld.world.World;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Player extends Actor{
	private int lifePoints;
	private int balance;
	private ActorWorld world;
	private BalloonGenerator bgen;
	public Player(ActorWorld a, BalloonGenerator bGen){
		lifePoints = 100;
		balance = 600;
		world = a;
		this.bgen = bGen;
		updateMessage();
	}
	public Player(int liPoi, int bal, ActorWorld a, BalloonGenerator bGen){
		lifePoints = liPoi;
		balance = bal;
		world = a;
		this.bgen = bGen;
		updateMessage();
	}
	protected int getLifePoints() {
		return lifePoints;
	}
	protected void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}
	public int getBalance() {
		return balance;
	}
	protected void setBalance(int balance) {
			this.balance = balance;
	}
	public void act(){
		String hint = "Now, if your funds are sufficient, consider building more towers."
				+ "\nClick Run to start the next level.";
		String[] levelz = {"levelOne", "levelTwo", "levelThree", "levelFour"};
		ArrayList<String> levels = new ArrayList<String>(Arrays.asList(levelz));
		updateMessage();
		checkIfDead();
		if(checkIfLevelEnd()){
			balance+=100;
			updateMessage();
			String currentLevel = this.bgen.getFileName();
			JOptionPane.showMessageDialog(null, currentLevel + " done!\n" + hint);
			int in = levels.indexOf(currentLevel);

			if(in == 3){
				updateMessage();
				JOptionPane.showMessageDialog(null, "Game completed!");
				System.exit(0);
			}
			else{
				//System.out.println("else block reached");
				ArrayList<Location> occLocs = world.getGrid().getOccupiedLocations();
				//problem. also fix negative balance issue. 
				//BROKEN <- fixed 10/30/2017
				for(Location loc: occLocs);
				Location loc = new Location(0,0);
				{
					if(((this.getGrid()).get(loc)) instanceof BalloonBazaar){
						//System.out.println("confirm");
						((BalloonBazaar)((this.getGrid()).get(loc))).incrementPlayerBalance();
						updateMessage();
					}
				}
				this.bgen.removeSelfFromGrid();
				bgen = new BalloonGenerator(levels.get(in+1));
				this.bgen.putSelfInGrid(world.getGrid(), new Location(15,15));
				((WorldFrame)(((World)world).getJFrame())).getGUIController().stop();
			}

		}
	}
	protected void checkIfDead(){
		if(lifePoints <=0 ){
			JOptionPane.showMessageDialog(null, "Game over. You lost all of your lifepoints. "
					+ "\nRun JAR file to play again.");
			System.exit(0);
		}
	}
	public boolean checkIfLevelEnd(){
		ArrayList<Location> locs = world.getGrid().getOccupiedLocations();
		ArrayList<Actor> actors = new ArrayList<Actor>();
		for(Location loc:locs){
			actors.add(world.getGrid().get(loc));
		}
		for(Actor a : actors){
			if(a instanceof Balloon){
				return false;
			}
		}
		return true;
	}
	public void updateMessage(){
		String towerPrices = "Dart Tower costs $250. \t Pin Tower costs $600."
				+ "\tShotgun Tower costs $400. \tBalloonBazaar costs $550.";
		world.setMessage("Your lives: " + getLifePoints() + "\tYour balance: $" + getBalance()
				+ "\n" + towerPrices);
	}

}

package gameClasses;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import levelRunners.BTDRunnerOne;

public class Balloon extends Bug{
	private int steps;
	private int sideLength;
	private int sideNumber;
	private int hitPoints;
	private char id;
	public Balloon(int hitPoints){
		steps = 0;
		sideLength= 3;
		sideNumber = 0;
		this.hitPoints = hitPoints;
		//changeBalloonState();
		Color cols[] = {Color.RED, Color.BLUE, Color.GREEN};
		ArrayList<Color> colors = new ArrayList<Color>(Arrays.asList(cols));
		this.setColor(colors.get(this.hitPoints-1));
		setDirection(270);
	}

	public Balloon(int hitPoints, char id){
		steps = 0;
		sideLength= 3;
		sideNumber = 0;
		//if(hitPoints<=0)
			//System.out.println("error");
		this.hitPoints = hitPoints;
		//changeBalloonState();
		Color cols[] = {Color.RED, Color.BLUE, Color.GREEN};
		ArrayList<Color> colors = new ArrayList<Color>(Arrays.asList(cols));
		this.setColor(colors.get(this.hitPoints-1));
		setDirection(270);
		this.id = id;
	}
	public void act(){
		Grid<Actor> gr = getGrid();
		if (gr == null){
			return;
		}
		if(steps < sideLength-1 && sideNumber <= 8){
			move();
			steps++;
		}
		else {
			
			if(getLocation().equals(new Location(0,2)) && !(this.canMove())){
				decrementPlayerLifePoints();
				this.removeSelfFromGrid();
			}
			move();
			switch(sideNumber){
			case 4:
				setDirection(0);
				break;
			case 5:
				setDirection(45);
				break;
			case 6:
				setDirection(0);
				break;
			case 7:
				setDirection(315);
				break;
			default:
				break;
			//old path
			//			if(sideNumber == 10)
			//				setDirection(0);
			//			if(sideNumber == 12)
			//				setDirection(90);
			//			if(sideNumber == 13)
			//				setDirection(180);
			//			if(sideNumber == 15)
			//				setDirection(90);
			//			if(sideNumber == 16)
			//				setDirection(0);
			//			if(sideNumber == 19)
			//				setDirection(270);
			//			if(sideNumber == 23)
			//				setDirection(225);
			//			if(sideNumber == 24)
			//				setDirection(270);
			}
			steps = 0;
			sideNumber++;
		}
		//System.out.println(this.getLocation());
		changeBalloonState();
	}
	

	//  begin getters.
	public int getSteps() {
		return steps;
	}
	public int getHitPoints() {
		return hitPoints;
	}
	public void setHitPoints(int hiPoi) {
		this.hitPoints = hiPoi;
	}
	public int getSideNumber() {
		return sideNumber;
	}
	//  end getters
	protected void decrementPlayerLifePoints() {
		BTDRunnerOne.getPlayer().setLifePoints
		(BTDRunnerOne.getPlayer().getLifePoints()-getHitPoints());
	}
	protected void awardMoney(){
		Color cols[] = {Color.RED, Color.BLUE, Color.GREEN};
		ArrayList<Color> colors = new ArrayList<Color>(Arrays.asList(cols));
		if(hitPoints==0){
			int i = colors.indexOf(getColor()) + 1;
			BTDRunnerOne.getPlayer().setBalance(
					BTDRunnerOne.getPlayer().getBalance() + (i));
		}
		else if(!((colors.get(hitPoints-1)).equals(getColor()))){
			int i = colors.indexOf(getColor())+1;
			BTDRunnerOne.getPlayer().setBalance(
					BTDRunnerOne.getPlayer().getBalance() + (i-hitPoints));
		}
		BTDRunnerOne.getPlayer().updateMessage();
	}
	public char getId() {
		return id;
	}

	protected void changeBalloonState(){
		if(hitPoints <= 0){
			if(this.getGrid() != null)
				this.removeSelfFromGrid();
			return;
		}
		Color cols[] = {Color.RED, Color.BLUE, Color.GREEN};
		ArrayList<Color> colors = new ArrayList<Color>(Arrays.asList(cols));
		this.setColor(colors.get(this.hitPoints-1));
	}
	//Overridden because the Bug move() causes the bug
	//to remove itself if next is not valid.
	//Also, balloons shouldn't leave behind flowers.
	public void move()
	{
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		if (gr.isValid(next))
			moveTo(next);

	}
	//Overridden because the Bug canMove() returns false if
	//next location contains an occupant
	public boolean canMove()
	{
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return false;
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		return gr.isValid(next);
	}
}

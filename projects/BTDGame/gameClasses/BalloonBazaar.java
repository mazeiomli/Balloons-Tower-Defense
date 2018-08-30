package gameClasses;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import levelRunners.BTDRunnerOne;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class BalloonBazaar extends Actor{
	//fix me baby
	private int timesIncremented;
	public BalloonBazaar(){
		if(BTDRunnerOne.getPlayer().getBalance()-this.obtainBuyPrice() <0){
			JOptionPane.showMessageDialog(null, "Insufficient funds. Cannot construct structure.");
			timesIncremented = -1;
		}
		else{
			BTDRunnerOne.getPlayer().setBalance(
					(BTDRunnerOne.getPlayer().getBalance())-this.obtainBuyPrice());
			BTDRunnerOne.getPlayer().updateMessage();
			timesIncremented = 0;
			this.setColor(Color.YELLOW);
		}
		
	}
	public void act(){
		if(timesIncremented==-1){
			this.removeSelfFromGrid();
			return;
		}
		ArrayList<Location> occLocs = this.getGrid().getOccupiedLocations();
		boolean shouldPay = true;
		for(Location loc: occLocs){
			if(((this.getGrid()).get(loc)) instanceof Balloon){
				shouldPay = false;
			}
			if(((this.getGrid()).get(loc)) instanceof BalloonGenerator){
				if(((BalloonGenerator)((this.getGrid()).get(loc))).getNumberOf() > 0){
					shouldPay = false;
					//System.out.println("confirm");
				}
			}
		}
		if(shouldPay){
			incrementPlayerBalance();
		}
	}
	public void incrementPlayerBalance(){
		BTDRunnerOne.getPlayer().setBalance(
				(BTDRunnerOne.getPlayer().getBalance())+75);
	}
	public void sellBalloonBazaar(){
		this.removeSelfFromGrid();
		BTDRunnerOne.getPlayer().setBalance(
				(BTDRunnerOne.getPlayer().getBalance())+this.obtainSellPrice());
	}
	public int obtainBuyPrice(){
		return 550;
	}
	protected int obtainSellPrice(){
		return ((obtainBuyPrice())/5);
	}
}

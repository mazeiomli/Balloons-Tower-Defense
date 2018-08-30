package gameClasses;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import levelRunners.BTDRunnerOne;

public abstract class Tower extends Actor{
	//Dart:250 Pin:600 Shotgun:400 Bazaar:550
	private int shotsFired;
	public Tower(){
		if(BTDRunnerOne.getPlayer().getBalance()-this.obtainBuyPrice() <0){
			JOptionPane.showMessageDialog(null, "Insufficient funds. "
					+ "Cannot construct structure. \nStructure will"
					+ "be after level is run.");
			shotsFired = -1;
		}
		else{
			BTDRunnerOne.getPlayer().setBalance(
					(BTDRunnerOne.getPlayer().getBalance())-this.obtainBuyPrice());
			BTDRunnerOne.getPlayer().updateMessage();
			shotsFired = 0;
		}
		
	}
	public void act(){
		if(shotsFired==-1){
			this.removeSelfFromGrid();
			return;
		}
		if(shotsFired%obtainMod()!=0){
			shotsFired++;
			return;
		}
		ArrayList<Balloon> bals = findBalloons();
		this.popBalloons(bals);
		shotsFired++;
	}
	public abstract int obtainMod();
	public abstract int obtainBuyPrice();
	public int obtainSellPrice(){
		return (obtainBuyPrice()/5);
	}
	public abstract ArrayList<Balloon> findBalloons();
	public void popBalloons(ArrayList<Balloon> bals){
		for(Balloon b : bals){
			b.setHitPoints(b.getHitPoints()-1);
			b.awardMoney();
			b.changeBalloonState();
		}
	}
	public void sellTower(){
		if(shotsFired != -1){
		this.removeSelfFromGrid();
		BTDRunnerOne.getPlayer().setBalance(
				(BTDRunnerOne.getPlayer().getBalance())+this.obtainSellPrice());
		BTDRunnerOne.getPlayer().updateMessage();
		}
		else{
			JOptionPane.showMessageDialog(null, "Cannot sell invalid structure.");
		}
	}
}

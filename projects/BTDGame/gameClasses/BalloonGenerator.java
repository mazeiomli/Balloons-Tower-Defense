package gameClasses;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
//problem with level two
public class BalloonGenerator extends Actor{
	private String fileName;
	private String col;
	private int numberOf;
	private int bhit;
	private String nxtLine;
	public BalloonGenerator(String fiNa){
		fileName = fiNa;
		setColor(new Color(0,200,0));
		try{		
			Scanner inFile = new Scanner(new File(fileName + ".txt"));
			this.col = inFile.nextLine();
			this.numberOf = inFile.nextInt();
			String color[] = {"red", "blue", "green"};
			for(int i = 0; i<color.length;i++){
				if((this.col).equalsIgnoreCase(color[i])){
					this.bhit = i+1;
					//System.out.println(bhit);
					break;
				}
			}
			this.nxtLine = inFile.next();
			//System.out.println(nxtLine);
			//System.out.println(fileName);
			//System.out.println(this.bhit);
			//System.out.println(numberOf);
			inFile.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	protected String getFileName(){
		return this.fileName;
	}
	protected void setFileName(String fiNa){
		this.fileName = fiNa;
	}
	
	private void generateBalloon(){
		if(numberOf<=0 && nxtLine.equals("ERIC")){
			return;
			//System.exit(0);
			//JOptionPane.showMessageDialog(null, "Game over");
		}
		Grid<Actor> gr = getGrid();
		if(gr==null)
			return;
		try{	

			Scanner inFile = new Scanner(new File(fileName + ".txt"));
			//System.out.println(this.bhit);
			//real code
//			Balloon b1 = new Balloon(bhit);
//			b1.putSelfInGrid(gr, new Location(11,16));
//			this.numberOf--;
			Balloon b1 = new Balloon(bhit, ((char)(numberOf+64)));
			b1.putSelfInGrid(gr, new Location(11,16));
			this.numberOf--;
			
//			else{
//				try{	
//					col = inFile.nextLine();
//					numberOf = inFile.nextInt();
//					String color[] = {"red", "blue", "green"};
//					for(int i = 0; i<color.length;i++){
//						if(col.equals(color[i])){
//							bhit = i+1;
//							break;
//						}
//					}
//					inFile.close();
//				}catch (Exception e){
//					e.printStackTrace();
//				}
//			}
			inFile.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public int getNumberOf() {
		return numberOf;
	}
	public void act(){
		generateBalloon();
	}
}

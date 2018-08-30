 package levelRunners;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import gameClasses.*;

public class BTDRunnerOne {
	private static Player p;
	public static Player getPlayer() {
		return p;
	}
	public static void changeJOP()
	{
		// These colors are very ugly - you need to CHANGE them!
		// Edit 10:38PM 8/30/16 by Eric Li-- colors changed
		// The font of the message text
		UIManager.put("Label.font", new FontUIResource (new Font("Times New Roman", Font.BOLD, 24)));
		// The color of the message text
		UIManager.put("OptionPane.messageForeground",new Color(0,0,0));

		// color for text field (where you are inputting data)
		UIManager.put("TextField.background", new Color(255,193,128));
		// font for message in text field
		UIManager.put("TextField.font", new FontUIResource(new Font("Arial", Font.ITALIC, 24)));
		// color for message in text field
		UIManager.put("TextField.foreground", new Color(41,41,163));

		// The color of the panel 
		UIManager.put("Panel.background",new Color(51,204,51));
		// The color around the outside of the panel
		UIManager.put("OptionPane.background",new Color(153,230,153));

		// Buttons at bottom
		UIManager.put("Button.background",new Color(0,0,77));
		UIManager.put("Button.foreground", new Color(204,204,255));
		UIManager.put("Button.font", new FontUIResource	(new Font("SansSerif", Font.BOLD, 14)));
	}
	
	public static void main(String[] args) {
		changeJOP();
		//balance out towers
		String directions = "Welcome to Bloons Tower Defense(BTD). \nBalloons will spawn at the "
				+ "bottom right and "
				+ "will follow the yellow path. \nIf the balloon reaches the end of the yellow path, the "
				+ "balloon will disappear \nand your lifepoints will decrease. \nFor example, "
				+ "a red balloon has one hitpoint, \nmeaning it takes one tower shot to "
				+ "pop the balloon and the red balloon will \ndecrement your "
				+ "lifepoints by one if the red balloon reaches the "
				+ "end of the path. \nIf your lifepoints go"
				+ "to zero, the game ends. \nThe objective of BTD is to build towers to pop the balloons"
				+ "\nand to survive to the last level. ";
		String towerInfo = "\nThe Dart Tower, "
				+ "which shoots fast, \nhas great range,"
				+ "and is single target,"
				+ " costs $250, \nPin Tower, which shoots \nvery slow in all directions "
				+ "around it, costs $600, "
				+ "and Shotgun Tower, \nwhich shoots slow and shoots at its front three "
				+ "spaces, costs $400 and you \nmust choose its compass direction"
				+ ". \nBalloonBazaar, which does not pop balloons and generates"
				+ "\n$75 after every level, costs $550.";
		String gameHints ="\nYou will start with $600 and 20 lifepoints. \n"
				+ "Right click on a green space to build a tower or bazaar. \n"
				+ "Right click on a structure to find its sell price or sell it. \n"
				+ "Click the Run button to start each level. \n "
				+ "Once the level starts, you cannot build any structures \nuntil the level ends.\n"
				+ "Good luck!"
				+ " Hint: Build towers next to the yellow path.";
		JOptionPane.showMessageDialog(null, directions);
		JOptionPane.showMessageDialog(null, towerInfo);
		JOptionPane.showMessageDialog(null, gameHints);
		ActorWorld world = new ActorWorld(new BoundedGrid<Actor>(16,17));
		
		BalloonGenerator bgen = new BalloonGenerator("levelOne");
		
		world.add(new Location(15,15), bgen);
		
		p = new Player(25, (600), world, bgen);
		p.setColor(new Color(0,200,0));
		world.add(new Location(15,16), p);
		
		world.show();

	}
}

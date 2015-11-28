package aco;

import java.util.ArrayList;
import java.awt.Graphics2D;

public class AntColony {
	
	private ArrayList<Ant> ants = new ArrayList<Ant>();
	
	public AntColony() {
		
	}
	
	/**
	 * Adds an ant to the colony.
	 * 
	 * @param ant The and to be added.
	 */
	public void add(Ant ant) {
		ants.add(ant);
	}
	
	/**
	 * Renders all ants in the colony on the given graphics object.
	 * 
	 * @param g2 The Graphics2D object to be drawn to.
	 */
	public void render(Graphics2D g2) {
		for (Ant ant : ants) {
			ant.render(g2, 500, 500);
		}	
	}
	
}

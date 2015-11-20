package aco;

import java.util.ArrayList;
import java.awt.Graphics2D;

public class AntColony {
	
	private ArrayList<Ant> ants = new ArrayList<Ant>();
	
	public AntColony() {
		
	}
	
	public void add(Ant ant) {
		ants.add(ant);
	}
	
	public void render(Graphics2D g2) {
		for (Ant ant : ants) {
			ant.render(g2, 500, 500);
		}	
	}
	
}

package aco;

import java.util.ArrayList;
import java.awt.Graphics2D;

public class AntColony {
	
	private Ant[] ants;
	private City[] cities;
	private int[][] pheremoneMatrix;
	private int minX, minY, maxX, maxY;
	
	public AntColony(int antCount, int cityCount, int maxX, int maxY) {
		this.ants = new Ant[antCount];
		this.cities = new City[cityCount];
		this.maxX = maxX;
		this.maxY = maxY;
		this.pheremoneMatrix = new int[cityCount][cityCount];
		
		for (int i = 0; i < antCount; i++) {
			ants[i] = new Ant(cityCount);
		}
	}
	
	public AntColony(int antCount, City[] cities, int minX, int minY, int maxX, int maxY) {
		int cityCount = cities.length;
		this.ants = new Ant[antCount];
		this.cities = cities;
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		this.pheremoneMatrix = new int[cityCount][cityCount];
		
		for (int i = 0; i < antCount; i++) {
			ants[i] = new Ant(cityCount);
		}
	}
	
	/**
	 * Renders all ants in the colony on the given graphics object.
	 * 
	 * @param g2 The Graphics2D object to be drawn to.
	 */
	public void render(Graphics2D g2) {
		for (Ant ant : ants) {
			ant.render(g2, minX, minY, maxX, maxY);
		}	
		for (City city: cities) {
			city.render(g2, minX, minY, maxX, maxY);
		}
	}
}

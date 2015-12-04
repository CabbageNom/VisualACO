package aco;

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class AntColony {
	
	private Ant[] ants;
	private ExecutorService pool;
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
	 * Performs one iteration of the algorithm.
	 */
	public void iterate() {
		findPaths();
		applyPheremone();
	}
	
	public void findPaths() {
		pool = ExecutorService.newFixedThreadPool(antCount);
		for (Ant ant : ants) {
			ant = new Ant();
			pool.execute(ant);
		}
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.MINUTES);
	}
	
	public void applyPheremone() {
		// TODO: implement
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

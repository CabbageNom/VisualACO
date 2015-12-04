package aco;

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AntColony {
	
	private Ant[] ants;
	private ExecutorService pool;
	private City[] cities;
	private int[][] pheremoneMatrix;
	private int minX, minY, maxX, maxY;
	private static final int PHEREMONE_MIN = 1000, PHEREMONE_MAX = 10000;
	
	/*public AntColony(int antCount, int cityCount, int maxX, int maxY) {
		this.ants = new Ant[antCount];
		this.cities = new City[cityCount];
		this.maxX = maxX;
		this.maxY = maxY;
		this.pheremoneMatrix = new int[cityCount][cityCount];
	}*/
	
	public AntColony(int antCount, City[] cities, int minX, int minY, int maxX, int maxY) {
		int cityCount = cities.length;
		this.ants = new Ant[antCount];
		this.cities = cities;
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		this.pheremoneMatrix = new int[cityCount][cityCount];
		
		for (int[] pheremoneArray : pheremoneMatrix) {
			for (int i = 0; i < pheremoneArray.length; i++) {
				pheremoneArray[i] = PHEREMONE_MIN;
			}
		}
	}
	
	/**
	 * Performs one iteration of the algorithm.
	 */
	public void iterate() {
		generatePaths();
		applyPheremone();
	}
	
	public void generatePaths() {
		pool = Executors.newFixedThreadPool(ants.length);
		
		for (int i = 0; i < ants.length; i++) {
			ants[i] = new Ant(cities, pheremoneMatrix);
			pool.execute(ants[i]);
		}
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.MINUTES);
			System.out.println("123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Ant ant : ants) {
			String tour = "" + ant;
			tour += ": ";
			for (int i : ant.getTour()) {
				tour += i;
				tour += "  ";
			}
			System.out.println(tour);
		}
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

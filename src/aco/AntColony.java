package aco;

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AntColony {
	
	private Ant[] ants;
	private ExecutorService pool;
	private City[] cities;
	private int[][] pheremoneMatrix;
	private int[] shortestTour = {5,4,3,2,1,0,6,7,8,9,10,15,14,13,12,11,16,17,18,19,20,25,24,23,22,21,26,27,28}; 
	private double shortestTourLength;
	private int minX, minY, maxX, maxY;
	private static final int PHEREMONE_MIN = 1000, PHEREMONE_MAX = 100000, PHEREMONE_CONST = 1000;
	private static final double PHEREMONE_DECAY_RATE = 0.85;
	
	public AntColony(int antCount, City[] cities, int minX, int minY, int maxX, int maxY) {
		this.ants = new Ant[antCount];
		this.cities = cities;
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		this.pheremoneMatrix = new int[cities.length][cities.length];
		this.shortestTour = new int[cities.length];
		initPheremoneMatrix();
		initShortestTour();
	}
	
	/**
	 * Performs one iteration of the algorithm.
	 */
	public void iterate() {
		generatePaths();
		calculateShortestTour();
		applyAllPheremone();
		printPheremoneMatrix();
		decayPheremone();
	}
	
	public void generatePaths() {
		pool = Executors.newFixedThreadPool(ants.length);
		
		for (int i = 0; i < ants.length; i++) {
			ants[i] = new Ant(cities, pheremoneMatrix);
			pool.execute(ants[i]);
		}
		pool.shutdown();
		
		try {
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
			System.out.println("123");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void applyAllPheremone() {
		for (Ant ant : ants) {
			applyPheremone(ant);
		}
	}
	
	public void applyPheremone(Ant ant) {
		int[] tour = ant.getTour();
		double tourLength = getTourLength(tour);
		int pheremoneToAdd = (int) (PHEREMONE_CONST*tourLength/shortestTourLength);
		for (int i = 0; i < tour.length; i++) {
			int u = tour[i];
			int v = tour[(i+1) % tour.length];
			pheremoneMatrix[u][v]+= pheremoneToAdd;
			if (pheremoneMatrix[u][v] > PHEREMONE_MAX)
				pheremoneMatrix[u][v] = PHEREMONE_MAX;
		}
	}
	
	public void decayPheremone() {
		for (int i = 0; i < pheremoneMatrix.length; i++) {
			for (int j = 0; j < pheremoneMatrix.length; j++) {
				pheremoneMatrix[i][j] *= PHEREMONE_DECAY_RATE;
				if (pheremoneMatrix[i][j] < PHEREMONE_MIN)
					pheremoneMatrix[i][j] = PHEREMONE_MIN;
			}
		}
	}
	
	public double getTourLength(int[] tour) {
		double total = 0;
		for (int cityIndex = 0; cityIndex < tour.length; cityIndex++) {
			int nextCityIndex = (cityIndex + 1) % tour.length;
			double dist = cities[tour[cityIndex]].dist(cities[tour[nextCityIndex]]);
			total += dist;
		}
		return total;
	}
	
	public void calculateShortestTour() {
		shortestTourLength = getTourLength(shortestTour);
		for (Ant ant : ants) {
			int[] tour = ant.getTour();
			double length = getTourLength(tour);
			System.out.println(length);
			System.out.println(tour);
			if (length < shortestTourLength) {
				shortestTourLength = length;
				shortestTour = tour;
			}
		}
	}
	
	void printPheremoneMatrix() {
		String res = "";
		for (int[] pheremoneArray : pheremoneMatrix) {
			for (int pheremone : pheremoneArray) {
				res += String.format("% 6d,", pheremone);
			}
			res += "\n";
		}
		System.out.println(res);
	}
	
	void initPheremoneMatrix() {
		for (int[] pheremoneArray : pheremoneMatrix) {
			for (int i = 0; i < pheremoneArray.length; i++) {
				pheremoneArray[i] = PHEREMONE_MIN;
			}
		}
	}
	
	void initShortestTour() {
		for (int i = 0; i < cities.length; i++) {
			shortestTour[i] = i;
		}
	//	shortestTour = new int[] {5,4,3,2,1,0,6,7,8,9,10,15,14,13,12,11,16,17,18,19,20,25,24,23,22,21,26,27,28};
	}
	
	public int[] getShortestTour() {
		return shortestTour;
	}
	
	public double getShortestTourLength() {
		return getTourLength(shortestTour);
	}
	
	void renderPheremoneMatrix(Graphics2D g2, int minX, int minY, int maxX, int maxY) {
		minX *= 0.95;
		minY *= 0.95;
		maxX *= 1.05;
		maxY *= 1.05;
		System.out.println("PMAT RENDER");
		g2.drawLine(100,100, 300, 300);
	 	for (int i = 0; i < pheremoneMatrix.length; i++) {
			for (int j = i + 1; j < pheremoneMatrix.length; j++) {
				g2.setStroke(new BasicStroke(pheremoneMatrix[i][j]/50000));
				g2.drawLine(
					(int) cities[i].getDrawX(minX, minY, maxX, maxY),
					(int) cities[i].getDrawY(minX, minY, maxX, maxY),
					(int) cities[j].getDrawX(minX, minY, maxX, maxY),
					(int) cities[j].getDrawY(minX, minY, maxX, maxY)
				);
			}	
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
		g2.setColor(Color.RED);
		renderPheremoneMatrix(g2, minX, minY, maxX, maxY);
	}
}

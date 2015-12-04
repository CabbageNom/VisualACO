package aco;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Random;

public class Ant implements Runnable {
	
	private double x, y;
	private int cityIndex;
	private int[] tour;
	private City[] cities;
	private int[][] pheremoneMatrix;
	private Random random = new Random();
	private static final int DRAW_SIZE_DIAMETER = 8;
	
	public Ant(City[] cities, int[][] pheremoneMatrix) {
		this.cities = cities;
		this.pheremoneMatrix = pheremoneMatrix;
		this.tour = new int[cities.length];
		this.cityIndex = 0;
		this.x = 0;
		this.y = 0;
		
		System.out.println("ANT CREATED");
	}
	
	@Override
	public void run() {
		generateTour();
	}
	
	/**
	 * Generates the tour for this ant according to the cities and pheremone matrix.
	 */
	public void generateTour() {
		System.out.println("Generating tour");
		tour[0] = 0;
		for (int i = 1; i < cities.length; i++) {
			int next = nextCity();
			cityIndex = next;
			tour[i] = next;
		}
	}
	
	/**
	 * Calculates the next city in the tour.
	 */
	public int nextCity() {
		int[] pheremone = pheremoneMatrix[cityIndex];
		int sum = 0;
		for (int i = 0; i < pheremone.length; i++) {
			if (inTour(i))
				continue;
			sum += pheremone[i];
		}
		
		int rand = random.nextInt(sum);
		for (int i = 0; i < pheremone.length; i++) {
			if (inTour(i))
				continue;
			rand -= pheremone[i];
			if (rand <= 0) {
				return i;
			}
		}
		// Something went wrong if we got here
		System.out.println("Something went wrong in Ant.nextCity()");
		int i = 1;
		while (inTour(i)) {
			i++;
		}
		return i;
	}
	
	private boolean inTour(int index) {
		for (int i : tour) {
			if (i == index)
				return true;
		}
		return false;
	}
	
	/**
	 * Returns the X position of this ant on the map, for use when drawing.
	 */
	public double getX() {
		return this.x;	
	}
	
	/**
	 * Sets the ant's x position for drawing.
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Returns the Y position of this ant on the map, for use when drawing.
	 */
	public double getY() {
		return this.y;	
	}
	
	/**
	 * Sets the ant's y position for drawing.
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Returns the current tour of the ant.
	 */
	public int[] getTour() {
		return tour;	
	}
	
	/**
	 * Sets this ant's tour.
	 * 
	 * @param tour the new tour.
	 */
	public void setTour(int[] tour) {
		this.tour = tour;
	}
	
	/**
	 * Renders an ant as a dot on the specified graphics object.
	 * 
	 * @param g2 Graphics2D object to draw on.
	 * @param maxX The maximum X value of all cities, to scale the render window appropriately.
	 * @param maxY The maximum Y value of all cities, to scale the render window appropriately.
	 */
	public void render(Graphics2D g2, int minX, int minY, int maxX, int maxY) {
		int drawX = (int) (this.getX()/(maxX/500));
		int drawY = (int) (this.getY()/(maxY/500));
		
		g2.setColor(Color.RED);
		g2.fillOval(
			drawX - DRAW_SIZE_DIAMETER/2,
			drawY - DRAW_SIZE_DIAMETER/2,
			DRAW_SIZE_DIAMETER,
			DRAW_SIZE_DIAMETER
		);
	}
	
}

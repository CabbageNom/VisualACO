/**
 * Represents a city to be visited in the TSP.
 * 
 * Represents a city to be visited in the TSP, and includes utility functions for interactions between cities and for interaction between cities and ants.
 */
package aco;

import java.awt.Graphics2D;
import java.awt.Color;

public class City {
	
	private double x, y;
	private Color color;
	private static final int DRAW_SIZE_X = 5, DRAW_SIZE_Y = 10;
	
	public City(double x, double y) {
		this.x = x;
		this.y = y;
		this.color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
	}
	
	/**
	 * Returns the X position of this city on the map.
	 */
	public double getX() {
		return this.x;	
	}
	
	/**
	 * Returns the Y position of this city on the map.
	 */
	public double getY() {
		return this.y;	
	}
	
	/**
	 * Returns the distance between two cities.
	 * 
	 * @param city The other city to which the distance is calculated.
	 */
	public double dist(City city) {
		double dx = this.getX() - city.getX();
		double dy = this.getY() - city.getY();
		
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	/**
	 * Renders the city on the given graphics object.
	 * 
	 * @param g2 Graphics2D object to draw on.
	 * @param maxX The maximum X value of all cities, to scale the render window appropriately.
	 * @param maxY The maximum Y value of all cities, to scale the render window appropriately.
	 */
	public void render(Graphics2D g2, int minX, int minY, int maxX, int maxY) {
		// Give 10% border to render panel
		minX *= 0.90;
		minY *= 0.90;
		maxX *= 1.10;
		maxY *= 1.10;
		int drawX = (int) (700*(this.getX() - minX)/(1.0*(maxX - minX)));
		int drawY = (int) (700*(this.getY() - minY)/(1.0*(maxY - minY)));
		
		g2.setColor(this.color);
		g2.fillRect(
			drawX - DRAW_SIZE_X/2,
			drawY - DRAW_SIZE_Y/2,
			DRAW_SIZE_X,
			DRAW_SIZE_Y
		);
	}
}

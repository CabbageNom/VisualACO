/**
 * Represents a city to be visited in the TSP.
 * 
 * Represents a city to be visited in the TSP, and includes utility functions for interactions between cities and for interaction between cities and ants.
 */
package aco;

public class City {
	
	private double x, y;
	
	public City(double x, double y) {
		this.x = x;
		this.y = y;	
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
	 */
	public double dist(City city) {
		double dx = this.getX() - city.getX();
		double dy = this.getY() - city.getY();
		
		return Math.sqrt(dx*dx + dy*dy);
	}
	
}

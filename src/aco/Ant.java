package aco;

import java.awt.Graphics2D;
import java.awt.Color;

public class Ant {
	
	private double x, y;
	private int cityIndex;
	private static final int DRAW_SIZE_DIAMETER = 8;
	
	public Ant(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the X position of this ant on the map, for use when drawing.
	 */
	public double getX() {
		return this.x;	
	}
	
	/**
	 * Returns the Y position of this ant on the map, for use when drawing.
	 */
	public double getY() {
		return this.y;	
	}
	
	/**
	 * Renders an ant as a dot on the specified graphics object.
	 * 
	 * @param g2 Graphics2D object to draw on.
	 * @param maxX The maximum X value of all cities, to scale the render window appropriately.
	 * @param maxY The maximum Y value of all cities, to scale the render window appropriately.
	 */
	public void render(Graphics2D g2, int maxX, int maxY) {
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

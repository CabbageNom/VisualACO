/**
 * Application to visualise Ant Colony Optimisations on the Travelling Salesman Problem.
 * 
 * @Author Calum Calder <info@calum-calder.com>
 * @Version 1.0
 */
package aco;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.Dimension;

import java.util.ArrayList;

public class Main {
	
	public static AntColony colony;
	
	/**
	 * Creates the JFrame in which the map is rendered.
	 */
	static void createFrame() {
		JFrame frame = new JFrame() {{
			this.setTitle("VisualACO");
			this.getContentPane().add(new Renderer());
			this.setSize(new Dimension(710, 710));
			this.setVisible(true);
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}};	
	}
	
	public static void printTour(int[] tour) {
		String str = "";
		for (int i : tour) {
			str += i;
			str += "  ";
		}
		System.out.println(str);
	}
	
	public static void main(String[] args) {
		FileParser fp = new FileParser();
		fp.readFile("wi29.tsp");
		colony = new AntColony(
				5,
				fp.getCities(),
				(int) (fp.getMinX()),
				(int) (fp.getMinY()),
				(int) (fp.getMaxX()),
				(int) (fp.getMaxY())
			);
		
		int iterations = 10;
		
		for (int i = 0; i < iterations; i++) {
			colony.iterate();
			System.out.println("Tour " + i);
			for (int j : colony.getShortestTour()) {
				System.out.print(j);
				System.out.print(",");
			}
			System.out.println("");
			System.out.print("len: ");
			System.out.println(colony.getShortestTourLength());
			System.out.println("");
		}
		//createFrame();
	}
	
}

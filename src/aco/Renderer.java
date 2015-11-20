package aco;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.RenderingHints;

public class Renderer extends JPanel {
	
	/**
	 * The rate at which the panel should be repainted.
	 */
	private static final short REFRESH_RATE = 30;
	private static RenderingHints rh;
	
	public Renderer() {
		this.setVisible(true);
		this.setSize(new Dimension(500, 500));
		this.setLocation(200, 200);
		Thread thread = new Thread(() -> this.refresh());
		rh = new RenderingHints(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON
		);
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;	
		g2.setRenderingHints(rh);
		
		for (City city : Main.cities) {
			city.render(g2);
		}
		
		Main.colony.render(g2);
	}
	
	/**
	 * Repaints the graphics indefinitely.
	 */
	void refresh() {
		try {
			while (true) {
				this.repaint();
				Toolkit.getDefaultToolkit().sync();
				Thread.sleep(1000/REFRESH_RATE);	
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
}

package aco;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Toolkit;

public class Renderer extends JPanel {
	
	private static final short REFRESH_RATE = 30;
	
	public Renderer() {
		this.setVisible(true);
		this.setSize(new Dimension(500, 500));
		this.setLocation(200, 200);
		Thread thread = new Thread(() -> this.refresh());
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;	
		g.fillRect(20, 20, 40, 40);
	}
	
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

package aco;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FileParser {
	
	private FileInputStream fis;
	private City[] cities;
	private double maxX = 0, maxY = 0;
	private double minX, minY;
	
	public FileParser() {
		
	}
	
	public double getMinX() {
		return minX;
	}
	
	public double getMinY() {
		return minY;
	}
	
	public double getMaxX() {
		return maxX;
	}
	
	public double getMaxY() {
		return maxY;
	}
	
	public City[] getCities() {
		return cities;
	}
	
	public void readSizeLine(String line) {
		int size = Integer.parseInt(line.substring(12));
		cities = new City[size];
	}
	
	public void readCityLine(Matcher m) {
		if (m.matches()) {
			int index = Integer.parseInt(m.group(1)) - 1;
			double x = Double.parseDouble(m.group(2));
			double y = Double.parseDouble(m.group(3));
			if (index == 0) {
				minX = x;
				minY = y;
			}
			if (x > maxX)
				maxX = x;
			if (y > maxY)
				maxY = y;
			if (x < minX)
				minX = x;
			if (y < minY)
				minY = y;
			cities[index] = new City(x, y);
		}
	}
	
	public void readFile(String filename) {
		try {
			fis = new FileInputStream(filename);
		} catch (Exception e) { e.printStackTrace(); }
		Scanner scan = new Scanner(fis);
		int j = 0;
		Pattern p = Pattern.compile("(\\d+)\\s(\\d+\\.\\d+)\\s(\\d+\\.\\d+).*");
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (j == 4) {
				readSizeLine(line);
			}
			if (j > 6) {
				readCityLine(p.matcher(line));
			}
			j++;
		}
		System.out.println(minX);
		System.out.println(maxX);
		
		System.out.println(minY);
		System.out.println(maxY);
	}
}

package edu.uptc.so.processnmemory.views.figures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import edu.uptc.util.logger.Logger;

public abstract class Figure implements Logger {
	public static final Color BLUE = new Color(100, 100,200);
	public static final Color LIGHTBLUE = new Color(200, 200,240);
	public static final Color GRAY = new Color(100, 100,100);
	public static final Color LIGHTGRAY = new Color(200, 200,200);
	public static final Color RED = new Color(200, 100,100);
	public static final Color GREEN = new Color(100, 200,80);
	public static final Color ORANGE = new Color(230, 160,90);
	public static final Color YELLOW = new Color(150, 150,100);
	
	public int x, y, width, height;
	
	public abstract void draw(Graphics2D g);
	
	public int bottomY() {
		return this.y + this.height;
	}
	
	public void animate() {}
	
	public void drawBorderRect(Graphics2D g, int x, int y, int widrh, int height, Color color) {
		g.setColor(color);
		g.fillRect(x, y, widrh, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, widrh, height);
	}
	
	public void drawBorderRect(Graphics2D g, int x, int y, int width, int height, Color color, String text) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		this.drawCenteredString(g, text, x, y, width, height);
	}
	
	public void drawCenteredString(Graphics2D g, String text, int x, int y, int width, int height) {
		Font f = g.getFont();
		g.drawString(text, x + (width - f.getSize() * text.length() * .5f) / 2, y  + (height + f.getSize()) / 2);
	}
}

package edu.uptc.so.processnmemory.views.diagram;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import edu.uptc.so.processnmemory.views.figures.Figure;

public class StateFigure extends Figure {
	private Font font;
	private String name;
	private Color color;

	public StateFigure(String name, int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.width = 150;
		this.height = 65;
		this.name = name;
		this.color = color;
		this.font = new Font("Arial", Font.PLAIN, 25);
	}

	public void draw(Graphics2D g) {
		g.setFont(this.font);
		g.setColor(this.color);
		g.fillRoundRect(this.x, this.y, this.width, this.height, 10, 10);
		g.setColor(Color.BLACK);
		g.drawRoundRect(this.x, this.y, this.width, this.height, 10, 10);

		String[] ws = name.split("\n");

		if (ws.length == 1)
			g.drawString(this.name, this.x + this.width / 2 - this.name.length() * g.getFont().getSize() / 4,
					this.y + this.height / 2 + g.getFont().getSize() / 3);
		else
			this.distributeName(g, ws);

	}

	private void distributeName(Graphics2D g, String[] ws) {
		for (int i = 0;i < ws.length;i++) {
			g.drawString(ws[i], this.x + this.width / 2 - ws[i].length() * g.getFont().getSize() / 4,
					this.y + this.height / 4 + g.getFont().getSize() / 3 + i * g.getFont().getSize());
		}
	}
}

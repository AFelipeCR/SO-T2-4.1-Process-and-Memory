package edu.uptc.so.processnmemory.views.diagram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DiagramPanel extends JPanel {
	private static final long serialVersionUID = -5243581549038084364L;
	private DiagramFigure diagram;
	
	public DiagramPanel() {
		this.diagram = new DiagramFigure();
		this.init();
	}
	
	private void init() {
		this.setBackground(Color.WHITE);
	}
	
	@Override
	public void paint(Graphics gr) {
		super.paint(gr);
		Graphics2D g = (Graphics2D) gr;
		this.diagram.draw(g);
	}

}

package edu.uptc.so.processnmemory.views.queue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import edu.uptc.so.processnmemory.models.Process;

public class CPUPanel extends JPanel {
	private static final long serialVersionUID = -6688569145190228334L;
	private CPUFigure queue;
	
	public CPUPanel() {
		this.queue = new CPUFigure();
		this.init();
	}
	
	private void init() {
		this.setBackground(Color.WHITE);
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.queue.draw((Graphics2D) g);
	}

	public void addProcess(Process process) {
		this.queue.add(process);
	}

	public void updateCPU() {
		this.queue.updateCPU();
	}
}

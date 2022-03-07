package edu.uptc.so.processnmemory.views.memory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

public class MemoryPanel extends JPanel implements MouseWheelListener {
	private static final long serialVersionUID = -800263806163148924L;
	private MemoryFigure memory;

	public MemoryPanel() {
		this.memory = new MemoryFigure();
		this.init();
	}

	private void init() {
		this.setBackground(Color.WHITE);
		this.addMouseWheelListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.memory.draw((Graphics2D) g);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(this.memory.bottom() >= this.getSize().getHeight() - 10 && this.memory.y <= 10) {
			this.memory.y += e.getWheelRotation() * -10;
			
			if(this.memory.bottom() < this.getSize().getHeight() - 10)
				this.memory.y = (int) (this.getSize().getHeight()- 10 - this.memory.height);
			
			if(this.memory.y > 10)
				this.memory.y = 10;
		}
	}
	
	public void updateMemory(){
		this.memory.updateMemory();
	}
}

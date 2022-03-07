package edu.uptc.so.processnmemory.views;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private MainPanel mainPanel;
	
	public MainFrame() {
		this.mainPanel = new MainPanel();
		this.init();
	}
	
	private void init() {
		this.add(this.mainPanel);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setTitle("Procesos y memoria");
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

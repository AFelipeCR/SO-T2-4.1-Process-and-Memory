package edu.uptc.so.processnmemory.views;

import java.awt.CardLayout;

import javax.swing.JPanel;

import edu.uptc.so.processnmemory.views.config.ConfigPanel;
import edu.uptc.so.processnmemory.views.simulation.SimulationPanel;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 4486964186112131393L;
	public static final String CONFIG = "CONFIG", SIMULATION = "SIMULATION";
	private ConfigPanel configPanel;
	private SimulationPanel simulationPanel;
	
	public MainPanel() {
		this.configPanel = new ConfigPanel();
		this.simulationPanel = new SimulationPanel();
		this.init();
	}
	
	private void init() {
		this.setLayout(new CardLayout());
		this.add(this.configPanel, CONFIG);
		this.add(this.simulationPanel, SIMULATION);
	}
	
	public void setCard(String card) {
		((CardLayout) this.getLayout()).show(this, card);
		
		if(card.contentEquals(SIMULATION)) {
			this.simulationPanel.start();
		}
	}
}

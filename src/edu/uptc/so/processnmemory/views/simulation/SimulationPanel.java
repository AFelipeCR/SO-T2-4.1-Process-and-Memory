package edu.uptc.so.processnmemory.views.simulation;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import com.spirit.custom.SPanel;

import edu.uptc.so.processnmemory.views.memory.MemoryPanel;
import edu.uptc.so.processnmemory.views.queue.CPUPanel;

public class SimulationPanel extends SPanel {
	private static final long serialVersionUID = 7653801635310680018L;
	private Thread animationThread;
	private CPUPanel cpuPanel;
	private MemoryPanel memoryPanel;
	private ControlsPanel controlsPanel;
	private ProcessManagementPanel processManagementPanel;
	
	public SimulationPanel() {
		this.controlsPanel = new ControlsPanel();
		this.cpuPanel = new CPUPanel();
		this.memoryPanel = new MemoryPanel();
		this.processManagementPanel = new ProcessManagementPanel(this.cpuPanel);
		this.init();
	}

	
	private void init() {
		this.setLayout(new BorderLayout(5, 5));
		
		SPanel main = new SPanel();
		main.setLayout(new GridLayout(1, 2));
		main.add(cpuPanel);
		main.add(memoryPanel);
		
		this.add(this.controlsPanel, BorderLayout.NORTH);
		this.add(main, BorderLayout.CENTER);
		this.add(this.processManagementPanel, BorderLayout.EAST);
		
		this.animationThread = new Thread(() -> {
			while (true) {
				this.cpuPanel.repaint();
				this.memoryPanel.repaint();
				
				try {
					Thread.sleep(125 / 3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void start() {
		if(!this.animationThread.isAlive())
			this.animationThread.start();
		
		this.memoryPanel.updateMemory();
		this.cpuPanel.updateCPU();
	}
}

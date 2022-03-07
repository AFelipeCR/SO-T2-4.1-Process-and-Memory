package edu.uptc.so.processnmemory.views.simulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

import com.spirit.custom.SButton;
import com.spirit.custom.SPanel;

import edu.uptc.so.processnmemory.config.Config;
import edu.uptc.so.processnmemory.views.MainPanel;

public class ControlsPanel extends SPanel {
	private static final long serialVersionUID = -1537184018511326727L;
	private SPanel sliderPanel;

	public ControlsPanel() {
		this.sliderPanel = new SPanel();
		this.init();
	}

	private void init() {
		int height = 60;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(0, height + 20));
		this.sliderPanel.setPreferredSize(new Dimension(270, height));
		this.sliderPanel.setBorder(BorderFactory.createTitledBorder("Velocidad"));
		JSlider speedSlider = new JSlider(5, 100, Config.SPEED);
		speedSlider.addChangeListener((ChangeEvent e) -> Config.SPEED = ((JSlider) e.getSource()).getValue());
		JLabel speedLabel = new JLabel(speedSlider.getValue() + "");
		speedSlider.addChangeListener((ChangeEvent e) -> speedLabel.setText(((JSlider) e.getSource()).getValue() + ""));

		this.sliderPanel.add(speedSlider);
		this.sliderPanel.add(speedLabel);

		SButton returnButton = new SButton("Volver");
		SButton startButton = new SButton("Iniciar");
		SButton stopButton = new SButton("Detener");
		
		returnButton.addActionListener((ActionEvent e) ->  {
			Config.Cpu.setRunning(false);
			startButton.setVisible(true);
			stopButton.setVisible(false);
			((MainPanel) this.getParent().getParent()).setCard(MainPanel.CONFIG);
		});
		
		stopButton.setVisible(false);
		
		startButton.addActionListener((ActionEvent e) ->  {
			Config.Cpu.setRunning(true);
			stopButton.setVisible(true);
			startButton.setVisible(false);
		});
		
		stopButton.addActionListener((ActionEvent e) ->  {
			Config.Cpu.setRunning(false);
			startButton.setVisible(true);
			stopButton.setVisible(false);
		});
		
		SPanel l = new SPanel();
		l.setPreferredSize(new Dimension(100, height));
		
		l.add(returnButton);
		
		SPanel r = new SPanel();
		r.setPreferredSize(new Dimension(100, height));

		r.add(startButton);
		r.add(stopButton);
		
		SPanel c = new SPanel();
		c.setPreferredSize(new Dimension(100, height));
		
		c.add(this.sliderPanel);
		
		this.add(l, BorderLayout.WEST);
		this.add(c, BorderLayout.CENTER);
		this.add(r, BorderLayout.EAST);
		this.setBackground(Color.WHITE);
	}
}

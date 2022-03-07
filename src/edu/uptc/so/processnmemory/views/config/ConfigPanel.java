package edu.uptc.so.processnmemory.views.config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import com.spirit.custom.SButton;
import com.spirit.custom.SComboBox;
import com.spirit.custom.SPanel;
import com.spirit.custom.SSpinner;

import edu.uptc.so.processnmemory.config.Config;
import edu.uptc.so.processnmemory.models.Memory;
import edu.uptc.so.processnmemory.views.MainPanel;

public class ConfigPanel extends SPanel {
	private static final long serialVersionUID = -6146181538199696103L;

	public ConfigPanel() {
		this.init();
	}

	private void init() {
		SPanel content = new SPanel();
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);

		JLabel title = new JLabel("Configuración", JLabel.CENTER);
		title.setPreferredSize(new Dimension(0, 50));
		title.setFont(new Font("Arial", Font.PLAIN, 30));

		SPanel memorySizeP = new SPanel("Tamaño de la memoria");
		SComboBox<Integer> memorySizesBox = new SComboBox<Integer>(new Integer[] { 128, 256, 512, 1024, 2048 });
		memorySizesBox.setSelectedItem(Integer.valueOf(512));

		SPanel pageSizeP = new SPanel("Tamaño de página");
		SComboBox<Integer> pageSizesBox = new SComboBox<Integer>(new Integer[] { 8, 16, 32, 64, 128 });

		pageSizeP.add(pageSizesBox);

		memorySizesBox.setSelectedItem(Integer.valueOf(8));
		
		SPanel memoryTypeP = new SPanel("Gestión de memoria");
		ButtonGroup bg = new ButtonGroup();
		JRadioButton pagesRB = new JRadioButton("Paginación");
		pagesRB.setSelected(true);
		JRadioButton segmentsRB = new JRadioButton("Segmentación");
		bg.add(pagesRB);
		bg.add(segmentsRB);
		
		pagesRB.addActionListener((ActionEvent e) -> pageSizesBox.setEnabled(true));
		segmentsRB.addActionListener((ActionEvent e) -> pageSizesBox.setEnabled(false));

		memoryTypeP.add(pagesRB);
		memoryTypeP.add(segmentsRB);

		memorySizeP.add(memorySizesBox);

		SPanel quantumP = new SPanel("Quantum");
		SSpinner quantumS = new SSpinner(1, 1000);
		quantumS.setValue(10);
		
		quantumP.add(quantumS);
		SButton okButton = new SButton("Continuar");
		okButton.addActionListener((ActionEvent e) -> {
			Config.QUANTUM = quantumS.getNumberValue();
			Memory m = null;
			
			if(pagesRB.isSelected()) {
				try {
					m = new Memory(memorySizesBox.getSelectedValue(), pageSizesBox.getSelectedValue());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				Config.MEMORY_TYPE =  Config.PAGINATION;
			}else {
				try {
					m = new Memory(memorySizesBox.getSelectedValue());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				Config.MEMORY_TYPE =  Config.SEGMENTATION;
			}
			
			if(m != null) {
				Config.Cpu.setMemory(m);
				Config.Cpu.updateCPU();
			}
			
			((MainPanel) this.getParent()).setCard(MainPanel.SIMULATION);
			Config.Cpu.start();
		});
		
		SPanel centerPanel = new SPanel(new GridBagLayout());
		centerPanel.setPreferredSize(new Dimension(800, 150));
		centerPanel.add(quantumP, 0, 0);
		centerPanel.add(memorySizeP, 0, 1);
		centerPanel.add(memoryTypeP, 1, 0);
		centerPanel.add(pageSizeP, 1, 1);
		centerPanel.add(okButton, 3, 1);
		content.add(centerPanel, BorderLayout.CENTER);

		this.add(title, BorderLayout.NORTH);
		this.add(content);
	}
}

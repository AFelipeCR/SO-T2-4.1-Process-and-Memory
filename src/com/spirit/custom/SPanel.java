package com.spirit.custom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class SPanel extends JPanel {
	public SPanel() {
		super();
		this.setBackground(Color.WHITE);
	}

	public SPanel(LayoutManager layout) {
		super(layout);
		this.setBackground(Color.WHITE);
	}

	public SPanel(Border border) {
		super();
		this.setBorder(border);
		this.setBackground(Color.WHITE);
	}

	public SPanel(String title) {
		super();
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.setBackground(Color.WHITE);
	}

	@Override
	public Component add(Component comp) {
		comp.setBackground(this.getBackground());
		return super.add(comp);
	}
	
	public void setPreferredSize(int width, int height) {
		super.setPreferredSize(new Dimension(width, height));
	}

	public void add(Component comp, int row, int col) {
		comp.setBackground(this.getBackground());
		super.add(comp, new GridBagConstraints(col, row, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 1,
				new Insets(0, 0, 0, 0), 0, 0));
	}

	public void add(Component comp, int row, int col, int colspan) {
		comp.setBackground(this.getBackground());
		super.add(comp, new GridBagConstraints(col, row, colspan, 1, 1, GridBagConstraints.BOTH,
				GridBagConstraints.CENTER, 1, new Insets(0, 0, 0, 0), 0, 0));
	}
}

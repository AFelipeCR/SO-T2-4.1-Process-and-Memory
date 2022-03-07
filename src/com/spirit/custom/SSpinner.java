package com.spirit.custom;

import java.awt.Dimension;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;


@SuppressWarnings("serial")
public class SSpinner extends JSpinner {
	public SSpinner() {
		super();
	}
	
	public SSpinner(SpinnerModel model) {
		super(model);
	}
	
	public SSpinner(int min, int max) {
		super();
		this.addChangeListener((ChangeEvent e) -> {
			if(this.getNumberValue() < min)
				this.setValue(min);
			
			if(this.getNumberValue() > max)
				this.setValue(max);
		});
	}
	
	public SSpinner(int min) {
		super();
		this.addChangeListener((ChangeEvent e) -> {
			if(this.getNumberValue() < min)
				this.setValue(min);
		});
	}
	
	
	public int getNumberValue() {
		return (int) super.getValue();
	}
	
	public void setPreferredSize(int width, int height) {
		super.setPreferredSize(new Dimension(width, height));
	}
}

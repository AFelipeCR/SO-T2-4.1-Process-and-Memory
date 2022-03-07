package com.spirit.custom;

import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class SButton extends JButton {

	public SButton(String text) {
		super(text);
	}
	
	public SButton(String text, ActionListener listener) {
		super(text);
		this.addActionListener(listener);
	}
}

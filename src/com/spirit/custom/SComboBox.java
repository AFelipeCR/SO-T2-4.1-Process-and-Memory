package com.spirit.custom;

import javax.swing.JComboBox;

@SuppressWarnings({ "serial" })
public class SComboBox<E> extends JComboBox<E> {
	public SComboBox() {
		super();
	}
	
	public SComboBox(E[] values) {
		super(values);
	}
	

	@SuppressWarnings("unchecked")
	public E getSelectedValue() {
		return (E) super.getSelectedItem();
	}
}

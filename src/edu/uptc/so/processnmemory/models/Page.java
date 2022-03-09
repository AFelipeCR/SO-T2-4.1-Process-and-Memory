package edu.uptc.so.processnmemory.models;

/**
 * Representaci�n de una p�gina
 * @author Felipe
 *
 */
public class Page {
	public final Process process;
	private Frame frame;
	public final int size;
	
	public Page(Process process, int size) {
		this.process = process;
		this.size = size;
	}

	public void setFrame(Frame frame) {
		if(frame != null)
			frame.setPage(this);
		
		this.frame = frame;
	}
	
	public Frame getFrame() {
		return frame;
	}
}

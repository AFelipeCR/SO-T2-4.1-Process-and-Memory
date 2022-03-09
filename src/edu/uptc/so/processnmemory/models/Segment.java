package edu.uptc.so.processnmemory.models;

/**
 * Representación de un segmento en memoria
 * @author Felipe
 *
 */
public class Segment {
	public final int id;
	private Process process;
	
	public Segment(int id, Process process) {
		this.id = id;
		this.process = process;
	}
	
	public Process getProcess() {
		return process;
	}
}

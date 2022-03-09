package edu.uptc.so.processnmemory.models;

import java.util.ArrayList;
import java.util.List;

import edu.uptc.util.Util;

/**
 * Representación de la memoria
 * @author Felipe
 *
 */
public class Memory {
	public final int size, frameSize;
	private final Frame[] frames;
	private List<Frame> freeFrames;
	private List<Segment> segments;

	/**
	 * Paginación
	 * @param size Tamaño de la memoria
	 * @param frameSize Tamaño de los marcos
	 * @throws Exception Lanza una excepción si el tamaño de la memoria o del marco n oes potencia de 2
	 */
	public Memory(int size, int frameSize) throws Exception {
		if (!Util.isPowerOfTwo(size))
			throw new Exception("Size is not power of 2");

		if (!Util.isPowerOfTwo(frameSize))
			throw new Exception("Frame size is not power of 2");

		this.size = size;
		this.frameSize = frameSize;
		this.frames = new Frame[size / frameSize];
		this.freeFrames = new ArrayList<Frame>();

		for (int i = 0; i < this.frames.length; i++) {
			this.freeFrames.add(this.frames[i] = new Frame(i, this.frameSize));
		}
	}
	
	/**
	 * Segmentación
	 * @param size Tamaño de la memoria
	 * @throws Exception Lanza una excepción si el tamaño de la memoria o del marco n oes potencia de 2
	 */
	public Memory(int size) throws Exception {
		if (!Util.isPowerOfTwo(size))
			throw new Exception("Size is not power of 2");

		this.size = size;
		this.segments = new ArrayList<Segment>();
		this.frameSize = Integer.MIN_VALUE;
		this.frames = null;
	}

	/**
	 * Añade un proceso a la memoria y dependiendo de la configuración de la memoria,
	 * selecciona Segmentación o Paginación
	 * @param process Proceso a agregar
	 */
	public void add(Process process) {
		if (this.frames != null) {
			process.initPages(this.frameSize);

			for (Page page : process.getPages()) {
				page.setFrame(this.freeFrames.remove(0));
			}
		}else {
			int id = this.freeSegment((int) Math.ceil((double) process.size / 8));
			
			if(id != Integer.MIN_VALUE) {
				this.segments.add(new Segment(id, process));
				this.segments.sort((Segment s1, Segment s2) -> s1.id - s2.id);
			}
		}
	}

	/**
	 * Elimina un proceso de la memoria
	 * @param process Proceso a elimnar
	 */
	public void remove(Process process) {
		if (this.frames != null) {
			for (Page page : process.getPages()) {
				if (page.getFrame() != null) {
					this.freeFrames.add(page.getFrame());
					page.getFrame().setPage(null);
				}
			}

			this.freeFrames.sort((Frame f1, Frame f2) -> f1.id - f2.id);
		}else {
			this.segments.removeIf((Segment s) -> s.getProcess() == process);
		}
	}

	public Frame[] getFrames() {
		return frames;
	}

	public List<Segment> getSegments() {
		return segments;
	}
	
	/**
	 * Busca un segmento libre con suficiente tamaño para el proceso
	 * @param size Tamaño del proceso
	 * @return Posición de inicio del segmento de memoria
	 */
	private int freeSegment(int size) {
		if(this.segments.isEmpty())
			return 0;
		
		int p = 0, diff;
		Segment segment;
		
		for (int i = 0; i < this.segments.size(); i++) {
			segment = this.segments.get(i);
			diff = segment.id - p;
			
			if(diff >= size) {
				return p;
			}else {
				p = (int) (segment.id + Math.ceil((double) segment.getProcess().size / 8));
			}
		}

		return p;
	}
}

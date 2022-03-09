package edu.uptc.so.processnmemory.models;

/**
 * Representación de un proceso
 * @author Felipe
 *
 */
public class Process {
	private Page[] pages;
	private ProcessState state;
	public final String pId;
	public final int exTime, size;
	public int currentExTime;
	private boolean isWaiting;

	public Process(String pId, int exTime, int size) {
		this.pId = pId;
		this.exTime = this.currentExTime = exTime;
		this.state = ProcessState.CREATED;
		this.size = size;
	}
	
	public ProcessState getState() {
		return state;
	}

	public int getCurrentTime() {
		return currentExTime;
	}
	
	/**
	 * A partir de un salto de tiempo se realiza la verificacion de que el proceso termine
	 * @param unitTime Unidad de tiempo
	 */
	public void step(int unitTime) {
		this.currentExTime -= unitTime;
		
		if(this.currentExTime <= 0) {
			this.state = ProcessState.TERMINATED;
		}
	}
	
	/**
	 * Establece el estado Listo del proceso 
	 * a partir de su creacion
	 */
	public void start() {
		if(this.state == ProcessState.CREATED)
			this.state = ProcessState.READY;
	}
	
	/**
	 * Establece el estado Ejecutando del proceso
	 */
	public void run() {
		if(this.state == ProcessState.READY)
			this.state = ProcessState.EXECUTING;
	}
	
	/**
	 * Establece el estado Listo del proceso 
	 * a partir de su ejecución
	 */
	public void interrupt() {
		if(this.state == ProcessState.EXECUTING)
			this.state = ProcessState.READY;
	}
	
	/**
	 * Establece el estado Esperando CPU a 
	 * partir de la ejecució nde proceso
	 */
	public void waitCPU() {
		if(this.state == ProcessState.EXECUTING) {
			this.state = ProcessState.WAITING_CPU;
			this.isWaiting = true;
		}
	}
	
	/**
	 * Establece el estado Listo del proceso
	 * a partir del estado Esperando CPU
	 */
	public void reschedule() {
		if(this.state == ProcessState.WAITING_CPU)
			this.state = ProcessState.READY;
	}
	
	/**
	 * Establece el estado Terminado a partir
	 * del estado Ejecutando
	 */
	public void terminate() {
		if(this.state == ProcessState.EXECUTING)
			this.state = ProcessState.TERMINATED;
	}
	
	public String getInfo() {
		return this.pId + " " + this.exTime;
	}
	
	/**
	 * A partir del tamaño de página pageSize
	 * se generan las paginas del proceso
	 * @param pageSize Tamaño de pagina
	 */
	public void initPages(int pageSize) {
		if(this.pages == null) {
			this.pages = new Page[(int) Math.ceil((double) this.size / pageSize)];
			
			for (int i = 0; i < this.pages.length; i++) {
				this.pages[i] = new Page(this, pageSize);
			}
		}
	}
	
	/**
	 * Si las páginas se han generado
	 * retorna el array con las páginas
	 * @return
	 */
	public Page[] getPages() {
		return pages;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isWaiting() {
		return isWaiting;
	}
	
	public void setWaiting(boolean isWaiting) {
		this.isWaiting = isWaiting;
	}
}

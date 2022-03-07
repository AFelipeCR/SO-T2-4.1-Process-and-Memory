package edu.uptc.so.processnmemory.models;

public class Process {
	private Page[] pages;
	private ProcessState state;
	public final String pId;
	public final int exTime, size;
	public int currentExTime;
	private boolean isWaiting;
	
	public Process(String pId, int exTime) {
		this.pId = pId;
		this.exTime = this.currentExTime = exTime;
		this.state = ProcessState.CREATED;
		this.size = 17;
	}
	
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

	public void step(int unitTime) {
		this.currentExTime -= unitTime;
		
		if(this.currentExTime == 0) {
			this.state = ProcessState.TERMINATED;
		}
	}
	
	public void start() {
		if(this.state == ProcessState.CREATED)
			this.state = ProcessState.READY;
	}
	
	public void run() {
		if(this.state == ProcessState.READY)
			this.state = ProcessState.EXECUTING;
	}
	
	public void interrupt() {
		if(this.state == ProcessState.EXECUTING)
			this.state = ProcessState.READY;
	}
	
	public void waitCPU() {
		if(this.state == ProcessState.EXECUTING) {
			this.state = ProcessState.WAITING_CPU;
			this.isWaiting = true;
		}
	}
	
	public void reschedule() {
		if(this.state == ProcessState.WAITING_CPU)
			this.state = ProcessState.READY;
	}
	
	public void terminate() {
		if(this.state == ProcessState.EXECUTING)
			this.state = ProcessState.TERMINATED;
	}
	
	public String getInfo() {
		return this.pId + " " + this.exTime;
	}
	
	public int pagesQuantity(int pageSize) {
		return this.pages.length;
	}
	
	public void initPages(int pageSize) {
		if(this.pages == null) {
			this.pages = new Page[(int) Math.ceil((double) this.size / pageSize)];
			
			for (int i = 0; i < this.pages.length; i++) {
				this.pages[i] = new Page(this, pageSize);
			}
		}
	}
	
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

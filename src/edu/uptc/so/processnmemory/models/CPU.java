package edu.uptc.so.processnmemory.models;

import java.util.LinkedList;
import java.util.Queue;

import edu.uptc.so.processnmemory.config.Config;
import edu.uptc.util.TestCallback;

public class CPU implements Runnable{
	public Memory memory;
	public int quantum, unitTime;
	private int currentTime, nextQuantum;
	private Process processing;
	private Queue<Process> processesQ;
	private TestCallback tcb;
	private Thread thread;
	private volatile boolean isRunning;

	public CPU(int quantum, int unitTime, Memory memory) {
		this.thread = new Thread(this);
		this.processesQ = new LinkedList<Process>();
		this.quantum = quantum;
		this.unitTime = unitTime;
		this.currentTime = 0;
		this.memory = memory;
	}
	
	public CPU(int quantum, int unitTime) {
		this.thread = new Thread(this);
		this.processesQ = new LinkedList<Process>();
		this.quantum = quantum;
		this.currentTime = 0;
		this.unitTime = unitTime;
		this.memory = null;
	}
	
	public CPU(Memory memory) {
		this.thread = new Thread(this);
		this.processesQ = new LinkedList<Process>();
		this.quantum = Config.QUANTUM;
		this.unitTime = Config.UNIT_TIME;
		this.currentTime = 0;
		this.memory = memory;
	}
	
	public CPU() {
		this.thread = new Thread(this);
		this.processesQ = new LinkedList<Process>();
		this.quantum = Config.QUANTUM;
		this.unitTime = Config.UNIT_TIME;
		this.currentTime = 0;
		this.memory = null;
	}

	public void add(Process process) {
		if (this.processesQ.isEmpty() && this.processing == null) {
			process.start();
			this.processing = process;
			this.processing.run();
			this.nextQuantum = this.currentTime + this.quantum;
		} else {
			this.processesQ.add(process);
			process.start();
		}
		
		this.memory.add(process);
	}

	public void step() {
		if (this.processing != null) {
			if (this.currentTime == this.nextQuantum && !this.processesQ.isEmpty()) {
				Process p = this.processing;
				this.processing = this.processesQ.poll();
				this.processesQ.add(p);
				p.waitCPU();
				this.processing.run();
				p.reschedule();
				this.nextQuantum = this.currentTime + quantum;
			}
			
			if (this.processing != null) {
				
				this.processing.step(this.unitTime);
				
				if (this.processing.getState() == ProcessState.TERMINATED) {
					this.memory.remove(this.processing);
					if (!this.processesQ.isEmpty()) {
						this.processing = this.processesQ.poll();
						this.processing.run();
						this.nextQuantum = this.currentTime + quantum + 1;
					}
				}
			}
			

		} else if (!this.processesQ.isEmpty()) {
			this.processing = this.processesQ.poll();
			this.processing.run();
		}

		this.currentTime += this.unitTime;
	}

	@Override
	public void run() {
		while (true) {
			while(this.isRunning) {
				try {
					Thread.sleep(10000 / Config.SPEED);
				} catch (InterruptedException e) {
				}
				
				if(this.tcb != null)
					this.tcb.run(currentTime);
				
				this.step();
			}
		}
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setTestCallback(TestCallback cb) {
		this.tcb = cb;
	}
	
	public void setMemory(Memory memory) {
		this.memory = memory;
	}
	
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public void start() {
		if(!this.thread.isAlive())
			this.thread.start();
	}

	public void interrumpt() {
		this.thread.interrupt();
	}
	
	public void updateCPU(){
		this.quantum = Config.QUANTUM;
		this.unitTime = Config.UNIT_TIME;
	}
}

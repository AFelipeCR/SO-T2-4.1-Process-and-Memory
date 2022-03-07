package edu.uptc.so.processnmemory.config;

import edu.uptc.so.processnmemory.models.CPU;

public class Config {
	public static final int PAGINATION = 0;
	public static final int SEGMENTATION = 1;
	
	public static int currentPID = 0;
	public static int SPEED = 50;
	public static int QUANTUM = 10;
	public static int UNIT_TIME = 1;
	public static int MEMORY_TYPE = PAGINATION;
	
	public static final CPU Cpu = new CPU();
}

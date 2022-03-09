package edu.uptc.so.processnmemory.config;

import edu.uptc.so.processnmemory.models.CPU;

/**
 * Configuración de la aplicacion
 * @author Felipe
 *
 */
public class Config {
	public static final int PAGINATION = 0;
	public static final int SEGMENTATION = 1;
	
	/**
	 * Contador para la numeración de procesos
	 */
	public static int currentPID = 0;
	
	/**
	 * Valor de velocidad
	 */
	public static int SPEED = 50;
	
	/**
	 * Unidad de quantum
	 */
	public static int QUANTUM = 10;
	
	/**
	 * Unidad de tiempo
	 */
	public static int UNIT_TIME = 1;
	
	/**
	 * Tipo de memoria
	 */
	public static int MEMORY_TYPE = PAGINATION;
	
	/**
	 * Cpu de la simulación
	 */
	public static final CPU Cpu = new CPU();
}

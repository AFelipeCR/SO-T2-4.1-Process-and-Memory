package edu.uptc.so.processnmemory.models;

/**
 * Estados posibles de un proceso
 * @author Felipe
 *
 */
public enum ProcessState {
	CREATED, READY, WAITING_CPU, EXECUTING, WIATING_IO, RECEIVING_IO, TERMINATED
}

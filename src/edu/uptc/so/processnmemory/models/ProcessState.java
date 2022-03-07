package edu.uptc.so.processnmemory.models;

public enum ProcessState {
	CREATED, READY, WAITING_CPU, EXECUTING, WIATING_IO, RECEIVING_IO, TERMINATED
}

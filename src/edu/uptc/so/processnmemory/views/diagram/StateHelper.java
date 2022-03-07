package edu.uptc.so.processnmemory.views.diagram;

public class StateHelper {
	public static final int RIGHT = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int UP = 3;
	public static final int CENTER = 10;
	
	public final StateFigure state;
	public final int direction;
	public final int position;
	
	public StateHelper(StateFigure state, int direction, int position) {
		this.state = state;
		this.direction = direction;
		this.position = position;
	}
	
	public StateHelper(StateFigure state, int direction) {
		this.state = state;
		this.direction = direction;
		this.position = CENTER;
	}
}

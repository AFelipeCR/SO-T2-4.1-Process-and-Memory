package edu.uptc.so.processnmemory.views.diagram;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import edu.uptc.so.processnmemory.views.figures.Figure;

public class DiagramFigure extends Figure {
	private List<Figure> figures;
	
	public DiagramFigure() {
		this.figures = new ArrayList<>();
		this.init();
	}

	private void init() {
		this.figures.add(new StateFigure("Created", 50, 50, Figure.GRAY));
		this.figures.add(new StateFigure("Ready", 270, 50, Figure.GRAY));
		this.figures.add(new StateFigure("Executing", 400, 170, Figure.GRAY));
		this.figures.add(new StateFigure("Waiting\nCPU", 600, 50, Figure.RED));
		this.figures.add(new StateFigure("Waiting\nI|O", 170, 290, Figure.RED));
		this.figures.add(new StateFigure("Receiving\nI|O", 50, 170, Figure.RED));
		this.figures.add(new StateFigure("Terminated", 400, 290, Figure.GRAY));
		
		this.figures.add(new TransitionFigure(
				(StateFigure) this.figures.get(0), StateHelper.RIGHT, 
				(StateFigure) this.figures.get(1), StateHelper.LEFT));
		this.figures.add(new TransitionFigure(
				(StateFigure) this.figures.get(1), StateHelper.RIGHT, 
				(StateFigure) this.figures.get(2), StateHelper.UP));
		this.figures.add(new TransitionFigure(
				(StateFigure) this.figures.get(2), StateHelper.RIGHT, 
				(StateFigure) this.figures.get(3), StateHelper.DOWN));
		this.figures.add(new TransitionFigure(
				(StateFigure) this.figures.get(3), StateHelper.UP, 
				(StateFigure) this.figures.get(1), StateHelper.UP));
		this.figures.add(new TransitionFigure(
				(StateFigure) this.figures.get(2), StateHelper.LEFT, StateHelper.UP, 
				(StateFigure) this.figures.get(1), StateHelper.DOWN, StateHelper.RIGHT));
		this.figures.add(new TransitionFigure(
				(StateFigure) this.figures.get(2), StateHelper.LEFT, StateHelper.DOWN, 
				(StateFigure) this.figures.get(4), StateHelper.UP));
		this.figures.add(new TransitionFigure(
				(StateFigure) this.figures.get(4), StateHelper.LEFT, 
				(StateFigure) this.figures.get(5), StateHelper.DOWN));
		this.figures.add(new TransitionFigure(
				(StateFigure) this.figures.get(5), StateHelper.UP, 
				(StateFigure) this.figures.get(1), StateHelper.DOWN, StateHelper.LEFT));
		this.figures.add(new TransitionFigure(
				(StateFigure) this.figures.get(2), StateHelper.DOWN, 
				(StateFigure) this.figures.get(6), StateHelper.UP));
	}
	@Override
	public void draw(Graphics2D g) {
		this.figures.forEach((Figure f) -> f.draw(g));
	}

}

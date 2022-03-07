package edu.uptc.so.processnmemory.views.diagram;

import java.awt.Graphics2D;

import edu.uptc.so.processnmemory.views.figures.Figure;

public class TransitionFigure extends Figure {
	private final int spacer = 15;
	private final StateHelper startHelper;
	private final StateHelper endHelper;

	public TransitionFigure(StateFigure s, int sDir, int sPos, StateFigure e, int eDir, int ePos) {
		this.startHelper = new StateHelper(s, sDir, sPos);
		this.endHelper = new StateHelper(e, eDir, ePos);
	}

	public TransitionFigure(StateFigure s, int sDir, StateFigure e, int eDir, int ePos) {
		this.startHelper = new StateHelper(s, sDir);
		this.endHelper = new StateHelper(e, eDir, ePos);
	}

	public TransitionFigure(StateFigure s, int sDir, int sPos, StateFigure e, int eDir) {
		this.startHelper = new StateHelper(s, sDir, sPos);
		this.endHelper = new StateHelper(e, eDir);
	}

	public TransitionFigure(StateFigure s, int sDir, StateFigure e, int eDir) {
		this.startHelper = new StateHelper(s, sDir);
		this.endHelper = new StateHelper(e, eDir);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(BLUE);

		int sX = 0, sY = 0, eX = 0, eY = 0;

		switch (this.startHelper.direction) {
		case StateHelper.RIGHT:
			sX = this.startHelper.state.x + this.startHelper.state.width;
			sY = this.startHelper.state.y + this.startHelper.state.height / 2;
			break;
		case StateHelper.LEFT:
			sX = this.startHelper.state.x;
			sY = this.startHelper.state.y + this.startHelper.state.height / 2
					+ (this.startHelper.position == StateHelper.UP ? -spacer
							: this.startHelper.position == StateHelper.DOWN ? spacer : 0);
			break;
		case StateHelper.UP:
			sX = this.startHelper.state.x + this.startHelper.state.width / 2;
			sY = this.startHelper.state.y;
			break;
		case StateHelper.DOWN:
			sX = this.startHelper.state.x + this.startHelper.state.width / 2;
			sY = this.startHelper.state.y + this.startHelper.state.height;
			break;
		}

		switch (this.endHelper.direction) {
		case StateHelper.LEFT:
			eX = this.endHelper.state.x;
			eY = this.endHelper.state.y + this.endHelper.state.height / 2;
			g.fillPolygon(
					new int[] {eX - this.spacer / 2, eX, eX - this.spacer / 2}, 
					new int[] {eY - this.spacer / 3, eY, eY + this.spacer / 3}, 3);
			break;
		case StateHelper.UP:
			eX = this.endHelper.state.x + this.endHelper.state.width / 2;
			eY = this.endHelper.state.y;
			g.fillPolygon(
					new int[] {eX - this.spacer / 3, eX, eX + this.spacer / 3}, 
					new int[] {eY - this.spacer / 2, eY, eY - this.spacer / 2}, 3);
			break;
		case StateHelper.DOWN:
			eX = this.endHelper.state.x + this.endHelper.state.width / 2
					+ (this.endHelper.position == StateHelper.RIGHT ? this.spacer
							: this.endHelper.position == StateHelper.LEFT ? -this.spacer : 0);
			eY = this.endHelper.state.y + this.endHelper.state.height;
			g.fillPolygon(
					new int[] {eX - this.spacer / 3, eX, eX + this.spacer / 3}, 
					new int[] {eY + 1 + this.spacer / 2, eY, eY + 1 + this.spacer / 2}, 3);
			break;
		}

		if ((sX == eX || sY == eY) && Math.abs(this.endHelper.direction - this.startHelper.direction) == 2)
			g.drawLine(sX, sY, eX, eY);
		else if (Math.abs(this.endHelper.direction - this.startHelper.direction) == 2) {
			g.drawPolyline(new int[] { sX, sX, eX, eX }, new int[] { sY, sY - this.spacer, sY - this.spacer, eY }, 4);
		} else if (this.endHelper.direction == this.startHelper.direction)
			switch (this.endHelper.direction) {
			case StateHelper.UP:
				g.drawPolyline(new int[] { sX, sX, eX, eX }, new int[] { sY, sY - this.spacer, sY - this.spacer, eY }, 4);
				break;
			}
		else {
			g.drawPolyline(new int[] { sX, eX, eX }, new int[] { sY, sY, eY }, 3);
		}
	}
}

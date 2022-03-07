package edu.uptc.so.processnmemory.views.queue;

import java.awt.Color;
import java.awt.Graphics2D;

import edu.uptc.so.processnmemory.models.Process;
import edu.uptc.so.processnmemory.models.ProcessState;
import edu.uptc.so.processnmemory.views.figures.Figure;

public class ProcessFigure extends Figure {
	private CPUFigure parent;
	public static final int PROCESS_HEIGHT = 30;
	private Color color;
	private int startX;
	private int targetY;
	private int waitDelay;
	private boolean isPaginated;

	private Process process;

	public ProcessFigure(int x, int y, boolean isPaginated) {
		this.x = x;
		this.y = y;
		this.width = 500;
		this.height = PROCESS_HEIGHT;
		this.color = BLUE;
		this.isPaginated = isPaginated;
	}

	public ProcessFigure(CPUFigure parent, Process process, int x, int y, boolean isPaginated) {
		this.parent = parent;
		this.process = process;
		this.startX = x;
		this.x = this.startX - 600;
		this.y = y;
		this.width = 500;
		this.height = PROCESS_HEIGHT;
		this.color = LIGHTGRAY;
		this.targetY = Integer.MAX_VALUE;
		this.waitDelay = 13;
		this.isPaginated = isPaginated;
	}

	@Override
	public synchronized void draw(Graphics2D g) {
		String stateT = "";
		
		if (this.process != null)
			this.animate();

		if (this.process != null)
			if (this.process.getState() == ProcessState.EXECUTING) {
				this.color = GREEN;
				stateT = "Ejecutando";
			}
			else if (this.process.getState() == ProcessState.READY) {
				if(this.waitDelay > 12)
					this.waitDelay = 12;
				
				if(this.waitDelay > 0 && this.process.isWaiting()) {
					this.color = ORANGE;
					stateT = "Esperando CPU";
					this.waitDelay--;
					
					if(this.waitDelay == 0)
						this.process.setWaiting(false);
				}
				else {
					this.color = LIGHTBLUE;
					stateT = "Listo";
				}
			}
			else if (this.process.getState() == ProcessState.WAITING_CPU) {
				this.color = ORANGE;
				stateT = "Esperando CPU";
			}
			else if (this.process.getState() == ProcessState.TERMINATED) {
				stateT = "Terminado";
			}

		g.setColor(this.color);
		g.fillRect(this.x, this.y, this.isPaginated ? this.width : this.width - 100, this.height);
		g.setColor(Color.BLACK);
		g.drawRect(this.x, this.y, this.isPaginated ? this.width : this.width - 100, this.height);

		g.drawLine(this.x + 50, this.y, this.x + 50, this.y + this.height);
		g.drawLine(this.x + 175, this.y, this.x + 175, this.y + this.height);
		g.drawLine(this.x + 300, this.y, this.x + 300, this.y + this.height);
		
		if(this.isPaginated)
			g.drawLine(this.x + 400, this.y, this.x + 400, this.y + this.height);
		
		if (this.process == null)
			g.setColor(Color.WHITE);
		
		g.drawString(this.process == null ? "PID" : this.process.pId, this.x + 10, this.y + this.height / 1.5f);
		g.drawString(this.process == null ? "Time" : this.process.getCurrentTime() + "", this.x + 100,
				this.y + this.height / 1.5f);
		g.drawString(this.process == null ? "State" : stateT, this.x + 210,
				this.y + this.height / 1.5f);
		
		this.drawCenteredString(g, this.process == null ? "Size (bytes)" : this.process.getSize() + "", 
				this.x + 300, this.y, 100, PROCESS_HEIGHT);
		if(this.isPaginated)
		this.drawCenteredString(g, this.process == null ? "Pages" : this.process.getPages().length + "", 
				this.x + 400, this.y, 100, PROCESS_HEIGHT);

		g.setColor(Color.BLACK);
	}

	@Override
	public void animate() {
		if ((this.process.getState() == ProcessState.CREATED || this.process.getState() == ProcessState.EXECUTING
				|| this.process.getState() == ProcessState.WAITING_CPU || this.process.getState() == ProcessState.READY) && this.x < this.startX)
			this.x += 120;
		else if (this.process.getState() == ProcessState.TERMINATED && this.x > this.startX - 600)
			this.x -= 120;
		else if (this.process.getState() == ProcessState.TERMINATED)
			this.destroy();
		
		
		if  (this.y > this.targetY) {
			this.y -= 5;
		} else if (this.targetY != Integer.MAX_VALUE && this.y < this.targetY) {
			this.y += 5;
		}
		
		if (this.process.getState() == ProcessState.READY && this.y == this.parent.headerY + PROCESS_HEIGHT) {
			this.parent.setpToTail(true);
		}
	}

	public void destroy() {
		this.parent.psToDelete.add(this);
	}

	public void setTargetY(int targetY) {
		this.targetY = targetY;
	}

	public Process getProcess() {
		return process;
	}
}

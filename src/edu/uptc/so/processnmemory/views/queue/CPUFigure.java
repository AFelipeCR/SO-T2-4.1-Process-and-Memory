package edu.uptc.so.processnmemory.views.queue;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import edu.uptc.so.processnmemory.config.Config;
import edu.uptc.so.processnmemory.models.CPU;
import edu.uptc.so.processnmemory.models.Process;
import edu.uptc.so.processnmemory.views.figures.Figure;

public class CPUFigure extends Figure {
	private CPU cpu;
	private List<ProcessFigure> processses;
	protected List<ProcessFigure> psToDelete;
	private boolean pToTail;
	public final int headerY;

	public CPUFigure() {
		this.y = 20;
		this.headerY = this.y + 10;
		this.processses = new ArrayList<>();
		this.psToDelete = new ArrayList<ProcessFigure>();
		this.cpu = Config.Cpu;
	}

	@Override
	public synchronized void draw(Graphics2D g) {
		if(this.cpu != null) {
			g.drawString("Tiempo: " + this.cpu.getCurrentTime(), this.x + 10, this.y - 5);
			g.drawString("Quantum: " + this.cpu.quantum, this.x + 200, this.y - 5);
			new ProcessFigure(x + 10, this.headerY, this.cpu.memory.getFrames() != null).draw(g);
			this.processses.forEach((Figure f) -> f.draw(g));
			this.update();
		}
	}

	public void add(Process process) {
		if (this.processses.isEmpty())
			this.processses
					.add(new ProcessFigure(this, process, this.x + 10, this.headerY + ProcessFigure.PROCESS_HEIGHT, this.cpu.memory.getFrames() != null));
		else {
			ProcessFigure pf = new ProcessFigure(this, process, this.x + 10,
					this.processses.get(this.processses.size() - 1).y + ProcessFigure.PROCESS_HEIGHT, this.cpu.memory.getFrames() != null);
			this.processses.add(pf);
		}

		this.cpu.add(process);
	}

	private void update() {
		this.psToDelete.forEach((Figure f) -> this.processses.remove(f));

		if (this.pToTail) {
			this.pToTail = false;
			this.processses.add(this.processses.remove(0));
		}

		for (int i = 1; i <= this.processses.size(); i++) {
			this.processses.get(i - 1).setTargetY(this.headerY + ProcessFigure.PROCESS_HEIGHT * i);
		}
	}

	public void setpToTail(boolean pToTail) {
		this.pToTail = pToTail;
	}

	public void updateCPU() {
		this.cpu = Config.Cpu;
	}
}

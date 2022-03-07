package edu.uptc.so.processnmemory.views.memory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import edu.uptc.so.processnmemory.config.Config;
import edu.uptc.so.processnmemory.models.Frame;
import edu.uptc.so.processnmemory.models.Memory;
import edu.uptc.so.processnmemory.models.Segment;
import edu.uptc.so.processnmemory.views.figures.Figure;

public class MemoryFigure extends Figure {
	private Memory memory;
	private int byteSide;

	public MemoryFigure() {
		this.width = 128;
		this.x = 20;
		this.y = 10;
	}

	@Override
	public void draw(Graphics2D g) {
		if(this.memory != null) {
			g.setColor(Color.BLACK);
			
			g.drawLine(this.x + this.width + 10, this.y, this.x + this.width + 10, this.y + this.height);
			
			for (int i = 0; i <= this.memory.size / 8; i += 8) {
				g.drawLine(this.x + this.width + 5, this.y + i * this.byteSide, this.x + this.width + 15, this.y + i * this.byteSide);
				g.drawString("" + (i * 8), this.x + this.width + 18, this.y + i * this.byteSide + g.getFont().getSize() / 2);
			}

			if (this.memory.frameSize == Integer.MIN_VALUE) {
				this.drawBorderRect(g, this.x, this.y, this.width,
						(int) (this.byteSide * (float) Math.ceil((float) this.memory.size / 8)), GRAY);

				List<Segment> ss = this.memory.getSegments();

				for (int i = 0; i < ss.size(); i++) {
					this.drawBorderRect(g, this.x, this.y + ss.get(i).id * this.byteSide, this.width,
							(int) (this.byteSide * (float) Math.ceil((float) ss.get(i).getProcess().size / 8)),
							this.generateColorFromProcessId(ss.get(i).getProcess().pId), ss.get(i).getProcess().pId);
				}
			} else {
				Frame[] fs = this.memory.getFrames();

				for (int i = 0; i < fs.length; i++) {
					if (fs[i].getPage() == null)
						this.drawBorderRect(g, this.x, this.y + i * this.byteSide, this.width, this.byteSide, GRAY);
					else {
						this.drawBorderRect(g, this.x, this.y + i * this.byteSide, this.width, this.byteSide,
								this.generateColorFromProcessId(fs[i].getPage().process.pId), fs[i].getPage().process.pId);
					}
				}
			}
		}
	}

	private Color generateColorFromProcessId(String id) {
		int s = 0;
		
		for (char c : id.toCharArray()) {
			s += c;
		}

		return new Color((id.charAt(id.length() - 1) * 100) % 256, (s + 200) % 256, (id.length() + s) % 256);
	}

	public int bottom() {
		return this.y + this.height;
	}
	
	public void updateMemory() {
		this.memory = Config.Cpu.memory;
		this.byteSide = (int) (this.memory.frameSize == Integer.MIN_VALUE ? 8: this.memory.frameSize * 1.2f);
		this.height = this.memory.size * byteSide / 8;
	}
}

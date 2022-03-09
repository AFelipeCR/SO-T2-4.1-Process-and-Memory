package edu.uptc.so.processnmemory.models;

/**
 * Representación de un marco
 * @author Felipe
 *
 */
public class Frame {
	private Page page;
	public final int id, size;
	
	public Frame(int id, int size) {
		this.id = id;
		this.size = size;
	}

	public void setPage(Page page) {
		if(page == null)
			this.page.setFrame(null);
		
		this.page = page;
	}
	
	public Page getPage() {
		return page;
	}
}

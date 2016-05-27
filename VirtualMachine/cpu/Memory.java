package tp.pr5.mv.cpu;

import java.util.ArrayList;

import tp.pr5.mv.observers.MemoryObserver;


public class Memory {

	private MemoryCell[] cells;
	private int cont;
	private final int MAX = 99999;
	private ArrayList<MemoryObserver> views;
	
	public Memory() {
		this.cells = new MemoryCell[this.MAX];
		this.cont = 0;
		this.views = new ArrayList<MemoryObserver>();
	}
	
	public int getCont() {
		return this.cont;
	}
	
	public MemoryCell getCell(int n) {
		return this.cells[n];
	}
	
	public void addObserver(MemoryObserver view) {
		this.views.add(view);
	}
	
	public boolean isEmpty() {
		return this.cont == 0;
	}
	
	public int existPosition(int pos) {
		for (int i=0; i<this.cont; i++) {
			if (this.cells[i].getPos() == pos)
				return i;
		}
		
		return -1;	//Si no encuentra la posicion
	}
	
	public int nextPosToInsert(int pos) {
		for (int i=0; i<this.cont; i++){
			if (this.cells[i].getPos() > pos)
				return i;
		}
		
		return this.cont;
	}
	
	public void insertCell (MemoryCell cell) {
		if (isEmpty()) {
			this.cells[0] = cell;
			this.cont++;
		}
		
		else {
			int pos = this.existPosition(cell.getPos());
			
			if (pos != -1)
				this.cells[pos] = cell;	//Se sustituye la celda nueva por la antigua
			
			else {
				pos = this.nextPosToInsert(cell.getPos());
				for (int i=this.cont; i>pos; i--)
					this.cells[i] = this.cells[i-1];
				
				this.cells[pos] = cell;
				this.cont++;
			}
		}
		
		for (MemoryObserver ob: this.views)
			ob.onWrite(cell.getValue(), cell.getPos());
	}
	
	public String toString() {	
		if (this.isEmpty())
			return "Memoria: <vacia>";
		
		else {
			String s = "";
			for (int i=0; i<this.cont; i++)
				s = s + " " + "[" + this.cells[i].getPos() + "]:" + this.cells[i].getValue();
			
			return "Memoria:" + s;
		}		
	}
}

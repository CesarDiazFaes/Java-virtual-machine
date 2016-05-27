package tp.pr5.mv.cpu;

public class MemoryCell {
	
	private int value;
	private int pos;
	
	public MemoryCell(int v, int p) {
		this.value = v;
		this.pos = p;
	}

	public int getValue() {
		return this.value;
	}

	public int getPos() {
		return this.pos;
	}
}

package tp.pr5.mv.cpu;

public class ExecutionManager {
	
	private int currentPc;
	private int nextPc;
	private boolean halt;
	
	public ExecutionManager() {
		this.currentPc = 0;
		this.nextPc = 0;
		this.halt = false;
	}
	
	public boolean getHalt() {
		return this.halt;
	}
	
	public int getCurrentPc() {
		return this.currentPc;
	}

	public int getNextPc() {
		return this.nextPc;
	}
	
	public boolean haltMV() {
		return this.halt = true;
	}
	
	public void setNextPc(int n) {
		this.nextPc = n;
	}
	
	public void incrementPc() {
		if (this.comparePc()) {
			this.nextPc++;
			this.currentPc++;
		}
		
		else
			this.currentPc = this.nextPc;	//En caso de haber un salto, se igualan los punteros.
	}
	
	public void incrementTimes(int times) {
		if (times < 0) {	//Decrementa
			for (int i=times; i<0; i++) {
				this.currentPc--;
				this.nextPc--;
			}
		}
		
		else {	//Incrementa
			for (int i=0; i<times; i++) {
				this.currentPc++;
				this.nextPc++;
			}
		}
	}
	
	public boolean comparePc() {
		return this.nextPc == this.currentPc;
	}
}

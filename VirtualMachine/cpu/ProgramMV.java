package tp.pr5.mv.cpu;

import tp.pr5.mv.instruction.Instruction;

public class ProgramMV {
	
	private Instruction[] prog;
	private int cont;
	private final int MAX = 99999;
	
	public ProgramMV() {
		this.cont = 0;
		this.prog = new Instruction[this.MAX];
	}
	
	public int getCont() {
		return this.cont;
	}
	
	public Instruction getIns(int i) {
		return this.prog[i];
	}
	
	public void addInstruction(Instruction ins) {
		this.prog[this.cont] = ins;
		this.cont++;
	}
	
	public String toString() {
		String separator = System.lineSeparator();
		String line = "El programa introducido es:" + separator;
		
		for (int i=0; i<this.getCont(); i++)
			line = line + i + ": " + this.getIns(i) + separator;
		
		return line;
	}
}

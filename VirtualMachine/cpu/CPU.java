package tp.pr5.mv.cpu;

import java.io.IOException;
import java.util.ArrayList;

import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.observers.*;
import tp.pr5.mv.strategies.*;

public class CPU {
	
	private Memory mem;
	private OperandStack opStack;
	private ProgramMV program;
	private ExecutionManager ex;
	private InStrategy inStrat;
	private OutStrategy outStrat;
	private ArrayList<CPUObserver> views;
	
	public CPU (InStrategy is, OutStrategy os) {
		this.mem = new Memory();
		this.opStack = new OperandStack();
		this.program = new ProgramMV();
		this.ex = new ExecutionManager();
		this.inStrat = is;
		this.outStrat = os;
		this.views = new ArrayList<CPUObserver>();
	}
	
	public boolean isHalt() {
		return this.ex.getHalt();
	}
	
	public void loadProgram(ProgramMV prog) {
		this.program = prog;
		
		for (CPUObserver obs: views) {
			obs.newProgram(this.program.toString());	//Mostramos el programa
			obs.updateInStrat(this.inStrat.updateInFile());	//Para el modo window, actualiza el fichero de entrada
		}
	}
	
	public boolean lastInstruction() {
		return this.ex.getNextPc() >= this.program.getCont();
	}
	
	public void step() throws MyException, IOException {
		Instruction ins = this.program.getIns(this.ex.getCurrentPc());
		for (CPUObserver obs: views)
			obs.currentInstruction(ins);
		
		ins.execute(this.opStack, this.mem, this.ex, this.inStrat, this.outStrat);
		this.ex.incrementPc();	//Incrementamos los punteros de programa
		for (CPUObserver obs: views) {
			obs.onStep();
			obs.programState(this.updateProgram());
			obs.updateInStrat(this.inStrat.updateInFile());
			obs.updateOutStrat(this.outStrat.updateOutFile());
		}
		
		if (this.isHalt() || this.lastInstruction()) {
			for (CPUObserver obs: views)
				obs.onHalt();
		}
	}
	
	public void step(Instruction ins) throws MyException, IOException {
		ins.execute(this.opStack, this.mem, this.ex, this.inStrat, this.outStrat);
	}
	
	public void addStackObs(StackObserver view) {
		this.opStack.addObserver(view);
	}
	
	public void addMemoryObs(MemoryObserver view) {
		this.mem.addObserver(view);
	}
	
	public void addCPUObs(CPUObserver view){
		this.views.add(view);
	}
	
	public String updateProgram() {
		String prog = "";
		
		for (int i=0; i<this.program.getCont(); i++) {
			if (this.ex.getCurrentPc() == i)
				prog = prog + "*   ";
			
			else
				prog = prog + "    ";
			
			prog = prog + i + ":   " + this.program.getIns(i) + '\n';
		}
		
		return prog;
	}
	
	public String toString() {
		String separator = System.lineSeparator();
		String line = "El estado de la máquina tras ejecutar la instrucción es: ";
		
		return line + separator + this.mem.toString() + separator + this.opStack.toString();
	}
}

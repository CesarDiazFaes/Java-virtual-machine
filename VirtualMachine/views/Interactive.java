package tp.pr5.mv.views;

import tp.pr5.mv.controllers.InteractiveController;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.observers.CPUObserver;

public class Interactive implements CPUObserver {
	
	private InteractiveController controller;
	
	public Interactive(InteractiveController cntrl) {
		this.controller = cntrl;
	}
	
	public void prepareProgram(ProgramMV prog, boolean asm) {
		if (!asm)
			prog = this.controller.getUserInstructions();
		
		this.controller.setProgram(prog);
	}
	
	public void startCPU() {
		this.controller.configureCPU();
		this.controller.executeProgram();
	}

	public void currentInstruction(Instruction ins) {
		System.out.println("Comienza la ejecución de " + ins.toString());
	}
	
	public void onStep() {
		this.controller.watchCPU();
	}

	public void newProgram(String prog) {
		System.out.println(prog);
	}

	public void onHalt() {
		System.exit(0);
	}

	public void programState(String prog) {
		
	}

	public void updateInStrat(String in) {
		
	}

	public void updateOutStrat(String out) {
		
	}
}

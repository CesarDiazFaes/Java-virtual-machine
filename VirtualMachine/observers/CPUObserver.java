package tp.pr5.mv.observers;

import tp.pr5.mv.instruction.Instruction;

public interface CPUObserver {
	
	public void currentInstruction(Instruction ins);
	public void newProgram(String prog);
	public void programState(String prog);
	public void updateInStrat(String in);
	public void updateOutStrat(String out);
	public void onHalt();
	public void onStep();
}

package tp.pr5.mv.opBranch;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public class JUMP extends Instruction {
	
	protected int num;
	
	public JUMP(int n) {
		this.num = n;
	}
	
	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) {
		if (this.num >= 0) {
			if (ex.getCurrentPc() == this.num)
				ex.incrementTimes(-1);	//Salta sobre si mismo
		
			else
				ex.setNextPc(this.num);
		}
	}
	
	public Instruction parse(String line){
		String[] words = line.split(" ");
		
			if (words.length == 2){
				int n = Integer.parseInt(words[1]);
				
				if (words[0].equalsIgnoreCase("JUMP"))
					return new JUMP(n);
			}
		
		return null;
	}
	
	public String toString() {
		return "JUMP " + this.num;
	}
}

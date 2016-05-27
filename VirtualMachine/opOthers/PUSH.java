package tp.pr5.mv.opOthers;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public class PUSH extends Instruction {
	
	protected int num;
	
	public PUSH(int n) {
		this.num = n;
	}

	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) {
		op.apilar(num);
	}
	
	public Instruction parse(String line){
		String[] words = line.split(" ");
		
			if (words.length == 2){
				int n = Integer.parseInt(words[1]);
				
				if (words[0].equalsIgnoreCase("PUSH"))
					return new PUSH(n);
			}
		
		return null;
	}
	
	public String toString() {
		return "PUSH " + this.num;
	}

}

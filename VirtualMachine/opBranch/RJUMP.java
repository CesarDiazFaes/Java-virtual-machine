package tp.pr5.mv.opBranch;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public class RJUMP extends Instruction {
	
	protected int num;
	
	public RJUMP(int n) {
		this.num = n;
	}
	
	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) {
		if (this.num != 0)	//Incrementa o decrementa dependiendo del numero introducido
			ex.incrementTimes(this.num-1);
		
		else	//Si es cero, no avanza
			ex.incrementTimes(-1);
	}
	
	public Instruction parse(String line){
		String[] words = line.split(" ");
		
			if (words.length == 2){
				int n = Integer.parseInt(words[1]);
				
				if (words[0].equalsIgnoreCase("RJUMP"))
					return new RJUMP(n);
			}
		
		return null;
	}

	public String toString() {
		return "RJUMP " + this.num;
	}
}

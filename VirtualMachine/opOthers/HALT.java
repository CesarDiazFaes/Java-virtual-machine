package tp.pr5.mv.opOthers;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public class HALT extends Instruction {

	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) {
		ex.haltMV();
	}
	
	public Instruction parse(String line) {
		String[] words = line.split(" ");
				
		if (words.length == 1){
			if (words[0].equalsIgnoreCase("HALT"))
				return new HALT();
		}
		
		return null;
	}
	
	public String toString() {
		return "HALT";
	}
}

package tp.pr5.mv.opOthers;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public class POP extends Instruction {

	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException {
		if (op.isEmpty())
			throw new MyException("Error ejecutando " + this.toString() + ": la pila est� vac�a.");
		
		op.desapilar();
	}
	
	public Instruction parse(String line) {
		String[] words = line.split(" ");
				
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("POP"))
				return new POP();
		}
		
		return null;
	}
	
	public String toString() {
		return "POP";
	}

}

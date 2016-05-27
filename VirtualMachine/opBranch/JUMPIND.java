package tp.pr5.mv.opBranch;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public class JUMPIND extends Instruction {

	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException {
		if (!op.isEmpty()) {
			int n = op.desapilar();
			if (n >= 0)
				ex.setNextPc(n);
			
			else
				throw new MyException("Error ejecutando " + this.toString() + ": posición incorrecta");
		}
		
		else
			throw new MyException("Error ejecutando " + this.toString() + ": la pila está vacía.");
	}
	
	public Instruction parse(String line) {
		String[] words = line.split(" ");
				
		if (words.length == 1){
			if (words[0].equalsIgnoreCase("JUMPIND"))
				return new JUMPIND();
		}
		
		return null;
	}

	public String toString() {
		return "JUMPIND";
	}
}

package tp.pr5.mv.opOthers;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public class FLIP extends Instruction {

	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException {
		if (op.getTop() < 2)
			throw new MyException("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + op.getTop() + ")");
		
		int num1 = op.desapilar();
		int num2 = op.desapilar();
		op.apilar(num1);
		op.apilar(num2);
	}
	
	public Instruction parse(String line) {
		String[] words = line.split(" ");
				
		if (words.length == 1){
			if (words[0].equalsIgnoreCase("FLIP"))
				return new FLIP();
		}
		
		return null;
	}
	
	public String toString() {
		return "FLIP";
	}

}

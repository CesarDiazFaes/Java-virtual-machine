package tp.pr5.mv.opBoolean;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.operationTypes.Boolean;
import tp.pr5.mv.strategies.*;

public class NOT extends Boolean {
	
	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException {
		if (!op.isEmpty()) {
			int num1 = op.desapilar();
			if(this.calculate(num1, num1))
				op.apilar(1);
			else
				op.apilar(0);
		}
		
		else
			throw new MyException("Error ejecutando NOT: la pila está vacía.");
	}
	
	protected boolean calculate(int a, int b) {
		return a==0;
	}
	
	public Instruction parse(String line) {
		String[] words = line.split(" ");
				
		if (words.length == 1){
			if (words[0].equalsIgnoreCase("NOT"))
				return new NOT();
		}
		
		return null;
	}
	
	public String toString() {
		return "NOT";
	}
}

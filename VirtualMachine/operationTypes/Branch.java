package tp.pr5.mv.operationTypes;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public abstract class Branch extends Instruction {
	
	protected int num;
	
	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException {
		if (!op.isEmpty()) {
			int n = op.desapilar();
			if (this.calculate(n) && this.num >= 0) {
				if (ex.getCurrentPc() == this.num)	//Si salta en sí misma, evitamos que aumente el pc
					ex.incrementTimes(-1);
				
				else
					ex.setNextPc(this.num);
			}
		}
		
		else
			throw new MyException("Error ejecutando " + this.toString() + ": la pila está vacía.");
	}
	
	protected abstract boolean calculate(int a);
}

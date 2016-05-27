package tp.pr5.mv.operationTypes;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public abstract class RelBranch extends Instruction {
	
	protected int num;
	
	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException {
		if (!op.isEmpty()) {
			int n = op.desapilar();
			if (this.calculate(n)) {
				if (this.num != 0)	//Incrementa o decrementa dependiendo del numero introducido
					ex.incrementTimes(this.num-1);
				
				else	//Si es cero, no avanza
					ex.incrementTimes(-1);
			}
		}
		
		else
			throw new MyException("Error ejecutando " + this.toString() + ": la pila está vacía");
	}
	
	protected abstract boolean calculate(int a);
}

package tp.pr5.mv.operationTypes;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public abstract class Arithmetic extends Instruction {
	
	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException {
		if (op.getTop() > 1) {
			int num1 = op.desapilar();
			int num2 = op.desapilar();
			op.apilar(this.calculate(num1, num2));
		}
		
		else
			throw new MyException("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + op.getTop() + ")");
	}
	
	protected abstract int calculate(int a, int b);
}

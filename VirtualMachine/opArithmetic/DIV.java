package tp.pr5.mv.opArithmetic;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.operationTypes.Arithmetic;
import tp.pr5.mv.strategies.*;

public class DIV extends Arithmetic {
	
	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException {
		if (op.getTop() > 1) {
			if(op.getValorCima() != 0) { //Error al dividir por 0
				int num1 = op.desapilar();
				int num2 = op.desapilar();
				op.apilar(this.calculate(num1, num2));
			}
			
			else
				throw new MyException("Error ejecutando " + this.toString() + ", no se puede dividir por 0");
		}
		
		else	
			throw new MyException("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + op.getTop() + ")");
	}
	
	protected int calculate(int a, int b) {
		return b / a;
	}
	
	public Instruction parse(String line) {
		String[] words = line.split(" ");
				
		if (words.length == 1){
			if (words[0].equalsIgnoreCase("DIV"))
				return new DIV();
		}
		
		return null;
	}
	
	public String toString(){
		return "DIV";
	}
}

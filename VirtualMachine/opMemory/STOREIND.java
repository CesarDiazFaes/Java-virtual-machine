package tp.pr5.mv.opMemory;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public class STOREIND extends Instruction {

	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException {
		if (op.getTop() > 1) {
			int value = op.desapilar();
			int pos = op.desapilar();
			if (pos >= 0) {
				MemoryCell cell = new MemoryCell(value, pos);
				mem.insertCell(cell);
			}
			
			else
				throw new MyException("Error ejecutando " + this.toString() + ": posición incorrecta");
		}
		
		else
			throw new MyException("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + op.getTop() + ")");
	}
	
	public Instruction parse(String line) {
		String[] words = line.split(" ");
				
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("STOREIND"))
				return new STOREIND();
		}
		
		return null;
	}

	public String toString() {
		return "STOREIND";
	}
}

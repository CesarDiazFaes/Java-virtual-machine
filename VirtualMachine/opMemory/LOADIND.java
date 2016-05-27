package tp.pr5.mv.opMemory;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public class LOADIND extends Instruction {
	
	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException {
		if (!op.isEmpty()) {
			int pos = mem.existPosition(op.desapilar());
			if (pos != -1) {
				MemoryCell cell = mem.getCell(pos);
				op.apilar(cell.getValue());
			}
			
			else
				throw new MyException("Error ejecutando " + this.toString() + ": no existe esa posición en memoria");
		}
		
		else
			throw new MyException("Error ejecutando " + this.toString() + ": la pila está vacía");
	}

	public Instruction parse(String line) {
		String[] words = line.split(" ");
				
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("LOADIND"))
				return new LOADIND();
		}
		
		return null;
	}

	public String toString() {
		return "LOADIND";
	}
}

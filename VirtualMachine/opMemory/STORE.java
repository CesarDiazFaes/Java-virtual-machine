package tp.pr5.mv.opMemory;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public class STORE extends Instruction {
	
	protected int num;
	
	public STORE(int n) {
		this.num = n;
	}

	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException {
		if (this.num < 0)
			throw new MyException("Error ejecutando " + this.toString() + ": posición incorrecta");
		
		else if (op.isEmpty())
			throw new MyException("Error ejecutando " + this.toString() + ": la pila está vacía");
		
		int value = op.desapilar();
		MemoryCell cell = new MemoryCell(value, this.num);
		mem.insertCell(cell);
	}
	
	public Instruction parse(String line){
		String[] words = line.split(" ");
		
			if (words.length == 2) {
				int n = Integer.parseInt(words[1]);
				
				if (words[0].equalsIgnoreCase("STORE"))
					return new STORE(n);
			}
		
		return null;
	}
	
	public String toString() {
		return "STORE " + this.num;
	}
}

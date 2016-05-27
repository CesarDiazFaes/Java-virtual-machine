package tp.pr5.mv.opMemory;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public class LOAD extends Instruction {
	
	protected int num;
	
	public LOAD(int n) {
		this.num = n;
	}

	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException {
		if (this.num < 0)
			throw new MyException("Error ejecutando " + this.toString() + ": posición incorrecta");
		
		int pos = mem.existPosition(this.num);
		if (pos != -1)
			op.apilar(mem.getCell(pos).getValue());
			
		else
			throw new MyException("Error ejecutando " + this.toString() + ": no existe esa posición en memoria");
	}
	
	public Instruction parse(String line){
		String[] words = line.split(" ");
		
			if (words.length == 2){
				int n = Integer.parseInt(words[1]);
				
				if (words[0].equalsIgnoreCase("LOAD"))
					return new LOAD(n);
			}
		
		return null;
	}

	public String toString() {
		return "LOAD " + this.num;
	}
}

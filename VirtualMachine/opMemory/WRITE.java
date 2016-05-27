package tp.pr5.mv.opMemory;

import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.cpu.*;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public class WRITE extends Instruction {
	
	protected int pos;
	protected int val;
	
	public WRITE(int p, int v) {
		this.pos = p;
		this.val = v;
	}

	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException {
		if (this.pos < 0)
			throw new MyException("Error ejecutando " + this.toString() + ": posición negativa");
		
		else {
			MemoryCell cell = new MemoryCell(this.val, this.pos);
			mem.insertCell(cell);
		}
	}
	
	public Instruction parse(String line) {	//No es necesario implementarlo ya que el usuario no tiene acceso a ella como instrucción.
		return null;
	}

	public String toString() {	//De momento no se usa ya que el usuario no puede llamar a esta instrucción directamente.
		return "WRITE " + this.pos + " " + this.val;
	}
}

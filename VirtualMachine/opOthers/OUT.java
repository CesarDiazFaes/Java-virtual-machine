package tp.pr5.mv.opOthers;

import java.io.IOException;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public class OUT extends Instruction {

	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException, IOException {
		if (op.isEmpty())
			throw new MyException("Error ejecutando " + this.toString() + ": la pila está vacía.");
		
		else if (op.getValorCima() < 0)
			throw new MyException("Error ejecutando " + this.toString() + ": valor negativo.");
		
		else {
			int num = op.desapilar();
			char c = (char) num;
			os.write(c);
		}
	}
	
	public Instruction parse(String line) {
		String[] words = line.split(" ");
				
		if (words.length == 1){
			if (words[0].equalsIgnoreCase("OUT"))
				return new OUT();
		}
		
		return null;
	}
	
	public String toString() {
		return "OUT";
	}
}

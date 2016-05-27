package tp.pr5.mv.opOthers;

import java.io.IOException;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.strategies.*;

public class IN extends Instruction {

	public void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws IOException {
		int c = is.read();
		op.apilar(c);
	}

	public Instruction parse(String line) {
		String[] words = line.split(" ");
		
		if (words.length == 1){
			if (words[0].equalsIgnoreCase("IN"))
				return new IN();
		}
		
		return null;
	}

	public String toString() {
		return "IN";
	}
}

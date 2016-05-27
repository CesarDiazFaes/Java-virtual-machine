package tp.pr5.mv.opArithmetic;

import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.operationTypes.Arithmetic;

public class SUB extends Arithmetic{
	
	protected int calculate(int a, int b) {
		return b - a;
	}
	
	public Instruction parse(String line) {
		String[] words = line.split(" ");
				
		if (words.length == 1){
			if (words[0].equalsIgnoreCase("SUB"))
				return new SUB();
		}
		
		return null;
	}
	
	public String toString(){
		return "SUB";
	}
}

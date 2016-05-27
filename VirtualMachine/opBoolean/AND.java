package tp.pr5.mv.opBoolean;

import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.operationTypes.Boolean;

public class AND extends Boolean {
	
	protected boolean calculate(int a, int b) {
		return (a != 0 && b != 0);
	}
	
	public Instruction parse(String line) {
		String[] words = line.split(" ");
				
		if (words.length == 1){
			if (words[0].equalsIgnoreCase("AND"))
				return new AND();
		}
		
		return null;
	}
	
	public String toString() {
		return "AND";
	}
}

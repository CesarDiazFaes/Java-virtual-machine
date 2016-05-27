package tp.pr5.mv.opComparison;

import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.operationTypes.Comparison;

public class EQ extends Comparison {
	
	protected boolean calculate(int a, int b) {
		return a==b;
	}
	
	public Instruction parse(String line) {
		String[] words = line.split(" ");
				
		if (words.length == 1){
			if (words[0].equalsIgnoreCase("EQ"))
				return new EQ();
		}
		
		return null;
	}

	public String toString() {
		return "EQ";
	}
}

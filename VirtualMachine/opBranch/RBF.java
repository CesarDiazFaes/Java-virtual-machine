package tp.pr5.mv.opBranch;

import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.operationTypes.RelBranch;

public class RBF extends RelBranch {

	public RBF(int n) {
		this.num = n;
	}
	
	protected boolean calculate(int a) {
		return a == 0;
	}
	
	public Instruction parse(String line){
		String[] words = line.split(" ");
		
			if (words.length == 2){
				int n = Integer.parseInt(words[1]);
				
				if (words[0].equalsIgnoreCase("RBF"))
					return new RBF(n);
			}
		
		return null;
	}

	public String toString() {
		return "RBF " + this.num;
	}
}

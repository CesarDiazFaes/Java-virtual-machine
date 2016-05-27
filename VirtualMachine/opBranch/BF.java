package tp.pr5.mv.opBranch;

import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.operationTypes.Branch;

public class BF extends Branch {
	
	public BF(int n) {
		this.num = n;
	}
	
	public boolean calculate(int a) {
		return a == 0;
	}
	
	public Instruction parse(String line){
		String[] words = line.split(" ");
		
			if (words.length == 2){
				int n = Integer.parseInt(words[1]);
				
				if (words[0].equalsIgnoreCase("BF"))
					return new BF(n);
			}
		
		return null;
	}
	
	public String toString() {
		return "BF " + this.num;
	}
}

package tp.pr5.mv.opBranch;

import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.operationTypes.Branch;

public class BT extends Branch {
	
	public BT(int n) {
		this.num = n;
	}
	
	public boolean calculate(int a) {
		return a != 0;
	}
	
	public Instruction parse(String line){
		String[] words = line.split(" ");
		
			if (words.length == 2){
				int n = Integer.parseInt(words[1]);
				
				if (words[0].equalsIgnoreCase("BT"))
					return new BT(n);
			}
		
		return null;
	}
	
	public String toString() {
		return "BT " + this.num;
	}
}

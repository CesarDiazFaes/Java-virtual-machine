package tp.pr5.mv.comOperations;

import java.io.IOException;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.definedExceptions.MyException;

public class STEPS extends STEP {
	
	protected int num;
	
	public STEPS(int n) {
		this.num = n;
	}
	
	public void executeCommand() throws MyException, IOException {
		int i = 0;
		
		while (i<this.num && !CommandInterpreter.cpu.isHalt() && !CommandInterpreter.cpu.lastInstruction()) {
			CommandInterpreter.cpu.step();
			i++;
		}
	}

	public CommandInterpreter parseComm(String s) {
		String[] words = s.split(" ");
		
		if (words.length == 2) {
			int n = Integer.parseInt(words[1]);
			
			if (words[0].equalsIgnoreCase("STEP") && n > 0)
				return new STEPS(n);
		}
		
		return null;	
	}
}

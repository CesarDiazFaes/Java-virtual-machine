package tp.pr5.mv.comOperations;

import java.io.IOException;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.opOthers.PUSH;

public class CPUSH extends CommandInterpreter {
	
	protected int num;
	
	public CPUSH(int n) {
		this.num = n;
	}

	public void executeCommand() throws IOException, MyException {
		PUSH ins = new PUSH(this.num);
		CommandInterpreter.cpu.step(ins);
	}

	public CommandInterpreter parseComm(String s) {
		String[] words = s.split(" ");
		
		if (words.length == 2) {
			int n = Integer.parseInt(words[1]);
			if (words[0].equalsIgnoreCase("PUSH"))
				return new CPUSH(n);
		}
		
		return null;
	}
}

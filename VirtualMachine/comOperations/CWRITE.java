package tp.pr5.mv.comOperations;

import java.io.IOException;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.opMemory.WRITE;

public class CWRITE extends CommandInterpreter {
	
	protected int val;
	protected int pos;
	
	public CWRITE(int p, int v) {
		this.pos = p;
		this.val = v;
	}

	public void executeCommand() throws IOException, MyException {
		WRITE ins = new WRITE(this.pos, this.val);
		CommandInterpreter.cpu.step(ins);
	}

	public CommandInterpreter parseComm(String s) {
		String[] words = s.split(" ");
		
		if (words.length == 3) {
			int position = Integer.parseInt(words[1]);
			int value = Integer.parseInt(words[2]);
			
			if (words[0].equalsIgnoreCase("WRITE"))
				return new CWRITE(position, value);
		}
		
		return null;
	}
}

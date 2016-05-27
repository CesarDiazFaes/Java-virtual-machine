package tp.pr5.mv.comOperations;

import java.io.IOException;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.definedExceptions.MyException;

public class RUN extends STEP {
	
	public void executeCommand() throws IOException, MyException {
		while (!CommandInterpreter.cpu.isHalt() && !CommandInterpreter.cpu.lastInstruction()) {
			CommandInterpreter.cpu.step();
		}
	}

	public CommandInterpreter parseComm(String s) {
		String[] words = s.split(" ");
		
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("RUN"))
				return new RUN();
		}
		
		return null;	
	}
}

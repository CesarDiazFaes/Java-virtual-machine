package tp.pr5.mv.comOperations;

import java.io.IOException;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.definedExceptions.MyException;

public class STEP extends CommandInterpreter {

	public void executeCommand() throws IOException, MyException {
		CommandInterpreter.cpu.step();
	}

	public CommandInterpreter parseComm(String s) {
		String[] words = s.split(" ");
		
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("STEP"))
				return new STEP();
		}
		
		return null;	
	}
}

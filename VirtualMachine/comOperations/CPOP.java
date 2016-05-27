package tp.pr5.mv.comOperations;

import java.io.IOException;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.opOthers.POP;

public class CPOP extends CommandInterpreter {

	public void executeCommand() throws IOException, MyException {
		POP ins = new POP();
		CommandInterpreter.cpu.step(ins);

	}

	public CommandInterpreter parseComm(String s) {
		String[] words = s.split(" ");
		
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("POP"))
				return new CPOP();
		}
		
		return null;
	}
}

package tp.pr5.mv.comOperations;

import tp.pr5.mv.command.CommandInterpreter;

public class QUIT extends CommandInterpreter {

	public void executeCommand() {
		CommandInterpreter.quit = true;
	}

	public CommandInterpreter parseComm(String s) {
		String[] words = s.split(" ");
		
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("QUIT"))
				return new QUIT();
		}
		
		return null;
	}
}

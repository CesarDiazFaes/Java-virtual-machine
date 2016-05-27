package tp.pr5.mv.command;

import tp.pr5.mv.comOperations.*;

public class CommandParser {
	
	private static CommandInterpreter[] commands = {	//Comandos de la maquina virtual
		new STEP(),
		new STEPS(0),
		new RUN(),
		new QUIT(),
		new CPUSH(0),
		new CPOP(),
		new CWRITE(0, 0),
	};
	
	public static CommandInterpreter parseCommand(String line) {
		try {
			for(CommandInterpreter com: commands) {
				CommandInterpreter command = com.parseComm(line);
				if(command != null)
					return command;
			}
			
			
		} catch (NumberFormatException ex) {
			return null;
		}
		
		return null;
	}
}

package tp.pr5.mv.command;

import java.io.IOException;

import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.definedExceptions.MyException;

public abstract class CommandInterpreter {
	
	protected static CPU cpu;
	protected static boolean quit = false;
	
	public abstract void executeCommand() throws IOException, MyException;
	public abstract CommandInterpreter parseComm(String s);
	
	public boolean isQuit() {
		return CommandInterpreter.quit;
	}
	
	public static void configureCommandInterpreter(CPU cpu) {
		CommandInterpreter.cpu = cpu;
	}
}

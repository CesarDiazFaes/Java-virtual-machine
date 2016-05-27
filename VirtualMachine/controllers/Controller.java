package tp.pr5.mv.controllers;

import java.io.IOException;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;

public abstract class Controller {
	
	protected CPU cpu;
	
	public Controller(CPU cpu) {
		this.cpu = cpu;
	}
	
	public void configureCPU() {
		CommandInterpreter.configureCommandInterpreter(this.cpu);	//Con el programa ya introducido en la CPU, la configuramos
	}
	
	public void setProgram(ProgramMV prog) {
		this.cpu.loadProgram(prog);
	}
	
	public void watchCPU() {
		System.out.println(this.cpu);
	}
	
	public abstract void executeProgram() throws IOException, MyException;
	
}

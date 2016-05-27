package tp.pr5.mv.controllers;

import java.io.IOException;
import java.util.Scanner;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.command.CommandParser;
import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.instruction.InstructionParser;

public class InteractiveController extends Controller {

	public InteractiveController(CPU cpu) {
		super(cpu);
	}
	
	public ProgramMV getUserInstructions() {
		ProgramMV prog = new ProgramMV();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String line;
		
		System.out.println("Introduce el programa fuente:");
		System.out.print("> ");
		line = sc.nextLine();
		while (!line.equalsIgnoreCase("END")) {
			Instruction ins = InstructionParser.parse(line);
			if (ins == null)
				System.err.println("La instrucción " + line + " no existe.");
			
			else
				prog.addInstruction(ins);
			
			System.out.print("> ");
			line = sc.nextLine();
		}
		
		return prog;
	}
	
	public void executeProgram() {
		Scanner sc = new Scanner(System.in);
		String line;
		
		do {
			System.out.print("> ");
			line = sc.nextLine();
			CommandInterpreter command = CommandParser.parseCommand(line);
			try {
				if (command != null) {
					command.executeCommand();
				}
				
				else
					System.err.println("El comando " + line + " es incorrecto");
				
			} catch (IOException e) {
				e.printStackTrace();
				
			} catch (MyException e) {
				System.err.println(e);
			}
			
		} while (!line.equalsIgnoreCase("QUIT") && !this.cpu.isHalt() && !this.cpu.lastInstruction());
		
		sc.close();
	}
}

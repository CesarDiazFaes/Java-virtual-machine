package tp.pr5.mv.controllers;

import java.io.IOException;

import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.opMemory.WRITE;
import tp.pr5.mv.opOthers.*;

public class SwingController extends Controller {
	
	public SwingController(CPU cpu) {
		super(cpu);
	}
	
	public void executeProgram() throws MyException, IOException {
		this.cpu.step();
	}
	
	public void executeRun() throws MyException, IOException {
		while (!cpu.lastInstruction() && !cpu.isHalt()) {
			this.cpu.step();
		}
	}
	
	public void executePush(int num) throws MyException, IOException {
		this.cpu.step(new PUSH(num));
	}
	
	public void executePop() throws MyException, IOException {
		this.cpu.step(new POP());
	}

	public void executeWrite(int v, int p) throws MyException, IOException {
		this.cpu.step(new WRITE(p, v));
	}

	public String showProgram() {
		return this.cpu.updateProgram();
	}
}

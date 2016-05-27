package tp.pr5.mv.controllers;

import java.io.IOException;

import tp.pr5.mv.comOperations.RUN;
import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.definedExceptions.MyException;

public class BatchController extends Controller {

	public BatchController(CPU cpu) {
		super(cpu);
	}

	public void executeProgram() {
		RUN runCom = new RUN();
		try {
			runCom.executeCommand();
			
		} catch (IOException ex) {
			ex.printStackTrace();
			
		} catch (MyException ex) {
			System.err.println(ex);
		}
	}
}

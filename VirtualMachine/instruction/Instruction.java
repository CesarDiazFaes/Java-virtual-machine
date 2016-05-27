package tp.pr5.mv.instruction;

import java.io.IOException;

import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.strategies.*;

public abstract class Instruction {
	
	public abstract void execute(OperandStack op, Memory mem, ExecutionManager ex, InStrategy is, OutStrategy os) throws MyException, IOException;
	public abstract String toString();
	public abstract Instruction parse(String line);
}
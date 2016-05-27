package tp.pr5.mv.instruction;

import tp.pr5.mv.opArithmetic.*;
import tp.pr5.mv.opBoolean.*;
import tp.pr5.mv.opBranch.*;
import tp.pr5.mv.opComparison.*;
import tp.pr5.mv.opMemory.*;
import tp.pr5.mv.opOthers.*;

public class InstructionParser {
	
	private static Instruction[] instructions = {	//Instrucciones de la Maquina Virtual
		new POP(),
		new DUP(),
		new FLIP(),
		new ADD(),
		new SUB(),
		new MUL(),
		new DIV(),
		new OUT(),
		new HALT(),
		new EQ(),
		new LT(),
		new GT(),
		new LE(),
		new NOT(),
		new AND(),
		new OR(),
		new NEG(),
		new JUMPIND(),
		new LOADIND(),
		new STOREIND(),
		new IN(),
		new PUSH(0),
		new LOAD(0),
		new STORE(0),
		new JUMP(0),
		new BT(0),
		new BF(0),
		new RJUMP(0),
		new RBT(0),
		new RBF(0),
	};
	
	public static Instruction parse(String line) {	
		try {
			for(Instruction ins: instructions) {
				Instruction instruction = ins.parse(line);
				if(instruction != null)
					return instruction;
			}
		}
		
		catch(java.lang.NumberFormatException e) {
			return null;
		}
		
		return null;
	}
}

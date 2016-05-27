package tp.pr5.mv;

import java.io.BufferedReader;
import java.io.IOException;

import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.instruction.*;

public class ASMFile {
	
	private BufferedReader file;
	
	public ASMFile(BufferedReader f) {
		this.file = f;
	}
	
	public void loadProgramFromFile(ProgramMV prog) throws IOException, MyException {
		String line = this.file.readLine();
		Instruction ins;

		while (line != null) {
			int pos = this.haveComment(line);
			if (pos != -1)
				line = line.substring(0, pos);
			
			ins = InstructionParser.parse(line);
			if (ins != null)
				prog.addInstruction(ins);
			
			else if (!line.equals(""))
				throw new MyException("Error en fichero ASM. En la linea: " + line);
			
			line = this.file.readLine();
		}
	}
	
	private int haveComment(String ins) {
		for (int i=0; i<ins.length(); i++) {
			if (ins.charAt(i) == ';')
				return i;
		}
		
		return -1;
	}
	
	public void close() throws IOException {
		this.file.close();
	}
}

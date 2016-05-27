package tp.pr5.mv.strategies;

import java.io.IOException;

public class ConsoleInStrategy implements InStrategy {

	public int read() throws IOException {
		return System.in.read();
	}

	public void close() {
		//No hace nada
	}

	public String updateInFile() {
		return null;
	}
}

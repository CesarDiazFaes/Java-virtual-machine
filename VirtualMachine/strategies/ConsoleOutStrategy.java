package tp.pr5.mv.strategies;

public class ConsoleOutStrategy implements OutStrategy {

	public void write(char c) {
		System.out.println(c);
	}

	public void close() {
		//No hace nada
	}

	public String updateOutFile() {
		return null;
	}
}

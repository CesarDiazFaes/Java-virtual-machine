package tp.pr5.mv.strategies;

public class NullOutStrategy implements OutStrategy {

	public void write(char c) {
		System.out.println(-1);
	}
	
	public void close() {
		//No hace nada
	}

	public String updateOutFile() {
		return null;
	}
}

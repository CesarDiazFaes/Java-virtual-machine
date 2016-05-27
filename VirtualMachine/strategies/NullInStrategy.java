package tp.pr5.mv.strategies;

public class NullInStrategy implements InStrategy {

	public int read() {
		return -1;
	}

	public void close() {
		//No hace nada
	}

	public String updateInFile() {
		return null;
	}
}

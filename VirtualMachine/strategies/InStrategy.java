package tp.pr5.mv.strategies;

import java.io.IOException;

public interface InStrategy {
	
	public abstract int read() throws IOException;
	public abstract void close() throws IOException;
	public abstract String updateInFile();
}

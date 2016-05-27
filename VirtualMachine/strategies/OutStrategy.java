package tp.pr5.mv.strategies;

import java.io.IOException;

public interface OutStrategy {
	
	public abstract void write(char c) throws IOException;
	public abstract void close() throws IOException;
	public abstract String updateOutFile();
}

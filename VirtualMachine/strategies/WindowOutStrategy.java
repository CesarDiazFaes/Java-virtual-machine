package tp.pr5.mv.strategies;

import java.io.IOException;

public class WindowOutStrategy implements OutStrategy {
	
	private String FileText = "";
	private OutStrategy outStrat;
	
	public WindowOutStrategy() {
		this.outStrat = new NullOutStrategy();
	}
	
	public WindowOutStrategy(String outFileName) throws IOException {
		this.outStrat = new FileOutStrategy(outFileName);
	}
	
	public String updateOutFile() {
		return this.FileText;
	}

	public void write(char c) throws IOException {
		this.FileText = this.FileText + c;
		this.outStrat.write(c);
	}

	public void close() throws IOException {
		
	}
}
package tp.pr5.mv.strategies;

import java.io.FileReader;
import java.io.IOException;

public class WindowInStrategy implements InStrategy {
	
	private InStrategy inStrat;
	private StringBuilder inText = new StringBuilder();
	private int cont;
	
	public WindowInStrategy() {
		this.inStrat = new NullInStrategy();
	}
	
	public WindowInStrategy(String fileName) throws IOException {
		this.cont = 0;
		this.loadFileText(fileName);
		this.inStrat = new FileInStrategy(fileName);
	}

	public int read() throws IOException {
		this.cont++;
		return this.inStrat.read();
	}
	
	private void loadFileText(String fileName) {
		try {
			FileReader f = new FileReader(fileName);
			int reading = f.read();
			while (reading != -1) {	//Hasta que sea fin de fichero es decir, lea -1
				this.inText.append((char) reading);	//Hacemos un cast del valor leido y lo introducimos en el String
				reading = f.read();
			}
			
			f.close();
		}
		
		catch (IOException ex) {
			System.err.println(ex);
        	System.exit(1);
		}
	}

	public void close() throws IOException {
		
	}
	
	public String updateInFile() {
		String text = "";
		int i = 0;
		
		while (i<this.cont) {
			if (this.inText.length() > i) {
				if (this.inText.charAt(i) == '\n')
					text = text + '\n';
					
				else
					text = text + "*";
			}
			
			i++;
		}
		
		if (this.cont <= this.inText.length())
			text = text + this.inText.substring(i, this.inText.length());
		return text;
	}
}

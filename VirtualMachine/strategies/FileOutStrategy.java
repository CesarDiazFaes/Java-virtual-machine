package tp.pr5.mv.strategies;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutStrategy implements OutStrategy {
	
	private FileOutputStream file;
	
	public FileOutStrategy(String outFileName) throws IOException {
		this.file = new FileOutputStream(outFileName);
	}
	
	public void write(char c) throws IOException {
		if (c == '\n')	//Con esto conseguimos representar los saltos de linea en el fichero de salida
			this.file.write('\r');
		
		this.file.write(c);
	}

	public void close() throws IOException {
		this.file.close();
	}

	public String updateOutFile() {
		return null;
	}
}

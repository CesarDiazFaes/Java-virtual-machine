package tp.pr5.mv.strategies;

import java.io.*;

public class FileInStrategy implements InStrategy {
	
	private FileReader file;
	
	public FileInStrategy(String fileName) throws IOException {
		this.file = new FileReader(fileName);
	}
	
	public int read() throws IOException {
		 return this.file.read();
	}

	public void close() throws IOException {
		this.file.close();
	}

	public String updateInFile() {
		return null;
	}
}

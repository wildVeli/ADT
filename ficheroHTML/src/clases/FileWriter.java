package clases;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class FileWriter {
	private ObjectOutputStream out = null;
	
	public FileWriter(String fileName, boolean append) throws FileNotFoundException, IOException {
		File file = new File(fileName);
		if (append && file.exists()) {
			out = new MyObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName, append)));
		} else {
			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
		}
	}
	
	public FileWriter(String fileName) throws FileNotFoundException, IOException {
		out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
	}
	
	public FileWriter(File file, boolean append) throws FileNotFoundException, IOException {
		if (append && file.exists()) {
			out = new MyObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file, append)));
		} else {
			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		}
	}
	
	public FileWriter(File file) throws FileNotFoundException, IOException {
		out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
	}
	
	public void writeObject(Object o) throws IOException {
		out.writeObject(o);
	}
	
	public void close() {
		if (out != null)
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private class MyObjectOutputStream extends ObjectOutputStream {
		public MyObjectOutputStream(OutputStream out) throws IOException {
			super(out);
		}
		@Override
		public void writeStreamHeader() throws IOException {
			
		}
	}
}
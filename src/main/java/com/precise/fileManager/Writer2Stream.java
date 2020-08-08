package com.precise.fileManager;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.apache.log4j.Logger;

/**
 * Wrapperclass to wrap an OutputStream around a Writer
 */
public class Writer2Stream extends OutputStream{
	Logger logger = Logger.getLogger(Writer2Stream.class.getName());
	public Writer out;

	public Writer2Stream(Writer w) {
		super();
		out = w;
	}

	public void write(int i) throws IOException {
		out.write(i);
	}

	public void write(byte[] b) throws IOException {
		for (int i = 0; i < b.length; i++) {
			int n = b[i];
			//Convert byte to ubyte
			n = ((n >>> 4) & 0xF) * 16 + (n & 0xF);
			out.write(n);
		}
	}

	public void write(byte[] b, int off, int len) throws IOException {
		for (int i = off; i < off + len; i++) {
			int n = b[i];
			n = ((n >>> 4) & 0xF) * 16 + (n & 0xF);
			out.write(n);
		}
	}

}

package com.precise.fileManager;

import java.io.File;

import org.apache.log4j.Logger;

public class FileInfo {
	Logger logger = Logger.getLogger(FileInfo.class.getName());
	public String name = null, clientFileName = null, fileContentType = null;
	private byte[] fileContents = null;
	public File file = null;
	public StringBuffer sb = new StringBuffer(100);

	public void setFileContents(byte[] aByteArray) {
		fileContents = new byte[aByteArray.length];
		System.arraycopy(aByteArray, 0, fileContents, 0, aByteArray.length);
	}

}

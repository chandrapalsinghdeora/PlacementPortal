package com.precise.fileManager;

import java.util.Hashtable;

public class UploadMonitor {

	public static Hashtable<String,UplInfo> uploadTable = new Hashtable<String,UplInfo>();

	public static void set(String fName, UplInfo info) {
		uploadTable.put(fName, info);
	}

	public static void remove(String fName) {
		uploadTable.remove(fName);
	}

	public static UplInfo getInfo(String fName) {
		UplInfo info = (UplInfo) uploadTable.get(fName);
		return info;
	}

}

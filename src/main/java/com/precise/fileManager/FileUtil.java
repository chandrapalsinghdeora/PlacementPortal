package com.precise.fileManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class FileUtil {
	static Logger loggerFile = Logger.getLogger(FileUtil.class.getName());
	
	public static Vector<File> expandFileList(String[] files, boolean inclDirs) {
		Vector<File> v = new Vector<File>();
		if (files == null) return v;
		for (int i = 0; i < files.length; i++){
			loggerFile.info("Size of fileArray :: "+files.length);
			v.add(new File(URLDecoder.decode(files[i])));
		}
		for (int i = 0; i < v.size(); i++) {
			File f = (File) v.get(i);
			if (f.isDirectory()) {
				File[] fs = f.listFiles();
				for (int n = 0; n < fs.length; n++)
					v.add(fs[n]);
				if (!inclDirs) {
					v.remove(i);
					i--;
				}
			}
		}
		return v;
	}
	
	/**
	 * Method to build an absolute path
	 * @param dir the root dir
	 * @param name the name of the new directory
	 * @return if name is an absolute directory, returns name, else returns dir+name
	 */
	public static String getDir(String dir, String name) {
		loggerFile.info("Directory :: "+dir+" name : "+name);
		if (!dir.endsWith(File.separator)) dir = dir + File.separator;
		File mv = new File(name);
		String new_dir = null;
		if (!mv.isAbsolute()) {
			new_dir = dir + name;
		}
		else new_dir = name;
		return new_dir;
	}
	
	/**
	 * Method to create new directory of absolute path
	 * @param name the new_directory of the new directory
	 * @param dir_name the name directory name
	 * @return if new directory created successfully then, returns true, else returns false
	 */
	public String createDir(String name ,String dir_name, boolean fileFlag, String defaultDir) {
		loggerFile.info("createDir() :: New Directory Path :: "+name+" new directory name :: "+dir_name+" defaultDir : "+defaultDir);
		boolean flag = false;
		try{
			if(fileFlag){
				flag = new File(name).mkdirs();
			}else{
				flag = true;
			}
			int userId = 1;
			int file_label = 0;
			file_label = getFileLabel(defaultDir,new File(name).getParent());
			
			String procedureCall = "{call proc_FileManager(?,?,?,?,?,?,?,?,?)}";
			Connection connection = null;
			try {
				connection = new DbConnection().getConnectionDB();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "insert");
				callableSt.setInt(2, 0);
				callableSt.setString(3, dir_name);
				callableSt.setString(4, name);
				callableSt.setString(5, file_label+"");
				callableSt.setString(6, "Directory");
				callableSt.setString(7, null);
				callableSt.setBoolean(8, true);
				callableSt.setInt(9, userId);
				
				callableSt.execute();
				System.out.println("Create new Directory then save record into database");
				
			}catch (SQLException e) {
			    e.printStackTrace();
			} finally {
				if (connection != null)
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		
			
			// call here your database for saving new directory name with label
		}catch(Exception e ){
			e.printStackTrace();
			loggerFile.error("createDir() :: "+name+" is not created due to "+e.getMessage());
		}
		loggerFile.debug("Directory created flag :: "+flag);
		if(flag){
			return "Directory created";
		}else{
			return "Creation of directory " + name + " failed";
		}
	}


	/**
	 * This Method converts a byte size in a kbytes or Mbytes size, depending on the size
	 *     @param size The size in bytes
	 *     @return String with size and unit
	 */
	public static String convertFileSize(long size) {
		loggerFile.info("size :: "+size);
		int divisor = 1;
		String unit = "bytes";
		if (size >= 1024 * 1024) {
			divisor = 1024 * 1024;
			unit = "MB";
		}
		else if (size >= 1024) {
			divisor = 1024;
			unit = "KB";
		}
		if (divisor == 1) return size / divisor + " " + unit;
		String aftercomma = "" + 100 * (size % divisor) / divisor;
		if (aftercomma.length() == 1) aftercomma = "0" + aftercomma;
		return size / divisor + "." + aftercomma + " " + unit;
	}

	/**
	 * Copies all data from in to out
	 * 	@param in the input stream
	 *	@param out the output stream
	 *	@param buffer copy buffer
	 */
	public static void copyStreams(InputStream in, OutputStream out, byte[] buffer) throws IOException {
	//	loggerFile.info("input stram :: "+in+" Output :: "+out);
		copyStreamsWithoutClose(in, out, buffer);
		in.close();
		out.close();
	}

	/**
	 * Copies all data from in to out
	 * 	@param in the input stream
	 *	@param out the output stream
	 *	@param buffer copy buffer
	 */
	public static void copyStreamsWithoutClose(InputStream in, OutputStream out, byte[] buffer)
			throws IOException {
		int b;
		while ((b = in.read(buffer)) != -1)
			out.write(buffer, 0, b);
	}

	/**
	 * Returns the Mime Type of the file, depending on the extension of the filename
	 */
	public static String getMimeType(String fName) {
		loggerFile.info("File Name :: "+fName);
		fName = fName.toLowerCase();
		if (fName.endsWith(".jpg") || fName.endsWith(".jpeg") || fName.endsWith(".jpe")) return "image/jpeg";
		else if (fName.endsWith(".gif")) return "image/gif";
		else if (fName.endsWith(".pdf")) return "application/pdf";
		else if (fName.endsWith(".htm") || fName.endsWith(".html")  || fName.endsWith(".htmls") || fName.endsWith(".shtml")) return "text/html";
		else if (fName.endsWith(".avi")) return "video/x-msvideo";
		else if (fName.endsWith(".mov") || fName.endsWith(".qt")) return "video/quicktime";
		else if (fName.endsWith(".mpg") || fName.endsWith(".mpeg") || fName.endsWith(".mpe")) return "video/mpeg";
		else if (fName.endsWith(".zip")) return "application/zip";
		else if (fName.endsWith(".tiff") || fName.endsWith(".tif")) return "image/tiff";
		else if (fName.endsWith(".rtf")) return "application/rtf";
		else if (fName.endsWith(".mid") || fName.endsWith(".midi")) return "audio/x-midi";
		else if (fName.endsWith(".xl") || fName.endsWith(".xls") || fName.endsWith(".xlsx") || fName.endsWith(".xlv")
				|| fName.endsWith(".xla") || fName.endsWith(".xlb") || fName.endsWith(".xlt")
				|| fName.endsWith(".xlm") || fName.endsWith(".xlk")) return "application/excel";
		else if (fName.endsWith(".doc") || fName.endsWith(".docx") || fName.endsWith(".dot")) return "application/msword";
		else if (fName.endsWith(".png")) return "image/png";
		else if (fName.endsWith(".ppt") || fName.endsWith(".pptx") ) return "application/mspowerpoint";
		else if (fName.endsWith(".js")) return "application/javascript";
		else if (fName.endsWith(".xml")) return "text/xml";
		else if (fName.endsWith(".svg")) return "image/svg+xml";
		else if (fName.endsWith(".mp3")) return "audio/mp3";
		else if (fName.endsWith(".ogg")) return "audio/ogg";
		else return "text/plain";
	}
	
	/**
	 * Converts some important chars (int) to the corresponding html string
	 */
	public static String conv2Html(int i) {
		if (i == '&') return "&amp;";
		else if (i == '<') return "&lt;";
		else if (i == '>') return "&gt;";
		else if (i == '"') return "&quot;";
		else return "" + (char) i;
	}
	/**
	 * Converts a normal string to a html conform string
	 */
	public static String conv2Html(String st) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < st.length(); i++) {
			buf.append(conv2Html(st.charAt(i)));
		}
		return buf.toString();
	}

	/**
	 * Converts a dir string to a linked dir string
	 * 	@param dir the directory string (e.g. /usr/local/httpd)
	 *	@param browserLink web-path to Browser.jsp
	 */
	public static String dir2linkdir(String dir, String browserLink, int sortMode, boolean READ_ONLY,String[] FORBIDDEN_DRIVES) {
		loggerFile.info("Directory :: "+dir+" browserLink : "+browserLink+" sortMode :: "+sortMode);
		File f = new File(dir);
		StringBuffer buf = new StringBuffer();
		boolean forbidden = false;
		
		while (f.getParentFile() != null) {
			forbidden = false;
			for (int i2 = 0; i2 < FORBIDDEN_DRIVES.length; i2++) {
			//	System.out.println("FORBIDDEN_DRIVES :: "+FORBIDDEN_DRIVES[i2]+" entry "+f.getParentFile().getAbsolutePath().toLowerCase());
				if (f.getParentFile().getAbsolutePath().toLowerCase().equals(FORBIDDEN_DRIVES[i2])) {
					forbidden = true;
					break;
				}
				
			}
			if (f.canRead() && !READ_ONLY && !forbidden) {
				String encPath = URLEncoder.encode(f.getAbsolutePath());
				buf.insert(0, "<a href=\"" + browserLink + "?sort=" + sortMode + "&amp;dir="
						+ encPath + "\">" + conv2Html(f.getName()) + File.separator + "</a>");
			}
			else buf.insert(0, conv2Html(f.getName()) + File.separator);
			f = f.getParentFile();
		}
		forbidden = false;
		for (int i2 = 0; i2 < FORBIDDEN_DRIVES.length; i2++) {
		//	System.out.println("FORBIDDEN_DRIVES :: "+FORBIDDEN_DRIVES[i2]+" entry "+f.getAbsolutePath().toLowerCase());
			if (f.getAbsolutePath().toLowerCase().equals(FORBIDDEN_DRIVES[i2])) {
				forbidden = true;
				break;
			}
			
		}
		if (f.canRead() && !READ_ONLY && !forbidden) {
			String encPath = URLEncoder.encode(f.getAbsolutePath());
			buf.insert(0, "<a href=\"" + browserLink + "?sort=" + sortMode + "&amp;dir=" + encPath
					+ "\">" + conv2Html(f.getAbsolutePath()) + "</a>");
		}
		else buf.insert(0, f.getAbsolutePath());
		
		return buf.toString();
	}

	/**
	 *	Returns true if the given filename tends towards a packed file
	 */
	public static boolean isPacked(String name, boolean gz) {
		loggerFile.info("Name : "+name+" packed file flag : "+gz);
		return (name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar")
				|| (gz && name.toLowerCase().endsWith(".gz")) || name.toLowerCase()
				.endsWith(".war"));
	}
	/**
	 *	If RESTRICT_BROWSING = true this method checks, whether the path is allowed or not
	 */
	public static boolean isAllowed(boolean READ_ONLY,boolean RESTRICT_BROWSING,String RESTRICT_PATH,boolean RESTRICT_WHITELIST, File path, boolean write) throws IOException{
		boolean isAllowed = false;
		try{
			loggerFile.info("READ_ONLY : "+READ_ONLY+" RESTRICT_BROWSING : "+RESTRICT_BROWSING+" RESTRICT_PATH : "+RESTRICT_PATH+
					" RESTRICT_WHITELIST : "+RESTRICT_WHITELIST+" path : "+path+" write : "+write);
			if (READ_ONLY && write) 
				isAllowed=false;
			
			if (RESTRICT_BROWSING) {
	            StringTokenizer stk = new StringTokenizer(RESTRICT_PATH, ";");
	            while (stk.hasMoreTokens()){
				    if (path!=null && path.getCanonicalPath().startsWith(stk.nextToken()))
				    	isAllowed = RESTRICT_WHITELIST;
	            }
	            isAllowed = !RESTRICT_WHITELIST;
			}
			else{
				isAllowed =  true;
			}
		}catch(IOException e ){
			e.printStackTrace();
			isAllowed = false;
			throw e;
		}
		loggerFile.info("isAllowed : "+isAllowed);
		return isAllowed;
	}
	
	/**
	 * Method to delete files from directory of absolute path
	 * @param name the new_directory of the new directory
	 * @return if directory created successfully then, returns true, else returns false
	 */
	public static boolean deleteFileList(boolean READ_ONLY,boolean RESTRICT_BROWSING,String RESTRICT_PATH,
			boolean RESTRICT_WHITELIST, Vector<File> v,HttpServletRequest request){
		
		boolean messgae = false;
		try {
			String parentDir = null;
			String deleteName = null;
			for (int i = v.size() - 1; i >= 0; i--) {
				parentDir = null;
				deleteName = null;
				File f = (File) v.get(i);
				try {
					if (!isAllowed(READ_ONLY, RESTRICT_BROWSING, RESTRICT_PATH, RESTRICT_WHITELIST, f, true)) {
						request.setAttribute("error", "You are not allowed to access " + f.getAbsolutePath());
						messgae = true;
						break;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				parentDir = f.getAbsolutePath();
				deleteName = f.getName();
				loggerFile.info("DeleteFile() :: file name :: "+deleteName+" parent directory : "+parentDir);
				boolean deleteFlag = !f.canWrite() || !f.delete();
				loggerFile.info("DeleteFile() :: file deleteFlag : "+deleteFlag);
				if (deleteFlag) {
					request.setAttribute("error", "Cannot delete " + f.getAbsolutePath() + ". Deletion aborted");
					messgae = true;
					break;
				}else{
					// update database of delete file 
					loggerFile.info("DeleteFile() :: file name :: "+deleteName+" parent directory : "+parentDir);
					int userId = 1;
					
					String procedureCall = "{call proc_FileManager(?,?,?,?,?,?,?,?,?)}";
					Connection connection = null;
					try {
						connection = new DbConnection().getConnectionDB();
						CallableStatement callableSt = connection.prepareCall(procedureCall);
						callableSt.setString(1, "delete");
						callableSt.setInt(2, 0);
						callableSt.setString(3, deleteName);
						callableSt.setString(4, parentDir);
						callableSt.setString(5, null);
						callableSt.setString(6, null);
						callableSt.setString(7, null);
						callableSt.setBoolean(8, false);
						callableSt.setInt(9, userId);
						
						callableSt.execute();
						System.out.println("Delete Directory then update record into database");
						
					}catch (SQLException e) {
					    e.printStackTrace();
					} finally {
						if (connection != null)
							try {
								connection.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		loggerFile.info("DeleteFile() :: messgae : "+messgae);
		return messgae;
	}
	
	/**
	 * Method to create new file of absolute path
	 * @param name the new file of directory
	 * @param file_name the name of file
	 * @param currentDirectory the file save directory location 
	 * @return if new file created successfully then returns true, else returns false
	 */
	public String createFiles(String name, String currentDirectory , String file_name, boolean isCreateFile,String defaultDir) {
		loggerFile.info("createFiles() :: file_name : "+file_name+" currentDirectory :"+currentDirectory+" file_path : "+name+" defaultDir : "+defaultDir);
		boolean flag = false;
		try{
			
			int userId = 1;
			if(isCreateFile)
				flag = new File(name).createNewFile();
			else{
				flag = true;
				loggerFile.info("createFiles() :: file_name : "+file_name+" is upload then save database");
			}
			
			int file_label = 0;
			file_label = getFileLabel(defaultDir,currentDirectory);
						
			String procedureCall = "{call proc_FileManager(?,?,?,?,?,?,?,?,?)}";
			Connection connection = null;
			try {
				connection = new DbConnection().getConnectionDB();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "insert");
				callableSt.setInt(2, 0);
				callableSt.setString(3, file_name);
				callableSt.setString(4, name);
				callableSt.setString(5, file_label+"");
				callableSt.setString(6, "File");
				callableSt.setString(7, null);
				callableSt.setBoolean(8, true);
				callableSt.setInt(9, userId);
				
				callableSt.execute();
				System.out.println("insert new create file into database");
				
			}catch (SQLException e) {
			    e.printStackTrace();
			} finally {
				if (connection != null)
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}catch(IOException e ){
			e.printStackTrace();
			loggerFile.error("createFiles() :: "+name+" is not created due to "+e.getMessage());
		}
		loggerFile.debug("createFiles() :: File created then flag :: "+flag);
		if(flag){
			return "File created";
		}else{
			return "Creation of file " + name + " failed";
		}
	}
	
	/**
	 * Method to rename the file
	 * @param file which file is used for rename
	 * @param name the re-name of the file
	 * @return if name of file rename successfully then returns true, else returns false
	 */
	public boolean renameFile(File file,String name) {
		loggerFile.info("renameFile() :: New Name :: "+name+" updated file : "+file);
		boolean flag = false;
		try{
			flag = !file.canWrite() || !file.renameTo(new File(name.trim()));
			// save update file name into database
			int userId = 1;
			String file_name = name.substring(name.lastIndexOf("\\")+1);
			String parent_dir = file.getPath();
			String rename_file = file.getName();
			loggerFile.info("renameFile() :: filename :: "+file_name+" parent dir :: "+parent_dir+" old file name :: "+rename_file);
			String procedureCall = "{call proc_FileManager(?,?,?,?,?,?,?,?,?)}";
			Connection connection = null;
			try {
				connection = new DbConnection().getConnectionDB();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "rename");
				callableSt.setInt(2, 0);
				callableSt.setString(3, file_name);
				callableSt.setString(4, parent_dir);
				callableSt.setString(5,  new File(name.trim()).getAbsolutePath());
				callableSt.setString(6, null);
				callableSt.setString(7,  rename_file);
				callableSt.setBoolean(8, true);
				callableSt.setInt(9, userId);
				
				callableSt.execute();
				System.out.println("Rename file then update record into database");
				
			}catch (SQLException e) {
			    e.printStackTrace();
			} finally {
				if (connection != null){
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}catch(Exception e ){
			e.printStackTrace();
			loggerFile.error("renameFile() :: "+name+" is not created due to "+e.getMessage());
		}
		loggerFile.debug("renameFile() :: Rename File then flag :: "+flag);
		return flag;
	}
	
	/**
	 * Method to moveSelectedFile the file
	 * @param f which file is used for moving
	 * @param currentFileDir the current file location
	 * @param new_dir the move directory location 
	 * @return if moveSelectedFile is moved successfully then returns true, else returns false
	 */
	public boolean moveSelectedFile(String currentFileDir,File f, File newFile, String defaultDir){
		boolean messgae = false;
		String new_dir = null;
		int userId = 1;
		try {
			new_dir = newFile.getCanonicalPath();
			new_dir = new_dir.substring(0, new_dir.lastIndexOf("\\")+1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		loggerFile.info("moveSelectedFile() :: New Name :: "+f.getName()+" currentFileDir : "+currentFileDir+" new_dir:: "+new_dir+" defaultDir : "+defaultDir);
		try{
			String procedureCall = "{call proc_FileManager(?,?,?,?,?,?,?,?,?)}";
			Connection connection = null;
			int file_label = 0;
			file_label = getFileLabel(defaultDir,new_dir);
						
			try {
				connection = new DbConnection().getConnectionDB();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "movefile");
				callableSt.setInt(2, 0);
				callableSt.setString(3, f.getName());
				callableSt.setString(4, newFile.getCanonicalPath());
				callableSt.setString(5,  file_label+"");
				callableSt.setString(6, null);
				callableSt.setString(7,  currentFileDir);
				callableSt.setBoolean(8, true);
				callableSt.setInt(9, userId);
				
				callableSt.execute();
			//	System.out.println("moveSelectedFile then update record into database");
				messgae = true;
			}catch (SQLException e) {
				messgae = false;
			    e.printStackTrace();
			} finally {
				if (connection != null){
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
        
		}catch(Exception e ){
			e.printStackTrace();
			loggerFile.error("moveSelectedFile() :: "+currentFileDir+" is not created due to "+e.getMessage());
		}
		loggerFile.info("moveSelectedFile() :: Move File then result :: "+messgae);
		return messgae;
	}

	
	/**
	 * Method to getFileLabel 
	 * @param str1 the default file location
	 * @param str2 the current file location
	 * @return returns file label
	 */
	 public int getFileLabel(String str1, String str2){
		 loggerFile.info("getFileLabel() str :: "+str1+" str2 :: "+str2);
		 String[] wordsstr1 = str1.split("\\\\"); 
		 String[] wordsstr2 = str2.split("\\\\");
		
		 int count1 = wordsstr1.length;
		 int count2 = wordsstr2.length;
		 
		 int diff = count1-count2;
		 
		 return Math.abs(diff);
	 }

}

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" import="org.apache.log4j.Logger" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.*, java.net.*, java.text.*, java.util.zip.*, java.io.*,com.precise.fileManager.*" %>
<%!static Logger  logger = Logger.getLogger("FileManager");%> 

<%!
	/**
	*If true, all operations (besides upload and native commands) 
	*which change something on the file system are permitted
	*/
	private static boolean READ_ONLY = false;
	//If true, uploads are allowed even if READ_ONLY = true
	private static boolean ALLOW_UPLOAD = true;

    //Allow browsing and file manipulation only in certain directories
	private static final boolean RESTRICT_BROWSING = false;
    //If true, the user is allowed to browse only in RESTRICT_PATH,
    //if false, the user is allowed to browse all directories besides RESTRICT_PATH
    private static final boolean RESTRICT_WHITELIST = false;
    //Paths, sperated by semicolon
    private static final String RESTRICT_PATH = "C:\\"; //Win32: Case important!!
//	private static final String RESTRICT_PATH = "/etc;/var";

    //The refresh time in seconds of the upload monitor window
	private static final int UPLOAD_MONITOR_REFRESH = 2;
	//The number of colums for the edit field
	private static final int EDITFIELD_COLS = 85;
	//The number of rows for the edit field
	private static final int EDITFIELD_ROWS = 30;
	//Open a new window to view a file
	private static final boolean USE_POPUP = true;
	/**
	 * If USE_DIR_PREVIEW = true, then for every directory a tooltip will be
	 * created (hold the mouse over the link) with the first DIR_PREVIEW_NUMBER entries.
	 * This can yield to performance issues. Turn it off, if the directory loads to slow.
	 */
	private static final boolean USE_DIR_PREVIEW = false;
	private static final int DIR_PREVIEW_NUMBER = 10;
	/**
	 * The name of an optional CSS Stylesheet file
	 */
	private static final String CSS_NAME = "fileManager.css";
	/**
	 * The compression level for zip file creation (0-9)
	 * 0 = No compression
	 * 1 = Standard compression (Very fast)
	 * ...
	 * 9 = Best compression (Very slow)
	 */
	private static final int COMPRESSION_LEVEL = 1;
	/**
	 * The FORBIDDEN_DRIVES are not displayed on the list. This can be usefull, if the
	 * server runs on a windows platform, to avoid a message box, if you try to access
	 * an empty removable drive (See KNOWN BUGS in Readme.txt).
	 */
	 private static final String[] FORBIDDEN_DRIVES = {"c:\\", "d:\\", "e:\\", "f:\\"};

	/**
	 * Max time in ms a process is allowed to run, before it will be terminated
	 */
	private static final long MAX_PROCESS_RUNNING_TIME = 30 * 1000; //30 seconds

	//Button names
	private static final String SAVE_AS_ZIP = "Download selected files as zip";
	private static final String RENAME_FILE = "Rename File";
	private static final String DELETE_FILES = "Delete selected files";
	private static final String CREATE_DIR = "Create Dir";
	private static final String CREATE_FILE = "Create File";
	private static final String MOVE_FILES = "Move Files";
	private static final String COPY_FILES = "Copy Files";
	private static final String UPLOAD_FILES = "Upload";

	//Normally you should not change anything after this line
	//----------------------------------------------------------------------------------
	//Change this to locate the tempfile directory for upload (not longer needed)
//	private static String tempdir = "C:\\IIM_TEMP\\";
	
	private static String VERSION_NR = "1.2";
	private static DateFormat dateFormat = DateFormat.getDateTimeInstance();



	static Hashtable<String,UplInfo> uploadTable = new Hashtable<String,UplInfo>();

	static void set(String fName, UplInfo info) {
		uploadTable.put(fName, info);
	}

	static void remove(String fName) {
		uploadTable.remove(fName);
	}

	static UplInfo getInfo(String fName) {
		UplInfo info = (UplInfo) uploadTable.get(fName);
		return info;
	}
	%>
<%
		//Get the current browsing directory
		request.setAttribute("dir", request.getParameter("dir"));
	//	int userid = request.getAttribute("userid")==null?0:(Integer)request.getAttribute("userid");
		int roleId = request.getAttribute("roleId")==null?0:(Integer)request.getAttribute("roleId");
		System.out.println("role id : "+roleId);
		if(roleId==20 || roleId==6 ){
			READ_ONLY = false;
			ALLOW_UPLOAD = true;
		}else{
			READ_ONLY = true;
			ALLOW_UPLOAD = false;
		}
		String currentDirecotry =request.getParameter("dir")==null?"":(String)request.getParameter("dir");
		System.out.println("Current Directory : "+ request.getParameter("dir")+" roleId :: "+roleId);
		//request.setAttribute("userid", request.getAttribute("userid"));
		request.setAttribute("roleId", request.getAttribute("roleId"));
		// The browser_name variable is used to keep track of the URI
		// of the jsp file itself.  It is used in all link-backs.
		//final String browser_name = request.getRequestURI();
		
		final String browser_name =(String) request.getAttribute("browserPath");
	//	final String browser_name = request.getLocalName();
		System.out.println("browser_name :: "+browser_name);
		final String FOL_IMG = "";
		
		boolean nohtml = false;
		boolean dir_view = true;
		//Get Javascript
		System.out.println("Current javascript : "+request.getParameter("javascript"));
		final String defaultPath = getServletContext().getInitParameter("defaultLocation");
		System.out.println("defaultPath "+defaultPath);
		File defaultdir = new File(defaultPath);
		if(!defaultdir.exists()){
			defaultdir.mkdirs();
		}
		if (request.getParameter("javascript") != null) {
			dir_view = false;
			nohtml = true;
			//Tell the browser that it should cache the javascript
			response.setHeader("Cache-Control", "public");
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
			response.setHeader("Expires", sdf.format(new Date(now.getTime() + 1000 * 60 * 60 * 24*2)));
			response.setHeader("Content-Type", "text/javascript");
		}
		// View file
		else if (request.getParameter("file") != null) {
			System.out.println("View File : "+request.getParameter("file"));
            File f = new File(request.getParameter("file"));
            if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,f, false)) {
                request.setAttribute("dir", f.getParent());
                request.setAttribute("error", "You are not allowed to access "+f.getAbsolutePath());
            }
            else if (f.exists() && f.canRead()) {
                if (FileUtil.isPacked(f.getName(), false)) {
                    //If zipFile, do nothing here
                }
                else{
                    String mimeType = FileUtil.getMimeType(f.getName());
                    System.out.println("View File mimeType : "+mimeType);
                    response.setContentType(mimeType);
                    /* if (mimeType.equals("text/plain")) {
                    	response.setHeader(
                            "Content-Disposition", "inline;filename=\""+f.getName()+"\"");
                    }
                    else {
                    	response.setHeader("Content-Disposition", "inline;filename=\""
                            + f.getName() + "\"");
                    } */
                    
                    response.setHeader("Content-Disposition", "inline;filename=\""
                            + f.getName() + "\"");
                    
                    response.setContentLength((int) f.length());
    				InputStream is = new FileInputStream(f);
    				ServletOutputStream outStream = response.getOutputStream();  
    				org.apache.commons.io.IOUtils.copy(is, outStream);
    				is.close();
    				outStream.flush();
                    
                    nohtml = true;
                    dir_view = false;
                }
            }
            else {
                request.setAttribute("dir", f.getParent());
                request.setAttribute("error", "File " + f.getAbsolutePath()
                        + " does not exist or is not readable on the server");
            }
		}
		// Download selected files as zip file
		else if ((request.getParameter("Submit") != null)
				&& (request.getParameter("Submit").equals(SAVE_AS_ZIP))) {
			System.out.println("Download Selected File as zip file");
			Vector<File> v = FileUtil.expandFileList(request.getParameterValues("selfile"), false);
			//Check if all files in vector are allowed
			String notAllowedFile = null;
			for (int i = 0;i < v.size(); i++){
				File f = (File) v.get(i);
				if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,f, false)){
					notAllowedFile = f.getAbsolutePath();
					break;
				}
			}
			if (notAllowedFile != null){
				request.setAttribute("error", "You are not allowed to access " + notAllowedFile);
			}
			else if (v.size() == 0) {
				request.setAttribute("error", "No files selected");
			}
			else {
				System.out.println("Start Creating the zip file , file name ::");
				File dir_file = new File("" + request.getAttribute("dir"));
				System.out.println("dir_file :: "+dir_file.getAbsolutePath()+" :: "+dir_file.getCanonicalPath()+" :: "+dir_file.getName());
				int dir_l = dir_file.getAbsolutePath().length();
				System.out.println("dir_file lenght:: "+dir_l);
				
				// Set the content type based to zip
				response.setContentType("application/zip");
				response.setHeader("Content-Disposition", "attachment;filename=\"files.zip\"");
				
				out.clearBuffer();
				System.out.println("out object :: "+out);
			
				// start create zip file here
				OutputStream outstream =  response.getOutputStream();
		        ZipOutputStream zipOut =  new ZipOutputStream(new BufferedOutputStream(outstream));
		        FileInputStream fis = null;
		        BufferedInputStream fif  = null;
	            zipOut.setComment("Created by File Manager v. " + VERSION_NR);
				zipOut.setLevel(COMPRESSION_LEVEL);
				
	            for (File file : v) {
	            //	System.out.println("Zipping the file: "+file.getName());
	            	
            	 	try {
            	 		fis = null;
           	 		  	fis = new FileInputStream(file);
        			} catch (FileNotFoundException fnfe) {
        				zipOut.write(("ERROR ld not find file " + file.getName()).getBytes());
        				zipOut.closeEntry();
        				System.out.println("Could find file "+ file.getAbsolutePath());
        				continue;
        			}
          	 	 	
	                ZipEntry ze = new ZipEntry(file.getName());
	                try{
	                	zipOut.putNextEntry(ze);
	                	fif  = new BufferedInputStream(fis);
	   	             // Write the contents of the file
	   	    			int data = 0;
	   	    			while ((data = fif.read()) != -1) {
	   	    				zipOut.write(data);
	   	    			}
	                }catch(Exception e ){
	                //	System.out.println("hello dost :: "+e.getMessage());
	                //	e.printStackTrace();
	                }
	                
	    			fif.close();
	    			zipOut.closeEntry();
	                zipOut.flush();
	                fis.close();
            	
	            }
	            
	            // end the zip file here 
	            zipOut.close();
	            outstream.flush();
				nohtml = true;
				dir_view = false;
			}
		}
		// Download file
		else if (request.getParameter("downfile") != null) {
			String filePath = request.getParameter("downfile");
			System.out.println("Download File :: File Path "+filePath);
			File f = new File(filePath);
			if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,f, false)){
				request.setAttribute("dir", f.getParent());
				request.setAttribute("error", "You are not allowed to access " + f.getAbsoluteFile());
			}
			else if (f.exists() && f.canRead()) {
			 	String mimeType = FileUtil.getMimeType(f.getCanonicalPath());
		        if (mimeType == null) {        
		            mimeType = "application/octet-stream";
		        }
		        
		        System.out.println("mimeType: "+mimeType);
				response.setContentType(mimeType);
				response.setHeader("Content-Disposition", "attachment;filename=\"" + f.getName()
						+ "\"");
				response.setContentLength((int) f.length());
				InputStream is = new FileInputStream(f);
				ServletOutputStream outStream = response.getOutputStream();  
				org.apache.commons.io.IOUtils.copy(is, outStream);
				is.close();
				outStream.flush();
				
				 
				/* BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));
				byte buffer[] = new byte[8 * 1024];
				out.clearBuffer();
			//	OutputStream out_s = new Writer2Stream(out);
				FileOutputStream fos = new FileOutputStream(new File(f.getName()));
				//FileUtil.copyStreamsWithoutClose(fileInput, out_s, buffer);
				System.out.println("buffer size : "+buffer.length+" file length : "+f.length());
				int b;
				while ((b = fileInput.read(buffer)) != -1)
					fos.write(buffer, 0, b);
				
				
				fileInput.close();
				fos.flush();
				fos.close(); */
				nohtml = true;
				dir_view = false;
			}
			else {
				request.setAttribute("dir", f.getParent());
				request.setAttribute("error", "File " + f.getAbsolutePath()
						+ " does not exist or is not readable on the server");
			}
		}
		if (nohtml) return;
		//else
			// If no parameter is submitted, it will take the path from file Manager
			if (request.getAttribute("dir") == null) {
				String path = null;
				System.out.println("File Path :: "+ application.getRealPath(request.getRequestURI()));
				if (application.getRealPath(request.getRequestURI()) != null) {
					File f = new File(application.getRealPath(request.getRequestURI())).getParentFile();
					System.out.println("Directory not found then File Path :: "+f.getName());
					//This is a hack needed for tomcat
					while (f != null && !f.exists()){
						f = f.getParentFile();
						System.out.println("Directory not found then parent File name :: "+f.getName());
					}
					if (f != null)
						path = f.getAbsolutePath();
					System.out.println("Directory not found then parent File Path :: "+path);
				}
				if (path == null) { // handle the case where we are not in a directory (ex: war file)
					path = new File(".").getAbsolutePath();
					System.out.println("Path not found then current file Path :: "+path);
				}
				//Check path
                if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,new File(path), false)){
                	//TODO Blacklist
                    if (RESTRICT_PATH.indexOf(";")<0) path = RESTRICT_PATH;
                    else path = RESTRICT_PATH.substring(0, RESTRICT_PATH.indexOf(";"));
                }
                System.out.println("At last directory not found then File Path :: "+path);
             //   path="C:\\IIM\\";
                path = getServletContext().getInitParameter("defaultLocation");
                System.out.println("Set File Path :: "+path+" view folder :: "+(String)request.getParameter("vf"));
                if((String)request.getAttribute("vf")!=null)
                	path = path+ "\\" + (String)request.getAttribute("vf");
                System.out.println("Set File Path :: "+path);
                if(!new File(path).exists()){new File(path).mkdirs(); }
				request.setAttribute("dir", path);
			} %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="expires" content="0">
<meta http-equiv="pragma" content="no-cache">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/manager.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<script type="text/javascript" src="js/manager.js"></script>
<link rel="icon" href="images/favicon.ico" type="image/x-icon"/>


<%
			//If a cssfile exists, it will take it
			String cssPath = null;
			if (application.getRealPath(request.getRequestURI()) != null) 
				cssPath = new File(application.getRealPath(request.getRequestURI())).getParent()
					+ File.separator + CSS_NAME;
			
			if (cssPath == null) cssPath = application.getResource(CSS_NAME).toString();
			
			if (new File(cssPath).exists()) {
	%>
		<link rel="stylesheet" type="text/css" href="css/<%=CSS_NAME%>">
     <%		}
			else if (request.getParameter("uplMonitor") == null) { %>
			
	
	<style type="text/css">
		.textfield {display: inline-block;
    width: auto;
    height: auto;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
input[type=file] {
    display: inline-block !important;
}
		input.textfield {margin: 5px 1px 5px 1px;}
		/* input.button:Hover { color: #444444 } */
		table.filelist {background-color:#666666; width:100%; border:0px none #ffffff}
		.formular {margin: 1px; background-color:#ffffff; padding: 1em; border-top:1px solid #cccccc;}
		.formular2 {margin: 1px;}
		th { background-color:#337ab7; color:#fff; }
		th a{ color:#fff; }
		tr.mouseout { background-color:#ffffff; }
		tr.mousein  { background-color:#eeeeee; }
		tr.checked  { background-color:#cccccc }
		tr.mousechecked { background-color:#c0c0c0 }
		/* td { font-family:Verdana, Arial, Helvetica, sans-serif;  color: #666666;}*/
		td.message { /* background-color: #FFFF00; */font-size: medium;  color: #000000; text-align:center; font-weight:bold}
		td.error { /* background-color: #FF0000; */ font-size: medium; color: #000000; text-align:center; font-weight:bold}
		A { text-decoration: none; }
		A:Hover { color : Red; text-decoration : underline; }
		/* BODY { font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 14px; color: #666666;}*/
	</style>
	<%}
		
        //Check path
        if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,new File((String)request.getAttribute("dir")), false)){
            request.setAttribute("error", "You are not allowed to access " + request.getAttribute("dir"));
        }
		//Upload monitor
		else if (request.getParameter("uplMonitor") != null) { %>
	<style type="text/css">
		BODY { font-family:Verdana, Arial, Helvetica, sans-serif; font-size: 8pt; color: #666666;}
	</style><%
			String fname = request.getParameter("uplMonitor");
			//First opening
			boolean first = false;
			if (request.getParameter("first") != null) first = true;
			System.out.println("Upload File : "+fname+" first "+request.getParameter("first")+" and flag : "+first);
			UplInfo info = new UplInfo();
			if (!first) {
				info = UploadMonitor.getInfo(fname);
				System.out.println("Upload File info: "+info);
				if (info == null) {
					//Windows
					int posi = fname.lastIndexOf("\\");
					if (posi != -1) info = UploadMonitor.getInfo(fname.substring(posi + 1));
					System.out.println("In Windows Upload File Position : "+posi);
				}
				if (info == null) {
					//Unix
					int posi = fname.lastIndexOf("/");
					if (posi != -1) info = UploadMonitor.getInfo(fname.substring(posi + 1));
					System.out.println("In UNIX Upload File Position : "+posi);
				}
			}
			dir_view = false;
			request.setAttribute("dir", null);
			System.out.println("In Windows Upload File aborted : "+info.aborted);
			if (info.aborted) {
				UploadMonitor.remove(fname);
				System.out.println("UploadMonitor File remove, file name : "+fname);
				%>
	</head>
	<body>
		<b>Upload of <%=fname%></b><br><br>Upload aborted.
	</body>
</html><%
			}
			else if (info.totalSize != info.currSize || info.currSize == 0) {
				System.out.println("File name : "+info.totalSize+" ::"+info.currSize);
				System.out.println("browser_name : "+browser_name+" UPLOAD_MONITOR_REFRESH : "+UPLOAD_MONITOR_REFRESH+" urlEncoder of fname"+URLEncoder.encode(fname));
				%>
		<META HTTP-EQUIV="Refresh" CONTENT="<%=UPLOAD_MONITOR_REFRESH%>;URL=<%=browser_name %>?uplMonitor=<%=URLEncoder.encode(fname)%>">
	</head>
	<body>
		<b>Upload of <%=fname%></b><br><br>
		<center><table height="20px" width="90%" bgcolor="#eeeeee" style="border:1px solid #cccccc"><tr>
			<td bgcolor="blue" width="<%=info.getPercent()%>%"></td><td width="<%=100-info.getPercent()%>%"></td>
		</tr></table></center>
		<%=FileUtil.convertFileSize(info.currSize)%> from <%=FileUtil.convertFileSize(info.totalSize)%>
		(<%=info.getPercent()%> %) uploaded (Speed: <%=info.getUprate()%>).<br>
		Time: <%=info.getTimeElapsed()%> from <%=info.getTimeEstimated()%>
	</body>
</html><%
			}
			else {
				UploadMonitor.remove(fname);
				%>
	</head>
	<body onload="javascript:window.close()">
		<b>Upload of <%=fname%></b><br><br>	Upload finished.
	</body>
</html><%
			}
		}
		
		//Click on a filename, special viewer (zip+jar file)
		else if (request.getParameter("file") != null) {
			
			File f = new File(request.getParameter("file"));
            if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,f, false)){
                request.setAttribute("error", "You are not allowed to access " + f.getAbsolutePath());
            }
			else if (FileUtil.isPacked(f.getName(), false)) {
				//ZipFile
				try {
					ZipFile zf = new ZipFile(f);
					Enumeration entries = zf.entries();
%>
	
	</head>
	<body>
		<h2>Content of <%=FileUtil.conv2Html(f.getName())%></h2><br />
		<table class="filelist" cellspacing="1px" cellpadding="0px">
			<th>Name</th><th>Uncompressed size</th><th>Compressed size</th><th>Compression ratio</th><th>Date</th>
<%
					long size = 0;
					int fileCount = 0;
					while (entries.hasMoreElements()) {
						ZipEntry entry = (ZipEntry) entries.nextElement();
						if (!entry.isDirectory()) {
							fileCount++;
							size += entry.getSize();
							long ratio = 0;
							if (entry.getSize() != 0) ratio = (entry.getCompressedSize() * 100)
									/ entry.getSize();
							out.println("<tr class=\"mouseout\"><td>" + FileUtil.conv2Html(entry.getName())
									+ "</td><td>" + FileUtil.convertFileSize(entry.getSize()) + "</td><td>"
									+ FileUtil.convertFileSize(entry.getCompressedSize()) + "</td><td>"
									+ ratio + "%" + "</td><td>"
									+ dateFormat.format(new Date(entry.getTime())) + "</td></tr>");

						}
					}
					zf.close();
					//No directory view
					dir_view = false;
					request.setAttribute("dir", null);
%>
		</table>
		<p align=center>
		<b><%=FileUtil.convertFileSize(size)%> in <%=fileCount%> files in <%=f.getName()%>. Compression ratio: <%=(f.length() * 100) / size%>%
		</b></p>
	</body>
</html>
<%
				}
				catch (ZipException ex) {
					request.setAttribute("error", "Cannot read " + f.getName()
							+ ", no valid zip file");
				}
				catch (IOException ex) {
					request.setAttribute("error", "Reading of " + f.getName() + " aborted. Error: "
							+ ex);
				}
			}
		}
		// Upload
		else if ((request.getContentType() != null)	&& (request.getContentType().toLowerCase().startsWith("multipart"))) {
			if (!ALLOW_UPLOAD){
                request.setAttribute("error", "Upload is forbidden!");
            }
			response.setContentType("text/html");
			HttpMultiPartParser parser = new HttpMultiPartParser();
			boolean error = false;
			try {
				int bstart = request.getContentType().lastIndexOf("oundary=");
				String bound = request.getContentType().substring(bstart + 8);
				int clength = request.getContentLength();
				// temp directory file location for saving file
				String tempdir =  getServletContext().getInitParameter("tempFileLocation");
				System.out.println("tempdir :: "+tempdir);
				System.out.println("Upload() ::  bstart : "+bstart+" bound : "+bound+" clength : "+clength+" tempdir ::"+tempdir);
			//	Hashtable ht = parser.processData(request.getInputStream(), bound,  tempdir, clength);
				Hashtable ht = new Hashtable(1);
				System.out.println("uploadedFile :: "+(FileInfo)request.getAttribute("uploadFileInfo"));
				ht.put("myFile", (FileInfo)request.getAttribute("uploadFileInfo"));
				ht.put("dir",(String)request.getAttribute("dir"));
				System.out.println("Upload() ::hashtable size :: "+ht.size()+" hashtable value of myFile "+ ht.get("myFile") +"  dir :: "+(String)ht.get("dir"));
				String uploadFileName =(String)request.getAttribute("uploadFile")==null?"":(String) request.getAttribute("uploadFile");
				if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,new File((String)ht.get("dir")), false)){
                    //This is a hack, cos we are writing to this directory
                	request.setAttribute("error", "You are not allowed to access " + ht.get("dir"));
                    error = true;
                }
				else if (ht.get("myFile") != null) {
					FileInfo fi = (FileInfo) ht.get("myFile");
					File f = fi.file;
					System.out.println("Upload() :: FileInfo "+fi.file+" file name :: "+f.getName());
				//	UplInfo info = UploadMonitor.getInfo(fi.clientFileName);
					/* if (info != null && info.aborted) {
						f.delete();
						request.setAttribute("error", "Upload aborted");
					} */
					/* else { */
						// Move file from temp to the right dir
						String path = (String) ht.get("dir");
						
						if (!path.endsWith(File.separator)) path = path + File.separator;
						System.out.println("Upload() :: path "+path+" upload file name ::"+f.getName()+path.lastIndexOf("\\")+path.length()+" ::"+(path + f.getName()));
						
						File newFile = new File(path + f.getName());
						System.out.println("File : "+newFile.getName()+" exixts :: "+newFile.exists());
						if(!newFile.exists()){
							boolean movefile = f.renameTo(newFile);
						//	boolean movefile = f.canWrite() || f.renameTo(new File(path + f.getName()));
							System.out.println("Upload() :: movefile "+movefile);
							
							// upload ne file current
							if (!movefile) {
								request.setAttribute("error", "Cannot upload file.");
								error = true;
								f.delete();
							}else{
								// new_file, dir , file_name,true
								System.out.println("file path :: "+(path+f.getName())+" path : "+path+" file name :: "+f.getName()+" defaultPath "+defaultPath);
								new FileUtil().createFiles(path+f.getName() ,path, f.getName(), false, defaultPath) ;
							}
						}else{
							//request.setAttribute("error", "Already file exists so cannot upload file.");
							error = true;
							f.delete();	
						}
					/* } */
				}
				else {
					request.setAttribute("error", "No file selected for upload");
					error = true;
				}
				System.out.println("Upload() :: directory :: "+(String) ht.get("dir"));
				request.setAttribute("dir", (String) ht.get("dir"));
			}
			catch (Exception e) {
				request.setAttribute("error", "Error " + e + ". Upload aborted");
				e.printStackTrace();
				error = true;
			}
			if (!error) request.setAttribute("message", "File upload correctly finished.");
		}
		// The form to edit a text file
		else if (request.getParameter("editfile") != null) {
			System.out.println("Edit File name : "+request.getParameter("editfile"));
			File ef = new File(request.getParameter("editfile"));
            if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,ef, true)){
                request.setAttribute("error", "You are not allowed to access " + ef.getAbsolutePath());
            }
            else{
%>
		
	</head>
	<body>
		<center>
		<h2>Edit <%=FileUtil.conv2Html(request.getParameter("editfile"))%></h2><br />
<%
                BufferedReader reader = new BufferedReader(new FileReader(ef));
                String disable = "";
                if (!ef.canWrite()) disable = " readonly";
                out.println("<form action=\"" + browser_name + "\" method=\"Post\">\n"
                        + "<textarea name=\"text\" wrap=\"off\" cols=\"" + EDITFIELD_COLS
                        + "\" rows=\"" + EDITFIELD_ROWS + "\"" + disable + ">");
                String c;
                // Write out the file and check if it is a win or unix file
                int i;
                boolean dos = false;
                boolean cr = false;
                while ((i = reader.read()) >= 0) {
                    out.print(FileUtil.conv2Html(i));
                    if (i == '\r') cr = true;
                    else if (cr && (i == '\n')) dos = true;
                    else cr = false;
                }
                reader.close();
                //No File directory is shown
                request.setAttribute("dir", null);
                dir_view = false;

%>			</textarea><br /><br />
			<table class="formular">
				<input type="hidden" name="nfile" value="<%= request.getParameter("editfile")%>">
				<input type="hidden" name="sort" value="<%=request.getParameter("sort")%>">
					<tr><td colspan="2"><input type="radio" name="lineformat" value="dos" <%= dos?"":"checked"%> >Ms-Dos/Windows
					<input type="radio" name="lineformat" value="unix" <%= dos?"checked":""%> >Unix
					<input type="checkbox" name="Backup" checked>Write backup</td></tr>
					<tr>
						<td title="Enter the new filename"><input type="text" name="new_name" value="<%=ef.getName()%>">
						<input type="Submit" name="Submit" value="Save"></td>
						</form>
						<form action="<%=browser_name%>" method="Post">
							<td align="left">
								<input type="Submit" name="Submit" value="Cancel">
								<input type="hidden" name="nfile" value="<%= request.getParameter("editfile")%>">
								<input type="hidden" name="sort" value="<%=request.getParameter("sort")%>">
							</td>
						</form>
					</tr>
			</table>
		</center>
		<br />
		
	</body>
</html>
<%
            }
		}
		// Save or cancel the edited file
		else if (request.getParameter("nfile") != null) {
			File f = new File(request.getParameter("nfile"));
			System.out.println("Save File name : "+f.getName()+" submit "+request.getParameter("Submit")+" new name "+ 
			request.getParameter("new_name")+" Back Up :: "+request.getParameter("Backup"));
			if (request.getParameter("Submit").equals("Save")) {
				File new_f = new File(FileUtil.getDir(f.getParent(), request.getParameter("new_name")));
	            if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,new_f, true)){
	                request.setAttribute("error", "You are not allowed to access " + new_f.getAbsolutePath());
	            }
				if (new_f.exists() && new_f.canWrite() && request.getParameter("Backup") != null) {
					File bak = new File(new_f.getAbsolutePath() + ".bak");
					bak.delete();
					new_f.renameTo(bak);
				}
				if (new_f.exists() && !new_f.canWrite()) request.setAttribute("error",
						"Cannot write to " + new_f.getName() + ", file is write protected.");
				else {
					BufferedWriter outs = new BufferedWriter(new FileWriter(new_f));
					StringReader text = new StringReader(request.getParameter("text"));
					int i;
					boolean cr = false;
					String lineend = "\n";
					if (request.getParameter("lineformat").equals("dos")) lineend = "\r\n";
					while ((i = text.read()) >= 0) {
						if (i == '\r') cr = true;
						else if (i == '\n') {
							outs.write(lineend);
							cr = false;
						}
						else if (cr) {
							outs.write(lineend);
							cr = false;
						} else {
							outs.write(i);
							cr = false;
						}
					}
					outs.flush();
					outs.close();
				}
			}
			request.setAttribute("dir", f.getParent());
		}
		//Unpack file to the current directory without overwriting
		else if (request.getParameter("unpackfile") != null) {
			File f = new File(request.getParameter("unpackfile"));
			String root = f.getParent();
			request.setAttribute("dir", root);
            if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,new File(root), true)){
                request.setAttribute("error", "You are not allowed to access " + root);
            }
			//Check if file exists
			else if (!f.exists()) {
				request.setAttribute("error", "Cannot unpack " + f.getName()
						+ ", file does not exist");
			}
			//Check if directory is readonly
			else if (!f.getParentFile().canWrite()) {
				request.setAttribute("error", "Cannot unpack " + f.getName()
						+ ", directory is write protected.");
			}
			//GZip
			else if (f.getName().toLowerCase().endsWith(".gz")) {
				//New name is old Name without .gz
				String newName = f.getAbsolutePath().substring(0, f.getAbsolutePath().length() - 3);
				try {
					byte buffer[] = new byte[0xffff];
					FileUtil.copyStreams(new GZIPInputStream(new FileInputStream(f)), new FileOutputStream(
							newName), buffer);
				}
				catch (IOException ex) {
					request.setAttribute("error", "Unpacking of " + f.getName()
							+ " aborted. Error: " + ex);
				}
			}
			//Else try Zip
			else {
				try {
					ZipFile zf = new ZipFile(f);
					Enumeration entries = zf.entries();
					//First check whether a file already exist
					boolean error = false;
					while (entries.hasMoreElements()) {
						ZipEntry entry = (ZipEntry) entries.nextElement();
						if (!entry.isDirectory()
								&& new File(root + File.separator + entry.getName()).exists()) {
							request.setAttribute("error", "Cannot unpack " + f.getName()
									+ ", File " + entry.getName() + " already exists.");
							error = true;
							break;
						}
					}
					if (!error) {
						//Unpack File
						entries = zf.entries();
						byte buffer[] = new byte[0xffff];
						while (entries.hasMoreElements()) {
							ZipEntry entry = (ZipEntry) entries.nextElement();
							File n = new File(root + File.separator + entry.getName());
							if (entry.isDirectory()) n.mkdirs();
							else {
								n.getParentFile().mkdirs();
								n.createNewFile();
								FileUtil.copyStreams(zf.getInputStream(entry), new FileOutputStream(n),
										buffer);
							}
						}
						zf.close();
						request.setAttribute("message", "Unpack of " + f.getName()
								+ " was successful.");
					}
				}
				catch (ZipException ex) {
					request.setAttribute("error", "Cannot unpack " + f.getName()
							+ ", no valid zip file");
				}
				catch (IOException ex) {
					request.setAttribute("error", "Unpacking of " + f.getName()
							+ " aborted. Error: " + ex);
				}
			}
		}
		// Delete Files
		else if ((request.getParameter("Submit") != null)
				&& (request.getParameter("Submit").equals(DELETE_FILES))) {
			Vector<File> v = FileUtil.expandFileList(request.getParameterValues("selfile"), true);
			boolean error = false;
			//delete backwards
			if(v.size()>0){
				error = FileUtil.deleteFileList(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,v,request);
			}
			if ((!error) && (v.size() > 1)) request.setAttribute("message", "All files deleted");
			else if ((!error) && (v.size() > 0)) request.setAttribute("message", "File deleted");
			else if (!error) request.setAttribute("error", "No files selected");
		}
		// Create Directory
		else if ((request.getParameter("Submit") != null)
				&& (request.getParameter("Submit").equals(CREATE_DIR))) {
			String dir = "" + request.getAttribute("dir");
			String dir_name = request.getParameter("cr_dir");
			String new_dir = FileUtil.getDir(dir, dir_name);
			System.out.println("dir : "+dir+" new dir :: "+new_dir+" dir_name :: "+dir_name);
            if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,new File(new_dir), true)){
                request.setAttribute("error", "You are not allowed to access " + new_dir);
            }
			else {
				String message = new FileUtil().createDir(new_dir,dir_name,true, defaultPath);
				System.out.println("after calling createDir(), then result:: "+message);
				request.setAttribute("message", message);
			}
			
		}
		// Create a new empty file
		else if ((request.getParameter("Submit") != null)
				&& (request.getParameter("Submit").equals(CREATE_FILE))) {
			
			String dir = "" + request.getAttribute("dir");
			String file_name = request.getParameter("cr_dir");
			String new_file = FileUtil.getDir(dir, file_name);
			System.out.println("Creating New File:: Directory : "+dir+" file_name "+file_name+" new name "+ new_file);
			
            if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,new File(new_file), true)){
                request.setAttribute("error", "You are not allowed to access " + new_file);
            }
			// Test, if file_name is empty
			else if (!"".equals(file_name.trim()) && !file_name.endsWith(File.separator)) {
				String msg = new FileUtil().createFiles(new_file, dir , file_name,true, defaultPath );
				request.setAttribute("message",msg);
			}
			else request.setAttribute("error", "Error: " + file_name + " is not a valid filename");
		}
		// Rename a file
		else if ((request.getParameter("Submit") != null)
				&& (request.getParameter("Submit").equals(RENAME_FILE))) {
			Vector<File> v = FileUtil.expandFileList(request.getParameterValues("selfile"), true);
			String dir = "" + request.getAttribute("dir");
			String new_file_name = request.getParameter("cr_dir");
			String new_file = FileUtil.getDir(dir, new_file_name);
			System.out.println("Rename New File:: Directory : "+dir+" new_file_name "+new_file_name+" new_file "+ new_file);
			
            if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,new File(new_file), true)){
                request.setAttribute("error", "You are not allowed to access " + new_file);
            }
			// The error conditions:
			// 1) Zero Files selected
			else if (v.size() <= 0) request.setAttribute("error",
					"Select exactly one file or folder. Rename failed");
			// 2a) Multiple files selected and the first isn't a dir
			//     Here we assume that expandFileList builds v from top-bottom, starting with the dirs
			else if ((v.size() > 1) && !(((File) v.get(0)).isDirectory())) request.setAttribute(
					"error", "Select exactly one file or folder. Rename failed");
			// 2b) If there are multiple files from the same directory, rename fails
			else if ((v.size() > 1) && ((File) v.get(0)).isDirectory()
					&& !(((File) v.get(0)).getPath().equals(((File) v.get(1)).getParent()))) {
				request.setAttribute("error", "Select exactly one file or folder. Rename failed");
			}
			else {
				File f = (File) v.get(0);
                if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,f, true)){
                    request.setAttribute("error", "You are not allowed to access " + f.getAbsolutePath());
                }
				// Test, if file_name is empty
				else if ((new_file.trim() != "") && !new_file.endsWith(File.separator)) {
					boolean msg = new FileUtil().renameFile(f,new_file);
					if (msg) {
						request.setAttribute("error", "Creation of file " + new_file + " failed");
					}else {
						request.setAttribute("message", "Renamed file "
							+ ((File) v.get(0)).getName() + " to " + new_file);
					}
				}
				else request.setAttribute("error", "Error: \"" + new_file_name
						+ "\" is not a valid filename");
			}
		}
		// Move selected file(s)
		else if ((request.getParameter("Submit") != null)
				&& (request.getParameter("Submit").equals(MOVE_FILES))) {
			Vector<File> v = FileUtil.expandFileList(request.getParameterValues("selfile"), true);
			String dir = "" + request.getAttribute("dir");
			String dir_name = request.getParameter("cr_dir");
			String new_dir = FileUtil.getDir(dir, dir_name);
			System.out.println("Move File:: Directory : "+dir+" dir_name "+dir_name+" new_dir "+ new_dir);
			
            if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,new File(new_dir), false)){
                request.setAttribute("error", "You are not allowed to access " + new_dir);
            }
            else{
    			boolean error = false;
    			String currentFileDir = "", fileName="";
                // This ensures that new_dir is a directory
                if (!new_dir.endsWith(File.separator)) new_dir += File.separator;
                for (int i = v.size() - 1; i >= 0; i--) {
                    File f = (File) v.get(i);
                    fileName=f.getName();
                    currentFileDir=f.getAbsolutePath();
                    System.out.println("Move File:: move file name : "+fileName+" current file path :: "+currentFileDir);
                   
                    if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,f, true)){
                        request.setAttribute("error", "You are not allowed to access " + f.getAbsolutePath());
                        error = true;
                        break;
                    }
                    else {
                        if (!f.canWrite() || !f.renameTo(new File(new_dir
                                + f.getAbsolutePath().substring(dir.length())))) {
                            request.setAttribute("error", "Cannot move " + f.getAbsolutePath()
                                    + ". Move aborted");
                            error = true;
                            break;
                        }else{
                        	File newFile  =new File(new_dir
                                    + f.getAbsolutePath().substring(dir.length()));
                        	System.out.println("newFile  :"+newFile.getAbsolutePath()+" file path :" + newFile.getPath()+ ":: "+newFile.getCanonicalPath());
                        	boolean msg = new FileUtil().moveSelectedFile(currentFileDir,f, newFile, defaultPath);
                        	System.out.println("After successfully update with database then result "+msg);
                        }
                    	
                    }
                }
                if ((!error) && (v.size() > 1)) request.setAttribute("message", "All files moved");
                else if ((!error) && (v.size() > 0)) request.setAttribute("message", "File moved");
                else if (!error) request.setAttribute("error", "No files selected");
            }
		}
		// Copy Files
		else if ((request.getParameter("Submit") != null)
				&& (request.getParameter("Submit").equals(COPY_FILES))) {
			Vector<File> v = FileUtil.expandFileList(request.getParameterValues("selfile"), true);
			String dir = (String) request.getAttribute("dir");
			if (!dir.endsWith(File.separator)) dir += File.separator;
			String dir_name = request.getParameter("cr_dir");
			String new_dir = FileUtil.getDir(dir, dir_name);
			System.out.println("Copy File:: file name : "+dir+" current directory name "+dir_name+" new directory name "+ new_dir);
			
            if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,new File(new_dir), true)){
                request.setAttribute("error", "You are not allowed to access " + new_dir);
            }
            else{
    			boolean error = false;
                if (!new_dir.endsWith(File.separator)) new_dir += File.separator;
                try {
                    byte buffer[] = new byte[0xffff];
                    for (int i = 0; i < v.size(); i++) {
                        File f_old = (File) v.get(i);
                        File f_new = new File(new_dir + f_old.getAbsolutePath().substring(dir.length()));
                     //   System.out.println("Copy File:: file_old : "+f_old.getName()+" file_new "+f_new.getName()
                    //    +" path : "+f_old.getAbsolutePath()+" path :: "+f_new.getAbsolutePath());
            			
                        if (!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,f_old, false)||
                        		!FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,f_new, true)){
                            request.setAttribute("error", "You are not allowed to access " + f_new.getAbsolutePath());
                            error = true;
                        }
                        else if (f_old.isDirectory()){
                        	boolean copyDirFlag = f_new.mkdirs();
                        	System.out.println("Copy File:: file_old : "+f_old.getName()+" is directory so create new direcotry :: "+f_new.getName()+" :: "+f_new.getCanonicalPath());
                        	// use database for updating folder label into table
                        	new FileUtil().createDir(f_new.getCanonicalPath() ,f_new.getName(), false, defaultPath) ;
                        }
                        // Overwriting is forbidden
                        else if (!f_new.exists()) {
                        	FileUtil.copyStreams(new FileInputStream(f_old), new FileOutputStream(f_new), buffer);
                        	System.out.println("Copy File:: file_new : "+f_new.getName()+"  file_path AbsolutePath:: "+f_new.getAbsolutePath()+" file_new canonical path:: "+f_new.getCanonicalPath()+" f_new parent:: "+f_new.getParent());
                        	System.out.println("Copy File:: file_old AbsolutePath: "+f_old.getAbsolutePath()+" file_old AbsolutePath:: "+f_old.getCanonicalPath()+"::"+f_old.getPath()+" :: "+f_old.getName());
                        	new FileUtil().createFiles(f_new.getCanonicalPath() ,f_new.getCanonicalPath().substring(0,f_new.getCanonicalPath().lastIndexOf("\\")+1), f_new.getName(), false, defaultPath) ;
                        	// use database for updating file label into table
                        }
                        else {
                            // File exists
                            request.setAttribute("error", "Cannot copy " + f_old.getAbsolutePath()
                                    + ", file already exists. Copying aborted");
                            error = true;
                            break;
                        }
                    }
                }
                catch (IOException e) {
                    request.setAttribute("error", "Error " + e + ". Copying aborted");
                    error = true;
                }
                if ((!error) && (v.size() > 1)) request.setAttribute("message", "All files copied");
                else if ((!error) && (v.size() > 0)) request.setAttribute("message", "File copied");
                else if (!error) request.setAttribute("error", "No files selected");
            }
		}
		// Directory viewer
		if (dir_view && request.getAttribute("dir") != null) {
			File f = new File("" + request.getAttribute("dir"));
			 System.out.println("View Directiry :: file name : "+f.getName()+" directory location : "+f.getAbsolutePath());
			//Check, whether the dir exists
			if (!f.exists() || !FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,f, false)) {
				if (!f.exists()){
                    request.setAttribute("error", "Directory " + f.getAbsolutePath() + " does not exist.");
                }
                else{
                    request.setAttribute("error", "You are not allowed to access " + f.getAbsolutePath());
                }
				//if attribute olddir exists, it will change to olddir
				if (request.getAttribute("olddir") != null && FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,new File((String) request.getAttribute("olddir")), false)) {
					f = new File("" + request.getAttribute("olddir"));
				}
				//try to go to the parent dir
				else {
					if (f.getParent() != null && FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,f, false)) f = new File(f.getParent());
				}
				//If this dir also do also not exist, go back to browser.jsp root path
				if (!f.exists()) {
					String path = null;
					if (application.getRealPath(request.getRequestURI()) != null) path = new File(
							application.getRealPath(request.getRequestURI())).getParent();

					if (path == null) // handle the case were we are not in a directory (ex: war file)
					path = new File(".").getAbsolutePath();
					f = new File(path);
				}
				if (FileUtil.isAllowed(READ_ONLY,RESTRICT_BROWSING,RESTRICT_PATH,RESTRICT_WHITELIST,f, false))
					request.setAttribute("dir", f.getAbsolutePath());
                else
                	request.setAttribute("dir", null);
			}
			System.out.println("View Directiry :: browser_name:: "+browser_name);
%>
		<%-- <script type="text/javascript" src="<%=browser_name %>?javascript"> </script> --%>
		
		<script type="text/javascript" src="js/bootstrap.js"></script>
	</head>
	<body>
	<jsp:include page="commonJsp/Header.jsp" />
	<div class="space"></div>
<%
			//Output message
			if (request.getAttribute("message") != null) {
				out.println("<table border=\"0\" width=\"100%\"><tr><td class=\"message\">");
				out.println(request.getAttribute("message"));
				out.println("</td></tr></table>");
			}
			//Output error
			if (request.getAttribute("error") != null) {
				out.println("<table border=\"0\" width=\"100%\"><tr><td class=\"error\">");
				out.println(request.getAttribute("error"));
				out.println("</td></tr></table>");
			}
            if (request.getAttribute("dir") != null){
%>
		
		<form class="formular" action="<%= browser_name %>" method="Post" name="FileList" id="FileListForm">
    		Filename filter: <input name="filt" class="textfield" onKeypress="event.cancelBubble=true;" onkeyup="filter(this)" type="text">
    		<br /><br />
			
			<table id="filetable" class="table table-bordered">
<%
			// Output the table, starting with the headers.
			String dir = URLEncoder.encode("" + request.getAttribute("dir"));
			System.out.println("View Record :: directory :: "+dir);
			String cmd = browser_name + "?dir=" + dir;
			System.out.println("View Record :: command :: "+cmd);
			int sortMode = 1;
			if (request.getParameter("sort") != null) sortMode = Integer.parseInt(request
					.getParameter("sort"));
			int[] sort = new int[] {1, 2, 3, 4};
			for (int i = 0; i < sort.length; i++)
				if (sort[i] == sortMode) sort[i] = -sort[i];
			out.print("<tr><th style='text-align:center'><input type='checkbox' name='selall' onClick='AllFiles(this.form)'></th><th title=\"Sort files by name\" align=left><a href=\""
					+ cmd + "&amp;sort=" + sort[0] + "\">Name</a></th>"
					+ "<th title=\"Sort files by size\" align=\"right\"><a href=\"" + cmd
					+ "&amp;sort=" + sort[1] + "\">Size</a></th>"
					+ "<th title=\"Sort files by type\" align=\"center\"><a href=\"" + cmd
					+ "&amp;sort=" + sort[3] + "\">Type</a></th>"
					+ "<th title=\"Sort files by date\" align=\"left\"><a href=\"" + cmd
					+ "&amp;sort=" + sort[2] + "\">Date</a></th>"
					+"<th>&nbsp;</th>"); //
			if (!READ_ONLY) out.print ("<th>&nbsp;</th>");
			out.println("</tr>");
			char trenner = File.separatorChar;
			// Output the Root-Dirs, without FORBIDDEN_DRIVES
			File[] entry = File.listRoots();
			System.out.println("View Roots list size :: "+entry.length);
			for (int i = 0; i < entry.length; i++) {
				boolean forbidden = false;
				for (int i2 = 0; i2 < FORBIDDEN_DRIVES.length; i2++) {
				//	System.out.println("FORBIDDEN_DRIVES :: "+FORBIDDEN_DRIVES[i2]+" entry "+entry[i].getAbsolutePath().toLowerCase());
					if (entry[i].getAbsolutePath().toLowerCase().equals(FORBIDDEN_DRIVES[i2])) forbidden = true;
				}
				if (!forbidden) {
					out.println("<tr class=\"mouseout\" onmouseover=\"this.className='mousein'\""
							+ "onmouseout=\"this.className='mouseout'\">");
					out.println("<td>&nbsp;</td><td align=left >");
					String name = URLEncoder.encode(entry[i].getAbsolutePath());
					String buf = entry[i].getAbsolutePath();
					out.println(" &nbsp;<a href=\"" + browser_name + "?sort=" + sortMode
							+ "&amp;dir=" + name + "\">[" + buf + "]</a>");
					out.print("</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td></td></tr>");
				}
			}
			// Output the parent directory link ".."
			/* if (f.getParent() != null) {
				out.println("<tr class=\"mouseout\" onmouseover=\"this.className='mousein'\""
						+ "onmouseout=\"this.className='mouseout'\">");
				out.println("<td></td><td align=left>");
				out.println(" &nbsp;<a href=\"" + browser_name + "?sort=" + sortMode + "&amp;dir="
						+ URLEncoder.encode(f.getParent()) + "\">" + FOL_IMG + "[..]</a>");
				out.print("</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td></td></tr>");
			} */
			
			// Output all files and dirs and calculate the number of files and total size
			entry = f.listFiles();
			if (entry == null) entry = new File[] {};
			long totalSize = 0; // The total size of the files in the current directory
			long fileCount = 0; // The count of files in the current working directory
			if (entry != null && entry.length > 0) {
				Arrays.sort(entry, new FileComp(sortMode));
				for (int i = 0; i < entry.length; i++) {
					String name = URLEncoder.encode(entry[i].getAbsolutePath());
					String type = "File"; // This String will tell the extension of the file
					if (entry[i].isDirectory()) type = "DIR"; // It's a DIR
					else {
						String tempName = entry[i].getName().replace(' ', '_');
						if (tempName.lastIndexOf('.') != -1) type = tempName.substring(
								tempName.lastIndexOf('.')).toLowerCase();
					}
					String ahref = "<a onmousedown=\"dis()\" href=\"" + browser_name + "?sort="
							+ sortMode + "&amp;";
					String dlink = "&nbsp;"; // The "Download" link
					String elink = "&nbsp;"; // The "Edit" link
					String buf = FileUtil.conv2Html(entry[i].getName());
					if (!entry[i].canWrite()) buf = "<i>" + buf + "</i>";
					String link = buf; // The standard view link, uses Mime-type
					if (entry[i].isDirectory()) {
						if (entry[i].canRead() && USE_DIR_PREVIEW) {
							//Show the first DIR_PREVIEW_NUMBER directory entries in a tooltip
							File[] fs = entry[i].listFiles();
							if (fs == null) fs = new File[] {};
							Arrays.sort(fs, new FileComp());
							StringBuffer filenames = new StringBuffer();
							for (int i2 = 0; (i2 < fs.length) && (i2 < 10); i2++) {
								String fname = FileUtil.conv2Html(fs[i2].getName());
								if (fs[i2].isDirectory()) filenames.append("[" + fname + "];");
								else filenames.append(fname + ";");
							}
							if (fs.length > DIR_PREVIEW_NUMBER) filenames.append("...");
							else if (filenames.length() > 0) filenames
									.setLength(filenames.length() - 1);
							link = ahref + "dir=" + name + "\" title=\"" + filenames + "\">"
									+ FOL_IMG + "[" + buf + "]</a>";
						}
						else if (entry[i].canRead()) {
							link = ahref + "dir=" + name + "\">" + FOL_IMG + "[" + buf + "]</a>";
						}
						else link = FOL_IMG + "[" + buf + "]";
					}
					else if (entry[i].isFile()) { //Entry is file
						totalSize = totalSize + entry[i].length();
						fileCount = fileCount + 1;
						if (entry[i].canRead()) {
							dlink = ahref + "downfile=" + name + "\">Download</a>";
							//If you click at the filename
							
							if (USE_POPUP) link = ahref + "file=" + name + "\" target=\"_blank\">"
									+ buf + "</a>";
							else link = ahref + "file=" + name + "\">" + buf + "</a>";
							/* if (entry[i].canWrite()) { // The file can be edited
								//If it is a zip or jar File you can unpack it
								if (FileUtil.isPacked(name, true)) elink = ahref + "unpackfile=" + name
										+ "\">Unpack</a>";
								else elink = ahref + "editfile=" + name + "\">Edit</a>";
							} */
							if (!entry[i].canWrite()) { // If the file cannot be edited
								//If it is a zip or jar File you can unpack it
								if (FileUtil.isPacked(name, true)) elink = ahref + "unpackfile=" + name
										+ "\">Unpack</a>";
								else elink = ahref + "editfile=" + name + "\">View</a>";
							}
						}
						else {
							link = buf;
						}
					}
					String date = dateFormat.format(new Date(entry[i].lastModified()));
					out.println("<tr class=\"mouseout\" onmouseup=\"selrow(this, 2)\" "
							+ "onmouseover=\"selrow(this, 0);\" onmouseout=\"selrow(this, 1)\">");
					if (entry[i].canRead()) {
						out.println("<td align=center><input type=\"checkbox\" name=\"selfile\" value=\""
										+ name + "\" onmousedown=\"dis()\"></td>");
					}
					else {
						out.println("<td align=center><input type=\"checkbox\" name=\"selfile\" disabled></td>");
					}
					out.print("<td align=left> &nbsp;" + link + "</td>");
					if (entry[i].isDirectory()) out.print("<td>&nbsp;</td>");
					else {
						out.print("<td align=right title=\"" + entry[i].length() + " bytes\">"
								+ FileUtil.convertFileSize(entry[i].length()) + "</td>");
					}
					out.println("<td align=\"center\">" + type + "</td><td align=left> &nbsp;" + // The file type (extension)
							date + "</td><td>" + // The date the file was created
							dlink + "</td>"); // The download link
					if (!READ_ONLY)
						out.print ("<td>" + elink + "</td>"); // The edit link (or view, depending)
					out.println("</tr>");
				}
			}%>
		</table>
		<!-- <input type="checkbox" name="selall" onClick="AllFiles(this.form)">  Select all -->
		<p align=center>
			<b title="<%=totalSize%> bytes">
			<%=FileUtil.convertFileSize(totalSize)%></b><b> in <%=fileCount%> files in 
			  <c:choose>
			  	<c:when test="${sessionBean.roleID==4}"><%=(String) request.getAttribute("dir") %></c:when>  
			  	<c:otherwise>
			  		<%= FileUtil.dir2linkdir((String) request.getAttribute("dir"), browser_name, sortMode,READ_ONLY, FORBIDDEN_DRIVES)%>
			  	</c:otherwise> 
			  </c:choose>
			  
			</b>
		</p>
		<input type="hidden" name="dir" value="<%=request.getAttribute("dir")%>">
		<input type="hidden" name="sort" value="<%=sortMode%>">
		<input title="Download selected files and directories as one zip file" class="button btn btn-primary" id="but_Zip" type="Submit" name="Submit" value="<%=SAVE_AS_ZIP%>">
		<% if (!READ_ONLY) {%>
			<input title="Delete all selected files and directories incl. subdirs" class="button btn btn-danger"  id="but_Del" type="Submit" name="Submit" value="<%=DELETE_FILES%>"
			onclick="return confirm('Do you really want to delete the entries?')">
		<% } %>
	<% if (!READ_ONLY) {%>
			<br />
			<!-- input area for getting value of rename file, copy file from one to another directory, move file from one to another directory, create directory, create file -->
			<input title="Enter new dir or filename or the relative or absolute path" class="textfield" type="text" 
				onKeypress="event.cancelBubble=true;" id="text_Dir" name="cr_dir">
				
			<input title="Create a new directory with the given name" class="button btn btn-primary" id="but_NDi" type="submit" name="Submit" value="<%=CREATE_DIR%>" onclick="return  requried(1)" >
		<% 	if(roleId!=20){ %>
			<input title="Create a new empty file with the given name" class="button btn btn-primary" id="but_NFi" type="submit" name="Submit" value="<%=CREATE_FILE%>" onclick="return  requried(2)">
		<% 	} %>
			<input title="Move selected files and directories to the entered path" id="but_Mov" class="button btn btn-primary" type="submit" name="Submit" value="<%=MOVE_FILES%>" onclick="return  requried(3)">
			<input title="Copy selected files and directories to the entered path" id="but_Cop" class="button btn btn-primary" type="submit" name="Submit" value="<%=COPY_FILES%>" onclick="return  requried(4)">
			<input title="Rename selected file or directory to the entered name" id="but_Ren" class="button btn btn-primary" type="submit" name="Submit" value="<%=RENAME_FILE%>" onclick="return  requried(5)">
			<p id="errMsg"></p>
			
	<% } %>
		</form>
	<% if (ALLOW_UPLOAD) { %>
		<br />
		<div class="formular">
			<form class="formular2" action="<%=browser_name%>"
			enctype="multipart/form-data" method="POST" id="fileUploadForm">
			<input type="hidden" name="dir"
				value="<%=request.getAttribute("dir")%>"> <input
				type="hidden" name="sort" value="<%=sortMode%>"> <input id="fileName"
				type="file" name="myFile" class="textfield"
				onKeypress="event.cancelBubble=true;" accept="image/jpg,image/png,image/jpeg,image/gif,.pdf, .csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.presentationml.presentation, .ppt, application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet,.doc,.docx,.xml,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document, application/vnd.ms-excel" >
			<%-- <input title="Upload selected file to the current working directory" type="Submit" class="button btn btn-primary" 
				name="Submit" value="<%=UPLOAD_FILES%>" > --%>
			<input title="Upload selected file to the current working directory"
				type="button" class="button btn btn-primary" name="Submit" id="fileNameType"
				value="<%=UPLOAD_FILES%>" onclick="checkFileType();"> <strong
				style="color: red;">Upload only PDF, Word, Excel, PPT and
				Image(.jpg, .png, .jpeg) File</strong>
		</form>
		<br /><br />
    	</div>
    <%} %>
    <% }%>
	<jsp:include page="commonJsp/Footer.jsp" />	
	<script language="javascript" type="text/javascript">
	
	function checkFileType() {
		var fileName=document.getElementById("fileName").value;
		var ext = $('#fileName').val().split('.').pop().toLowerCase();
		if($.inArray(ext, ['pdf','doc','docx','jpeg','jpg','png','xlsx','ppt','pptx','xls']) == -1) {
			alert("Only PDF, Word, Excel,PPT and Image(.jpg, .png, .jpeg) File are allowed to upload.")
		}else{
			document.getElementById("fileUploadForm").submit();
		}
		
		
	}
	
		function requried(flag) {
		//	alert("flag :: "+flag);
			$("#errMsg").html("");
		//	alert(" dir : "+$("#dir").val()+" sort :"+$("#sort").val());
			var msg = '';
			if(flag=='1'){
				msg='Please enter the directory name';
			}else if(flag=='2'){
				msg='Please enter the file name';
			}else if(flag=='3'){
				msg='Please enter the directory name, where you move file(s)';
			}else if(flag=='4'){
				msg='Please enter the directory name, where you copy file(s)';
			}else if(flag=='5'){
				msg='Please enter the new file name';
			}
			
			$("#text_Dir").css("border", "2px solid blue");
			var empt = document.forms["FileList"]["text_Dir"];  
			if (empt.value == "")  { 
				$("#text_Dir").css("border", "2px solid red");
				$("#errMsg").html("<strong style='color:red'>"+ msg+"</strong>");
				setTimeout(function(){
					$("#errMsg").html("");
					$("#text_Dir").css("border", "1px solid ");
				}, 5000);
				return false;  
			} else  {
				//alert("ok done submit form")
				return true;   
			}  
		}
	
		</script>
		
	<div class="space"></div>


		
		
	</body>
</html>

<%
    }
%>
package com.precise.fileManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
	public static Connection conn=  null;
	
	public Connection getConnectionDB(){

		String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://192.168.1.30;databaseName=IIAM";
		String dbuser = "sa";
		String dbpassword="admin123";
		
		//System.out.println("getConnectionDB() :: dbDriver ::"+dbDriver+" dbURL ::"+dbURL+" dbuser ::"+dbuser+" dbpassword ::"+dbpassword);
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbURL+";user="+dbuser+";password="+dbpassword);
		}  catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

}

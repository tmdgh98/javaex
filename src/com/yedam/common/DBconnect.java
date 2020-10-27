package com.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "hr";
	private static String password = "hr";
	
	public static Connection conn; 
	
	public static Connection getConnection() { 
		try { 
			Class.forName(driver); 
			conn = DriverManager.getConnection(url, user, password);
		}catch(ClassNotFoundException | SQLException e) { 
			e.printStackTrace();
		}
		return conn;
	}
}

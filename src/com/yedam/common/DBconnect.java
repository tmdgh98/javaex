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
			System.out.println("연결성공");
		}catch(ClassNotFoundException | SQLException e) { 
			e.printStackTrace();
			System.out.println("연결실패");
		}
		return conn;
	}
}

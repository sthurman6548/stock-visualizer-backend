package com.example.visualizer.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	
	
 public static void connect() throws SQLException {
	 Connection conn;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_visualizr_server", "java_user", "rebel076");
 }
}

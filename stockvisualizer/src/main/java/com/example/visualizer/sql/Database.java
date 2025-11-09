package com.example.visualizer.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.example.visualizer.model.Portfolio;

import java.sql.Date;

public class Database {

	protected static Connection conn;
	
 public Database() throws SQLException {
	 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_visualizr_server", "java_user", "rebel076");
 }
 
 public static void dataUpload(Portfolio pf, double cw, double ow, LocalDate oDate) {
	 String pquery = "INSERT INTO portfolio (name, account, currWorth, origWorth, currDate, origDate)" + " values (?, ?, ?, ?, ?, ?);";
	 String squery = "INSERT INTO stocks (symbol, purDate, quant, price, currPrice, percent, account)" + "values (?, ?, ?, ?, ?, ?, ?);";
	 Date date = Date.valueOf(oDate);
	 Date nw = Date.valueOf(LocalDate.now());
	 try {
	 PreparedStatement p = conn.prepareStatement(pquery);
	 p.setString(1, pf.getName());
	 p.setString(2, pf.getAccount());
	 p.setDouble(3, cw);
	 p.setDouble(4, ow);
	 p.setDate(5, nw);
	 p.setDate(6, date);
	 
	 p.addBatch();
	 
	 p.execute();
	 } catch(SQLException e) {
		 System.out.print("[ERROR] with SQL: " + e);
	 }
	 for (int i = 0; i < pf.holdings.size(); i++) {
	 try {
		 PreparedStatement s = conn.prepareStatement(squery);
		 s.setString(1, pf.holdings.get(i).getSymbol());
		 s.setDate(2, Date.valueOf(pf.holdings.get(i).getPur_date()));
		 s.setInt(3, pf.holdings.get(i).getQuant());
		 s.setDouble(4, pf.holdings.get(i).getPrice());
		 s.setDouble(5, pf.holdings.get(i).getCurrPrice());
		 s.setDouble(6, pf.holdings.get(i).getPercent());
		 s.setString(7, pf.getAccount());
		 s.addBatch();
		 s.execute();
	 }catch(SQLException e) {
		 System.out.print("[ERROR] Stocks with SQL: " + e);

	 	}
	  } 
 	}
}

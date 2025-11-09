package com.example.visualizer.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONException;

import com.example.visualizer.model.Portfolio;
import com.example.visualizer.model.Stock;
import com.example.visualizer.sql.*;

public class Valuate {

	public static void processPortfolio(Portfolio pf) {
		double cw;
		double ow;
		cw = 0;
		
		LocalDate oDate;
		ow = originalWorth(pf.holdings);
		
		try {
			cw = currentWorth(pf.holdings);
		}catch(JSONException e) {
			System.out.println("Used all your submits");
		}
		
		oDate = originalDate(pf.holdings);
		percentage(pf);
		
		System.out.printf("Current Worth: %.2f, Original Worth: %.2f, Date: %tB, %td, %tY%n", cw ,ow, oDate, oDate, oDate );
		
		try {
		Database db = new Database();
		Database.dataUpload(pf, cw, ow, oDate);
		
		} catch(SQLException e) {
			System.out.println("[Exception] " + e);
		}
	}
	
	public static double currentWorth(ArrayList<Stock> p) {
		ArrayList<Double> prices;
		double worth;
		 
		prices = ExternHandler.getCurrPrices(p);
		worth = 0;
		
		for(int i = 0; i < p.size(); i++) {
			worth += prices.get(i) * p.get(i).getQuant();
		}
		return worth;
	}
	
	public static double originalWorth(ArrayList<Stock> p) {
		double worth;
		worth = 0;
		for(int i = 0; i < p.size(); i++) {
			System.out.println(p.get(i).getSymbol());
			worth += p.get(i).getPrice() * p.get(i).getQuant();
		}
		return worth;
	}
	
	public static LocalDate originalDate(ArrayList<Stock> p) {
		LocalDate latest = LocalDate.MIN;
		for(int i = 0; i < p.size(); i++) {
			if(p.get(i).getPur_date().isAfter(latest)) {
				latest = p.get(i).getPur_date();
		}
	  }
		return latest;
	}
	
	public static void percentage(Portfolio pf) {
		for(int i = 0; i < pf.holdings.size(); i++) {
			pf.holdings.get(i).setPercent(pf.percentage(i));
		}
	}
}

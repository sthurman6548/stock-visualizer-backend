package com.example.visualizer.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONException;

import com.example.visualizer.model.Portfolio;
import com.example.visualizer.model.Stock;

public class Valuate {

	public static void processPortfolio(Portfolio pf) {
		double cw;
		double ow;
		cw = 0;
		
		//LocalDate oDate;
		ow = originalWorth(pf.holdings);
		
		try {
		cw = currentWorth(pf.holdings);
		}catch(JSONException e) {
			System.out.println("Used all your submits");
		}
		
		//oDate = originalDate(pf.holdings);
		
		
		for(int i = 0; i < pf.holdings.size(); i++) {
			pf.percentage(i);
		}
		System.out.printf("Current Worth: %.2f Original Worth: %.2f, ", cw ,ow);
	}
	
	public static double currentWorth(ArrayList<Stock> p) {
		ArrayList<Double> prices;
		double worth;
		 
		prices = DownloadController.getCurrPrices(p);
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
	
}

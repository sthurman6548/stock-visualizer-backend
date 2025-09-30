package com.example.visualizer.model;

import java.util.ArrayList;
public class Portfolio {
	 ArrayList<Stock> holdings;
	private String name;
	private String account;


	public Portfolio(String new_name, String new_account) {
		
		holdings = new ArrayList<Stock>();
		this.name = new_name;
		this.account = new_account;
	}


	public String getAccount() {
		return account;
	}


	public String getName() {
		return name;
	}
	
	public void addStock(String[] line) {
		Stock newStock = Stock.fromCsv(line);
		holdings.add(newStock);	
	}
	
	public void print() {
		System.out.printf("Name:%s, Account_Number: %s", name, account);
		System.out.println("\n-----------------------------------------------------");
		for(int i=0; i < holdings.size(); i++) {
			System.out.printf("Symbol: %s \nDate: %s \nShares: %s \nPrice: %f \nCurrency: %s ", holdings.get(i).getSymbol(),
					holdings.get(i).getPur_date().toString(),
					holdings.get(i).getQuant(),
					holdings.get(i).getPrice(),
					holdings.get(i).getCurncy()
					);
			System.out.println("\n-----------------------------------------------------");

		}
	}
}
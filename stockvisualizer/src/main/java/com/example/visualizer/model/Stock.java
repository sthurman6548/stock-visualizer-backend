package com.example.visualizer.model;


import java.time.LocalDate;

public class Stock {
	private String symbol;
	private LocalDate pur_date;
	private int quant;
	private double price;
	private double currPrice;
	private String curncy;
	private double percent;
	
	public Stock(String symbol, LocalDate date, int amt, double price, String currency) {
		this.symbol = symbol;
		this.pur_date = date;
		this.price = price;
		this.quant = amt;
		this.curncy = currency;
		this.currPrice = 0;
		this.percent = 0;
		
	}

	public String getSymbol() {
		return symbol;
	}

	public LocalDate getPur_date() {
		return pur_date;
	}

	public int getQuant() {
		return quant;
	}

	public double getPrice() {
		return price;
	}

	public String getCurncy() {
		return curncy;
	}
	
	public double getCurrPrice() {
		return this.currPrice;
	}
	
	public void setCurrPrice(double price) {
		this.currPrice = price;
	}
	
	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public static Stock fromCsv(String[] line) {
		return new Stock (
				line[0],
				LocalDate.parse(line[1]),
				Integer.parseInt(line[2]),
				Double.parseDouble(line[3]),
				line[4]		
				);
		
				
	}
	
	
}

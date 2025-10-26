package com.example.visualizer.service;
import java.io.IOException;
import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONObject;

import com.example.visualizer.model.Stock;

public class DownloadController {
	
	
	public static ArrayList<Double> getCurrPrices(ArrayList<Stock> p) {
		String key = "CYU1L8WMM1AYXZ21";
		ArrayList<Double> prices = new ArrayList<Double>();
		double price;
		HttpClient client; 
		int counter;
		
		client = HttpClient.newHttpClient();
		price = 0;
		counter = 0;
		
		for(int i =0; i < p.size(); i++) {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(helper(p,i,key)))
					.GET()
					.timeout(Duration.ofSeconds(10))
					.build();
			
			try {
				HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());
					price = handleResponse(res);
					prices.add(i, price);
					counter++;
			
			} catch (IOException e) {	
				System.out.println("[Error]: " + e);
			} catch (InterruptedException e) {
				System.out.println("[Error]: " + e);
			}
				
		}
		return prices;
	}
	
	private static String helper(ArrayList<Stock> p,int index, String key) {
		String url;
		
		url = String.format("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=%s&apikey=%s", p.get(index).getSymbol(),key );
		return url;
	}
	
	private static double handleResponse(HttpResponse<String> res) {
		
		JSONObject base;
		JSONObject gq; 
		double price;
		
		base = new JSONObject(res.body());
		gq = base.getJSONObject("Global Quote");
		price = gq.getDouble("05. price");
		return price;
	}
	
}

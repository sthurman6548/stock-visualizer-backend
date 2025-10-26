package com.example.visualizer;
import com.example.visualizer.model.Portfolio;
import com.example.visualizer.model.Stock;
import com.example.visualizer.service.DownloadController;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import com.opencsv.CSVReader;

@SpringBootApplication
public class StockvisualizerApplication {

	public static void main(String[] args) throws CsvValidationException, IOException {
		SpringApplication.run(StockvisualizerApplication.class, args);
			
	}

}

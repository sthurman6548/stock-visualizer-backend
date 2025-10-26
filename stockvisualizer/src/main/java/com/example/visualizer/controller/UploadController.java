package com.example.visualizer.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

import com.example.visualizer.model.*;
import com.example.visualizer.service.Valuate;

@RestController
@RequestMapping("/api")
@ResponseStatus(HttpStatus.ACCEPTED)
@CrossOrigin(origins = "http://localhost:5173")
public class UploadController {
	Portfolio pf;
		
	@CrossOrigin(origins = "http://localhost:5173")
	@PostMapping(value = "/info") 
	public void info(@RequestParam("Name") String name, @RequestParam("Account") String acc) {
		pf = new Portfolio(name,acc);
	} 
	
	
	@PostMapping(value = "/upload", consumes = {"multipart/form-data"} ) 
		public void catcher(@RequestParam("files") MultipartFile file)  throws IOException, CsvValidationException{
			InputStreamReader fin = new InputStreamReader(file.getInputStream());
			CSVReader csv = new CSVReader(fin);
			String[] line;
			
			csv.readNext();
			while ((line = csv.readNext()) != null) {
				pf.addStock(line);
			}
			
			Valuate.processPortfolio(pf);
			csv.close();		
		}
}


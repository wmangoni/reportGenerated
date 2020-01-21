package com.zallpy.reportsBuilder.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zallpy.reportsBuilder.model.Customer;
import com.zallpy.reportsBuilder.model.Parser;
import com.zallpy.reportsBuilder.model.Report;
import com.zallpy.reportsBuilder.model.Sales;
import com.zallpy.reportsBuilder.model.Seller;

@RestController
public class MainController {

	private String path = "./src/main/resources/data/in";
	private String outputPath = "./src/main/resources/data/out";
	
	@RequestMapping(value = "/generateReports", method = RequestMethod.GET)
	@ResponseBody
	public String generateReports() {
		
		String content = getFilesContent();
		Parser parser = new Parser(content);
		
		List<Customer> listCustomer = parser.getCustomer();
		List<Seller> listSeller = parser.getSellers();
		List<Sales> listSales = parser.getItens();
		
		Report report = new Report();
	
		try {
			Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
			String[] data = dataDeHoje.toLocaleString().split(":");
			report.buildReport( 
					listCustomer, 
					listSeller, 
					listSales
				);
			
			
			report.WriteResultInFile(
					outputPath + 
					"/report_" + 
					data[0] + 
					"-" + 
					data[1] + 
					"-" + 
					data[2] + 
					Math.random() * 999 +
					".dat"
				);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		return report.getContentFinal();
	}

	@RequestMapping(value = "/files", method = RequestMethod.GET)
	@ResponseBody
	public String getFilesContent() {

		String allContent = "";
		String teste = "";
		
		final File folder = new File(path);
		List<String> files = listFilesForFolder(folder);
		
		for (String name : files) {
			try {
				String[] formatFile = name.split("\\.");
				//apenas lê arquivos .dat
				if (formatFile.length > 1 && formatFile[1].equals("dat")) {
					allContent += readFile(name);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
				allContent = e.getMessage();
				e.printStackTrace();
			}
		}
		
		return allContent;
	
	}

	private List<String> listFilesForFolder(final File folder) {

		List<String> files = new ArrayList<String>();

		if (folder != null) {
			for (final File fileEntry : folder.listFiles()) {
				if (fileEntry.isDirectory()) {
					files = listFilesForFolder(fileEntry);
				} else {
					//System.out.println(fileEntry.getName());
					files.add(fileEntry.getName());
				}
			}
		}

		return files;
	}

	private String readFile(String fileName) throws FileNotFoundException, IOException {
		
		String content = "";
		 
		BufferedReader br = new BufferedReader(
			new FileReader(
				new File(path + "/" + fileName)
			)
		); 

		String st;
		
		while ((st = br.readLine()) != null) {
			//System.out.println(st);
			content += st + System.lineSeparator();
		}
		
		br.close();
		
		return content;
		
	}

}

package com.zallpy.reportsBuilder.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import com.zallpy.reportsBuilder.model.SallesItem;
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
		report.setCustomer(2);
		report.setSelesman(1);
		report.setMostExpensiveSale("001");
		report.setWorstSeller("asdfa");
		try {
			report.buildReport(outputPath + "/teste.dat", listCustomer, listSeller, listSales);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		return "ok";
	}

	@RequestMapping(value = "/files", method = RequestMethod.GET)
	@ResponseBody
	public String getFilesContent() {

		String _return = "";
		String teste = "";
		
		final File folder = new File(path);
		List<String> files = listFilesForFolder(folder);
		
		for (String name : files) {
			try {
				_return += readFile(name);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				_return = e.getMessage();
				e.printStackTrace();
			}
		}
		
		return _return;
		
//		Parser parser = new Parser(_return);
//		List<Customer> listCustomer = parser.getCustomer();
//		for (Customer c : listCustomer) {
//			teste += c.getId() + " | ";
//			teste += c.getCpf() + " | ";
//			teste += c.getName() + " | ";
//			teste += c.getArea() + System.lineSeparator();
//		}
//		
//		List<Seller> listSeller = parser.getSellers();
//		for (Seller c : listSeller) {
//			teste += c.getId() + " | ";
//			teste += c.getCpf() + " | ";
//			teste += c.getName() + " | ";
//			teste += c.getSalary() + System.lineSeparator();
//		}
//		
//		List<Sales> listSales = parser.getItens();
//		for (Sales c : listSales) {
//			teste += c.getId() + " | ";
//			teste += c.getSalesId() + " | ";
//			teste += c.getSallesItens().toString() + " | ";
//			teste += c.getSalesman().getName() + System.lineSeparator();
//		}
//		return teste;
		//return _return;
	}

	private List<String> listFilesForFolder(final File folder) {

		List<String> files = new ArrayList<String>();

		if (folder != null) {
			for (final File fileEntry : folder.listFiles()) {
				if (fileEntry.isDirectory()) {
					files = listFilesForFolder(fileEntry);
				} else {
					System.out.println(fileEntry.getName());
					files.add(fileEntry.getName());
				}
			}
		}

		return files;
	}

	private String readFile(String fileName) throws FileNotFoundException, IOException {
		
		String _return = "";
		 
		BufferedReader br = new BufferedReader(
			new FileReader(
				new File(path + "/" + fileName)
			)
		); 

		String st;
		
		while ((st = br.readLine()) != null) {
			System.out.println(st);
			_return += st + System.lineSeparator();
		}
		
		br.close();
		
		return _return;
		
	}

}

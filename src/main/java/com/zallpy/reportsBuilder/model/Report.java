package com.zallpy.reportsBuilder.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report {
	
	//Quantidade de clientes no arquivo de entrada
	private Integer customer;
	
	//Quantidade de vendedor no arquivo de entrada
	private Integer selesman;

	//ID da venda mais cara
	private String mostExpensiveSale;
	
	//O pior vendedor
	private String worstSaller;
	
	private String contentFinal;
	
	Map<String, Double> totalPriceForSeller = new HashMap<String, Double>();
	

	public void buildReport(List<Customer> customers, List<Seller> sellers, List<Sales> sales) {
	    this.setCustomer(customers.size());
	    this.setSelesman(sellers.size());
	    this.checkMoreExpansiveSale(sales);
	    this.checkForWorstSeller();
	}
	
	public void WriteResultInFile(String fileName) throws IOException {
		String str = "";
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	    str += "Total Clientes: " + this.getCustomer() + System.lineSeparator();
	    str += "Total Vendedores: " + this.getSelesman() + System.lineSeparator();
	    str += "ID da venda mais cara: " + this.getMostExpensiveSale() + System.lineSeparator();
	    str += "Pior Vendedor: " + this.getWorstSaller() + System.lineSeparator();
	    writer.write(str);
	    
	    System.out.println(str);
	    
	    setContentFinal(str);
	     
	    writer.close();
	}

	private void checkMoreExpansiveSale(List<Sales> sales) {
		
		Double maxPrice = 0.0;
		String id = "Nenhum item encontrado";
		
		for (Sales sale : sales) {
			Double salesPrice = 0.0;
			for (SallesItem item : sale.getSallesItens()) {
				salesPrice += item.getQuantity() * item.getPrice();
			}
			
			if (maxPrice < salesPrice) {
				maxPrice = salesPrice;
				id = sale.getSalesId();
			}
			
			totalPriceForSeller.put(sale.getSalesman().getName(), salesPrice);
		}
		
		mostExpensiveSale = id;
	}
	
	private void checkForWorstSeller() {
		Double lessPrice = Double.MAX_VALUE;
		String seler = "Nenhum vendedor encontrado";
		for (Map.Entry<String, Double> entry : totalPriceForSeller.entrySet()) {
			if (lessPrice > entry.getValue()) {
				lessPrice = entry.getValue();
				seler = entry.getKey();
			}
		}
		worstSaller = seler;
	}
	
	public Integer getCustomer() {
		return customer;
	}
	public void setCustomer(Integer customer) {
		this.customer = customer;
	}
	public Integer getSelesman() {
		return selesman;
	}
	public void setSelesman(Integer selesman) {
		this.selesman = selesman;
	}
	public String getMostExpensiveSale() {
		return mostExpensiveSale;
	}
	public void setMostExpensiveSale(String mostExpensiveSale) {
		this.mostExpensiveSale = mostExpensiveSale;
	}
	public String getWorstSaller() {
		return worstSaller;
	}
	public void setWorstSeller(String worstSaller) {
		this.worstSaller = worstSaller;
	}

	public String getContentFinal() {
		return contentFinal;
	}

	public void setContentFinal(String contentFinal) {
		this.contentFinal = contentFinal;
	}

}

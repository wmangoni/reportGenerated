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
	

	public void buildReport(String fileName, List<Customer> customers, List<Seller> sellers, List<Sales> sales) throws IOException {
	    String str = "";
	    this.setCustomer(customers.size());
	    this.setSelesman(sellers.size());
	    this.checkMoreExpansiveSale(sales);
	    
	    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	    str += "Total Clientes: " + this.getCustomer() + System.lineSeparator();
	    str += "Total Vendedores: " + this.getSelesman() + System.lineSeparator();
	    str += "Item mais caro: " + this.getMostExpensiveSale() + System.lineSeparator();
	    str += "Pior Vendedor: " + this.getWorstSaller() + System.lineSeparator();
	    writer.write(str);
	     
	    writer.close();
	}
	//le arquivos
	//guardo em memoria
	//separo vendedores, clientes e vendas
	//faço a contagem e analise de cada requisito do report
	//salvo essas informaçõe em um arquivo de saida
	private void checkMoreExpansiveSale(List<Sales> sales) {
		Double price = 0.0;
		Map<String, Double> totalPriceForSeller = new HashMap<String, Double>();
		String id = "Nenhum item encontrado";
		for (Sales sale : sales) {
			Double maxPrice = 0.0;	
			for (SallesItem item : sale.getSallesItens()) {
				maxPrice += item.getQuantity() * item.getPrice();
				if (item.getQuantity() * item.getPrice() > price) {
					price = item.getPrice();
					id = item.getId();
				}
			}
			
			totalPriceForSeller.put(sale.getSalesman().getName(), maxPrice);
		}
		checkSalesForSeller(totalPriceForSeller);
		mostExpensiveSale = id;
	}
	
	private void checkSalesForSeller(Map<String, Double> totalPriceForSeller) {
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

}

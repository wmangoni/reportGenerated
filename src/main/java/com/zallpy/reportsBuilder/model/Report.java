package com.zallpy.reportsBuilder.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Report {
	
	//Quantidade de clientes no arquivo de entrada
	private Integer customer;
	
	//Quantidade de vendedor no arquivo de entrada
	private Integer selesman;

	//ID da venda mais cara
	private String mostExpensiveSale;
	
	//O pior vendedor
	private String worstSaller;
	

	public void buildReport(String fileName) throws IOException {
	    String str = "Hello";
	    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	    writer.write(str);
	     
	    writer.close();
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

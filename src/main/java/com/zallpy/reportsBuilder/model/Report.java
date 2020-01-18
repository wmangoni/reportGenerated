package com.zallpy.reportsBuilder.model;

public class Report {
	
	//Quantidade de clientes no arquivo de entrada
	private String customer;
	
	//Quantidade de vendedor no arquivo de entrada
	private String selesman;

	//ID da venda mais cara
	private String mostExpensiveSale;
	
	//O pior vendedor
	private String worstSaller;
	
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getSelesman() {
		return selesman;
	}
	public void setSelesman(String selesman) {
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

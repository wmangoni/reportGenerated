package com.zallpy.reportsBuilder.model;

import java.util.List;

public class Sales {
	
	
	private String id;
	
	private String salesId;
	
	private List<SallesItem> SallesItens;
	
	private Seller salesman;
	
	
	public Sales(String id, String salesId, List<SallesItem> SallesItens, Seller salesman) {
		this.id = id;
		this.salesId = salesId;
		this.setSallesItens(SallesItens);
		this.setSalesman(salesman);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSalesId() {
		return salesId;
	}
	
	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public List<SallesItem> getSallesItens() {
		return SallesItens;
	}

	public void setSallesItens(List<SallesItem> sallesItens) {
		SallesItens = sallesItens;
	}

	public Seller getSalesman() {
		return salesman;
	}

	public void setSalesman(Seller salesman) {
		this.salesman = salesman;
	}

}

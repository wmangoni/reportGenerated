package com.zallpy.reportsBuilder.model;

public class Customer extends Person {

	private String area;
	
	public Customer(String id, String cpf, String name, String area) {
		super(id, cpf, name);
		this.setArea(area);
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}

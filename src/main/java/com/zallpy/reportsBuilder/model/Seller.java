package com.zallpy.reportsBuilder.model;

public class Seller extends Person {

	private Double salary;
	
	public Seller(String id, String cpf, String name, Double salary) {
		super(id, cpf, name);
		this.setSalary(salary);
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
}

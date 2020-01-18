package com.zallpy.reportsBuilder.model;

public class Person {
	
	
	private String id;
	
	private String cpf;
	
	private String name;
	
	
	public Person(String id, String cpf, String name) {
		this.id = id;
		this.cpf = cpf;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}

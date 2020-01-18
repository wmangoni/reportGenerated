package com.zallpy.reportsBuilder.model;

public class SallesItem {

	private String id;
	private Integer quantity;
	private Double price;
	
	public SallesItem(String id, Integer qty, Double price) {
		this.id = id;
		this.quantity = qty;
		this.price = price;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}

package com.zallpy.reportsBuilder.model;

import java.util.ArrayList;
import java.util.List;

public class Parser {
	
	//  Exemple of content file
	
	//  id  cpf           name  Salary
	//	001ç1234567891234çPedroç50000
	//	001ç3245678865434çPauloç40000.99
	
	//  id  cpf              name          businnes area
	//	002ç2345675434544345çJose da SilvaçRural
	//	002ç2345675433444345çEduardo PereiraçRural
	
	//  id  saleIDç[Item ID  -  Item Quantity-Item Price]                    Salesman name
	//	003ç10ç    [1  -  10  -  100,  2  -  30  -  2.50, 3  -  40  -  3.10]çPedro
	//	003ç08ç    [1  -  34  -  10,   2  -  33  -  1.50, 3  -  40  -  0.10]çPaulo

	private final String CHARACTER_SEPARATOR = "ç";
	private final String LINE_SEPARATOR = System.lineSeparator();
	private final String ARRAY_INDEX_SEPARATOR = ",";
	private final String ITENS_SEPARATOR = "-";
	
	public final String ID_SELLER = "001";
	public final String ID_CUSTOMER = "002";
	public final String ID_SELLES = "003";
	
	private List<String[]> sellers = new ArrayList<String[]>();
	private List<String[]> customers = new ArrayList<String[]>();
	private List<String[]> salles = new ArrayList<String[]>();
	
	public Parser(String text) {
		String[] lines = brokenContent(text);
		for (String line : lines) {
			String str[] = brokenLines(line);
			if (str[0].equals(ID_SELLER)) {
				sellers.add(str);
			}
			if (str[0].equals(ID_CUSTOMER)) {
				customers.add(str);
			}
			if (str[0].equals(ID_SELLES)) {
				salles.add(str);
			}
		}
	}
	
	public List<Seller> getSellers() {
		
		List<Seller> listSellers = new ArrayList<Seller>();
		
		for (String stringSeller[] : this.sellers) {
		
			Seller seller = new Seller(
				stringSeller[0],
				stringSeller[1],
				stringSeller[2],
				Double.valueOf(stringSeller[3])
			);
			
			listSellers.add(seller);
			
		}
		
		return listSellers;
	
	}
	
	public List<Customer> getCustomer() {
		
		List<Customer> listCustomers = new ArrayList<Customer>();
		
		for (String stringSeller[] : this.customers) {
		
			Customer seller = new Customer(
				stringSeller[0],
				stringSeller[1],
				stringSeller[2],
				stringSeller[3]
			);
			
			listCustomers.add(seller);
			
		}
		
		return listCustomers;
	
	}
	
	public List<Sales> getItens() {
		
		List<Sales> listSales = new ArrayList<Sales>();
		
		List<SallesItem> sallesItens = new ArrayList<SallesItem>();
				
		for (String list[] : this.salles) {
			Seller s = null;
			for (Seller seller : getSellers()) {
				if (seller.getName().equals(list[3])) {
					s = seller;
				}
			}
			
			String str[] = brokenArrayInItens(list[2]);
			for (int i = 0; i < str.length; i++) {
				String[] el = brokenItensParts(str[i]);
				sallesItens.add(new SallesItem(el[0], Integer.valueOf(el[1]), Double.valueOf(el[2])));
			}
			
			Sales sales = new Sales(
				list[0],
				list[1],
				sallesItens,
				s
			);
			
			listSales.add(sales);
			
		}
		
		return listSales;
	
	}
	
	private String[] brokenLines(String line) {
		return line.split(CHARACTER_SEPARATOR);
	}
	
	private String[] brokenContent(String content) {
		return content.split(LINE_SEPARATOR);
	}
	
	private String[] brokenArrayInItens(String arr) {
		arr = arr.substring(1);
		arr = arr.substring(0, arr.length() - 1);
		return arr.split(ARRAY_INDEX_SEPARATOR);
	}
	
	private String[] brokenItensParts(String item) {
		return item.split(ITENS_SEPARATOR);
	}

}

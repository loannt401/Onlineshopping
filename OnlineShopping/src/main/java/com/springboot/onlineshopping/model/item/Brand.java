package com.springboot.onlineshopping.model.item;

public class Brand {

	private int brandID;
	private String name;
	public Brand() {
		// TODO Auto-generated constructor stub
	}

	public Brand(int brandID, String name) {
		this.brandID = brandID;
		this.name = name;
	}

	public int getBrandID() {
		return this.brandID;
	}

	/**
	 * 
	 * @param brandID
	 */
	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
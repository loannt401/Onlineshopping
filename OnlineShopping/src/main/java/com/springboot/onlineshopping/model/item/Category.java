package com.springboot.onlineshopping.model.item;

public class Category {

	private int cateID;
	private String name;
	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(int cateID, String name) {
		this.cateID = cateID;
		this.name = name;
	}

	public int getCateID() {
		return this.cateID;
	}

	/**
	 * 
	 * @param cateID
	 */
	public void setCateID(int cateID) {
		this.cateID = cateID;
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
package com.springboot.onlineshopping.model.item;

public class Type {

	private int typeID;
	private String name;
	public Type() {
		// TODO Auto-generated constructor stub
	}

	public Type(int typeID, String name) {
		this.typeID = typeID;
		this.name = name;
	}

	public int getTypeID() {
		return this.typeID;
	}

	/**
	 * 
	 * @param typeID
	 */
	public void setTypeID(int typeID) {
		this.typeID = typeID;
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
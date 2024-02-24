package com.springboot.onlineshopping.model.item;

public class Price {

	private int costPrice;
	private int sellingPrice;
	public Price() {
		// TODO Auto-generated constructor stub
	}
	public Price(int costPrice, int sellingPrice) {
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
	}
	public int getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}
	public int getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	

}
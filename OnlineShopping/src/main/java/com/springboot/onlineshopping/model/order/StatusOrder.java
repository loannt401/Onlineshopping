package com.springboot.onlineshopping.model.order;

public class StatusOrder {
	private int statusOrderID;
	private String nameStatusOrder;
	public StatusOrder(int statusOrderID, String nameStatusOrder) {
		super();
		this.statusOrderID = statusOrderID;
		this.nameStatusOrder = nameStatusOrder;
	}
	public int getStatusOrderID() {
		return statusOrderID;
	}
	public void setStatusOrderID(int statusOrderID) {
		this.statusOrderID = statusOrderID;
	}
	public String getNameStatusOrder() {
		return nameStatusOrder;
	}
	public void setNameStatusOrder(String nameStatusOrder) {
		this.nameStatusOrder = nameStatusOrder;
	}
	
}

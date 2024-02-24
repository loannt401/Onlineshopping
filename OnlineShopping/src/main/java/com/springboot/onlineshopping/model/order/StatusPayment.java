package com.springboot.onlineshopping.model.order;

public class StatusPayment {
	private int statusPayID;
	private String nameStatusPay;
	public StatusPayment(int statusPayID, String nameStatusPay) {
		this.statusPayID = statusPayID;
		this.nameStatusPay = nameStatusPay;
	}
	public int getStatusPayID() {
		return statusPayID;
	}
	public void setStatusPayID(int statusPayID) {
		this.statusPayID = statusPayID;
	}
	public String getNameStatusPay() {
		return nameStatusPay;
	}
	public void setNameStatusPay(String nameStatusPay) {
		this.nameStatusPay = nameStatusPay;
	}
	
}

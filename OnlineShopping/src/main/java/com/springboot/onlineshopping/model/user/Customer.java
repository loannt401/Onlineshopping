package com.springboot.onlineshopping.model.user;


public class Customer extends User{
	private int customerID;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(User user) {
		super();
		this.user = user;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
}
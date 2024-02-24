package com.springboot.onlineshopping.model.user;

public class Admin extends User{
	private int adminID;
	

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Admin(int adminID) {
		this.adminID = adminID;
	}

	public int getAdminID() {
		return this.adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

}
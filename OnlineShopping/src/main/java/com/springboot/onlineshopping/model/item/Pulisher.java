package com.springboot.onlineshopping.model.item;

public class Pulisher {

	private int pulisherID;
	private String fullname;
	private String email;
	private String phone;
	private String address;
	public Pulisher() {
		// TODO Auto-generated constructor stub
	}

	public Pulisher(int pulisherID, String fullname, String email, String phone, String address) {
		this.pulisherID = pulisherID;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public int getPulisherID() {
		return this.pulisherID;
	}

	/**
	 * 
	 * @param pulisherID
	 */
	public void setPulisherID(int pulisherID) {
		this.pulisherID = pulisherID;
	}

	public String getFullname() {
		return this.fullname;
	}

	/**
	 * 
	 * @param fullname
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	/**
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

}
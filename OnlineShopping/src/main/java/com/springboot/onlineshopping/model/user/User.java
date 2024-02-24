package com.springboot.onlineshopping.model.user;



public class User {
	private int userID;
	private Name name;
	private Role role;
	private Account account;
	private Address address;
	private String phone;
	private String email;
	private String dob;
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Name name, Role role, Account account, String phone, String email) {
		this.name = name;
		this.role = role;
		this.account = account;
		this.phone = phone;
		this.email = email;
	}
	
	public User(int userID, Name name, Role role, Account account, Address address, String phone, String email,
			String dob) {
		this.userID = userID;
		this.name = name;
		this.role = role;
		this.account = account;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.dob = dob;
	}


	public User(Name name, Role role, Account account, Address address, String phone, String email, String dob) {
		this.name = name;
		this.role = role;
		this.account = account;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.dob = dob;
	}

	public int getUserID() {
		return this.userID;
	}

	/**
	 * 
	 * @param userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Name getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(Name name) {
		this.name = name;
	}

	public Role getRole() {
		return this.role;
	}

	/**
	 * 
	 * @param role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	public Account getAccount() {
		return this.account;
	}

	/**
	 * 
	 * @param account
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	public Address getAddress() {
		return this.address;
	}

	/**
	 * 
	 * @param address
	 */
	public void setAddress(Address address) {
		this.address = address;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

}
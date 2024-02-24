package com.springboot.onlineshopping.model.user;

public class Account {

	private String username;
	private String password;
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Account(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
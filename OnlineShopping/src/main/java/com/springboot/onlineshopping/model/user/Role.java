package com.springboot.onlineshopping.model.user;


public class Role {

	private int roleID;
	private String name;
	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(int roleID, String name) {
		this.roleID = roleID;
		this.name = name;
	}

	public int getRoleID() {
		return this.roleID;
	}

	/**
	 * 
	 * @param roleID
	 */
	public void setRoleID(int roleID) {
		this.roleID = roleID;
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
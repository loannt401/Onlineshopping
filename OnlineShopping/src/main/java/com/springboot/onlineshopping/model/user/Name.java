package com.springboot.onlineshopping.model.user;


public class Name {

	private String firstName;
	private String midName;
	private String lastName;
	public Name() {
		// TODO Auto-generated constructor stub
	}
	public Name(String firstName, String midName, String lastName) {
		super();
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMidName() {
		return midName;
	}
	public void setMidName(String midName) {
		this.midName = midName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName(){
		return this.firstName + " " + this.midName + " " + this.lastName;
	}

}
package com.springboot.onlineshopping.model.item;

import java.sql.Date;

public class Author {

	private int authorID;
	private String fullname;
	private Date dob;
	private String email;
	private String phone;
	public Author() {
		// TODO Auto-generated constructor stub
	}

	public Author(int authorID, String fullname, Date dob, String email, String phone) {
		this.authorID = authorID;
		this.fullname = fullname;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
	}

	public int getAuthorID() {
		return this.authorID;
	}

	/**
	 * 
	 * @param authorID
	 */
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
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

	public Date getDob() {
		return this.dob;
	}

	/**
	 * 
	 * @param dob
	 */
	public void setDob(Date dob) {
		this.dob = dob;
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

}
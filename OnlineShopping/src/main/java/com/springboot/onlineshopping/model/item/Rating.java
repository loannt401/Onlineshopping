package com.springboot.onlineshopping.model.item;

public class Rating {

	private int ratingID;
	private String name;
	private int star;
	public Rating() {
		// TODO Auto-generated constructor stub
	}

	public Rating(int ratingID, String name, int star) {
		this.ratingID = ratingID;
		this.name = name;
		this.star = star;
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

	public int getStar() {
		return this.star;
	}

	/**
	 * 
	 * @param star
	 */
	public void setStar(int star) {
		this.star = star;
	}

	public int getRatingID() {
		return this.ratingID;
	}

	/**
	 * 
	 * @param ratingID
	 */
	public void setRatingID(int ratingID) {
		this.ratingID = ratingID;
	}

}
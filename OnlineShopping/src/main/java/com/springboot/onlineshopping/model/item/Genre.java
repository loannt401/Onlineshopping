package com.springboot.onlineshopping.model.item;

public class Genre {

	private int genreID;
	private String name;
	public Genre() {
		// TODO Auto-generated constructor stub
	}

	public Genre(int genreID, String name) {
		this.genreID = genreID;
		this.name = name;
	}

	public int getGenreID() {
		return this.genreID;
	}

	/**
	 * 
	 * @param genreID
	 */
	public void setGenreID(int genreID) {
		this.genreID = genreID;
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
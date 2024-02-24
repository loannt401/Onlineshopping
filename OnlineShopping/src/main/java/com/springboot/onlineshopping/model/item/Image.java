package com.springboot.onlineshopping.model.item;

public class Image {

	private String name;
	private String link;
	public Image() {
		// TODO Auto-generated constructor stub
	}

	public Image(String name, String link) {
		this.name = name;
		this.link = link;
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

	public String getLink() {
		return this.link;
	}

	/**
	 * 
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}

}
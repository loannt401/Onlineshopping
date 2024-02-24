package com.springboot.onlineshopping.model.item;


public class Book extends Item{

	private int bookID;
	private Author author;
	private Genre genre;
	private Pulisher pulisher;
	private Item item;
	private String country;
	private String releaseDate;

	public Book(int bookID, Author author, Genre genre, Pulisher pulisher, Item item, String country,
			String releaseDate) {
		this.bookID = bookID;
		this.author = author;
		this.genre = genre;
		this.pulisher = pulisher;
		this.item = item;
		this.country = country;
		this.releaseDate = releaseDate;
	}

	public int getBookID() {
		return this.bookID;
	}

	/**
	 * 
	 * @param bookID
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public Author getAuthor() {
		return this.author;
	}

	/**
	 * 
	 * @param author
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}

	public Genre getGenre() {
		return this.genre;
	}

	/**
	 * 
	 * @param genre
	 */
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Pulisher getPulisher() {
		return this.pulisher;
	}

	/**
	 * 
	 * @param pulisher
	 */
	public void setPulisher(Pulisher pulisher) {
		this.pulisher = pulisher;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

}
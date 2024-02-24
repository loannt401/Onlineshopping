package com.springboot.onlineshopping.model.item;


import java.util.List;


public class Item {

	private int itemID;
	private String name;
	private String description;
	private Price price;
	private int quantity;
	private Category category;
	private Image image;
	private List<Feedback> feedback;
	private	float ratingItem;
    
	public Item() {
		// TODO Auto-generated constructor stub
	}
	
	public Item(int itemID, String name, String description, Price price, int quantity, Category category, Image image,
			List<Feedback> feedback, float ratingItem) {
		this.itemID = itemID;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.image = image;
		this.feedback = feedback;
		this.ratingItem = ratingItem;
	}



	public int getItemID() {
		return this.itemID;
	}

	/**
	 * 
	 * @param itemID
	 */
	public void setItemID(int itemID) {
		this.itemID = itemID;
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

	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Price getPrice() {
		return this.price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return this.category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public Image getImage() {
		return this.image;
	}
	public void setImage(Image image) {
		this.image = image;
	}

	public List<Feedback> getFeedback() {
		return this.feedback;
	}
	
	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

	public float getRatingItem() {
		return ratingItem;
	}
	
	public int getIntRatingItem() {
		return (int) ratingItem;
	}

	public void setRatingItem(float ratingItem) {
		this.ratingItem = ratingItem;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if(obj instanceof Item) {
	    	Item item = (Item) obj;
	    	if(this.itemID == item.itemID) return true;
	    }
	    return false;
	}


	@Override
	public int hashCode() {
	    return itemID;
	}

}
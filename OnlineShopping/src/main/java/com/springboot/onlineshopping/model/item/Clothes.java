package com.springboot.onlineshopping.model.item;


public class Clothes extends Item{

	private int clothesID;
	private String material;
	private Type type;
	private String gender;
	private String color;
	private Item item;


	public Clothes(int clothesID, String material, Type type, String gender, String color, Item item) {
		this.clothesID = clothesID;
		this.material = material;
		this.type = type;
		this.gender = gender;
		this.color = color;
		this.item = item;
	}

	public int getClothesID() {
		return this.clothesID;
	}

	/**
	 * 
	 * @param clothesID
	 */
	public void setClothesID(int clothesID) {
		this.clothesID = clothesID;
	}

	public String getMaterial() {
		return this.material;
	}

	/**
	 * 
	 * @param material
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	public Type getType() {
		return this.type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(Type type) {
		this.type = type;
	}

	public String getGender() {
		return this.gender;
	}

	/**
	 * 
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getColor() {
		return this.color;
	}

	/**
	 * 
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
package com.springboot.onlineshopping.model.item;


public class Phone extends Item{

	private int phoneID;
	private Brand brand;
	private String ram;
	private String system;
	private String color;
	private String size;
	private Item item;
	public Phone() {
		// TODO Auto-generated constructor stub
	}

	public Phone(int phoneID, Brand brand, String ram, String system, String color, String size, Item item) {
		this.phoneID = phoneID;
		this.brand = brand;
		this.ram = ram;
		this.system = system;
		this.color = color;
		this.size = size;
		this.item = item;
	}

	public int getPhoneID() {
		return phoneID;
	}

	public void setPhoneID(int phoneID) {
		this.phoneID = phoneID;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
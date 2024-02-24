package com.springboot.onlineshopping.model.order;

import com.springboot.onlineshopping.model.user.Address;

public class Shipment {

	private int shipID;
	private TypeShipment typeShip;
	private String fullname;
	private String phone;
	private Address address;
	public Shipment(int shipID, TypeShipment typeShip, String fullname, String phone, Address address) {
		this.shipID = shipID;
		this.typeShip = typeShip;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
	}
	public Shipment(TypeShipment typeShip, String fullname, String phone, Address address) {
		this.typeShip = typeShip;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
	}
	public int getShipID() {
		return shipID;
	}
	public void setShipID(int shipID) {
		this.shipID = shipID;
	}
	public TypeShipment getTypeShip() {
		return typeShip;
	}
	public void setTypeShip(TypeShipment typeShip) {
		this.typeShip = typeShip;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	

}
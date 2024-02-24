package com.springboot.onlineshopping.model.order;

public class TypeShipment {
    private int typeShipID;
    private String nameShip;
    private int costShip;
	public TypeShipment(int typeShipID, String nameShip, int costShip) {
		this.typeShipID = typeShipID;
		this.nameShip = nameShip;
		this.costShip = costShip;
	}
	public int getTypeShipID() {
		return typeShipID;
	}
	public void setTypeShipID(int typeShipID) {
		this.typeShipID = typeShipID;
	}
	public String getNameShip() {
		return nameShip;
	}
	public void setNameShip(String nameShip) {
		this.nameShip = nameShip;
	}
	public int getCostShip() {
		return costShip;
	}
	public void setCostShip(int costShip) {
		this.costShip = costShip;
	}
	
	public String getFullShip() {
		return this.nameShip + " (Phí: " + this.costShip + "VND)"; 
	}
    
}

package com.springboot.onlineshopping.model.order;


public class Order {

	private int orderID;
	private StatusOrder statusOrder;
	private Cart cart;
	private Payment payment;
	private Shipment shipment;
	private String date;
	public Order(int orderID, StatusOrder statusOrder, Cart cart, Payment payment, Shipment shipment, String date) {
		this.orderID = orderID;
		this.statusOrder = statusOrder;
		this.cart = cart;
		this.payment = payment;
		this.shipment = shipment;
		this.date = date;
	}
	public Order(StatusOrder statusOrder, Cart cart, Payment payment, Shipment shipment) {
		this.statusOrder = statusOrder;
		this.cart = cart;
		this.payment = payment;
		this.shipment = shipment;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public StatusOrder getStatusOrder() {
		return statusOrder;
	}
	public void setStatusOrder(StatusOrder statusOrder) {
		this.statusOrder = statusOrder;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Shipment getShipment() {
		return shipment;
	}
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	


}
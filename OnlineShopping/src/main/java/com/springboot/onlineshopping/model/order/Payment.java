package com.springboot.onlineshopping.model.order;

public class Payment {

	private int payID;
	private MethodPayment methodPayment;
	private long totalPrice;
	private int costShipment;
	private long amountCart;
	private StatusPayment statusPayment;
	private String cashType;
	private float cashReceived;
	private String cardBank;
	private String cardName;
	private String cardNumber;

	public Payment(int payID, MethodPayment methodPayment, long totalPrice, int costShipment, long amountCart,
			StatusPayment statusPayment, String cashType, float cashReceived, String cardBank, String cardName,
			String cardNumber) {
		this.payID = payID;
		this.methodPayment = methodPayment;
		this.totalPrice = totalPrice;
		this.costShipment = costShipment;
		this.amountCart = amountCart;
		this.statusPayment = statusPayment;
		this.cashType = cashType;
		this.cashReceived = cashReceived;
		this.cardBank = cardBank;
		this.cardName = cardName;
		this.cardNumber = cardNumber;
	}

	public Payment(long totalPrice, int costShipment, long amountCart) {
		this.totalPrice = totalPrice;
		this.costShipment = costShipment;
		this.amountCart = amountCart;
	}

	public int getPayID() {
		return payID;
	}

	public void setPayID(int payID) {
		this.payID = payID;
	}

	public MethodPayment getMethodPayment() {
		return methodPayment;
	}

	public void setMethodPayment(MethodPayment methodPayment) {
		this.methodPayment = methodPayment;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCostShipment() {
		return costShipment;
	}

	public void setCostShipment(int costShipment) {
		this.costShipment = costShipment;
	}

	public long getAmountCart() {
		return amountCart;
	}

	public void setAmountCart(long amountCart) {
		this.amountCart = amountCart;
	}

	public StatusPayment getStatusPayment() {
		return statusPayment;
	}

	public void setStatusPayment(StatusPayment statusPayment) {
		this.statusPayment = statusPayment;
	}

	public String getCashType() {
		return cashType;
	}

	public void setCashType(String cashType) {
		this.cashType = cashType;
	}

	public float getCashReceived() {
		return cashReceived;
	}

	public void setCashReceived(float cashReceived) {
		this.cashReceived = cashReceived;
	}

	public String getCardBank() {
		return cardBank;
	}

	public void setCardBank(String cardBank) {
		this.cardBank = cardBank;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
		

}
package com.springboot.onlineshopping.model.order;

import java.sql.Date;
import java.util.Map;

import com.springboot.onlineshopping.model.item.Item;
import com.springboot.onlineshopping.model.user.Customer;

public class Cart {

	private int cartID;
	private Customer customer;
	private Map<Item, Integer> items;
	private long amount;
	private Date createDate;
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Cart(int cartID, Customer customer, Map<Item, Integer> items, long amount, Date createDate) {
		this.cartID = cartID;
		this.customer = customer;
		this.items = items;
		this.amount = amount;
		this.createDate = createDate;
	}

	public Cart(Customer customer, long amount, Date createDate) {
		this.customer = customer;
		this.amount = amount;
		this.createDate = createDate;
	}

	public int getCartID() {
		return this.cartID;
	}

	/**
	 * 
	 * @param cartID
	 */
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	/**
	 * 
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Map<Item, Integer> getItems() {
		return this.items;
	}

	/**
	 * 
	 * @param items
	 */
	public void setItems(Map<Item, Integer> items) {
		this.items = items;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
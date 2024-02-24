package com.springboot.onlineshopping.model.item;


import com.springboot.onlineshopping.model.user.Customer;

public class Feedback {

	private int feedbackID;
	private Customer customer;
	private String text;
	private String date;
	private Rating rating;
	public Feedback() {
		// TODO Auto-generated constructor stub
	}
	public Feedback(int feedbackID, Customer customer, String text, String date, Rating rating) {
		this.feedbackID = feedbackID;
		this.customer = customer;
		this.text = text;
		this.date = date;
		this.rating = rating;
	}
	public Feedback(Customer customer, String text, Rating rating) {
		this.customer = customer;
		this.text = text;
		this.rating = rating;
	}
	public int getFeedbackID() {
		return feedbackID;
	}
	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}

}
package com.springboot.onlineshopping.dao.itemDAO;

import java.util.List;

import com.springboot.onlineshopping.model.item.Category;
import com.springboot.onlineshopping.model.item.Feedback;
import com.springboot.onlineshopping.model.item.Item;
import com.springboot.onlineshopping.model.item.Rating;
import com.springboot.onlineshopping.model.order.Order;

public interface ItemDAO {
	List<Item> getCategoryItem(int cateID);
	List<Item> getAllItem();
	Item getItem(int itemID);
	List<Item> findItem(String key);
	boolean updateCategory(Category category);
	Category getCategory(int cateID);
	Rating getRating(int ratingID);
	Feedback getFeedback(int customerID, int itemID, int cartID);
	Feedback addFeedback(Feedback feedback, Order order, int itemID);
	Feedback getFeedback(int feedbackID);
	List<Feedback> getRatingFeedback(int ratingID);
	List<Feedback> getItemFeedback(int itemID);
	
}






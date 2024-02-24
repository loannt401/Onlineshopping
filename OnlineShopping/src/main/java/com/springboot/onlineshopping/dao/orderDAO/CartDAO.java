package com.springboot.onlineshopping.dao.orderDAO;

import java.util.Map;

import com.springboot.onlineshopping.model.item.Item;
import com.springboot.onlineshopping.model.order.Cart;
import com.springboot.onlineshopping.model.order.Order;
import com.springboot.onlineshopping.model.user.Customer;

public interface CartDAO {
	boolean createCart(Cart cart);
	boolean updateCart(Cart cart, Item item, int quantity);
	Cart getCustomer(Customer customer);
	Cart getCart(int cartID);
	Cart getOrder(Order order);
	Map<Item, Integer> getItemCart(int cartID);
	boolean addCart(Cart cart, Item item, int quantity);
	long getAmount(int cartID);
	boolean removeItemCart(int cartID, int itemID, int quantity);
}
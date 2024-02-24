package com.springboot.onlineshopping.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.onlineshopping.dao.itemDAO.ItemDAO;
import com.springboot.onlineshopping.dao.orderDAO.CartDAO;
import com.springboot.onlineshopping.model.item.Item;
import com.springboot.onlineshopping.model.order.Cart;
import com.springboot.onlineshopping.model.user.Customer;
import com.springboot.onlineshopping.model.user.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	private CartDAO cartDAO;
	private ItemDAO itemDAO;
	
	@Autowired
	public CartController(CartDAO cartDAO, ItemDAO itemDAO) {
		this.cartDAO = cartDAO;
		this.itemDAO = itemDAO;
	}

	@GetMapping("/user/addcart")
	public String addcart(@RequestParam(name="itemid") String itemid_raw, HttpSession session) {
		try {
			int itemid = Integer.parseInt(itemid_raw);
			Item item = itemDAO.getItem(itemid);
			User user = (User) session.getAttribute("user");
			if (user == null || user.getRole().getRoleID()==1) {
				return "redirect:/login";
			}
			else if(user != null && user.getRole().getRoleID()==2){
				Customer customer = new Customer(user);
				Cart cart = cartDAO.getCustomer(customer);
				if(cart != null) {
					boolean status = cartDAO.addCart(cart, item, 1);
					System.out.println(status);
					if (status == true) {
						return "redirect:/home?success=1";
					}
				}
				
			}
			
		} catch (NumberFormatException e) {
			System.out.println("lỗi không xác định!");
		}

		return "redirect:/home";
	}
	
	@GetMapping("/user/addcart2")
	public String addcart2(@RequestParam(name="itemid") String itemid_raw, 
			@RequestParam(name="quantity") String quantity_raw,
			HttpSession session,
			Model model) {
		try {
			int itemid = Integer.parseInt(itemid_raw);
			int quantity = Integer.parseInt(quantity_raw);
			Item item = itemDAO.getItem(itemid);
			User user = (User) session.getAttribute("user");
			if (user == null || user.getRole().getRoleID()==1) {
				return "redirect:/login";
			}
			else if(user != null && user.getRole().getRoleID()==2) {
				Customer customer = new Customer(user);
				Cart cart = cartDAO.getCustomer(customer);
				if(cart != null) {
					boolean status = cartDAO.addCart(cart, item, quantity);
					System.out.println(status);
					if (status == true) {
						return "redirect:/detailitem?success=1&itemid=" + itemid;
					}
				}	
			}
			
		} catch (NumberFormatException e) {
			System.out.println("lỗi không xác định!");
		}

		return "redirect:/home";
	}
	
	@GetMapping("/user/cart")
	public String cart(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Customer customer = new Customer(user);
		if(user != null && user.getRole().getRoleID()==2) {
			Cart cart = cartDAO.getCustomer(customer);
			model.addAttribute("cart", cart);
			return "cart";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/removecart")
	public String removecart(@RequestParam(name="cartid") String cartID_raw,
			@RequestParam(name="itemid") String itemID_raw,
			@RequestParam(name="quantity") String quantity_raw) {
		try {
			int cartID = Integer.parseInt(cartID_raw);
			int itemID = Integer.parseInt(itemID_raw);
			int quantity = Integer.parseInt(quantity_raw);
			boolean status = cartDAO.removeItemCart(cartID, itemID, quantity);
		} catch (NumberFormatException e) {
			System.out.println(e);
		}
		return "redirect:/cart";
	}
	
	@GetMapping("/updatecart")
	public String updatecart(@RequestParam(name="cartid") String cartID_raw,
			@RequestParam(name="itemid") String itemID_raw,
			@RequestParam(name="quantity") String quantity_raw) {
		try {
			int cartID = Integer.parseInt(cartID_raw);
			int itemID = Integer.parseInt(itemID_raw);
			int quantity = Integer.parseInt(quantity_raw);
			Cart cart = cartDAO.getCart(cartID);
			Item item = itemDAO.getItem(itemID);
			boolean status = cartDAO.updateCart(cart, item, quantity);
			System.out.println(status);
		} catch (NumberFormatException e) {
			System.out.println(e);
		}
		return "redirect:/cart";
	}

}




















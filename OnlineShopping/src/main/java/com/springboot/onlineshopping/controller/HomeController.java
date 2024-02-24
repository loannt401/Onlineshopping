package com.springboot.onlineshopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.onlineshopping.dao.itemDAO.BookDAO;
import com.springboot.onlineshopping.dao.itemDAO.ClothesDAO;
import com.springboot.onlineshopping.dao.itemDAO.ItemDAO;
import com.springboot.onlineshopping.dao.itemDAO.PhoneDAO;
import com.springboot.onlineshopping.model.item.Book;
import com.springboot.onlineshopping.model.item.Clothes;
import com.springboot.onlineshopping.model.item.Item;
import com.springboot.onlineshopping.model.item.Phone;

@Controller
public class HomeController {
	private final ItemDAO itemDAO;
	private final BookDAO bookDAO;
	private final ClothesDAO clothesDAO;
	private final PhoneDAO phoneDAO;
	
	@Autowired
	public HomeController(ItemDAO itemDAO, BookDAO bookDAO, ClothesDAO clothesDAO, PhoneDAO phoneDAO) {
		this.itemDAO = itemDAO;
		this.bookDAO = bookDAO;
		this.clothesDAO = clothesDAO;
		this.phoneDAO = phoneDAO;
	}
	

	@GetMapping(value = {"/home", "/user"})
	public String home(@RequestParam(name="success", required = false) String success, 
			@RequestParam(name="cateid", required = false) String cateID_raw,
			@RequestParam(name="search", required = false) String search,
			Model model) {
		List<Item> listitem = new ArrayList<Item>();
		if(cateID_raw == null) {
			listitem = itemDAO.getAllItem();
		}
		else {
			try {
				int cateID = Integer.parseInt(cateID_raw);
				listitem = itemDAO.getCategoryItem(cateID);
			} catch (NumberFormatException e) {
				System.out.println("So khong dung dinh dang");
			}
		}
		if(search != null) {
			listitem = itemDAO.findItem(search);
			
		}
		model.addAttribute("listitem",listitem);
		try {
			int success2 = Integer.parseInt(success);
			if(success2 == 1) {
				model.addAttribute("success", "Thêm sản phẩm thành công!");
			}
			else if(success2 == 2) {
				model.addAttribute("success", "Đổi mật khẩu thành công!");
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		return "home";
	}

	@GetMapping("/detailitem")
	public String detailitem(@RequestParam(name = "itemid") String itemId, 
			@RequestParam(name="success", required = false) String success,
			Model model) {
		try {
			int itemID = Integer.parseInt(itemId);
			Item item = itemDAO.getItem(itemID);
			int categoryID = item.getCategory().getCateID();
			model.addAttribute("success", success);
			model.addAttribute("item", item);
			if (categoryID == 1) {
				Book book = bookDAO.getBook(itemID);
				model.addAttribute("book", book);
			}
			else if (categoryID == 3) {
				Phone phone = phoneDAO.getPhone(itemID);
				System.out.println(phone.getSystem());
				model.addAttribute("phone", phone);
				
			}
			else if (categoryID == 2) {
				Clothes clothes = clothesDAO.getClothes(itemID);
				model.addAttribute("clothes", clothes);
			} 
			
			return "itemdetail";
		} catch (NumberFormatException e) {
			return "Invalid integer format";
		}
		
	}
}

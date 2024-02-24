package com.springboot.onlineshopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.onlineshopping.dao.orderDAO.OrderDAO;
import com.springboot.onlineshopping.model.order.Order;

@Controller
public class ManagerController {
	@Autowired
	private OrderDAO orderDAO;
	
	@GetMapping(value = {"/admin", "/manager"})
	public String manager(@RequestParam(name="statusorderid", required = false) String statusOrderID_raw,
			@RequestParam(name="success", required = false) String success,
			Model model) {
		List<Order> list = new ArrayList<Order>();
		if(statusOrderID_raw != null && !statusOrderID_raw.isEmpty()) {
			try {
				int statusOrderID = Integer.parseInt(statusOrderID_raw);
				list = orderDAO.getStatusOrderOrder(statusOrderID);
				model.addAttribute("statusorderid", statusOrderID);
			} catch (NumberFormatException e) {
				return "Không đúng định dạng";
			}
		}
		else {
			list = orderDAO.getAllOrder();
		}
		try {
			int success2 = Integer.parseInt(success);
			if(success2 == 1) {
				model.addAttribute("success", "Đã cập nhật trạng thái thành công!");
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		model.addAttribute("list", list);
		return "manage";
	}
	
	@PostMapping("/do-manager")
	public String domanager(@RequestParam(name="statusorderid") String statusOrderID_raw,
			@RequestParam(name="orderID") String orderID_raw){
		try {
			int statusOrderID = Integer.parseInt(statusOrderID_raw);
			int orderID = Integer.parseInt(orderID_raw);
			boolean status = orderDAO.updateOrder(orderID, statusOrderID);
			if(status) {
				return "redirect:/manager?success=1";
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		return "redirect:/manager";
	}
}

package com.springboot.onlineshopping.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.onlineshopping.dao.itemDAO.ItemDAO;
import com.springboot.onlineshopping.dao.orderDAO.CartDAO;
import com.springboot.onlineshopping.dao.orderDAO.OrderDAO;
import com.springboot.onlineshopping.model.item.Feedback;
import com.springboot.onlineshopping.model.item.Item;
import com.springboot.onlineshopping.model.item.Rating;
import com.springboot.onlineshopping.model.order.Cart;
import com.springboot.onlineshopping.model.order.MethodPayment;
import com.springboot.onlineshopping.model.order.Order;
import com.springboot.onlineshopping.model.order.Payment;
import com.springboot.onlineshopping.model.order.Shipment;
import com.springboot.onlineshopping.model.order.StatusOrder;
import com.springboot.onlineshopping.model.order.StatusPayment;
import com.springboot.onlineshopping.model.order.TypeShipment;
import com.springboot.onlineshopping.model.user.Address;
import com.springboot.onlineshopping.model.user.Customer;
import com.springboot.onlineshopping.model.user.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	@GetMapping("/checkout")
	public String checkout(@RequestParam(name="error", required = false) String error,
			Model model) {
		List<TypeShipment> listTypeShipments = orderDAO.getAllTypeShipments();
		model.addAttribute("list", listTypeShipments);
		try {
			int error2 = Integer.parseInt(error);
			if (error2 == 1) {
				model.addAttribute("error", "Nhập thiếu thông tin đăng kí!");
			}
			else if (error2 == 2) {
				model.addAttribute("error", "Số điện thoại không đúng định dạng!");
			}
		} catch (NumberFormatException e) {
			System.out.println("lỗi không đúng định dạng!");
		}
		return "shipment";
	}
	
	@PostMapping("/do-shipment")
	public String doshipment(@RequestParam(name="fullname") String fullname,
			@RequestParam(name="phone") String phone,
			@RequestParam(name="typeShipID") String typeShipID_raw, 
			@RequestParam(name="ward") String ward,
			@RequestParam(name="district") String district,
			@RequestParam(name="city") String city,
			@RequestParam(name="addressDetail") String addressDetail,
			HttpSession session) {
		if(fullname != null && !fullname.isEmpty() &&
				phone != null && !phone.isEmpty() &&
				ward != null && !ward.isEmpty() &&
				district != null && !district.isEmpty() &&
				city != null && !city.isEmpty() &&
				addressDetail != null && !addressDetail.isEmpty()) {
			String phoneRegex = "\\d{10,12}";
			if(!Pattern.matches(phoneRegex, phone)) {
				return "redirect:/checkout?error=2";
			}
			else {
				try {
					int typeShipID = Integer.parseInt(typeShipID_raw);
					TypeShipment typeShipment = orderDAO.getTypeShipment(typeShipID);
					Address address = new Address(addressDetail, ward, district, city);
					Shipment shipment = new Shipment(typeShipment, fullname, phone, address);
					session.setAttribute("shipment", shipment);
					return "redirect:/payment";
				} catch (NumberFormatException e) {
					return "redirect:/checkout";
				}
			}
		}
		else {
			return "redirect:/checkout?error=1";
		}
	}
	
	@GetMapping("/payment")
	public String payment(@RequestParam(name="error", required = false) String error,
			HttpSession session, Model model) {
		Shipment shipment = (Shipment) session.getAttribute("shipment");
		User user = (User) session.getAttribute("user");
		Customer customer = new Customer(user);
		Cart cart = cartDAO.getCustomer(customer);
		long amountCart = cart.getAmount();
		int costShipment = shipment.getTypeShip().getCostShip();
		long totalPrice = amountCart + (long) costShipment;
		Payment payment = new Payment(totalPrice, costShipment, amountCart);
		session.setAttribute("payment", payment);
		model.addAttribute("cart", cart);
		try {
			int error2 = Integer.parseInt(error);
			if (error2 == 1) {
				model.addAttribute("error", "Không thể đặt được đơn hàng!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "payment";
	}
	
	@PostMapping("/do-order")
	public String doorder(@RequestParam(name="methodPayID") String methodPayID_raw,
			HttpSession session) {
		try {
			int methodPayID = Integer.parseInt(methodPayID_raw);
			Payment payment = (Payment) session.getAttribute("payment");
			session.removeAttribute("payment");
			MethodPayment methodPayment = orderDAO.getMethodPayment(methodPayID);
			payment.setMethodPayment(methodPayment);
			if(methodPayID == 1) {
				StatusPayment statusPayment = orderDAO.getStatusPayment(2);
				payment.setStatusPayment(statusPayment);
				session.setAttribute("payment", payment);
				return "redirect:/paycard";
			}
			else if(methodPayID == 2){
				StatusPayment statusPayment = orderDAO.getStatusPayment(1);
				payment.setStatusPayment(statusPayment);
				session.setAttribute("payment", payment);
				return "redirect:/order";
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		return "redirect:/payment?error=1";
	}
	
	@GetMapping("/paycard")
	public String paycard(@RequestParam(name="error", required = false) String error, Model model) {
		try {
			int error2 = Integer.parseInt(error);
			if (error2 == 1) {
				model.addAttribute("error", "Nhập thiếu thông tin đăng kí!");
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		return "paycard";
	}
	
	@PostMapping("/do-paycard")
	public String dopaycard(@RequestParam(name="cardBank") String cardBank,
			@RequestParam(name="cardName") String cardName,
			@RequestParam(name="cardNumber") String cardNumber,
			HttpSession session) {
		if(cardBank != null && !cardBank.isEmpty() &&
				cardName != null && !cardName.isEmpty() &&
				cardNumber != null && !cardNumber.isEmpty()) {
			Payment payment = (Payment) session.getAttribute("payment");
			session.removeAttribute("payment");
			payment.setCardBank(cardBank);
			payment.setCardName(cardName);
			payment.setCardNumber(cardNumber);
			session.setAttribute("payment", payment);
			return "redirect:/order";
		}
		else {
			return "redirect:/paycard?error=1";
		}
	}
	
	@GetMapping("/order")
	public String order(HttpSession session, Model model) {
		Shipment shipment = (Shipment) session.getAttribute("shipment");
		User user = (User) session.getAttribute("user");
		Payment payment = (Payment) session.getAttribute("payment");
		Customer customer = new Customer(user);
		Cart cart = cartDAO.getCustomer(customer);
		StatusOrder statusOrder = orderDAO.getStatusOrder(1);
		Order order = new Order(statusOrder, cart, payment, shipment);
		Order order2 = orderDAO.addOrder(order);
		if(order2 != null) {
			Calendar calendar = Calendar.getInstance();
			Date date = new Date(calendar.getTime().getTime());
			Cart cart2 = new Cart(customer, 0, date);
			boolean status2 = cartDAO.createCart(cart2);
			if(status2) {
				model.addAttribute("order", order2);
				session.removeAttribute("shipment");
				session.removeAttribute("payment");
				model.addAttribute("success", "Đặt hàng thành công!");
				return "order";
			}
		}
		return "redirect:/payment?error=1";
	}
	
	@GetMapping("/orderhistory")
	public String orderhistory(@RequestParam(name="statusorderid", required = false) String statusOrderID_raw,
			@RequestParam(name="orderid", required = false) String orderID_raw,
			@RequestParam(name="error", required = false) String error,
			@RequestParam(name="itemid", required = false) String itemID_raw,
			HttpSession session, Model model) {
		List<Order> list = new ArrayList<Order>();
		User user = (User) session.getAttribute("user");
		int userID = user.getUserID();
		if(statusOrderID_raw != null && !statusOrderID_raw.isEmpty()) {
			try {
				int statusOrderID = Integer.parseInt(statusOrderID_raw);
				list = orderDAO.getCustomerStatusOrder(userID, statusOrderID);
				model.addAttribute("statusorderid", statusOrderID);
			} catch (NumberFormatException e) {
				return "Không đúng định dạng";
			}
		}
		else {
			list = orderDAO.getCustomerOrder(userID);
		}
		if(orderID_raw != null && !orderID_raw.isEmpty()) {
			try {
				int orderID = Integer.parseInt(orderID_raw);
				Order order = orderDAO.getOrder(orderID);
				model.addAttribute("order", order);
			} catch (NumberFormatException e) {
				return "Không đúng định dạng";
			}
		}
		if(error != null && !error.isEmpty()) {
			try {
				int error2 = Integer.parseInt(error);
				int itemID = Integer.parseInt(itemID_raw);
				Item item = itemDAO.getItem(itemID);
				model.addAttribute("item", item);
				if (error2 == 1) {
					model.addAttribute("error", "Thiếu bình luận!");
				}
				else if (error2 == 2) {
					model.addAttribute("error", "Sản phẩm đã được đánh giá!");
				}
				else if (error2 == 3) {
					model.addAttribute("error", "Sản phẩm đã được đánh giá!");
				}
			} catch (NumberFormatException e) {
				System.out.println("lỗi không đúng định dạng!");
			}
		}
		model.addAttribute("list", list);
		return "orderhistory";
	}
	
	@GetMapping("/orderdetail")
	public String orderdetail(@RequestParam(name="orderID") String orderID_raw,
			Model model) {
		try {
			int orderID = Integer.parseInt(orderID_raw);
			Order order = orderDAO.getOrder(orderID);
			model.addAttribute("order", order);
			return "order";
		} catch (NumberFormatException e) {
			return "lỗi không đúng định dạng";
		}
	}
	
	@GetMapping("/feedback")
	public String feedback(@RequestParam(name="orderid") String orderID_raw, 
			Model model) {
		
		return "feedback";
	}
	
	@GetMapping("/cancelfeedback")
	public String cancelfeedback() {
		return "redirect:/orderhistory?statusorderid=4";
	}
	
	@PostMapping("/do-feedback")
	public String dofeedback(@RequestParam(name="itemid") String itemID_raw,
			@RequestParam(name="orderid") String orderID_raw,
			@RequestParam(name="text") String text,
			@RequestParam(name="star") String star_raw,
			HttpSession session) {
		if(text != null && !text.isEmpty() && star_raw != null && !star_raw.isEmpty()) {

			try {
				int itemID = Integer.parseInt(itemID_raw);
				int orderID = Integer.parseInt(orderID_raw);				
				int star = Integer.parseInt(star_raw);
				Rating rating = itemDAO.getRating(star);
				User user = (User) session.getAttribute("user");
				Customer customer = new Customer(user);
				Feedback feedback = new Feedback(customer, text, rating);
				Order order = orderDAO.getOrder(orderID);
				Feedback feedback2 = itemDAO.addFeedback(feedback, order, itemID);
				if(feedback2 != null) {
					return "redirect:/orderhistory?statusorderid=4&error=3&orderid=" + orderID_raw;
				}
				else {
					return "redirect:/orderhistory?statusorderid=4&error=2&orderid=" + orderID_raw + "&itemid=" + itemID_raw;
					
				}
			} catch (NumberFormatException e) {
				return "lỗi không đúng định dạng";
			}
		}
		else {
			return "redirect:/orderhistory?statusorderid=4&error=1&orderid=" + orderID_raw + "&itemid=" + itemID_raw;
		}
	}
}











package com.springboot.onlineshopping.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.onlineshopping.dao.orderDAO.CartDAO;
import com.springboot.onlineshopping.dao.userDAO.UserDAO;
import com.springboot.onlineshopping.model.order.Cart;
import com.springboot.onlineshopping.model.user.Account;
import com.springboot.onlineshopping.model.user.Address;
import com.springboot.onlineshopping.model.user.Customer;
import com.springboot.onlineshopping.model.user.Name;
import com.springboot.onlineshopping.model.user.Role;
import com.springboot.onlineshopping.model.user.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	private UserDAO userDAO;
	private CartDAO cartDAO;

	@Autowired
	public LoginController(UserDAO userDAO, CartDAO cartDAO) {
		super();
		this.userDAO = userDAO;
		this.cartDAO = cartDAO;
	}

	@GetMapping("/login")
	public String login(@RequestParam(name = "error", required = false) Integer error, Model model) {
		model.addAttribute("error", error);
		return "login";
	}
	
	@GetMapping("/register")
	public String register(@RequestParam(name = "error", required = false) String error, Model model) {
		try {
			int error2 = Integer.parseInt(error);
			if (error2 == 1) {
				model.addAttribute("error", "Nhập thiếu thông tin đăng kí!");
			}
			else if (error2 == 2) {
				model.addAttribute("error", "Số điện thoại không đúng định dạng!");
			}
			else if (error2 == 3) {
				model.addAttribute("error", "Email không đúng định dạng!");
			}
			else if (error2 == 4) {
				model.addAttribute("error", "Mật khẩu không trùng khớp!");
			}
			else if (error2 == 5) {
				model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
			}
		} catch (NumberFormatException e) {
			
		}
		return "register";
	}
	
	@PostMapping("/do-login")
	public String dologin(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password, HttpSession httpSession) {
		if(username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
			Account account = new Account(username, password);
			User user = userDAO.getAccount(account);
			if (user != null) {
				httpSession.setAttribute("user", user);
				if(user.getRole().getRoleID()==2) {
					return "redirect:/user";
				}
				else {
					return "redirect:/admin";
				}
			}
			else {
				return "redirect:/login?error=1";
			}
		}
		else {
			return "redirect:/login?error=2";
		}
	}
	
	
	@PostMapping("/do-register")
	public String doregister(@RequestParam(name = "firstname") String firstname,
			@RequestParam(name = "midname") String midname, 
			@RequestParam(name = "lastname") String lastname,
			@RequestParam(name = "username") String username,
			@RequestParam(name = "phone") String phone,
			@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "password2") String password2,
			Model model
			) {
		if(firstname != null && !firstname.isEmpty() && lastname != null && !lastname.isEmpty() 
		&& username != null && !username.isEmpty() && phone != null && !phone.isEmpty()
		&& email != null && !email.isEmpty() && password != null && !password.isEmpty()
		&& password2 != null && !password2.isEmpty()){
			String phoneRegex = "\\d{10,12}";
			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			User usercheck = userDAO.getUser(username);
			if(!Pattern.matches(phoneRegex, phone)) {
				return "redirect:/register?error=2";
			}
			else if (!Pattern.matches(emailRegex, email)) {
				return "redirect:/register?error=3";
			}
			
			else if(!password.equals(password2)){
				return "redirect:/register?error=4";
			}
			else if (usercheck != null) {
				return "redirect:/register?error=5";
			}
			else {
				String firstString = firstname.replaceAll("\\s", "");
				String midString = midname.replaceAll("\\s", "");
				String lastString = lastname.replaceAll("\\s", "");
				Name name = new Name(firstString, midString, lastString);
				Role role = userDAO.getRole(2);
				Account account = new Account(username, password);
				User user = new User(name, role, account, phone, email);
				Customer customer = new Customer(user);
				boolean status = userDAO.addCustomer(customer);
				if(status == true) {
					User user2 = userDAO.getUser(username);
					Customer customer2 = new Customer(user2);
					Calendar calendar = Calendar.getInstance();
					Date date = new Date(calendar.getTime().getTime());
					Cart cart = new Cart(customer2, 0, date);
					boolean status2 = cartDAO.createCart(cart);
					System.out.println(status2);
				}
				return "redirect:/login";
			}
		}
		else {
			return "redirect:/register?error=1";
		}
		
	}
	
	@GetMapping("/exit")
	public String exit(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/home";
	}
	
	@GetMapping("/changepassword")
	public String changepassword(@RequestParam(name="error", required = false) String error,
			Model model) {
		try {
			int error2 = Integer.parseInt(error);
			if (error2 == 1) {
				model.addAttribute("error", "Nhập thiếu thông tin đăng kí!");
			}
			else if (error2 == 2) {
				model.addAttribute("error", "Mật khẩu cũ không trùng khớp!");
			}
			else if (error2 == 3) {
				model.addAttribute("error", "Mật khẩu mới không trùng khớp!");
			}
			else if (error2 == 4) {
				model.addAttribute("error", "Lỗi hệ thống!");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "changepassword";
	}
	
	@PostMapping("/do-changepassword")
	public String dochangepassword(@RequestParam(name="password1") String password1,
			@RequestParam(name="password2") String password2,
			@RequestParam(name="password3") String password3,
			HttpSession session) {
		if(password1 != null && !password1.isEmpty() && 
				password2 != null && !password2.isEmpty() &&
				password3 != null && !password3.isEmpty() ) {
			User user = (User) session.getAttribute("user");
			String password_old = user.getAccount().getPassword();
			if(password1.equals(password_old)) {
				if(password2.equals(password3)) {
					boolean status = userDAO.changePassword(user, password2);
					if(status) {
						int userID = user.getUserID();
						session.removeAttribute("user");
						User user2 = userDAO.getUser(userID);
						session.setAttribute("user", user2);
						return "redirect:/home?success=2";
					}
					else {
						return "redirect:/changepassword?error=4";
					}
				}
				else {
					return "redirect:/changepassword?error=3";
				}
			}
			else {
				return "redirect:/changepassword?error=2";
			}
		}
		else {
			return "redirect:/changepassword?error=1";
		}
	}
	
	@GetMapping("/profile")
	public String profile(@RequestParam(name="error", required = false) String error,
			@RequestParam(name="success", required = false) String success,
			HttpSession session, Model model) {
		try {
			int error2 = Integer.parseInt(error);
			if (error2 == 1) {
				model.addAttribute("error", "Nhập thiếu thông tin đăng kí!");
			}
			else if (error2 == 2) {
				model.addAttribute("error", "Email không đúng định dạng!");
			}
			else if (error2 == 3) {
				model.addAttribute("error", "Lỗi hệ thống!");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			int success2 = Integer.parseInt(success);
			if(success2 == 1) {
				model.addAttribute("success", "Cập nhật thông tin thành công!");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "profile";
	}
	
	@PostMapping("/do-profile")
	public String doprofile(@RequestParam(name = "firstname") String firstname,
			@RequestParam(name = "midname") String midname, 
			@RequestParam(name = "lastname") String lastname,
			@RequestParam(name = "username") String username,
			@RequestParam(name = "phone") String phone,
			@RequestParam(name = "email") String email,
			@RequestParam(name = "dob") String dob,
			@RequestParam(name="ward") String ward,
			@RequestParam(name="district") String district,
			@RequestParam(name="city") String city,
			@RequestParam(name="addressDetail") String addressDetail,
			HttpSession session) {
		if (firstname != null && !firstname.isEmpty() 
				&& lastname != null && !lastname.isEmpty() 
				&& username != null && !username.isEmpty() 
				&& phone != null && !phone.isEmpty()
				&& email != null && !email.isEmpty() ) {

			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			if (!Pattern.matches(emailRegex, email)) {
				return "redirect:/profile?error=2";
			}
			else {
				User user = (User) session.getAttribute("user");
		        Name name = new Name(firstname, midname, lastname);
		        Role role = user.getRole();
		        Account account = new Account(username);
		        Address address = new Address(addressDetail, ward, district, city);
		        int userID = user.getUserID();
		        User user2 = new User(userID, name, role, account, address, phone, email, dob);
		        boolean status = userDAO.updateUser(user2);
		        if(status) {
		        	session.removeAttribute("user");
		        	session.setAttribute("user", user2);
		        	return "redirect:/profile?success=1";
		        }
		        else {
					return "redirect:/profile?error=3";
		        }
			}
	        
		}
		else {
			return "redirect:/profile?error=1";
		}
	}
}









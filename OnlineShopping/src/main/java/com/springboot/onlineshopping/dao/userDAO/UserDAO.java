package com.springboot.onlineshopping.dao.userDAO;

import java.util.List;

import com.springboot.onlineshopping.model.user.Account;
import com.springboot.onlineshopping.model.user.Customer;
import com.springboot.onlineshopping.model.user.Role;
import com.springboot.onlineshopping.model.user.User;

public interface UserDAO {
	User getAccount(Account account);
	boolean updateUser(User user);
	User getUser(int userID);
	boolean changePassword(User user, String password);
	User getUser(String username);
	Role getRole(int roleID);
	boolean addCustomer(Customer customer);
	boolean deleteCustomer(Customer customer);
	List<Customer> findCustomer(String key);
	List<Customer> getAllCustomer();
	Customer getCustomer(int customerID);

}
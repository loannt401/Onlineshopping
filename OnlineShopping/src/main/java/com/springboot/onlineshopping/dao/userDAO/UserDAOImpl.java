package com.springboot.onlineshopping.dao.userDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.onlineshopping.model.user.Account;
import com.springboot.onlineshopping.model.user.Address;
import com.springboot.onlineshopping.model.user.Customer;
import com.springboot.onlineshopping.model.user.Name;
import com.springboot.onlineshopping.model.user.Role;
import com.springboot.onlineshopping.model.user.User;

@Repository
public class UserDAOImpl implements UserDAO {
	private JdbcTemplate jdbc;

	@Autowired
	public UserDAOImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public User getAccount(Account account) {
		String sql = "SELECT * FROM onlineshopping.user where Username= ? and Password = ?";
		try {
			User user = jdbc.queryForObject(sql, this::mapUser, account.getUsername(), account.getPassword());
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean updateUser(User user) {
		String sql = "UPDATE `onlineshopping`.`user` "
				+ "SET `Username` = ?,"
				+ " `FirstName` = ?,"
				+ " `MidName` = ?,"
				+ " `LastName` = ?,"
				+ " `Phone` = ?,"
				+ " `Email` = ?,"
				+ " `Dob` = ?,"
				+ " `AddressDetail` = ?,"
				+ " `Ward` = ?, `District` = ?, `City` = ? "
				+ "WHERE (`UserID` = ?)";
		try {
			jdbc.update(sql, user.getAccount().getUsername(),
					user.getName().getFirstName(),
					user.getName().getMidName(),
					user.getName().getLastName(),
					user.getPhone(),
					user.getEmail(),
					user.getDob(),
					user.getAddress().getAddressDetail(),
					user.getAddress().getWard(),
					user.getAddress().getDistrict(),
					user.getAddress().getCity(),
					user.getUserID());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public User getUser(int userID) {
		String sql = "SELECT * FROM onlineshopping.user where user.UserID=?";
		try {
			User user = jdbc.queryForObject(sql, this::mapUser, userID);
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean changePassword(User user, String password) {
		String sql = "UPDATE `onlineshopping`.`user` "
				+ "SET `Password` = ? "
				+ "WHERE (`UserID` = ?)";
		try {
			jdbc.update(sql, password, user.getUserID());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public User getUser(String username) {
		String sql = "SELECT * FROM onlineshopping.user where Username=?";
		try {
			User user = jdbc.queryForObject(sql, this::mapUser, username);
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Role getRole(int roleID) {
		String sql = "SELECT * FROM onlineshopping.role where role.RoleID = ?";
		try {
			Role role = jdbc.queryForObject(sql, this::mapRole, roleID);
			return role;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean addCustomer(Customer customer) {
		User user = customer.getUser();
		String sql = "INSERT INTO `onlineshopping`.`user` (`RoleID`, `Username`, `Password`, "
				+ "`FirstName`, `MidName`, `LastName`, `Phone`, `Email`) \r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			jdbc.update(sql, user.getRole().getRoleID(),
					user.getAccount().getUsername(),
					user.getAccount().getPassword(),
					user.getName().getFirstName(),
					user.getName().getMidName(),
					user.getName().getLastName(),
					user.getPhone(),
					user.getEmail());
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Customer> findCustomer(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(int customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	private User mapUser(ResultSet rs, int rowNum) throws SQLException {
		Name name = new Name(rs.getString(5), rs.getString(6), rs.getString(7));
		Role role = getRole(rs.getInt(2));
		Account account = new Account(rs.getString(3), rs.getString(4));
		Address address = new Address(rs.getString(11), rs.getString(12),rs.getString(13), rs.getString(14));
		return new User(rs.getInt(1), name, role, account, address, rs.getString(8), rs.getString(9), rs.getString(10));
	}

	private Role mapRole(ResultSet rs, int rowNum) throws SQLException {
		return new Role(rs.getInt(1), rs.getString(2));
	}

}
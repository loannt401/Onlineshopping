package com.springboot.onlineshopping.dao.orderDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.onlineshopping.dao.itemDAO.ItemDAO;
import com.springboot.onlineshopping.dao.userDAO.UserDAO;
import com.springboot.onlineshopping.model.item.Item;
import com.springboot.onlineshopping.model.order.Cart;
import com.springboot.onlineshopping.model.order.Order;
import com.springboot.onlineshopping.model.user.Customer;
import com.springboot.onlineshopping.model.user.User;

@Repository
public class CartDAOImpl implements CartDAO {
	@Autowired
	private JdbcTemplate jdbc;
	private final ItemDAO itemDAO;
	private final UserDAO userDAO;

	@Autowired
	public CartDAOImpl(JdbcTemplate jdbc, ItemDAO itemDAO, UserDAO userDAO) {
		this.jdbc = jdbc;
		this.itemDAO = itemDAO;
		this.userDAO = userDAO;
	}

	@Override
	public boolean createCart(Cart cart) {
		String sql = "INSERT INTO `onlineshopping`.`cart` "
				+ "(`Amount`, `CreateDate`, `UserID`) "
				+ "VALUES (?,?,?)";
		try {
			jdbc.update(sql, cart.getAmount(), 
					cart.getCreateDate(), 
					cart.getCustomer().getUser().getUserID());
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean updateCart(Cart cart, Item item, int quantity) {
		String sql1 = "UPDATE `onlineshopping`.`cartitem` "
				+ "SET `quantity` = ? "
				+ "WHERE (`CartID` = ?) and (`ItemID` = ?)";
		String sql2 = "UPDATE `onlineshopping`.`cart` "
				+ "SET `Amount` = ? "
				+ "WHERE (`CartID` = ?)";
		if (quantity == 0) {
			boolean status = removeItemCart(cart.getCartID(), item.getItemID(), cart.getItems().get(item));
			return true;
		}
		try {
			jdbc.update(sql1, quantity, cart.getCartID(), item.getItemID());
			long amount = getAmount(cart.getCartID());
			amount = amount - cart.getItems().get(item) * item.getPrice().getSellingPrice() 
					+ item.getPrice().getSellingPrice() * quantity;
			jdbc.update(sql2, amount, cart.getCartID());
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public Cart getCustomer(Customer customer) {
		String sql = "SELECT * FROM onlineshopping.cart where UserID = ? order by CartID desc limit 1";
		try {
			Cart cart = jdbc.queryForObject(sql, this::mapCart, customer.getUser().getUserID());
			return cart;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Cart getCart(int cartID) {
		String sql = "SELECT * FROM onlineshopping.cart where CartID = ?";
		try {
			Cart cart = jdbc.queryForObject(sql, this::mapCart, cartID);
			return cart;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Cart getOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<Item, Integer> getItemCart(int cartID) {
		Map<Item, Integer> items = new HashMap<Item, Integer>();
		try {
			String sql1 = "SELECT ItemID FROM onlineshopping.cartitem where CartID = ?";
			List<Integer> listItemID = jdbc.query(sql1, this::mapItemID, cartID);
			String sql2 = "SELECT quantity FROM onlineshopping.cartitem where CartID = ?";
			List<Integer> listQuantity = jdbc.query(sql2, this::mapQuantity, cartID);
			for(int i=0; i<listItemID.size(); i++) {
				int itemID = listItemID.get(i);
				int quantity = listQuantity.get(i);
				Item item = itemDAO.getItem(itemID);
				items.put(item, quantity);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return items;
	}
	
	@Override
	public boolean addCart(Cart cart, Item item, int quantity) {
		Map<Item, Integer> items = new HashMap<Item, Integer>();
		if(cart.getItems() != null) {
			items = cart.getItems();
		}
		String sql3 = "UPDATE `onlineshopping`.`cart` "
				+ "SET `Amount` = ? "
				+ "WHERE (`CartID` = ?)";
		if(items.containsKey(item)) {
			String sql1 = "UPDATE `onlineshopping`.`cartitem` "
					+ "SET `quantity` = ? "
					+ "WHERE (`CartID` = ?) and (`ItemID` = ?)";
			try {
				int total = items.get(item) + quantity;
				jdbc.update(sql1, total, cart.getCartID(), item.getItemID());
				long amount = getAmount(cart.getCartID());
				amount += item.getPrice().getSellingPrice() * quantity;
				jdbc.update(sql3, amount, cart.getCartID());
				return true;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
		}
		else {
			String sql2 = "INSERT INTO `onlineshopping`.`cartitem` "
					+ "(`CartID`, `ItemID`, `quantity`) VALUES (?,?,?)";
			try {
				jdbc.update(sql2, cart.getCartID(), item.getItemID(), quantity);
				long amount = getAmount(cart.getCartID());
				amount += (long) item.getPrice().getSellingPrice() * quantity;
				jdbc.update(sql3, amount, cart.getCartID());
				return true;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
		}
	}

	@Override
	public long getAmount(int cartID) {
		String sql = "SELECT Amount FROM onlineshopping.cart where CartID = ?";
		try {
			long amount = jdbc.queryForObject(sql, this::mapAmount, cartID);
			return amount;
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	@Override
	public boolean removeItemCart(int cartID, int itemID, int quantity) {
		String sql = "DELETE FROM `onlineshopping`.`cartitem` "
				+ "WHERE (`CartID` = ?) and (`ItemID` = ?)";
		String sql2 = "UPDATE `onlineshopping`.`cart` "
				+ "SET `Amount` = ? "
				+ "WHERE (`CartID` = ?)";
		try {
			jdbc.update(sql, cartID, itemID);
			long amount = getAmount(cartID);
			Item item = itemDAO.getItem(itemID);
			amount -= item.getPrice().getSellingPrice() * quantity;
			jdbc.update(sql2, amount, cartID);
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	private Integer mapItemID(ResultSet rs, int rowNum) throws SQLException {
		return rs.getInt("ItemID");
	}
	
	private Integer mapQuantity(ResultSet rs, int rowNum) throws SQLException {
		return rs.getInt("quantity");
	}
	
	private Long mapAmount(ResultSet rs, int rowNum) throws SQLException {
		return rs.getLong(1);
	}
	
	private Cart mapCart(ResultSet rs, int rowNum) throws SQLException {
		int CartID = rs.getInt(1);
		long Amount = rs.getLong(2);
		Date CreateDate = rs.getDate(3);
		int UserID = rs.getInt(4);
		User user = userDAO.getUser(UserID);
		Customer customer = new Customer(user);
		Map<Item, Integer> items = getItemCart(CartID);
		return new Cart(CartID, customer, items, Amount, CreateDate);
	}


}
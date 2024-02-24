package com.springboot.onlineshopping.dao.itemDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.springboot.onlineshopping.dao.userDAO.UserDAO;
import com.springboot.onlineshopping.dao.userDAO.UserDAOImpl;
import com.springboot.onlineshopping.model.item.Category;
import com.springboot.onlineshopping.model.item.Feedback;
import com.springboot.onlineshopping.model.item.Image;
import com.springboot.onlineshopping.model.item.Item;
import com.springboot.onlineshopping.model.item.Price;
import com.springboot.onlineshopping.model.item.Rating;
import com.springboot.onlineshopping.model.order.Order;
import com.springboot.onlineshopping.model.user.Customer;

@Repository
public class ItemDAOImpl implements ItemDAO {
	private JdbcTemplate jdbc;
	private UserDAO userDAO;
	
	@Autowired
	public ItemDAOImpl(JdbcTemplate jdbc, UserDAO userDAO) {
		this.jdbc = jdbc;
		this.userDAO = userDAO;
	}

	@Override
	public List<Item> getCategoryItem(int cateID) {
		List<Item> list = new ArrayList<Item>();
		String sql = "SELECT * FROM onlineshopping.item where CateID = ?";
		try {
			list = jdbc.query(sql, this::mapItem, cateID);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<Item> getAllItem() {
		String sql = "SELECT * FROM onlineshopping.item";
		List<Item> items = new ArrayList<Item>();
		try {
			items = jdbc.query(sql, this::mapItem);
		} catch (Exception e) {
			System.out.println(e);
		}
		return items;
	}

	@Override
	public Item getItem(int itemID) {
		String sql = "SELECT * FROM onlineshopping.item where ItemID=?";
		try {
			Item item = jdbc.queryForObject(sql, this::mapItem, itemID);
			return item;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Item> findItem(String key) {
		List<Item> list = new ArrayList<Item>();
		String sql = "SELECT * FROM onlineshopping.item where Name like '%" + key + "%' ";
		try {
			list = jdbc.query(sql, this::mapItem);
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	@Override
	public boolean updateCategory(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Category getCategory(int cateID) {
		String sql = "SELECT * FROM onlineshopping.category where CateID=?";
		try {
			Category category = jdbc.queryForObject(sql, this::mapCategory, cateID);
			return category;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Rating getRating(int ratingID) {
		String sql = "SELECT * FROM onlineshopping.rating where ratingID=?";
		try {
			Rating rating = jdbc.queryForObject(sql, this::mapRating, ratingID);
			return rating;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Feedback getFeedback(int customerID, int itemID, int cartID) {
		String sql = "SELECT * FROM onlineshopping.feedback "
				+ "where UserID = ? and ItemID = ? and CartID = ?";
		try {
			Feedback feedback = jdbc.queryForObject(sql, this::mapFeedback,
					customerID, itemID, cartID);
			return feedback;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Feedback addFeedback(Feedback feedback, Order order, int itemID) {
		if(getFeedback(feedback.getCustomer().getUser().getUserID(),
				itemID, order.getCart().getCartID()) == null) {
			String sql = "INSERT INTO `onlineshopping`.`feedback` "
					+ "(`ratingID`, `UserID`, `ItemID`, `CartID`, `Text`, `Date`) "
					+ "VALUES (?,?,?,?,?, now())";
			String sql2 = "select * from onlineshopping.feedback "
					+ "where feedbackID = last_insert_id();";
			try {
				jdbc.update(sql, feedback.getRating().getRatingID(),
						feedback.getCustomer().getUser().getUserID(),
						itemID,
						order.getCart().getCartID(),
						feedback.getText());
				Feedback feedback2 = jdbc.queryForObject(sql2, this::mapFeedback);
				return feedback2;
			} catch (Exception e) {
			}
		}
		return null;
	}

	@Override
	public Feedback getFeedback(int feedbackID) {
		String sql = "SELECT * FROM onlineshopping.feedback where feedbackid = ?";
		try {
			Feedback feedback = jdbc.queryForObject(sql, this::mapFeedback, feedbackID);
			return feedback;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@Override
	public List<Feedback> getRatingFeedback(int ratingID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Feedback> getItemFeedback(int itemID) {
		String sql = "SELECT * FROM onlineshopping.feedback where feedback.ItemID=?";
		List<Feedback> listfeedback = new ArrayList<Feedback>();
		try {
			listfeedback = jdbc.query(sql, this::mapFeedback, itemID);
		} catch (Exception e) {
			System.out.println(e);
		}
		return listfeedback;
	}

	
	private Feedback mapFeedback(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer(userDAO.getUser(rs.getInt(3)));
		Rating rating = getRating(rs.getInt(2));
		return new Feedback(rs.getInt(1), customer, rs.getString(6), rs.getString(7), rating);
	}

	private Item mapItem(ResultSet rs, int rowNum) throws SQLException {
		Price price = new Price(rs.getInt(7), rs.getInt(8));
		Category category = getCategory(rs.getInt(2));
		Image image = new Image(rs.getString(3), rs.getString(6));
		List<Feedback> list = getItemFeedback(rs.getInt(1));
		float ratingItem = 0;
		int count = 0;
		for(Feedback feedback : list) {
			count += 1;
			ratingItem += feedback.getRating().getStar();
		}
		if(count > 0) {
			ratingItem/=count;
		}
		DecimalFormat df = new DecimalFormat("#.##");
        String roundedNumber = df.format(ratingItem);
        float ratingItemnew = Float.parseFloat(roundedNumber);
		return new Item(rs.getInt(1), rs.getString(3), rs.getString(4), price, rowNum, category, image, list, ratingItemnew);
	}
	
	private Rating mapRating(ResultSet rs, int rowNum) throws SQLException {
		return new Rating(rs.getInt(1), rs.getString(2), rs.getInt(3));
	}
	
	private Category mapCategory(ResultSet rs, int rowNum) throws SQLException {
		return new Category(rs.getInt(1), rs.getString(2));
	}
	


}
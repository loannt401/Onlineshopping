package com.springboot.onlineshopping.dao.orderDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

@Repository
public class OrderDAOImpl implements OrderDAO {
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private CartDAO cartDAO;

	@Override
	public List<TypeShipment> getAllTypeShipments() {
		String sql = "SELECT * FROM onlineshopping.typeshipment";
		List<TypeShipment> list = new ArrayList<TypeShipment>();
		try {
			list = jdbc.query(sql, this::mapTypeShipment);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public TypeShipment getTypeShipment(int typeShipID) {
		String sql = "SELECT * FROM onlineshopping.typeshipment where TypeShipID = ?";
		try {
			TypeShipment typeShipment = jdbc.queryForObject(sql, this::mapTypeShipment, typeShipID);
			return typeShipment;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<MethodPayment> getAllMethodPayments() {
		List<MethodPayment> list = new ArrayList<MethodPayment>();
		String sql = "SELECT * FROM onlineshopping.methodpayment";
		try {
			list = jdbc.query(sql, this::mapMethodPayment);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public MethodPayment getMethodPayment(int methodPayID) {
		String sql = "SELECT * FROM onlineshopping.methodpayment where MethodPayID = ?";
		try {
			MethodPayment methodPayment = jdbc.queryForObject(sql, this::mapMethodPayment, methodPayID);
			return methodPayment;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Shipment addShipment(Shipment shipment) {
		String sql = "INSERT INTO `onlineshopping`.`shipment` "
				+ "(`TypeShipID`, `Fullname`, `phone`, `AddressDetail`, "
				+ "`Ward`, `District`, `City`) "
				+ "VALUES (?,?,?,?,?,?,?)";
		String sql2 = "select * from onlineshopping.shipment where ShipID = last_insert_id()";
		try {
			jdbc.update(sql, shipment.getTypeShip().getTypeShipID(),
					shipment.getFullname(),
					shipment.getPhone(),
					shipment.getAddress().getAddressDetail(),
					shipment.getAddress().getWard(),
					shipment.getAddress().getDistrict(),
					shipment.getAddress().getCity());
			Shipment shipment2 = jdbc.queryForObject(sql2, this::mapShipment);
			return shipment2;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public StatusPayment getStatusPayment(int statusPayID) {
		String sql = "SELECT * FROM onlineshopping.statuspayment where StatusPayID = ?";
		try {
			StatusPayment statusPayment = jdbc.queryForObject(sql, this::mapStatusPayment, statusPayID);
			return statusPayment;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Payment addPayment(Payment payment) {
		String sql3 = "select * from onlineshopping.payment where PayID = last_insert_id();";
		if(payment.getMethodPayment().getMethodPayID() == 1) {
			String sql1 = "INSERT INTO `onlineshopping`.`payment` "
					+ "(`MethodPayID`, `StatusPayID`, `TotalPrice`,"
					+ " `CostShipment`, `AmountCart`, `CardBank`, "
					+ "`CardName`, `CartNumber`) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			try {
				jdbc.update(sql1, payment.getMethodPayment().getMethodPayID(),
						payment.getStatusPayment().getStatusPayID(),
						payment.getTotalPrice(),
						payment.getCostShipment(),
						payment.getAmountCart(),
						payment.getCardBank(),
						payment.getCardName(),
						payment.getCardNumber());
			} catch (Exception e) {
				return null;
			}
		}
		else {
			String sql2 = "INSERT INTO `onlineshopping`.`payment` "
					+ "(`MethodPayID`, `StatusPayID`, `TotalPrice`,"
					+ " `CostShipment`, `AmountCart`) "
					+ "VALUES (?,?,?,?,?)";
			try {
				jdbc.update(sql2, payment.getMethodPayment().getMethodPayID(),
						payment.getStatusPayment().getStatusPayID(),
						payment.getTotalPrice(),
						payment.getCostShipment(),
						payment.getAmountCart());
			} catch (Exception e) {
				return null;
			}
		}

		try {
			Payment payment2 = jdbc.queryForObject(sql3, this::mapPayment);
			return payment2;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public StatusOrder getStatusOrder(int statusOrderID) {
		String sql = "SELECT * FROM onlineshopping.statusorder where StatusOrderID = ?";
		try {
			StatusOrder statusOrder = jdbc.queryForObject(sql, this::mapStatusOrder, statusOrderID);
			return statusOrder;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Order addOrder(Order order) {
		Shipment shipment = addShipment(order.getShipment());
		if(shipment != null) {
			 Payment payment = addPayment(order.getPayment());
			if(payment != null) {
				String sql = "INSERT INTO `onlineshopping`.`order` "
						+ "(`StatusOrderID`, `CartID`, `PayID`, `ShipID`, `Date`) "
						+ "VALUES (?,?,?,?, now())";
				String sql2 ="select * from onlineshopping.order where OrderID = last_insert_id()";
				try {
					jdbc.update(sql, 1,
							order.getCart().getCartID(),
							payment.getPayID(),
							shipment.getShipID());
					Order order2 = jdbc.queryForObject(sql2, this::mapOrder);
					return order2;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
		return null;
	}

	@Override
	public Shipment getShipment(int shipmentID) {
		String sql = "SELECT * FROM onlineshopping.shipment  where ShipID = ?";
		try {
			Shipment shipment = jdbc.queryForObject(sql, this::mapShipment, shipmentID);
			return shipment;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Payment getPayment(int paymentID) {
		String sql = "SELECT * FROM onlineshopping.payment where PayID = ?";
		try {
			Payment payment = jdbc.queryForObject(sql, this::mapPayment, paymentID);
			return payment;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Order getOrder(int orderID) {
		String sql = "SELECT * FROM onlineshopping.order where OrderID = ?";
		try {
			Order order = jdbc.queryForObject(sql, this::mapOrder, orderID);
			return order;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<Order> getCustomerOrder(int customerID) {
		String sql = "SELECT `order`.* "
				+ "FROM user "
				+ "JOIN cart ON user.UserID = cart.UserID "
				+ "JOIN `order` ON cart.CartID = `order`.CartID "
				+ "WHERE user.UserID = ?";
		List<Order> list = new ArrayList<Order>();
		try {
			list = jdbc.query(sql, this::mapOrder, customerID);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<Order> getCustomerStatusOrder(int customerID, int statusOrderID) {
		String sql = "SELECT `order`.* "
				+ "FROM user "
				+ "JOIN cart ON user.UserID = cart.UserID "
				+ "JOIN `order` ON cart.CartID = `order`.CartID "
				+ "WHERE user.UserID = ? and StatusOrderID = ?";
		List<Order> list = new ArrayList<Order>();
		try {
			list = jdbc.query(sql, this::mapOrder, customerID, statusOrderID);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	@Override
	public List<Order> getAllOrder() {
		List<Order> list = new ArrayList<Order>();
		String sql = "SELECT * FROM onlineshopping.order";
		try {
			list = jdbc.query(sql, this::mapOrder);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<Order> getStatusOrderOrder(int statusOrderID) {
		List<Order> list = new ArrayList<Order>();
		String sql = "SELECT * FROM onlineshopping.order where statusorderid = ?";
		try {
			list = jdbc.query(sql, this::mapOrder, statusOrderID);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public boolean updateOrder(int orderID, int statusOrderID) {
		String sql = "UPDATE `onlineshopping`.`order` "
				+ "SET `StatusOrderID` = ? WHERE (`OrderID` = ?)";
		try {
			jdbc.update(sql, statusOrderID, orderID);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean deleteOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean deleteShipment(Shipment shipment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePayment(Payment payment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePayment(Payment payment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getCartPayment(Cart cart) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getShipmentPayment(Shipment shipment) {
		// TODO Auto-generated method stub
		return 0;
	}

	private TypeShipment mapTypeShipment(ResultSet rs, int rowNum) throws SQLException {
		return new TypeShipment(rs.getInt(1), rs.getString(2), rs.getInt(3));
	}
	
	private MethodPayment mapMethodPayment(ResultSet rs, int rowNum) throws SQLException {
		return new MethodPayment(rs.getInt(1), rs.getString(2));
	}
	
	private StatusOrder mapStatusOrder(ResultSet rs, int rowNum) throws SQLException {
		return new StatusOrder(rs.getInt(1), rs.getString(2));
	}
	
	private StatusPayment mapStatusPayment(ResultSet rs, int rowNum) throws SQLException {
		return new StatusPayment(rs.getInt(1), rs.getString(2));
	}
	
	private Shipment mapShipment(ResultSet rs, int rowNum) throws SQLException {
		TypeShipment typeShipment = getTypeShipment(rs.getInt(2));
		Address address = new Address(rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
		return new Shipment(rs.getInt(1), typeShipment, rs.getString(3), rs.getString(4), address);
	}
	
	private Payment mapPayment(ResultSet rs, int rowNum) throws SQLException {
		MethodPayment methodPayment = getMethodPayment(rs.getInt(2));
		StatusPayment statusPayment = getStatusPayment(rs.getInt(3));
		return new Payment(rs.getInt(1), methodPayment, rs.getLong(4), rs.getInt(5), rs.getLong(6), statusPayment, rs.getString(7), rs.getFloat(8), rs.getString(9), rs.getString(10), rs.getString(11));
	}
	
	private Order mapOrder(ResultSet rs, int rowNum) throws SQLException {
		StatusOrder statusOrder = getStatusOrder(rs.getInt(2));
		Cart cart = cartDAO.getCart(rs.getInt(3));
		Payment payment = getPayment(rs.getInt(4));
		Shipment shipment = getShipment(rs.getInt(5));
		return new Order(rs.getInt(1), statusOrder, cart, payment, shipment, rs.getString(6));
	}
}
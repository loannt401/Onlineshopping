package com.springboot.onlineshopping.dao.orderDAO;

import java.util.List;

import com.springboot.onlineshopping.model.order.Cart;
import com.springboot.onlineshopping.model.order.MethodPayment;
import com.springboot.onlineshopping.model.order.Order;
import com.springboot.onlineshopping.model.order.Payment;
import com.springboot.onlineshopping.model.order.Shipment;
import com.springboot.onlineshopping.model.order.StatusOrder;
import com.springboot.onlineshopping.model.order.StatusPayment;
import com.springboot.onlineshopping.model.order.TypeShipment;
import com.springboot.onlineshopping.model.user.Customer;

public interface OrderDAO {
	List<TypeShipment> getAllTypeShipments();
	TypeShipment getTypeShipment(int typeShipID);
	List<MethodPayment> getAllMethodPayments();
	MethodPayment getMethodPayment(int methodPayID);
	Shipment addShipment(Shipment shipment);
	StatusPayment getStatusPayment(int statusPayID);
	Payment addPayment(Payment payment);
	StatusOrder getStatusOrder(int statusOrderID);
	Order addOrder(Order order);
	Shipment getShipment(int shipmentID);
	Payment getPayment(int paymentID);
	Order getOrder(int orderID);
	List<Order> getCustomerOrder(int customerID);
	List<Order> getCustomerStatusOrder(int customerID, int statusOrderID);
	List<Order> getAllOrder();
	List<Order> getStatusOrderOrder(int statusOrderID);
	boolean updateOrder(int orderID, int statusOrderID);
	boolean deleteOrder(Order order);
	boolean deleteShipment(Shipment shipment);
	boolean deletePayment(Payment payment);
	boolean updatePayment(Payment payment);
	float getCartPayment(Cart cart);
	float getShipmentPayment(Shipment shipment);

}